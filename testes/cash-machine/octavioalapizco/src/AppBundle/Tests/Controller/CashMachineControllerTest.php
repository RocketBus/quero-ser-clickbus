<?php

namespace AppBundle\Tests\Controller;
use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class CashMachineControllerTest extends WebTestCase
{
    public function testExisteFormDeSolicitudEnIndex()
    {
        $client = static::createClient();
        $crawler = $client->request('GET', '/');
        $this->assertGreaterThan(
            0,
            $crawler->filter('form[name="cash_machine"]')->count()
        );
    }
	
	public function testMostrarErrorAlSolicitarDineroSinParametros()
    {
        $client = static::createClient();

        $crawler = $client->request('POST', '/entregar_dinero');
		$numErrores=$crawler->filter('div.alert-warning')->count();
        $this->assertTrue(1===$numErrores);
    }
	
	public function testEntregarDineroAlSolicitarConParametros()
    {
        $client = static::createClient();
        $client->request('POST', '/entregar_dinero',array('cantidad_solicitada'=>150));
		
		$response = $client->getResponse();
		$this->assertSame(200, $client->getResponse()->getStatusCode()); // Test if response is OK
		$this->assertSame('application/json', $response->headers->get('Content-Type')); // Test if Content-Type is valid application/json
		$this->assertEquals('{"success":true}', $response->getContent()); // Test if company was inserted
		$this->assertNotEmpty($client->getResponse()->getContent()); // Test that response is not empty
    }
	
	
	
	public function testSimulaSubmitDe250()
    {
        $client = static::createClient();
		$crawler = $client->request('GET', '/');
		$form = $crawler->selectButton('Aceptar')->form(array(
			'cantidad_solicitada' => 250
		));
		$client->submit($form);
		
		$response = $client->getResponse();
		$this->assertSame(200, $client->getResponse()->getStatusCode()); // Test if response is OK
		$this->assertSame('application/json', $response->headers->get('Content-Type')); // Test if Content-Type is valid application/json
		$this->assertEquals('{"success":true}', $response->getContent()); // Test if company was inserted
		$this->assertNotEmpty($client->getResponse()->getContent()); // Test that response is not empty
    }
}
