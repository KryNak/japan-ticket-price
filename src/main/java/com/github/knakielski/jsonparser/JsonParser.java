package com.github.knakielski.jsonparser;

import static java.lang.String.format;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.FormatMethod;
import com.google.errorprone.annotations.FormatString;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JsonProvider;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonParser {
    private static final JsonProvider jsonProvider = Configuration.defaultConfiguration().jsonProvider();
    private final Object document;

    public static JsonParser parse(String json) {
        return new JsonParser(jsonProvider.parse(json));
    }

    @FormatMethod public <T> T read(@FormatString String jsonPath, Object... args) {
        return single(JsonPath.read(document, format(jsonPath, args)));
    }

    private static <T> T single(List<T> results) {
        Preconditions.checkArgument(results.size() == 1, "Result should contain exact one element");
        return results.get(0);
    }
}
