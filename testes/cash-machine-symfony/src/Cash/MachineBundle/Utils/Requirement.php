<?php
namespace Cash\MachineBundle\Utils;

/**
 * 
 * @version 1.0
 * @author Developer  Samuel Medrano.
 */
class Requirement {
    
    const DENOMINATION_1 = 100;
    const DENOMINATION_2 = 50;
    const DENOMINATION_3 = 20;
    const DENOMINATION_4 = 10;
    
    /** @var $ammount wished ammount to withdraw*/
    protected $ammount;


    /**
     * 
     * @param int $ammount Ammount required by client
     */
    public function __construct($ammount) {
       $this->ammount = $ammount;
    }
    
    
    /**
     * All availables denominations
     * @return array 
     */
    public function getDenomination()
    {
        return array(
            self::DENOMINATION_1,
            self::DENOMINATION_2,
            self::DENOMINATION_3,
            self::DENOMINATION_4,
        );
    }
    
    
    /**
     * 
     * Check if given ammount is valid, if it is valid, return array with
     * number of bills that cash machine should give to withdraw 
     * @param int $ammount wished ammount to withdraw
     * @return array bills that cash machine should give to withdraw 
     * @throws Exception\NoteUnavailableException
     */
    public function isValidAmmount()
    {
        $ammount = $this->ammount;
        $denomination = $this->getDenomination();
        $bills = array();
        $ammountTemp  = $ammount;
        $total = $ammountTemp;
        
        foreach ($denomination as $den )
        {
            $infoBill = $this->decreaseAmmount($den, $ammountTemp,array());
            
            
            if(array_key_exists('quantity', $infoBill)){
                $ammountTemp = $infoBill['quantity'];
                $total = ($total)- ($den *$infoBill[$den]);
                unset($infoBill['quantity']);
                $bills[$den] = $infoBill[$den];
            }
            
        }
        
        if($total)
        {
            throw  new Exception\NoteUnavailableException("Not available for this ammount");
        }
        return  $bills;
    }
    
    /**
     * Decrease given Ammount recursively till it is less than decresedNumber.
     * Saving number of times it was decresead before be less than  decresedNumber.
     * 
     * @param int $decresedNumber ammount to subtract
     * @param int $ammount wished  ammount that is needed decrease
     * @param array $infoQuantity It is not needed, function use it to make recursive decrease, anyway
     *  it array shoul contains info like  number of times decreased,
     * ammount to substract and ammount. 
     * @return array info about how many bills of kind of $decresedNumber should cash machine give and "quantity" the result
     * after substracct param $ammount 
     */
    public function decreaseAmmount($decresedNumber, $ammount,$infoQuantity = array())
    {
        
        if($decresedNumber<=$ammount)
        {
            
            $ammount =$ammount -$decresedNumber;
            if(!array_key_exists($decresedNumber,$infoQuantity))
            {
                $infoQuantity[$decresedNumber] = 0;
            }
            $infoQuantity[$decresedNumber] += 1;
            
            $infoQuantity['quantity'] = $ammount;
            
            
            $infoQuantity = $this->decreaseAmmount($decresedNumber,$ammount,$infoQuantity);
             
        }
        
        return $infoQuantity;
    }
    
    /**
     * Check if given ammount is negative
     * @param int $ammount to check
     * @return boolean
     * @throws \InvalidArgumentException
     */
    public function isValidArgument($ammount)
    {
        $ammount = $this->ammount;
        if($ammount < 0) {
            throw new \InvalidArgumentException("Invalid argument.");
        }
        
        return true; 
    }
    
}
