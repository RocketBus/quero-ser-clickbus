<?php

namespace AppBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use AppBundle\Logica\CashMachineLogic;


class CashMachineController extends Controller
{
    
    public function indexAction()
    {
        return $this->render('AppBundle:CashMachine:index.html.twig');
    }
	
	public function entregarDineroAction(Request $request)
    {
		$cantidad = $request->request->get('cantidad_solicitada');
		$cashMachine=new CashMachineLogic();
		
		try{
			$billetes=$cashMachine->getBilletes($cantidad);
			return $this->render('AppBundle:CashMachine:dinero_entregado.html.twig',array('cantidad'=>$cantidad,'billetes'=>$billetes));
		}catch(\Exception $e){
			return $this->render('AppBundle:CashMachine:error.html.twig',array('cantidad'=>$cantidad,'msg_error'=>$e->getMessage()));
		}
    }
}
