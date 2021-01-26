use auth;

insert role(role_name, description, create_date) values ("employee","employee can only access itself","2021-01-23-00-00"),("hr","hr can access everything","2021-01-23-00-00");
insert user(username, password, email, create_date) values ("admin","admin","admin@gmail.com","2021-01-23-00-00");
insert user_role(active_flag, user_id, role_id, create_date) values (1,1,2,"2021-01-23-00-00");