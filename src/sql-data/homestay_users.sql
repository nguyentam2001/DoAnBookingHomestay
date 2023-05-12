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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `age` int NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `user_image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK4qu1gr772nnf6ve5af002rwya` (`role_id`),
  CONSTRAINT `FK4qu1gr772nnf6ve5af002rwya` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'Hoài Đức, Hà Nội',61,'vietanh@gmail.com','Hoàng Việt Anh','male','617 857 2896','VietAnhHoang',2,'$2a$12$9SkKkQOL7a6EXoHt0I9sdentk3IPE2Nu/xMe58EwKzikxAPOSxIlK',_binary '',NULL),(3,'Bắc giang',68,'nguyenhoai@gmail.com','Nguyễn Thị Hoài',NULL,'562 368 1607','NguyenHoai',2,'$2a$12$9SkKkQOL7a6EXoHt0I9sdentk3IPE2Nu/xMe58EwKzikxAPOSxIlK',_binary '',NULL),(4,'Bắc Giang',51,'nguyenhuong@gmail.com','Nguyễn Thị Hương','female','453 276 9159','NguyenHuong',2,'$2a$12$9SkKkQOL7a6EXoHt0I9sdentk3IPE2Nu/xMe58EwKzikxAPOSxIlK',_binary '',NULL),(5,'Thái Bình',45,'hoangAnh@gmail.com','Hoàng Văn Anh',NULL,'441 188 4898','hoangAnh',2,'$2a$12$9SkKkQOL7a6EXoHt0I9sdentk3IPE2Nu/xMe58EwKzikxAPOSxIlK',_binary '',NULL),(6,'Bac Giang',80,'Hiep@gmail.com','Nguyễn Thị Hiệp',NULL,'973 326 2709','NguyenHiep',2,'$2a$12$9SkKkQOL7a6EXoHt0I9sdentk3IPE2Nu/xMe58EwKzikxAPOSxIlK',_binary '',NULL),(7,'Ha Noi',12,'HoangNguyen@gmail.com','Hoàng Văn Nguyên',NULL,'660 123 7660','HoangNguyen@gmail.com',2,'$2a$12$9SkKkQOL7a6EXoHt0I9sdentk3IPE2Nu/xMe58EwKzikxAPOSxIlK',_binary '',NULL),(8,'Karanggintung',22,'jde6@altervista.org','Joli de la Valette Parisot','0','195 431 3323','jde6',2,'$2a$12$9SkKkQOL7a6EXoHt0I9sdentk3IPE2Nu/xMe58EwKzikxAPOSxIlK',_binary '',NULL),(9,'Palayan City',71,'hjaffa7@state.gov','Hetti Jaffa','0','166 496 8018','hjaffa7',2,'$2a$12$9SkKkQOL7a6EXoHt0I9sdentk3IPE2Nu/xMe58EwKzikxAPOSxIlK',_binary '',NULL),(10,'Engenheiro Beltrão',37,'vpitman8@mashable.com','Van Pitman','1','832 687 2599','vpitman8',2,'$2a$12$9SkKkQOL7a6EXoHt0I9sdentk3IPE2Nu/xMe58EwKzikxAPOSxIlK',_binary '',NULL),(11,'Ma’an',17,'nhaddow9@phoca.cz','Nerta Haddow','0','664 130 8731','nhaddow9',2,'$2a$12$9SkKkQOL7a6EXoHt0I9sdentk3IPE2Nu/xMe58EwKzikxAPOSxIlK',_binary '',NULL),(12,'Ha Noi',61,'2001tambh@gmail.com','Nguyễn Văn Tâm','male','0337966780','tam',1,'$2a$12$9SkKkQOL7a6EXoHt0I9sdentk3IPE2Nu/xMe58EwKzikxAPOSxIlK',_binary '','anh_2.jpg'),(14,'Hà Nam',20,'tinhtemanly@gmail.com ','Nguyễn Vũ Chí Tình','1','0337966780','TinhNVC',1,'$2a$12$9SkKkQOL7a6EXoHt0I9sdentk3IPE2Nu/xMe58EwKzikxAPOSxIlK',_binary '',NULL),(26,'',0,'060601tambh@gmail.com','Nguyễn Vũ Chí Tình','male','0337966789','TinhNVC',NULL,'$2a$10$gqY3hkuPvBeYRWJ6L74w..MQ9NsA9ngajhNIFYwCMjFPJytR89U36',_binary '','Chien.jpg');
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

-- Dump completed on 2023-05-13  0:34:57
