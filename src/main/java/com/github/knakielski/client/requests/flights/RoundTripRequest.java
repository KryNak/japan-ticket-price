package com.github.knakielski.client.requests.flights;

import static com.github.knakielski.client.requests.flights.FlightsPathVariations.SEARCH_ROUND_TRIP;

import com.github.knakielski.client.requests.SkyScannerGetRequest;
import com.github.knakielski.client.requests.flights.valueobjects.RequiredFromEntityId;
import com.github.knakielski.utils.FieldsReader;
import java.util.function.Function;
import kong.unirest.GetRequest;
import kong.unirest.HttpResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jspecify.nullness.Nullable;

@Setter(AccessLevel.PRIVATE)
@Accessors(fluent = true)
@SuppressWarnings("unused")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RoundTripRequest implements SkyScannerGetRequest {

    private static final String REQUEST_PATH = new FlightsPath().getVariation(SEARCH_ROUND_TRIP);

    private final String fromEntityId;
    @Nullable private String toEntityId;
    @Nullable private String wholeMonthDepart;
    @Nullable private String wholeMonthReturn;
    @Nullable private String departDate;
    @Nullable private String returnDate;
    @Nullable private String market;
    @Nullable private String locale;
    @Nullable private String currency;
    @Nullable private String stops;
    @Nullable private String adults;
    @Nullable private String infants;
    @Nullable private String cabinClass;

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
        @Nullable private String toEntityId;
        @Nullable private String wholeMonthDepart;
        @Nullable private String wholeMonthReturn;
        @Nullable private String departDate;
        @Nullable private String returnDate;
        @Nullable private String market;
        @Nullable private String locale;
        @Nullable private String currency;
        @Nullable private String stops;
        @Nullable private String adults;
        @Nullable private String infants;
        @Nullable private String cabinClass;

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
