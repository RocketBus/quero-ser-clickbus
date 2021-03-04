package br.com.clickbus.domain;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * {@link Place} entity model class
 *
 * @author Danilo Bitencourt
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "places")
public class Place implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id private String id;

  @NotNull private String name;

  @Indexed(unique = true)
  private String slug; // TODO automatic generation

  @NotNull private City city;

  @NotNull private State state;

  @CreatedDate @EqualsAndHashCode.Exclude private ZonedDateTime createdAt = ZonedDateTime.now();

  @CreatedDate @EqualsAndHashCode.Exclude private ZonedDateTime updatedAt = ZonedDateTime.now();
}
