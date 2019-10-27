package br.com.click.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.click.dto.PlaceDTO;
import br.com.click.entity.Place;
import br.com.click.exception.PlaceNotFoundException;
import br.com.click.service.PlaceService;
import io.swagger.annotations.Api;

@RestController
@Api(value = "Place")
@RequestMapping("/place")
public class PlaceController {

	@Autowired
	private PlaceService placeService;

	@PostMapping
	public ResponseEntity<Place> addPlace(@RequestBody @Valid PlaceDTO dto) {
		Place place = placeService.save(new Place(dto));
		return new ResponseEntity<>(place, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Place> editPlace(@PathVariable("id") Long id, @RequestBody @Valid PlaceDTO placeDTO) {
		return placeService.findById(id).map(record -> {
			record.setName(placeDTO.getName());
			record.setSlug(placeDTO.getSlug());
			record.setState(placeDTO.getState());
			record.setDtUpdate(LocalDateTime.now());
			Place place = placeService.save(record);
			return ResponseEntity.ok().body(place);
		}).orElseThrow(() -> new PlaceNotFoundException("Place id '" + id + "' nao encontrado"));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Place> getById(@PathVariable Long id) {
		return placeService.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElseThrow(() -> new PlaceNotFoundException("Place id '" + id + "' nao encontrado"));
	}
	
	@GetMapping("/getByName/{name}")
	public ResponseEntity<List<Place>> getByName(@PathVariable String name) {
		return ResponseEntity.ok(placeService.getByName(name));
	}

	@GetMapping
	public ResponseEntity<List<Place>> getAllPlaces(@RequestParam(value = "search", required = false) String name) {
		return ResponseEntity.ok(placeService.getAllPlaces(name));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		return placeService.findById(id).map(record -> {
			placeService.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new PlaceNotFoundException("Place id '" + id + "' nao encontrado"));
	}
	
}
