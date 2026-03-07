package com.flightsearch.flight_service.service;

import com.flightsearch.flight_service.dto.request.DeleteRecordDto;
import com.flightsearch.flight_service.dto.request.FlightCreateRequestDto;
import com.flightsearch.flight_service.dto.request.FlightSearchRequestDto;
import com.flightsearch.flight_service.dto.response.FlightResponseDto;
import com.flightsearch.flight_service.dto.response.FlightSearchResponseDto;

import java.util.List;
import java.util.UUID;

public interface IFlightService {


    FlightResponseDto createFlight(FlightCreateRequestDto requestDto);


    String deleteFlight(DeleteRecordDto deleteRecordDto);


    List<FlightSearchResponseDto> searchFlights(FlightSearchRequestDto requestDto);


    FlightResponseDto getFlightById(UUID id);

    List<FlightResponseDto> getAllFlights();


    FlightResponseDto updateFlight(UUID id, FlightCreateRequestDto requestDto);

}