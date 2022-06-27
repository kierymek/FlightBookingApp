package com.app.flight;

import com.amadeus.resources.Traveler;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("travelers")
public class TravelerInstance {

    @Id
    private String id;
    private Traveler traveler;

    public TravelerInstance(String id, Traveler traveler) {
        super();
        this.id = id;
        this.traveler = traveler;
    }
}