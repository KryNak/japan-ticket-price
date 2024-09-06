package com.github.knakielski.utils;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenericUtils {

    public static <T> List<T> castToGenericList(Object toCast, Class<T> genericType) {
        if (!(toCast instanceof List<?> rawList)) {
            throw new ClassCastException();
        }
        if (!areArgumentsMatchingType(rawList, genericType)) {
            throw new ClassCastException();
        }

        return rawList.stream()
                      .map(genericType::cast)
                      .toList();
    }

    private static <T> boolean areArgumentsMatchingType(List<?> arguments, Class<T> expectedType) {
        return arguments.stream().allMatch(expectedType::isInstance);
    }

}
