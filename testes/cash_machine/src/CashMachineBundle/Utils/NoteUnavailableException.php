<?php
namespace CashMachineBundle\Utils;
use Symfony\Component\Config\Definition\Exception\Exception;
class NoteUnavailableException extends Exception
{
    public function __construct($message, $code = 0, Exception $previous = null) {
        parent::__construct($message, $code, $previous);
    }    
    public function __toString() {
        return $this->message."\n";
    }
    public function JsonEncoded(){
        return json_encode(
            array(
                "status"=>"error",
                "exception"=>"NoteUnavailableException", 
                "message"=>$this->message
            ));
    }
        
}

