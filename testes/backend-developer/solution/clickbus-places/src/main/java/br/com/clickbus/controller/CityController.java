package br.com.clickbus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clickbus.dto.CreateCityDto;
import br.com.clickbus.service.CityService;

@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private CityService cityService;
	
	@PostMapping
	public ResponseEntity<Void> saveCity(@RequestBody CreateCityDto cityDto) {
		cityService.saveCity(cityDto.buildCity());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<String> findAllCities() {
		return cityService.findAllCities();
	}
	
}
