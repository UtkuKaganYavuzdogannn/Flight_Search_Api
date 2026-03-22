package com.flightsearch.flight_service.dto.mapper;

import com.flightsearch.flight_service.dto.response.AirportDto;
import com.flightsearch.flight_service.dto.response.FlightSearchResponseDto;
import com.flightsearch.flight_service.entity.Flight;
import org.springframework.stereotype.Component;

//DB'den gelen uçuş bilgilerini tekrar response dto'ya çeviriyoruz daha sonra kullanıcıya dönebiliriz.
//Dbde bir kolon ismi değiştiği zaman eğer dto ile cevap dönüyorsak bir sorun yaşamayız veya frontend patlamaz.
@Component
public class FlightMapper {

    public FlightSearchResponseDto toSearchResponse(Flight flight, AirportDto depAirport, AirportDto arrAirport){
        return new FlightSearchResponseDto(
                flight.getId(),
                depAirport,
                arrAirport,
                flight.getDepartureDate(),
                flight.getReturnDate(),
                flight.getPrice()
        );
    }

}
