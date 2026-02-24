package com.flightsearch.flight_service.application.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

public record FlightCreateRequestDto(UUID departureAirportId,
                                     UUID arrivalAirportId,
                                     LocalDateTime departureDateTime,
                                     LocalDateTime returnDateTime,
                                     Double price) {
}
