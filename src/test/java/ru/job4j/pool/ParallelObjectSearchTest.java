package ru.job4j.pool;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ParallelObjectSearchTest {
    Integer[] data = new Integer[] {
            10, 3, 34, 2, 67, 99, 1, 12, 9, 5, 6, 100, 97, 45, 43, 39, -5, 0
    };

    @Test
    public void whenObjectContainsThenReturnIndex() {
        ParallelObjectSearch<Integer> search =
                new ParallelObjectSearch<>(data, 0, 0, data.length);
        assertThat(search.getIndex(), is(17));
    }

    @Test
    public void whenObjectNotContainsThenReturnMinusOne() {
        ParallelObjectSearch<Integer> search =
                new ParallelObjectSearch<>(data, 11, 0, data.length);
        assertThat(search.getIndex(), is(-1));
    }

}