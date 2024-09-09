package com.github.knakielski.client.factory;

import com.google.common.io.Resources;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.SneakyThrows;

class SkyScannerClientMock implements SkyScannerClient {
    @Override
    @SneakyThrows
    public String getRoundTrip() {
        return Files.readString(Paths.get(Resources.getResource("response-mock.json").getPath()));
    }
}
