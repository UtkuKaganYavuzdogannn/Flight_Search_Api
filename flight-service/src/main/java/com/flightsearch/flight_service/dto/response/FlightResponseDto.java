package com.flightsearch.flight_service.dto.response;


import java.time.LocalDateTime;
import java.util.UUID;

public record FlightResponseDto(UUID id,
                                UUID departureAirportId,
                                UUID arrivalAirportId,
                                LocalDateTime departureDate,
                                LocalDateTime returnDate,
                                UUID pairID,
                                Double price) {
}
