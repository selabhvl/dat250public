## DAT250: Software Technology Experiment Assignment 2

### Introduction

The goal of this assignment is to get to know the Java Persistence Architecture (JPA). This will include setting up a database for experimentation and study object-relational mapping.

Note that this is an **individual** assignment (see Hand-in at the end of the document).

### Experiment 1: JPA tutorial (optional)

You will complete a simple tutorial with an overview and a gentle introduction to JPA. Especially technicalities that will hinder you from completing experiment 2 will be solved here.

The goal is to set up a Java application that uses JPA for storing entities in a database.

**Initial setup:**
1. Clone the template project using git from [here](https://github.com/timKraeuter/dat250-jpa-tutorial), for example, by using [GitHub Desktop](https://www.google.com/search?q=GitHub+Desktop) or any other git-client.
2. Import the maven project into your IDEA (will be demonstrated in the Lab).
3. Run the class **Main**. It should terminate without errors.

### Experiment 2: Banking/Credit Card example JPA

Implement the domain model for credit cards similar to the Person-Address-Examples in Monday's lecture on object-relational mappings.
Pay close attention to the bidirectional associations in the domain model.

We suggest using the [template project](https://github.com/timKraeuter/dat250-jpa-tutorial) from experiment 1 to start the implementation.
Please finish Experiment 1 first if you get stuck on Experiment 2 before asking questions.

![Class Diagram domain model](https://raw.githubusercontent.com/selabhvl/dat250public/master/expassignments/pictures/creditCard.svg)

Questions:
- Where is the database? Explain the used database and how/when it runs.
- Can you provide the SQl used to create the table **Customer** (Hint: **Hibernate** is used for the object-relational-mapping)?
- Find a way to inspect the database tables being created and attach a screenshot of the database schema to your report. Do the created tables correspond to your initial thoughts regarding the exercise?

Persist the objects shown in the following object diagram into your database in the class **_CreditCardsMain_** of the project. If you need more knowledge about persistence management (entityManager-operations persist(), find(), etc...), look into the lecture notes of [Lecture 5](https://hvl.instructure.com/courses/21915/pages/lecture-5-persistence-management-and-queries?module_item_id=531426).

![Object Diagram](https://raw.githubusercontent.com/selabhvl/dat250public/master/expassignments/pictures/object-diagram.svg)

Make sure the associated test case **_CreditCardsMainTest_** runs successfully. You are **not** allowed to change the test case!

### Hand-in: short report

As hand in, you must add a markdown file called `dat250-expass2.md` to the same repository that you created as part of experiment assignment 1:

https://github.com/selabhvl/dat250public/blob/master/expassignments/expass1.md

In particular, you should write about:

- technical problems that you encountered during installation and use of Java Persistence Architecture (JPA) and how you resolved

- a link to your code for experiment 2 above. Make sure the included test case passes!

- an explanation of how you inspected the database tables and what tables were created. For the latter, you may provide screenshots.

- any pending issues with this assignment that you did not manage to solve

The hand-in should be written in **English**.
