package com.app.flight;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightOrder;
import com.amadeus.resources.FlightPrice;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
@Service
@EnableMongoRepositories
public class  MongoConnect {
    @Autowired
    FlightReservationRepository flightReservationRepo;
    @Autowired
    TravelerRepository travelerRepo;
    @Autowired
    UserRepository userRepository;
    public UserInstance login(String email, String password) throws RuntimeException {
        System.out.println("login in mongodb api with: " + email + " " + password);
        UserInstance user = userRepository.findByCredentials(email, password);
        System.out.println("found user: " + user);
        if (user == null) {
            throw new RuntimeException("Invalid credentials!");
        }
        return user;
    }

    public UserInstance register(JsonObject user) throws RuntimeException {
        JsonObject userJson = user.get("data").getAsJsonObject();
        String email = userJson.get("email").getAsString();
        UserInstance foundUser = userRepository.findByEmail(email);
        if (foundUser != null) {
            throw new RuntimeException("User already exists!");
        }
        UserInstance userInstance = new UserInstance(user);
        System.out.println("register in mongodb api with: " + userInstance + "userRepository: " +  userRepository);

        userRepository.save(userInstance);
        return userInstance;
    }

    public UserInstance auth(String email) throws RuntimeException {
        System.out.println("auth in mongodb api with: " + email);
        UserInstance user = userRepository.findByEmail(email);
//        UserInstance user = userRepository.findByCredentials(email, password);
        System.out.println("found user: " + user);
        if (user == null) {
            throw new RuntimeException("Invalid credentials!");
        }
        return user;
    }
     void addTraveler(JsonObject user) throws RuntimeException {
        userRepository.save(new UserInstance(user));
    }

    public FlightReservation saveReservation(JsonObject order) throws RuntimeException {
        JsonObject flight = order.getAsJsonArray("flightOffers").get(0).getAsJsonObject();
        JsonObject traveler = order.getAsJsonArray("travelers").get(0).getAsJsonObject();
        JsonObject user = order.get("user").getAsJsonObject();
        FlightReservation reservation = new FlightReservation(flight, traveler, user);
        flightReservationRepo.save(reservation);
        return reservation;
    }

    public List<FlightReservation> getUserReservations(String email) throws RuntimeException {
        return flightReservationRepo.findAllByEmail(email);
    }

    public void cancel(String id) throws RuntimeException {
        flightReservationRepo.deleteById(id);
    }
}
