package com.github.knakielski;

import com.github.knakielski.client.factory.ClientType;
import com.github.knakielski.client.factory.SkyScannerClient;
import com.github.knakielski.client.factory.SkyScannerClientFactory;
import com.github.knakielski.jsonparser.SkyScannerResponseProcessor;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AppServer {

    private static final SkyScannerClient client = SkyScannerClientFactory.create(ClientType.CACHE_CLIENT);
    private static final SkyScannerResponseProcessor processor = SkyScannerResponseProcessor.create();

    public static void runJavalin() {
        Javalin.create()
               .get("/fetch-price", handleGet())
               .get("/healthcheck", ctx -> ctx.json("OK"))
               .start(8080);
    }

    private static Handler handleGet() {
        return ctx -> ctx.json(flightPrice());
    }

    private static BigDecimal flightPrice() {
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
