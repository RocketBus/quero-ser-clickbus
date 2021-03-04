package br.com.clickbus.model;

import br.com.clickbus.domain.City;
import br.com.clickbus.domain.State;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Danilo Bitencourt */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String id;
  private String name;
  private String slug;
  private City city;
  private State state;
}
