<?php

use Symfony\Component\Routing\RouteCollection;
use Symfony\Component\Routing\Route;

$collection = new RouteCollection();

$collection->add('cash_machine_homepage', new Route('withDraw/', array(
        '_controller' => 'CashMachineBundle:Default:index',
)));

return $collection;
