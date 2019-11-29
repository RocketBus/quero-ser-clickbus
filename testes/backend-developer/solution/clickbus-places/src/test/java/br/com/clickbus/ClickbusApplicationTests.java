package br.com.clickbus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.clickbus.controller.PlaceController;
import br.com.clickbus.entity.Place;
import br.com.clickbus.repository.PlaceRepository;
import br.com.clickbus.service.PlaceService;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase
class ClickbusApplicationTests {

	@InjectMocks
	private PlaceService placeService;
	
	@InjectMocks
	private PlaceController placeController;
	
	@Mock
	private PlaceRepository placeRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testFindAllPlaces() {
		
		Place place = new Place();
		final LocalDateTime now = LocalDateTime.now();
		place.setCreatedAt(now);
		place.setUpdatedAt(null);
		place.setName("Praça Chico Bento");
		place.setCity("São Gonçalo");
		place.setSlug("Rio Alcântara");
		place.setId(1);
		place.setState("Rio de Janeiro");
		
		when(placeRepository.findAll()).thenReturn(Arrays.asList(place));

		List<Place> mockedPlaces = (List<Place>) placeService.findAllPlaces();
		
		assertEquals("Praça Chico Bento", mockedPlaces.get(0).getName());
		assertEquals(now, mockedPlaces.get(0).getCreatedAt());
		assertEquals(null, mockedPlaces.get(0).getUpdatedAt());
		assertEquals("Rio Alcântara", mockedPlaces.get(0).getSlug());
		assertEquals(1, mockedPlaces.get(0).getId());
		assertEquals("São Gonçalo", mockedPlaces.get(0).getCity());
		assertEquals("Rio de Janeiro", mockedPlaces.get(0).getState());
		
		ResponseEntity<Iterable<Place>> findAllPlaces = placeController.findAllPlaces();
		assertEquals(HttpStatus.OK, findAllPlaces.getStatusCode());
		assertEquals("São Gonçalo", ((List<Place>) findAllPlaces.getBody()).get(0).getCity());
		
	}
	
}
