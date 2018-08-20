#Application for Rooms Reservation

##Introduction  

Backend for application that supports rooms reservation that supports following functionalites:  
* Register a customer. 
* User searches for available hotel rooms. Search criteria include: a. period b. city c. daily price range.
* User asks for room reservation for specific period. 
* User can check their reservations. 
* User can cancel his reservation.   

Technologies used in application:
* Java 8
* Spring Boot 2 (with Spring Data module for db communication purposes)
* Postgres Database
* Maven
* Docker 

##Database initialization
Thanks to Flyway there is no need to initialize database manually. It's just about running mybooking service and database will be initialized with example rooms automatically.

##Api Definition


**Customers:**  
Fetch all customers: 
GET `http://localhost:9885/api/customers`

Register new customer : 
POST `http://localhost:9885/api/customers`
Example Body:
  ```  
  {
      "firstName": "Michal",
      "lastName" : "Cholewinski",
      "email": "elo@elo.pl"
    }
  ```


**Rooms:**  
Search for available rooms:  
GET `http://localhost:9885/api/rooms`  
Required parameters:  
-priceMin - min price  
-priceMax - max price that is taken into account  
-city - city where system should find rooms - don't have to be full name (if types 'Ki' system will find every city that name starts with 'Ki')  
-startDate - desired start date ISO DATE  
-endDate  - desired end date ISO DATE  




Example call:  
`http://localhost:9885/api/rooms?priceMin=100&priceMax=1000&city=Gdynia&startDate=2018-10-20&endDate=2018-10-22`  

**Reservations:**    
Get all reservations:  
GET `http://localhost:9885/api/reservations`  
There is optional parameter 'userId' that allows check reservations for concrete user  


Create new reservation:  
POST `http://localhost:9885/api/reservations`  
Example request body:  
```
{
	"roomId":"1",
	"userId": "2",
	"startDate":"2018-10-20",
	"endDate": "2018-10-22"
}
```  


Cancel reservation:  
Having id of reservation we are able to cancel it by calling following endpoint with delet method:  
DELETE `http://localhost:9885/api/reservations/{reservationId}`  

##Restrictions
This is only test application. There are still some things that should be improved.  
* Test - as this is only simple PoC, integration test are written in a way that are using test migrations scripts as well as production migration scrips to populate database. To fix it these scripts should be separated to make test as much independent as possible.
* Security - there is no security matters implemented. There are no check in code if proper user uses particular features.

##Author
**Michał Cholewiński**, Software Engineer  
[cholewinskimichal.com](http://cholewinskimichal.com)  
[Eager To IT](http://eagertoit.com)