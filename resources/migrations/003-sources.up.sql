CREATE TABLE sources (
  id int unsigned not null auto_increment primary key,
  name varchar(255) default null,
  decription text default null
)engine=innodb default charset=utf8;
