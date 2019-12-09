package br.com.clickbus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clickbus.dto.CreateAndEditPlaceDto;
import br.com.clickbus.entity.Place;
import br.com.clickbus.service.PlaceService;

@RestController
@RequestMapping("/places")
public class PlaceController {

	@Autowired
	private PlaceService placeService;
	
	@GetMapping
	public ResponseEntity<Iterable<Place>> findAllPlaces() {
		return new ResponseEntity<Iterable<Place>>(placeService.findAllPlaces(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Place> savePlace(@RequestBody CreateAndEditPlaceDto placeDto) {
		return new ResponseEntity<Place>(placeService.savePlace(placeDto.buildPlace()), HttpStatus.CREATED);
	}
	
	@PutMapping("/{slug}")
	public ResponseEntity<Place> editPlace(@RequestBody CreateAndEditPlaceDto placeDto, @PathVariable String slug) {
		return new ResponseEntity<Place>(placeService.editPlaceBySlug(placeDto, slug), HttpStatus.NO_CONTENT);
	}
	
}
