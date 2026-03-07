package com.flightsearch.flight_service.controller;

import com.flightsearch.flight_service.dto.request.DeleteRecordDto;
import com.flightsearch.flight_service.dto.request.FlightCreateRequestDto;
import com.flightsearch.flight_service.dto.request.FlightSearchRequestDto;
import com.flightsearch.flight_service.dto.response.AirportDto;
import com.flightsearch.flight_service.dto.response.FlightResponseDto;
import com.flightsearch.flight_service.dto.response.FlightSearchResponseDto;
import com.flightsearch.flight_service.entity.Flight;
import com.flightsearch.flight_service.repository.FlightRepository;
import com.flightsearch.flight_service.service.AirportClient;
import org.springframework.web.bind.annotation.*;

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
                requestDto.departureDate(),
                requestDto.returnDate(),
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

    @DeleteMapping("/delete")
    public String deleteFlight(@RequestBody DeleteRecordDto deleteRecordDto) {
        if (!flightRepository.existsById(deleteRecordDto.id())) {
            return "Bu Id'ye ait bir kayıt bulunamadı.";
        } else {
            flightRepository.deleteById(deleteRecordDto.id());
            return "Bu kayıt silindi.";

        }
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


