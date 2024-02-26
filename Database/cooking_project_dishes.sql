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
-- Table structure for table `dishes`
--

DROP TABLE IF EXISTS `dishes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dishes` (
  `dishID` int NOT NULL AUTO_INCREMENT,
  `dishName` varchar(255) NOT NULL,
  `description` varchar(2000) NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `dishType` varchar(255) NOT NULL,
  `accountID` int DEFAULT NULL,
  `publishDate` date DEFAULT NULL,
  `photoPath` varchar(255) DEFAULT NULL,
  `is_published` char(1) DEFAULT NULL,
  `DishRequestedDate` date DEFAULT NULL,
  PRIMARY KEY (`dishID`),
  KEY `fk_categoryID` (`dishType`),
  KEY `fk_dishAccountID` (`accountID`),
  CONSTRAINT `fk_dishAccountID` FOREIGN KEY (`accountID`) REFERENCES `user_account` (`accountID`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dishes`
--

LOCK TABLES `dishes` WRITE;
/*!40000 ALTER TABLE `dishes` DISABLE KEYS */;
INSERT INTO `dishes` VALUES (64,'Greek Salad','2 tomatoes',4.00,'available','Salad',3,'2023-12-04','Uploads/Greek Salad.jpeg','Y','2023-12-01'),(65,'Greek Salad','2 tomatoes',3.00,'available','Salad',3,'2023-12-04','Uploads/Greek Salad.jpeg','Y','2023-12-01'),(66,'Greek Salad','2 tomatoes',3.00,'available','Salad',3,'2023-12-04','Uploads/Greek Salad.jpeg','Y','2023-12-01'),(67,'Greek Salad','2 tomatoes',3.00,'available','Salad',3,'2023-12-04','Uploads/Greek Salad.jpeg','Y','2023-12-01'),(68,'Greek Salad','2 tomatoes',3.00,'available','Salad',3,'2023-12-04','Uploads/Greek Salad.jpeg','Y','2023-12-01'),(69,'Greek Salad','2 tomatoes',3.00,'available','Salad',3,'2023-12-04','Uploads/Greek Salad.jpeg','Y','2023-12-01'),(70,'Greek Salad','2 tomatoes',3.00,'available','Salad',3,'2023-12-04','Uploads/Greek Salad.jpeg','Y','2023-12-01');
/*!40000 ALTER TABLE `dishes` ENABLE KEYS */;
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
