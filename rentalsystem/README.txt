
I tried to focus on covering all the requeriments asked in this technical test, keeping it simple, building a small but complete application.

### HOW TO RUN THE APPLICATION ###

To run the application just execute the following commands using any Command prompt, as long as the Maven environment variables have been configured.

mvn clean package
mvn spring-boot:run

Or import the project in your IDE as Maven Project and thanks to the Spring Tool plugin, it can start locally on the server (Boot Dashboard view).


### ENDPOINTS ###

As an example of the endpoints for testing the RESTful API try these endpoints in a any testing tool for Web services like Postman.

1- "Rent one or several games and calculate the price."

http://localhost:8080/rental
Method: Post

Json body:
{
	"userId":1,
	"games":[
		{
			"gameId": 1,
			"days": 1
		},
		{
			"gameId": 2,
			"days": 5
		}
	]
}

2- "Return a game and calculate surcharges (if exist)"

http://localhost:8080/return/game/1/user/1/2019-09-20
Method: Get


### WHAT IS NEEDED ###

To develope this project I worked with a bunch of libraries, frameworks and tools such as:

Spring Boot 2.0.X
Maven 3.9
Java 1.8
Spring Tool 3.9.9
MySQL Server 5+


### ASSUMPTIONS ###

1.- The start date of the rental is the current date of the system.
2.- A video game it can only be associated with one type of game.
3.- I focused on the requeriments demanded, so the CRUD operations of the tables were ommited. I provide the MySQL database script that I used with some inserts included which is in the "db" folder.