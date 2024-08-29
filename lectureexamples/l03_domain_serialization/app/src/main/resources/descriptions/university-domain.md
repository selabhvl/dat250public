# Domain Description: University

A university offers various study programmes and courses.
Moreover, a university has a multitude of registered students and various teachers on its payroll.
A student is registered at most one study programme at any point in time but the student can 
be registered at various study programmes over time.
A student has a first- and lastname, a unique student id and at least one contact e-mail addresses.
Teachers are registered with their first- and lastname, an email address and have a salary.

A study programme is comprised of mandatory and optional courses. 
Each course has a unique course code, a course title and a number of ECTS.
Students are enrolled in zero or more courses each semester.
Each courses is taugth by at least one teacher but some courses may have many teachers. 

A student that is enrolled in a course gets a grade when completing the course.
A grade can either be a character grade on the scale A-F or a simple pass/fail.
When a student fails a course (i.e. either "F" or "fail" grade is registered), the student may have a second attempt.
However, that attempt must happen in a different semester. 

## Bonus: Teaching Assistant

We extend the above description in the sense that we open up for _teaching assistants (TAs)_. 
A student that has taken a course may become a TA for the same course in a later semester. 
This means that the student then also has a salary and that the course has not only teachers but also teaching assistants.

