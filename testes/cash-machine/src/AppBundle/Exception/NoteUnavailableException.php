<?php

/**
 * Created by PhpStorm.
 * User: Jorge
 * Date: 11/27/15
 * Time: 8:09 PM
 */

namespace AppBundle\Exception;

class NoteUnavailableException extends \Exception
{
    public function errorMessage()
    {
        return 'Amount must by divisible by 10';
    }
}