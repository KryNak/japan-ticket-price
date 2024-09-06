package com.github.knakielski.jsonparser;

import static com.github.knakielski.TestResourceReader.readPredefined;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

import com.github.knakielski.client.factory.ClientType;
import com.github.knakielski.client.factory.SkyScannerClient;
import com.github.knakielski.client.factory.SkyScannerClientFactory;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestSkyScannerResponseProcessor {

    private final SkyScannerResponseProcessor processorMock = spy(SkyScannerResponseProcessor.create());

    @Test
    public void test_extract_flight_price_1() {
        //given
        SkyScannerClient clientMock = SkyScannerClientFactory.create(ClientType.MOCK);
        String json = clientMock.getRoundTrip();

        //when
        BigDecimal result = processorMock.extractFlightPrice(json);

        //then
        assertThat(result).isEqualByComparingTo(BigDecimal.valueOf(4416_99, 2));
    }

    @ParameterizedTest
    @MethodSource("provideInputsForExtractFlightPriceTest")
    public void test_extract_flight_price_2(String input, BigDecimal expected) {
        //given

        //when
        BigDecimal result = processorMock.extractFlightPrice(input);

        //then
        assertThat(result).isEqualByComparingTo(expected);
    }

    private static Stream<Arguments> provideInputsForExtractFlightPriceTest() {
        return Stream.of(
                Arguments.of(readPredefined(1), BigDecimal.valueOf(4416_99, 2)),
                Arguments.of(readPredefined(2), BigDecimal.valueOf(78_78, 2)),
                Arguments.of(readPredefined(3), BigDecimal.valueOf(191919_45, 2))
        );
    }
}
