package com.places.manager.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.places.manager.db.PlacesDatabase;
import com.places.manager.model.Place;
import com.places.manager.repository.PlacesRepository;

@Repository
public class PlacesRepositoryImpl implements PlacesRepository {
	
	@Autowired
	PlacesDatabase db;
	
	public List<Place> list() {
		return db.selectAll();
	}
	
	@Override
	public List<Place> listByName(String placeName) {		
		return db.selectAllPlacesWherePlaceNameLike(placeName);
	}

	public Boolean create(Place place) {
		return db.insertPlace(place);
	}

	@Override
	public Boolean edit(Place placeToBeEdited) {
		return db.updatePlace(placeToBeEdited);
	}
}
