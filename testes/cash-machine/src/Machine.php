<?php
declare(strict_types=1);
/**
 * Rioxygen
 * @license  BS3-Clausule
 * @author Ricardo Ruiz <ricardojesus.ruizcruz@protonmail.com>
 */
namespace Rioxygen\ClickBus;

use Rioxygen\ClickBus\BaseInterfaces\MachineDispenser;
use Rioxygen\ClickBus\Exception\UnavailableException;

/**
 * Class Machine
 * @package Rioxygen\ClickBus
 */
class Machine implements MachineDispenser
{
    /**
     * Available type of convertions
     * @var array
     */
    static $cashout = array(100.00,50,20,10);
    /**
     *
     * @param float $cash
     * @return array
     */
    public function deliver($cash = null) : array
    {
        if (!is_float($cash))
            throw new \InvalidArgumentException("No float given");
        if (max(self::$cashout) < $cash)
            throw new UnavailableException("Note Unavailable Exception");
        if ($cash < 0)
            throw new \InvalidArgumentException("NoteUnavailableException");
        return self::$cashout;
    }
}