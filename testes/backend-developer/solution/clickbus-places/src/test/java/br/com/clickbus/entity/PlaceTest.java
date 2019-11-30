package br.com.clickbus.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class PlaceTest {

	private List<Place> places;
	
	LocalDateTime now = LocalDateTime.now();
	
	public static List<Place> placeInAList(final LocalDateTime now) {
		Place place = new Place();
		place.setCreatedAt(now);
		place.setUpdatedAt(null);
		place.setName("Praça Chico Bento");
		City city = new City();
		city.setCity("São Gonçalo");
		place.setCity(city);
		place.setSlug("Rio Alcântara");
		place.setId(1);
		List<Place> places = new ArrayList<>();
		places.add(place);
		return places;
	}

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		places = placeInAList(now);
	}
	
	@Test
	public void testGetters() {
		assertEquals("Praça Chico Bento", places.get(0).getName());
		assertEquals(now, places.get(0).getCreatedAt());
		assertEquals(null, places.get(0).getUpdatedAt());
		assertEquals("Rio Alcântara", places.get(0).getSlug());
		assertEquals(1, places.get(0).getId());
		assertEquals("São Gonçalo", places.get(0).getCity().getCity());
	}

}
