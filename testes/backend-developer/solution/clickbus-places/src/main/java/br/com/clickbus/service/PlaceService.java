package br.com.clickbus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clickbus.entity.Place;
import br.com.clickbus.repository.PlaceRepository;
import static br.com.clickbus.utils.SlugGenerator.toSlug;;

@Service
public class PlaceService {

	@Autowired
	private PlaceRepository placeRepository;
	
	public Iterable<Place> findAllPlaces() {
		return placeRepository.findAll();
	}
	
	public Place savePlace(Place place) {
		if (place.getSlug() == null) {
			place.setSlug(toSlug(place.getName()));
		}
		return placeRepository.save(place);
	}
	
}
