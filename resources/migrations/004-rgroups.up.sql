CREATE TABLE rgroups (
  id int unsigned not null auto_increment primary key,
  name varchar(255) default null,
  parent int unsigned default 0,
  rotational_phone varchar(255) default null,
  manager_id int unsigned default 0,
  description text default null
) engine=innodb default charset=utf8;
