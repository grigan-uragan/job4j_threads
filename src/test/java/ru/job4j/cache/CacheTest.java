package ru.job4j.cache;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void whenAddModelThenTrue() {
        Base first = new Base(1, 0);
        Cache cache = new Cache();
        assertThat(cache.add(first), is(true));
    }

    @Test
    public void whenAdd2EqualsModelThenFalse() {
        Base first = new Base(1, 0);
        Base second = new Base(1, 0);
        Cache cache = new Cache();
        cache.add(first);
        assertThat(cache.add(second), is(false));
    }

    @Test
    public void whenUpdateModelThenVersion1() {
        Base first = new Base(1, 0);
        Cache cache = new Cache();
        cache.add(first);
        cache.update(first);
        assertThat(first.getVersion(), is(1));
    }

    @Test
    public void whenDeleteModelThenCacheIsEmpty() {
        Base first = new Base(1, 0);
        Cache cache = new Cache();
        cache.add(first);
        cache.delete(first);
        assertThat(cache.isEmpty(), is(true));
    }

    @Test(expected = OptimisticException.class)
    public void whenModelVersionNotEqualsThenUpdateFallingWithException() {
        Base first = new Base(1, 0);
        Base second = new Base(1, 0);
        Cache cache = new Cache();
        cache.add(first);
        first.setVersion(1);
        cache.update(second);
    }
}