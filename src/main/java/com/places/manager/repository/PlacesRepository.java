package com.places.manager.repository;

import java.util.List;

import com.places.manager.model.Place;

public interface PlacesRepository {

	public List<Place> list();
	
	public List<Place> listByName(String placeName);

	public Boolean create(Place placeToBeCreated);
	
	public Boolean edit(Place placeToBeEdited);

	public Place findById(Integer id);
}
