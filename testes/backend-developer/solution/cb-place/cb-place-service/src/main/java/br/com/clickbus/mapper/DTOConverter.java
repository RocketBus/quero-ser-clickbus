package br.com.clickbus.mapper;

import org.springframework.data.domain.Page;

/**
 * Interface to DTOMapper
 *
 * @param <D> the type of DTO class
 * @param <E> the type of Entity class
 *     <p>* @author Danilo Bitencourt
 */
public interface DTOConverter<D, E> {

  D convertToDTO(E e);

  E convertToEntity(D d);

  Page<D> convertToDTO(Page<E> page);
}
