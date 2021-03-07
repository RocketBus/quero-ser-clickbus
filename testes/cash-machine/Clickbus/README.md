Cash Machine - Clickbus
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
 
**Entrada:** 30.00  
**Resultado:** [20.00, 10.00]


**Entrada:** 80.00  
**Resultado:** [50.00, 20.00, 10.00]


**Entrada:** 125.00  
**Resultado:** *throw NoteUnavailableException*


**Entrada:** -130.00   
**Resultado:** *throw InvalidArgumentException*


**Entrada:** NULL  
**Resultado:** [Empty Set]


# Cash Machine ClickCa$h  by Greicy (Clickbus)

Teste em java para clickbus onde simula a utilização do caixa eletrônico.

Primeiro acesso pela tela de login validando numero da conta e senha, depois é direcionado para a tela de saque, onde as notas dsponíveis são R$100,00 / R$50,00 / R$20,00 / R$10,00.

O limite de saque e a quantidade de notas é infinito, o cliente não pode sacar valores inferiores a 10,00 e nem valores quebrados.

## Layout 
![Mobile 1](https://github.com/greicyitakura/quero-ser-clickbus/blob/master/testes/cash-machine/Clickbus/telaLogin.png)
![Mobile 3](https://github.com/greicyitakura/quero-ser-clickbus/blob/master/testes/cash-machine/Clickbus/telaSaque.png)

# Tecnologias utilizadas
- Java
- Foi utilizado GUI (Graphical User Interface) no Eclipse.

