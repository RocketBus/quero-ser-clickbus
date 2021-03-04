package br.com.clickbus.mapper;

import br.com.clickbus.domain.Place;
import br.com.clickbus.model.PlaceDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

/** @author Danilo Bitencourt */
@Mapper
public class PlaceMapper implements DTOConverter<PlaceDTO, Place> {

  private final ModelMapper modelMapper;

  @Autowired
  public PlaceMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public PlaceDTO convertToDTO(Place e) {
    return modelMapper.map(e, PlaceDTO.class);
  }

  @Override
  public Place convertToEntity(PlaceDTO d) {
    return modelMapper.map(d, Place.class);
  }

  @Override
  public Page<PlaceDTO> convertToDTO(Page<Place> page) {
    return page.map(this::convertToDTO);
  }
}
