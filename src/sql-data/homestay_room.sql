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
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `room_id` int NOT NULL AUTO_INCREMENT,
  `area` double NOT NULL,
  `bed_numbers` varchar(255) DEFAULT NULL,
  `number_of_person` int NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `room_description` varchar(255) DEFAULT NULL,
  `room_name` varchar(255) DEFAULT NULL,
  `room_type` int NOT NULL,
  `status` bit(1) NOT NULL,
  `homestay_id` int DEFAULT NULL,
  PRIMARY KEY (`room_id`),
  KEY `FKqcvstr3bg2lmknhdjwitgr3jm` (`homestay_id`),
  CONSTRAINT `FKqcvstr3bg2lmknhdjwitgr3jm` FOREIGN KEY (`homestay_id`) REFERENCES `homestay` (`homestay_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (3,100,'1',7,350000.00,'Một căn phòng ấm cúng với một giường đôi thoải mái','Phòng Giường Đôi Ấm Cúng',1,_binary '\0',3),(4,20,'2',9,150000.00,'Một căn phòng rộng rãi với hai giường đơn thoải mái','Phòng Hai Giường Đơn',0,_binary '\0',3),(5,120,'3',26,250000.00,'Một căn phòng lý tưởng cho gia đình với một giường đôi và một giường đơn','Phòng Gia Đình',1,_binary '\0',2),(6,110,'3',2,300000.00,'','Phòng Ba Giường Đơn',0,_binary '\0',1),(7,150,'4',19,220000.00,'Một căn phòng lý tưởng cho nhóm bạn với hai giường đôi và một giường đơn','Phòng Nhóm',1,_binary '\0',13),(9,120,'2',22,200000.00,'','Phòng Đơn Đơn Standard',0,_binary '\0',1),(10,140,'4',28,1000000.00,'','Phòng đơn Deluxe',0,_binary '\0',2),(11,150,'2',2,450000.00,'','Phòng đơn Executive',0,_binary '\0',1),(12,150,'4',3,550000.00,'','Phòng đôi Deluxe',0,_binary '\0',13),(13,150,'3',2,550000.00,'','Phòng gia đình',0,_binary '\0',2),(14,160,'4',2,650000.00,'','Phòng Đơn Superior',0,_binary '\0',3),(19,170,'2',3,650000.00,'','Phòng Giường Đôi Supperior',0,_binary '\0',13),(26,140,'2',3,700000.00,'','Phòng Giường Đôi Vip',0,_binary '\0',1),(28,150,'4',2,300000.00,'','Phòng Hoàng gia',0,_binary '\0',10),(29,140,'4',2,300000.00,'','Phòng Giường Đôi Thiên Đường',0,_binary '\0',1),(30,85,'3',26,500000.00,'','Phòng Giường Đôi Tình Yêu Ấm Cúng ',0,_binary '\0',1),(31,46,'2',2,300000.00,'','Phòng Đơn Superior',0,_binary '\0',1),(32,81,'2',19,200000.00,'','Phòng Giường Đôi Executive',0,_binary '\0',1),(33,0,NULL,12,500000.00,'Căn hộ sang trọng king of heart lodgeo với thiết kế cổ điển phù hợp cho các đôi tình nhân.','Căn hộ King of heart lodgeo',1,_binary '\0',12);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
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
