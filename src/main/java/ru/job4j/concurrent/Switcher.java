package ru.job4j.concurrent;

public class Switcher {

    public static void main(String[] args) {
        MasterSlaveBarrier barrier = new MasterSlaveBarrier();
        Thread master = new Thread(() -> {
            while (true) {
                try {
                    barrier.tryMaster();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                barrier.doneMaster();
            }
        });

        Thread slave = new Thread(() -> {
            while (true) {
                try {
                    barrier.trySlave();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                barrier.doneSlave();
            }
        });

        master.start();
        slave.start();
        try {
            master.join();
            slave.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
