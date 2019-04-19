use mybatis_impl;

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