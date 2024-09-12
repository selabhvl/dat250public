## DAT250: Software Technology Experiment Assignment 4

### Introduction

The goal of this assignment is to get to know the Java Persistence Architecture (JPA) and study object-relational mapping.

Note that this is an **individual** assignment (see Hand-in at the end of the document).

### Experiment 1: JPA tutorial (optional)

You will complete a simple tutorial with an overview and a gentle introduction to JPA. Especially technicalities that will hinder you from completing experiment 2 will be solved here.

The goal is to set up a Java application that uses JPA for storing entities in a database.

**Initial setup:**
1. Fork the template project using git from [here](https://github.com/webminz/dat250-jpa-tutorial).
2. Perform gradle run
3. Run the class **Main**. It should terminate without errors.
4. Follow the JPA tutorial at (https://github.com/webminz/dat250-jpa-tutorial/blob/master/README.md) to get used to JPA and understand the project setup.

### Experiment 2: Banking/Credit Card example JPA

Implement the domain model for credit cards similar to the Person-Address-Examples in the lecture on object-relational mappings.
Pay close attention to the bidirectional associations in the domain model.

Please finish Experiment 1 first if you get stuck on Experiment 2 before asking questions.

![Class Diagram domain model](https://raw.githubusercontent.com/selabhvl/dat250public/master/expassignments/pictures/creditCard.svg)

Questions:
- Explain the used database and how/when it runs.
- Can you provide the SQL used to create the table **Customer**?
- Find a way to inspect the database tables being created and create a database schema in your report. Do the created tables correspond to your initial thoughts regarding the exercise?

Persist the objects shown in the following object diagram into your database in the class **_CreditCardsMain_** of the project. If you need more knowledge about persistence management (entityManager-operations persist(), find(), etc...), look into the lecture notes of [Lecture 9](https://hvl.instructure.com/courses/28936/pages/lecture-9-persistence-management-and-queries-monday?module_item_id=819339).

![Object Diagram](https://raw.githubusercontent.com/selabhvl/dat250public/master/expassignments/pictures/object-diagram.svg)

Make sure the associated test case **_CreditCardsMainTest_** runs successfully. You are **not** allowed to change the test case!
If you forked the repository correctly, tests are automatically run when you push your changes.

### Hand-in: short report

As hand in, you must add a markdown file called `dat250-expass4.md`, which you should hand in via Canvas by posting a link to it.

In particular, you should write about:

- technical problems that you encountered during installation and use of Java Persistence Architecture (JPA) and how you resolved

- a link to your code for experiment 2 above. Make sure the included test case passes!

- an explanation of how you inspected the database tables and what tables were created. For the latter, you may provide screenshots.

- any pending issues with this assignment that you did not manage to solve

The hand-in should be written in **English**.
