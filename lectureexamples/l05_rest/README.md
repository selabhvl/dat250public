# Lecture 05: REST APIs

In this lecture, we will look at how to develop a REST web service by using the
Spring Boot framework, which under the hood uses the Spring Web MVC framework.

## Have a look

This project exemplifies how to realise a REST API for an app that suggests 
whether a user should bring an umbrella or not.
The class `SuggestionController` is the main component here.
It contains several annotations that register URL-handlers.

The `TestApi` class shows how to test this API in a unit test.

## Challenge: Make it complete

To fully test your understanding of both using and providing HTTP REST APIs, you 
can try to bind the different prepared components together in order to realise the 
original app idea. Right now, this app is basically nothing as a web storage
for JSON objects that look something like this:

```json
{
  "gadget": "SUNGLASSES",
  "hoursGuaranteed": 24
}
```

you can `GET` these for a specified location and change them via `POST`.
But the original idea is that one can create locations by sending a 
```http request
POST http://localhost:8080/suggest/locations
```
with the following payload
```json
{
  "name": "Bergen",
  "latitude": 60.388,
  "longitude": 5.326
  
}
```
to create a new location. On the inital creation, the app should retrieve the Weather Forecast
from the respective [API provided by Met.no](https://api.met.no/weatherapi/locationforecast/2.0/documentation) 
(Tips: You may have a look at Spring's [RestClient](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html#rest-restclient) here).
The `util` package contains code to parse the rather big JSON document coming from that web service into a more
manageable format that can be passed into the `HowLongAlgorithm`, which was first introduced two weeks ago
in [the lecture about enterprise architecture](../l02_enterprise_apps/rain-counter/README.md).
The result of the latter shall then be used to create the representation of a `Suggestion` object, which is
then returned by a
```http request
GET http://localhost:8080/suggest/{location}
```

Calling a `PUT` on a location-URL will trigger a refresh of the location-weather-forecast.
A `DELETE` simply deletes the registered location.
