package com.flightsearch.flight_service.repository;

import com.flightsearch.flight_service.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FlightRepository extends JpaRepository<Flight, UUID> {

}
