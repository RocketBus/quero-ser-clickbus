<?php
	namespace classes;
	require (__DIR__."/exception/NoteUnavailableException.php");


class dispenser {
	
	/**
	 * Available Bill on  dispenser
	 * @var array
	 */
	private $availabeBills = array(
		'100',
		'50' ,
		'20' ,
		'10',
		);

	public $change;

	public function __construct( array $bills = array()) {
		if(!empty($bills)) {
			$this->availabeBills = $bills;
		}
	}

	public function separateBills($value) {
		rsort($this->availabeBills, SORT_NUMERIC);
		$billsAvailable = $this->availabeBills;
		$wallet = [] ;
		foreach($billsAvailable as $billToGet ) {
			$numberOfBills = $this->getBills($value, $billToGet);
			$wallet[$billToGet] = $numberOfBills;
		}
		if($value !== 0) {
			$string_bills = implode(',', $this->availabeBills);
			throw new exception\NoteUnavailableException("You can't withdraw a value that cannot be a combination of {$string_bills} bills");
			return;
		}
		return $wallet;
	}

	private function getBills(&$value, $billToGet) {
		$numberOfBills = floor($value/$billToGet);
		$value = $value%$billToGet;
		return $numberOfBills;
	}




}