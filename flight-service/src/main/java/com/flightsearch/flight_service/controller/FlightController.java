package com.flightsearch.flight_service.controller;

import com.flightsearch.airport_service.application.dto.response.AirportResponseDto;
import com.flightsearch.flight_service.application.dto.request.FlightSearchRequestDto;
import com.flightsearch.flight_service.application.dto.response.AirportDto;
import com.flightsearch.flight_service.application.dto.response.FlightResponseDto;
import com.flightsearch.flight_service.service.AirportClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final AirportClient airportClient;

    public FlightController(AirportClient airportClient) {
        this.airportClient = airportClient;
    }

    @PostMapping("/search")
    public List<FlightResponseDto> searchFlights(
            @RequestBody FlightSearchRequestDto request
    ) {

        AirportDto departureAirport =
                airportClient.getAirportById(request.departureAirportId());

        AirportDto arrivalAirport =
                airportClient.getAirportById(request.arrivalAirportId());

        // Şimdilik MOCK response
        FlightResponseDto flight = new FlightResponseDto(
                UUID.randomUUID(),
                departureAirport,
                arrivalAirport,
                request.departureDate(),
                request.returnDate(),
                2500.0
        );

        return List.of(flight);
    }
}