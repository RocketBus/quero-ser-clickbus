<?php

namespace AppBundle\Tests\Controller;
use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class CashMachineControllerTest extends WebTestCase
{
    public function testIndexAction()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/');

        $this->assertGreaterThan(
            0,
            $crawler->filter('form[name="cash_machine"]')->count()
        );
    }
	
	public function testEntregarDineroAction()
    {
        $client = static::createClient();

        $crawler = $client->request('POST', '/entregar_dinero',array('cantidad_solicitada'=>150));
		$numForms=$crawler->filter('form[name="dinero_entregado"]')->count();
        $this->assertTrue(1===$numForms);
    }
	
	public function testEntregarDineroNoParamsAction()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/entregar_dinero');
		$numForms=$crawler->filter('form[name="frmError"]')->count();
        $this->assertTrue(1===$numForms);
    }
	
	public function testEntregarSubmit()
    {
        $client = static::createClient();
		$crawler = $client->request('GET', '/');
		$form = $crawler->selectButton('Aceptar')->form(array(
			'cantidad_solicitada' => 250
		));
		$crawler = $client->submit($form);
		$numForms=$crawler->filter('form[name="dinero_entregado"]')->count();
        $this->assertTrue(1===$numForms);
    }
}
