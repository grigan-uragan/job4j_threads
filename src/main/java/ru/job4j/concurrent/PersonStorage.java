package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

@ThreadSafe
public class PersonStorage {
    @GuardedBy("this")
    private final Map<Integer, Person> persons = new HashMap<>();

    public synchronized boolean add(Person person) {
        boolean result = persons.containsKey(person.getId());
        if (result) {
            update(person);
        } else {
            persons.put(person.getId(), person);
        }
        return !result;
    }

    public synchronized boolean update(Person person) {
        boolean result = persons.containsKey(person.getId());
        if (result) {
            persons.replace(person.getId(), person);
        }
        return result;
    }

    public synchronized boolean delete(Person person) {
        Person result = persons.remove(person.getId());
        return result != null;
    }

    public synchronized void transfer(int idFrom, int idTo, int amount) {
        Person from = findById(idFrom);
        Person to = findById(idTo);
        if (from != null && to != null) {
            int balanceFrom = from.getAmount();
            if (balanceFrom < amount) {
                throw new IllegalStateException("insufficient funds");
            }
            int balanceTo = to.getAmount();
            from.setAmount(balanceFrom - amount);
            to.setAmount(balanceTo + amount);
        }

    }

    private synchronized Person findById(int id) {
        Person result;
        result = persons.get(id);
        return result;
    }
}
