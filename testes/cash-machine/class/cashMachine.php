<?php

namespace classes;

require("dispenser.php");


class cashMachine {

	private $dispenser;

	public function __construct(  ) {
		$this->dispenser = new dispenser();
	}

	public function withdraw( $value = null ) {
		$value = (int) $value;
		if( $value<0 ) {
				throw new \InvalidArgumentException("You can't ask for a negative value");
				return;
		}
		if( $value !== 0 ) {
			return $this->dispenser->separateBills($value);
		}
		return array();

	}

}

$cashMachine = new cashMachine();

$valuesToWithDraw = array('30', '80', '125', '-130', null );
foreach ($valuesToWithDraw as $valueToTest) {
	try {

		
		$wallet = $cashMachine->withdraw($valueToTest);
		if(!empty($wallet)) {
			$string_wallet ="";
			$interations = 0;
			foreach ($wallet as $bill => $qtde) {
				if( $qtde > 0 ) {
					if ($interations>=1) {
						$string_wallet .= " , ";
					}
					$string_wallet .= "{$qtde} of ".number_format($bill,2);
					++$interations;
				}

			}
		}
		else{
			$string_wallet = "empty set";	
		}
		

		echo "**Entrada:**" . $valueToTest.PHP_EOL;
 		echo "**Resultado:**[{$string_wallet}]";
	}
	catch (\InvalidArgumentException $argument) {
		echo "**Entrada:**" . $valueToTest.PHP_EOL;
		echo "**Resultado:** *throw InvalidArgumentException*";
	}
	catch (exception\NoteUnavailableException $notesException) {
		echo "**Entrada:**" . $valueToTest.PHP_EOL;
		echo "**Resultado:** *throw NoteUnavailableException*";	
	}
	echo PHP_EOL;
	
}
