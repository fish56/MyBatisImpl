use mybatis_impl;

create table article(
  id int primary key auto_increment,
  title char(20) not null ,
  author_id int ,
  content varchar(500),
  foreign key (author_id) references monkey(id)
);

insert into article(title, author_id, content) VALUES ('a title', 99999, 'content');
-- failed, author_id not exist

insert into article(title, author_id, content) VALUES ('a title', 1, 'content');

select * from article;

select
  article.id, title, content, monkey.name as author_name, monkey.id as author_id
from article
       left join monkey on article.author_id = monkey.id
where article.id = 2
;

update article
set content = 'content'
where author_id = 1;