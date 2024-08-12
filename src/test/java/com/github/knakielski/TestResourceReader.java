package com.github.knakielski;

import com.google.common.io.Resources;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestResourceReader {

    @SneakyThrows
    public static String read(String filePath) {
        String resourcePathString = Resources.getResource(filePath).getPath();
        Path resourcePath = Paths.get(resourcePathString);
        return Files.readString(resourcePath);
    }

}
