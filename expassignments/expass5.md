## DAT250: Software Technology Experiment Assignment 5

### Introduction

The goal of this exercise is to gain some experience working with a NoSQL database and how it can be integrated in a modern application tech stack.

Concretely, we will be working with Redis (you can as well choose to work with the open-source Valkey which is almost fully inter-operable). 
Redis is foremost considered a _cache_, but with switching on _persistence_ it turns into a database.
Moreover, it can also serve as a message broker and offers vector database capabilities (but that is outside the scope of this exercise).

The agenda of this exercise is as follows:

1. Install Redis/Valkey 
2. Experiment with the CLI 
3. Experiment with the Java client
4. Implement a cache 

## Installation 

There are multiple options to get your hand on Valkey/Redis.

- install it locally ([Redis](https://redis.io/docs/latest/operate/oss_and_stack/install/install-stack/),[Valkey](https://valkey.io/topics/installation/)),
- run it in a Docker container ([Redis](https://redis.io/docs/latest/operate/oss_and_stack/install/install-stack/docker/),[Valkey](https://hub.docker.com/r/valkey/valkey/)), or
- do not install it all locally but use a hosted cloud service ([Redis](https://redis.io/docs/latest/operate/rc/rc-quickstart/), [Valkey emulator **limited functionality**](https://valkey.io/try-valkey/)).


Chose the option that fits bet with your needs, your Hardware, and your experience. 
In general, we would recommend to simply install it locally.

## Command Line Interface 

The most basic way to talk to a Redis/Valkey instance is via the command line interface (CLI).

Make sure to have the 
```
redis-cli
```
installed.

You can connect to a server via:
```
redis -u redis://username:password@server:port 
```

If not further specified, the cli tries to connect to localhost on port 6379 without a username/password.

You can check whether everythin is working by sending a 
```
PING 
```
The server will respond with a `PONG`.

Redis/valkey can be thought of as persistent managed `HashMap`,
you can write key-value-pairs via `SET` and retrieve a value via `GET`.

```
SET user bob
GET user 
SET user alice 
GET user
```

Since, Redis/Valkey is often used as a Cache, you can place an expiration on your keys.
Try the following:

```
EXPIRE user 5 
TTL user 
# wait 5 seconds and then try 
TTL user 
GET user
```

Concerning Redis/Valkey datatypes: _Keys_ are just plain strings. One convention is to user the colon character (`:`) to 
create hierarchical structure on keys. For instance, you may express type information: `user:42`, `post:fa91d45b-883b-4d61-8213-8df91eebf292`.
When it comes to the _Values_, Redis/Valkey offers basically the same types that you find in most programming languages strings, integers, floats, sets, 
lists and hashes. Each datatype comes with specialized set/get operations. You may want to have a look _operation index_ ([Redis](https://redis.io/docs/latest/commands//?name=exp), [Valkey](https://valkey.io/commands/)).

Now, try the following 
- keep track of logged in users 
- store the structured information of votes for a poll 

### Use Case 1: Keep track of logged-in users

The _Set_ datatype could be used keep track of what users are currently logged in.
First, check what operations are available on the Set datatype.
Then, try to find out what operations need to be called in order to simulate the following.

1. Initial state: no user is logged in 
2. User "alice" logs in 
3. User "bob" logs in 
4. User "alice" logs off
5. User "eve" logs in 

### Use Case 2: Represent complex informatin 

Assume an object representing a `Poll` comprising the number of votes for each option:
```json
{
    "id": "03ebcb7b-bd69-440b-924e-f5b7d664af7b",
    "title": "Pineapple on Pizza?",
    "options": [
        {
            "caption": "Yes, yammy!",
            "voteCount": 269
        },
        {
            "caption": "Mamma mia, nooooo!",
            "voteCount": 268
        },
        {
            "caption": "I do not really care ...",
            "voteCount": 42
        }
    ]
}
```

Investigate the _Hash datatype_ and how it can be used to represent such a complex object (in the most recent versions 
of Redis/Valkey there is actually also a JSON type, which you may use).
Check, how you can increment the vote count for an option without having to replace the whole object. 


## Using the Java library

Your next task is to redo the above experiment by using the Java library `jedis`.
Include the following dependency into your `build.gradle(.kts)`:
```kotlin
// If you are using Redis
implementation("redis.clients:jedis:6.2.0")
// If you are using Valkey
implementation("io.valkey:valkey-java:5.4.0")

```

Both libraries come with exmples ([jedis](https://redis.io/docs/latest/develop/clients/jedis/),[valkey](https://github.com/valkey-io/valkey-java?tab=readme-ov-file#connect-to-valkey)) on how to connect to the database with a respective client object.
For instance, with the jedis lirary you connect to a local Redis server like so:
```java

public class Main {
    public static void main(String[] args) {
        UnifiedJedis jedis = new UnifiedJedis("redis://localhost:6379");

        // Code that interacts with Redis...

        jedis.close();
    }
}
```
The `jedis` object offers an [API](https://www.javadoc.io/doc/redis.clients/jedis/latest/index.html) that should look quite familiar now after the experiments on the command line.

## Implementing a Cache 

The most common use case for Redis/Valkey is to implement a Cache for frequently accessed data,
i.e. if there is some piece of information that is often requested but normally requires an 
_"expensive"_ round-trip to a (relational) database, it can be more efficient to store that 
information redundantly in a Redis/Valkey database. 

Your objective is to write a Cache for the aggregated number of votes in each poll.
Without a cache, the following query had to be executed otherwise:
```sql
SELECT o.presentationOrder, COUNT(v.id)
FROM vote_options o 
INNER JOIN votes v on o.id = v.voted_on 
WHERE o.poll = ?
GROUP BY o.presentationOrder
ORDER BY o.presentationOrder;
```

With Redis/Valkey, we can store a denormalised presentation of each by `Poll` 
that keeps track of the number of votes for each option. 

If one is about to retrieve the number of votes per option in a poll (e.g. when calling respective REST endpoint),
the logic be as follows:
1. The client checks whether the poll is cached 
2. If "yes", the numbers are returned at once and a response is send
3. otherwise, the client has to contact the database (probably via JPA) and aggregate the numbers himself
4. before returning, the client can put the current state of the poll into the cache such that 
subsequent calls my be faster.

Some things to consider:
- Selection of an appropriate Redis/Valkey datatype to store the denormalized `Poll` presentation 
- The time to live of `Poll` in the cache 
- A logic to "invalidate" a `Poll` entry in the cache in the event of a vote.


## Optional: Possible Extensions

- Try out the [Spring Data integration for Redis and their _Cache template_](https://docs.spring.io/spring-data/redis/reference/redis/redis-cache.html),
- Set up a [Redis cluster with multiple nodes](https://redis.io/docs/latest/operate/oss_and_stack/management/scaling/#create-a-redis-cluster), decide on a sharding strategy and watch how different records get distributed,
- Check if you could use Redis/Valkey as your sole database by [serializing the domain model](https://redis.io/learn/develop/java/redis-and-spring-course/lesson_6) into Hashes/Json (thus replacing JPA) 


## Hand-in: short report

As hand in, you must add a markdown file called `dat250-expass5.md` to the same repository that you created as part of experiment assignment 1:

https://github.com/selabhvl/dat250public/blob/master/expassignments/expass1.md

In particular, you should write about (if any):

- Technical problems that you encountered during installation and use of MongoDB and how you resolved

- Any pending issues with this assignment which you did not manage to solve

The hand-in should be written in **English**.
