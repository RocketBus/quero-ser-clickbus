package br.com.clickbus.repository;

import br.com.clickbus.domain.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Interface to define the Place Repository Behavior
 *
 * @author Danilo Bitencourt
 */
public interface PlaceRepository extends MongoRepository<Place, String> {

  @Query("{ 'name': { '$regex' : ?0, $options: 'i'}}")
  Page<Place> search(String term, Pageable pageable);
}
