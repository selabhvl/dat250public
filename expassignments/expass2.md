## DAT250: Software Technology Experiment Assignment 2

### Introduction

The goal of this assignment is to get to know the Java Persistence Architecture (JPA). This will include setting up a database for experimentation and study object-relational mapping.

Note that this is an **individual** assignment (see Hand-in at the end of the document).

### Experiment 1: JPA tutorial (optional)

You will have to complete a simple tutorial that gives you an overview and a gentle introduction to JPA. Especially technicalities that will hinder you from completing experiment 2 will be solved here.

Setup a Java application that uses JPA for storing entities in a database based on the following tutorial:

https://www.vogella.com/tutorials/JavaPersistenceAPI/article.html#installation

Use the Maven project _experiment-1_ found [here](https://github.com/timKraeuter/dat250-jpa-example) as a starting point, so that you can skip step 3 (Installation) in the tutorial.

Complete the tutorial and find a way to inspect the database tables being created - either from the IDE or by installing the [derby database server](http://db.apache.org/derby/papers/DerbyTut/index.html) locally.

### Experiment 2: Banking/Credit Card example JPA

Implement the domain model for credit cards corresponding to the small assignment that was introduced in the last lecture video on object-relationship mapping.
Use the Maven project _experiment-2_ found [here](https://github.com/timKraeuter/dat250-jpa-example) as a starting point.
Here is a screenshot of the domain model:
![Class Diagram domain model](https://raw.githubusercontent.com/selabhvl/dat250public/master/expassignments/pictures/creditCard.svg)

Find a way to inspect the database tables being created and attach a screenshot of the database schema to your report.
Do the tables created correspond to your initial answer to the exercise?

Persist the objects shown in the following object diagram into your database in the Main class of the project.

![Object Diagram](https://raw.githubusercontent.com/selabhvl/dat250public/master/expassignments/pictures/object-diagram.svg)

Make sure the associated test case **_MainTest_** runs successfully. You are **not** allowed to change the test case!

### Hand-in: short report

As hand in, you must add a markdown file called `dat250-expass2.md` to the same repository that you created as part of experiment assignment 1:

https://github.com/selabhvl/dat250public/blob/master/expassignments/expass1.md

In particular, you should write about:

- technical problems that you encountered during installation and use of Java Persistence Architecture (JPA) and how you resolved

- a link to your code for experiment 2 above. Make sure the included test case passes!

- an explanation of how you inspected the database tables and what tables were created. For the latter, you may provide screenshots.

- any pending issues with this assignment that you did not manage to solve

The hand-in should be written in **English**.
