DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;

create table employees
(
    employeeId    bigserial primary key,
    first_name    text unique not null,
    last_name     text unique not null,
    department_id bigserial   not null,
    job_title     text        not null,
    gender_id     bigserial   not null,
    birthday_date date       not null
);


insert into employees (employeeId, first_name, last_name, department_id, job_title, gender_id, birthday_date)
VALUES (default, 'employee first name 1', 'employee last name 1', 0, 'job title 1', 0, '2003-02-07'),
       (default, 'employee first name 2', 'employee last name 2', 1, 'job title 1', 1, '2003-06-02');

