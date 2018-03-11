<?php

/**
 * Created by PhpStorm.
 * User: Jorge
 * Date: 11/27/15
 * Time: 5:21 PM
 */
namespace AppBundle\Util;

use AppBundle\Exception\InvalidArgumentException;
use AppBundle\Exception\NoteUnavailableException;

class Calculator
{
    private $bills = [100, 20, 50, 10];

    /**
     * Returns and array indicating how many bills are necessary to cover the amount
     *
     * @param int $amount Amount to withdraw
     * @return array
     */
    public function getResult($amount)
    {
        $result = [];

        if($this->isValidAmount($amount))
        {
            rsort($this->bills);
            foreach ($this->bills as $bill)
            {
                $result[$bill] = intval($amount / $bill);
                $amount       %= $bill;
            }
        }

        return $result;
    }

    /**
     * Checks if the amount is greater than zero and divisible by 10
     *
     * @throws InvalidArgumentException if amount is zero or less
     * @throws NoteUnavailableException if amount is not divisible by 10
     * @param int $amount Amount to withdraw
     * @return boolean
     */
    private function isValidAmount($amount)
    {
        sort($this->bills);
        $bill = $this->bills[0];

        if($amount <= 0)
        {
            throw new InvalidArgumentException();
        }
        if($amount % $bill !== 0)
        {
            throw new NoteUnavailableException();
        }

        return true;
    }
}