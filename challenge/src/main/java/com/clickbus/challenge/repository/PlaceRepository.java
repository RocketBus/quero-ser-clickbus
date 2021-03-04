package com.clickbus.challenge.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.clickbus.challenge.request.Place;

public interface PlaceRepository extends MongoRepository<Place, String >{
public Place findByName(String name);

}
