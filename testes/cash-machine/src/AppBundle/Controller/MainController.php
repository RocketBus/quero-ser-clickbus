<?php
    // src/AppBundle/Controller/MainController.php
    namespace AppBundle\Controller;

    use Symfony\Bundle\FrameworkBundle\Controller\Controller;
    use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
    use Symfony\Component\HttpFoundation\Response;
    use Symfony\Component\HttpFoundation\JsonResponse;
    
    use AppBundle\Utils\Money;
    use AppBundle\Utils\NoteUnavailableException;
    use AppBundle\Utils\InvalidArgumentException;

    class MainController extends Controller
    {
        
        /**
        * @Route("/", name="showMeCashMachine")
        */
        public function showCashMachine()
        {
            $number = $this->numberAction(180);
            $html = $this->container->get('templating')->render('/cashmachine.html.twig',array('number'=>$number));
            return new Response($html);
        }
        
        /**
        * @Route("/getMoney/{money}", defaults={"money" = null}, name="showMeTheMoney")
        */
        public function numberAction($money)
        {
            if($money == null) return new JsonResponse(array());
            ini_set('memory_limit', '-1');
            $money = $money.'';
            try{
                if(ctype_digit($money)){
                    $money = intval($money);
                }
                else{
                    $ex = new InvalidArgumentException("El valor debe ser númerico y positivo");
                    return new Response($ex->toJson());
                }
            }
            catch(InvalidArgumentException $e){
                $ex = new InvalidArgumentException("El valor debe ser númerico y positivo");
                return new Response($ex->toJson());
            }
            
            if($money % 10 != 0){
                $ex = new NoteUnavailableException("El valor debe ser múltiplo de 10");
                return new Response($ex->toJson());
            }
            
            $mon = new Money();
            $number = $mon->dameBilletes($money);
            return new JsonResponse($number);            
        }
    }
?>