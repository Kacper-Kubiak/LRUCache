package pl.kkubiak.concise.services;

import org.springframework.stereotype.Service;
import pl.kkubiak.concise.models.LRUCache;

@Service
public class LRUCacheServiceBean implements LRUCacheService {

    private final LRUCache lruCache;

    public LRUCacheServiceBean() {
        this.lruCache = new LRUCache();
    }

    @Override
    public String getValueByKey(String key) {
        return lruCache.get(key);
    }

    @Override
    public void putValue(String key, String value) {
        lruCache.put(key, value);
    }

    @Override
    public void updateCapacity(int capacity) {
        lruCache.changeCapacity(capacity);
    }

    @Override
    public void invalidateCache() {
        lruCache.invalidate();
    }
}
