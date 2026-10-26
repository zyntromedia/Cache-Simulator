package org.iteabag.simulator.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Least Recently Used — ขจัดรายการที่ไม่ได้ใช้นานที่สุดออกก่อน
 */
public class LRUCache<K, V> extends AbstractCache<K, V> {
    
    private final Map<K, V> data;
    
    public LRUCache(int capacity) {
        super(capacity);
        // true = จัดเรียงตามการเข้าถึง (ล่าสุดอยู่ท้าย)
        this.data = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
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
        data.put(key, value);
    }
    
    @Override
    public boolean contains(K key) {
        return data.containsKey(key);
    }
    
    @Override
    public void clear() {
        data.clear();
        hitCount = 0;
        missCount = 0;
    }
    
    @Override
    public int size() {
        return data.size();
    }
}
