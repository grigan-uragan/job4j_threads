package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        char ch = '/';
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if (ch == '/') {
                    ch = '|';
                } else if (ch == '|') {
                    ch = '\\';
                } else {
                    ch = '/';
                }
                Thread.sleep(500);
                System.out.print("\rLoading....." + ch);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("was interrupted");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new ConsoleProgress());
        thread.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
