package com.places.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.places.manager.model.Place;
import com.places.manager.service.PlacesService;

@RestController
@RequestMapping("/places")
public class PlacesController {
	
	@Autowired
	PlacesService placesService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Place>> listPlaces(
			@RequestParam(value = "name", required = false) String placeName) {
		List<Place> places = placesService.listPlaces(placeName);
		return ResponseEntity.ok(places);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Place> findPlaceById(@PathVariable("id") Integer id) {
		Place place = placesService.findPlaceById(id);
		return ResponseEntity.ok(place);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> createPlace(@RequestBody Place placeToBeCreated) {
		placesService.createPlace(placeToBeCreated);
		return ResponseEntity.ok("Place Saved Successfully!");
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> editPlace(@RequestBody Place placeToBeEdited) {
		placesService.editPlace(placeToBeEdited);
		return ResponseEntity.ok("Place Edited Successfully!");
	}
	
}
