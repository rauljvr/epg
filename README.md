I tried to focus on covering all the requeriments asked in this technical test, keeping it simple, building a small but complete application.

### WHAT IS NEEDED ###

To develope this project I worked with a bunch of libraries, frameworks and tools such as:

Spring Boot 2.0.X
Maven 3.9
Java 1.8
Spring Tool 3.9.9
MySQL Server 5+


### HOW TO RUN THE APPLICATION ###

To run the application just execute the following commands using any Command prompt, as long as the Maven environment variables have been configured in advanced.

mvn clean package
mvn spring-boot:run

Or import the project in your IDE as a Maven Project and thanks to the Spring Tool plugin, so you can start it locally on the server (Boot Dashboard view).


### ENDPOINTS ###


Here the examples of the endpoints for testing the RESTful API. You can try these endpoints in a any testing tool for Web services like Postman. 

I also included Swagger configuracion to try them out throght it by going on:

http://localhost:8080/swagger-ui.html

The endpoints are:

Method GET:

1. Get an account:
http://localhost:8080/accountService/account/savings1

2. Get all accounts
http://localhost:8080/accountService/accounts

3. Get a pageable list with all accounts
http://localhost:8080/accountService/accountsPageable

Method PATCH:

4. Create an account (since I assumed it is possible to create or update others entities like currency and money when invoking this service I considerer correct to use Patch as a method)

http://localhost:8080/accountService/createAccount

Json body:
{
    "name": "savings2",
    "currency": {
        "currencyCode": "CAN"
    },
    "money": {
        "balance": 15.00
    },
    "treasury": false
}

Method PUT:

5. Making a transfer between accounts by updating their balances

http://localhost:8080/accountService/transferMoneyAccount

Json body:
{
    "fromAccount": "savings1",
    "toAccount": "savings2",
    "money": 200.00
}


### ASSUMPTIONS ###


1. Is not required to save the transfer details, so in that way I keept it simple regarding the DB model
2. The name of the account is unique in the DB and works like a primary key (it can't be changed)
3. I am not taking into account the rate exchange when making the transfer between 2 different currencies as it wasn't asked explicitily
