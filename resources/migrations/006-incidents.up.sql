CREATE TABLE incidents (
  id int unsigned not null auto_increment primary key,
  status char(1) default null COMMENT "O=Open, C=Closed", 
  title varchar(255) default null,
  inc_number varchar(255) default null,
  tier char(1) default null comment "1,2,3",
  severity char(1) default null comment "1,2,3",
  environment varchar(255) default null,
  rgroup_id int unsigned default 0,
  source_id int unsigned default 0,
  summary text default null,
  current_status text default null,
  potential_escalation char(1) comment "L=Low, M=Medium, H=High",
  bridge_details text default null,
  start_time timestamp,
  end_time timestamp,
  coord_id_1 int unsigned default 0 COMMENT "Incident Coordinator 1",
  coord_id_2 int unsigned default 0 COMMENT "Incident Coordinator 2",
  coord_id_3 int unsigned default 0 COMMENT "Incident Coordinator 3",
  total_outage varchar(255)
) engine=innodb default charset=utf8;
