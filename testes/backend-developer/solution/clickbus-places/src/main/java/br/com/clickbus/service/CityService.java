package br.com.clickbus.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clickbus.dto.ReturnCityDto;
import br.com.clickbus.entity.City;
import br.com.clickbus.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	public City saveCity(City city) {
		return cityRepository.save(city);
	}
	
	public List<ReturnCityDto> findAllCities() {
		return cityRepository.findAll().stream()
			.map(city -> {
				return new ReturnCityDto(city.getCity(), city.getState().getState());
			})
			.collect(Collectors.toList());
	}
	
}
