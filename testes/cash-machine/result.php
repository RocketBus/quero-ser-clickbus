<?php 

function ATM($n) {
  $change = array();  //define empty array
  $qty = array(100,50,20,10); //define array of currencies
  
  foreach ($qty as $value) { //loop through each array value
    if ($n === 0) {
      return $change;
    }
    
    if ($n >= $value) { 
      $times = floor($n/$value); //get how many times the actual value divides the withdraw value
      
      for ($i=0; $i<$times; $i++) { //push the value $times times  
        array_push($change,$value);
      }
      
    $n -= $times*$value; //substracts the change already taken into account
    }
  }
  
  return $change;
}

function dispChange($A) { //display the change in rows
	echo "Your money is ready: <br><br>";
	foreach ($A as $val) {
		echo "<p>- $val <p>";
	}
}

$n = $_POST["value"];
if (!is_numeric($n)) { //check if the submited value is a number
	exit( "The value needs to be a number <br><a href='/pruebaCB'>Return</a>" );
} 

$n = intval($n);
if($n < 0) { //check if the submited value is positive
	exit( "The value needs to be positive <br><a href='/pruebaCB'>Return</a>" );
} elseif($n%10 !== 0) { //check if the submited value is at least multiple of 10
	exit( "The value needs to be at least multiple of 10 <br><a href='/pruebaCB'>Return</a>" );
} else {
dispChange(ATM($n));
  }
?>