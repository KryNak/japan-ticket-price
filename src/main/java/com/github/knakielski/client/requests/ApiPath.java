package com.github.knakielski.client.requests;

public interface ApiPath <T extends PathVariation> {

    String getVariation(T variation);

}
