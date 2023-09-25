package experiments.concurrency.trial;

import java.util.Random;

public class Consumer implements Runnable {
    private Drop drop;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();
        for (String message = drop.take();
             ! message.equals("DONE");
             message = drop.take()) {
            System.out.format("MESSAGE RECEIVED: %s%n", message);
            try {
                System.out.println("Consumer sleeping...");
                Thread.sleep(random.nextInt(5000));
                System.out.println("Consumer woke up...");
            } catch (InterruptedException e) {}
        }
    }
}