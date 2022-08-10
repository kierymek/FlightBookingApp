package com.app.flight;

import com.google.gson.JsonObject;

public class Flight {
    private String number;
    private String from;
    private String to;
    private String departureDate;
    private String arrivalDate;

    private String terminal;
    private String duration;
    private String aircraft;

    public Flight(JsonObject flightElement) {
        this.number = flightElement.get("number").getAsString();
        this.from = flightElement.get("arrival").getAsJsonObject().get("iataCode").getAsString();
            this.terminal = flightElement.get("arrival").getAsJsonObject().get("terminal") != null
                ? flightElement.get("arrival").getAsJsonObject().get("terminal").getAsString()
                : flightElement.get("departure").getAsJsonObject().get("terminal") != null
                    ? flightElement.get("departure").getAsJsonObject().get("terminal").getAsString()
                    : "to be announced";
        this.to = flightElement.get("departure").getAsJsonObject().get("iataCode").getAsString();
        this.departureDate = flightElement.get("arrival").getAsJsonObject().get("at").getAsString();
        this.arrivalDate = flightElement.get("departure").getAsJsonObject().get("at").getAsString();
        this.aircraft = flightElement.get("aircraft").getAsJsonObject().get("code").getAsString();
        this.duration = flightElement.get("duration").getAsString();
    }

    public Flight(String number, String from, String to, String departureDate, String arrivalDate, String terminal, String duration, String aircraft) {
        this.number = number;
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.terminal = terminal;
        this.duration = duration;
        this.aircraft = aircraft;
    }

    public Flight() {}
}
