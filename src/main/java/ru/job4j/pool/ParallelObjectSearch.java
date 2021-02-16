package ru.job4j.pool;

import java.util.concurrent.RecursiveTask;

public class ParallelObjectSearch<T> extends RecursiveTask<Integer> {
    private final T[] data;
    private final T element;
    private final int start;
    private final int end;

    public ParallelObjectSearch(T[] data, T element, int start, int end) {
        this.data = data;
        this.element = element;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - start) < 10) {
            for (int i = start; i < end; i++) {
                if (element.equals(data[i])) {
                    return i;
                }
            }
            return -1;
        }
        int mid = (start + end) / 2;
        ParallelObjectSearch<T> leftSearch = new ParallelObjectSearch<>(data, element, start, mid);
        ParallelObjectSearch<T> rightSearch = new ParallelObjectSearch<>(data, element, mid, end);
        leftSearch.fork();
        rightSearch.fork();
        int result = leftSearch.join();
        if (result == -1) {
            result = rightSearch.join();
        }
        return result;
    }

    public int getIndex() {
        return this.invoke();
    }
}
