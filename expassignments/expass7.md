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

Let us have a look at the [code](https://github.com/webminz/dat250-jpa-tutorial) from [expass4](./expass4.md) again, 
which is using the embedded [H2](https://h2database.com/html/main.html) database.
For a _production deployment_, we would like to replace this database with a proper 
relational database managament system such as [Postgres](https://www.postgresql.org/).

If you had set up a database such as Postgres on your home server before, you may agree that installing 
these types of software products can become rather complicated and difficult.
On of the main advantages of Docker is that it greatly simplifies running such software.
Instead of manually installing PostgreSQL and configuring it correctly, we simply need to get our hands
on a suitable container image and run it.

Thankfully, there is an officially maintained [docker image of PostgreSQL](https://hub.docker.com/_/postgres/).

You can download to your machine via:
```shell
docker pull postgres
```

> The image name `postgres` gets implicitly expanded to `docker.io/library/postgres:latest`, i.e.
> the image tagged as `latest`, stored in the repository `postgres`, maintained by Docker Inc. (`library`)
> and hosted on Docker Hub (`docker.io`).

Before running the image you should have a look at the [image documentation](https://hub.docker.com/_/postgres/) on DockerHub (or maybe even at the [Dockerfile](https://github.com/docker-library/postgres/blob/master/17/bullseye/Dockerfile) itself) to see what the configuration options
for this image are.

**First Exercise:**
Can you find out what `-p` and `-e` arguments you have to pass to the `docker run` command?

```shell
docker run -p {{ Find out what Port you have to expose... }} \
 -e {{ Find out what environment variables you have to set... }} \
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

Use the credentials that you previously set via the environment variables.
Once, you are logged in, create a new user for your JPA client (best practice).

```sql
CREATE USER jpa_client WITH PASSWORD 'secret';
```

After the new user has been created, switch over to the Java project.
To change the database from H2 to PostgreSQL, you first have to add the correct JDBC driver.
Open the `build.gradle.kts` file and add the following dependency:

```kotlin
implementation("org.postgresql:postgresql:42.7.4")
```

Also, replace the old connection parameters in the `persistence.xml` from the JPA example, which used H2:
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
    <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect"/>
    <property name="hibernate.connection.driver_class" value="org.postgresql.Driver"/>
    <property name="hibernate.connection.url" value="jdbc:postgresql://127.0.0.1:5432/postgres"/>
    <property name="hibernate.connection.username" value="jpa_client"/>
    <property name="hibernate.connection.password" value="secret"/>
    ...
```

Try running your Unit test now!

Are your tests passing, or do you see any errors?

Probably, your tests will be failing!

Depending on whether you had enabled the [automatic schema-generation](https://docs.jboss.org/hibernate/orm/6.1/userguide/html_single/Hibernate_User_Guide.html#configurations-hbmddl) in your `persistence.xml`, you might get errors because the tables expected by 
JPA are not present. 
You may set the value of `hibernate.hbm2ddl.auto` to `create-drop` to initialize the database on startup.
However, this is rather dangerous when it comes to production databases and might not be allowed in your environment.
Instead, it is safer to apply such SQL migrations manually.

Hibernate as your JPA implementation can automatically generate SQL `CREATE TABLE` statements from your annotated entities. 
After adding the following three lines to your `persistence.xml`:

```xml
    ...
    <property name="jakarta.persistence.schema-generation.scripts.action" value="drop-and-create"/>
    <property name="jakarta.persistence.schema-generation.scripts.create-target" value="schema.up.sql"/>
    <property name="jakarta.persistence.schema-generation.scripts.drop-target" value="schema.down.sql"/>
```
you will see that the files `schema.up.sql` and `schema.down.sql` are generated when the `PersistenceContext`
The file `schema.up.sql` will contain the `CREATE TABLE` that are needed.

You may either apply them manually via a SQL client or, more elegantly, consult the [image documentation](https://hub.docker.com/_/postgres/) on how the `/docker-entrypoint-initdb.d/` directory can be used to bootstrap a database (Tips: [use the `--mount` flag!](https://docs.docker.com/reference/cli/docker/container/run/#mount)).

Your task is to make sure that the Unit tests can be run just as before with the only difference that we 
are using a real PostgreSQL database in the background instead of the embedded H2 database.


## Building you own dockerized application


In the second part of this experiment, you shall try to containerize an existing application. 
For this, go back to your code from [expass2](./expass2.md) or [expass3](./expass3.md),
i.e. the Spring Boot application comprising a REST API for the _Poll App_ either with or 
without a SPA web user interface.
We want to package this Spring Boot application as a container image such that it is easier
to distribute it among other developers and eventually deploy it on the cloud.

Begin with selecting a suitable base image: Good candidates are the official [gradle](https://hub.docker.com/_/gradle) or [temurin](https://hub.docker.com/_/eclipse-temurin) images.

Now, write your `Dockerfile`.
Recall the following steps:

- Start with the `FROM <base image>` line,
- copy your application into the image via the `COPY` instruction,
- and install dependencies as well as package the application by executing the respective shell commands with `RUN` statements (tips: the `gradle bootJar` task can be "handy" here).
- The final instruction should be the `CMD` instruction that starts the Spring Boot Java application.

When everything works as expected, consider the following improvements/extensions:
- make sure that the app is not run as `root` user inside the container,
- use a multi-stage build to keep your image "slim" and without extra vulnerabilities,

P.S.: You may want to have a look at the examples from [Lecture 14](../lectureexamples/l14_containers/Dockerfile`).

## Delivery: Document your experiments

You shall document the two tasks (using the PostgreSQL docker image, and creating your own docker image) by writing 
it down in a markdown file, which you then deliver via Canvas.


## Optional Extensions 

If you want, you can take this experiment even further by:

- [writing a docker-compose file](https://docs.docker.com/compose/intro/features-uses/),
- [publishing you newly built image on DockerHub](https://docs.docker.com/docker-hub/repos/create/),
- [adding the image build step to your GitHub Actions CI pipeline](https://github.com/marketplace/actions/build-and-push-docker-images),
- [enabling Kubernetes in DockerDekstop](https://docs.docker.com/desktop/kubernetes/) and [create a deployment](https://kubernetes.io/docs/tasks/run-application/run-stateless-application-deployment/) for your newly built image,
- ...


