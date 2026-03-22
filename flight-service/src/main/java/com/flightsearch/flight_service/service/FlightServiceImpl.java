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
import com.flightsearch.flight_service.dto.mapper.FlightMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AirportClient airportClient;
    private final FlightMapper flightMapper;

    public FlightServiceImpl(FlightRepository flightRepository,
                             AirportClient airportClient , FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.airportClient = airportClient;
        this.flightMapper = flightMapper;
    }

    @Override
    public FlightResponseDto createFlight(FlightCreateRequestDto requestDto) {

        // Airport var mı kontrolü
        AirportDto departureAirport =
                airportClient.getAirportById(requestDto.departureAirportId());

        AirportDto arrivalAirport =
                airportClient.getAirportById(requestDto.arrivalAirportId());


        if (departureAirport == null || arrivalAirport == null) {
            throw new RuntimeException("BIR AIRPORT'A ULAŞILMIYOR.");
        }

        Flight departureFlight = new Flight(
                requestDto.departureAirportId(),
                requestDto.arrivalAirportId(),
                requestDto.departureDate(),
                requestDto.returnDate(),
                null,               //Henüz pair id generated edilmediği için boş
                requestDto.price()
        );

        Flight savedDeparture = flightRepository.save(departureFlight);

        // Buraya kadar kaydetmek istediğimiz ilk uçuş bilgisini kaydetmiş oluyoruz.
        //Şimdi dönüş tarihli uçaksa 2. uçuş kaydını oluşturuyorum.

        if (requestDto.returnDate() != null) {
            Flight returnFlight = new Flight(
                    requestDto.arrivalAirportId(), // Kalkış-Varış yer değiştirdi
                    requestDto.departureAirportId(),
                    requestDto.returnDate(), // Dönüş tarihi kalkış saati oldu
                    null,
                    savedDeparture.getId(), // Dönüşün referansı gidişin Id'si .
                    requestDto.price()
            );

            Flight savedReturn = flightRepository.save(returnFlight);

            // (Opsiyonel) Gidiş uçuşunu da güncellemek istersen:
            savedDeparture.setPairID(savedReturn.getId());
            flightRepository.save(savedDeparture);
        }


        return new FlightResponseDto(
              savedDeparture.getId(),
                savedDeparture.getDepartureAirportId(),
                savedDeparture.getArrivalAirportId(),
                savedDeparture.getDepartureDate(),
                savedDeparture.getReturnDate(),
                savedDeparture.getPairID(),
                savedDeparture.getPrice()
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

    // SEARCH METODU BURADA :

    @Override
    public List<FlightSearchResponseDto> searchFlights(FlightSearchRequestDto request) {

       List<FlightSearchResponseDto> finalResponseList = new ArrayList<>();

       List<Flight> departureFlights = flightRepository.searchByRouteAndDate(request.departureAirportId(),
                                                request.arrivalAirportId(),request.departureDate());

       for (Flight depFlight: departureFlights){
           AirportDto departureAirport = airportClient.getAirportById(depFlight.getDepartureAirportId());
           AirportDto arrivalAirport = airportClient.getAirportById(depFlight.getArrivalAirportId());

           FlightSearchResponseDto gidisDto = flightMapper.toSearchResponse(depFlight, departureAirport, arrivalAirport);
           finalResponseList.add(gidisDto);

           if (request.returnDate() != null && depFlight.getPairID()!= null){
               flightRepository.findById(depFlight.getPairID()).ifPresent(returnFlight -> {
                   FlightSearchResponseDto donusDto = flightMapper.toSearchResponse(returnFlight,arrivalAirport,departureAirport);
                   finalResponseList.add(donusDto);
               });
           }
       }


       return finalResponseList;
    }


    //Burası da  daha sonra dolucak .
    @Override
    public FlightResponseDto getFlightById(UUID id) {
        return null;
    }


    @Override
    public FlightResponseDto updateFlight(UUID id, FlightCreateRequestDto requestDto) {
        return null;
    }
}