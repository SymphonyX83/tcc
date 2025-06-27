create table oncall (
    id int not null auto_increment primary key,
    groups_id int not null,
    primary_employee_id int not null,
    secondary_employee_id int not null,
    manager_employee_id int not null,
    start_oncall date,
    end_oncall date,
    comments text,
    foreign key (groups_id) references groups(id),
    foreign key (primary_employee_id) references employees(id),
    foreign key (secondary_employee_id) references employees(id),
    foreign key (manager_employee_id) references employees(id)
)