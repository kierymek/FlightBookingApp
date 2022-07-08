package com.app.flight;

import com.amadeus.resources.Traveler;
import com.google.gson.JsonObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class UserInstance {
    private String fname;
    private String lname;
    @Indexed(unique=true)
    private String email;
    private String password;

    public UserInstance() {}

    public UserInstance(String fname, String lname, String email, String password) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
    }

    public UserInstance(JsonObject user) {
        String[] nameArray = user.get("fullName").getAsString().split("");
        this.fname = nameArray[1];
        this.lname = nameArray[1];
        this.email = user.get("email").getAsString();
        this.password = user.get("password").getAsString();
    }
}