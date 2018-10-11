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
    private $cashout = array(100,50,20,10);
    /**
     * @param null $cash
     * @return array
     * @throws UnavailableException
     */
    public function deliver($cash = null) : array
    {
        $response = array();
        if (!is_float($cash))
            throw new \InvalidArgumentException("No float given");
        if (max($this->cashout) < $cash)
            throw new UnavailableException("Note Unavailable Exception");
        if ($cash < 0)
            throw new \InvalidArgumentException("NoteUnavailableException");
        ksort($this->cashout);
        foreach ($this->cashout as $value) {
            do {
                if ($cash >= $value) {
                    $response[] = floatval($value);
                    $cash -= $value;
                }
            } while($cash > $value);
        }
        return $response;
    }
}