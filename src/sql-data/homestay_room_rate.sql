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
-- Table structure for table `room_rate`
--

DROP TABLE IF EXISTS `room_rate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_rate` (
  `rate_id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `rate_points` double NOT NULL,
  `booking_id` int DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`rate_id`),
  KEY `FK8uownpcmnxg95400hggt7piys` (`booking_id`),
  CONSTRAINT `FK8uownpcmnxg95400hggt7piys` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_rate`
--

LOCK TABLES `room_rate` WRITE;
/*!40000 ALTER TABLE `room_rate` DISABLE KEYS */;
INSERT INTO `room_rate` VALUES (1,'Crowned hawk-eagle',3,1,NULL,NULL),(2,'Silver-backed jackal',4,2,NULL,NULL),(3,'Lion, galapagos sea',5,4,NULL,NULL),(4,'Tamandua, southern',2,5,NULL,NULL),(5,'Sage hen',3,7,NULL,NULL),(6,'Australian brush turkey',5,9,NULL,NULL),(7,'North American river otter',4,10,NULL,NULL),(8,'Heron, black-crowned night',3,11,NULL,NULL),(9,'Golden brush-tailed possum',2,12,NULL,NULL),(10,'Tapir, brazilian',3,13,NULL,NULL),(11,'Crowned hawk-eagle',4,25,NULL,NULL),(12,'Silver-backed jackal',4,26,NULL,NULL),(13,'Lion, galapagos sea',5,27,NULL,NULL),(14,'Tamandua, southern',2,28,NULL,NULL),(15,'Sage hen',3,29,NULL,NULL),(16,'Australian brush turkey',5,30,NULL,NULL),(17,'North American river otter',4,31,NULL,NULL),(18,'Heron, black-crowned night',3,32,NULL,NULL),(19,'Golden brush-tailed possum',2,24,NULL,NULL),(20,'Khong cos gi ca',4,34,NULL,NULL),(21,'Không có gì',3,35,'2023-04-15 22:11:52',NULL),(24,'Căn hộ rất tuyệt',4,42,'2023-04-16 00:16:39',NULL),(25,'KHoong cso gi',4,44,'2023-04-17 13:10:40',NULL),(26,'Phòng tiện nghi và đẹp',5,46,'2023-04-21 00:00:08',NULL),(27,'',5,49,'2023-04-22 22:32:56',NULL),(28,'Phục vụ rất tốt và chu đáo',4,54,'2023-04-25 23:56:08',NULL),(29,'rất tốt',4,62,'2023-05-03 08:51:04',NULL),(30,'RẤT TUYỆT VỜI',5,71,'2023-05-03 10:14:53',NULL),(31,'Không có nhận xét gì',4,79,'2023-05-04 10:53:59',NULL),(32,'Không có nhận xet gì thêm',5,80,'2023-05-04 10:56:35',NULL),(33,'Không nhận xet gi hon',5,81,'2023-05-04 11:02:30',NULL),(34,'Khong có gì cả',5,85,'2023-05-04 11:37:51',NULL),(35,'KHông có gì',5,88,'2023-05-06 21:01:50',NULL),(36,'Không có nhận xét gì',5,99,'2023-05-11 14:41:40',NULL);
/*!40000 ALTER TABLE `room_rate` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-13  0:34:56
