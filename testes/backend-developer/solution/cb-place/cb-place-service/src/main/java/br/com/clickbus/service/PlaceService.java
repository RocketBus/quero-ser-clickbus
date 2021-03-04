package br.com.clickbus.service;

import br.com.clickbus.exception.PlaceNotFoundException;
import br.com.clickbus.model.PlaceDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface to define the Place Services Behavior
 *
 * @author Danilo Bitencourt
 */
public interface PlaceService {

  /**
   * Find all registered places
   *
   * @param pageable {@link org.springframework.data.domain.Pageable} instance representing the
   *     pagination options
   * @return An {@link List} of { @PlaceDTO} registered
   */
  Page<PlaceDTO> findAll(Pageable pageable);

  /**
   * Find registered place by id parameter
   *
   * @param id {@link java.lang.String} instance representing the place id
   * @return {@link PlaceDTO} instance representing the saved place
   */
  PlaceDTO findById(final String id) throws PlaceNotFoundException;

  /**
   * Find registered place by a searchTerm parameter
   *
   * @param searchTerm {@link java.lang.String} instance representing the search term
   * @param pageable
   * @return An {@link List} of { @PlaceDTO} registered
   */
  Page<PlaceDTO> search(final String searchTerm, Pageable pageable) throws PlaceNotFoundException;

  /**
   * Save the Place
   *
   * @param place {@link PlaceDTO} instance representing the place
   * @return {@link PlaceDTO} instance representing the saved place
   */
  PlaceDTO save(final PlaceDTO place);

  /**
   * Save the Place
   *
   * @param id {@link java.lang.String} instance representing the place id
   * @param place {@link PlaceDTO} instance representing the place
   * @return {@link PlaceDTO} instance representing the saved place
   */
  PlaceDTO update(final String id, final PlaceDTO place) throws PlaceNotFoundException;

  /**
   * Delete the place correspondent to id
   *
   * @param id {@link java.lang.String} instance representing the place id
   */
  void delete(final String id) throws PlaceNotFoundException;
}
