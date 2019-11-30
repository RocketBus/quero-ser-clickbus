package br.com.clickbus.dto;

import br.com.clickbus.entity.City;
import br.com.clickbus.entity.Place;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePlaceDto {

	private String name;
	private String slug;
	private int cityId;

	public Place buildPlace() {
		Place place = new Place();
		place.setName(this.name);
		City city = new City();
		city.setId(this.cityId);
		place.setCity(city);
		place.setSlug(this.slug);
		return place;
	}
	
}
