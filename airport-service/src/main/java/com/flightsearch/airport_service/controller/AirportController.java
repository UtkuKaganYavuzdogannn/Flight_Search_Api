package com.flightsearch.airport_service.controller;

import com.flightsearch.airport_service.application.dto.request.AirportCreateRequestDto;
import com.flightsearch.airport_service.application.dto.request.AirportUpdateRequestDto;
import com.flightsearch.airport_service.application.dto.response.AirportResponseDto;
import com.flightsearch.airport_service.entity.Airport;
import com.flightsearch.airport_service.repository.AirportRepository;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/airports")
public class AirportController {
    private final AirportRepository airportRepository;

    public AirportController(AirportRepository airportRepository){
        this.airportRepository= airportRepository;
    }

@PostMapping
    public AirportResponseDto createAirport(@RequestBody AirportCreateRequestDto requestDto) {

    Airport airport = new Airport(requestDto.city());

    Airport savedAirport = airportRepository.save(airport);


    return new AirportResponseDto(savedAirport.getId(),
            savedAirport.getCity());
}

    @PutMapping("/{id}")
    public AirportResponseDto updateAirport(
            @PathVariable UUID id,
            @RequestBody AirportUpdateRequestDto requestDto
    ) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Havaalanı bulunamadı: " + id));


        //Bulunan Airportun Şehir bilgisini set ettik.
        airport.setCity(requestDto.city());
        Airport updatedAirport = airportRepository.save(airport);

        // 4. Response DTO olarak dön
        return new AirportResponseDto(
                updatedAirport.getId(),
                updatedAirport.getCity()
        );
    }

}
