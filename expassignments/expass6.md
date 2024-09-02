## DAT250: Software Technology Experiment Assignment 6

### Introduction

The goal of this assignment is to make initial experiments with messaging systems and the publish/subscribe paradigm. In the experiments you will be using the RabbitMQ message broker: https://www.rabbitmq.com

### Experiment 1: Installation

Start by doing an installation as described here: https://www.rabbitmq.com/download.html.
We recommend the docker or local installation.

### Experiment 2: Hello World

Complete the **"Hello World"** tutorial at: https://www.rabbitmq.com/tutorials/tutorial-one-java.html

You can freely choose the programming language.

If you choose Java, using a Gradle/Maven project and adding the [amqp-client dependency](https://mvnrepository.com/artifact/com.rabbitmq/amqp-client) and then running the main classes from the IDE is the fastest way to complete the tutorial.

### Experiment 3: Work Queues

Complete the **"Work queues"** tutorial at: https://www.rabbitmq.com/tutorials/tutorial-two-java.html.

Work queues implement the [competing consumer pattern](https://www.enterpriseintegrationpatterns.com/patterns/messaging/CompetingConsumers.html) mentioned in the lecture.

You can freely choose the programming language.

### Experiment 4: Topics

Complete the **"Publish/subscribe"** tutorial at: https://www.rabbitmq.com/tutorials/tutorial-three-java.html

You can freely choose the programming language.

### Hand-in: short report

As hand in, you must add a markdown file called `dat250-expass7.md` to the same repository that you created in the earlier software technology assignments.

In particular, you should write about:

- technical problems that you encountered during the completion of the tutorial

- a link to your code for experiments 1-4 above

- any pending issues with this assignment which you did not manage to solve

The hand-in should be written in **English**.
