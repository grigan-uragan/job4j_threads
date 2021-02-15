package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (k, m) -> {
            if (model.getVersion() != m.getVersion()) {
                throw new OptimisticException("Versions are not equals");
            }
            model.setVersion(m.getVersion() + 1);
            return model;
        }) == null;
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }

    public boolean isEmpty() {
        return memory.isEmpty();
    }
}
