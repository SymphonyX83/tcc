CREATE TABLE oncall (
  id int unsigned not null auto_increment primary key,
  rgroup_id int unsigned default 0,
  poncall_id int unsigned default 0 COMMENT "Select WHERE rgroup is the same than above selected",
  soncall_id int unsigned default 0 COMMENT "Select WHERE rgroup is the same than above selected",
  start_date date,
  end_date date
) engine=innodb default charset=utf8; 

