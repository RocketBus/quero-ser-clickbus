package br.com.clickbus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.clickbus.entity.City;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

	public List<City> findAll();
	
}
