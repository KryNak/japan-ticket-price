package com.github.knakielski;

import com.github.knakielski.client.factory.SkyScannerClient;
import com.github.knakielski.jsonparser.SkyScannerResponseProcessor;
import io.javalin.http.Handler;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainController {

    private final SkyScannerClient client;
    private final SkyScannerResponseProcessor processor;
    private final CommissionService commissionService;

    public MainController(SkyScannerClient client, SkyScannerResponseProcessor processor,
                          CommissionService commissionService) {
        this.client = client;
        this.processor = processor;
        this.commissionService = commissionService;
    }

    public Handler handleGet() {
        return ctx -> ctx.json(flightPrice());
    }

    private BigDecimal flightPrice() {
        String json = client.getRoundTrip();
        BigDecimal originalPrice = processor.extractFlightPrice(json);
        return commissionService.takeAccountOfCommissions(originalPrice)
                                .setScale(2, RoundingMode.CEILING);
    }


}
