<?php
require_once 'ATM.php';

$atm = new ATM();

$response = $atm->requestDeal($_GET['amount']);
header('Content-Type: application/json;charset=utf-8');
echo $response;

?>