/*
 *  Copyright (c) 2018 Otávio Santana and others
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *  The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *  and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *  You may elect to redistribute this code under either of these licenses.
 *  Contributors:
 *  Otavio Santana
 */
package org.jnosql.aphrodite.antlr.cache;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TTLCacheTest {


    private final Function<String, Integer> supplier = Integer::valueOf;

    @Test
    public void shouldReturnErrorWhenDurationIsNull() {
        assertThrows(NullPointerException.class, () -> TTLCache.of(1, null, null));

    }

    @Test
    public void shouldReturnIllegalWhenValueIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> TTLCache.of(-1, TimeUnit.DAYS, supplier));
    }

    @Test
    public void shouldReturnIllegalWhenValueIsZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            TTLCache.of(0, TimeUnit.DAYS, supplier);
        });
    }

    @Test
    public void shouldCreateInstance() {
        assertNotNull(TTLCache.of(1L, TimeUnit.DAYS,supplier));
        assertNotNull(TTLCache.of(1L, TimeUnit.HOURS, supplier));
        assertNotNull(TTLCache.of(1L, TimeUnit.MINUTES, supplier));
        assertNotNull(TTLCache.of(1L, TimeUnit.SECONDS, supplier));
        assertNotNull(TTLCache.of(1L, TimeUnit.MICROSECONDS, supplier));
        assertNotNull(TTLCache.of(1L, TimeUnit.NANOSECONDS, supplier));
        assertNotNull(TTLCache.of(1L, TimeUnit.MILLISECONDS, supplier));
    }

    @Test
    public void shouldPut() {
        Map<String, Integer> map = TTLCache.of(100, TimeUnit.MILLISECONDS, supplier);
        assertNull(map.put("one", 1));
        assertNotNull(map.put("one", 1));
    }

    @Test
    public void shouldGet() throws InterruptedException {
        Map<String, Integer> map = TTLCache.of(2, TimeUnit.MILLISECONDS, supplier);
        map.put("1", 1);
        assertNotNull(map.get("1"));
        TimeUnit.MILLISECONDS.sleep(5L);
        assertNotNull(map.get("1"));
    }

    @Test
    public void shouldUseSupplierWhenGetDoesHaveData()  {
        Function<String, Integer> supplier = Integer::valueOf;

        Map<String, Integer> map = TTLCache.of(2, TimeUnit.MILLISECONDS, supplier);
        assertEquals(Integer.valueOf(1), map.get("1"));
    }

    @Test
    public void shouldUseSupplierWhenDataIsExpired() throws InterruptedException {
        Function<String, Integer> supplier = Integer::valueOf;
        Map<String, Integer> map = TTLCache.of(2, TimeUnit.MILLISECONDS, supplier);
        map.put("1", 1_000);
        assertNotNull(map.get("1"));
        assertEquals(Integer.valueOf(1_000), map.get("1"));
        TimeUnit.MILLISECONDS.sleep(5L);
        assertEquals(Integer.valueOf(1), map.get("1"));
    }

    @Test
    public void shouldGetSize() {
        Map<String, Integer> map = TTLCache.of(2, TimeUnit.MILLISECONDS, supplier);
        assertEquals(0, map.size());
        map.put("one", 1);
        assertEquals(1, map.size());
        map.put("two", 2);
        assertEquals(2, map.size());
    }

    @Test
    public void shouldReturnsEmpty() {
        Map<String, Integer> map = TTLCache.of(2, TimeUnit.MILLISECONDS, supplier);
        assertTrue(map.isEmpty());
        map.put("one", 1);
        assertFalse(map.isEmpty());
    }

    @Test
    public void shouldContains() throws InterruptedException {
        Map<String, Integer> map = TTLCache.of(2, TimeUnit.MILLISECONDS, supplier);
        map.put("one", 1);
        assertTrue(map.containsKey("one"));
        TimeUnit.MILLISECONDS.sleep(5L);
        assertFalse(map.containsKey("one"));
    }

    @Test
    public void shouldContainsValue() {
        Map<String, Integer> map = TTLCache.of(2, TimeUnit.MILLISECONDS, supplier);
        map.put("one", 1);
        assertTrue(map.containsValue(1));
        assertFalse(map.containsValue(2));
    }

    @Test
    public void shouldRemove() {
        Map<String, Integer> map = TTLCache.of(2, TimeUnit.MILLISECONDS, supplier);
        map.put("one", 1);
        assertTrue(map.containsKey("one"));
        map.remove("one");
        assertFalse(map.containsKey("one"));
    }

    @Test
    public void shouldClear() {
        Map<String, Integer> map = TTLCache.of(2, TimeUnit.MILLISECONDS, supplier);
        map.put("one", 1);
        map.put("two", 1);
        assertFalse(map.isEmpty());
        map.clear();
        assertTrue(map.isEmpty());
    }

    @Test
    public void shouldReturnErrorWhenPutAllIsNull() {
        assertThrows(NullPointerException.class, () -> {
            Map<String, Integer> map = TTLCache.of(2, TimeUnit.MILLISECONDS, supplier);
            map.putAll(null);
        });

    }

    @Test
    public void shouldPutAll() throws InterruptedException {
        Map<String, Integer> map = TTLCache.of(2, TimeUnit.MILLISECONDS, supplier);
        map.putAll(Collections.singletonMap("one", 1));
        assertTrue(map.containsKey("one"));
        TimeUnit.MILLISECONDS.sleep(5L);
        assertFalse(map.containsKey("one"));
    }

    @Test
    public void shouldReturnKeySet() throws InterruptedException {
        Map<String, Integer> map = TTLCache.of(2, TimeUnit.MILLISECONDS, supplier);
        assertTrue(map.keySet().isEmpty());
        map.put("one", 1);
        map.put("two", 2);
        assertFalse(map.keySet().isEmpty());
        assertEquals(2, map.keySet().size());
        TimeUnit.MILLISECONDS.sleep(5L);
        map.put("four", 4);
        assertFalse(map.keySet().isEmpty());
        assertEquals(1, map.keySet().size());
    }

    @Test
    public void shouldReturnValues() throws InterruptedException {
        Map<String, Integer> map = TTLCache.of(2, TimeUnit.MILLISECONDS, supplier);
        assertTrue(map.values().isEmpty());
        map.put("one", 1);
        map.put("two", 2);
        assertFalse(map.values().isEmpty());
        assertEquals(2, map.values().size());
        TimeUnit.MILLISECONDS.sleep(5L);
        map.put("four", 4);
        assertFalse(map.values().isEmpty());
        assertEquals(1, map.values().size());
    }

    @Test
    public void shouldReturnEntrySet() throws InterruptedException {
        Map<String, Integer> map = TTLCache.of(2, TimeUnit.MILLISECONDS, supplier);
        assertTrue(map.entrySet().isEmpty());
        map.put("one", 1);
        map.put("two", 2);
        assertFalse(map.entrySet().isEmpty());
        assertEquals(2, map.entrySet().size());
        TimeUnit.MILLISECONDS.sleep(5L);
        map.put("four", 4);
        assertFalse(map.entrySet().isEmpty());
        assertEquals(1, map.entrySet().size());
    }

    @Test
    public void shouldCleanWhenScheduledPass() throws InterruptedException {
        Map<String, Integer> map = TTLCache.of(2, TimeUnit.MILLISECONDS, supplier);
        map.put("one", 1);
        map.put("two", 2);
        TimeUnit.MILLISECONDS.sleep(8L);
        assertTrue(map.isEmpty());
    }

    @Test
    public void shouldRequestFromSupplierWhenCleanScheduledPass() throws InterruptedException {
        Function<String, Integer> supplier = Integer::valueOf;
        Map<String, Integer> map = TTLCache.of(2, TimeUnit.MILLISECONDS, supplier);
        map.put("1", 1000);
        map.put("2", 2);
        assertEquals(Integer.valueOf(1_000), map.get("1"));
        TimeUnit.MILLISECONDS.sleep(8L);
        assertEquals(Integer.valueOf(1), map.get("1"));
    }

}