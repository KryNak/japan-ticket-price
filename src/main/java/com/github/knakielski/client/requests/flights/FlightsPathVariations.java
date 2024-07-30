package com.github.knakielski.client.requests.flights;

import com.github.knakielski.client.requests.PathVariation;

public enum FlightsPathVariations implements PathVariation {
    SEARCH_ROUND_TRIP("search-roundtrip");

    private final String route;

    FlightsPathVariations(String route) {
        this.route = route;
    }

    @Override
    public String path() {
        return route;
    }

}
