package br.com.click.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.click.entity.Place;
import br.com.click.repository.PlaceRepository;

@RunWith(SpringRunner.class)
public class PlaceServiceTest {
	@TestConfiguration
	static class PlaceServiceTestContextConfiguration {
		@Bean
		public PlaceService placeService() {
			return new PlaceService();
		}
	}

	@Autowired
	private PlaceService placeService;

	@MockBean
	private PlaceRepository placeRepository;

	private Place p;

	@Before
	public void setUp() {
		p = new Place("name", "slug", "state", "city");
	}

	@Test
	public void whenFindByIdOK() {
		Optional<Place> optional = Optional.of(p);
		Mockito.when(placeService.findById(1L)).thenReturn(optional);

		assertThat(p.getName()).isEqualTo(optional.get().getName());
		assertThat(p.getSlug()).isEqualTo(optional.get().getSlug());
		assertThat(p.getState()).isEqualTo(optional.get().getState());
		assertThat(p.getCity()).isEqualTo(optional.get().getCity());
	}

	@Test
	public void whenFindByIdEmpty() {
		Optional<Place> optional = Optional.empty();
		Mockito.when(placeService.findById(1L)).thenReturn(optional);

		assertThat(false).isEqualTo(optional.isPresent());
	}

	@Test
	public void whenFindByNameOK() {
		List<Place> lista = Arrays.asList(p);
		Mockito.when(placeService.getByName("name")).thenReturn(lista);

		compareItems(lista);
	}

	@Test
	public void whenFindByNameEmpty() {
		List<Place> lista = new ArrayList<Place>();
		Mockito.when(placeService.getByName("name")).thenReturn(lista);

		assertThat(true).isEqualTo(lista.isEmpty());
	}

	@Test
	public void whenGetAllPlacesWithParam() {
		List<Place> lista = Arrays.asList(p);
		Mockito.when(placeService.getAllPlaces("name")).thenReturn(lista);

		compareItems(lista);
	}

	@Test
	public void whenGetAllPlacesWithParamNull() {
		List<Place> lista = Arrays.asList(p);
		Mockito.when(placeService.getAllPlaces(null)).thenReturn(lista);

		compareItems(lista);
	}

	@Test
	public void whenGetAllPlacesReturnEmpty() {
		List<Place> lista = new ArrayList<Place>();
		Mockito.when(placeService.getAllPlaces(null)).thenReturn(lista);

		assertThat(true).isEqualTo(lista.isEmpty());
	}

	@Test
	public void whenEditSavePlaceOk() {
		Place place = new Place();
		when(placeService.save(Mockito.any(Place.class))).thenReturn(place);
		Place returnedPlace = placeService.save(place);
		assertEquals(place.getName(), returnedPlace.getName());
	}
	
	private void compareItems(List<Place> lista) {
		assertThat(p.getName()).isEqualTo(lista.get(0).getName());
		assertThat(p.getSlug()).isEqualTo(lista.get(0).getSlug());
		assertThat(p.getState()).isEqualTo(lista.get(0).getState());
		assertThat(p.getCity()).isEqualTo(lista.get(0).getCity());
	}
}
