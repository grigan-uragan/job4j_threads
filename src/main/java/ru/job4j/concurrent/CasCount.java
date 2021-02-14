package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CasCount {
    private int start;
    private final AtomicReference<Integer> count = new AtomicReference<>(start);

    public CasCount(int start) {
        this.start = start;
    }

    public void increment() {
        Integer ref;
        int newValue;
        do {
            ref = count.get();
            newValue = ref + 1;
        } while (!count.compareAndSet(ref, newValue));
    }

    public int get() {
        return count.get();
    }
}
