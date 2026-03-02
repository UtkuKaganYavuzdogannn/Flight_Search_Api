package com.flightsearch.flight_service.controller;

import com.flightsearch.flight_service.application.dto.request.FlightCreateRequestDto;
import com.flightsearch.flight_service.application.dto.request.FlightSearchRequestDto;
import com.flightsearch.flight_service.application.dto.response.AirportDto;
import com.flightsearch.flight_service.application.dto.response.FlightResponseDto;
import com.flightsearch.flight_service.application.dto.response.FlightSearchResponseDto;
import com.flightsearch.flight_service.entity.Flight;
import com.flightsearch.flight_service.repository.FlightRepository;
import com.flightsearch.flight_service.service.AirportClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/flights")
public class FlightController {

    //DI Ve Const. Partı

    private final AirportClient airportClient;
    private final FlightRepository flightRepository;

    public FlightController(AirportClient airportClient, FlightRepository flightRepository) {
        this.airportClient = airportClient;
        this.flightRepository = flightRepository;
    }

    // API endpointleri

    @PostMapping
    public FlightResponseDto createFlight(@RequestBody FlightCreateRequestDto requestDto) {

        Flight flight = new Flight(
                requestDto.departureAirportId(),
                requestDto.arrivalAirportId(),
                requestDto.departureDateTime(),
                requestDto.returnDateTime(),
                requestDto.price()
        );

        Flight savedFlight = flightRepository.save(flight);

        return new FlightResponseDto(savedFlight.getId(),
                savedFlight.getDepartureAirportId(),
                savedFlight.getArrivalAirportId(),
                savedFlight.getDepartureDate(),
                savedFlight.getReturnDate(),
                savedFlight.getPrice());

    }



    // RestTemplate ile search

    @PostMapping("/search")
    public List<FlightSearchResponseDto> searchFlights(
            @RequestBody FlightSearchRequestDto request
    ) {

        AirportDto departureAirport =
                airportClient.getAirportById(request.departureAirportId());

        AirportDto arrivalAirport =
                airportClient.getAirportById(request.arrivalAirportId());

        // Şimdilik MOCK response
        FlightSearchResponseDto flight = new FlightSearchResponseDto(
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


