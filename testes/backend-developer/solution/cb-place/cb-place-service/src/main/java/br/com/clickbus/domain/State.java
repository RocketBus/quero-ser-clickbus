package br.com.clickbus.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@link State} model class
 *
 * @author Danilo Bitencourt
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class State implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name;
  private String uf;
}
