package ru.job4j.pool;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RowColSumTest {

    private int[][] matrix = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };

    @Test
    public void whenSequenceMatrix3By3ThenResultTrue() {
        RowColSum.Sums[] sum = RowColSum.sum(matrix);
        RowColSum.Sums[] result = new RowColSum.Sums[] {
                new RowColSum.Sums(6, 12),
                new RowColSum.Sums(15, 15),
                new RowColSum.Sums(24, 18)
        };
        assertThat(sum, is(result));
    }

    @Test
    public void whenSequenceMatrix3By3ThenAsyncResultTrue() {
        RowColSum.Sums[] sum = RowColSum.asyncSum(matrix);
        RowColSum.Sums[] result = new RowColSum.Sums[] {
                new RowColSum.Sums(6, 12),
                new RowColSum.Sums(15, 15),
                new RowColSum.Sums(24, 18)
        };
        assertThat(sum, is(result));
    }
}