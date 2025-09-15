create table teachers (
    id serial not null ,
    ssn text not null,
    name text null,
    primary key (id),
    unique (ssn)
);

create table courses (
    id text not null default gen_random_uuid(),
    code text not null,
    semester char(1) not null,
    year decimal(4) not null,
    ects int not null,
    teacher int null,
    primary key (id),
    foreign key (teacher) references teachers (id)
);

create table students (
    id bigserial not null ,
    firstname text null,
    lastname text null,
    email text null,
    primary key (id)
);

create table enrollment(
    student_id bigint not null,
    course_id text not null,
    grade char(1) null,
    enrollment_date date null,
    grade_ts timestamptz null,
    primary key (student_id, course_id),
    foreign key (student_id) references students (id),
    foreign key (course_id) references courses(id)
);