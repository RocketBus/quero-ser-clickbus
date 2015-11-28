<?php

namespace AppBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use AppBundle\Entity\ATM;
use AppBundle\Form\Type\ATMType;
use AppBundle\Exception\InvalidArgumentException;
use AppBundle\Exception\NoteUnavailableException;

class DefaultController extends Controller
{
    /**
     * @Route("/", name="index")
     */
    public function indexAction()
    {
        $atm  = new ATM();
        $form = $this->createForm(new ATMType(), $atm, array('action' => $this->generateUrl('withdraw')));

        return $this->render('default/index.html.twig', array(
            'form' => $form->createView()
        ));
    }

    /**
     * @Route("/withdraw", name="withdraw")
     */
    public function withdrawAction(Request $request)
    {
        $atm  = new ATM();
        $form = $this->createForm(new ATMType(), $atm);

        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid())
        {
            $error  = '';
            $result = [];
            $calculator = $this->get('calculator');
            try
            {
                $result = $calculator->getResult($atm->getAmount());
            }
            catch(InvalidArgumentException $e)
            {
                $error = $e->errorMessage();
            }
            catch(NoteUnavailableException $e)
            {
                $error = $e->errorMessage();
            }

            return $this->render('default/withdrawal.html.twig', array(
                'result' => $result,
                'error'  => $error
            ));
        }

        return $this->render('default/index.html.twig', array(
            'form' => $form->createView()
        ));
    }
}
