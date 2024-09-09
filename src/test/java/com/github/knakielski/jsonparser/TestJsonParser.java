package com.github.knakielski.jsonparser;

import static com.github.knakielski.TestResourceReader.readPredefined;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

final class TestJsonParser {

    private static JsonParser jsonParser;

    @BeforeAll
    static void setup() {
        jsonParser = JsonParser.parse(readPredefined(1));
    }

    @Test
    void read() {
        //given

        //when
        Integer result = jsonParser.read(Integer.class, "$.data.filterStats.carriers[?(@.name == 'LOT')].id");

        //then
        assertThat(result).isEqualTo(-32093);
    }

}
