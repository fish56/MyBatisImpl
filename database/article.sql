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

-- 下面的不用执行，只是展示常用的sql语句

select * from article;

select
  article.id, title, content, monkey.name as author_name, monkey.id as author_id
from article
       left join monkey on article.author_id = monkey.id
where true
  and author_id = 1
order by article.id desc
limit 2
offset 1
;

update article
set content = 'content'
where author_id = 1;

select count(*) as countOfAnyAuthor
from article
where author_id = 1;

select * from article
where
id = 1
or id = 2
or id = 3
;

select * from article
where id in (2, 3, 4, 5)
;