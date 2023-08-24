# DAT250: Software Technology Experiment Assignment 1

## Introduction

The goal of this assignment is to ensure that you have a working Software Development Environment installed on your computer, which will be needed in the remainder of this course. As part of the installation, you will have to validate that the installed software development environment is working properly.

If you encounter problems during the installation of the software development environment, then please post your questions and issues on the Canvas discussion forum for the DAT250 course.

Note that this is an **individual** assignment (see Hand-in at the end of the document).

## Installation: Software Development Environment

You must make sure that you have the following installed on your PC:

- Java Development Environment (JDK),

- An Integrated Development Environment (IDE) or sufficiently sophisticated code editor, 

- The _Gradle_ software build tool,

- A _Git_ client,

- An environment to execute _containers_ like _Podman_ or _Docker_.

In addition, you must make sure that you have a GitHub as well as an account in DockerHub.

### Java

The minimum requirement for this course is a Java Development Kit (JDK), i.e. availability of the binaries `java` and `javac` on the `$PATH`, in version _11_ (class file format: `>= 55`).

With the acquisition of Sun Microsystems by Oracle and the associated changes to the licensing of the original Java virtual machine and JDK, a plethora of Java distributions has become available, e.g. Oracle JDK, OpenJDK, Eclipse Adoptium, Amazon Coretto, etc.

