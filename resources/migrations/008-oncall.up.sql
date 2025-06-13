CREATE TABLE oncall (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    rgroup_id INT NOT NULL,
    primary_id INT NOT NULL,
    secondary_id INT NOT NULL,
    manager_id INT NOT NULL,
    start_oncall DATETIME NOT NULL,
    end_oncall DATETIME NOT NULL,
    comments TEXT,
    FOREIGN KEY (rgroup_id) REFERENCES rgroups(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (primary_id) REFERENCES employees(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (secondary_id) REFERENCES employees(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (manager_id) REFERENCES employees(id) ON DELETE CASCADE ON UPDATE CASCADE
)