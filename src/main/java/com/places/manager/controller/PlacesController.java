package com.places.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.places.manager.model.Place;
import com.places.manager.service.PlacesService;

@RestController
@RequestMapping("/places")
public class PlacesController {
	
	@Autowired
	PlacesService placesService;

	@GetMapping
	public ResponseEntity<List<Place>> listPlaces() {
		List<Place> places = placesService.listPlaces();
		return ResponseEntity.ok(places);
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
