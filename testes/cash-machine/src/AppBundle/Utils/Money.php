<?php
    namespace AppBundle\Utils;
    class Money
    {
        
        public function dameBilletes($cantidad)
		{
            $billetesDisponibles = array(10,20,50,100);
			$matrixCambio = $this->calcularMatrix($cantidad, $billetesDisponibles);
			$result = $this->seleccionarBilletes($cantidad, $billetesDisponibles, $matrixCambio);
            return $result;
		}

		public function calcularMatrix($cantidad, $billetesDisponibles)
		{
			$matrizCambio = array(array());

			for($i = 0; $i <= count($billetesDisponibles); $i++){
				$matrizCambio[$i][0] = 0;
            }

			for($j = 1; $j <= $cantidad; $j++)
            {
				$matrizCambio[0][$j] = 9999999;
            }

            for ($i = 1; $i <= count($billetesDisponibles); $i++)
            	for ($j = 1; $j <= $cantidad; $j++)
            	{
                	if ($j < $billetesDisponibles[$i - 1])
                		$matrizCambio[$i][$j] = $matrizCambio[$i - 1][$j];
                	else
                	{
                    	$minimo = 0;
                        $parama = $matrizCambio[$i - 1][$j];
                        $paramb = $matrizCambio[$i][$j- $billetesDisponibles[$i - 1]] + 1;
                        $minimo = min($parama , $paramb);
                        $matrizCambio[$i][$j] = $minimo;
                	}
            	}
            return $matrizCambio;
		}

		public function seleccionarBilletes($c, $billetesDisponibles, $tabla)
		{
        	$i = -1;
        	$j = -1;
        	$seleccion = array();
        	for($i = 0; $i< count($billetesDisponibles); $i++)
        		$seleccion[$i]=0;

        	$i = count($billetesDisponibles);
        	$j= $c;         
        	while($j>0)
        	{
            	if($i>1 && $tabla[$i][$j]==$tabla[$i-1][$j])
            	{
                	$i--;
            	}
            	else
            	{
                	$seleccion[$i-1]++;
                	$j = $j - $billetesDisponibles[$i-1];
            	}
        	}
        	return $seleccion;
    	}    
    }
?>