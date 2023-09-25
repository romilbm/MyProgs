package experiments.concurrency.trial;

import java.util.Random;

public class Producer implements Runnable {
    private Drop drop;

    public Producer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };
        Random random = new Random();

        for (String s: importantInfo) {
            drop.put(s);
            try {
                System.out.println("Producer sleeping...");
                Thread.sleep(random.nextInt(5000));
                System.out.println("Producer woke up...");
            } catch (InterruptedException e) {}
        }
        drop.put("DONE");
    }
}