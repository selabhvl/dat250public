## DAT250: Software Technology Experiment Assignment 7

### Introduction 

The goal of this lecture is to get familiar with Docker.
Your task is to "containerize" our own application.

### Set-up

If not already done (see _containers_ section in [expass1](./expass1.md)), you should make sure that `docker` is 
installed on your system. 

For the remainder you need to be able to run `docker` from the command line.
You can use the 
```shell
docker system info
```
command to check that the system is running, i.e. the above commad should not produce an error.



## Building you own dockerized application


In the second part of this experiment, you shall try to containerize an existing application. 
You may chose between any of the previous iterations of the PollApp, e.g. [expass2](./expass2.md) (i.e. the REST API without backend)
or a full-stack application with database backend etc.
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

You shall document the above task by writing 
it down in a markdown file, which you then deliver via Canvas.


## Optional Extensions 

If you want, you can take this experiment even further by:

- [writing a docker-compose file](https://docs.docker.com/compose/intro/features-uses/),
- [publishing you newly built image on DockerHub](https://docs.docker.com/docker-hub/repos/create/),
- [adding the image build step to your GitHub Actions CI pipeline](https://github.com/marketplace/actions/build-and-push-docker-images),
- [enabling Kubernetes in DockerDekstop](https://docs.docker.com/desktop/kubernetes/) and [create a deployment](https://kubernetes.io/docs/tasks/run-application/run-stateless-application-deployment/) for your newly built image,
- ...


