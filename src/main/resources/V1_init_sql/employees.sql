DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;

create table employees
(
    employee_id   bigserial primary key,
    first_name    text unique not null,
    last_name     text unique not null,
    department_id bigserial   not null,
    job_title     text        not null,
    gender        text        not null,
    birthday_date date        not null
);


insert into employees (employee_id, first_name, last_name, department_id, job_title, gender, birthday_date)
VALUES (default, 'employee first name 1', 'employee last name 1', 0, 'job title 1', 'MALE', '2003-02-07'),
       (default, 'employee first name 2', 'employee last name 2', 1, 'job title 1', 'FEMALE', '2003-06-02');

