use mydb;

CREATE TABLE SEC_USER 
(
ID INT NOT NULL AUTO_INCREMENT,
FIRST_NAME VARCHAR(20),
LAST_NAME VARCHAR(20),
EMAIL VARCHAR(30),
PASSWORD VARCHAR(256), 
PRIMARY KEY (ID),
UNIQUE KEY (EMAIL)
);

CREATE TABLE SEC_ROLE 
(
ID INT NOT NULL AUTO_INCREMENT,
NAME VARCHAR(20),
PRIMARY KEY (ID)
);

CREATE TABLE SEC_USER_ROLE(
USER_ID int,
ROLE_ID int,
FOREIGN KEY (user_id)
REFERENCES user(id),
FOREIGN KEY (role_id)
REFERENCES role(id)
);

-- password doug for Pratap and john for Ajay
insert into SEC_USER(first_name,last_name,email,password) values ('Pratap','Narayan','pratap.narayan@gmail.com','$2a$10$U2STWqktwFbvPPsfblVeIuy11vQ1S/0LYLeXQf1ZL0cMXc9HuTEA2');
insert into SEC_USER(first_name,last_name,email,password) values ('Ajay','Pratap','ajay.pratap@hotmail.com','$2a$10$YzcbPL.fnzbWndjEcRkDmO1E4vOvyVYP5kLsJvtZnR1f8nlXjvq/G');

insert into SEC_ROLE values(1,'ROLE_ADMIN');
insert into SEC_ROLE values(2,'ROLE_USER');

insert into SEC_USER_ROLE values(1,1);
insert into SEC_USER_ROLE values(2,2);

select * from SEC_USER;
select * from SEC_ROLE;
select * from SEC_USER_ROLE;
