create table groups (
    id int not null auto_increment primary key,
    name varchar(255),
    email varchar(255) unique,
    description text,
    active enum('Yes', 'No') default 'Yes'
)