package com.places.manager.repository;

import java.util.List;

import com.places.manager.model.Place;

public interface PlacesRepository {

	public List<Place> list();

	public Boolean save(Place place);
	
	public Boolean edit(Place place);
}
