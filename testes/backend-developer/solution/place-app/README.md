# Projeto app-place - API REST

# Dependências
O projeto utiliza maven para gerenciar as dependências.

 - Java 8
 - Spring Boot
 - Spring Data
 - Docker
 - Swagger
 - Lombok
 - Mockito
 - Junit
 - H2

# Instalação 
mvn install
docker build -t app-place
docker run -d --name app-place -p 8888:8888 app-place

# JDBC Url usar H2 DB arquivo para persistir os dados.
spring.datasource.url=jdbc:h2:file:~/testedb;

# Usar H2 DB Driver
spring.datasource.driver-class-name=org.h2.Driver

## Testes
-Testes Unitários podem ser feitos via IDE;

Exemplos:
 Todos os endpoint estão no swagger abaixo:
 http://ip:8888/swagger-ui.html

- Criar uma nova place
POST - http://ip:8888/place
Body:
{"name": "string", "slug": "string", "state": "string", "city": "string"}

- Editar uma place
PUT - http://ip:8888/place/{id}
Body:
{"name": "string", "slug": "string", "state": "string", "city": "string"}

- Buscar todas as places
GET - http://ip:8888/place

- Buscar uma Place espcifica
GET - http://ip:8888/place/{id}

- Busca place por name
GET - http://ip:8888/place?search=placeName

## Ferramentas
-Spring Tools Suite;

-PostMan para realizar as chamadas aos serviços (https://www.getpostman.com/apps), pode ser utilizado qualquer outro correspondente;
