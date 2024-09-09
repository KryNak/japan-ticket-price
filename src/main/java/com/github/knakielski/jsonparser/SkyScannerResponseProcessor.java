package com.github.knakielski.jsonparser;

import java.math.BigDecimal;

public class SkyScannerResponseProcessor {

    private SkyScannerResponseProcessor() {}

    public static SkyScannerResponseProcessor create() {
        return new SkyScannerResponseProcessor();
    }

    public BigDecimal extractFlightPrice(String requestResponse) {
        JsonParser parser = JsonParser.parse(requestResponse);
        Integer lotId = parser.read(Integer.class, "$.data.filterStats.carriers[?(@.name == 'LOT')].id");
        Double price = parser.read(Double.class, "$.data.itineraries[?(@.id =~ /.*%d.*/i)].price.raw", lotId);
        return BigDecimal.valueOf(price);
    }

}
