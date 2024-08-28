# Lecture 02: Enterprise Apps

This is the demo code for the second lecture of the course DAT250 av HVL thaugt in the Fall of 2024.

This project is meant to demonstrate how the Spring framework can be used to implement _Dependency Injection_ in a slightly complicated Java project.

## Step #1: Getting Familiar

First of all the goal is to get familiar with the project, its layout and what is does. 
Start by importing it into your favourite IDE, which should be able to automatically detect the `gradle` project and download the necessary dependencies.

You can verify that everything works:
```shell
./gradlew app:test
```
and run the application with
```shell
./gradlew app:test
```
Take a moment to interpret the program output shown in the terminal. 
You may want to open the `no.hvl.dat250.l02.App` main class, play around
with the parameters (if you are very curious you may even have a look at the implementation
of the `no.hvl.dat250.l02.HowLongAlgorithm`).

Long story short is that this app calculates the hours that it will keep on raining resp.
the hours it will stay dry from a given point in time based on weather forecast data given in JSON
format. The app is made to illustrate common components, that one may find in
more complicated (smart/cloud-based/enterprise) software systems, i.e. service layers,
domain object, data repositories etc.

> **Objective 1**
> 
> Analyse the application architecture by creating a simple architecture diagram (boxes and arrows)
> with the application's classes and interfaces. Identify the "_usage_" and "_creates_" dependencies. Finally,
> suggest how you would group thoses classes into more structured packages if the app would become bigger, e.g. like `service`, 
> `domain`, `controller`, `repository`, ...


## Step 2: Add Spring

In the next step, we are going to add the _Spring Core_ framework 
to do dependency injection. The respective software package is called `spring-context` and
can be added by adding the following dependency to your `build.gradle.kts` file:

```kotlin
implementation("org.springframework:spring-context:6.1.12")
```

Afterwards, reload the project to download the dependencies.

In the following, we want to replace the hard-wired object creation
in the first few lines of the main method in `no.hvl.dat250.l02.App`
with a Spring-based dependency-injected configuration.

Thus, the following
```java
public static void main(String[] args) {
    // creation
    CommandLineResultPresenter presenter = new CommandLineResultPresenter();
    File f = new File("src/test/resources/forecast1.json");
    WeatherForecastSource source = new JsonFileBasedForecastSource(f);
    HowLongAlgorithm algorithm = new HowLongAlgorithm(source);

    // ...
}
```
should become
```java
public static void main(String[] args) {
    // ... ApplicationContext creation
    CommandLineResultPresenter presenter = context.getBean(CommandLineResultPresenter.class);
    HowLongAlgorithm algorithm = context.getBean(HowLongAlgorithm.class);

    // ...
}
```
where `context` is a variable that refers to a `ApplicationContext`-object, i.e. Spring's name
for the _IoC container_, which manages all components partaking in dependency injection.

Let's start small and create a new Spring configuration, which is a new class looking like this:
```java 
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    // empty
}
```
Next, update the main class `App` to use a Spring `ApplicationContext`:
```java
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

```

> **Objective 2**
> 
> Your next objective is to replace the explicit creation (calling `new`) of the `WeatherForecastSource` with
> a respective call to `context.getBean(WeatherForecastSource.class)`. For this, you will have to
> find out how to create Spring beans, we recommend you having a look at the 
> [official documentation for the @Bean annotation](https://docs.spring.io/spring-framework/reference/core/beans/java/basic-concepts.html).

## Step #3: Extend

In the final phase, it is about time to apply the dependency injection on a large scale!
Start reading the documentation about the [@Autowired annotation](https://docs.spring.io/spring-framework/reference/core/beans/annotation-config.html).

Is there a way to make the explicit _hard-wiring_ of Beans a bit more implicit?
For this, have a look at the [@Component annotation and component scanning (@ComponentScan)](https://docs.spring.io/spring-framework/reference/core/beans/classpath-scanning.html).

> **Objective 3**:
> 
> Replace the whole object creation part in the main method with
> Spring bean instantiation as sketched above.

Finally, you are invited to try and experiment on your own. 
Try playing around with creating different beans implementing the `WeatherForecastSource`! 
What happens? The documentation about the [@Primary](https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/autowired-primary.html)
and [@Qualifier](https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/autowired-qualifiers.html)
annotations may be good starting points? 
Can this be used for different configuration? Have a look at the test cases!
Can dependency injection applied here as well?


