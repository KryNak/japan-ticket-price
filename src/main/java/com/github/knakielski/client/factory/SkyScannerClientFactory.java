package com.github.knakielski.client.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SkyScannerClientFactory {

    public static SkyScannerClient create(ClientType clientType) {
        SkyScannerClient skyScannerClient = new SkyScannerClientImpl();
        SkyScannerClient skyScannerClientCacheProxy = new SkyScannerClientCacheProxy(skyScannerClient);

        return switch (clientType) {
            case MOCK -> new SkyScannerClientMock();
            case NO_CACHE_CLIENT -> skyScannerClient;
            case CACHE_CLIENT -> skyScannerClientCacheProxy;
        };
    }

}
