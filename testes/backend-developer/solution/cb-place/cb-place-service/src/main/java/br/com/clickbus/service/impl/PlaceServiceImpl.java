package br.com.clickbus.service.impl;

import br.com.clickbus.domain.Place;
import br.com.clickbus.exception.PlaceNotFoundException;
import br.com.clickbus.mapper.PlaceMapper;
import br.com.clickbus.model.PlaceDTO;
import br.com.clickbus.repository.PlaceRepository;
import br.com.clickbus.service.PlaceService;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * {@link PlaceService} implementation class
 *
 * @author Danilo Bitencourt
 */
@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

  private final Logger log = LogManager.getLogger(PlaceServiceImpl.class);

  private final PlaceRepository placeRepository;
  private final PlaceMapper placeMapper;

  @Autowired
  public PlaceServiceImpl(final PlaceRepository placeRepository, final PlaceMapper placeMapper) {
    this.placeRepository = placeRepository;
    this.placeMapper = placeMapper;
  }

  /** {@inheritDoc} */
  @Override
  @Transactional(readOnly = true)
  public Page<PlaceDTO> findAll(Pageable pageable) {
    Page<Place> places = placeRepository.findAll(pageable);
    return placeMapper.convertToDTO(places);
  }

  /** {@inheritDoc} */
  @Override
  @Transactional(readOnly = true)
  public PlaceDTO findById(String id) {
    Assert.notNull(id, "The place name must be not null");
    Optional<Place> place = placeRepository.findById(id);

    if (!place.isPresent()) {
      throw new PlaceNotFoundException(id);
    }

    return placeMapper.convertToDTO(place.get());
  }

  /** {@inheritDoc} */
  @Override
  @Transactional(readOnly = true)
  public Page<PlaceDTO> search(final String searchTerm, Pageable pageable) {
    Assert.notNull(searchTerm, "The place searchTerm must be not null");

    Page<Place> places = placeRepository.search(searchTerm, pageable);
    return placeMapper.convertToDTO(places);
  }

  /** {@inheritDoc} */
  @Override
  public PlaceDTO save(PlaceDTO place) {
    Assert.notNull(place, "The place object must be not null");
    Assert.isNull(place.getId(), "The place id must be null");
    log.debug("Request to save Place : {}", place);

    Place newPlace = placeMapper.convertToEntity(place);
    return placeMapper.convertToDTO(placeRepository.save(newPlace));
  }

  /** {@inheritDoc} */
  @Override
  public PlaceDTO update(String id, PlaceDTO place) {
    Assert.notNull(id, "The place id must be not null");
    Assert.notNull(place, "The place object must be not null");
    log.debug("Request to update Place : {}", place);

    Optional<Place> foundPlace = placeRepository.findById(id);

    if (!foundPlace.isPresent()) {
      throw new PlaceNotFoundException(id);
    }

    foundPlace.get().setName(place.getName());
    foundPlace.get().setSlug(place.getSlug());
    foundPlace.get().setCity(place.getCity());
    foundPlace.get().setState(place.getState());

    return placeMapper.convertToDTO(placeRepository.save(foundPlace.get()));
  }

  /** {@inheritDoc} */
  @Override
  public void delete(String id) {
    Assert.notNull(id, "The place id must be not null");

    Optional<Place> foundPlace = placeRepository.findById(id);

    if (!foundPlace.isPresent()) {
      throw new PlaceNotFoundException(id);
    }

    placeRepository.deleteById(id);
  }
}
