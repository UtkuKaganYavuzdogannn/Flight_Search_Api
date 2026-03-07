package com.flightsearch.flight_service.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

public record FlightSearchRequestDto(UUID departureAirportId,
                                     UUID arrivalAirportId,
                                     LocalDateTime departureDate,
                                     LocalDateTime returnDate) {
}
