create table employees (
    id int not null auto_increment primary key,
    name varchar(255),
    email varchar(255) unique,
    pphone varchar(255),
    sphone varchar(255)
)