CREATE TABLE `sources` (
  `id` int NOT NULL AUTO_INCREMENT,
  `detection_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  PRIMARY KEY (`id`),
  KEY `detection_id` (`detection_id`),
  CONSTRAINT `sources_ibfk_1` FOREIGN KEY (`detection_id`) REFERENCES `detections` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);