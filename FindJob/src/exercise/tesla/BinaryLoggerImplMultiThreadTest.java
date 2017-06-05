package exercise.tesla;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;

/**
 * Created by romil on 6/4/17.
 */
public class BinaryLoggerImplMultiThreadTest extends Thread{
    private Thread t;
    private String threadname;
    static String path = "/Users/romil/";
    final static String filename1 = "mylog.log";
    final static String fullFilename1 = path + filename1;
    BinaryLoggerImpl<MyBinaryLoggable> bli;

    public BinaryLoggerImplMultiThreadTest(String threadname, BinaryLoggerImpl<MyBinaryLoggable> bli) {
        this.bli = bli;
        this.threadname = threadname;
    }

    public void run() {

        String data = "";
        for (int i=0; i < 5; i++) {
            data += threadname;
            System.out.println("Data being written:" + data);
            MyBinaryLoggable binLog1 = new MyBinaryLoggable(data);
            try {
                bli.write(binLog1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void start() {
        if (t == null) {
            t = new Thread(this, threadname);
            t.start();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        BinaryLoggerImplTest.deletePrevLogs(path, filename1);
        BinaryLoggerImpl<MyBinaryLoggable> bli = new BinaryLoggerImpl<>(fullFilename1, 30);

        BinaryLoggerImplMultiThreadTest t1 = new BinaryLoggerImplMultiThreadTest("One", bli);
        BinaryLoggerImplMultiThreadTest t2 = new BinaryLoggerImplMultiThreadTest("Two", bli);

        t1.start();
        t2.start();

        while (t1.isAlive() || t2.isAlive()) {
            t1.join();
            t2.join();
        }

        Thread th1 = new Thread(new LogReader(bli));


        th1.start();
        th1.join();
    }

    public static class LogReader implements Runnable {
        BinaryLoggerImpl<MyBinaryLoggable> bli;

        public LogReader(BinaryLoggerImpl<MyBinaryLoggable> bli) {
            this.bli = bli;
        }

        @Override
        public void run() {
            Iterator<MyBinaryLoggable> itr = null;
            try {
                itr = bli.read(new File(fullFilename1), MyBinaryLoggable.class);
                while (itr.hasNext()) {
                    System.out.println(itr.next());
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }

        }
    }
}
