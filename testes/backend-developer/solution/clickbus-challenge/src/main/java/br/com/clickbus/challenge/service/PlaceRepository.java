package br.com.clickbus.challenge.service;

import br.com.clickbus.challenge.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findOneByName(String name);
}
