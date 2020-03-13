package com.clickbus.challenge.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickbus.challenge.exceptions.PlacesServiceException;
import com.clickbus.challenge.repository.PlaceRepository;
import com.clickbus.challenge.request.Place;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepository repository;

    public Place createPlace(Place place) {
        if (repository.findByName(place.getName()) != null) {
          throw new PlacesServiceException("This place already exists");
      }
      else {
          place.setCreatedAt(new Date());
          place.setUpdatedAt(new Date());
          return repository.save(place);
      }
    }

    public List<Place> getAll(){
        List<Place> list = repository.findAll();
        list.sort((Place s1, Place s2)->s1.getName().compareTo(s2.getName()));
        return list;
    }

    public Place getByPlaceName(String placeName) {
        if(repository.findByName(placeName) != null) {
        return repository.findByName(placeName);
        }
        else {
            throw new PlacesServiceException("Invalid place");
        }
    }

    public Place editPlace(String placeName, Place updates) {
        Place changes = repository.findByName(placeName);
        if(changes != null) {
        changes.setSlug(updates.getSlug());
        changes.setCity(updates.getCity());
        changes.setState(updates.getState());
        changes.setUpdatedAt(new Date());
        return repository.save(changes);
        } else {
            throw new PlacesServiceException("Invalid place");
        }
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void delete(String placeName) {
        Place del = repository.findByName(placeName);
        if(del != null) {
            repository.delete(del);
        } else {
            throw new PlacesServiceException("Invalid place");
        }
    }
}
