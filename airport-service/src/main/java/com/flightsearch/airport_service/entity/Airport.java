package com.flightsearch.airport_service.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="airports")
public class Airport {

    @Id
    @GeneratedValue
    @Column(name="airport_id")
    private UUID id;

    @Column(nullable = false ,length = 50)
    private String city;

    public Airport() {
    }

    public Airport(String city) {
        this.city = city;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

