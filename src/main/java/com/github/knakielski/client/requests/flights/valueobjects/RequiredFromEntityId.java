package com.github.knakielski.client.requests.flights.valueobjects;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class RequiredFromEntityId {
    private final String fromEntityId;
}
