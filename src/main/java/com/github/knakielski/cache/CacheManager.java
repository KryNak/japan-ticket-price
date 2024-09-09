package com.github.knakielski.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheManager {

    public static final String GET_ROUND_TRIP_KEY = "getRoundTripKey";
    private static CacheManager instance;

    static {
        instance = new CacheManager();
    }

    private final Cache<String, String> cache;

    public CacheManager() {
        this.cache = Caffeine.newBuilder()
                             .maximumSize(100)
                             .expireAfterWrite(25, TimeUnit.HOURS)
                             .build();
    }

    public static CacheManager getInstance() {
        if (instance != null) {
            instance = new CacheManager();
        }

        return instance;
    }

    public static Cache<String, String> getCache() {
        return getInstance().cache;
    }

    public void invalidCache() {
        CacheManager.getCache().invalidate(CacheManager.GET_ROUND_TRIP_KEY);
        log.info("Cache has been cleaned");
    }

}
