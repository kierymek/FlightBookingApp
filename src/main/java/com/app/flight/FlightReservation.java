package com.app.flight;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("flightReservations")
public class FlightReservation {
    private String email;
    private Flight[] flights;
    private boolean oneWay;
    private Double price;

    private String passport;
    private String name;
    private String surname;
    private String dateOfBirth;

    @Id
    private String id;

    public FlightReservation(JsonObject flight, JsonObject traveler, JsonObject user) {
        super();
        this.email = user.get("email").getAsString();
        JsonArray flightSegments = flight.get("itineraries").getAsJsonArray();
        this.flights = new Flight[flightSegments.size()];
        for (int i = 0; i < flightSegments.size(); i++) {
            JsonObject flightElement = flightSegments.get(i).getAsJsonObject().get("segments").getAsJsonArray().get(0).getAsJsonObject();
            this.flights[i] = new Flight(flightElement);
        }
        this.oneWay = flight.get("oneWay").getAsBoolean();
        this.price = flight.get("price").getAsJsonObject().get("total").getAsDouble();
        this.passport = traveler.get("documents").getAsJsonArray().get(0).getAsJsonObject().get("number").getAsString();
        this.name = traveler.get("name").getAsJsonObject().get("firstName").getAsString();
        this.surname = traveler.get("name").getAsJsonObject().get("lastName").getAsString();
        this.dateOfBirth = traveler.get("dateOfBirth").getAsString();
        this.id = "" + System.currentTimeMillis();
    }

    public FlightReservation(String id, String email, Flight[] flights, boolean oneWay, Double price, String passport, String name, String surname, String dateOfBirth) {
        this.email = email;
        this.flights = flights;
        this.oneWay = oneWay;
        this.price = price;
        this.passport = passport;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.id = id;
    }

    public FlightReservation() {}
}
