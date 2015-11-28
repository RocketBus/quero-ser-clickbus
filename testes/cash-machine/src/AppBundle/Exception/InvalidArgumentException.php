<?php
/**
 * Created by PhpStorm.
 * User: Jorge
 * Date: 11/27/15
 * Time: 8:16 PM
 */

namespace AppBundle\Exception;


class InvalidArgumentException extends \Exception
{
    public function errorMessage()
    {
        return 'Amount not valid';
    }
}