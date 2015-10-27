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


Aplicación Final
----------------
![alt tag](https://raw.githubusercontent.com/nullcool/quero-ser-clickbus/MiguelAngelDelMonteOrtega/testes/cash-machine/screenshots/img1.png)


Ejemplos
---------
* 
 **Entrada:** 30.00  
 **Resultado:** [20.00, 10.00]
 * 
 ![alt tag](https://github.com/nullcool/quero-ser-clickbus/blob/MiguelAngelDelMonteOrtega/testes/cash-machine/screenshots/img2.png)

* 
  **Entrada:** 80.00  
  **Resultado:** [50.00, 20.00, 10.00]
  * ![alt tag](https://github.com/nullcool/quero-ser-clickbus/blob/MiguelAngelDelMonteOrtega/testes/cash-machine/screenshots/img3.png)

* 
  **Entrada:** 125.00  
  **Resultado:** *throw NoteUnavailableException*
  * ![alt tag](https://raw.githubusercontent.com/nullcool/quero-ser-clickbus/MiguelAngelDelMonteOrtega/testes/cash-machine/screenshots/img4.png)
  Las excepciones son de hecho excepciones reales no solo se disparan así en Javascript, tienen un método que se llama toJson() pero son Excepciones reales de PHp
  * ![alt tag](https://raw.githubusercontent.com/nullcool/quero-ser-clickbus/MiguelAngelDelMonteOrtega/testes/cash-machine/screenshots/img5.png)

* 
 **Entrada:** -130.00   
 **Resultado:** *throw InvalidArgumentException*
 Desde la aplicación no se pueden ingresar valores negativos, sin embargo si se hace la consulta directa
 * ![alt tag](https://raw.githubusercontent.com/nullcool/quero-ser-clickbus/MiguelAngelDelMonteOrtega/testes/cash-machine/screenshots/img6.png)
 

* 
  **Entrada:** NULL  
  **Resultado:** [Empty Set]
  La aplicación mostrará
  * ![alt tag](https://raw.githubusercontent.com/nullcool/quero-ser-clickbus/MiguelAngelDelMonteOrtega/testes/cash-machine/screenshots/img7.png)
  sin embargo el servicio devuelve []
  * ![alt tag](https://raw.githubusercontent.com/nullcool/quero-ser-clickbus/MiguelAngelDelMonteOrtega/testes/cash-machine/screenshots/img9.png)

