<?php
require_once 'NoteUnavailableException.php';
require_once 'NoteDeal.php';

/**
 * ATM simple simulator
 *
 * Conditions Of Satisfaction:
 *
 * Siempre ofrecer el menor número posible de billetes;
 * Usted puede retirar el importe solicitado en los billetes disponibles;
 * El saldo del cliente es infinito;
 * El cajero siempre tiene todas las denominaciones y saldo.
 * Billetes disponibles en $100.00 ; $ 50.00; $ 20.00 y $ 10.00
 *
 * @author eduardo
 *
 */
class ATM
{
    private $Availabledenominations;
    private $dealtNotes;
    
    /**
     * ATM simple simulator
     */
    public function __construct()
    {
        $this->Availabledenominations = array(100, 50, 20, 10);
        $this->dealtNotes = array();
    }

    /**
     * starts deal request for the given amount.
     *
     * @param integer $amount Amount to be dealt
     * @throws InvalidArgumentException
     * @throws NoteUnavailableException
     */
    public function requestDeal($amount) {
        if ($amount == null) {
            $this->releaseNotes(); //releases an empty set at this point
        }elseif (!$this->verifyAmountRange($amount)) {
            throw new InvalidArgumentException();
        }elseif (!$this->verifyDenominationAvalilability($amount)){
            throw new NoteUnavailableException();
        }else {
            return $this->calculateDeal($amount);
        }
    }

    /**
     * Validates the required amount can be dealt with avalilabe denominations
     *
     * @param integer $param - positive integer representing de amount to be dealt which has to be verified
     * @return boolean true if amount can be dealt and false otherwise
     */
    protected function verifyDenominationAvalilability($amount) {
        $lastDenominationIndx = count($this->Availabledenominations) - 1;
        //lazy verification by trying the smallest denomination in order to deal the requested amount
        return (($amount % $this->Availabledenominations[$lastDenominationIndx]) == 0);
    }

    /**
     * Validates the reqired amount is within the allowed range
     * @param integer $amount positive integer representing de amount to be dealt which has to be verified
     * @return boolean
     */
    protected function verifyAmountRange($amount) {
        return ($amount > 0 && fmod($amount, 1) == 0); //positive integers
    }

    /**
     * calculates how many notes of each available denomination deal
     * @param unknown $param
     */
    protected function calculateDeal($amount) {
        $denLen = count($this->Availabledenominations);
        $i = 0;
        $currentDenomination = 0;
        
        for ($i; $i < $denLen; $i++) {
            $currentDenomination = $this->Availabledenominations[$i];
            if ($amount < $currentDenomination) {
                continue;
            }else {
                $this->setNotes($currentDenomination, floor($amount / $currentDenomination));
                $amount = $amount % $currentDenomination;
            }
        }
        return $this->releaseNotes();
    }

    /**
     * prepares required count of notes for the pot to be released
     * @param integer $denomination
     * @param integer $count
     */
    protected function setNotes($denomination, $count) {
        //logic for reducing Notes amount or notifying other entities could take place here too
        array_push($this->dealtNotes, new NoteDeal($denomination, $count));
    }

    /**
     * releases the requested amount on calculated notes amounts
     * @return string JSON representation of given deal
     */
    protected function releaseNotes() {
        return json_encode(array('notes'=>$this->dealtNotes));
    }
}
?>