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
-- Table structure for table `phone_no_patient`
--

DROP TABLE IF EXISTS `phone_no_patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `phone_no_patient` (
  `Phone_no_Patient` varchar(11) COLLATE utf8_bin NOT NULL,
  `Patient_Patient_medi_code` varchar(45) COLLATE utf8_bin NOT NULL,
  KEY `fk_Phone_no_Patient_Patient1` (`Patient_Patient_medi_code`),
  CONSTRAINT `fk_Phone_no_Patient_Patient1` FOREIGN KEY (`Patient_Patient_medi_code`) REFERENCES `patient` (`patient_medi_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone_no_patient`
--

LOCK TABLES `phone_no_patient` WRITE;
/*!40000 ALTER TABLE `phone_no_patient` DISABLE KEYS */;
INSERT INTO `phone_no_patient` VALUES ('0285535','FUAD'),('454545','FUAD'),('454545','FUAD'),('44564','jb'),('4545','jb'),('454545','jb'),('456456','help'),('+7886745','help'),('+45431234','help'),('01790059606','jubaer'),('454564654','jubaer'),('54545','ratan'),('78787','ratan'),('121212','ratan'),('5454','t'),('787','t'),('515454','t');
/*!40000 ALTER TABLE `phone_no_patient` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-27  0:20:53
