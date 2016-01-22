<?php

namespace Cash\MachineBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Cash\MachineBundle\Utils\Requirement;
use Symfony\Component\Validator\Constraints\Regex;
class DefaultController extends Controller
{
    
    public function indexAction()
    {
       $form = $this->createFormBuilder()
                ->add('ammount','text',array(
                        'constraints' => new Regex(
                                array('pattern' =>"[-?\d+\.0{2}]",'message' =>'Introduzca valores validos,con dos decimales a la derecha. Ejemplo 10.00 '))
                        ,'required' =>false
                    )
                )
               ->setMethod('POST')
               ->setAction($this->generateUrl('cash_machine_homepage'))
               ->add('submittButton','submit')
               ->getForm();
       
        
        $request = $this->getRequest();
        $bills = array();
        
         if ($request->isMethod('POST')) {
            $form->handleRequest($request);
            $bills =   'Empty Set';
            if($form->isValid()){
                $datas =  $form->getData();
                $ammount = $datas['ammount'];
                if($ammount){
                    $requirement =  new Requirement($ammount);
                    if($requirement->isValidArgument($ammount))
                    {
                        $bills =  $requirement->isValidAmmount($ammount);
                    }
                } 
            }
        }
        
        return $this->render('CashMachineBundle:Default:index.html.twig', array(
            'bills' => $bills,
            'form' => $form->createView()
        ));
        
    }
    
    
    
}
