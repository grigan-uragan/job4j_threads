package ru.job4j.concurrent;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CasCountTest {

    @Test
    public void when10ThreadIncrementThenReturn10() throws InterruptedException {
        CasCount casCount = new CasCount(0);
        for (int i = 0; i < 10; i++) {
           new Thread(casCount::increment,"#" + i).start();
        }

        Thread.sleep(1000);

        assertThat(casCount.get(), is(10));
    }

    @Test
    public void when10000ThreadIncrementThenCount10000() throws InterruptedException {
        CasCount casCount = new CasCount(0);
        for (int i = 0; i < 10000; i++) {
            new Thread(casCount::increment,"#" + i).start();
        }

        Thread.sleep(5000);

        assertThat(casCount.get(), is(10000));
    }

}