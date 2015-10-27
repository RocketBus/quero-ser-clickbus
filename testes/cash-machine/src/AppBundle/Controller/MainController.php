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
        * @Route("/getMoney/{money}", defaults={"money" = null},name="showMeMoney")
        */
        public function numberAction($money)
        {
            if($money == null) return new JsonResponse(array());
            ini_set('memory_limit', '-1');
            try{
                if(ctype_digit($money)){
                    $money = intval($money);
                }
                else{
                    throw new InvalidArgumentException("El valor debe ser númerico y positivo");
                }
            }
            catch(InvalidArgumentException $e){
                throw new InvalidArgumentException("El valor debe ser númerico y positivo");
            }
            
            if($money % 10 != 0)
                throw new NoteUnavailableException("El valor debe ser múltiplo de 10");
            
            $mon = new Money();
            $number = $mon->dameBilletes($money);
            return new JsonResponse($number);
            
        }
    }
?>