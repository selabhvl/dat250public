## DAT250: Software Technology Experiment Assignment 4

### Introduction

The goal of this assignment is to do initial experiments with REST APIs and the Spark/Java framework for implementing microservices. In this assignment, you will be using the following software technology:

- The Spark/Java framework: http://sparkjava.com
- The Postman tool: https://www.postman.com
- Swagger: https://swagger.io

### Experiment 1: Spark/Java Framework project and Postman

Fork the Spark/Java [red-green-counter example](https://github.com/selabhvl/dat250-sparkjava-counter) from the lectures using GitHub. Enable workflows under the **Actions** tab in your fork such that test cases are automatically run when your code changes.
Clone your project and import it into your IDE (maven project). Use a web browser to run a GET request targeting `http://localhost:8080/counters` to check that the service is deployed and operational.

Now use the Postman tool to test the counter service by creating the following HTTP requests in a collection in Postman:

```
GET localhost:8080/counters
PUT localhost:8080/counters
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

Keep working with the forked repository from experiment 1 and also impor the maven project located in **todos**.
After importing run the testsuite **TodoAPITest**. The tests should run but fail for now.

Use the Spark/Java framework to implement a REST API for Todo-items that enables CRUD operations and uses JSON for the representation of todo resources.
We have implemented a set of test cases for the REST API. Your job is to implement the API such that all test cases pass **without** changing them.

The REST API should make it possible to Create (POST) Todo-items, Read (GET) TODO-items, Update (PUT), and Delete (DELETE) Todo-items. See slide 36 from the lectures on web services for design principles on how you should organise the resources in a hierarchical information space of todo-items. The test cases will also hint at how the API should be structured.

You may use the Spark/Java counter service project from experiment 1 as a starting point for the implementation.

**Make sure that the test suite located under src/test/java runs successfully after your implementation is finished.** A green tick should appear next to your commit in the GitHub repository since test cases are executed upon code changes. You can check those runs under the **Actions** tab.

### Experiment 3: Swagger (optional)

Investigate how Swagger may be used to specify the REST API from experiment 2.

### Experiment 4: XML representation (optional)

Investigate how the Spark/Java may be used to return and consume XML resource representations of the Todo-items.

### Hand-in: short report

As hand in, you must add a markdown file called `dat250-expass4.md` to the same repository that you created in the earlier software technology assignments.

In particular, you should write about:

- technical problems that you encountered during installation and use of Java Persistence Architecture (JPA) and how you resolved

- a link to your code for experiment 2-4 above

- any pending issues with this assignment which you did not manage to solve

The hand-in should be written in **English**.
