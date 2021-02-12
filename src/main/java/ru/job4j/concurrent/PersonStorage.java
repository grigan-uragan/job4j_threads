package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@ThreadSafe
public class PersonStorage {
    @GuardedBy("this")
    private final List<Person> persons = new ArrayList<>();

    public synchronized boolean add(Person person) {
        if (persons.contains(person)) {
            return false;
        }
        return persons.add(person);
    }

    public synchronized boolean update(Person person) {
        int index = persons.indexOf(person);
        persons.set(index, person);
        return index != -1;
    }

    public synchronized boolean delete(Person person) {
        return persons.remove(person);
    }

    public synchronized void transfer(int idFrom, int idTo, int amount) {
        Person from = findById(idFrom);
        Person to = findById(idTo);
        from.setAmount(-amount);
        to.setAmount(amount);
    }

    private synchronized Person findById(int id) {
        for (Person p : persons) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new NoSuchElementException("Person with id=" + id + " not found");
    }
}
