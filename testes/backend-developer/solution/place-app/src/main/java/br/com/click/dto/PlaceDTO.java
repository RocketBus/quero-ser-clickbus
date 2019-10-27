package br.com.click.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDTO {
	
	@NotNull(message = "{name.not.null}")
	private String name;
	@NotNull(message = "{slug.not.null}")
	private String slug;
	@NotNull(message = "{state.not.null}")
	private String state;
	@NotNull(message = "{city.not.null}")
	private String city;
	
}
