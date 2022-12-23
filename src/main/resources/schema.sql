DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TABLE users (
    id uuid not null,
    email varchar(255) not null,
    name varchar(255) not null,
    password varchar(255) not null,
    tag_name varchar(255) not null,
    primary key (id)
);

create table user_role (
    user_id uuid not null,
    roles varchar(255)
)