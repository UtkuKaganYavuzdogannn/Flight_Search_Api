package com.flightsearch.flight_service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record FlightSearchResponseDto(
        UUID flightId,
        AirportDto departureAirport,
        AirportDto arrivalAirport,
        LocalDateTime departureDate,
        LocalDateTime returnDate,
        Double price
) {}
