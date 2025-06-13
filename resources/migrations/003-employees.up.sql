CREATE TABLE employees (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    pphone VARCHAR(255),
    sphone VARCHAR(255),
    is_manager char(1) DEFAULT '0'
)