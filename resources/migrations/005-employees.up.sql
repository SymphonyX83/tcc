CREATE TABLE employees (
  id int unsigned not null auto_increment primary key,
  name varchar(255) default null,
  firstname varchar(255) default null,
  lastname varchar(255) default null,
  pphone varchar(255) default null,
  sphone varchar(255) default null,
  is_manager char(1) default null COMMENT "Y=Yes, N=No",
  rgroup_id int unsigned not null,
  location_id int unsigned not null,
  FOREIGN KEY employees_rgroups(rgroup_id) references rgroups(id),
  FOREIGN KEY employees_locations(location_id) references locations(id)
) engine=innodb default charset=utf8;
