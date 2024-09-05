package com.github.knakielski.client.requests;

import java.util.function.Function;
import kong.unirest.HttpRequest;
import kong.unirest.HttpResponse;

public interface SkyScannerRequest<T extends HttpRequest<T>> {

    HttpResponse<String> execute(Function<String, T> httpMethod);

}
