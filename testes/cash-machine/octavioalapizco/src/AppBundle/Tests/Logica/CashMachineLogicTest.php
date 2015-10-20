<?php
namespace AppBundle\Tests\Logica;

class CashMachineLogicTests extends \PHPUnit_Framework_TestCase{
	public function testValidarCantidadNegativa(){
		$this->setExpectedException('InvalidArgumentException');
		$cashMachine=new \AppBundle\Logica\CashMachineLogic();
		$cashMachine->validar(-10);
	}
	
	public function testValidarCantidadCero(){
		$this->setExpectedException('InvalidArgumentException');
		$cashMachine=new \AppBundle\Logica\CashMachineLogic();
		$cashMachine->validar(-10);
	}
	
	public function testValidarCantidadMenorA10(){
		$this->setExpectedException('\AppBundle\Exception\NotAvailableException');
		$cashMachine=new \AppBundle\Logica\CashMachineLogic();
		$cashMachine->validar(8);
	}
	
	public function testValidarCantidad10(){		
		$cashMachine=new \AppBundle\Logica\CashMachineLogic();
		$valido=$cashMachine->validar(10);		
		$this->assertTrue($valido);
	}
	
	public function testGetCantidad100(){		
		$cashMachine=new \AppBundle\Logica\CashMachineLogic();
		$billetes=$cashMachine->getBilletes(100);
		$numDenominaciones=sizeof($billetes);
		$denominacion=intval($billetes[0]['denominacion']);		
		$this->assertTrue($numDenominaciones==1 && $denominacion==100 && $billetes[0]['cantidad']==1);
	}
	
	
	public function testGetCantidad300(){
		
		$cashMachine=new \AppBundle\Logica\CashMachineLogic();
		$billetes=$cashMachine->getBilletes(300);
		$numDenominaciones=sizeof($billetes);
		$denominacion=intval($billetes[0]['denominacion']);				
		$this->assertTrue($numDenominaciones==1 && $denominacion==100 && $billetes[0]['cantidad']==3);
	}
	
	public function testGetCantidad1950(){
		
		$cashMachine=new \AppBundle\Logica\CashMachineLogic();
		$billetes=$cashMachine->getBilletes(1950);
		$numDenominaciones=sizeof($billetes);
		$denominacion1=intval($billetes[0]['denominacion']);	
		$denominacion2=intval($billetes[1]['denominacion']);	
		
		$this->assertTrue($numDenominaciones==2 && $denominacion1==100 && $billetes[0]['cantidad']==19 && $denominacion2==50 && $billetes[1]['cantidad']==1 );
	}
	
	public function testGetCantidad1955(){
		$this->setExpectedException('\AppBundle\Exception\NotAvailableException');
		$cashMachine=new \AppBundle\Logica\CashMachineLogic();
		$billetes=$cashMachine->getBilletes(1955);		
	}
	
}
?>