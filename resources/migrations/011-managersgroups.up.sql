CREATE TABLE managersgroups (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    manager_id INT NOT NULL,
    rgroup_id INT NOT NULL,
    FOREIGN KEY (manager_id) REFERENCES employees(id),
    FOREIGN KEY (rgroup_id) REFERENCES rgroups(id)
)