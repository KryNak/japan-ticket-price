package com.github.knakielski.client.factory;

import com.github.knakielski.client.executors.SkyScannerRequestExecutor;
import com.github.knakielski.client.requests.flights.RoundTripRequest;
import com.github.knakielski.client.requests.flights.valueobjects.RequiredFromEntityId;
import io.avaje.config.Config;
import kong.unirest.Unirest;

class SkyScannerClientImpl implements SkyScannerClient {

    private final SkyScannerRequestExecutor requestExecutor = new SkyScannerRequestExecutor();

    static {
        Unirest.config()
               .defaultBaseUrl("https://sky-scanner3.p.rapidapi.com/")
               .addDefaultHeader("x-rapidapi-host", Config.get("x-rapidapi-host"))
               .addDefaultHeader("x-rapidapi-key", Config.get("x-rapidapi-key"));
    }

    @Override
    public String getRoundTrip() {
        RoundTripRequest roundTripRequest = new RoundTripRequest.Builder(RequiredFromEntityId.of("WAW"))
                .toEntityId("NRT")
                .departDate("2024-09-16")
                .returnDate("2024-09-30")
                .market("PL")
                .locale("pl-PL")
                .currency("PLN")
                .stops("direct")
                .adults("1")
                .cabinClass("economy")
                .build();

        return requestExecutor.executeGetMethod(roundTripRequest);
    }

}
