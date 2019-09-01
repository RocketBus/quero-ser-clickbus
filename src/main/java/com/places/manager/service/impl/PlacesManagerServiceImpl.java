package com.places.manager.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.places.manager.model.Place;
import com.places.manager.repository.PlacesRepository;
import com.places.manager.service.PlacesService;

@Service
public class PlacesManagerServiceImpl implements PlacesService{
	
	@Autowired
	PlacesRepository placesRepository;
	
	@Override
	public List<Place> listPlaces() {
		List<Place> returnedPlaces = placesRepository.list();
		return returnedPlaces;
	}
	
	@Override
	public Boolean savePlace(Place place) {
		place.setCreatedAt(LocalDateTime.now());
		Boolean saved = placesRepository.save(place);
		return saved;
	}

}
