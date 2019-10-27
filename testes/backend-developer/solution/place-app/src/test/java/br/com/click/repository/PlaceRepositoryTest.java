package br.com.click.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.click.dto.PlaceDTO;
import br.com.click.entity.Place;
import br.com.click.service.PlaceSpecification;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PlaceRepositoryTest {
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private PlaceRepository placeRepository;

	@Test
	public void whenFindByIdThenReturnPlace() {
		PlaceDTO dto = new PlaceDTO("name", "slu", "st", "city");
		entityManager.persist(new Place(dto));
		entityManager.flush();

		Optional<Place> place = placeRepository.findById(3L);

		assertThat(place.get().getName()).isEqualTo(dto.getName());
		assertThat(place.get().getSlug()).isEqualTo(dto.getSlug());
		assertThat(place.get().getState()).isEqualTo(dto.getState());
		assertThat(place.get().getCity()).isEqualTo(dto.getCity());
	}

	@Test
	public void whenFindByIdThenReturnEmpty() {
		Optional<Place> place = placeRepository.findById(2L);
		assertThat(place.isPresent()).isEqualTo(false);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void whenFindByIdNull() {
		placeRepository.findById(null);
		fail("Teste falhou!!");
	}

	@Test
	public void whenFindByNameThenReturnPlace() {
		PlaceDTO dto = new PlaceDTO("teste", "slug", "state", "city");
		entityManager.persist(new Place(dto));
		entityManager.flush();

		List<Place> listPlace = placeRepository.findOneByName(dto.getName());

		extracted(dto, listPlace);
	}

	public void extracted(PlaceDTO dto, List<Place> listPlace) {
		assertThat(listPlace.get(0).getName()).isEqualTo(dto.getName());
		assertThat(listPlace.get(0).getSlug()).isEqualTo(dto.getSlug());
		assertThat(listPlace.get(0).getState()).isEqualTo(dto.getState());
		assertThat(listPlace.get(0).getCity()).isEqualTo(dto.getCity());
	}

	@Test
	public void whenFindByNameThenReturnEmpty() {
		List<Place> listPlace = placeRepository.findOneByName("name");
		assertThat(listPlace.isEmpty()).isEqualTo(true);
	}

	@Test
	public void whenFindByNameNull() {
		List<Place> listPlace = placeRepository.findOneByName(null);
		assertThat(listPlace.isEmpty()).isEqualTo(true);
	}

	@Test
	public void whenFindByNameEmpty() {
		List<Place> listPlace = placeRepository.findOneByName("");
		assertThat(listPlace.isEmpty()).isEqualTo(true);
	}

	@Test
	public void whenFindAllThenReturnPlaces() {
		PlaceDTO dto = new PlaceDTO("teste1", "slug", "state", "city");
		entityManager.persist(new Place(dto));
		entityManager.flush();

		List<Place> listPlace = placeRepository.findAll();

		assertThat(listPlace.get(0).getName()).isEqualTo(dto.getName());
		assertThat(listPlace.get(0).getSlug()).isEqualTo(dto.getSlug());
		assertThat(listPlace.get(0).getState()).isEqualTo(dto.getState());
		assertThat(listPlace.get(0).getCity()).isEqualTo(dto.getCity());
	}

	@Test
	public void whenFindAllWithParamThenReturnPlaces() {
		PlaceDTO dto = new PlaceDTO("teste2", "slug", "state", "city");
		entityManager.persist(new Place(dto));
		entityManager.flush();

		List<Place> listPlace = placeRepository.findAll(new PlaceSpecification(new Place("teste2")));

		assertThat(listPlace.get(0).getName()).isEqualTo(dto.getName());
		assertThat(listPlace.get(0).getSlug()).isEqualTo(dto.getSlug());
		assertThat(listPlace.get(0).getState()).isEqualTo(dto.getState());
		assertThat(listPlace.get(0).getCity()).isEqualTo(dto.getCity());
	}

	@Test
	public void whenSaveThenReturnPlace() {
		PlaceDTO dto = new PlaceDTO("teste3", "slug", "state", "city");
		Place retorno = placeRepository.save(new Place(dto));

		Optional<Place> place = placeRepository.findById(retorno.getId());
		assertThat(place.get().getName()).isEqualTo(dto.getName());
		assertThat(place.get().getSlug()).isEqualTo(dto.getSlug());
		assertThat(place.get().getState()).isEqualTo(dto.getState());
		assertThat(place.get().getCity()).isEqualTo(dto.getCity());

		placeRepository.deleteById(retorno.getId());

	}

	@Test
	public void whenDeleteWithValue() {
		PlaceDTO dto = new PlaceDTO("teste4", "slug", "state", "city");
		Place retorno = placeRepository.save(new Place(dto));

		Optional<Place> place = placeRepository.findById(retorno.getId());
		assertThat(place.get().getName()).isEqualTo(dto.getName());

		placeRepository.deleteById(retorno.getId());
		Optional<Place> delete = placeRepository.findById(retorno.getId());
		assertThat(delete.isPresent()).isEqualTo(false);
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void whenDeleteWithOutValue() {
		placeRepository.deleteById(10L);
		Optional<Place> delete = placeRepository.findById(10L);
		assertThat(delete.isPresent()).isEqualTo(true);
	}
	
}
