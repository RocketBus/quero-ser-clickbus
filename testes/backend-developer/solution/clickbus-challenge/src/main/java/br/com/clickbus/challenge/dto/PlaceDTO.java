package br.com.clickbus.challenge.dto;

import br.com.clickbus.challenge.entity.Place;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class PlaceDTO {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String slug;

    @NotNull
    private String city;

    @NotNull
    private String state;

    public PlaceDTO(Long id, String name, String slug, String city, String state) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.city = city;
        this.state = state;
    }

    public static PlaceDTO builder(Long id, String name, String slug, String city, String state) {
        return new PlaceDTO(id, name, slug, city, state);
    }

    public Place buildPlace() {
        return Place.builder(this.name, this.slug, this.city, this.state);
    }
}
