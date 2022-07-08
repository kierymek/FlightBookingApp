package com.app.flight;

import com.amadeus.resources.Traveler;
import com.google.gson.JsonObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("travelers")
public class TravelerInstance {

    private String email;
    private Traveler traveler;

    public TravelerInstance(JsonObject travelerInfo) {
        super();
        String fname = travelerInfo.get("fname").getAsString();
        String lname = travelerInfo.get("lname").getAsString();
        String dateOfBirth = travelerInfo.get("dob").getAsString();
        Traveler traveler = new Traveler();

        Traveler.Phone phone = traveler.new Phone();
        phone.setCountryCallingCode("1");
        phone.setNumber("1231231234");
        phone.setDeviceType("MOBILE");

        Traveler.Contact contact = traveler.new Contact();
        Traveler.Phone[] phones = {phone};
        contact.setPhones(phones);
        traveler.setContact(contact);

        Traveler.Name name = traveler.new Name(fname, lname);
        traveler.setName(name);

        traveler.setDateOfBirth(dateOfBirth);
        traveler.setId("1");
        Traveler.Document document = traveler.new Document();
        document.setDocumentType("PASSPORT");
        document.setNumber("00000000");
        document.setExpiryDate("2025-04-14");
        document.setNationality("ES");
        document.setHolder(true);
        document.setIssuanceCountry("ES");
        Traveler.Document[] documents = {document};
        traveler.setDocuments(documents);

        this.traveler = traveler;
        this.email = travelerInfo.get("email").getAsString();
    }
}