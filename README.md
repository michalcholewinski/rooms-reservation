# rooms-reservation


#Api Description


Customers:
Fetch all customers: 
GET http://localhost:9885/api/customers

Register new customer : 
POST http://localhost:9885/api/customers
Example Body:
    {
      "firstName": "Michal",
      "lastName" : "Cholewinski",
      "email": "elo@elo.pl"
    }


Rooms:
Search for available rooms:
http://localhost:9885/api/rooms
Required parameters:
-priceMin - min price
-priceMax - max price that is taken into account
-city - city where system should find rooms - don't have to be full name (if types 'Ki' system will find every city that name starts with 'Ki')
-startDate - desired start date ISO DATE
-endDate  - desired end date ISO DATE




Example call:
http://localhost:9885/api/rooms?priceMin=100&priceMax=1000&city=Gdynia&startDate=2018-10-20&endDate=2018-10-22

Reservations:
Get all reservations:
GET http://localhost:9885/api/reservations
There is optional parameter 'userId' that allows check reservations for concrete user


Create new reservation:
POST http://localhost:9885/api/reservations
Example request body:
{
	"roomId":"1",
	"userId": "2",
	"startDate":"2018-10-20",
	"endDate": "2018-10-22"
}


Cancel reservation:
Having id of reservation we are able to cancel it by calling following endpoint with delet method:
DELETE http://localhost:9885/api/reservations/{reservationId}