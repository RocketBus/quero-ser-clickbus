package br.com.click.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.click.entity.Place;
import br.com.click.repository.PlaceRepository;

@Service
public class PlaceService {

	@Autowired
	private PlaceRepository placeRepository;

	public Place save(Place place) {
		return placeRepository.save(place);
	}

	public List<Place> getAllPlaces(String name) {
		if (name != null) {
			return (List<Place>) placeRepository.findAll(new PlaceSpecification(new Place(name)));
		}
		return (List<Place>) placeRepository.findAll();
	}

	public List<Place> getByName(String name) {
		return placeRepository.findOneByName(name);
	}

	public Optional<Place> findById(Long id) {
		return placeRepository.findById(id);
	}

	public void deleteById(Long id) {
		placeRepository.deleteById(id);
	}

}
