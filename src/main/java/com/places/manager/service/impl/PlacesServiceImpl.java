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
		place.setCreatedAt(LocalDateTime.now());
		Boolean saved = placesRepository.create(place);
		return saved;
	}

	@Override
	public Boolean editPlace(Place placeToBeEdited) {
		placeToBeEdited.setUpdatedAt(LocalDateTime.now());
		Boolean edited = placesRepository.edit(placeToBeEdited);
		return edited;
	}

}
