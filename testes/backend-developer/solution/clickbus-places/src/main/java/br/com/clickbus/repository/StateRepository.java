package br.com.clickbus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.clickbus.entity.State;

@Repository
public interface StateRepository extends CrudRepository<State, Integer> {

	public List<State> findAll();

}
