CREATE TABLE `locations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `zip_code` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
