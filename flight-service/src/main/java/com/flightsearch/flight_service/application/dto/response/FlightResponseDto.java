package com.flightsearch.flight_service.application.dto.response;


import java.time.LocalDateTime;
import java.util.UUID;

public record FlightResponseDto(UUID id,
                                AirportDto departureAirport,
                                AirportDto arrivalAirport,
                                LocalDateTime departureDateTime,
                                LocalDateTime returnDateTime,
                                Double price) {
}
