package pl.kkubiak.concise.config;

public class Router {
    private Router() {
        throw new IllegalStateException("Utility class");
    }
    public static final String API_PATH = "/api/v1";
    public static final String CACHES_URL = API_PATH+"/cache";
    public static final String CACHE_URL = API_PATH+"/cache/{key}";
    public static final String CAPACITY_URL = API_PATH+"/cache/capacity";
    public static final String INVALIDATE_CACHE_URL = API_PATH+"/cache/invalidate";
}
