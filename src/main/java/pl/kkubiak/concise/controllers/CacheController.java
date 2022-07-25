package pl.kkubiak.concise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kkubiak.concise.config.Router;
import pl.kkubiak.concise.requests.Cache;
import pl.kkubiak.concise.requests.CapacityRequest;
import pl.kkubiak.concise.services.LRUCacheService;

@RestController
public class CacheController {

    private final LRUCacheService lruCacheService;

    @Autowired
    public CacheController(LRUCacheService lruCacheService) {
        this.lruCacheService = lruCacheService;
    }

    //Get from cache
    @GetMapping(value = Router.CACHE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public Cache getValueFromCache(@PathVariable String key) {
        return new Cache(key, lruCacheService.getValueByKey(key));
    }

    @PostMapping(Router.CACHES_URL)
    public void putValueToCache(@RequestBody @Validated Cache cache) {
        lruCacheService.putValue(cache.getKey(), cache.getValue());
    }

    //Invalidate cache
    @PostMapping(Router.INVALIDATE_CACHE_URL)
    public void invalidateCache() {
        lruCacheService.invalidateCache();
    }

    //Change capacity
    @PutMapping(value = Router.CAPACITY_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCapacity(@RequestBody CapacityRequest capacityRequest) {
        lruCacheService.updateCapacity(capacityRequest.getCapacity());
    }
}
