package br.com.clickbus.challenge.service;


import br.com.clickbus.challenge.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository repository;

    public List<Place> findAll() {
        return repository.findAll();
    }

    public Optional<Place> findById(Long id) {
        return repository.findById(id);
    }

    public Place save(Place place) {
        return repository.save(place);
    }

    public List<Place> findByName(String name) {
        return repository.findOneByName(name);
    }
}
