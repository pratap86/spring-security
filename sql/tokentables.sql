use mydb;

create table oauth_access_token (
token_id varchar(255) NOT NULL,
token blob,
authentication_id varchar(255) DEFAULT NULL, 
user_name varchar(255) DEFAULT NULL, 
client_id varchar(255) DEFAULT NULL, 
authentication blob,
refresh_token varchar(255) DEFAULT NULL, 
PRIMARY KEY (token_id));

create table oauth_refresh_token ( 
token_id varchar(255) NOT NULL,
token blob,
authentication blob,
PRIMARY KEY (token_id));

select * from oauth_access_token;

select * from oauth_refresh_token;