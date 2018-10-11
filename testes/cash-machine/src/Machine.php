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
 * <p>Desarrolle una solución que simule la entrega de billetes cuando un cliente efectúe un saque en un cajero electrónico. </p>
 *   Los requisitos básicos son los siguientes:
 *   - Siempre entregar el menor número de notas posibles;
 *   - Es posible sacar el valor solicitado con las notas disponibles;
 *   - Saldo del cliente es infinito;
 *   - Cantidad de notas es infinito;
 * Notas disponibles de R $ 100,00; R $ 50,00; R $ 20,00 y R $ 10,00
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
     * @var integer
     */
    private $client;
    /**
     * @var Float
     */
    private $balance;
    /**
     * Machine constructor.
     * No construct info, beacuse no dependency over the requeriment
     */
    public function __construct()
    {
        $this->setClient();
    }
    public function setClient($client = 1000)
    {
        $this->client = $client;
        $this->balance = 100000000;
    }
    /**
     *
     * @param float $cash
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
        if (($cash) > $this->balance)
            throw new \InvalidArgumentException("No enought balance");
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