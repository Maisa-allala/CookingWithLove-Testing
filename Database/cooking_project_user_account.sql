CREATE DATABASE  IF NOT EXISTS `cooking_project` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cooking_project`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: cooking_project
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_account` (
  `accountID` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(8) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `DOB` date DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `accountType` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zip` char(8) DEFAULT NULL,
  `is_active` char(1) DEFAULT NULL,
  `accountRequestedDate` date DEFAULT NULL,
  `accountActiveDate` date DEFAULT NULL,
  PRIMARY KEY (`accountID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,'admin','Maisa','Allala','2020-11-10','$2y$10$fw6/AlPUVGrOa31DVl0c2uQZjecQ45DBjBe9SMv4cs0uOQ6js/NhW','admin','maisa@yahoo.com','419-297-8567','123 test ct',NULL,'Y','2023-12-01','2023-12-11'),(3,'seller1','Maisa','Allala','1985-01-14','$2y$10$fw6/AlPUVGrOa31DVl0c2uQZjecQ45DBjBe9SMv4cs0uOQ6js/NhW','Seller','maissa_alamoura@yahoo.com','417-789-8965','123 test ct','43623','Y','2023-12-23','2023-12-01'),(4,'buyer1','Maisa','allala','1985-03-18','$2y$10$fw6/AlPUVGrOa31DVl0c2uQZjecQ45DBjBe9SMv4cs0uOQ6js/NhW','Buyer','maissa_alamoura@yahoo.com','417-896-8547','123 test ct','43623','Y','2024-01-02',NULL),(5,'buyer2','Maisa','Allala','1985-01-14','$2y$10$fw6/AlPUVGrOa31DVl0c2uQZjecQ45DBjBe9SMv4cs0uOQ6js/NhW','Buyer','maissa_alamoura@yahoo.com','417-789-8965','123 test ct','43623','N','2024-01-09',NULL),(6,'seller2','Maisa','Allala','1970-12-05','$2y$10$fw6/AlPUVGrOa31DVl0c2uQZjecQ45DBjBe9SMv4cs0uOQ6js/NhW','Seller','maissa_alamoura@yahoo.com','419-297-8567','123 test ct','43623','N','2024-02-25',NULL);
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-26 18:53:04
