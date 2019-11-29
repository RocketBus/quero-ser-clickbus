package br.com.clickbus.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.clickbus.entity.Place;

@Repository
public interface PlaceRepository extends CrudRepository<Place, Integer> {

}
