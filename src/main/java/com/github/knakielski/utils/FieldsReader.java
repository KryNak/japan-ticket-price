package com.github.knakielski.utils;

import com.github.knakielski.client.requests.flights.RoundTripRequest;
import kong.unirest.HttpRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import static com.plugatar.jkscope.JKScope.let;
import static org.zalando.fauxpas.FauxPas.throwingBiFunction;
import static org.zalando.fauxpas.FauxPas.throwingPredicate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FieldsReader {

    public static <T1, T2 extends HttpRequest<T2>> T2 appendToRequestAsQueryParams(T2 request, T1 object) {
        return Arrays.stream(FieldUtils.getAllFields(RoundTripRequest.class))
                     .map(it -> let(it, it2 -> it2.setAccessible(true)))
                     .filter(throwingPredicate(it -> Objects.nonNull(it.get(object))))
                     .reduce(request, createAccumulator(object), combiner());
    }

    @NotNull
    private static <T1, T2 extends HttpRequest<T2>> BiFunction<T2, Field, T2> createAccumulator(T1 object) {
        return throwingBiFunction((req, field) -> req.queryString(field.getName(), field.get(object)));
    }

    @NotNull
    private static <T1 extends HttpRequest<T1>> BinaryOperator<T1> combiner() {
        return (a, b) -> b;
    }

}
