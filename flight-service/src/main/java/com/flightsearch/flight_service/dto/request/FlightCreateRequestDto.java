package com.flightsearch.flight_service.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record FlightCreateRequestDto(UUID departureAirportId,
                                     UUID arrivalAirportId,
                                     LocalDateTime departureDate,
                                     LocalDateTime returnDate,
                                     Double price) {
}
