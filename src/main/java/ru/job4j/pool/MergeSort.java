package ru.job4j.pool;

import java.util.Arrays;

public class MergeSort {
    public static int[] sort(int[] array) {
        return sort(array, 0, array.length - 1);
    }

    public static int[] sort(int[] array, int start, int end) {
        if (start == end) {
            return new int[] {array[start]};
        }
        int mid = (start + end) / 2;
        return merge(sort(array, start, mid), sort(array, mid + 1, end));
    }

    public static int[] merge(int[] left, int[] right) {
        int li = 0;
        int ri = 0;
        int resI = 0;
        int[] result = new int[left.length + right.length];
        while (resI != result.length) {
            if (li == left.length) {
                result[resI++] = right[ri++];
            } else if (ri == right.length) {
                result[resI++] = left[li++];
            } else if (left[li] < right[ri]) {
                result[resI++] = left[li++];
            } else {
                result[resI++] = right[ri++];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = new int[]{10, 3, 34, 2, 67, 99, 1, 12, 9, 5, 6};
        System.out.println(Arrays.toString(sort(array)));
    }
}
