package com.clickbus.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clickbus.challenge.request.Place;
import com.clickbus.challenge.service.PlaceService;

@RestController
public class PlacesController {

    @Autowired
    private PlaceService placeService;

    @RequestMapping(value = "/createPlace", method = RequestMethod.POST)
    public ResponseEntity<Place> place(@RequestBody Place place) {
        Place newPlace = placeService.createPlace(place);
        return new ResponseEntity<Place>(newPlace, new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getAllPlaces", method = RequestMethod.GET)
    public ResponseEntity<List<Place>> getAllPlaces() {
        return new ResponseEntity<List<Place>>(placeService.getAll(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getPlace", method = RequestMethod.GET)
    public ResponseEntity<Place> getPlace(@RequestParam String placeName) {
        return new ResponseEntity<Place> (placeService.getByPlaceName(placeName), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/editPlace/{placeName}", method = RequestMethod.PUT)
    public ResponseEntity<Place> editPlace(@PathVariable("placeName") String placeName, @RequestBody Place changes) {
        return new ResponseEntity<Place> (placeService.editPlace(placeName, changes), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteAllPlaces", method = RequestMethod.DELETE)
    public HttpStatus deleteAllPlaces() {
         placeService.deleteAll();
         return HttpStatus.OK;
    }

    @RequestMapping(value = "/deletePlace", method = RequestMethod.DELETE)
    public HttpStatus deletePlace(@RequestParam String placeName) {
        placeService.delete(placeName);
        return HttpStatus.OK;

    }
}
