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
	
	@Override
	public Place findById(Integer id) {
		return db.selectPlaceWhereIdEquals(id);
	}

	public Boolean create(Place place) {
		Integer rowsInserted = db.insertPlace(place);
		Boolean result = rowsInserted > 0 ? true : false;
		return result;
	}

	@Override
	public Boolean edit(Place placeToBeEdited) {
		Place placeToBeSaved = db.selectPlaceWhereIdEquals(placeToBeEdited.getId());
		
		if(placeToBeSaved != null) {
			placeToBeSaved.setName(placeToBeEdited.getName());
			placeToBeSaved.setSlug(placeToBeEdited.getSlug());
			placeToBeSaved.setCity(placeToBeEdited.getCity());
			placeToBeSaved.setState(placeToBeEdited.getState());
			placeToBeSaved.setUpdatedAt(placeToBeEdited.getUpdatedAt());
		}
		
		Integer rowsUpdated = db.updatePlace(placeToBeSaved);
		Boolean result = rowsUpdated > 0 ? true : false;
		return result;
	}

}
