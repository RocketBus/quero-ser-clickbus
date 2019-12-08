package br.com.clickbus.dto;

import br.com.clickbus.entity.City;
import br.com.clickbus.entity.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCityDto {

	private String city;
	private int stateId;
	
	public City buildCity() {
		City city = new City();
		city.setCity(this.city);
		
		State state = new State();
		state.setId(this.stateId);
		
		city.setState(state);
		
		return city;
	}
	
}
