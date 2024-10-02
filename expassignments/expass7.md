## DAT250: Software Technology Experiment Assignment 7

### Introduction 

The goal of this lecture is to get familiar with Docker.
We will look both at using "containerized" standard software as well as "containerizing" our own applications.

### Set-up

If not already done (see _containers_ section in [expass1](./expass1.md)), you should make sure that `docker` is 
installed on your system. 

For the remainder you need to be able to run `docker` from the command line.
You can use the 
```shell
docker system info
```
command to check that the system is running, i.e. the above commad should not produce an error.


### Using a Dockerized application: PostgreSQL

Let us have a look at the code example from [expass4](./expass4.md) again:
This [code](https://github.com/webminz/dat250-jpa-tutorial) is using the embedded H2 database.
We want to replace this with a real production such as [Postgres](https://www.postgresql.org/).
Those, who had set up a database such as Postgres on their home servers may agree that installing 
these types of software products can become rather complicated and difficult.
On of the main advantages of Docker is that it greatly simplifies running such software.
Instead of manally installing PostgreSQL and configuring it correctly, we simply need to get our hands
on a suitable container image and run it.

Thanfully, there is an officially maintained [docker image of PostgreSQL](https://hub.docker.com/_/postgres/).

You can download to your machine via:
```shell
docker pull postgres
```

Before running the image you should have a look at the image documentation on DockerHub (or even at the [Dockerfile](https://github.com/docker-library/postgres/blob/master/17/bullseye/Dockerfile) itself) to seet what the confiuration options are.

Can you find out what `-p` and `-e` arguments you have to pass to the `docker run` command 

```shell
docker -p << Find out what Port you have to expose... >> \
 -e << Find out what environment variables you have to set... >> \
 -d --name my-postgres --rm postgres
```

As soon as the container is started, check that it is running with
```shell
docker ps
```

and inspect the logs of the startup process with
```shell
docker logs my-postgres
```

If everything looks alright, try to connect to the database using you favourite SQL client, e.g. [DBeaver](https://dbeaver.io/)
or the Database client, which is integrated into IntelliJ.

Use the credentials that you previously set through the environment variables.
Once, you are logged in, create a new user for your JPA client.

```sql
CREATE USER jpa_client WITH PASSWORD 'secret';
```

Replace the old connection parameters in the `persistence.xml` from the JPA example, which used H2:
```xml
    ...
    <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>
    <property name="hibernate.connection.driver_class" value="org.h2.Driver"/>
    <property name="hibernate.connection.url" value="jdbc:h2:file:./DB;DB_CLOSE_DELAY=-1"/>
    ...
```
with the PostgreSQL connection:
```xml

    ...
    <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>
    <property name="hibernate.connection.driver_class" value="org.hibernate.dialect.PostgreSQLDialect"/>
    <property name="hibernate.connection.url" value="jdbc:postgresql://127.0.0.1:5432/postgres"/>
    <property name="hibernate.connection.username" value="jpa_client"/>
    <property name="hibernate.connection.password" value="secret"/>
    ...
```

Try running your Unit test now?
What do you see?
Probably, your tests will be failing!
This is because JPA might not automatically generate the DDL statements for you here.
And this is also rather dangerous when it comes to production databases. 
Instead, you should rather apply such SQL migrations manually.

First, let us obtain the DDL statements.
They can be automatically generated from your annoted clases. 
Add the following three lines to your `persistence.xml`:

```xml
    ...
    <property name="jakarta.persistence.schema-generation.scripts.action" value="drop-and-create"/>
    <property name="jakarta.persistence.schema-generation.scripts.create-target" value="schema.up.sql"/>
    <property name="jakarta.persistence.schema-generation.scripts.drop-target" value="schema.down.sql"/>
```
When running your tests again, you will see that the new files `schema.up.sql` and `schema.down.sql` are generated.
The former contains the `CREATE TABLE` statements that creates the table structure.

You may either apply them manually via a SQL client or, more elegantly, consult the [image documentation](https://hub.docker.com/_/postgres/) on how the `/docker-entrypoint-initdb.d/` directory can be used to bootstrap a database (Tips: use the `--volume` flag!).


## Building you own dockerized application


In the next stage, you shall try to containerize an existing application. 
For this, go back to your code from [expass2](./expass2.md) or [expass3](./expass3.md),
i.e. the Spring Boot application comprising a REST API for the _Poll App_, either with or 
without SPA web user interface.
We want to package this Spring Boot application as a container image such that it is easier
to distribute it amongst other developers and eventually deploy it on the cloud.

Begin with selecting a suitable base image: Good candidates are the official [gradle](https://hub.docker.com/_/gradle) or [temurin](https://hub.docker.com/_/eclipse-temurin) images.

Now, write your `Dockerfile`.
Recall the following steps:

- `FROM <base image>`
- copy you application into the image via the `COPY` instruction
- install dependencies and package the application (tips: have a look at the Dockerfile `RUN` instruction and the `gradle bootJar` task)
- The final instruction should be the `CMD` instruction that starts the Spring Boot Java application.

When everything works as expected, consider the following improvements/extensions:
- make sure that the app is not run as `root` user
- use a multi-stage build to make your image slim
- write a GitHub Action workflow that automatically publishes the image to DockerHub.

P.S.: You may want to have a look at the examples from [Lecture 14](../lectureexamples/l14_containers/).




**TODO**
