package ru.job4j.concurrent;

public class Switcher {
    private boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public static void main(String[] args) {
        Switcher switcher = new Switcher();
        Thread first = new Thread(() -> {
            while (true) {
                synchronized (switcher) {
                    if (switcher.isFlag()) {
                        try {
                            switcher.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println("Thread A");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    switcher.setFlag(true);
                    switcher.notify();
                }
            }
        });

        Thread second = new Thread(() -> {
            while (true) {
                synchronized (switcher) {
                    if (!switcher.isFlag()) {
                        try {
                            switcher.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Thread B");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    switcher.setFlag(false);
                    switcher.notify();
                }
            }
        });
        first.start();
        second.start();
        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
