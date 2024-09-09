package com.github.knakielski;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Preconditions;
import com.google.common.io.Resources;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestResourceReader {

    @SneakyThrows
    public static String read(String filePath) {
        String resourcePathString = Resources.getResource(filePath).getPath();
        Path resourcePath = Paths.get(resourcePathString);
        return Files.readString(resourcePath);
    }

    @SneakyThrows
    public static String readPredefined(Integer no) {
        checkArgument(no >= 1 && no <= 3, "There is no such predefined input");
        return read(pathForInput(no));
    }

    private static String pathForInput(Integer numb) {
        String filePrefix = "jsonparser/input-for-processor-test";
        String filePostfix = ".json";
        return String.format("%s%d%s", filePrefix, numb, filePostfix);
    }

}
