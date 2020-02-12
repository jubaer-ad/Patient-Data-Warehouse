-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: warehouse
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `doctor_address`
--

DROP TABLE IF EXISTS `doctor_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `doctor_address` (
  `Doctor_address_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Doctor_address_city` varchar(45) COLLATE utf8_bin NOT NULL,
  `Doctor_address_street` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `Doctor_address_country` varchar(45) COLLATE utf8_bin NOT NULL,
  `Doctor_address_zip_code` varchar(45) COLLATE utf8_bin NOT NULL,
  `Doctor_Doctor_medi_code` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`Doctor_address_id`,`Doctor_Doctor_medi_code`),
  KEY `fk_Doctor_address_Doctor1_idx` (`Doctor_Doctor_medi_code`),
  CONSTRAINT `fk_Doctor_address_Doctor1` FOREIGN KEY (`Doctor_Doctor_medi_code`) REFERENCES `doctor` (`doctor_medi_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_address`
--

LOCK TABLES `doctor_address` WRITE;
/*!40000 ALTER TABLE `doctor_address` DISABLE KEYS */;
INSERT INTO `doctor_address` VALUES (1,'df','df','df','df','df'),(2,'dhaka','trt','ertert','5454','kazi');
/*!40000 ALTER TABLE `doctor_address` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-27  0:20:49
