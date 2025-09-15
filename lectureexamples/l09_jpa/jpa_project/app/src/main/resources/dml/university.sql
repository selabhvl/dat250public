insert into teachers (ssn, "name" ) values
                    ('01017069069', 'Patrick Stünkel'),
                    ('01017013337', 'Pål Ellingsen'),
                    ('01017012356', 'Lars Michael Kristensen');


insert into courses (code, semester , year, ects, teacher) values
                   ('a3840b77-4cf6-46f7-98a3-4091d660f4a5', 'ING301', 'V', 2025, 10, 1),
                   ('423bdd39-d442-4fe4-a9bd-ca28740cda82', 'DAT250', 'H', 2024, 10, 1),
                   ('938f0573-2913-4240-83da-e0754f68126a', 'DAT100', 'H', 2025, 10, 3),
                   ('72956654-bdef-481b-b73a-91839b97ce94', 'DAT250', 'H', 2025, 10, 1);

insert into students (firstname, lastname, email) values ('Evered', 'Maevela', 'emaevela0@seattletimes.com');
insert into students (firstname, lastname, email) values ('Stefania', 'Tregidga', 'stregidga1@nyu.edu');
insert into students (firstname, lastname, email) values ('Gratia', 'Rosel', 'grosel2@nih.gov');
insert into students (firstname, lastname, email) values ('Durand', 'O''Doran', 'dodoran3@liveinternet.ru');
insert into students (firstname, lastname, email) values ('Sheilakathryn', 'Veltmann', 'sveltmann4@dagondesign.com');
insert into students (firstname, lastname, email) values ('Arlin', 'Stailey', 'astailey5@feedburner.com');
insert into students (firstname, lastname, email) values ('Maridel', 'Handling', 'mhandling6@admin.ch');
insert into students (firstname, lastname, email) values ('Gill', 'Risley', 'grisley7@xing.com');
insert into students (firstname, lastname, email) values ('Denny', 'Curson', 'dcurson8@jigsy.com');
insert into students (firstname, lastname, email) values ('Cristin', 'Drennan', 'cdrennan9@msn.com');
insert into students (firstname, lastname, email) values ('Carlie', 'Coy', 'ccoya@amazon.de');
insert into students (firstname, lastname, email) values ('Nannette', 'Franken', 'nfrankenb@slideshare.net');
insert into students (firstname, lastname, email) values ('Gennifer', 'Fludder', 'gfludderc@netlog.com');
insert into students (firstname, lastname, email) values ('Rocky', 'Ghiron', 'rghirond@un.org');
insert into students (firstname, lastname, email) values ('Neddie', 'Goodding', 'ngooddinge@unesco.org');


insert into enrollment (course_id, student_id, enrollment_date, grade_ts, grade) values ('a3840b77-4cf6-46f7-98a3-4091d660f4a5', 11, '2023-09-22', '2024-12-11T13:47:50Z', 'B');
insert into enrollment (course_id,student_id, enrollment_date, grade_ts, grade) values ('a3840b77-4cf6-46f7-98a3-4091d660f4a5',11, '2023-11-08', '2024-12-07T13:14:10Z', 'A');
insert into enrollment (course_id,student_id, enrollment_date, grade_ts, grade) values ('a3840b77-4cf6-46f7-98a3-4091d660f4a5',13, '2024-01-13', '2024-12-11T16:29:23Z', 'B');
insert into enrollment (course_id,student_id, enrollment_date, grade_ts, grade) values ('a3840b77-4cf6-46f7-98a3-4091d660f4a5',1, '2023-10-24', '2024-12-14T15:40:37Z', 'B');
insert into enrollment (course_id,student_id, enrollment_date, grade_ts, grade) values ('a3840b77-4cf6-46f7-98a3-4091d660f4a5',7, '2023-10-21', '2024-12-12T23:17:29Z', 'C');
insert into enrollment (course_id,student_id, enrollment_date, grade_ts, grade) values ('a3840b77-4cf6-46f7-98a3-4091d660f4a5',5, '2024-07-12', '2024-12-09T07:05:53Z', 'D');
insert into enrollment (course_id,student_id, enrollment_date, grade_ts, grade) values ('a3840b77-4cf6-46f7-98a3-4091d660f4a5',10, '2023-11-29', '2024-12-11T03:58:40Z', 'F');
insert into enrollment (course_id,student_id, enrollment_date, grade_ts, grade) values ('72956654-bdef-481b-b73a-91839b97ce94',11, '2025-01-08', '2024-12-07T13:14:10Z', 'A');
insert into enrollment (course_id,student_id, enrollment_date, grade_ts, grade) values ('72956654-bdef-481b-b73a-91839b97ce94',13, '2025-01-13', '2024-12-11T16:29:23Z', 'C');
insert into enrollment (course_id,student_id, enrollment_date) values ('938f0573-2913-4240-83da-e0754f68126a',14, '2025-08-14');
insert into enrollment (course_id,student_id, enrollment_date) values ('938f0573-2913-4240-83da-e0754f68126a',2, '2025-09-01');
insert into enrollment (course_id,student_id, enrollment_date) values ('938f0573-2913-4240-83da-e0754f68126a',3, '2025-09-01');
insert into enrollment (course_id,student_id, enrollment_date) values ('938f0573-2913-4240-83da-e0754f68126a',4, '2025-09-01');
insert into enrollment (course_id,student_id, enrollment_date) values ('938f0573-2913-4240-83da-e0754f68126a',6, '2025-08-31');
