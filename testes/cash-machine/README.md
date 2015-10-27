Cash Machine
============

Problema
----------
Desarrollar una solución que simula el proceso cuando un cliente realiza un retiro en un cajero automático.

Los requisitos básicos son:

Siempre ofrecer el menor número posible de billetes;
Usted puede retirar el importe solicitado en los billetes disponibles;
El saldo del cliente es infinito;
El cajero siempre tiene todas las denominaciones y saldo.
Billetes disponibles en $100.00 ; $ 50.00; $ 20.00 y $ 10.00

Ejemplos
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

