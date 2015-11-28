<?php

/**
 * Created by PhpStorm.
 * User: Jorge
 * Date: 11/27/15
 * Time: 6:43 PM
 */

namespace AppBundle\Tests\Util;

use AppBundle\Util\Calculator;

class CalculatorTest extends \PHPUnit_Framework_TestCase
{
    /**
     * Test whether amounts not divisible by 10 give a non empty result.
     *
     * @param  void
     * @return void
     */
    public function testAmountIsDivisibleBy10()
    {
        $calc   = new Calculator();
        $result = $calc->getResult(343);

        $this->assertEquals($result, []);
    }

    /**
     * Given a known amount, test if the result is correct.
     *
     * @param  void
     * @return void
     */
    public function testBillsAreCorrrect()
    {
        $mockup = [100=>1, 50=>1, 20=>1, 10=>1];
        $calc   = new Calculator();
        $result = $calc->getResult(180);

        $this->assertEquals($result, $mockup);
    }
}