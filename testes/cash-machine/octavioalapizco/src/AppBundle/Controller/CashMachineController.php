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
		
		$datosRespuesta=array();
		try{
			$billetes=$cashMachine->getBilletes($cantidad);
			$datosRespuesta=array(
				'success'=>true,
				'cantidad'=>number_format ($cantidad,0,'.',','),
				'billetes'=>$billetes
			);
		}catch(\Exception $e){
			$datosRespuesta=array(
				'success'=>false, 
				'msg_error'=>$e->getMessage(),
				'cantidad'=>$cantidad
			);
		}
		
		$response = new Response();
		$response->setContent(json_encode($datosRespuesta));
		$response->headers->set('Content-Type', 'application/json');
		return $response;		
    }
}
