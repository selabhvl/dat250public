## DAT250: Design and Prototyping Assignment B

This is the third part of the design and prototyping assignments where you will develop an IoT-cloud software system in the form of the FeedApp application that you started designing and prototyping in assignments A and B.

The focus of this assignment is the business logic of the application and exposing the business logic of application related to users, votes, and polls as a REST service.

The application is intended to eventually showcase a broad spectrum of important IoT and cloud technology, design principles, software platforms and protocols. You will be completing the application as part of the software technology project that you will be undertaking in the second part of the course.

### Task 1 - RESTful web API and services

Use the Spark/Java framework http://sparkjava.com or Spring Boot  https://spring.io/guides/gs/rest-service/ to implement a REST API for polls and voting.

The API must enable CRUD operations and use JSON (and/or XML) for the representation of poll and voting resources. See slide 36 from the lectures on web services for design principles on how you should organise the resources for polls and votes in a hierarchical information space.

Test the API using for instance the [Postman](https://learning.getpostman.com/?_ga=2.261200462.231504413.1536569579-264554042.1522913654) tool

You may use your solution to Software Technology Experiment 4: https://github.com/selabhvl/dat250public/blob/master/expassignments/expass4.md and Software Technology Experiment 5: https://github.com/selabhvl/dat250public/blob/master/expassignments/expass5.md as as basis for implementing the business logic and as a basis for the design of the API.

### Task 2 - Data Access Integration

Connect the implementation of the REST web service from Task 1 to the data access object implemented in assignment B: https://github.com/selabhvl/dat250public/blob/master/appassignments/appassignB.md

such that users, polls, and votes can be persistently stored in a database.
