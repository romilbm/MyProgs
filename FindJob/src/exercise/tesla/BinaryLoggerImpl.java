package exercise.tesla;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The class that implements {@link BinaryLogger}. It provides a way to write to the current log file
 * and rolls over internally when the max limit is reached for writing into a file. It also provides a
 * way to read the current and all rolled over files using an iterator.
 * @param <T>
 */
public class BinaryLoggerImpl<T extends BinaryLoggable> implements BinaryLogger<T>{

    private String filenameToWriteTo;
    private long maxFileLimit;
    public static final String FILE_NAME_SEPARATOR = "-";
    public static final String TIMESTAMP_SEPARATOR = "#";
    private Lock lock = new ReentrantLock();

    /**
     * Constructor. Use the factory to get an instance of this class.
     *
     * @param filenameToWriteTo
     * @param maxFileLimit
     */
    public BinaryLoggerImpl(String filenameToWriteTo, long maxFileLimit) {
        this.filenameToWriteTo = filenameToWriteTo;
        this.maxFileLimit = maxFileLimit;
    }

    /**
     * Writes the serialized instance.
     *
     * @param loggable an instance of {@link BinaryLoggable} that needs to be logged
     * @throws IOException if any IO operation fails
     */
    @Override
    public void write(T loggable) throws IOException {
        lock.lock();
        try {
            synchronizedWriting(loggable);
        }
        finally {
            lock.unlock();
        }

    }

    private void synchronizedWriting(T loggable) throws IOException {
        byte[] logBytes = loggable.toBytes();
        byte[] sizeInBytes = ByteBuffer.allocate(4).putInt(logBytes.length).array();

        File fileToWriteTo = new File(filenameToWriteTo);
        boolean appendMode = false;
        long currentSize = 0;

        //File has not yet reached the max limit so we may be able to write to it.
        if (fileToWriteTo.exists() && !fileToWriteTo.isDirectory() && fileToWriteTo.length() < maxFileLimit) {
            currentSize = fileToWriteTo.length();
            appendMode = true;
        }

        //the permissible space is not enough to accommodate the current log entry. so we will roll over.
        if ((maxFileLimit - currentSize) < (logBytes.length + sizeInBytes.length)) {
            File rolledOverFile = getRolledOverFile(fileToWriteTo);
            fileToWriteTo.renameTo(rolledOverFile);
            appendMode = false;
        }

        FileOutputStream fos = new FileOutputStream(filenameToWriteTo, appendMode);
        fos.write(sizeInBytes);
        fos.write(logBytes);
        fos.close();
    }

    private File getRolledOverFile(File f) {
        String currentFileName = f.getAbsolutePath();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String postfix = sdf.format(timestamp) + TIMESTAMP_SEPARATOR + timestamp.getTime();
        String newFileName = currentFileName + FILE_NAME_SEPARATOR + postfix;

        return new File(newFileName);
    }

    /**
     * Read and iterate through instances persisted in the given file. If the filename doesn't point
     * to a log file, it will return an empty iterator.
     *
     * @param file  a file instance from which to read from @param clazz a class of the type T, clazz
     *              should have a public no-arg constructor.
     * @param clazz The Object to the type Class<T>. Every iterated entity is of that type.
     * @throws IOException if any IO operation fails
     */
    @Override
    public Iterator<T> read(File file, Class<T> clazz) throws IOException {
        System.out.println("Came to read data");
        return new BinaryLoggerIterator<T>(file,clazz);
    }

    /**
     * Closes this stream and releases any system resources associated
     * with it. If the stream is already closed then invoking this
     * method has no effect.
     *
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void close() throws IOException {
        //do nothing. we have already closed all connections.
    }
}
