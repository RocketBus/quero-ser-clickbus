package br.com.clickbus.resource;

import static org.springframework.data.domain.Sort.Direction.ASC;

import br.com.clickbus.exception.PlaceNotFoundException;
import br.com.clickbus.model.PlaceDTO;
import br.com.clickbus.service.PlaceService;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/** @author Danilo Bitencourt */
@RestController
@RequestMapping("/api/places")
public class PlaceResource {

  private final PlaceService placeService;

  @Autowired
  public PlaceResource(PlaceService placeService) {
    this.placeService = placeService;
  }

  /**
   * GET /places : get all the places.
   *
   * @param page
   * @param size
   * @param sort
   * @return the {@link org.springframework.http.ResponseEntity} with status 200 (OK) and the list
   *     of places in body
   */
  @GetMapping
  public ResponseEntity<Page<PlaceDTO>> findAll(
      @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
      @RequestParam(value = "size", defaultValue = "30", required = false) Integer size,
      @RequestParam(value = "sort", defaultValue = "name", required = false) String sort) {

    Page<PlaceDTO> places = placeService.findAll(PageRequest.of(page, size, ASC, sort));
    return ResponseEntity.ok(places);
  }

  /**
   * GET /places/:id : get the "id" place.
   *
   * @param id the id of the place to retrieve
   * @return the {@link org.springframework.http.ResponseEntity} with status 200 (OK) and with body
   *     the place, or with status 404 (Not Found)
   */
  @GetMapping("/{id}")
  public ResponseEntity<PlaceDTO> findOne(@PathVariable String id) {
    try {
      PlaceDTO place = placeService.findById(id);
      return ResponseEntity.ok(place);
    } catch (PlaceNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }
  }

  /**
   * GET /places/search: get the place by searchTerm.
   *
   * @param searchTerm the searchTerm of the place to retrieve
   * @param page
   * @param size
   * @param sort
   * @return the {@link org.springframework.http.ResponseEntity} with status 200 (OK) and with body
   *     the place
   */
  @GetMapping("/search")
  public ResponseEntity<Page<PlaceDTO>> search(
      @RequestParam String searchTerm,
      @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
      @RequestParam(value = "size", defaultValue = "30", required = false) Integer size,
      @RequestParam(value = "sort", defaultValue = "name", required = false) String sort) {
    Page<PlaceDTO> places = placeService.search(searchTerm, PageRequest.of(page, size, ASC, sort));

    return ResponseEntity.ok(places);
  }

  /**
   * POST /places: Create a new place
   *
   * @param newPlace the place to create
   * @return the ResponseEntity with status 200 (OK) and with body the created place, or with status
   *     400 (Bad Request) if the place is not valid, or with status 500 (Internal Server Error) if
   *     the place could not be created
   * @throws URISyntaxException
   */
  @PostMapping
  public ResponseEntity<PlaceDTO> createPlace(@RequestBody PlaceDTO newPlace)
      throws URISyntaxException {
    try {
      PlaceDTO place = placeService.save(newPlace);
      return ResponseEntity.created(new URI(String.format("/api/places/%s", place.getId())))
          .body(place);
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
    }
  }

  /**
   * PUT /places: Update a existed place
   *
   * @param newPlace
   * @param id
   * @return the ResponseEntity with status 200 (OK) and with body the updated place, or with status
   *     404 (Not Found) if the "id" place not exists, or with status 500 (Internal Server Error) if
   *     the place could not be updated
   */
  @PutMapping("{id}")
  public ResponseEntity<PlaceDTO> updatePlace(
      @RequestBody PlaceDTO newPlace, @PathVariable String id) {
    try {
      PlaceDTO place = placeService.update(id, newPlace);
      return ResponseEntity.ok().body(place);
    } catch (PlaceNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }
  }

  /**
   * DELETE /places/:id : delete the "id" place.
   *
   * @param id
   * @return the {@link org.springframework.http.ResponseEntity} with status 200 (OK)
   */
  @DeleteMapping("{id}")
  public ResponseEntity<Void> deletePlace(@PathVariable String id) {
    try {
      placeService.delete(id);
    } catch (PlaceNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }

    return ResponseEntity.ok().body(null);
  }
}
