CREATE TABLE sources (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    detection_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    FOREIGN KEY (detection_id) REFERENCES detections(id) ON DELETE CASCADE ON UPDATE CASCADE
)