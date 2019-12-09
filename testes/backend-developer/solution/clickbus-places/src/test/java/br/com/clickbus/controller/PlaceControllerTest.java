package br.com.clickbus.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.clickbus.entity.Place;
import br.com.clickbus.entity.PlaceTest;
import br.com.clickbus.service.PlaceService;

public class PlaceControllerTest {

	@InjectMocks
	private PlaceController placeController;
	
	@Mock
	private PlaceService placeService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testFindAllPlaces() {
		
		when(placeService.findAllPlaces()).thenReturn(PlaceTest.placeInAList(LocalDateTime.now()));

		ResponseEntity<Iterable<Place>> findAllPlaces = placeController.findAllPlaces();
		assertEquals(HttpStatus.OK, findAllPlaces.getStatusCode());
		assertEquals("São Gonçalo", ((List<Place>) findAllPlaces.getBody()).get(0).getCity().getCity());
		
	}
	
}
