package com.app.flight;

import com.amadeus.resources.FlightOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("flightReservations")
public class FlightReservation {

    @Id
    private String id;
    private FlightOrder flightOrder;

    public FlightReservation(String id, FlightOrder flightOrder) {
        super();
        this.id = id;
        this.flightOrder = flightOrder;
    }
}
