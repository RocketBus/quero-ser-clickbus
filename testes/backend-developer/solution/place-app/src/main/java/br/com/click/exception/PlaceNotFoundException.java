package br.com.click.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlaceNotFoundException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
 
    public PlaceNotFoundException(String message) {
        super(message);
    }
}