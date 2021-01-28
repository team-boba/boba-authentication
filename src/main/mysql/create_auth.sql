drop database if exists auth;
create database auth;
use auth;

create table user (
	id int auto_increment primary key,
    username varchar(200) not null unique,
    password varchar(200) not null,
    email varchar(200) not null,
    create_date varchar(200) not null
);

create table role (
	id int auto_increment primary key,
    role_name varchar(200) not null unique,
    description varchar(500),
    create_date varchar(200) not null
);

create table user_role (
	id int auto_increment primary key, 
    user_id int not null,
    role_id int not null,
    active_flag boolean not null default 1,
    create_date varchar(200) not null,
	foreign key (user_id) references user(id) on update cascade on delete cascade,
    foreign key (role_id) references role(id) on update cascade on delete cascade
);

create table registration_token (
	id int auto_increment primary key, 
    token varchar(500) not null,
    valid_duration int not null,
    email varchar(200) not null,
    create_by varchar(200) not null
);
