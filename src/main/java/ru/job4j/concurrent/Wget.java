package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 100; i++) {
                            System.out.print("\rLoading: " + i + "%");
                            Thread.sleep(500);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        );
        thread.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
