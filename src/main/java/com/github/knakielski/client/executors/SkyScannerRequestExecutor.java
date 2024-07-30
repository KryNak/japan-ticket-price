package com.github.knakielski.client.executors;

import com.github.knakielski.client.requests.SkyScannerGetRequest;
import kong.unirest.Unirest;

public class SkyScannerRequestExecutor {

    public String executeGetMethod(SkyScannerGetRequest request) {
        return request.execute(Unirest::get)
                      .getBody();
    }

}
