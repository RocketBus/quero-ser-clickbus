<?php
namespace AppBundle\Tests\BusinessLogic;

class CashMachineLogicTests extends \PHPUnit_Framework_TestCase{
	public function testGenerarErrorConCantidadNegativa(){
		
		$this->setExpectedException('InvalidArgumentException');
		$cashMachine=new \AppBundle\BusinessLogic\CashMachineLogic();
		$cashMachine->validar(-10);
	}
	
	public function testGenerarErrorConCantidadCero(){
		$this->setExpectedException('InvalidArgumentException');
		$cashMachine=new \AppBundle\BusinessLogic\CashMachineLogic();
		$cashMachine->validar(0);
	}
	
	public function testGenerarErrorConCantidadMenorA10(){
		$this->setExpectedException('\AppBundle\Exception\NotAvailableException');
		$cashMachine=new \AppBundle\BusinessLogic\CashMachineLogic();
		$cashMachine->validar(8);
	}
	
	public function testGenerarErrorConDecimales(){
		$this->setExpectedException('\AppBundle\Exception\NotAvailableException');
		$cashMachine=new \AppBundle\BusinessLogic\CashMachineLogic();
		$cashMachine->validar(10.5);
	}
	
	public function testGenerarErrorAlSolicitar1955(){
		$this->setExpectedException('\AppBundle\Exception\NotAvailableException');
		$cashMachine=new \AppBundle\BusinessLogic\CashMachineLogic();
		$billetes=$cashMachine->getBilletes(1955);		
	}
	
	public function testValidarAlSolicitar10(){		
		$cashMachine=new \AppBundle\BusinessLogic\CashMachineLogic();
		$valido=$cashMachine->validar(10);		
		$this->assertTrue($valido);
	}
	
	
	public function testComprobarDenominacionesAlSolicitar100(){		
		$cashMachine=new \AppBundle\BusinessLogic\CashMachineLogic();
		$billetes=$cashMachine->getBilletes(100);
		$numDenominaciones=sizeof($billetes);
		$denominacion=intval($billetes[0]['denominacion']);		
		$this->assertTrue($numDenominaciones==1 && $denominacion==100 && $billetes[0]['cantidad']==1);
	}
	
	
	public function testComprobarDenominacionesAlSolicitar300(){
		
		$cashMachine=new \AppBundle\BusinessLogic\CashMachineLogic();
		$billetes=$cashMachine->getBilletes(300);
		$numDenominaciones=sizeof($billetes);
		$denominacion=intval($billetes[0]['denominacion']);				
		$this->assertTrue($numDenominaciones==1 && $denominacion==100 && $billetes[0]['cantidad']==3);
	}
	
	public function testComprobarDenominacionesAlSolicitar1950(){
		
		$cashMachine=new \AppBundle\BusinessLogic\CashMachineLogic();
		$billetes=$cashMachine->getBilletes(1950);
		$numDenominaciones=sizeof($billetes);
		$denominacion1=intval($billetes[0]['denominacion']);	
		$denominacion2=intval($billetes[1]['denominacion']);	
		
		$this->assertTrue($numDenominaciones==2 && $denominacion1==100 && $billetes[0]['cantidad']==19 && $denominacion2==50 && $billetes[1]['cantidad']==1 );
	}
	
	
	
}
?>