package com.places.manager.repository.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.places.manager.model.Place;
import com.places.manager.repository.PlacesRepository;

@Repository
public class PlacesRepositoryImpl implements PlacesRepository {
	
	private List<Place> defaultPlaces;
	
	public PlacesRepositoryImpl() {
		
		defaultPlaces = new ArrayList<>();
		
		Place festival = new Place();
		festival.setName("Sobbat Festival");
		festival.setSlug("California St 06");
		festival.setState("Ohio");
		festival.setCity("Columbus");
		festival.setCreatedAt(LocalDateTime.now());
		
		Place museum = new Place();
		museum.setName("Robot Museum");
		museum.setSlug("Tech St 25");
		museum.setState("Arizona");
		museum.setCity("Phoenix");
		museum.setCreatedAt(LocalDateTime.now());
		
		Place hotel = new Place();
		hotel.setName("Bates Hotel");
		hotel.setSlug("Trinity St 25");
		hotel.setState("Texas");
		hotel.setCity("Houston");
		hotel.setCreatedAt(LocalDateTime.now());
		
		defaultPlaces.add(festival);
		defaultPlaces.add(museum);
		defaultPlaces.add(hotel);
	}

	public List<Place> list() {
		
		return defaultPlaces;
	}

}
