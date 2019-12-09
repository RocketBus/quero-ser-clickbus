package br.com.clickbus.service;

import static br.com.clickbus.utils.SlugGenerator.toSlug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clickbus.dto.CreateAndEditPlaceDto;
import br.com.clickbus.entity.Place;
import br.com.clickbus.repository.PlaceRepository;;

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
	
	public Place findPlaceBySlug(String slug) {
		return placeRepository.findBySlug(slug);
	}
	
	public Place editPlaceBySlug(CreateAndEditPlaceDto createAndEditCityDto, String currentSlug) {
		Place buildPlace = createAndEditCityDto.buildPlace();
		Place placeEntity = findPlaceBySlug(currentSlug);
		buildPlace.setId(placeEntity.getId());
		return placeRepository.save(buildPlace);
	}
	
}
