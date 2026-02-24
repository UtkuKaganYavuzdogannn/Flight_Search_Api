package com.flightsearch.flight_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final RestTemplate restTemplate;

    public FlightController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/check-airport")
    public String checkAirportService() {
        return restTemplate.getForObject(
                "http://AIRPORT-SERVICE/airports/ping",
                String.class
        );
    }
}