package com.github.knakielski.client.factory;

import com.google.common.io.Resources;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;

class SkyScannerClientMock implements SkyScannerClient {
    @SneakyThrows
    public String getRoundTrip() {
        return Files.readString(Paths.get(Resources.getResource("response-mock.json").getPath()));
    }
}
