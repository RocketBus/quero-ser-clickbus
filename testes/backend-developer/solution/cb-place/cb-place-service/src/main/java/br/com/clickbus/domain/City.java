package br.com.clickbus.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@link City} model class
 *
 * @author Danilo Bitencourt
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name;
}
