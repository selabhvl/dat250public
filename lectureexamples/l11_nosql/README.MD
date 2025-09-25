# Lecture 11: NoSQL

This example shall demonstrate how a JPA-based persistence can be replaced
with a NoSQL database.

## MongoDB

MongoDB is _document database_ and the Spring Data Mongo library allows mapping 
your domain model into _documents_ and make them accessible via repositories.

The project is configured to by default connect to a MongoDB instance running on `localhost` on port `27017`. 
If your database should be running on a different port/server, you have to adjust the respective values
in the [`MongoConfig`](src/main/java/no/hvl/dat250/nosql/config/MongoConfig.java).

In order to migrate the project from Spring Data JPA to Spring Data Mongo, you will have to do the following:
- **Comment out** on lines `34:36` in [`NoSQLApplication`](src/main/java/no/hvl/dat250/nosql/NosqlApplication.java) and **comment in** lines `28:29`.
- Replace the `CrudRepository`s with `MongoRepository` in the different repository classes.
- Replace all the JPA-`@Id` annotations in your domain entities with `@org.springframework.data.annotation.Id`,
- Also, you will have to standardize all the datatypes used for the id field to either `String` or `BigInteger` since these are the only types that Spring Data Mongo supports automatic id-generation for.
- The `startup()`-Bean in the `NoSQL` application must be adjusted so that it, instead of en `EntityManager` uses the various
repository beans (which can be auto-injected).
- Finally, you will have to resolve the symmetric references between `Vote` and `VoteOption` by potentially declaring
on side as `@Transient` and/or make use of the `@DocumentReference` annotation.


## Redis

Redis is _key/value database_ and the Spring Data Redis library allows mapping
your domain model into _hashes_ and make them accessible via repositories.

The project is configured to by default connect to a Redis instance running on `localhost` on port `6379`.
If your database should be running on a different port/server, you have to adjust the respective values
in the [`RedisConfig`](src/main/java/no/hvl/dat250/nosql/config/RedisConfig.java).

In order to migrate the project from Spring Data JPA to Spring Data Redis, you will have to do the following:
- **Comment out** on lines `34:36` in [`NoSQLApplication`](src/main/java/no/hvl/dat250/nosql/NosqlApplication.java) and **comment in** lines `31:32`.
- Add the `@RedisHash` annotation on all of your domain entities.
- The type of the id in `User` and `Poll` needs to be changed from `Long` to `String` since Redis generates UUID keys.
- The `startup()`-Bean in the `NoSQL` application must be adjusted so that it, instead of en `EntityManager` uses the various
  repository beans (which can be auto-injected).
- Finally, you will have to resolve the symmetric references between `Vote` and `VoteOption` by potentially declaring
  on side as `@Transient` and/or make use of the `@DocumentReference` annotation.

