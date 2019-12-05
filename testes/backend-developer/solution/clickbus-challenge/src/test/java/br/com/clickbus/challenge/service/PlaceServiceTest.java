package br.com.clickbus.challenge.service;


import br.com.clickbus.challenge.dto.PlaceDTO;
import br.com.clickbus.challenge.entity.Place;
import br.com.clickbus.challenge.repository.PlaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith({SpringExtension.class})
public class PlaceServiceTest {

    public static final String NAME_PLACE = "Butanta";

    @Mock
    private PlaceRepository repository;

    @InjectMocks
    private PlaceService service;

    private Place place;

    @BeforeEach
    void setUp() {
        place = Place.builder(NAME_PLACE, "bt", "Sao Paulo", "SP");
    }

    @Test
    void whenFindAllThenReturnPlaces() {
        when(repository.findAll()).thenReturn(Arrays.asList(place));

        List<Place> placesFound = service.findAll();

        assertEquals(1, placesFound.size());
        assertEquals(NAME_PLACE, placesFound.get(0).getName());
        verify(repository, atLeastOnce()).findAll();
    }

    @Test
    void whenFindAllReturnListEmpty() {
        when(repository.findAll()).thenReturn(new ArrayList<>());

        List<Place> places = service.findAll();

        assertTrue(places.isEmpty());
        assertEquals(0, places.size());
        verify(repository, atLeastOnce()).findAll();
    }

    @Test
    void whenFindByIdOk() {
        when(repository.findById(1L)).thenReturn(Optional.of(place));

        Place actual = service.findById(1L).get();

        assertEquals(place.getName(), actual.getName());
        assertEquals(place.getSlug(), actual.getSlug());
        assertEquals(place.getCity(), actual.getCity());
        assertEquals(place.getState(), actual.getState());
        assertEquals(place.getCreatedAt(), actual.getCreatedAt());
        assertNull(actual.getUpdatedAt());
        verify(repository, atLeastOnce()).findById(anyLong());
    }


    @Test
    void whenFindByIdThenReturnEmpty() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertFalse(service.findById(1L).isPresent());
        verify(repository, atLeastOnce()).findById(anyLong());
    }

    @Test
    void whenFindByNameOk() {
        when(repository.findByName(NAME_PLACE)).thenReturn(Arrays.asList(place));

        List<Place> actual = service.findByName(NAME_PLACE);

        assertEquals(1, actual.size());
        verify(repository, atLeastOnce()).findByName(NAME_PLACE);
    }

    @Test
    void whenFindByNameNotFound() {
        when(repository.findByName(NAME_PLACE)).thenReturn(null);

        assertNull(service.findByName(NAME_PLACE));
        verify(repository, atLeastOnce()).findByName(NAME_PLACE);
    }

    @Test
    void whenSaveOk() {
        when(repository.save(any(Place.class))).thenReturn(place);

        Place actual = service.save(place);

        assertEquals(place, actual);
        verify(repository, atLeastOnce()).save(any(Place.class));
    }

    @Test
    public void whenAlterPlaceOk() {
        String editedName = "Lorem Ipsum";
        PlaceDTO placeDTO = PlaceDTO.builder(1L, editedName, place.getSlug(), place.getCity(), place.getState());
        when(repository.save(any(Place.class))).thenReturn(place);

        Place edited = service.alter(place, placeDTO);

        assertNotNull(edited);
        assertEquals(editedName, edited.getName());
        assertEquals(place.getSlug(), edited.getSlug());
        assertEquals(place.getCity(), edited.getCity());
        assertEquals(place.getCreatedAt(), edited.getCreatedAt());
        assertNotNull(edited.getUpdatedAt());
    }
}
