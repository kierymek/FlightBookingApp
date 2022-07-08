package com.app.flight;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<UserInstance, String> {

    @Query("{email:'?0'}")
    UserInstance findByEmail(String email);

    @Query("{$and: [{email:'?0'}, {password:'?1'}]}")
    UserInstance findByCredentials(String email, String password);

    public long count();

}