CREATE TABLE employeesgroups (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    rgroup_id INT NOT NULL,
    employee_id INT NOT NULL,
    comments TEXT NULL,
    FOREIGN KEY (rgroup_id) REFERENCES rgroups(id),
    FOREIGN KEY (employee_id) REFERENCES employees(id)
)