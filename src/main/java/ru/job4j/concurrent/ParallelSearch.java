package ru.job4j.concurrent;

public class ParallelSearch {
    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "Consumer");
        consumer.start();
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 3; i++) {
                        try {
                            queue.offer(i);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "Producer");
        producer.start();
        try {
            producer.join();
            consumer.interrupt();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
