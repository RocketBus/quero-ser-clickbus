package br.com.clickbus.challenge.entity;


import br.com.clickbus.challenge.dto.PlaceDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String slug;

    private String city;

    private String state;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Place(String name, String slug, String city, String state) {
        this.name = name;
        this.slug = slug;
        this.city = city;
        this.state = state;
        this.createdAt = LocalDateTime.now();
    }

    public static Place builder(String name, String slug, String city, String state) {
        return new Place(name, slug, city, state);
    }

    public PlaceDTO convertToDTO() {
        return PlaceDTO.builder(this.id, this.name, this.slug, this.city, this.state);
    }

    public void edit(PlaceDTO placeDTO) {
        this.name = placeDTO.getName();
        this.slug = placeDTO.getSlug();
        this.city = placeDTO.getCity();
        this.state = placeDTO.getState();
        this.updatedAt = LocalDateTime.now();
    }
}
