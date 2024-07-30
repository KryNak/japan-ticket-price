package com.github.knakielski.client.requests;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractApiPath <T extends PathVariation> implements ApiPath<T> {

    private final String root;

    @Override
    public String getVariation(T variation) {
        return String.join("/", root, variation.path());
    }
}
