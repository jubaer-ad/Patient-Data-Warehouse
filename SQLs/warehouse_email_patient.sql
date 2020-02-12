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
-- Table structure for table `email_patient`
--

DROP TABLE IF EXISTS `email_patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `email_patient` (
  `Email_Patient` varchar(255) COLLATE utf8_bin NOT NULL,
  `Patient_Patient_medi_code` varchar(45) COLLATE utf8_bin NOT NULL,
  KEY `fk_Email_Patient_Patient1` (`Patient_Patient_medi_code`),
  CONSTRAINT `fk_Email_Patient_Patient1` FOREIGN KEY (`Patient_Patient_medi_code`) REFERENCES `patient` (`patient_medi_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_patient`
--

LOCK TABLES `email_patient` WRITE;
/*!40000 ALTER TABLE `email_patient` DISABLE KEYS */;
INSERT INTO `email_patient` VALUES ('FHCGFGH','FUAD'),('THFRHFH','FUAD'),('FGHFGHFG','FUAD'),('dfgdghjfj','jb'),('fghfghf','jb'),('ghfghfgh','jb'),('dfsdfsdf','help'),('sdzxcxc','help'),('cxvxcvxcv','help'),('jubaerad1@gmail.com','jubaer'),('sfgegdgg','jubaer'),('dfgdfgdfg','jubaer'),('fjhjhj','ratan'),('ghjghjghj','ratan'),('ghjghjg','ratan'),('khjkbjkbnjk','t'),('hbjkbj','t'),('uhujbjb','t');
/*!40000 ALTER TABLE `email_patient` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-27  0:20:51
