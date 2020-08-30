## DAT250: Software Technology Experiment Assignment 2

### Introduction

The goal of this assignment is to make some initial experiment with the Java Persistence Architecture (JPA). This will include setting up a database for experimentation and study object-relational mapping.

If you encounter technical problems during this experiment assignment, then please post your questions and issues on the Canvas discussion forum for the DAT250 course: https://hvl.instructure.com/courses/13451/discussion_topics/103071

Note that this is an **individual** assignment (see Hand-in at the end of the document).

### Installation: Derby Database

Download and install the Apache Derby database by going through the tutorial which can be found here:

http://db.apache.org/derby/papers/DerbyTut/index.html

### Experiment 1: Application using JPA

Setup a Java application which uses JPA for storing entities in a database based on the following tutorial:

https://www.vogella.com/tutorials/JavaPersistenceAPI/article.html#installation

You may also use the Maven project found at:

https://github.com/lmkr/dat250-jpa-examples/tree/master/eclipselink/jpa-basic

as a starting point.

Find a way to inspect the database tables being created - either from the IDE or by logging into the database server.

### Experiment 2: Banking/Credit Card example JPA

Try to implement the domain model for credit cards corresponding to the small assignment that was introduced in the last lecture video on object-relationship mapping. Does the tables created correspond to your initial answer to the exercise.

### Hand-in: short report

As hand in, you must add a markdown file called `dat250-expass2.md` to the same repository that you created as part of experiment assignment 1:

https://github.com/selabhvl/dat250public/blob/master/expassignments/expass1.md

In particular, you should write about:

- technical problems that you encountered during installation and use of Java Persistence Architecture (JPA) and how you resolved

- a link to your code for experiment 1 and 2 above

- an explanation of how you inspected the database tables and what tables were created. For the latter you may provide screenshots.

- any pending issues with this assignment which you did not manage to solve

The hand-in should be written in **English**.
