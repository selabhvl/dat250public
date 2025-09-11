# Lecture 08 - Microservices

This lecture's code examples are a colorful mixture of small projects
that are meant to demonstrate different technologies that may be used 
in the context of microservices.

## sveltekit

This project illustrates the use of the application framework SvelteKit.
Svelte alone is simply a component framework for building a Single Page Application (SPA).
That application compiles down to an HTML file, a CSS file, and a rather cryptic JavaScript file.
This bundle of static resources can then be served from the web server of choice.
SvelteKit comes with its own web server (usually Node.js) and provides functionality
such as _Server Side Rendering (SSR)_, which tries to combine the benefits of SPAs and MPAs.

The project is a pure `npm` project (i.e. no gradle).
You may try to build this project, which now will create a Node.js application
rather than static assets.

```shell
cd sveltekit
npm run build
node build
```

Moreover, this application provides the frontend for the Chat application.

## Websockets

This is a Spring Boot project that demonstrates how web sockets can be used 
to write web applications where the server also can send updates (without the client having to poll).
You may have a look at the [`ChatHandler`](websocket/src/main/java/no/hvl/dat250/l07/websocket/ChatHandler.java)
and see how it is implemented using the _Observer_ design pattern.

To test the application, you have to first start the wbesocker server with
```shell
gradle websocket:bootRun # listens on port 8081
```

Then you can enter `chat` page on SvelteKit application and see how it works (i.e. you have to open several browser windwos).


## GraphQL

This is a Spring Boot project that demonstrates how GraphQl can be integrated
into a Spring application. GraphQL offers an alternative to a REST
API, where the client can specify what resource presentations he wants to have back
from the backend. Also, it is self-described by means of a schema.

Run the application with
```bash 
gradle graphql:bootRun
```

And then open <http://localhost:8082/graphiql> in your browser.

## gRPC

gRPC is a popular and efficient binary protocol that is often used for
inter-application communication. 


## Ngninx

Nginx is a very popular web server that offers a lot of functionality.
Often it is used as a _reverse proxy_ to offer a single endpoint in front of a multitude 
of internal services.

If you want to try it out, check the [documentation](https://nginx.org/en/docs/install.html)
on how to install nginx on your system. On most Linux distros and Mac OS X (if using Homebrew) this is generally
done via the package manager. On Windows, you may have to look a bit further.

Once, nginx is installed have a look at the [configuration file](ngninx/app.conf) and the 
comments therein to learn how to configure it.

Initially, it is started via

```bash
nginx -c <absolute path to>/app.conf
```

Once, it is running you can use 
```bash
nginx -s reload
```
to reload nginx after configuration changes and
```bash
nginx -s stop
```
to stop the service when you are done.

Things, you can do with it
- serve static web content,
- reverse-proxy to your other applications]
- enable TLS with a self signed certificate

When `openssl` is installed, a self-signed certificate is created with:
```java
sudo openssl req -x509 -nodes -days 365 -newkey rsa:2048 \
  -keyout selfsigned.key \
  -out selfsigned.crt
```

