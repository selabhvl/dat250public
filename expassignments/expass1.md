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
sdk install java 11-tem
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
### IDE

Software Development in Java should be done in a full-fledged IDE rather than in Notepad.
Today there are basically two viable choices for Java IDE:

- [Eclipse](https://www.eclipse.org/downloads/packages/)
- [JetBrains IntelliJ](https://www.jetbrains.com/idea/) where there are two choices:
  -- Community Edition (open)
  -- Ultimate Edition (commercial), [free educational licenses available](https://www.jetbrains.com/community/education/#students/)

Otherwise you could also use a sophisticated Code Editor like
- [Viusal Studio Code](https://code.visualstudio.com/) or 
- [NeoVim](https://neovim.io/)

combined with a Language Server for Java: https://github.com/eclipse-jdtls/eclipse.jdt.ls
Note that the latter option is only recommended if have experience with the Language Server Protocol and its intricacies.

### Gradle

### Git

### Containers 

## Exercise: Make an application production-ready

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
