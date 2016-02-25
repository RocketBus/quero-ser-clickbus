<?php

namespace CashMachineBundle\Utils;
class DeliverNotes
{
public function distribute($requested, $availableBills){
    arsort($availableBills);
    
    $outputNotes=array();
    
    while($requested > 0) {
        foreach ($availableBills as $key => $value) {
            if($requested >= $value){
                array_push($outputNotes, "$".number_format($value, 2, '.', ''));
                $requested=$requested-$value;
                break; 
            }
        }
    }
    return $outputNotes; 
}

}
