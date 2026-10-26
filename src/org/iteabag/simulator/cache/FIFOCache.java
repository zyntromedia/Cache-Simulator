package org.iteabag.simulator.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * First-In-First-Out — ขจัดรายการที่เข้ามาก่อนที่สุดออกก่อน
 */
public class FIFOCache<K, V> extends AbstractCache<K, V> {
    
    private final Map<K, V> data;
    private final Map<K, Long> insertionTime;
    
    public FIFOCache(int capacity) {
        super(capacity);
        this.data = new LinkedHashMap<>(capacity);
        this.insertionTime = new LinkedHashMap<>(capacity);
    }
    
    @Override
    public V get(K key) {
        if (data.containsKey(key)) {
            recordHit();
            return data.get(key);
        } else {
            recordMiss();
            return null;
        }
    }
    
    @Override
    public void put(K key, V value) {
        if (size() >= capacity && !data.containsKey(key)) {
            evictOldest();
        }
        data.put(key, value);
        insertionTime.put(key, System.nanoTime());
    }
    
    private void evictOldest() {
        K oldestKey = insertionTime.entrySet().stream()
            .min(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
        
        if (oldestKey != null) {
            data.remove(oldestKey);
            insertionTime.remove(oldestKey);
        }
    }
    
    @Override
    public boolean contains(K key) {
        return data.containsKey(key);
    }
    
    @Override
    public void clear() {
        data.clear();
        insertionTime.clear();
        hitCount = 0;
        missCount = 0;
    }
    
    @Override
    public int size() {
        return data.size();
    }
}
