## DAT250: Software Technology Experiment Assignment 3

### Introduction

The goal of this assignment is to get familiar with MongoDB by following its official tutorial. This will include some preliminary reading about high level details of MongoDB, performing a local installation on each corresponding machine and setting up a small database where basic CRUD operations will be tested on it.

If you encounter technical problems during this experiment assignment, then please post your questions and issues on the Canvas discussion forum for the DAT250 course: https://hvl.instructure.com/courses/13451/discussion_topics/103071

Note that this is an **individual** assignment (see Hand-in at the end of the document).

### Getting started

MongoDB is a document database designed for ease of development and scaling. Visit its introduction page to get an overview:

https://docs.mongodb.com/manual/introduction/

Try the following **Examples** located under https://docs.mongodb.com/manual/tutorial/getting-started/ section within the embedded console provided:

0. Switch Database
1. Populate a collection (Insert)
2. Select All Documents
3. Specify Equality Matches
4. Specify Fields to Return (Projection)

### Installation: MongoDB Database

We will install the MongoDB 4.4 Community Edition as it is enough for the experiments on this assignment. **We will work with a local installation, not with the MongoDB Atlas cloud services.**

You will find installation instructions depending on the operative system here:

https://docs.mongodb.com/manual/administration/install-community/

**NOTE** that before installing MongoDB, you should validate the package using either the provided PGP signature or SHA-256 checksum.

Follow this tutorial to do so: https://docs.mongodb.com/manual/tutorial/verify-mongodb-packages/

Windows tip:

Note that if the option "MongoDB as a Windows service" is not installed, one needs to run the **mongod** instance (the server) before running the **mongo** commands which runs a client instance.

Details on how to run **mongod** can be found here: https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/#run-mongodb-from-cmd

General installation tip:

Adding your <mongo shell installation dir> to the PATH environment variable allows you to type mongo directly instead of having to first go to the <mongo shell installation dir> directory or specify the full path to the binary.
Alternatively, you can copy the mongo shell to a location on your filesystem that is already present in your PATH, such as /usr/bin on Linux.

#### Mongo Shell

The mongo shell is an interactive JavaScript interface to MongoDB. You can use the mongo shell to query and update data as well as perform administrative operations.
Further details can be found in https://docs.mongodb.com/manual/mongo/

### Experiment 1: MongoDB CRUD operations

CRUD operations stands for create, read, update, and delete documents.

Follow and do the tutorials through the CRUD operations section:

https://docs.mongodb.com/manual/tutorial/insert-documents/

https://docs.mongodb.com/manual/tutorial/query-documents/

https://docs.mongodb.com/manual/tutorial/update-documents/

https://docs.mongodb.com/manual/tutorial/remove-documents/

https://docs.mongodb.com/manual/core/bulk-write-operations/


### Experiment 2: Aggregation

Aggregation operations process data records and return computed results. Aggregation operations group values from multiple documents together, and can perform a variety of operations on the grouped data to return a single result. MongoDB provides three ways to perform aggregation: the aggregation pipeline, the map-reduce function, and single purpose aggregation methods.

We will focus on **Map-Reduce** for this experiment, but you are encouraged to take a look at Aggregation Pipeline.

#### Map-Reduce

First, read the material provided in:

https://docs.mongodb.com/manual/aggregation/

https://docs.mongodb.com/manual/core/map-reduce/

Complete the Examples tutorial (the Aggregation alternative part is not mandatory, but you can do it) from:

https://docs.mongodb.com/manual/tutorial/map-reduce-examples/

**Add an additional operation developed by you and show its the result given by its execution.**

### Hand-in: short report

As hand in, you must add a markdown file called `dat250-expass3.md` to the same repository that you created as part of experiment assignment 1:

https://github.com/selabhvl/dat250public/blob/master/expassignments/expass1.md

In particular, you should write about:

- Technical problems that you encountered during installation and use of MongoDB and how you resolved

- Screenshots for:

  - The correct validation of the installation package (https://docs.mongodb.com/manual/tutorial/verify-mongodb-packages/)

  - Relevant results obtained during Experiment 1 (it is not necessary to put a single screenshot on each substep, but at least one significant from each CRUD operation).

  - Experiment 2 example working and the additional Map-reduce operation (and its result) developed by each of you.

- Reason about why your implemented Map-reduce operation in Experiment 2 is useful and interpret the collection obtained. 

- Any pending issues with this assignment which you did not manage to solve

The hand-in should be written in **English**.
