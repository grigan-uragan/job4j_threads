package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final SimpleArrayList<T> storage = new SimpleArrayList<>();

    public synchronized void add(T element) {
        storage.add(element);
    }

    public synchronized T get(int index) {
        return storage.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(storage).iterator();
    }

    private SimpleArrayList<T> copy(SimpleArrayList<T> original) {
        SimpleArrayList<T> result = new SimpleArrayList<>();
        for (T temp : original) {
            result.add(temp);
        }
        return result;
    }
}
