package br.com.click.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.click.entity.Place;
import br.com.click.service.PlaceService;

@RunWith(SpringRunner.class)
@WebMvcTest(PlaceController.class)
public class PlaceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PlaceService service;

	@Test
	public void WhenGetAllPlacesWithParam() throws Exception {
		Place place = new Place("name", "slug", "state", "city");
		given(service.getAllPlaces("name")).willReturn(Arrays.asList(place));

		mockMvc.perform(get("/place?search=name").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].name", is(place.getName())))
				.andExpect(jsonPath("$[0].slug", is(place.getSlug())))
				.andExpect(jsonPath("$[0].state", is(place.getState())))
				.andExpect(jsonPath("$[0].city", is(place.getCity())));
	}
	
	
	@Test
	public void WhenGetAllPlacesWithParamNull() throws Exception {
		Place place1 = new Place("name1", "slug1", "state1", "city1");
		Place place2 = new Place("name2", "slug2", "state2", "city2");
		given(service.getAllPlaces(null)).willReturn(Arrays.asList(place1, place2));

		mockMvc.perform(get("/place").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].name", is(place1.getName())))
				.andExpect(jsonPath("$[0].slug", is(place1.getSlug())))
				.andExpect(jsonPath("$[0].state", is(place1.getState())))
				.andExpect(jsonPath("$[0].city", is(place1.getCity())));
	}
	
	@Test
	public void WhenGetAllPlacesReturnEmpty() throws Exception {
		given(service.getAllPlaces(null)).willReturn(new ArrayList<Place>());

		mockMvc.perform(get("/place").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}
	
	@Test
	public void WhenGetByNameReturnPlace() throws Exception {
		Place place = new Place("name", "slug", "state", "city");
		given(service.getByName("name")).willReturn(Arrays.asList(place));

		mockMvc.perform(get("/place/getByName/name").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].name", is(place.getName())))
				.andExpect(jsonPath("$[0].slug", is(place.getSlug())))
				.andExpect(jsonPath("$[0].state", is(place.getState())))
				.andExpect(jsonPath("$[0].city", is(place.getCity())));
	}
	
	@Test
	public void WhenGetByNameReturnNotFound() throws Exception {
		given(service.getByName("name")).willReturn(new ArrayList<Place>());

		mockMvc.perform(get("/place/getByName/name").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}
	
	@Test
	public void WhenGetByIdReturnPlace() throws Exception {
		Optional<Place> optional = Optional.of(new Place("name", "slug", "state", "city"));
		given(service.findById(1L)).willReturn(optional);

		mockMvc.perform(get("/place/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is(optional.get().getName())))
				.andExpect(jsonPath("$.slug", is(optional.get().getSlug())))
				.andExpect(jsonPath("$.state", is(optional.get().getState())))
				.andExpect(jsonPath("$.city", is(optional.get().getCity())));
	}
	
	@Test
	public void WhenGetByIdReturnNotFound() throws Exception {
		given(service.findById(1L)).willReturn(Optional.empty());

		mockMvc.perform(get("/place/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	
	@Test
	public void WhenSavePlaceOk() {
		Place place = new Place("name", "slug", "state", "city");
		given(service.save(place)).willReturn(place);

		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(place);

			mockMvc.perform(post(new URI("/place")).contentType(MediaType.APPLICATION_JSON).content(json))
					.andExpect(status().isCreated());
		} catch (JsonProcessingException e) {
			fail(e.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void WhenEditPlaceNotFound() {
		Place place = new Place("name", "slug", "state", "city");
		place.setId(10L);
		given(service.save(place)).willReturn(place);

		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(place);

			mockMvc.perform(put(new URI("/place/1")).contentType(MediaType.APPLICATION_JSON).content(json))
					.andExpect(status().isNotFound());
		} catch (JsonProcessingException e) {
			fail(e.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void WhenDeletePlaceNotFound() {
		try {
			mockMvc.perform(delete(new URI("/place/1")).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isNotFound());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
}
