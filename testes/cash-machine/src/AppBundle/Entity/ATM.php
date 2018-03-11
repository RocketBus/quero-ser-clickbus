<?php

/**
 * Created by PhpStorm.
 * User: Jorge
 * Date: 11/26/15
 * Time: 7:04 PM
 */

namespace AppBundle\Entity;

class ATM
{
    protected $amount;

    /**
     * @param void
     * @return int
     */
    public function getAmount()
    {
        return $this->amount;
    }

    /**
     * @param int
     * @return void
     */
    public function setAmount($amount)
    {
        $this->amount = $amount;
    }

}