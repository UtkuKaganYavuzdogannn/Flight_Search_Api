package com.flightsearch.flight_service.controller;

import com.flightsearch.flight_service.dto.request.DeleteRecordDto;
import com.flightsearch.flight_service.dto.request.FlightCreateRequestDto;
import com.flightsearch.flight_service.dto.request.FlightSearchRequestDto;
import com.flightsearch.flight_service.dto.response.FlightResponseDto;
import com.flightsearch.flight_service.dto.response.FlightSearchResponseDto;
import com.flightsearch.flight_service.service.IFlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final IFlightService flightService;

    public FlightController(IFlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public FlightResponseDto createFlight(
            @RequestBody FlightCreateRequestDto requestDto) {

        return flightService.createFlight(requestDto);
    }

    @DeleteMapping("/delete")
    public void deleteFlight(
            @RequestBody DeleteRecordDto deleteRecordDto) {

        flightService.deleteFlight(deleteRecordDto);
    }

    @PostMapping("/search")
    public List<FlightSearchResponseDto> searchFlights(
            @RequestBody FlightSearchRequestDto request) {

        return flightService.searchFlights(request);
    }

}