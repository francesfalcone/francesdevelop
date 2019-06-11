-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bonifici
-- ------------------------------------------------------
-- Server version	8.0.15

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
-- Table structure for table `transazione`
--

DROP TABLE IF EXISTS `transazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `transazione` (
  `id_transazione` varchar(36) NOT NULL,
  `importo` decimal(10,0) DEFAULT NULL,
  `data_esecuzione` date DEFAULT NULL,
  `commissione` decimal(10,0) DEFAULT NULL,
  `nominativo_beneficiario` varchar(45) DEFAULT NULL,
  `cro` varchar(35) DEFAULT NULL,
  `codice_cliente` int(11) NOT NULL,
  `codice_conto` int(11) NOT NULL,
  `iban_beneficiario` varchar(27) DEFAULT NULL,
  `tipologia` varchar(45) DEFAULT NULL,
  `divisa` varchar(3) DEFAULT NULL,
  `causale` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_transazione`),
  KEY `codice_cliente_fk_idx` (`codice_cliente`),
  KEY `codice_conto_fk_idx` (`codice_conto`),
  CONSTRAINT `codice_cliente_fk` FOREIGN KEY (`codice_cliente`) REFERENCES `cliente` (`codice_cliente`),
  CONSTRAINT `codice_conto_fk` FOREIGN KEY (`codice_conto`) REFERENCES `conto` (`codice_conto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transazione`
--

LOCK TABLES `transazione` WRITE;
/*!40000 ALTER TABLE `transazione` DISABLE KEYS */;
INSERT INTO `transazione` VALUES ('5f80e545-732a-48e2-bea0-a6f78084451d',12,'2019-02-18',1,'Gino Rossi','89077602640',1,4,'IT32A0305801604100571333068',NULL,'EUR','Bonifico per saldo fattura numero 123'),('e474d5fc-fd68-4ac0-b3fc-8cac97c7d8d0',12,'2019-02-18',1,'Gino Rossi','61219803523',1,4,'IT32A0305801604100571333068',NULL,'EUR','Bonifico per saldo fattura numero 123');
/*!40000 ALTER TABLE `transazione` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-11 17:26:13
