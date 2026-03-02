package com.flightsearch.flight_service.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue
    @Column(name ="flight_id")
    private UUID id;

    @Column(nullable = false)
    private UUID departureAirportId;

    @Column(nullable = false)
    private UUID arrivalAirportId;

    @Column(nullable = false)
    private LocalDateTime departureDate;

    @Column(nullable = true)
    private LocalDateTime returnDate;



    @Column(nullable = false)
    private Double price;


    protected Flight() {
    }

    public Flight(UUID departureAirportId,
                  UUID arrivalAirportId,
                  LocalDateTime departureDate,
                  LocalDateTime returnDate,
                  Double price) {
        this.departureAirportId = departureAirportId;
        this.arrivalAirportId = arrivalAirportId;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
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

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public Double getPrice() {
        return price;
    }
}
