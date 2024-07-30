package com.github.knakielski.client.requests;


import kong.unirest.HttpRequest;
import kong.unirest.HttpResponse;

import java.util.function.Function;

public interface SkyScannerRequest <T extends HttpRequest<T>> {

    HttpResponse<String> execute(Function<String, T> httpMethod);

}
