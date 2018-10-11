<?php
declare(strict_types=1);
/**
 * Rioxygen
 * @license  BS3-Clausule
 * @author Ricardo Ruiz <ricardojesus.ruizcruz@protonmail.com>
 */
namespace Rioxygen\ClickBus;

use PHPUnit\Framework\TestCase;

/**
 * Class MachineDispenserTest Test
 * @author Ricardo Ruiz
 * @version 1.0
 * @package Rioxygen\ClickBus
 */
final class MachineDispenserTest extends TestCase
{
    /**
     * Initial Setup
     */
    public function setUp() : void
    {
        //return void;
    }
    /**
     *
     */
    public function test30Example()
    {
        $machineDispenser = new Machine();
        $response = $machineDispenser->deliver(30.00);
        $this->assertTrue(is_array($response));
    }
    /**
     *
     */
    public function test80Example()
    {
        $machineDispenser = new Machine();
        $response = $machineDispenser->deliver(80.00);
        $this->assertEquals($response, array(50.00,20.00,10.00));
        $this->assertTrue(is_array($response));
    }
    /**
     *
     */
    public function test100Example()
    {
        $machineDispenser = new Machine();
        $response = $machineDispenser->deliver(100.00);
        $this->assertTrue(is_array($response));
    }
    /**
     * @expectedException \Rioxygen\ClickBus\Exception\UnavailableException
     */
    public function testUnavailable()
    {
        $machineDispenser = new Machine();
        $machineDispenser->deliver(125.00);
        $this->expectException(\Rioxygen\ClickBus\Exception\UnavailableException::class);
    }
    /**
     * @expectedException \InvalidArgumentException
     */
    public function testInvalidArgument()
    {
        $machineDispenser = new Machine();
        $machineDispenser->deliver(-130.00);
        $this->expectExceptionObject(\InvalidArgumentException::class);
    }
    /**
     * @expectedException \Rioxygen\ClickBus\Exception\UnavailableException
     */
    public function testInvalidBalance()
    {
        $machineDispenser = new Machine();
        $machineDispenser->deliver(200000000000.00);
        $this->expectExceptionObject(\Rioxygen\ClickBus\Exception\UnavailableException::class);
    }
    /**
     * @expectedException \InvalidArgumentException
     */
    public function testInvalidArgumentString()
    {
        $machineDispenser = new Machine();
        $machineDispenser->deliver("asd");
        $this->expectExceptionObject(\InvalidArgumentException::class);
    }
    /**
     * TDD workflow
     */
    public function tearDown() : void
    {
        //return void;
    }
}