package org.iteabag.simulator.cache;

/**
 * คลาสฐานที่มีตรรกะสถิติและความจุไว้แล้ว
 */
public abstract class AbstractCache<K, V> implements Cache<K, V> {
    
    protected final int capacity;
    protected int hitCount = 0;
    protected int missCount = 0;
    
    public AbstractCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
    }
    
    @Override
    public int capacity() {
        return capacity;
    }
    
    @Override
    public int getHitCount() {
        return hitCount;
    }
    
    @Override
    public int getMissCount() {
        return missCount;
    }
    
    protected void recordHit() {
        hitCount++;
    }
    
    protected void recordMiss() {
        missCount++;
    }
    
    @Override
    public String toString() {
        return String.format("%s{capacity=%d, hit=%.1f%%}",
            getClass().getSimpleName(),
            capacity,
            getHitRate() * 100);
    }
}
