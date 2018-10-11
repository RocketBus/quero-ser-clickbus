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
        $this->assertTrue(is_array($machineDispenser->deliver(30.00)));
    }
    /**
     *
     */
    public function test80Example()
    {
        $machineDispenser = new Machine();
        $this->assertTrue(is_array($machineDispenser->deliver(80.00)));
    }
    /**
     *
     */
    public function test100Example()
    {
        $machineDispenser = new Machine();
        $this->assertTrue(is_array($machineDispenser->deliver(100.00)));
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
     * TDD workflow
     */
    public function tearDown() : void
    {
        //return void;
    }
}