package com.flightsearch.flight_service.scheduler;


import com.flightsearch.flight_service.entity.Flight;
import com.flightsearch.flight_service.repository.FlightRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Component
public class FlightDataSyncScheduler {

    private final FlightRepository flightRepository;
    private final Random random = new Random() ;

    private final String ANKARA_ID = "25e857e6-7f33-4eb6-9309-4926370a00e4";
    private final String ISTANBUL_ID = "19405834-484c-4016-8428-22cb7bdd5d1b";


    public FlightDataSyncScheduler(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void flightsFromMockApi (){
        System.out.println("---Backround Job başladı "+ LocalDateTime.now() + "---");

        double randomPrice = 1500 + (500 * random.nextDouble());
        LocalDateTime flightDate = LocalDateTime.now().plusDays(random.nextInt(30));

        Flight mockFlight = new Flight(
                UUID.fromString(ANKARA_ID),
                UUID.fromString(ISTANBUL_ID),
                flightDate,
                null, // returnDate
                null, // pairID
                Math.round(randomPrice * 100.0) / 100.0
        );

        flightRepository.save(mockFlight);
        System.out.println("İşlem Başarılı: Ankara -> İstanbul yönüne " + mockFlight.getPrice() + " TL tutarında yeni uçuş eklendi.");
        System.out.println("--- Background Job Tamamlandı ---");


    }

}
