package com.github.knakielski.client.factory;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.knakielski.cache.CacheManager;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class SkyScannerClientCacheProxy implements SkyScannerClient {

    private final SkyScannerClient skyScannerClient;
    private static final Cache<String, String> cache = CacheManager.getCache();

    @Override
    public String getRoundTrip() {
        return cache.get(CacheManager.GET_ROUND_TRIP_KEY, k -> skyScannerClient.getRoundTrip());
    }

}
