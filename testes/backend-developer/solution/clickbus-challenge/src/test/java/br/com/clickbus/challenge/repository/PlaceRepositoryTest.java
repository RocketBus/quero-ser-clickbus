package br.com.clickbus.challenge.repository;


import br.com.clickbus.challenge.entity.Place;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PlaceRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PlaceRepository repository;

    private Place place;

    @BeforeEach
    void setUp() {
        place = Place.builder("Butanta", "bt", "Sao Paulo", "SP");
        testEntityManager.persist(place);
    }

    @Test
    public void whenFindAllReturnAllItems() {
        List<Place> actual = repository.findAll();

        assertEquals("Butanta", actual.get(0).getName());
    }

    @Test
    public void whenFindByIdReturnPlace() {
        Place placeSaved = repository.save(place);
        Optional<Place> actual = repository.findById(placeSaved.getId());

        assertTrue(actual.isPresent());
    }

    @Test
    public void whenFindByIdReturnEmpty() {
        Optional<Place> actual = repository.findById(100L);

        assertFalse(actual.isPresent());
    }


    @Test
    public void whenFindByNameReturnEmpty() {
        List<Place> places = repository.findByName("Cotia");
        assertTrue(places.isEmpty());
    }

    @Test
    public void whenFindByNameReturnPlaces() {
        List<Place> places = repository.findByName("Butanta");

        assertFalse(places.isEmpty());
    }


    @Test
    public void whenTryToSavePlaceWithAttributesNull(){
        Place place = new Place();

        Assertions.assertThrows(ConstraintViolationException.class, ()->{
            repository.save(place);
        });
    }

    @Test
    public void whenSaveOk(){
        Place actual = repository.save(this.place);

        assertNotNull(actual.getId());
        assertEquals(place.getName(), actual.getName());
        assertEquals(place.getSlug(), actual.getSlug());
        assertEquals(place.getState(), actual.getState());
        assertEquals(place.getCreatedAt(), actual.getCreatedAt());
        assertNull(actual.getUpdatedAt());
    }
}
