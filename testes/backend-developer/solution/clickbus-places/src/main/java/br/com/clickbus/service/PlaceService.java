package br.com.clickbus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clickbus.entity.Place;
import br.com.clickbus.repository.PlaceRepository;

@Service
public class PlaceService {

	@Autowired
	private PlaceRepository placeRepository;
	
	public Iterable<Place> findAllPlaces() {
		return placeRepository.findAll();
	}
	
}
