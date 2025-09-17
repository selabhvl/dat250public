## DAT250: Software Technology Experiment Assignment 4

### Introduction

The goal of this assignment is to get to know the **~Java~ Jakarta Persistence Architecture (JPA)** and study object-relational mapping.
Note that this is an **individual** assignment (see Hand-in at the end of the document).

### Experiment: Include JPA into the project

This experiment immediately builds on the result of [expass2](./expass2.md) and especially the domain model you have been 
developing as part of it.


As a first step you should add the dependencies for [`Hibernate`](https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core) and the [H2 Database](https://mvnrepository.com/artifact/com.h2database/h2/2.3.232)
to your `build.gradle(.kts)`:

```kotlin 
dependencies {
    implementation("org.hibernate.orm:hibernate-core:7.1.1.Final")
    implementation("jakarta.persistence:jakarta.persistence-api:3.2.0")
    implementation("com.h2database:h2:2.3.232")

    // ... more dependencies
}
```

> [!IMPORTANT]
> In this excercise we want to use Hibernate **alone**, i.e. without Springs auto-configuration magic.
> Therefore, please make sure that you don not have the _Spring Boot Starter_ dependency in your build file 
> to avoid weird exceptions:
> ```kotlin
> implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.5.5") // I must not be here ...
>```

Next, you can copy the test code into a new Java class `PollsTest`, which should be placed in the `no.hvl.dat250.jpa.polls` package underneath
the `src/test/java` source-set folder.

```java
package no.hvl.dat250.jpa.polls;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// TODO: you may have to adjust the imports to import the domain model entities
// import no.hvl.dat250.jpa.polls.Poll;
// import no.hvl.dat250.jpa.polls.User;
// import no.hvl.dat250.jpa.polls.VoteOption;
// import no.hvl.dat250.jpa.polls.Vote;


public class PollsTest {

    private EntityManagerFactory emf;


    private void populate(EntityManager em) {
        User alice = new User("alice", "alice@online.com");
        User bob = new User("bob", "bob@bob.home");
        User eve = new User("eve", "eve@mail.org");
        em.persist(alice);
        em.persist(bob);
        em.persist(eve);
        Poll poll = alice.createPoll("Vim or Emacs?");
        VoteOption vim = poll.addVoteOption("Vim");
        VoteOption emacs = poll.addVoteOption("Emacs");
        Poll poll2 = eve.createPoll("Pineapple on Pizza");
        VoteOption yes = poll2.addVoteOption("Yes! Yammy!");
        VoteOption no = poll2.addVoteOption("Mamma mia: Nooooo!");
        em.persist(poll);
        em.persist(poll2);
        em.persist(alice.voteFor(vim));
        em.persist(bob.voteFor(vim));
        em.persist(eve.voteFor(emacs));
        em.persist(eve.voteFor(yes));
    }

    @BeforeEach
    public void setUp() {
        EntityManagerFactory emf = new PersistenceConfiguration("polls")
                .managedClass(Poll.class)
                .managedClass(User.class)
                .managedClass(Vote.class)
                .managedClass(VoteOption.class)
                .property(PersistenceConfiguration.JDBC_URL, "jdbc:h2:mem:polls")
                .property(PersistenceConfiguration.SCHEMAGEN_DATABASE_ACTION, "drop-and-create")
                .property(PersistenceConfiguration.JDBC_USER, "sa")
                .property(PersistenceConfiguration.JDBC_PASSWORD, "")
                .createEntityManagerFactory();
        emf.runInTransaction(em -> {
            populate(em);
        });
        this.emf = emf;
    }

    @Test
    public void testUsers() {
        emf.runInTransaction(em -> {
            Integer actual = (Integer) em.createNativeQuery("select count(id) from users", Integer.class).getSingleResult();
            assertEquals(3, actual);

            User maybeBob = em.createQuery("select u from User u where u.username like 'bob'", User.class).getSingleResultOrNull();
            assertNotNull(maybeBob);
        });
    }

    @Test
    public void testVotes() {
        emf.runInTransaction(em -> {
            Long vimVotes = em.createQuery("select count(v) from Vote v join v.votesOn as o join o.poll as p join p.createdBy u where u.email = :mail and o.presentationOrder = :order", Long.class).setParameter("mail", "alice@online.com").setParameter("order", 0).getSingleResult();
            Long emacsVotes = em.createQuery("select count(v) from Vote v join v.votesOn as o join o.poll as p join p.createdBy u where u.email = :mail and o.presentationOrder = :order", Long.class).setParameter("mail", "alice@online.com").setParameter("order", 1).getSingleResult();
            assertEquals(2, vimVotes);
            assertEquals(1, emacsVotes);
        });
    }

    @Test
    public void testOptions() {
        emf.runInTransaction(em -> {
            List<String> poll2Options = em.createQuery("select o.caption from Poll p join p.options o join p.createdBy u where u.email = :mail order by o.presentationOrder", String.class).setParameter("mail", "eve@mail.org").getResultList();
            List<String> expected = Arrays.asList("Yes! Yammy!", "Mamma mia: Nooooo!");
            assertEquals(expected, poll2Options);
        });
    }
}

```

You may change the imports and optionally also the package definitions such that they work with your folder structure.

As you may notice, this codes assumes that a few methods are available in your domain model

In `User.java` there should at least be the following constructor and methods:
```java
    /**
     * Creates a new User object with given username and email.
     * The id of a new user object gets determined by the database.
     */
    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.created = new LinkedHashSet<>();
    }

    /**
     * Creates a new Poll object for this user
     * with the given poll question
     * and returns it.
     */
    public Poll createPoll(String question) {
        // TODO: implement
    }

    /**
     * Creates a new Vote for a given VoteOption in a Poll
     * and returns the Vote as an object.
     */
    public Vote voteFor(VoteOption option) {
        // TODO: implement
    }
```
In `Poll.java`, there should be the following method:
```java
     /**
     *
     * Adds a new option to this Poll and returns the respective
     * VoteOption object with the given caption.
     * The value of the presentationOrder field gets determined
     * by the size of the currently existing VoteOptions for this Poll.
     * I.e. the first added VoteOption has presentationOrder=0, the secondly
     * registered VoteOption has presentationOrder=1 ans so on.
     */
    public VoteOption addVoteOption(String caption) {
         // TODO: implement
    }
```
Once these methods are implemented, the compilation error in your project should be resolved.
Try running the `PollTest` now! It will most likely result in some `Exceptions` thrown by 
the Hibernate framework. This is because the domain model is no yet linked up with the database.

Thus, it is time for the main objective of this experiment: _Add the necessary JPA annotations to turn your
exisiting Poll domain model into an entity model, which can be linked with the database_.
Once, all the correct annotations are placed, the data should be persisted correctly and the test cases will be _green_.

### Resources:

There are a lot of resources out there on the topic of JPA.
As the most useful resoure, we refer to the ["Short Introduction to Hibernate"](https://docs.jboss.org/hibernate/orm/7.1/introduction/html_single/Hibernate_Introduction.html).
You may also want to have a look at an older [tutorial](https://github.com/webminz/dat250-jpa-tutorial)




### Hand-in: short report

As hand in, you must add a markdown file called `dat250-expass4.md`, which you should hand in via Canvas by posting a link to it.

In particular, you should write about:

- technical problems that you encountered during installation and use of Java Persistence Architecture (JPA) and how you resolved
- a link to your code for experiment 2 above. Make sure the included test case passes!
- an explanation of how you inspected the database tables and what tables were created. For the latter, you may provide screenshots.
- any pending issues with this assignment that you did not manage to solve

The hand-in should be written in **English**.
