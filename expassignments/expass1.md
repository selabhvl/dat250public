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

Otherwise you could also use a sophisticated Code Editor like
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

Next, we gonna set up `gradle` as our build system for this app. This allows for making the whole build process reproducable such that other developers can build our app also or allows us to run the whole build on a _continuous integration (CI)_ server. 

1. Open up a command line shell and navigate to the location of the repository you just cloned.
2. Run `gradle init` in that location


**TODO: patrick**

## Hand-in: short report

As hand in, you must create a repository on GitHub and inside the repository add a markdown file called `dat250-expass1.md`, containing a short laboratory report documenting what you have done.

You can find information about markdown here: https://guides.github.com/features/mastering-markdown/

In particular, you should write about:

- technical problems that you encountered during installation of the software development environment and how you have solved them
- how you have *validated* (checked) that the software development environment is working
- technical problems and how you (tried to) sove them, 
- any pending issues with this assignment which you did not manage to solve.

**Please add the URL to your published container on DockerHub in the report.**

The hand-in must be written in **English**!
