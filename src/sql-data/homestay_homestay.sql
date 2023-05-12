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
-- Table structure for table `homestay`
--

DROP TABLE IF EXISTS `homestay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `homestay` (
  `homestay_id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `homestay_name` varchar(255) DEFAULT NULL,
  `address_id` int DEFAULT NULL,
  `address_details` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`homestay_id`),
  KEY `FKr2fqkyxc4ob5c7gse10bqg6u8` (`address_id`),
  CONSTRAINT `FKr2fqkyxc4ob5c7gse10bqg6u8` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homestay`
--

LOCK TABLES `homestay` WRITE;
/*!40000 ALTER TABLE `homestay` DISABLE KEYS */;
INSERT INTO `homestay` VALUES (1,'Homestay nằm cạnh bãi biển','Beach House 2 Homestay',1,'304, 2/4 street, Nha Trang, Việt Nam','homestay1.jpg'),(2,'Homestay nằm ngay khu công viên','Park View Lodge Homestay',1,'304, 2/4 street, Nha Trang, Việt Nam','homestay2.jpg'),(3,'Homestay có view khu vườn','Garden Oasis Homestay',2,'120-122 Hà Huy Tập, Phường Tân Phong , Quận 7, District 7, Ho Chi Minh City','homestay3.jpg'),(7,'Homestay sát bờ sông','Riverfront Residence Homestay',5,'Cua Hoi, Cua Lo, Nghe An, Cửa Lò','homestay1.jpg'),(8,'Homestay ở khu trung tâm thương mại','Shopping District Homestay',6,'145 Nguyễn Văn Thoại, Da Nang','homestay2.jpg'),(9,'Homestay trong khu nghỉ dưỡng','Resort Getaway Homestay',7,'thôn Trường An xã trường yên, Ninh Binh','homestay3.jpg'),(10,'Homestay gần trạm xe buýt','Bus Station Guesthouse Homestay',8,'621/7 Hai Bà Trưng, Cẩm Phô, Quảng Nam, Son Phong, Hoi An','homestay4.jpg'),(11,'Homestay đối diện công viên','Parkside Retreat Homestay',1,'304, 2/4 street, Nha Trang, Việt Nam','homestay5.jpg'),(12,'Homestay nằm cạnh bờ biển tuyệt đẹp','Nha Trang HEART LODGEO',1,'304, 2/4 street, Nha Trang, Việt Nam','homestay6.jpg'),(13,'Homestay với sự sang trọng của thành phố Hồ Chí Minh','Ho Chi Minh KING OF HOMESTAY',2,'120-122 Hà Huy Tập, Phường Tân Phong , Quận 7, District 7, Ho Chi Minh City','homestay3.jpg'),(16,'Homestay tuyệt vời với những phong cảnh thiên nhiên','Center City Of Homestay',2,'120-122 Hà Huy Tập, Phường Tân Phong , Quận 7, District 7, Ho Chi Minh City',NULL);
/*!40000 ALTER TABLE `homestay` ENABLE KEYS */;
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
