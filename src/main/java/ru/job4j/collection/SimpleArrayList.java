package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements Iterable<T> {
    private Object[] container = new Object[10];
    private int modCount = 0;
    private int size = 0;

    public void add(T element) {
        if (container.length == size) {
            container = growUp();
        }
        container[size++] = element;
        modCount++;
    }

    public T get(int index) {
        return (T) container[Objects.checkIndex(index, size)];
    }

    private Object[] growUp() {
        int oldCapacity = container.length;
        int newCapacity = (oldCapacity + (oldCapacity / 2 + 1));
        return Arrays.copyOf(container, newCapacity);
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int expectedMod = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                return point != size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedMod != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[point++];
            }
        };
    }
}
