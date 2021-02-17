package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class MasterSlaveBarrier {
    @GuardedBy("this")
    private boolean flag = false;

    public void tryMaster() throws InterruptedException {
        synchronized (this) {
            if (flag) {
                this.wait();
            }
            System.out.println("Thread master");
        }
    }

    public void trySlave() throws InterruptedException {
        synchronized (this) {
            if (!flag) {
                this.wait();
            }
            System.out.println("Thread slave");
        }
    }

    public void doneMaster() {
       synchronized (this) {
           if (!flag) {
               flag = true;
               this.notify();
           }
       }
    }

    public void doneSlave() {
        synchronized (this) {
            if (flag) {
                flag = false;
                this.notify();
            }
        }
    }
}
