package com.flightsearch.flight_service.service;



import com.flightsearch.flight_service.dto.response.AirportDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class AirportClient {
    private final RestTemplate restTemplate;

    public AirportClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    public AirportDto getAirportById(UUID id){

        String url = "http://localhost:8081/airports/" + id ;

        return restTemplate.getForObject(url, AirportDto.class);
    }



}
