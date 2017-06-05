package exercise.tesla;

import javafx.util.Pair;

import java.io.*;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.util.*;

/**
 * This class is the iterator over a log file.
 * To use this, you only have to provide the base log file.
 * It will go and fetch rolled over files and the current files
 * and iterate over them (starting with the oldest).
 * It will return the object of class that implements T.
 */
public class BinaryLoggerIterator<T extends BinaryLoggable> implements Iterator<T> {

    private final File fileToIterate;
    private final Class<T> classToReturnObjectsOf;
    private ArrayList<Pair<File,Long>> rolledOverLogFiles = new ArrayList<>();
    private Iterator<Pair<File, Long>> logFilesItr = null;
    private FileInputStream currentFileInputStream = null;
    private int nextBlockSizeToFetch;

    /**
     * Constructor for this iterator.
     *
     * @param fileToIterate The File handle of the base log file to iterate over.
     * @param classToReturnObjectsOf The class whose object will wrap the byte array from the log files
     * @throws IOException
     */
    public BinaryLoggerIterator(File fileToIterate, Class<T> classToReturnObjectsOf) throws
            IOException {
        this.fileToIterate = fileToIterate;
        this.classToReturnObjectsOf = classToReturnObjectsOf;
        getAllRolledOverFiles();
        sortFilesByCreationDate();
        startFetchingNext();
    }

    /**
     * Sorts the files by their creation date (oldest first).
     *
     * @throws IOException
     */
    private void sortFilesByCreationDate() {
        Collections.sort(rolledOverLogFiles, new Comparator<Pair<File, Long>>() {
            @Override
            public int compare(Pair<File, Long> o1, Pair<File, Long> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
    }

    /**
     * Get the created date of the file.
     *
     * @param file the file to get the creation time of.
     * @return FileTime of the created date.
     * @throws IOException
     */
    private long getCreatedTime(File file) throws IOException {
        long currentTime = new Timestamp(System.currentTimeMillis()).getTime();
        String filename = file.getName();

        String[] split = filename.split(BinaryLoggerImpl.TIMESTAMP_SEPARATOR);

        if (split.length == 1) return currentTime;

        String ts = split[split.length-1];
        long t;
        try {
            t = Long.parseLong(ts);
        } catch (NumberFormatException e) {
            return currentTime;
        }

        return t;
    }

    /**
     * Finds all the log files - the current log file and rolled over log files.
     *
     * @throws IOException
     */
    private void getAllRolledOverFiles() throws IOException {
        String path = fileToIterate.getParent();
        File directory = new File(path);
        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith(fileToIterate.getName());
            }
        };
        File[] rolledOverLogFilesList = directory.listFiles(filenameFilter);
        if (rolledOverLogFilesList == null) return;
        for (File f : rolledOverLogFilesList) {
            rolledOverLogFiles.add(new Pair<>(f,new Long(getCreatedTime(f))));
        }
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return (nextBlockSizeToFetch != 0);
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();

        try {
            byte[] logBytes = new byte[nextBlockSizeToFetch];
            currentFileInputStream.read(logBytes);
            T t = classToReturnObjectsOf.newInstance();
            t.fromBytes(logBytes);
            startFetchingNext();
            return t;

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } catch (IllegalAccessException e) {

        } catch (InstantiationException e) {

        }
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /**
     * Prepare to fetch for the next time next() is called.
     * @throws IOException
     */
    private void startFetchingNext() throws IOException {
        if (logFilesItr == null) {
            logFilesItr = rolledOverLogFiles.iterator();
            nextBlockSizeToFetch = 0;
        }

        if (currentFileInputStream == null && logFilesItr.hasNext()) {
            currentFileInputStream = new FileInputStream(logFilesItr.next().getKey());
        }
        byte[] sizeInBytes = new byte[4];

        if (currentFileInputStream != null) {
            int bytesRead = currentFileInputStream.read(sizeInBytes);
            if (bytesRead == -1) {
                if (logFilesItr.hasNext()) {
                    currentFileInputStream.close();
                    currentFileInputStream = new FileInputStream(logFilesItr.next().getKey());
                    sizeInBytes = new byte[4];
                    currentFileInputStream.read(sizeInBytes);
                } else {
                    currentFileInputStream.close();
                    currentFileInputStream = null;
                    nextBlockSizeToFetch = 0;
                    return;
                }
            }
            nextBlockSizeToFetch = ByteBuffer.wrap(sizeInBytes).getInt();
        }
    }
}
