package com.github.knakielski.client.requests.flights;

import com.github.knakielski.client.requests.SkyScannerGetRequest;
import com.github.knakielski.client.requests.flights.valueobjects.RequiredFromEntityId;
import com.github.knakielski.utils.FieldsReader;
import kong.unirest.GetRequest;
import kong.unirest.HttpResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.function.Function;

import static com.github.knakielski.client.requests.flights.FlightsPathVariations.SEARCH_ROUND_TRIP;

@Setter(AccessLevel.PRIVATE)
@Accessors(fluent = true)
@SuppressWarnings("unused")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RoundTripRequest implements SkyScannerGetRequest {

    private static final String REQUEST_PATH = new FlightsPath().getVariation(SEARCH_ROUND_TRIP);

    private final String fromEntityId;
    private String toEntityId;
    private String wholeMonthDepart;
    private String wholeMonthReturn;
    private String departDate;
    private String returnDate;
    private String market;
    private String locale;
    private String currency;
    private String stops;
    private String adults;
    private String infants;
    private String cabinClass;

    @Override
    public HttpResponse<String> execute(Function<String, GetRequest> httpMethod) {
        GetRequest getRequest = httpMethod.apply(REQUEST_PATH);
        GetRequest enrichedWithQueryParamsGetRequest = FieldsReader.appendToRequestAsQueryParams(getRequest, this);
        return sendRequest(enrichedWithQueryParamsGetRequest);
    }

    private static HttpResponse<String> sendRequest(GetRequest request) {
        return request.asString();
    }

    @Setter
    @Accessors(fluent = true)
    public static class Builder {
        private final String fromEntityId;
        private String toEntityId;
        private String wholeMonthDepart;
        private String wholeMonthReturn;
        private String departDate;
        private String returnDate;
        private String market;
        private String locale;
        private String currency;
        private String stops;
        private String adults;
        private String infants;
        private String cabinClass;

        public Builder(RequiredFromEntityId fromEntityId) {
            this.fromEntityId = fromEntityId.getFromEntityId();
        }

        public RoundTripRequest build() {
            return new RoundTripRequest(fromEntityId)
                    .toEntityId(toEntityId)
                    .wholeMonthDepart(wholeMonthDepart)
                    .wholeMonthReturn(wholeMonthReturn)
                    .departDate(departDate)
                    .returnDate(returnDate)
                    .market(market)
                    .locale(locale)
                    .currency(currency)
                    .stops(stops)
                    .adults(adults)
                    .infants(infants)
                    .cabinClass(cabinClass);
        }

    }

}
