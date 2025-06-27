/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19  Distrib 10.11.11-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: contacts
-- ------------------------------------------------------
-- Server version	10.11.11-MariaDB-0+deb12u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `detections`
--

DROP TABLE IF EXISTS `detections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `detections` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detections`
--

LOCK TABLES `detections` WRITE;
/*!40000 ALTER TABLE `detections` DISABLE KEYS */;
INSERT INTO `detections` VALUES
(1,'Call/Chat',NULL),
(2,'Monitoring Tool',NULL),
(5,'Email',NULL),
(7,'Incident',NULL),
(8,'Walk-in',NULL),
(9,'Escalation',NULL);
/*!40000 ALTER TABLE `detections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `locations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `zip_code` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES
(1,'Planta Norte','Mexicali','BC','Mexico',NULL,NULL),
(2,'BT West','Mexicali','BC','Mexico',NULL,NULL),
(3,'RDC3','Savannah','GA','United States',NULL,NULL),
(4,'Planta Sur','Mexicali','BC','Mexico',NULL,NULL),
(5,'Cuyamaca','Mexicali','BC','Mexico',NULL,NULL),
(6,'Dallas Warehouse','Dallas','Texas','United States',NULL,NULL);
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sources`
--

DROP TABLE IF EXISTS `sources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `sources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detection_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `detection_id` (`detection_id`),
  CONSTRAINT `sources_ibfk_1` FOREIGN KEY (`detection_id`) REFERENCES `detections` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sources`
--

LOCK TABLES `sources` WRITE;
/*!40000 ALTER TABLE `sources` DISABLE KEYS */;
INSERT INTO `sources` VALUES
(1,1,'Service Desk','Escalations from Service Desk Through Shared Chatgroup'),
(3,2,'OpManager','Manage Engine Tool'),
(4,2,'OEM','Oracle Enterprise Manager'),
(6,5,'Monitoring Tools',NULL),
(7,5,'SME','Subject Matter Expert Email'),
(9,2,'Xymon',NULL),
(10,2,'AppManager','Manage Engine Application Manager'),
(11,7,'ITSM','ServiceNow Platform'),
(12,9,'BT Service Desk',NULL),
(13,9,'Business Stakeholders',NULL),
(14,1,'SME',NULL),
(15,5,'Monitoring Tool',NULL),
(16,5,'ISP',NULL),
(17,1,'Business Stakeholders',NULL),
(18,1,'Previous/Existing Chatgroup','Re-incidence');
/*!40000 ALTER TABLE `sources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oncall`
--

DROP TABLE IF EXISTS `oncall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `oncall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groups_id` int(11) NOT NULL,
  `primary_employee_id` int(11) NOT NULL,
  `secondary_employee_id` int(11) NOT NULL,
  `manager_employee_id` int(11) NOT NULL,
  `start_oncall` date DEFAULT NULL,
  `end_oncall` date DEFAULT NULL,
  `comments` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `groups_id` (`groups_id`),
  KEY `primary_employee_id` (`primary_employee_id`),
  KEY `secondary_employee_id` (`secondary_employee_id`),
  KEY `manager_employee_id` (`manager_employee_id`),
  CONSTRAINT `oncall_ibfk_1` FOREIGN KEY (`groups_id`) REFERENCES `groups` (`id`),
  CONSTRAINT `oncall_ibfk_2` FOREIGN KEY (`primary_employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `oncall_ibfk_3` FOREIGN KEY (`secondary_employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `oncall_ibfk_4` FOREIGN KEY (`manager_employee_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oncall`
--

LOCK TABLES `oncall` WRITE;
/*!40000 ALTER TABLE `oncall` DISABLE KEYS */;
INSERT INTO `oncall` VALUES
(1,1,1,2,3,'2025-06-28','2025-06-29','testing'),
(2,2,2,3,1,'2025-06-28','2025-06-29','testing');
/*!40000 ALTER TABLE `oncall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `pphone` varchar(255) DEFAULT NULL,
  `sphone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES
(1,'Pedro Pacas Verdes','ppacas@gmail.com','686-123-4334','686-222-4321'),
(2,'Maria Pacas Verdes','mpacas@gmail.com','686-333-5656','686-221-8987'),
(3,'Perro Aguayo Campeon','paguayo@gmail.com','686-666-3354','686-555-0233');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incidents`
--

DROP TABLE IF EXISTS `incidents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `incidents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` char(1) DEFAULT NULL COMMENT '1=Open, 2=Closed',
  `number` char(7) DEFAULT NULL,
  `environment` varchar(255) DEFAULT NULL COMMENT 'DEV, QA, UAT, PROD',
  `location_id` int(11) NOT NULL,
  `ci_tier` char(1) DEFAULT NULL COMMENT '1,2,3',
  `severity` char(1) DEFAULT NULL COMMENT '1,2,3,4',
  `business_impact` varchar(255) DEFAULT NULL,
  `summary` text DEFAULT NULL,
  `group_id` int(11) NOT NULL,
  `bridge` varchar(255) DEFAULT NULL,
  `current_status` text DEFAULT NULL,
  `source_id` int(11) NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `coordinator1_id` int(11) NOT NULL,
  `coordinator2_id` int(11) NOT NULL,
  `coordinator3_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `location_id` (`location_id`),
  KEY `group_id` (`group_id`),
  KEY `source_id` (`source_id`),
  KEY `coordinator1_id` (`coordinator1_id`),
  KEY `coordinator2_id` (`coordinator2_id`),
  KEY `coordinator3_id` (`coordinator3_id`),
  CONSTRAINT `incidents_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`),
  CONSTRAINT `incidents_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`),
  CONSTRAINT `incidents_ibfk_3` FOREIGN KEY (`source_id`) REFERENCES `sources` (`id`),
  CONSTRAINT `incidents_ibfk_4` FOREIGN KEY (`coordinator1_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `incidents_ibfk_5` FOREIGN KEY (`coordinator2_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `incidents_ibfk_6` FOREIGN KEY (`coordinator3_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incidents`
--

LOCK TABLES `incidents` WRITE;
/*!40000 ALTER TABLE `incidents` DISABLE KEYS */;
INSERT INTO `incidents` VALUES
(1,'1','1','DEV',1,'1','1','Business impact here','summary here',1,'Bridge here','Current status here',10,'2025-06-27 08:00:00','2025-06-27 09:00:00',1,2,3);
/*!40000 ALTER TABLE `incidents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `active` enum('Yes','No') DEFAULT 'Yes',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES
(1,'Active Directory','perro@gmail.com','Active Directory Group','Yes'),
(2,'MYSQL DBA','oracleg@gmail.com','Oracle DBA Group','Yes'),
(3,'ERP Non-SAP','erp@gmail.com',NULL,'Yes'),
(4,'Windows Admins','windows.admins@gmail.com','Wintel','Yes'),
(5,'TCCW','Technology.Command.Center@gulfstream.com','Technology Command Center West','Yes'),
(6,'Citrix','citrix.admins@gmail.com',NULL,'Yes'),
(7,'SAP Basis','nomai@example.com',NULL,'Yes'),
(8,'SAP Security','nomail1@example.com',NULL,'Yes'),
(9,'Oracle PDBA','nomail2@example.com',NULL,'Yes'),
(10,'SQL DBA','nomail3@example.com',NULL,'Yes'),
(11,'Unix','unix@team.com',NULL,'Yes'),
(12,'Virtualization','virtua@team.com',NULL,'Yes'),
(13,'Storage','nomail4@example.com',NULL,'Yes');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lastname` varchar(50) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `username` varchar(200) DEFAULT NULL,
  `password` text DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `cell` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `fax` varchar(50) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `level` char(1) DEFAULT NULL COMMENT 'A=Administrador,U=Usuario,S=Sistema',
  `active` char(1) DEFAULT NULL COMMENT 'T=Activo,F=Inactivo',
  `imagen` text DEFAULT NULL,
  `last_login` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES
(1,'User','Regular','user@example.com','bcrypt+sha512$6a952c0d0c916b0a1b611018c1f34ecc$12$1a724b928afdfeaab4bd76413584b961b283bd3bcae8a0d1','1957-02-07',NULL,NULL,NULL,'user@example.com','U','T',NULL,'2025-06-26 23:44:49'),
(2,'User','Admin','admin@example.com','bcrypt+sha512$e9607b7b465d3b3adef74b596c8ca62a$12$bbbb86df0f97b191e2f07bc8e37e94edcd6868500b88e8e3','1957-02-07',NULL,NULL,NULL,'admin@example.com','A','T',NULL,'2025-06-26 23:44:49'),
(3,'User','System','system@example.com','bcrypt+sha512$2e914c62e1d90353a350faa9c653d8fa$12$c59694ca7d3e15ef4a8b794eb4c92836f25577780c5087ba','1957-02-07',NULL,NULL,NULL,'system@example.com','S','T',NULL,'2025-06-26 23:44:49');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-27 11:57:43
