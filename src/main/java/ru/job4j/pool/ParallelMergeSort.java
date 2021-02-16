package ru.job4j.pool;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelMergeSort extends RecursiveTask<int[]> {
    private final int[] array;
    private final int from;
    private final int to;

    public ParallelMergeSort(int[] array, int from, int to) {
        this.array = array;
        this.from = from;
        this.to = to;
    }

    @Override
    protected int[] compute() {
        if (from == to) {
            return new int[] {array[from]};
        }
        int mid = (from + to) / 2;
        ParallelMergeSort leftSort = new ParallelMergeSort(array, from, mid);
        ParallelMergeSort rightSort = new ParallelMergeSort(array, mid + 1, to);
        leftSort.fork();
        rightSort.fork();
        int[] left = leftSort.join();
        int[] right = rightSort.join();
        return MergeSort.merge(left, right);
    }

    public static int[] parallelSort(int[] array) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new ParallelMergeSort(array, 0, array.length - 1));
    }

    public static void main(String[] args) {
        int[] array = new int[]{10, 3, 34, 2, 67, 99, 1, 12, 9, 5, 6};
        array = ParallelMergeSort.parallelSort(array);
        System.out.println(Arrays.toString(array));
    }
}
