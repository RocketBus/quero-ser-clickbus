<?php
class dispenser {
	namespace classes;
	
	/**
	 * Available Bill on  dispenser
	 * @var array
	 */
	public $availabeBills = array(
		'100' => array("quantity"=>100),
		'50' =>  array("quantity"=>100),
		'20' =>  array("quantity"=>100), 
		'10' =>  array("quantity"=>100),
		);

	public function $change;

	publiv function __construct( array $bills = array()) {
		if(!empty($bills)) {
			self::$availabeBills = $bills;
		}
	}

	public function separateBills($value) {
		$billsAvailable = krsort(self::$availabeBills);
		$wallet = [] ;
		foreach($billsAvailable as $billToGet => $qtdeAvailable) {
			if($value < $billToGet) {
				continue;
			}
			$numberOfBills = $this->getBills($value, $billToGet, $qtdeAvailable);
		}
		$wallet = 
	}

	private function getBills(&$value, $billToGet, $qtdeAvailable) {
		$numberOfBills = floor($value/$billToGet);
		$value = $value%$billToGet;
		return $numberOfBills;
	}




}