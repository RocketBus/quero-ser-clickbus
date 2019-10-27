package br.com.click.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.click.dto.PlaceDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Place {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String slug;
	private String state;
	private String city;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dtCreate = LocalDateTime.now();
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dtUpdate = LocalDateTime.now();

	public Place(String name, String slug, String state, String city) {
		this.name = name;
		this.slug = slug;
		this.state = state;
		this.city = city;
	}

	public Place(PlaceDTO placeDTO) {
		this.name = placeDTO.getName();
		this.slug = placeDTO.getSlug();
		this.state = placeDTO.getState();
		this.city = placeDTO.getCity();
	}

	public Place(String name) {
		this.name = name;
	}
}
