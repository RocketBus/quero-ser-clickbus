package br.com.clickbus.challenge.controller;


import br.com.clickbus.challenge.dto.PlaceDTO;
import br.com.clickbus.challenge.entity.Place;
import br.com.clickbus.challenge.service.PlaceService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Api("place")
@RestController
@RequestMapping("place")
public class PlaceController {

    @Autowired
    private PlaceService service;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid PlaceDTO dto) {
        return new ResponseEntity(service.save(dto.buildPlace()).convertToDTO(), HttpStatus.CREATED);
    }


    @GetMapping("{id}")
    public ResponseEntity<PlaceDTO> findById(@PathVariable Long id) {
        return service.findById(id).map(place -> {
            return ResponseEntity.ok(place.convertToDTO());
        }).orElseThrow(null);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity findByName(@PathVariable String name) {
        List<Place> places = service.findByName(name);
        return !places.isEmpty() ? ResponseEntity.ok(places.stream().map(Place::convertToDTO).collect(Collectors.toList())) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity findAll() {
        List<PlaceDTO> places = service.findAll().stream().map(Place::convertToDTO).collect(Collectors.toList());
        return new ResponseEntity(places, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PlaceDTO> update(@PathVariable Long id, @RequestBody @Valid PlaceDTO placeDTO) {
        Place place = service.findById(id).orElseThrow(null);
        place.edit(placeDTO);
        return new ResponseEntity(service.save(place).convertToDTO(), HttpStatus.OK);
    }
}