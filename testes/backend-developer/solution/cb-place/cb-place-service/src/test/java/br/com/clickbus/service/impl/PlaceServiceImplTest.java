package br.com.clickbus.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

import br.com.clickbus.domain.City;
import br.com.clickbus.domain.Place;
import br.com.clickbus.domain.State;
import br.com.clickbus.exception.PlaceNotFoundException;
import br.com.clickbus.mapper.PlaceMapper;
import br.com.clickbus.model.PlaceDTO;
import br.com.clickbus.repository.PlaceRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/** @author Danilo Bitencourt */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PlaceServiceImplTest {

  private final String invalidId = "00000";

  @Mock private PlaceRepository placeRepository;

  private PlaceMapper placeMapper;

  @InjectMocks private PlaceServiceImpl placeService;

  private Place validPlace;
  private PlaceDTO validPlaceDTO;

  @BeforeEach
  void setUp() {
    placeMapper = Mockito.spy(new PlaceMapper(new ModelMapper()));
    placeService = new PlaceServiceImpl(placeRepository, placeMapper);

    City salvador = new City("Salvador");
    State bahia = new State("Bahia", "BA");

    this.validPlace =
        new Place("100", "Mercado Modelo", "mercado-modelo", salvador, bahia, null, null);
    this.validPlaceDTO =
        PlaceDTO.builder()
            .id("100")
            .name("Mercado Modelo")
            .slug("mercado-modelo")
            .city(salvador)
            .state(bahia)
            .build();
  }

  @Test
  public void testFindAll() {
    Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "name"));
    Page<Place> places = createPlaces(pageable);

    Mockito.when(placeRepository.findAll(pageable)).thenReturn(places);
    Mockito.doCallRealMethod().when(placeMapper).convertToDTO(places);

    Page<PlaceDTO> result = placeService.findAll(pageable);

    assertNotNull(result.getContent());
    assertEquals(result.getTotalElements(), places.getTotalElements());
    assertEquals(result.getSize(), places.getSize());
    assertEquals(result.getNumber(), places.getNumber());
    assertEquals(result.getSort(), places.getSort());
  }

  @Test
  public void testFindById() {
    Mockito.when(placeRepository.findById(validPlace.getId())).thenReturn(Optional.of(validPlace));
    Mockito.doCallRealMethod().when(placeMapper).convertToDTO(any(Place.class));

    PlaceDTO placeDTO = placeService.findById(validPlace.getId());

    assertNotNull(placeDTO);
    assertEquals(placeDTO.getId(), validPlace.getId());
  }

  @Test
  public void whenFindByInvalidId_thenThrowPlaceNotFoundException() {
    Mockito.when(placeRepository.findById(invalidId)).thenReturn(Optional.ofNullable(null));

    Assertions.assertThrows(
        PlaceNotFoundException.class,
        () -> {
          placeService.findById(invalidId);
        });
  }

  @Test
  public void testSearch() {
    Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "name"));
    Page<Place> places = createPlaces(pageable);

    Mockito.when(placeRepository.search(eq(validPlace.getName()), eq(pageable))).thenReturn(places);
    Mockito.doCallRealMethod().when(placeMapper).convertToDTO(any(Place.class));

    Page<PlaceDTO> result = placeService.search(validPlace.getName(), pageable);

    assertNotNull(result.getContent());
    assertEquals(result.getTotalElements(), places.getTotalElements());
    assertEquals(result.getSize(), places.getSize());
    assertEquals(result.getNumber(), places.getNumber());
    assertEquals(result.getSort(), places.getSort());
  }

  /** Test of save method, of class PlaceServiceImpl. */
  @Test
  public void testSave() {
    Mockito.when(placeRepository.save(any(Place.class))).thenReturn(validPlace);
    Mockito.doCallRealMethod().when(placeMapper).convertToDTO(any(Place.class));
    Mockito.doCallRealMethod().when(placeMapper).convertToEntity(any(PlaceDTO.class));

    validPlaceDTO.setId(null);
    PlaceDTO placeDTO = placeService.save(validPlaceDTO);

    assertNotNull(placeDTO);
    assertEquals(placeDTO.getId(), validPlace.getId());
  }

  @Test
  public void whenSaveWithId_thenThrowIllegalArgumentException() {
    validPlaceDTO.setId(null);

    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          placeService.save(validPlaceDTO);
        });
  }

  @Test
  public void testUpdate() {
    Mockito.when(placeRepository.findById(validPlace.getId())).thenReturn(Optional.of(validPlace));
    validPlace.setName("Name updated");
    Mockito.when(placeRepository.save(any(Place.class))).thenReturn(validPlace);

    Mockito.doCallRealMethod().when(placeMapper).convertToDTO(any(Place.class));

    PlaceDTO placeDTO = placeService.update(validPlaceDTO.getId(), validPlaceDTO);

    assertNotNull(placeDTO);
    assertEquals(placeDTO.getId(), validPlace.getId());
    assertEquals(placeDTO.getName(), validPlace.getName());
  }

  @Test
  public void whenUpdateWithoutId_thenThrowIllegalArgumentException() {
    Mockito.when(placeRepository.findById(invalidId)).thenReturn(Optional.ofNullable(null));

    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          placeService.update(null, validPlaceDTO);
        });
  }

  @Test
  public void whenDeleteInvalidId_thenThrowPlaceNotFoundException() {
    Mockito.when(placeRepository.findById(invalidId)).thenReturn(Optional.ofNullable(null));

    Assertions.assertThrows(
        PlaceNotFoundException.class,
        () -> {
          placeService.delete(invalidId);
        });
  }

  private Page<Place> createPlaces(Pageable pageable) {
    final List<Place> places = new ArrayList<>(3);
    City salvador = new City("Salvador");
    State bahia = new State("Bahia", "BA");

    Place pelourinho = new Place("1", "Pelourinho", "pelourinho", salvador, bahia, null, null);
    Place farol = new Place("2", "Farol da Barra", "farol-da-barra", salvador, bahia, null, null);
    Place bonfim =
        new Place("3", "Igreja do Bonfim", "igreja-do-bonfim", salvador, bahia, null, null);

    places.add(pelourinho);
    places.add(farol);
    places.add(bonfim);

    return new PageImpl<>(places, pageable, places.size());
  }
}
