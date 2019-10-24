-- MySQL dump 10.13  Distrib 5.5.55, for debian-linux-gnu (armv7l)
--
-- Host: localhost    Database: server
-- ------------------------------------------------------
-- Server version	5.5.55-0+deb8u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1978,'$2a$10$AGDLFHM/TmP9T7FehLDkW.XnVnUBMzXoDq4aE41wlx6shNhoMUpr6','student1'),(2806,'$2a$10$m/UiqHhszNVa.dAXEb.BtO9i0PumnFkXDkJJRBAkTBFxpHVNiOh7W','student2'),(2807,'$2a$10$akEWSWCHhcWZ9a7lsI.NI.N.a8Ot1WhFuJlbukI19N8ypFC3Ie92O','student3'),(2808,'$2a$10$9g7f4VMspnw7K0gSHtMT0OrOr4Wf/ZammCOJgD3rR6XCifytqQb36','admin'),(2809,'$2a$10$4iAMa.nnyoiy//GxeBzFTuYIUXKv91arR.jMf449NMdULWgPR.t1S','student4'),(4385,'$2a$10$OpFLPbtPPx0Yry7NztOHYOucP7dztEiiMRJ/3AndQlyWSOoz5HBc6','student5');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User_roles`
--

DROP TABLE IF EXISTS `User_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_roles` (
  `User_id` bigint(20) NOT NULL,
  `roles` int(11) DEFAULT NULL,
  KEY `FKi81fp6mx433heb7dvbxqaqvpv` (`User_id`),
  CONSTRAINT `FKi81fp6mx433heb7dvbxqaqvpv` FOREIGN KEY (`User_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_roles`
--

LOCK TABLES `User_roles` WRITE;
/*!40000 ALTER TABLE `User_roles` DISABLE KEYS */;
INSERT INTO `User_roles` VALUES (1978,1),(2806,1),(2807,1),(2808,0),(2809,1),(4385,1);
/*!40000 ALTER TABLE `User_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-28  9:22:22
