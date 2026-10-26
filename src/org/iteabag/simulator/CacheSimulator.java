package org.iteabag.simulator;

import org.iteabag.simulator.cache.Cache;
import org.iteabag.simulator.cache.LRUCache;

import java.util.List;

/**
 * ตัวจำลองหลัก — รันชุดทดสอบและแสดงผล
 */
public class CacheSimulator {
    
    private final Cache<Integer, Object> cache;
    
    public CacheSimulator(Cache<Integer, Object> cache) {
        this.cache = cache;
    }
    
    public void runSimulation(List<Integer> accessPattern) {
        System.out.println("═══════════════════════════════════════");
        System.out.printf("📊 เริ่มการจำลอง — %s (ความจุ: %d)%n",
            cache.getClass().getSimpleName(), cache.capacity());
        System.out.println("═══════════════════════════════════════");
        
        for (Integer key : accessPattern) {
            if (!cache.contains(key)) {
                cache.put(key, "value-" + key);
            }
            cache.get(key);
        }
        
        printResults();
    }
    
    private void printResults() {
        int total = cache.getHitCount() + cache.getMissCount();
        System.out.printf("📋 คำขอทั้งหมด:   %d%n", total);
        System.out.printf("✅ Cache Hit:    %d (%.1f%%)%n",
            cache.getHitCount(), cache.getHitRate() * 100);
        System.out.printf("❌ Cache Miss:   %d (%.1f%%)%n",
            cache.getMissCount(), (1 - cache.getHitRate()) * 100);
        System.out.printf("📦 ขนาดปัจจุบัน:  %d/%d%n",
            cache.size(), cache.capacity());
        System.out.println("═══════════════════════════════════════\n");
    }
    
    public static void main(String[] args) {
        // ตัวอย่างรูปแบบการเข้าถึงข้อมูล
        List<Integer> pattern = List.of(1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5, 1, 2, 1);
        
        // ทดสอบกับ LRU Cache
        Cache<Integer, Object> lru = new LRUCache<>(3);
        new CacheSimulator(lru).runSimulation(pattern);
    }
}
