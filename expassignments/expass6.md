# DAT250: Software Technology Experiment Assignment 6


The goal of this exercise is to gain some experience with message brokers.
This exercise is also a bit more free than the others.
Concretely, you are free in the choice of messaging protocol and message broker implementation.

Depending, on your previous experience and preferences you may choosen between:

- The AMQP protocol and [RabbitMQ](https://www.rabbitmq.com/), 
- The MQTT protocol and [Eclipse Mosquitto](https://mosquitto.org/),
- distributed Kafka Event Logs with [Apache Kafka](https://kafka.apache.org/),
- or even [Redis Pub/Sub](https://redis.io/docs/latest/develop/pubsub/).

If you do not have any experience with any message broker technology, we recommend sticking with RabbitMQ and start with working through [their tutorials](https://www.rabbitmq.com/tutorials).

## What you are building

In this exercise, we extend the poll application with some _event sourcing_ functionality:
The idea is that every `Poll` shall be accompanied with a respective topic on the message broker.
Whenever, a new Poll is created, a topic with the same name shall be registered.
Once a topic has been opened, clients may subscribe on Poll updates or they may push _poll events_ on the topic, i.e. they may vote on some option.
You will need to do the following changes to your `PollApp`:

- When creating a new `Poll` entity, a respective Topic shall be registered in the message broker,
- The `PollApp` itself must subscribe to all these topics to listen for _voting events_ (the concrete presentation of these events is up to you), 
once a _vote event_ is published the `PollApp` shall update the database accordingly (this may require to apply some changes to your domain 
model in order to react on "anonymous votes").

Finally, test your set-up up by connecting directly to the message broker with a standalone application: Try to register some votes and 
see if you are notified over new votes (e.g. when a new vote is made via the REST API).


### Hand-in: short report

As hand in, you must add a markdown file called `dat250-expass6.md` to the same repository that you created in the earlier software technology assignments.

In particular, you should write about:

- technical problems that you encountered during the completion of the tutorial

- any pending issues with this assignment which you did not manage to solve

The hand-in should be written in **English**.
