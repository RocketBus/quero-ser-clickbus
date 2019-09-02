# Places Manager

### About this application:  
This project was created with the purpose of manage a set of places by different ways.  
It's a api with some operations (list all, insert, update, search by id and search by name).

### Pre requisites:  
To start the project you will need some programs, among them:  
  
* Java 8 
* Apache Maven
* Postman to make the Http Request (Obs - on this project, a folder named useful_documents was included. On that folder a file named PlacesManager.postman_collection.json could be imported on Postman, with the requests alrealdy configured)

### Start guide:  
The following steps will help you to start the project and make requests to the api:  

* Build the app - go to the root of the project and run the command mvn package through the terminal.  
* Start the application - after the build, go to the target folder and run the command java -jar placesmanager-1.0.0.jar through the terminal.  

### Execution guide:  
The application can be tested through the Postman. The routes of the application are the following:  
* http://localhost:8080/placesmanager/places - with POST, PUT and GET methods - To create, edit and list all places respectively
* http://localhost:8080/placesmanager/places/{id} - with GET method - To get a specific place.
* http://localhost:8080/placesmanager/places?name={placeName} - with GET method - To List places and filter them by name.




