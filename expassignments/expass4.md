## DAT250: Software Technology Experiment Assignment 4

### Introduction

The goal of this assignment is to implement a simple REST API using the Spring framework. In this assignment, you will be using the following software technology:

- The Spring framework: [https://spring.io/](https://spring.io/)
- The Postman tool: [https://www.postman.com](https://www.postman.com)
- Swagger: [https://swagger.io](https://swagger.io)

### Experiment 1: Spring project and Postman

Fork the Spring project [counters and todos](https://github.com/selabhvl/dat250-spring-counters-todos) from the lectures using GitHub. Enable workflows under the **Actions** tab in your fork such that test cases are automatically run when your code changes.
Then clone and import your project into your IDE (maven project). You can use any git client, but we suggest [GitHub Desktop](https://www.google.com/search?q=GitHub+Desktop) or the git client integrated into IntelliJ IDEA.

Start the webserver by running the main class in *RestServiceApplication.java*. Use Postman to run a GET request targeting `http://localhost:8080/counters` to check that the service is deployed and operational.

Now use Postman to test the counter service by creating the following HTTP requests in a collection in Postman:

```
GET localhost:8080/counters
PUT localhost:8080/counters
GET localhost:8080/counters
```

and execute them.

You should use the following JSON representation of a counter resource in the body of the HTTP PUT request:

```
{
    "red": 3,
    "green": 2
}
```

### Experiment 2: REST API for TODO-items

Keep working with the project from experiment 1 and go to the package **todos**.
Run the test suite **TodoControllerTest**. The tests should run but fail for now.

Use the Spring framework to implement a REST API for Todo-items that enables CRUD operations and uses JSON to represent todo resources.
We have implemented a set of test cases for the REST API. You'll need to implement the API so that all test cases pass **without** changing them.

The REST API should make it possible to Create (POST) Todo-items, Read (GET) TODO-items, Update (PUT), and Delete (DELETE) Todo-items. Please look at slide 36 from the lectures on web services for design principles on how to organize the resources in a hierarchical information space of todo-items. The test cases will require the API to be structured correctly.

**Make sure that the test suite located under src/test/java runs successfully after your implementation is finished:**

A green tick should appear next to your commit in the GitHub repository since test cases are executed upon code changes. You can check those runs under the **Actions** tab.

### Experiment 3: Swagger (optional)

Investigate how Swagger may be used to specify the REST API from experiment 2.

### Experiment 4: XML representation (optional)

Investigate how the Spring may be used to return and consume XML resource representations of the Todo-items.

### Hand-in: short report

As a hand-in, you must add a markdown file called `dat250-expass4.md` to the same repository you created in the earlier software technology assignments.

In particular, you should write about:

- technical problems that you encountered during installation and how you resolved

- a link to your code for experiments 2-4 above

- any pending issues with this assignment that you did not manage to solve

The hand-in should be written in **English**.
