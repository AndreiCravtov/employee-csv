package com.sparta.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class TrackedHashMap<K, V> extends HashMap<K, V> {
    private boolean mapChanged = false;


    /**
     * Constructs an empty <tt>HashMap</tt> with the default initial capacity
     * (16) and the default load factor (0.75).
     */
    public TrackedHashMap() {
        super();
    }

    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and the default load factor (0.75).
     *
     * @param  initialCapacity the initial capacity.
     * @throws IllegalArgumentException if the initial capacity is negative.
     */
    public TrackedHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and load factor.
     *
     * @param  initialCapacity the initial capacity
     * @param  loadFactor      the load factor
     * @throws IllegalArgumentException if the initial capacity is negative
     * or the load factor is nonpositive
     */
    public TrackedHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    /**
     * Constructs a new <tt>HashMap</tt> with the same mappings as the
     * specified <tt>Map</tt>.  The <tt>HashMap</tt> is created with
     * default load factor (0.75) and an initial capacity sufficient to
     * hold the mappings in the specified <tt>Map</tt>.
     *
     * @param   m the map whose mappings are to be placed in this map
     * @throws  NullPointerException if the specified map is null
     */
    public TrackedHashMap(Map<? extends K,? extends V> m) {
        super(m);
    }

    /**
     * Indicates when a successful update has taken place through
     * @return - returns true when HashMap has changed
     */
    public boolean mapHasChanged() {
        return mapChanged;
    }

    /**
     * Returns false when HashMap is unchaged
     */
    public boolean setMapUnchanged() {
        mapChanged = false;
        return false;
    }

    /**
     * Clears all entries from HashMap
     */
    @Override
    public void clear() {
        mapChanged = true;
        super.clear();
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V,? super V,? extends V> remappingFunction) {
        mapChanged = true;
        return super.merge(key, value, remappingFunction);
    }

    @Override
    public V put(K key, V value) {
        mapChanged = true;
        return super.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K,? extends V> m) {
        mapChanged = true;
        super.putAll(m);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        mapChanged = true;
        return super.putIfAbsent(key, value);
    }

    @Override
    public V remove(Object key) {
        mapChanged = true;
        return super.remove(key);
    }

    @Override
    public boolean remove(Object key, Object value) {
        mapChanged = true;
        return super.remove(key, value);
    }

    @Override
    public V replace(K key, V value) {
        mapChanged = true;
        return super.replace(key, value);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        mapChanged = true;
        return super.replace(key, oldValue, newValue);
    }

    @Override
    public void replaceAll(BiFunction<? super K,? super V,? extends V> function) {
        mapChanged = true;
        super.replaceAll(function);
    }
}
