package br.com.clickbus.challenge.controller;


import br.com.clickbus.challenge.dto.PlaceDTO;
import br.com.clickbus.challenge.entity.Place;
import br.com.clickbus.challenge.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

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
    public ResponseEntity<Iterable<PlaceDTO>> findByName(@PathVariable String name) {
        Iterable<PlaceDTO> placeDTO = service.findByName(name).stream().map(Place::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(placeDTO);
    }

    @GetMapping
    public ResponseEntity<Iterable<PlaceDTO>> findAll() {
        Iterable<PlaceDTO> places = service.findAll().stream().map(Place::convertToDTO).collect(Collectors.toList());
        return new ResponseEntity<Iterable<PlaceDTO>>(places, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PlaceDTO> update(@RequestBody @Valid PlaceDTO placeDTO, @PathVariable Long id){

        Place place = service.findById(id).orElseThrow(null);
        place.edit(placeDTO);
        
        return new ResponseEntity(service.save(place).convertToDTO(), HttpStatus.OK);
    }
}
