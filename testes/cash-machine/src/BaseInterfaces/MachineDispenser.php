<?php
declare(strict_types=1);
/**
 * Rioxygen
 * @license  BS3-Clausule
 * @author Ricardo Ruiz <ricardojesus.ruizcruz@protonmail.com>
 */
namespace Rioxygen\ClickBus\BaseInterfaces;


/**
 * Interface MachineDispenser, over any
 * @package Rioxygen\ClickBus\BaseInterfaces
 */
interface MachineDispenser
{
    public function deliver($cash = null) : float;
}