package com.places.manager.db;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.places.manager.model.Place;

@Component
public class PlacesDatabase {
	
	private static Map<Integer, Place> placesDatabase = new HashMap<>();
	private static Integer id = 0;
	
	static {
		System.out.println("Initializing places simulated database...");
		
		Place festival = new Place();
		festival.setId(++id);
		festival.setName("Sarah's Festival");
		festival.setSlug("California St 06");
		festival.setState("Ohio");
		festival.setCity("Columbus");
		festival.setCreatedAt(LocalDateTime.now());
		
		Place museum = new Place();
		museum.setId(++id);
		museum.setName("Robot Museum");
		museum.setSlug("Tech St 25");
		museum.setState("Arizona");
		museum.setCity("Phoenix");
		museum.setCreatedAt(LocalDateTime.now());
		
		Place hotel = new Place();
		hotel.setId(++id);
		hotel.setName("Bates Hotel");
		hotel.setSlug("Trinity St 25");
		hotel.setState("Texas");
		hotel.setCity("Houston");
		hotel.setCreatedAt(LocalDateTime.now());
		
		placesDatabase.put(festival.getId(), festival);
		placesDatabase.put(museum.getId() , museum);
		placesDatabase.put(hotel.getId(), hotel);
	}
	
	public List<Place> selectAll(){
		List<Place> returnedPlaces = new ArrayList<>();
		
		for (Entry<Integer, Place> placeTuple : placesDatabase.entrySet()) {
			Place place = placeTuple.getValue();
			place.setId(placeTuple.getKey());
			returnedPlaces.add(place);
		}
		
		return returnedPlaces;
	}
	
	public List<Place> selectAllPlacesWherePlaceNameLike(String placeName) {
		List<Place> returnedPlacesFilteredByName = new ArrayList<>();

		for (Place place : placesDatabase.values()) {
			if (place.getName().toLowerCase().contains(placeName.toLowerCase())) {
				returnedPlacesFilteredByName.add(place);
			}
		}
		
		return returnedPlacesFilteredByName;
	}	
	
	public Integer insertPlace(Place newPlace) {
		Integer numberOfRowsInserted = 0;
		
		if(newPlace != null) {
			newPlace.setId(++id);
			placesDatabase.put(newPlace.getId(), newPlace);
			numberOfRowsInserted = 1;
		}
		
		return numberOfRowsInserted;
	}
	
	public Place updatePlace(Place placeToBeEdited) {
		
		if(placeToBeEdited != null) {
			placesDatabase.put(placeToBeEdited.getId(), placeToBeEdited);
		}
		
		return placesDatabase.get(placeToBeEdited.getId());
	}
	
	public Place selectPlaceWhereIdEquals(Integer id){
		return placesDatabase.get(id);
	}
}
