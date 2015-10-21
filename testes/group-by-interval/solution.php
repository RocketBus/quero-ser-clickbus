<?php

/**
 * Main funtion
 * @param  int $range     Size of the range
 * @param  array  $numberSet Input Array
 * @return array            A bidimensional Array
 */
function makeRange($range, array $numberSet) {
	try{
		$ordenedArray = orderNumbers( $numberSet );
	}
	catch( \InvalidArgumentException $e) {
		echo "throwed an InvalidArgumentException";
		return;
	}
	//what would be a divide by range method
	$firstElementFirstGroup = array_shift($ordenedArray);
	$firstGroupLimit = $firstElementFirstGroup + $range;
	$groupedRanges = [];
	$elements = count($ordenedArray);
	$interation = 1;
	$group = array($firstElementFirstGroup);
	if($firstElementFirstGroup<0) {
		$firstGroupLimit = $firstElementFirstGroup+($interation*$range);
	}
	foreach($ordenedArray as $item) {
		if($item<=$firstGroupLimit) {
			$group[] = $item;
		}
		else{
			$groupedRanges[] = $group;
			unset($group);
			$group = array($item);
			$calculatedLimit = ceil(($item/$range))*$range;
			$firstGroupLimit = $calculatedLimit > 0 ? $calculatedLimit: $range ;
		}
		echo $firstGroupLimit.PHP_EOL;
	}
	$groupedRanges[] = $group;
	return $groupedRanges;
}
/**
 * Function that's order the array
 * @param  array  $numberSet Inputed Array
 * @return array            Ordered Array
 */
function orderNumbers($numberSet) {
	$orderedArray = array();
	if( !empty($numberSet) && is_array($numberSet)) {
		foreach ($numberSet as $number) {
			if (!is_int($number)) {
				throw new \InvalidArgumentException("", 1);
				return;
			}
			if(!empty($orderedArray)) {
				foreach($orderedArray as $key=>$value) {
					if($number < $value) {
						$orderedArray[$key] = $number;
						$number=$value;

					}
				}
				$orderedArray[] = $number;
			}
			else {
				$orderedArray[] = $number;
			}

		}
	}
	return $orderedArray;
}

//TestCases

echo "Caso 1:".PHP_EOL;
var_dump(makeRange(10, array(10, 1, -20,  14, 99, 136, 19, 20, 117, 22, 93,  120, 131)));

echo "Caso 2:".PHP_EOL;
var_dump(makeRange(15, array(10, 1, -20,  14, 99, 136, 19, 20, 117, 22, 93, 120, 131)));

echo "Caso 3:".PHP_EOL;
var_dump(makeRange(15, array(10, 1, 'A',  14, 99, 133, 19, 20, 117, 22, 93,  120, 131)));

echo "Caso 4:".PHP_EOL;
var_dump(orderNumbers(NULL));