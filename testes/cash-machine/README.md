Cash Machine
============

O Problema
----------
Desenvolva uma solução que simule a entrega de notas quando um cliente efetuar um saque em um caixa eletrônico. 

Os requisitos básicos são os seguintes:

* Sempre entregar o menor número de notas possíveis;
* É possível sacar o valor solicitado com as notas disponíveis;
* Saldo do cliente é infinito;
* Quantidade de notas é infinito;
* Notas disponíveis de R$ 100,00; R$ 50,00; R$ 20,00 e R$ 10,00

Exemplos:
---------
* 
 **Entrada:** 30.00  
 **Resultado:** [20.00, 10.00]

* 
  **Entrada:** 80.00  
  **Resultado:** [50.00, 20.00, 10.00]

* 
  **Entrada:** 125.00  
  **Resultado:** *throw NoteUnavailableException*

* 
 **Entrada:** -130.00   
 **Resultado:** *throw InvalidArgumentException*

* 
  **Entrada:** NULL  
  **Resultado:** [Empty Set]
  
[![Build Status](https://travis-ci.org/rrcfesc/quero-ser-clickbus.svg?branch=master)](https://travis-ci.org/rrcfesc/quero-ser-clickbus)

# How to use
## Requeriments

    - Docker 17.06.1 or superior

## How to test or/use

```bash
$ cp .env.dist .env
$ cp docker-compose.yml.dist docker-compose.yml
$ docker-compose up -d
$ docker exec -it clickbus_web bash
# su magento2
$ php -v
PHP 7.1.22 (cli) (built: Sep 15 2018 03:35:24) ( NTS )
Copyright (c) 1997-2018 The PHP Group
Zend Engine v3.1.0, Copyright (c) 1998-2018 Zend Technologies
    with Xdebug v2.6.1, Copyright (c) 2002-2018, by Derick Rethans
$ composer update
$ vendor/bin/phpunit --config build/phpunit.xml
```
## Tests

The tests are allowed in folder tests

