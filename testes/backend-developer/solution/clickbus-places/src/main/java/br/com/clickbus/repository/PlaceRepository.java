package br.com.clickbus.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.clickbus.entity.Place;

public interface PlaceRepository extends CrudRepository<Place, Integer> {

}
