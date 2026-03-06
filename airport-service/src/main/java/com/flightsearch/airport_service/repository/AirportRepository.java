package com.flightsearch.airport_service.repository;

import com.flightsearch.airport_service.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AirportRepository extends JpaRepository<Airport, UUID> {
}
