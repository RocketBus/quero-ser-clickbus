package br.com.clickbus.dto;

import br.com.clickbus.entity.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStateDto {

	private String state;
	
	public State buildState() {
		State state = new State();
		state.setState(this.state);
		return state;
	}
	
}
