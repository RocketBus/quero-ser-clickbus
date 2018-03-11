<?php

/**
 * Exception thrown if the amount to be dealt by the ATM is out of allowed range
 * 
 * @author eduardo
 *
 */
class InvalidArgumentExceptions extends Exception
{
    function __construct($message, $code, $previous)
    {
        if (!isset($message) || $message == ''){
            $message = 'Amount has to be a positive intiger';
        }
        
        parent::__construct($message, $code, $previous);
    }
}
?>