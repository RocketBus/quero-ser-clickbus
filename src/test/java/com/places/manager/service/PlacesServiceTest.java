package com.places.manager.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.places.manager.model.Place;
import com.places.manager.repository.PlacesRepository;
import com.places.manager.service.impl.PlacesServiceImpl;

public class PlacesServiceTest {
	
	private List<Place> placesList = new ArrayList<>();
	private Integer id = 0;

	@Before
	public void initialize() {
		
		Place place1 = new Place();
		place1.setId(++id);
		place1.setName("Sarah's Festival");
		place1.setSlug("California St 06");
		place1.setState("Ohio");
		place1.setCity("Columbus");
		place1.setCreatedAt(LocalDateTime.now());
		
		Place place2 = new Place();
		place2.setId(++id);
		place2.setName("Robot Museum");
		place2.setSlug("Tech St 25");
		place2.setState("Arizona");
		place2.setCity("Phoenix");
		place2.setCreatedAt(LocalDateTime.now());
		
		placesList.add(place1);
		placesList.add(place2);
	}
	
	@Test
	public void searchPlacesWithoutNameReturnAllPlaces() {
		PlacesRepository repository = mock(PlacesRepository.class);
		when(repository.list()).thenReturn(placesList);
		PlacesServiceImpl service = new PlacesServiceImpl(repository);
		List<Place> returnedPlacesList = service.listPlaces(null);
		assertThat(returnedPlacesList.size()).isEqualTo(placesList.size());
	}
}
