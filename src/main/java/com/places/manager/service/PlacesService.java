package com.places.manager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.places.manager.model.Place;

@Service
public interface PlacesService {

	List<Place> listPlaces(String placeName);

	Boolean createPlace(Place placeToBeCreated);
	
	Boolean editPlace(Place placeToBeEdited);

	Place findPlaceById(Integer id);
}
