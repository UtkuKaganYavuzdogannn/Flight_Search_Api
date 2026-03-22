package com.flightsearch.flight_service.repository;

import com.flightsearch.flight_service.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface FlightRepository extends JpaRepository<Flight, UUID> {

    @Query(value = "SELECT * FROM flights f" +
                    "WHERE f.departure_airport_id = : depId" +
                    "AND f.arrival_airport_id = : arrId" +
                    "AND DATE(f.departure_date) = DATE(:depDate)" ,     //Date fonksiyonu tahih-saat uyumsuzluğu için
                    nativeQuery = true)
    List<Flight> searchByRouteAndDate(
            @Param("depId") UUID depId,
            @Param("arrId") UUID arrId,
            @Param("depDate")LocalDateTime depDate
            );


}
