package com.clickbus.challenge.exceptions;

public class PlacesServiceException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 7641011105414405431L;

    public PlacesServiceException(String message)
    {
        super(message);
    }
}
