package br.com.clickbus.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlaceNotFoundException extends RuntimeException{

    private String message;

    public PlaceNotFoundException(HttpStatus status){
        super(status.toString());
    }
}
