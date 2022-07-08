package com.app.flight;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TravelerRepository extends MongoRepository<TravelerInstance, String> {

    @Query("{name:'?0'}")
    TravelerRepository findItemByName(String name);

    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    List<TravelerRepository> findAll(String category);

    public long count();

}