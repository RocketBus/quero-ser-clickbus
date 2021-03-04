# cb-place

A SpringBoot API developed for the ClickBus back-end developer challenge

## Setup
### Requirements

* Docker
* Should use Java 11 or higher

## Build

```console
./mvnw clean install
```

## Run
```console
docker-compose up
```

## Endpoints

|Method | 	Url		| 	Description |
|-------| ------- | ----------- |
|GET| /v2/api-docs| 	swagger json|
|GET|/swagger-ui.html| 	swagger html|
|GET|/api/places| 	get N places with an offset|
|GET|/api/places/search?searchTerm| 	get N places by a term (name only) with an offset|
|GET|/api/places/{id}| 	get place by id|
|POST|/api/places| 	create a place|
|PUT|/api/places| 	update a place|
|DELETE|/api/places/{id}| 	delete a place by id|