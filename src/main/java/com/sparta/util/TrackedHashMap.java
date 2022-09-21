package com.sparta.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class TrackedHashMap<K, V> extends HashMap<K, V> {
    private boolean mapChanged = false;

    public TrackedHashMap() {
        super();
    }

    public boolean mapHasChanged() {
        return mapChanged;
    }

    public void setMapUnchanged() {
        mapChanged = false;
    }

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
