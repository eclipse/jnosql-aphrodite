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

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.util.Collections.synchronizedMap;
import static java.util.Collections.unmodifiableCollection;
import static java.util.Collections.unmodifiableSet;

class TTLCache<K, V> implements Map<K, V>, Runnable {


    private static final ScheduledExecutorService SCHEDULED_THREAD_POOL = Executors.newScheduledThreadPool(1);


    private final Map<K, V> store = new ConcurrentHashMap<>();
    private final Map<K, Long> timestamps = new ConcurrentHashMap<>();
    private final Map<K, K> mutex = synchronizedMap(new WeakHashMap<>());
    private final long ttl;
    private final Function<K, V> supplier;

    TTLCache(long value, TimeUnit unit, Function<K, V> supplier) {
        this.ttl = unit.toNanos(value);
        this.supplier = supplier;
        SCHEDULED_THREAD_POOL.schedule(this, value * 2, unit);
    }


    @Override
    public V get(Object key) {
        V value = this.store.get(key);
        if (Objects.isNull(value)) {
            K synchronizedKey = mutex.computeIfAbsent((K) key, (a) -> (K) key);
            synchronized (synchronizedKey) {
                value = supplier.apply(synchronizedKey);
                put(synchronizedKey, value);
            }
        }
        return value;
    }

    @Override
    public V put(K key, V value) {
        timestamps.put(key, System.nanoTime());
        return store.put(key, value);
    }

    @Override
    public int size() {
        return store.size();
    }

    @Override
    public boolean isEmpty() {
        return store.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        boolean containsKey = store.containsKey(key);
        return containsKey ? !checkExpired(key) : containsKey;
    }

    @Override
    public boolean containsValue(Object value) {
        return store.containsValue(value);
    }

    @Override
    public V remove(Object key) {
        timestamps.remove(key);
        return store.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        Objects.requireNonNull(map, "map is required");
        map.entrySet().forEach(this::put);
    }

    @Override
    public void clear() {
        synchronized (this) {
            timestamps.clear();
            store.clear();
        }

    }

    @Override
    public Set<K> keySet() {
        return unmodifiableSet(store.keySet());
    }

    @Override
    public Collection<V> values() {
        return unmodifiableCollection(store.values());
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return unmodifiableSet(store.entrySet());
    }

    @Override
    public void run() {
        if (!this.isEmpty()) {
            clearExpired();
        }
    }



    private void clearExpired() {
        store.keySet().stream().forEach(this::checkExpired);
    }

    private void put(Map.Entry<? extends K, ? extends V> entry) {
        this.put(entry.getKey(), entry.getValue());
    }

    private boolean checkExpired(Object key) {
        if (isObsolete(key)) {
            remove(key);
            return true;
        }
        return false;
    }

    private boolean isObsolete(Object key) {
        return (System.nanoTime() - timestamps.get(key)) > this.ttl;
    }

    static <K, V> Map<K, V> of(long value, TimeUnit timeUnit, Function<K, V> supplier) {
        Objects.requireNonNull(timeUnit, "timeUnit is required");
        Objects.requireNonNull(supplier, "supplier is required");
        if (value <= 0) {
            throw new IllegalArgumentException("The value to TTL must be greater than zero");
        }
        return new TTLCache<>(value, timeUnit, supplier);
    }


}
