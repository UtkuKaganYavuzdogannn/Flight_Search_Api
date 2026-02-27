package com.flightsearch.airport_service.controller;

import com.flightsearch.airport_service.application.dto.response.AirportResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/airports")
public class AirportController {

    @GetMapping("/{id}")
    public AirportResponseDto getAirportById(@PathVariable UUID id) {

        return new AirportResponseDto(id, "Istanbul");
    }
}
