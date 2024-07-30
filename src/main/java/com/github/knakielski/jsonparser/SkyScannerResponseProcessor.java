package com.github.knakielski.jsonparser;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(staticName = "create")
public class SkyScannerResponseProcessor {

    public BigDecimal extractFlightPrice(String requestResponse) {
        JsonParser parser = JsonParser.parse(requestResponse);
        Integer lotId = parser.read("$.data.filterStats.carriers[?(@.name == 'LOT')].id");
        Double price = parser.read("$.data.itineraries[?(@.id =~ /.*%d.*/i)].price.raw", lotId);
        return BigDecimal.valueOf(price);
    }

}
