package com.flightsearch.flight_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "flights")
public class Flight {

    @Column(name ="flight_id")
    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID departureAirportId;

    @Column(nullable = false)
    private UUID arrivalAirportId;

    @Column(nullable = false)
    private LocalDateTime departureDate;

    @Column(nullable = false)
    private LocalDateTime arrivalDate;



    @Column(nullable = false)
    private Double price;


    public Flight(UUID departureAirportId,
                  UUID arrivalAirportId,
                  LocalDateTime departureDate,
                  LocalDateTime returnDate,
                  Double price) {
        this.departureAirportId = departureAirportId;
        this.arrivalAirportId = arrivalAirportId;
        this.departureDate = departureDate;
        this.price = price;
    }


    public UUID getId() {
        return id;
    }

    public UUID getDepartureAirportId() {
        return departureAirportId;
    }

    public UUID getArrivalAirportId() {
        return arrivalAirportId;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public Double getPrice() {
        return price;
    }
}
