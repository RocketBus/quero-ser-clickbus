package br.com.clickbus.exception;

/**
 * This exception is throw in case of a place register not exists.
 *
 * @author Danilo Bitencourt
 */
public class PlaceNotFoundException extends RuntimeException {

  public PlaceNotFoundException(String id) {
    super(String.format("A place with ID '%s' not found!", id));
  }
}
