package ru.job4j.concurrent;

public class ParallelSearch {
    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread consumer = new Thread(
                () -> {
                    while (true) {
                        System.out.println(queue.poll());
                    }
                }, "Consumer");
        consumer.setDaemon(true);
        consumer.start();
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 3; i++) {
                        queue.offer(i);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "Producer");
        producer.start();
        try {
            producer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
