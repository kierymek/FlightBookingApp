package com.app.flight;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Location;
import com.google.gson.JsonObject;

import com.amadeus.referenceData.Locations;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightPrice;
import com.amadeus.resources.FlightOrder;

enum AmadeusConnect {
    INSTANCE;
    private Amadeus amadeus;
    private AmadeusConnect() {
        this.amadeus = Amadeus
            .builder("nKBtjCbBdgfGr22vLPfW5CC6AUAAQJoJ", "YVVjyerv0fYaJMBH")
            .build();
    }

    public Location[] location(String keyword) throws ResponseException {
        return amadeus.referenceData.locations.get(Params
            .with("keyword", keyword)
            .and("subType", Locations.AIRPORT));
    }

    public FlightOfferSearch[] flights(String origin, String destination, String departDate, String adults, String returnDate) throws ResponseException {
        System.out.println("FlightOfferSearch: ");
        System.out.println(returnDate);
        return returnDate != null
                ?  amadeus.shopping.flightOffersSearch.get(
                        Params.with("originLocationCode", origin)
                        .and("destinationLocationCode", destination)
                        .and("departureDate", departDate)
                        .and("returnDate", returnDate)
                        .and("adults", adults)
                        .and("max", 3))
                : amadeus.shopping.flightOffersSearch.get(
                        Params.with("originLocationCode", origin)
                          .and("destinationLocationCode", destination)
                          .and("departureDate", departDate)
                          .and("adults", adults)
                          .and("max", 3));
    }

    public FlightPrice confirm(FlightOfferSearch offer) throws ResponseException {
        return amadeus.shopping.flightOffersSearch.pricing.post(offer);
    }

    public FlightOrder order(JsonObject order) throws ResponseException {
        return amadeus.booking.flightOrders.post(order);
    }
}
