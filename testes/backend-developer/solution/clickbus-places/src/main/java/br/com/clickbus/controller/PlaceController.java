package br.com.clickbus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clickbus.entity.Place;
import br.com.clickbus.repository.PlaceRepository;

@RestController
public class PlaceController {

	@Autowired
	private PlaceRepository placeRepository;
	
	@GetMapping("/places")
	public ResponseEntity<Iterable<Place>> findAllPlaces() {
		return new ResponseEntity<Iterable<Place>>(placeRepository.findAll(), HttpStatus.OK);
	}
	
}
