package pl.kkubiak.concise.services;

public interface LRUCacheService {
    String getValueByKey(String key);
    void putValue(String key, String value);
    void updateCapacity(int capacity);
    void invalidateCache();
}
