package com.app.flight;

import com.amadeus.resources.FlightOrder;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Location;
import com.google.gson.JsonObject;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightPrice;
import com.amadeus.resources.Traveler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RestController
//@EnableMongoRepositories
@RequestMapping(value="/api")
public class ApiController {
	@Autowired
	private MongoConnect mongoConnect;
	@GetMapping("/locations")
	public Location[] locations(@RequestParam(required=true) String keyword) throws ResponseException {
		return AmadeusConnect.INSTANCE.location(keyword);
	}

	@GetMapping("/flights")
	public FlightOfferSearch[] flights(@RequestParam(required=true) String origin,
						  @RequestParam(required=true) String destination,
						  @RequestParam(required=true) String departDate,
						  @RequestParam(required=true) String adults,
						  @RequestParam(required = false) String returnDate)
						  throws ResponseException {
		return AmadeusConnect.INSTANCE.flights(origin, destination, departDate, adults, returnDate);
	}

	@PostMapping("/confirm")
	public FlightPrice confirm(@RequestBody(required=true) FlightOfferSearch search) throws ResponseException {
		return AmadeusConnect.INSTANCE.confirm(search);
	}

	@PostMapping("/traveler")
	public Traveler traveler(@RequestBody(required=true) JsonObject travelerInfo) {
		return DatabaseConnect.traveler(travelerInfo.get("data").getAsJsonObject());
	}

	@PostMapping("/order")
	public FlightReservation order(@RequestBody(required=true) JsonObject order) throws ResponseException{
		System.out.println("FlightOrder creating");
		JsonObject orderJson = order.get("data").getAsJsonObject();
		return mongoConnect.saveReservation(orderJson);
	}

	@PostMapping("/login")
	public UserInstance login(@RequestBody(required=true) JsonObject user) throws RuntimeException {
		JsonObject userJson = user.get("data").getAsJsonObject();
		String email = userJson.get("email").getAsString();
		String password = userJson.get("password").getAsString();
		try {
			return mongoConnect.login(email, password);
		} catch (RuntimeException err) {
			System.out.println("Error was catched when logging: " +  err);
			return null;
		}
	}

	@PostMapping("/register")
	public UserInstance register(@RequestBody(required=true) JsonObject user) throws RuntimeException {
		try {
			return mongoConnect.register(user.get("data").getAsJsonObject());
		} catch (RuntimeException err) {
			System.out.println("Error was catched when authentificating: " +  err);
			return null;
		}
	}

	@PostMapping("/auth")
	public UserInstance auth(@RequestBody(required=true) JsonObject user) throws RuntimeException {
		JsonObject userJson = user.get("data").getAsJsonObject();
		String email = userJson.get("email").getAsString();
		try {
			return mongoConnect.auth(email);
		} catch (RuntimeException err) {
			System.out.println("Error was catched when registering: " +  err);
			return null;
		}
	}

	@GetMapping("/reservations")
	public List<FlightReservation> reservations(@RequestParam(required=true) String email) throws ResponseException {
		return mongoConnect.getUserReservations(email);
	}

	@PostMapping("/cancel")
	public void cancel(@RequestBody(required=true) JsonObject req) throws RuntimeException {
		JsonObject userJson = req.get("data").getAsJsonObject();
		String id = userJson.get("id").getAsString();
		try {
			mongoConnect.cancel(id);
		} catch (RuntimeException err) {
			System.out.println("Error was catched when canceling: " +  err);
		}
	}

}