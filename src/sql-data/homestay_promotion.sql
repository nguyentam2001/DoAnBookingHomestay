-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: homestay
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotion` (
  `promotion_id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `percent_discount` double DEFAULT NULL,
  `promotion_name` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `homestay_id` int DEFAULT NULL,
  PRIMARY KEY (`promotion_id`),
  KEY `FKjdj3lt9uo5bwqati8qqjtc3p8` (`homestay_id`),
  CONSTRAINT `FKjdj3lt9uo5bwqati8qqjtc3p8` FOREIGN KEY (`homestay_id`) REFERENCES `homestay` (`homestay_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
INSERT INTO `promotion` VALUES (2,NULL,NULL,NULL,'2023-04-14',50,'Mã giảm mùa hè','2023-04-13',2),(3,NULL,NULL,'','2023-04-10',50,'Mã giảm mùa hè','2023-04-06',2),(4,NULL,NULL,'Sunny Home, homestay của Nắng giảm giá 50% chỉ hôm nay, Sunny Home, homestay của Nắng giảm giá 50% chỉ hôm nay, Sunny Home, homestay của Nắng giảm giá 50% chỉ hôm nay','2023-04-10',50,'Mã giảm mùa hè','2023-04-06',1),(5,NULL,NULL,'Hello','2023-04-21',50,'Mã giảm mùa hè','2023-04-11',2),(6,NULL,NULL,'Hello Hello','2023-04-10',50,'Mã giảm mùa hè','2023-04-09',1),(7,NULL,NULL,'Hello Hello','2023-04-13',50,'Mã giảm mùa hè','2023-04-10',2),(10,NULL,NULL,'sfsdfs','2023-04-11',50,'Mã giảm mùa hè','2023-04-04',2),(25,NULL,NULL,'Giảm giá dịp lễ tết vào mùa hè','2023-06-30',20,'Mã giảm mùa lễ hội','2023-05-01',1);
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-13  0:34:57
