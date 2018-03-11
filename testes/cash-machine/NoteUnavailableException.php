<?php

/**
 * Exception thrown if the amount to be dealt by the ATM doesnt match availabe denominations
 * 
 * @author eduardo
 *
 */
class NoteUnavailableException extends Exception
{
    function __construct($message = '', $code = 0, $previous = NULL)
    {
        if (!isset($message) || $message == ''){
            $message = 'No denomination available to deal the amount required';
        }
        
        parent::__construct($message, $code, $previous);
    }
}
?>