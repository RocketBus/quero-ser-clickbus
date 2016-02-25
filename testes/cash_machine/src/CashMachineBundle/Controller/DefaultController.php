<?php

namespace CashMachineBundle\Controller;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use CashMachineBundle\Utils\DeliverNotes;
use CashMachineBundle\Utils\NoteUnavailableException;
use CashMachineBundle\Utils\InvalidArgumentException;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Response;
class DefaultController extends Controller
{
    /**
     * @Route("/", name="home")
     * @Method("GET")
     * @Template()
     */
    public function indexAction()
    {
        $data = array('requested'=>0);
        $form = $this->createFormBuilder($data)
                ->setAction($this->generateUrl('deliverNotes'))
                ->add('requested', IntegerType::class)
                ->add('Solicitar', SubmitType::class, array(
                        'attr' => array('class' => 'save'),
                ))
                ->getForm();
        $notes=array(100, 50, 20, 10);
        return array(
            'notes' => $notes, 'form'=>$form->createView()
        );
    }
    /**
     * @Route("/deliverNotes", name="deliverNotes")
     * @Method("POST")
     */
    public function deliverNotes(Request $request){
        $availableBills=array(100, 50, 20, 10);
        arsort($availableBills);
        $totalNotesAvailable = count($availableBills);
        $postData = $request->request->get('form');
        $requested = $postData['requested'];
        
        if(!is_numeric($requested)){
            $invalidArgumentException = new InvalidArgumentException("El valor debe ser númerico");
            return new Response($invalidArgumentException);
        }
        $requested = intval($requested);
        if($requested <=0){
            $invalidArgumentException = new InvalidArgumentException("El valor debe ser mayor que cero");
            return new Response($invalidArgumentException);
        }
        $minVal=$availableBills[$totalNotesAvailable-1];
        $valid=$requested%$minVal;
        if($valid!=0){
            $noteUnavailableExcpetion=new NoteUnavailableException("El valor debe ser múltiplo del menor billete en existencia");
            return new Response($noteUnavailableExcpetion); 
        }
        $deliverNotes = new DeliverNotes();
        $delivered = $deliverNotes->distribute($requested, $availableBills);
        return new Response("<b>Tus billetes:</b> <br>".implode("<br> ", $delivered)); 
    }
}
