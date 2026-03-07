package com.flightsearch.flight_service.service;

import com.flightsearch.flight_service.dto.request.DeleteRecordDto;
import com.flightsearch.flight_service.dto.request.FlightCreateRequestDto;
import com.flightsearch.flight_service.dto.request.FlightSearchRequestDto;
import com.flightsearch.flight_service.dto.response.AirportDto;
import com.flightsearch.flight_service.dto.response.FlightResponseDto;
import com.flightsearch.flight_service.dto.response.FlightSearchResponseDto;
import com.flightsearch.flight_service.entity.Flight;
import com.flightsearch.flight_service.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FlightService implements IFlightService {

    private final FlightRepository flightRepository;
    private final AirportClient airportClient;

    public FlightService(FlightRepository flightRepository,
                         AirportClient airportClient) {
        this.flightRepository = flightRepository;
        this.airportClient = airportClient;
    }

    @Override
    public FlightResponseDto createFlight(FlightCreateRequestDto requestDto) {

        Flight flight = new Flight(
                requestDto.departureAirportId(),
                requestDto.arrivalAirportId(),
                requestDto.departureDate(),
                requestDto.returnDate(),
                requestDto.price()
        );

        Flight savedFlight = flightRepository.save(flight);

        return new FlightResponseDto(
                savedFlight.getId(),
                savedFlight.getDepartureAirportId(),
                savedFlight.getArrivalAirportId(),
                savedFlight.getDepartureDate(),
                savedFlight.getReturnDate(),
                savedFlight.getPrice()
        );
    }

    @Override
    public String deleteFlight(DeleteRecordDto deleteRecordDto) {

        if (!flightRepository.existsById(deleteRecordDto.id())) {
            throw new RuntimeException("Bu Id'ye ait bir kayıt bulunamadı.");
        }

         flightRepository.deleteById(deleteRecordDto.id());
        return "Kayıt başarıyla silindi.";
    }

    //Daha Sonra dolduralım.
    @Override
    public List<FlightSearchResponseDto> searchFlights(FlightSearchRequestDto request) {

        AirportDto departureAirport =
                airportClient.getAirportById(request.departureAirportId());

        AirportDto arrivalAirport =
                airportClient.getAirportById(request.arrivalAirportId());

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



    @Override
    public FlightResponseDto getFlightById(UUID id) {
        return null;
    }

    @Override
    public List<FlightResponseDto> getAllFlights() {
        return null;
    }

    @Override
    public FlightResponseDto updateFlight(UUID id, FlightCreateRequestDto requestDto) {
        return null;
    }
}