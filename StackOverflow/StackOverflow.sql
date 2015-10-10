drop table if exists users;
drop table if exists questions;
drop table if exists answers;

create table users (
    username    varchar(255) primary key,
    password    varchar(255)
);

insert into users values('John','abcd');
insert into users values('Tom','abcd');
insert into users values('Jane','abcd');

create table questions (
    id          integer primary key,
    title       varchar(255),
    question     text,
	taglist     varchar(255),
    postedby   varchar(255) references users(username),
    posttime   varchar(255)
);

insert into questions values(1,'I run into a 404 error!','I have got this problem.  After I set up the hMailServer.  The tomcat server cant be started anymore. It says the ports used by Tomcat is using by other processes.  I closed all other processes but it still gives the same error.Thanks','javaee servlet', 'John' , '2011-03-05 8:01 AM');
insert into questions values(2,'Tomcat cannot restart','questions content here','javaee tomcat', 'John' , '2011-03-05 5:37 AM');
insert into questions values(3,'What are the differences between Java and C++ Generics?','questions content here','java c++ generics', 'Tom' , '2011-03-04 11:30 PM');
insert into questions values(4,'How to get a request parameter in a servlet?','questions content here','servlet', 'Jane' , '2011-03-04 7:10 PM');


create table answers (
    id          integer primary key,
    title       varchar(255),
	answer     text,
    postedby   varchar(255),
    posttime   varchar(255),
	pvote       integer,
	nvote       integer,
	accepted    varchar(10)
); 
