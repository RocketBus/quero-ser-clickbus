package com.places.manager.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.places.manager.model.Place;
import com.places.manager.repository.PlacesRepository;
import com.places.manager.service.PlacesService;

@Service
public class PlacesServiceImpl implements PlacesService{
	
	@Autowired
	PlacesRepository placesRepository;
	
	public PlacesServiceImpl() {}

	// Included constructors to test purpose: The spring's context shared by the tests was not desired 
	public PlacesServiceImpl(PlacesRepository repository) {
		this.placesRepository = repository;
	}
	
	@Override
	public List<Place> listPlaces(String placeName) {
		List<Place> returnedPlaces = (placeName == null || placeName.isEmpty()) ? 
				placesRepository.list() : placesRepository.listByName(placeName) ;
		return returnedPlaces;
	}
	
	@Override
	public Place findPlaceById(Integer id) {
		Place returnedPlace = placesRepository.findById(id);
		return returnedPlace;
	}	
	
	@Override
	public Boolean createPlace(Place place) {
		if(validatePlace(place)) {
			place.setCreatedAt(LocalDateTime.now());
			return placesRepository.create(place);
		}
		return false;
	}

	@Override
	public Boolean editPlace(Place placeToBeEdited) {
		if(validatePlace(placeToBeEdited)) {
		placeToBeEdited.setUpdatedAt(LocalDateTime.now());
		return placesRepository.edit(placeToBeEdited);
		}
		return false;
	}
	
	private boolean validatePlace(Place place) {
		
		if((place != null) && 
				place.getName()  != null && !place.getName().trim().isEmpty() &&
			    place.getCity()  != null && !place.getCity().trim().isEmpty() &&
			    place.getState() != null && !place.getState().trim().isEmpty()) {
			return true;
		}
		
		return false;
	}

}
