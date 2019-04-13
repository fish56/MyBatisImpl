drop database if exists hello_mybatis;
create database hello_mybatis;
use hello_mybatis;

create table monkey(
  id int primary key auto_increment,
  name char(20) not null,

  phone_number int,
  birthday date
);
-- drop table monkey;

insert into monkey(
    name, phone_number, birthday
) VALUES (
    'Jon Snow', 4794062, '1989-06-04');

select * from monkey;

select LAST_INSERT_ID() from monkey;
SELECT max(id) FROM monkey;

create table article(
  id int primary key auto_increment,
  title char(20) not null ,
  author_id int ,
  content varchar(500),
  foreign key (author_id) references monkey(id)
);

insert into article(title, author_id, content) VALUES ('a title', 99999, 'dd');
-- failed, author_id not exist

insert into article(title, author_id, content) VALUES ('a title', 1, 'dd');
select * from article;

select
  article.id, title, content, monkey.name as author_name, monkey.id as author_id
from article
       left join monkey on article.author_id = monkey.id
where article.id = 2
;