To ease the management of multiple Java editions on one machine, we recommend using [SDKman](https://sdkman.io).

Follow the instructions [here](https://sdkman.io/install) to install _SDKman.
**Note**: If you are using windows, you first have to either install [Windows Subsystem for Linux (WSL)](https://learn.microsoft.com/en-us/windows/wsl/install) or install a CYGWin environment (If you install the Git SCM client for windows you will actually get such a CYGwin environment via _Git-Bash_).

After you have _SDKman_ installed, you can easily install and switch between JDKs.

Type
```bash
sdk install java
```
to install the latest JDK.
After installation you will be prompted whether you want to use it as your default:
Just say (Y)es!

Otherwise, you can look for a specific version via
```bash
sdk list java
```
and install it e.g. with
```bash
sdk install java 11.0.20-tem
```
to install Java 11 delivered by Eclipse Adoptium Temurin.

You can show the JDK currently in use (i.e. where the `java` and `javac` are pointing to) via
```bash
sdk current
```
and switch to another installed version via
```bash
sdk use java <version>
```

You can test, that java is installed by running

```bash
java -version
javac -version
```

The output should be something like:
```
<...>jdk version "mm.n.n" YYYY-MM-DD
<...>JDK Runtime Environment (build <...>)
```
where `mm` is a number `>= 11`.

### IDE

Software Development in Java should be done in a full-fledged IDE rather than in Notepad.
Today there are basically two viable choices for Java IDE:

- [Eclipse](https://www.eclipse.org/downloads/packages/)
- [JetBrains IntelliJ](https://www.jetbrains.com/idea/) where there are two choices:
  - Community Edition (free)
  - Ultimate Edition (commercial), [free educational licenses available](https://www.jetbrains.com/community/education/#students/)

Otherwise, you could also use a sophisticated Code Editor like
- [Viusal Studio Code](https://code.visualstudio.com/) or 
- [NeoVim](https://neovim.io/)

combined with a Language Server for Java: https://github.com/eclipse-jdtls/eclipse.jdt.ls
Note that the latter option is only recommended if have experience with the Language Server Protocol and its intricacies.

### Gradle

In the course, we will use [Gradle](https://gradle.org/) as a _build tool_. 

You can install it simply via _SDKman_:
```bash
sdk install gradle
```

To check that is is working run 
```bash
gradle -v
```
in a terminal.

### Git

Git has become a de-facto standard for software versioning and we will use it throughout the course to share code examples.

On Linux and Mac machines, git is usually already installed. 
If you are using Windows, you can install a git client from [here](https://git-scm.com/download/win) (The latter will also install a Unix-like shell on your machine called `Git Bash`).

You can verify the installation by running
```bash
git -v
```
which should print out the version number of the git client you are using.

### Containers 

Containers are a more recent approach of delivering a whole application encapsulated with all its dependencies. They simplify portability of software application to a high degree and becoming more and more the de-facto standard for deployment of applications (especially in the cloud).

The underlying technology that is enabling containers is part of the Linux kernel. 
However, it is also possible to emulate this functionality on Windows or Mac via specialized clients.

The most popular options are `docker`, `podman` or `containerd`.
For this course, we recommend [Podman](https://podman.io/) because it has a lower resource footprint than other clients.
If you have already been using another client, like for example Docker, you can simply keep using that one ;-) (Podman and Docker share exactly the same command line interface, i.e. you can treat `podman` as an alias for `docker`).

Read the [instructions](https://podman.io/docs/installation) on how to install Podman on your machine.

Also, you need to create an account on [DockerHub](https://hub.docker.com/), which is free of charge.
On DockerHub you can upload containers, i.e. packaged applications that are directly for production.

## Exercise: Make an application production-ready

To check that your local installation is ready for professional software development, your task now is to make an existing program _"production-ready"_.
This will get you a feel of how you have to set up your projects later in the course.

[Behind this link](https://raw.githubusercontent.com/selabhvl/dat250public/master/expassignments/code/App.java) you will find a Java web application that allows to perform some simple _unit conversions_ (feet to meter, inch to feet etc.). Currently, this app has several issues:
- the whole program is contained in a single method inside a single file (i.e. lacking modularity),
- it depends on external libraries that are not included in the file,
- there are no tests or other means of quality assurance,
- there is currently no way of automatically building and deploying it into production,
- ...

### Step 1: Source code versioning

As a first step, you should put the whole project into a version control system in order to track changes, jump back to previous versions and enable collaboration with other developers.
Start by creating a GitHub repository:

1. Log in to your GitHub account
2. Visit the dashboard (https://github.com/) and in the upper right corner click on the small `+` icon
3. Select `New repository`
4. Give a meaningful name, e.g. `dat250assignment1` and select `PUBLIC` repository type
4. Optionally, you can add a `README`, `License`, and the `Java`-`.gitignore`-template
5. Clone the newly created repository onto your machine using `git clone <repo-url>`

### Step 2: Set up build system 

Next, we gonna set up `gradle` as our build system for this app. This allows for making the whole build process reproducible such that other developers can build our app also or allows us to run the whole build on a _continuous integration (CI)_ server. 

1. Open up a command line shell and navigate to the location of the repository you just cloned.
2. Run `gradle init` in that location and pick the following settings:
```
Project type: 2 (application)
Implementation language: 3 (Java)
Subprojects: 1 (no)
Build script DSL: 2 (Kotlin)
New API behaviour: no
Test framework: 4 (Junit Jupiter/JUnit 5)
Project name: ...      # choose something meaningful, e.g. 'dat250exp1'
Source package: ...     # choose something meaningful, e.g. 'no.hvl.dat250.<studid>.exp1'
```
When the wizard has finished, browse the resulting directory layout, explore the contents of the `app/build.gradle.kts` and the `settings.gradle.kts` files.
Try executing the following commands and see how the contents of the `app/build` folder change after each command. 

In the following, I assume that you are using a UNIX shell enviroment (i.e. `sh`, `bash`, `zsh`, `fish` on a Linux or Mac, MinGW, Git-Bash or WSL if you are using Windows).
If you are using the Windows CMD or PowerShell, however, please replace `./gradlew` with `gradlew.bat` in the following!

```bash
./gradlew check
./gradlew build
./gradlew run
```

Now, that we have checked that the build infrastructure is working as it should, it is time to replace the placeholder code that was generated by `gradle init` with the given startercode.
For this, open [this URL](https://raw.githubusercontent.com/selabhvl/dat250public/master/expassignments/code/App.java), select everything, copy it into your clipboard, and overwrite with it the contents of `App.java`.

Finally, let us add our work to version control. 
You should check in at least the following files:

- `App.java`
- `AppTest.java`
- `build.gradle.kts`
- `settings.gradle.kts`
- `gradle/wrapper/gradle-wrapper.jar`
- `gradle/wrapper/gradle-wrapper.properties`
- `gradlew`
- `gradlew.bat`

The remaining files should be ignored by adding respective entries to your `.gitignore` (if they are not already present).
Commit your additions and push them to GitHub.

You can use your favourite git client for this, or simply use the command line interface by issuing the following commands:
```bash
git add <file1> <file2> ...
git commit -m "<commit message by you>"
git push 
```

### Step 3: Fix compilation errors

It is now time to open the project in your favourite IDE/editor. 
Note that some IDEs provide integration for gradle and are able to automatically detect the project structure and import it directly into the IDE. 
When opening the `App.java` file, you should detect some compilation problems.
First, the `package` declaration of the class is most likely not consistent (because missing) with the package structure generated by your choice of the _source package_ during the gradle initialization wizard. 
This problem can easily be fixed by adding a respective first line to the class.
The second problem is caused by a missing dependency towards [Javalin](https://javalin.io/).
The latter is a library that easily facilitates the development of (REST) web applications in a simple manner.
According to [their documentation](https://javalin.io/documentation), this issue can easily be resolved by adding a dependency to the [following artifact](https://central.sonatype.com/artifact/io.javalin/javalin/5.6.2) from [Maven Central](https://search.maven.org/).
Copy and paste the correct dependency into the `dependencies { ... }` section of your `build.gradle.kts`.

When all compilation errors are resolved, try 
```bash
./gradlew run
```
in your terminal and visit [http://localhost:9000/](http://localhost:9000/) in your web browser:
Can you find out how many meters 30 feet are?

If everything is working as expected: **Do not forget to commit**.

Before proceeding to step four: Can you also get rid of the

> Javalin: It looks like you don't have a logger in your project.

message that comes up when you start the application?

### Step 4: Quality Assurance

Now, it looks like that application is behaving more or less like it should.
Still, you might agree that the code quality has some room for improvement:wink:.
The whole application is contained in one single `main` method.
This makes the code less clear and also impossible to test.
Try to [refactor](https://martinfowler.com/books/refactoring.html) your code by extracting the core functionality (unit conversion) into a seperate method with well-defined input paramters and return values.
Add a `Javadoc` comment (starting with `/** ...`) for this method and write some test cases for it (Thus replacing the content of `AppTest.java` with some _real_ tests.

Try running 
```bash
./gradlew check
```
several times and see what happens when a test case fails.

### Step 5: Package Application

Finally, we want others to be able to deploy our app in their infrastructure and avoid the classic _"It worked on my machine"_ fallacy.
Today, containers are emerging as the de-facto standard for deploying applications both locally and in the cloud.
Containers are a way of packaging an application together with it's system-level dependencies.
They are based on a feature of the Linux operating system, which enables virtually isolated machines to share the operating system kernel.
To run and build containers, you need a container runtime.
One of the most known commercial container runtimes is [Docker](https://www.docker.com/).
In the course, we recommend [Podman](https://podman.io/) because of its lower resource footprint.
But eventually it is you choice what tool you will be using.
In the following, we will simply refer to the binary `podman` but feel free to alias this to `docker` or any other compatible container runtime.

_Containerization_ describes the process of packaging the application into a container.
Practically, this means adding a `Dockerfile` (make sure that the file has exactly this name) to your project.
A Dockerfile describes how to build layers onto an existing _base image_ (i.e. a linux distribution with some core libraries and binaries), which contain all dependencies and your apps.
A layer is built by executing a single Linux command. 
For a complete reference, we refer to the [documentation](https://docs.docker.com/engine/reference/builder/).

For a quick start, you can just follow along [this guide](https://docs.docker.com/language/java/build-images/), which explains how Java applications can be containerized.
A viable starting point for your app could be the following Dockerfile:
```docker
FROM eclipse-temurin:11.0.20_8-jdk
COPY settings.gradle.kts .
COPY gradlew .
RUN mkdir -p gradle/wrapper
COPY gradle/* gradle/wrapper
RUN chmod +x gradlew
COPY app ./app
RUN ./gradlew build
CMD ['./gradlew', 'run']
```

You can build the image using
```bash
podman build -t unit-converter .
```

You can list the locally available container images using:
```
podman images
```
Can you find your application? Are there more images other than the application you just build?

Finally, we are able to upload the container images to DockerHub.
Log in to your account, click on your username in the upper right corner, and click on `Account Settings`.
Pick the `Security` tab and choose `New Access Token`.
Give it a meaningful name and choose at least `Read & Write` privileges.
Copy the password string and enter the following command:
```bash
podman login -u <your docker.io username> docker.io
```
When asked for the password, past the content of your clipboard.
You should see a message that you are successfully logged in.

Next, you have to create a repository where you can push your images.
Go back to the main page and click on `Create repository`.
Choose `dat250` as the name and visibility: `Public`.
Now you should be able to push your image via

```bash
podman push unit-converter docker://docker.io/<your username>/dat250
```

Afterwards, you should see the image being available on DockerHub.
Make sure to include its URL in the final report.
Congratulations, now everyone is able to download and deploy your app!

### Bonus tasks

Congratulations, you have set have the infrastructure to build and package professional software systems.
If you are interested, you may try one of the following 

- [Calculate Code Coverage of your Unit Tests](https://docs.gradle.org/current/userguide/jacoco_plugin.html)
- [Analyze Code Quality via SonarQube](https://docs.sonarsource.com/sonarqube/latest/analyzing-source-code/scanners/sonarscanner-for-gradle/)
- [Set up a CI/CD pipeline that automatically builds your project on every commit to GitHub and publishes the result on DockerHub](https://docs.docker.com/language/java/configure-ci-cd/)
- [Set up Ascidoctor to produce a professional documenation of your app](https://docs.docker.com/language/java/configure-ci-cd/)
- ...


## Hand-in: short report

As hand in, you must create a repository on GitHub and inside the repository add a markdown file called `dat250-expass1.md`, containing a short laboratory report documenting what you have done.

You can find information about markdown here: https://guides.github.com/features/mastering-markdown/

In particular, you should write about:

- technical problems that you encountered during installation of the software development environment and how you have solved them
- how you have *validated* (checked) that the software development environment is working
- technical problems and how you (tried to) solve them, 
- any pending issues with this assignment which you did not manage to solve.

**Please add the URL to your published container on DockerHub in the report.**

The hand-in must be written in **English**!
