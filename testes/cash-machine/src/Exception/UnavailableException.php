<?php
declare(strict_types=1);
/**
 * Rioxygen
 * @license  BS3-Clausule
 * @author Ricardo Ruiz <ricardojesus.ruizcruz@protonmail.com>
 */
namespace Rioxygen\ClickBus\Exception;

/**
 * Class NoteUnavailableException
 * @package Rioxygen\ClickBus\Exception
 */
class UnavailableException extends \Exception
{
    /**
     * @param mixed $message
     */
    public function __construct($message) {
        parent::__construct($message);
    }
}