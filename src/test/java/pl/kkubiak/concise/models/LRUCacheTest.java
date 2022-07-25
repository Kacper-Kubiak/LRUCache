package pl.kkubiak.concise.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LRUCacheTest {

    @Test
    void getCapacity_Test() {
        LRUCache cache = new LRUCache(5);
        assertEquals(5, cache.getCapacity());
    }

    @Test
    void changeCapacity_Test() {
        LRUCache cache = new LRUCache(1);
        cache.changeCapacity(2);
        assertEquals(2, cache.getCapacity());
    }

    @Test
    void cachePutAndGet_Test() {
        LRUCache cache = new LRUCache(1);
        cache.put("1", "Test 1");
        assertEquals("Test 1", cache.get("1"));
    }

    @Test
    void invalidate_Test() {
        LRUCache cache = new LRUCache(5);
        cache.put("1", "Test 1");
        cache.put("2", "Test 2");
        cache.put("5", "Test 5");
        assertEquals(3, cache.getCacheSize());
        cache.invalidate();
        assertEquals(0, cache.getCacheSize());
    }

    @Test
    void changeCacheCapacityUp_Test() {
        LRUCache cache = new LRUCache(1);
        cache.put("1", "Test 1");
        cache.put("2", "Test 2");
        assertEquals("Test 2", cache.get("2"));
        assertEquals("-1", cache.get("1"));
        cache.changeCapacity(2);
        cache.put("3", "Test 3");
        assertEquals("Test 2", cache.get("2"));
        assertEquals("Test 3", cache.get("3"));
    }

    @Test
    void changeCacheCapacityDown_Test() {
        LRUCache cache = new LRUCache(6);
        cache.put("1", "Test 1");
        cache.put("2", "Test 2");
        cache.put("3", "Test 3");
        cache.put("4", "Test 4");
        cache.put("5", "Test 5");
        cache.put("6", "Test 6");
        cache.changeCapacity(3);
        assertEquals("-1", cache.get("1"));
        assertEquals("-1", cache.get("2"));
        assertEquals("-1", cache.get("3"));
        assertEquals("Test 4", cache.get("4"));
        assertEquals("Test 5", cache.get("5"));
        assertEquals("Test 6", cache.get("6"));
    }


    @Test
    void isCacheSizeCorrect_Test() {
        LRUCache cache = new LRUCache(5);
        cache.put("1", "Test 1");
        cache.put("2", "Test 2");
        cache.put("5", "Test 5");
        cache.put("9", "Test 9");
        cache.put("10", "Test 10");
        cache.put("3", "Test 3");
        cache.put("4", "Test 4");
        cache.put("6", "Test 6");
        cache.put("7", "Test 7");
        cache.put("8", "Test 8");
        assertEquals(5, cache.getCacheSize());
    }

    @Test
    void isCacheDataCorrect_Test() {
        LRUCache cache = new LRUCache(2);
        cache.put("1", "Test 1");
        cache.put("2", "Test 2");
        cache.put("5", "Test 5");
        cache.put("9", "Test 9");
        assertEquals("Test 5", cache.get("5"));
        assertEquals("Test 9", cache.get("9"));
    }

    @Test
    void putDuplicateKey_Test() {
        LRUCache cache = new LRUCache(3);
        cache.put("1", "Test 1");
        cache.put("2", "Test 2");
        cache.put("1", "Test 1");
        cache.put("3", "Test 3");
        assertEquals("Test 1", cache.get("1"));
        assertEquals("Test 2", cache.get("2"));
        assertEquals("Test 3", cache.get("3"));
    }

    @Test
    void putDuplicateKey2_Test() {
        LRUCache cache = new LRUCache(3);
        cache.put("1", "Test 1");
        cache.put("2", "Test 2");
        cache.put("3", "Test 3");
        cache.put("1", "Test 1");
        cache.put("4", "Test 4");
        assertEquals("Test 3", cache.get("3"));
        assertEquals("Test 1", cache.get("1"));
        assertEquals("Test 4", cache.get("4"));
    }
}