# Lecture 04: Domain Modeling / (De-)Serialization

This is the code example from the 4th lecture in the course DAT250 at HVL thaught in the Fall of 2024. 
This lecture is about _domain modelling_ and also
_serializing_ and _deserializing_ them.

## Domain Modeling

Domain modelling is paramount activity in software engineering.
It will help you understand your problem better and will ease communication
- with your _clients_ and _domain experts_,
- with your _peers_,
- and your _future self_.

There is no shortcut in becoming good at this.
Reading a book definitely helps but in general it is a skill that requires training. The `resources/` directory
contains a few problem domain descriptions, feel free to analyse them and draw class diagram for each of them:

- [XML (used in the lecture)](app/src/main/resources/xml-domain.md)

### Pattern Books

People have written many books in the context of domain modelling. 
Many of these books appear as a collection of _patterns_.
A pattern here describes some sort of _best practice_ that shows an 
especially elegant way to solve a problem or model a situation.
Depending on what you are looking for, there are different pattern book
for different use cases:

- GoF
- Analysis Patterns
- Patterns of Enterprise Application Architecture
- Domain Driven Design

### UML Rendering Software

Of course, the first draft of your domain model should be created 
on a whiteboard or a piece of paper. 
However, at one point you probably want to present the result
to you colleagues. Thus, you may be looking
for something that makes you domain model more policed and fancy looking.

Some powerful tools that render text-based class diagrams to nicely looking
images are

- Mermaid.js, and 
- PlanUML

## (De-)Serialization

There are many different libraries for (de-)serializing
data into and from various formats in Java.
Here, we have chosen _Jackson_ as the go-to library. 
Originally, Jackson was focused on JSON but it has support
for many serialization formats via optional modules. 

The best starting point and documentation is the official GitHub repository:
> <https://github.com/FasterXML/jackson>

To add Jackson to your project, you add the following dependency (already done for this project):
```kotlin
implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
```

The main entry point, for anything that you want to do with Jackson
is the `ObjectMapper`. Check out the code examples, how you can write
and read using different programming models.

Finally, it is your turn.
Have a look at the challenges package and see if you can solve different 
common problems.
