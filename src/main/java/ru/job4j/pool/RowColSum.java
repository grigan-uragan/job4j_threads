package ru.job4j.pool;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RowColSum {
    public static Sums[] sum(int[][] matrix) {
        Sums[] result = fill(matrix.length);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i].rowSum += matrix[i][j];
                result[j].colSum += matrix[i][j];
            }
        }
        return result;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        Sums[] result = new Sums[matrix.length];
        for (int i = 0; i < result.length; i++) {
            try {
                result[i] = new Sums(getSumRow(matrix, i).get(), getSumCol(matrix, i).get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static CompletableFuture<Integer> getSumRow(int[][] matrix, int row) {
        return CompletableFuture.supplyAsync(() -> {
            int result = 0;
            for (int i = 0; i < matrix[row].length; i++) {
                result += matrix[row][i];
            }
            return result;
        });
    }

    private static CompletableFuture<Integer> getSumCol(int[][] matrix, int col) {
        return CompletableFuture.supplyAsync(() -> {
            int result = 0;
            for (int i = 0; i < matrix.length; i++) {
                result += matrix[i][col];
            }
            return result;
        });
    }

    private static Sums[] fill(int len) {
        Sums[] result = new Sums[len];
        for (int i = 0; i < result.length; i++) {
            result[i] = new Sums(0, 0);
        }
        return result;
    }

    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }
    }
}
