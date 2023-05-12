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
-- Table structure for table `file_data`
--

DROP TABLE IF EXISTS `file_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file_data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `file_path` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `room_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4v51n16dhs8lbh89d61f2wvw8` (`room_id`),
  CONSTRAINT `FK4v51n16dhs8lbh89d61f2wvw8` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_data`
--

LOCK TABLES `file_data` WRITE;
/*!40000 ALTER TABLE `file_data` DISABLE KEYS */;
INSERT INTO `file_data` VALUES (14,'src/main/resources/static/images/1585033148-can-ho-mau-athena-fulland.jpg','1585033148-can-ho-mau-athena-fulland.jpg','image/jpeg',19),(15,'src/main/resources/static/images/1585033149-can-ho-mau-charmington-iris.jpg','1585033149-can-ho-mau-charmington-iris.jpg','image/jpeg',19),(37,'src/main/resources/static/images/1585033149-can-ho-mau-cosmo-tay-ho.jpg','1585033149-can-ho-mau-cosmo-tay-ho.jpg','image/jpeg',28),(40,'src/main/resources/static/images/1585033149-can-ho-mau-cosmo-tay-ho.jpg','1585033149-can-ho-mau-cosmo-tay-ho.jpg','image/jpeg',29),(45,'src/main/resources/static/images/1585033148-can-ho-mau-athena-fulland.jpg','1585033148-can-ho-mau-athena-fulland.jpg','image/jpeg',29),(47,'src/main/resources/static/images/1585033148-can-ho-mau-an-land-complex - Copy.jpg','1585033148-can-ho-mau-an-land-complex - Copy.jpg','image/jpeg',5),(48,'src/main/resources/static/images/1585033148-can-ho-mau-an-land-complex.jpg','1585033148-can-ho-mau-an-land-complex.jpg','image/jpeg',5),(49,'src/main/resources/static/images/1585033148-can-ho-mau-athena-fulland.jpg','1585033148-can-ho-mau-athena-fulland.jpg','image/jpeg',5),(52,'src/main/resources/static/images/1585033148-can-ho-mau-athena-fulland.jpg','1585033148-can-ho-mau-athena-fulland.jpg','image/jpeg',4),(53,'src/main/resources/static/images/1585033149-can-ho-mau-charmington-iris.jpg','1585033149-can-ho-mau-charmington-iris.jpg','image/jpeg',4),(54,'src/main/resources/static/images/1585033148-can-ho-mau-athena-fulland.jpg','1585033148-can-ho-mau-athena-fulland.jpg','image/jpeg',6),(60,'src/main/resources/static/images/1585033152-can-ho-mau-ecolife-capital.jpg','1585033152-can-ho-mau-ecolife-capital.jpg','image/jpeg',9),(61,'src/main/resources/static/images/1585033149-can-ho-mau-cosmo-tay-ho.jpg','1585033149-can-ho-mau-cosmo-tay-ho.jpg','image/jpeg',32),(62,'src/main/resources/static/images/5-star-hotel-room-wallpaper-preview.jpg','5-star-hotel-room-wallpaper-preview.jpg','image/jpeg',9),(63,'src/main/resources/static/images/5655394.jpg','5655394.jpg','image/jpeg',9),(64,'src/main/resources/static/images/1585033148-can-ho-mau-an-land-complex - Copy.jpg','1585033148-can-ho-mau-an-land-complex - Copy.jpg','image/jpeg',9),(65,'src/main/resources/static/images/1585033148-can-ho-mau-an-land-complex - Copy.jpg','1585033148-can-ho-mau-an-land-complex - Copy.jpg','image/jpeg',32),(66,'src/main/resources/static/images/1585033148-can-ho-mau-athena-fulland.jpg','1585033148-can-ho-mau-athena-fulland.jpg','image/jpeg',32),(68,'src/main/resources/static/images/1585033152-can-ho-mau-ecolife-capital.jpg','1585033152-can-ho-mau-ecolife-capital.jpg','image/jpeg',32),(70,'src/main/resources/static/images/beach-hotel-room-wallpaper-preview.jpg','beach-hotel-room-wallpaper-preview.jpg','image/jpeg',11),(71,'src/main/resources/static/images/ff5489f401d008f38a1eb13960075380.png','ff5489f401d008f38a1eb13960075380.png','image/png',11),(72,'src/main/resources/static/images/hotel_room_2-t2.jpg','hotel_room_2-t2.jpg','image/jpeg',11),(73,'src/main/resources/static/images/miami-florida-hotel-room-wallpaper-preview.jpg','miami-florida-hotel-room-wallpaper-preview.jpg','image/jpeg',11),(74,'src/main/resources/static/images/miami-florida-hotel-room-wallpaper-preview.jpg','miami-florida-hotel-room-wallpaper-preview.jpg','image/jpeg',30),(75,'src/main/resources/static/images/pexels-pixabay-271619.jpg','pexels-pixabay-271619.jpg','image/jpeg',30),(76,'src/main/resources/static/images/photo-1591088398332-8a7791972843.jpg','photo-1591088398332-8a7791972843.jpg','image/jpeg',30),(77,'src/main/resources/static/images/1585033148-can-ho-mau-an-land-complex - Copy.jpg','1585033148-can-ho-mau-an-land-complex - Copy.jpg','image/jpeg',30),(78,'src/main/resources/static/images/5655394.jpg','5655394.jpg','image/jpeg',26),(79,'src/main/resources/static/images/5-star-hotel-room-wallpaper-preview.jpg','5-star-hotel-room-wallpaper-preview.jpg','image/jpeg',26),(80,'src/main/resources/static/images/1585033148-can-ho-mau-an-land-complex - Copy.jpg','1585033148-can-ho-mau-an-land-complex - Copy.jpg','image/jpeg',26),(81,'src/main/resources/static/images/1585033148-can-ho-mau-athena-fulland.jpg','1585033148-can-ho-mau-athena-fulland.jpg','image/jpeg',26),(82,'src/main/resources/static/images/1585033148-can-ho-mau-athena-fulland.jpg','1585033148-can-ho-mau-athena-fulland.jpg','image/jpeg',33),(83,'src/main/resources/static/images/1585033149-can-ho-mau-cosmo-tay-ho.jpg','1585033149-can-ho-mau-cosmo-tay-ho.jpg','image/jpeg',33),(84,'src/main/resources/static/images/1585033152-can-ho-mau-ecolife-capital.jpg','1585033152-can-ho-mau-ecolife-capital.jpg','image/jpeg',33),(85,'src/main/resources/static/images/beach-hotel-room-wallpaper-preview.jpg','beach-hotel-room-wallpaper-preview.jpg','image/jpeg',33);
/*!40000 ALTER TABLE `file_data` ENABLE KEYS */;
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
