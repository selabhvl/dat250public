# DAT250 course project

## Background / Method 

The best way to learn something is by doing!
Therefore DAT250 will be mainly about doing _something_, more concretely: you shall build a larger ("enterprise-ready") software product
using various technologies.
It is meant to be done in groups around 3-4 people, but smaller or larger groups may be acceptable if discussed with the course teachers.
You are completely _free_ in terms of terms of chosing the project methodology (agile, waterfall, ...) and technology stack.
Also use of Generative Artificial Intelligence is up to you, i.e. whether you want to use it, what concrete tools you want to use and to what degree you want to use them.
The main goal of this project is that you should know _what_ are you doing and _why_ are you doing it.
Hence, the main priority is not only to deliver a feature-complete, secure, and resiliant product but also to _reason_ about your architectural, 
functional and technology choices and _documenting_ them.
The project and it's documentation will be the basis for an "exper discussion" during the oral exam, which will determine the grade of this course.

## Product Vision

The goal is to develop an _ideal_ **Learning Management System (LMS)**: Just consider Canvas and how it is and then imagine how you would like it to be.
Bother as a student and both from the perspective of an educator that is creating courses.
The system should facilitate creating and attending courses, which contain modules that may contain a series of web pages, multi-media content, 
interactive quizzes, coding exercises, submissions etc.
Below, we formulate some _core requirements_ in terms of the domain model and some mandatory product features, but you
are free in terms of how you want to realize them and whether you want to add additional features.

### Domain Description


The system considers two primary roles of users: _students_ and _teachers_. 
A user can be both at the same time, a student in course A and the teacher for course B.
Users are identified by an e-mail address.

A course is the main entity of the system.
A course should have a title, description and a semester.
Moreover, it may comprise one or more of the following

- Static web pages, where the teacher can put course content for the students to read through. 
Web pages could contain multi-media content, e.g. pictures and videos.
- Quizzes, where students can test their knowledge. Quiz questions can be of multiple types: 
multiple-choice, ordering, matching, "fill-the-blanks", etc. Quizzes may allow several attempts or not, 
there may be hints about wrong answers.
- Coding exercises, which contain some "starter-code" (that e.g. could be checked out directly via git) and potentially
a set of visible/hidden test cases that could be useed to auto-grade submissions 
- Traditional submission exercises, where students may deliver a PDF or Word document. 

All of these elements could be structured in modules, which give an ordering to the course content.
Moreover, the visibility of elements can be restricted to specific time-periods, and quizzes, coding exercises, and 
submissions may have deadlines.
Submissions could also be done in groups. 


### Mandatory Features 

- User Login: Authentication and Authorization using state-of-the-art methods
- Persistence of all domain data using a database system of your choice 
- Web-based user interface that allows student and teacher workflows 
- Event-system that listens for all important domain events, e.g. when students interact with a quiz, make a submission (after deadline), ...

As you can see, these requirements require some refinement and concretization before you can start implementing.


### Optional Features 

Just some ideas, feel free to develop your own ideas:

- Integration of GenerativeAI, e.g. for dynamically creating content, giving feedback to submissions etc.
- Have group-matchmaking-system, e.g. through a "Tinder-style" user interface
- ...

