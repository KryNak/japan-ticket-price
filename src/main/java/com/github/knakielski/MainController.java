package com.github.knakielski;

import com.github.knakielski.client.factory.SkyScannerClient;
import com.github.knakielski.jsonparser.SkyScannerResponseProcessor;
import io.javalin.http.Handler;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainController {

    private final SkyScannerClient client;
    private final SkyScannerResponseProcessor processor;

    public MainController(SkyScannerClient client, SkyScannerResponseProcessor processor) {
        this.client = client;
        this.processor = processor;
    }

    public Handler handleGet() {
        return ctx -> ctx.json(flightPrice());
    }

    private BigDecimal flightPrice() {
        String json = client.getRoundTrip();
        BigDecimal originalPrice = processor.extractFlightPrice(json);
        return takeAccountOfCommissions(originalPrice).setScale(2, RoundingMode.CEILING);
    }

    private static BigDecimal takeAccountOfCommissions(BigDecimal originalPrice) {
        BigDecimal siteVisitCommission = BigDecimal.valueOf(18272, 2);
        BigDecimal economyStandardCommission = BigDecimal.valueOf(40000, 2);
        return originalPrice.add(siteVisitCommission)
                            .add(economyStandardCommission);
    }

}
