package ru.job4j.concurrent;

public class Barrier {
    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(10);
        Thread first = new Thread(
                () -> {
                    for (int i = 0; i < 20; i++) {
                        countBarrier.count();
                    }
                }
        );

        Thread second = new Thread(
                () -> {
                    countBarrier.await();
                    System.out.println("Lets go");
                }
        );

        first.start();
        second.start();
        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
