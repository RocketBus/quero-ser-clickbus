<?php
declare(strict_types=1);
/**
 * Rioxygen
 * @license  BS3-Clausule
 * @author Ricardo Ruiz <ricardojesus.ruizcruz@protonmail.com>
 */
namespace Rioxygen\ClickBus;

use Rioxygen\ClickBus\BaseInterfaces\MachineDispenser;

/**
 * Class Machine
 * @package Rioxygen\ClickBus
 */
class Machine implements MachineDispenser
{
    static $cashout = array(100,50,20,10);
    /**
     *
     * @param string $cash
     * @return float
     */
    public function deliver($cash = null): float
    {
        return 100.00;
    }
}