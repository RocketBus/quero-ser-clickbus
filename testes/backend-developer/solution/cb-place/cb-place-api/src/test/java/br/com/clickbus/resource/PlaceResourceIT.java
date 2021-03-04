package br.com.clickbus.resource;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.clickbus.domain.City;
import br.com.clickbus.domain.State;
import br.com.clickbus.exception.PlaceNotFoundException;
import br.com.clickbus.model.PlaceDTO;
import br.com.clickbus.service.PlaceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Rest Integration tests for {@link PlaceResource}
 *
 * @author Danilo Bitencourt
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PlaceResourceIT {

  private final String invalidId = "00000";

  @Mock private PlaceService placeService;

  @InjectMocks private PlaceResource placeResource;

  private final ObjectMapper objectMapper = new ObjectMapper();
  private MockMvc mockMvc;
  private PlaceDTO validPlaceDTO;
  private PlaceDTO placeDTOWithoutId;

  @BeforeEach
  public void setUp() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(placeResource).build();

    City salvador = new City("Salvador");
    State bahia = new State("Bahia", "BA");

    this.validPlaceDTO =
        PlaceDTO.builder()
            .id("100")
            .name("Mercado Modelo")
            .slug("mercado-modelo")
            .city(salvador)
            .state(bahia)
            .build();
    this.placeDTOWithoutId =
        PlaceDTO.builder()
            .name("Invalid Place")
            .slug("invalid-place")
            .city(new City())
            .state(new State())
            .build();
  }

  @Test
  public void testFindAll() throws Exception {
    Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "name"));

    Page<PlaceDTO> places = new PageImpl<>(createPlaces(), pageable, 3);

    Mockito.when(placeService.findAll(pageable)).thenReturn(places);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/places?size=3&page=0&sort=name")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", Matchers.hasSize(3)))
        .andReturn();
  }

  @Test
  public void testFindOne() throws Exception {
    Mockito.when(placeService.findById(validPlaceDTO.getId())).thenReturn(validPlaceDTO);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(String.format("/api/places/%s", validPlaceDTO.getId()))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", Matchers.is(validPlaceDTO.getId())))
        .andReturn();
  }

  @Test
  public void whenFindByInvalidId_thenNotFound() throws Exception {
    Mockito.when(placeService.findById(invalidId)).thenThrow(PlaceNotFoundException.class);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(String.format("/api/places/%s", invalidId))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void testSearch() throws Exception {
    Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "name"));
    Page<PlaceDTO> places = new PageImpl<>(List.of(validPlaceDTO), pageable, 1);

    Mockito.when(placeService.search(validPlaceDTO.getName(), pageable)).thenReturn(places);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(
                    String.format(
                        "/api/places/search?searchTerm=%s&size=1&page=0&sort=name",
                        validPlaceDTO.getName()))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content[0].id", Matchers.is(validPlaceDTO.getId())))
        .andReturn();
  }

  @Test
  public void testCreatePlaceDTO() throws Exception {
    Mockito.when(placeService.save(any(PlaceDTO.class))).thenReturn(validPlaceDTO);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/places")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(placeDTOWithoutId)))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.id", Matchers.is(validPlaceDTO.getId())))
        .andReturn();
  }

  @Test
  public void whenCreateWithNullId_thenBadRequest() throws Exception {
    Mockito.when(placeService.save(placeDTOWithoutId)).thenThrow(IllegalArgumentException.class);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post(String.format("/api/places"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(placeDTOWithoutId)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testUpdatePlaceDTO() throws Exception {
    validPlaceDTO.setName("updated name");

    Mockito.when(placeService.update(eq(validPlaceDTO.getId()), any(PlaceDTO.class)))
        .thenReturn(validPlaceDTO);

    mockMvc
        .perform(
            MockMvcRequestBuilders.put(String.format("/api/places/%s", validPlaceDTO.getId()))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(validPlaceDTO)))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.name", Matchers.is(validPlaceDTO.getName())))
        .andReturn();
  }

  @Test
  public void whenUpdateWithNullContent_thenBadRequest() throws Exception {
    Mockito.when(placeService.update(eq(validPlaceDTO.getId()), isNull()))
        .thenThrow(IllegalArgumentException.class);

    mockMvc
        .perform(
            MockMvcRequestBuilders.put(String.format("/api/places/%s", validPlaceDTO.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(null)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testDeletePlaceDTO() throws Exception {
    Mockito.doNothing().when(placeService).delete(validPlaceDTO.getId());

    mockMvc
        .perform(
            MockMvcRequestBuilders.delete(String.format("/api/places/%s", validPlaceDTO.getId()))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void whenDeleteInvalidId_thenNotFound() throws Exception {
    Mockito.doThrow(PlaceNotFoundException.class).when(placeService).delete(invalidId);

    mockMvc
        .perform(
            MockMvcRequestBuilders.delete(String.format("/api/places/%s", invalidId))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  private List<PlaceDTO> createPlaces() {
    final List<PlaceDTO> places = new ArrayList<>(3);
    City salvador = new City("Salvador");
    State bahia = new State("Bahia", "BA");

    PlaceDTO pelourinho =
        PlaceDTO.builder()
            .id("1")
            .name("Pelourinho")
            .slug("pelourinho")
            .city(salvador)
            .state(bahia)
            .build();
    PlaceDTO farol =
        PlaceDTO.builder()
            .id("2")
            .name("Farol da Barra")
            .slug("farol-da-barra")
            .city(salvador)
            .state(bahia)
            .build();
    PlaceDTO bonfim =
        PlaceDTO.builder()
            .id("3")
            .name("Igreja do Bonfim")
            .slug("igreja-do-bonfim")
            .city(salvador)
            .state(bahia)
            .build();

    places.add(pelourinho);
    places.add(farol);
    places.add(bonfim);

    return places;
  }
}
