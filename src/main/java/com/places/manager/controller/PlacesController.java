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
	public ResponseEntity<PlacesResponse<Place>> listPlaces(
			@RequestParam(value = "name", required = false) String placeName) {
		List<Place> places = placesService.listPlaces(placeName);
		
		PlacesResponse<Place> genericResponse = new PlacesResponse<>();
		genericResponse.setPlaces(places);
		return ResponseEntity.ok(genericResponse);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PlacesResponse<Place>> findPlaceById(@PathVariable("id") Integer id) {
		Place place = placesService.findPlaceById(id);
		
		PlacesResponse<Place> genericResponse = new PlacesResponse<>();
		genericResponse.setPlace(place);
		return ResponseEntity.ok(genericResponse);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PlacesResponse<Place>> createPlace(@RequestBody Place placeToBeCreated) {
		Boolean isPlaceSaved = placesService.createPlace(placeToBeCreated);
		String message = isPlaceSaved ? "Place Saved Successfully!" : "Error trying to create Place. Name, City and State are required fields";
		
		PlacesResponse<Place> genericResponse = new PlacesResponse<>();
		genericResponse.setMessage(message);
		return ResponseEntity.ok(genericResponse);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PlacesResponse<Place>> editPlace(@RequestBody Place placeToBeEdited) {
		Boolean isPlaceEdited = placesService.editPlace(placeToBeEdited);
		
		String message = isPlaceEdited ? "Place Edited Successfully!" : "Error trying edit Place: Name, City and State are required fields";
		PlacesResponse<Place> genericResponse = new PlacesResponse<>();
		genericResponse.setMessage(message);
		return ResponseEntity.ok(genericResponse);
	}
	
}
