package exercise.tesla;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Iterator;

/**
 * Testing @{@link BinaryLoggerImpl}
 */
public class BinaryLoggerImplTest {
    public static void main(String[] args) {
        String path = "/Users/romil/";
        final String filename1 = "mylog.log";
        final String filename2 = "myerror.log";
        final String fullFilename1 = path + filename1;
        final String fullFilename2 = path + filename2;

        MyBinaryLoggable binLog1 = new MyBinaryLoggable("One");
        AlsoMyBinaryLoggable binLog2 = new AlsoMyBinaryLoggable("OneOne");
        MyBinaryLoggable binLog3 = new MyBinaryLoggable("OneOneOne");
        AlsoMyBinaryLoggable binLog4 = new AlsoMyBinaryLoggable("OneOneOneOne");

        BinaryLoggerImpl<MyBinaryLoggable> bli1 = new BinaryLoggerImpl<>(fullFilename1, 20);
        BinaryLoggerImpl<AlsoMyBinaryLoggable> bli2 = new BinaryLoggerImpl<>(fullFilename1, 20);
        BinaryLoggerImpl<MyBinaryLoggable> bli3 = new BinaryLoggerImpl<>(fullFilename1, 20);
        try {
            bli1.write(binLog1);
            bli2.write(binLog2);
            bli1.write(binLog3);
            bli2.write(binLog4);
            Iterator<MyBinaryLoggable> itr = bli3.read(new File(fullFilename1), MyBinaryLoggable.class);

            while (itr.hasNext()) {
                System.out.println(itr.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        deletePrevLogs(path, filename1);
    }

    public static void deletePrevLogs(String path, final String filename) {
        File directory = new File(path);
        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith(filename);
            }
        };
        File[] toBeDeleted = directory.listFiles(filenameFilter);
        for (File f : toBeDeleted) {
            f.delete();
        }
    }
}
