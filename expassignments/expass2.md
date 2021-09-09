## DAT250: Software Technology Experiment Assignment 2

### Introduction

The goal of this assignment is to do some initial experiments with the Java Persistence Architecture (JPA). This will include setting up a database for experimentation and study object-relational mapping.

Note that this is an **individual** assignment (see Hand-in at the end of the document).

### Experiment 1: Application using JPA

Setup a Java application which uses JPA for storing entities in a database based on the following tutorial:

https://www.vogella.com/tutorials/JavaPersistenceAPI/article.html#installation

Use the Maven project found [here](https://github.com/timKraeuter/dat250-jpa-example) as a starting point, so you can skip step 3 (Installation) in the tutorial.
Start with step 4.3 to validate that all database dependencies are present and then continue the tutorial.

Find a way to inspect the database tables being created - either from the IDE or by installing the [derby database server](http://db.apache.org/derby/papers/DerbyTut/index.html) locally.

**Please add a screenshot of the generated database tables to your report.**

### Experiment 2: Banking/Credit Card example JPA

Try to implement the domain model for credit cards corresponding to the small assignment that was introduced in the last lecture video on object-relationship mapping.
Here is the domain model:
![Class Diagram domain model](https://raw.githubusercontent.com/selabhvl/dat250public/master/expassignments/pictures/creditCard.svg)

Do the tables created correspond to your initial answer to the exercise?

Persist the object-world shown in the following object diagram into your database. How can you check that the object links are saved correctly in the database?

![Object Diagram](https://raw.githubusercontent.com/selabhvl/dat250public/master/expassignments/pictures/object-diagram.svg)


### Hand-in: short report

As hand in, you must add a markdown file called `dat250-expass2.md` to the same repository that you created as part of experiment assignment 1:

https://github.com/selabhvl/dat250public/blob/master/expassignments/expass1.md

In particular, you should write about:

- technical problems that you encountered during installation and use of Java Persistence Architecture (JPA) and how you resolved

- a link to your code for experiments 1 and 2 above

- an explanation of how you inspected the database tables and what tables were created. For the latter, you may provide screenshots.

- any pending issues with this assignment that you did not manage to solve

The hand-in should be written in **English**.
