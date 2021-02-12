package ru.job4j.concurrent;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PersonStorageTest {

    @Test
    public void whenAddedTwoPersonThenOk() {
        Person first = new Person(1, 100);
        Person second = new Person(2, 200);
        PersonStorage storage = new PersonStorage();
        assertThat(storage.add(first), is(true));
        assertThat(storage.add(second), is(true));
    }

    @Test
    public void whenUpdateFirstPersonThenTrue() {
        Person first = new Person(1, 100);
        Person second = new Person(1, 200);
        PersonStorage storage = new PersonStorage();
        storage.add(first);
        assertThat(storage.update(second), is(true));
    }

    @Test
    public void whenDeleteOnePersonThenTrue() {
        Person first = new Person(1, 100);
        Person second = new Person(2, 200);
        PersonStorage storage = new PersonStorage();
        storage.add(first);
        storage.add(second);
        assertThat(storage.delete(second), is(true));
    }

    @Test
    public void whenAdded2EqualsPersonThenFalse() {
        Person first = new Person(1, 100);
        Person second = new Person(1, 200);
        PersonStorage storage = new PersonStorage();
        storage.add(first);
        assertThat(storage.add(second), is(false));
    }

    @Test
    public void whenTransfer50ThenFirstGive50SecondTake50() {
        Person first = new Person(1, 100);
        Person second = new Person(2, 200);
        PersonStorage storage = new PersonStorage();
        storage.add(first);
        storage.add(second);
        storage.transfer(1, 2, 50);
        assertThat(first.getAmount(), is(50));
        assertThat(second.getAmount(), is(250));
    }
}