package com.flightsearch.flight_service.application.dto.response;


import java.time.LocalDateTime;
import java.util.UUID;

public record FlightResponseDto(UUID id,
                                UUID departureAirport,
                                UUID arrivalAirport,
                                LocalDateTime departureDateTime,
                                LocalDateTime returnDateTime,
                                Double price) {
}
