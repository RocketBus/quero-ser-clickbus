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
		festival.setName("Sarah's Festival");
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
		
		placesDatabase.put(++id, festival);
		placesDatabase.put(++id, museum);
		placesDatabase.put(++id, hotel);
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
	
	public Boolean insertPlace(Place newPlace) {
		placesDatabase.put(++id, newPlace);
		return true;
	}
	
	public Boolean updatePlace(Place placeToBeEdited) {
		if(placesDatabase.containsKey(placeToBeEdited.getId())) {
			placesDatabase.put(placeToBeEdited.getId(), placeToBeEdited);
			return true;
		}
		return false;
	}
}
