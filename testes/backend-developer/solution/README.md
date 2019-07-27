# Place API CB

Built with:
- Arquitetura REST
- Spring Boot
- Maven
- Java 1.8
- MySQL
- Flyway
- Git
- Heroku: https://place-api-cb.herokuapp.com | https://git.heroku.com/place-api-cb.git

The operations is described below.

## Create a place

### Request

`POST https://place-api-cb.herokuapp.com/places`

    {
    	"name": "name",
    	"slug": "slug",
    	"city": "city",
    	"state": "state"
	}


## Edit a place

### Request

`PUT https://place-api-cb.herokuapp.com/places/{id}`

    {
    	"name": "name",
    	"slug": "slug",
    	"city": "city",
    	"state": "state"
	}

## Get a specific place

### Request

`GET https://place-api-cb.herokuapp.com/places/{id}`

## List places and filter them by name

### Request

`GET https://place-api-cb.herokuapp.com/places?name={name}`