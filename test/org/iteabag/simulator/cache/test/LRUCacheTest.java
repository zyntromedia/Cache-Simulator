package org.iteabag.simulator.cache.test;

import org.iteabag.simulator.cache.LRUCache;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LRUCacheTest {
    
    private LRUCache<Integer, String> cache;
    
    @Before
    public void setUp() {
        cache = new LRUCache<>(2);
    }
    
    @Test
    public void testPutAndGet() {
        cache.put(1, "one");
        assertEquals("one", cache.get(1));
    }
    
    @Test
    public void testMissReturnsNull() {
        assertNull(cache.get(99));
    }
    
    @Test
    public void testEviction() {
        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three"); // ควรขจัด 1 ออก
        
        assertNull(cache.get(1));
        assertNotNull(cache.get(2));
        assertNotNull(cache.get(3));
    }
    
    @Test
    public void testHitStatistics() {
        cache.put(1, "one");
        cache.get(1); // Hit
        cache.get(2); // Miss
        
        assertEquals(1, cache.getHitCount());
        assertEquals(1, cache.getMissCount());
        assertEquals(0.5, cache.getHitRate(), 0.01);
    }
}
