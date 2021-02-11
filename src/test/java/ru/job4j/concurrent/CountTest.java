package ru.job4j.concurrent;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class CountTest {

    private class ThreadCount extends Thread {
        private final Count count;

        public ThreadCount(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            count.increment();
        }
    }

    @Test
    public void whenExecute2ThreadThen2() {
        final Count count = new Count();
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        first.start();
        second.start();
        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertThat(count.get(), is(2));
    }

}