package br.com.clickbus.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.clickbus.controller.PlaceController;
import br.com.clickbus.entity.Place;
import br.com.clickbus.entity.PlaceTest;
import br.com.clickbus.repository.PlaceRepository;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase
class PlaceServiceTest {

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
		
		
		when(placeRepository.findAll()).thenReturn(PlaceTest.placeInAList(LocalDateTime.now()));

		List<Place> mockedPlaces = (List<Place>) placeService.findAllPlaces();
		
		assertEquals("Praça Chico Bento", mockedPlaces.get(0).getName());
		
	}
	
}
