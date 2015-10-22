<?php

namespace AppBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use AppBundle\BusinessLogic\CashMachineLogic;
use Symfony\Component\HttpFoundation\Response;

class CashMachineController extends Controller
{
    
    public function indexAction()
    {
        return $this->render('AppBundle:CashMachine:index.html.twig');
    }
	
	public function entregarDineroAction(Request $request)
    {
		$cantidad = $request->request->get('cantidad_solicitada');
		$esAjax = $request->request->get('esAjax');
		
		$cashMachine=new CashMachineLogic();
		$method = $this->get('request')->getMethod();
		
		try{
			$billetes=$cashMachine->getBilletes($cantidad);
			if ( !empty($esAjax) ){
				$response = new Response();
				$response->setContent(json_encode(
					array('success'=>true,'cantidad'=>number_format ($cantidad,0,'.',',') ,'billetes'=>$billetes)
				));
				$response->headers->set('Content-Type', 'application/json');
				return $response;
			}else{
				return $this->render('AppBundle:CashMachine:dinero_entregado.html.twig',array('cantidad'=>$cantidad,'billetes'=>$billetes));
			}
		}catch(\Exception $e){
			if ( !empty($esAjax) ){
				
				$response = new Response();
				$response->setContent(json_encode(
					array('success'=>false, 'msg_error'=>$e->getMessage(),'cantidad'=>$cantidad)
				));
				$response->headers->set('Content-Type', 'application/json');
				return $response;
			}else{
				return $this->render('AppBundle:CashMachine:error.html.twig',array('cantidad'=>$cantidad,'msg_error'=>$e->getMessage()));
			}
		}
    }
}
