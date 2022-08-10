package com.app.flight;

import com.amadeus.resources.FlightOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface FlightReservationRepository extends MongoRepository<FlightReservation, String> {

    @Query("{name:'?0'}")
    FlightReservation findItemByName(String name);

    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    List<FlightReservation> findAll(String category);

    @Query("{email:'?0'}")
    List<FlightReservation> findAllByEmail(String email);

    public long count();

}