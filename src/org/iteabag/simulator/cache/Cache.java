package org.iteabag.simulator.cache;
 /**
  * อินเทอร์เฟซหลักสำหรับทุกกลยุทธ์แคช
  */
 public interface Cache<K, V> {
     
     /**
      * ดึงค่าจากแคช
      * @return ค่าที่เก็บไว้ หรือ null ถ้าไม่พบ
      */
     V get(K key);
     
     /**
      * เก็บค่าลงในแคช
      */
     void put(K key, V value);
     
     /**
      * ตรวจสอบว่ามีค่านี้อยู่ในแคชหรือไม่
      */
     boolean contains(K key);
     
     /**
      * ล้างข้อมูลทั้งหมดในแคช
      */
     void clear();
     
     /**
      * ขนาดปัจจุบันของแคช
      */
     int size();
     
     /**
      * ความจุสูงสุดของแคช
      */
     int capacity();
     
     // --- สถิติ ---
     int getHitCount();
     int getMissCount();
     
     default double getHitRate() {
         int total = getHitCount() + getMissCount();
         return total == 0 ? 0.0 : (double) getHitCount() / total;
     }
 }