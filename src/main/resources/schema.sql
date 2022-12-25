DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

create table users (
    id uuid not null,
    created timestamp,
    updated timestamp,
    email varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    password varchar(255) not null,
    status varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
);



create table user_role (
    user_id uuid not null,
    roles varchar(255)
)