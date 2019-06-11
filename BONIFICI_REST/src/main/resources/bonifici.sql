CREATE DATABASE  IF NOT EXISTS `bonifici` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bonifici`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: bonifici
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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cliente` (
  `codice_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `cognome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codice_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Marco','Rossi');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;

--
-- Table structure for table `conto`
--

DROP TABLE IF EXISTS `conto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `conto` (
  `codice_conto` int(11) NOT NULL AUTO_INCREMENT,
  `saldo` decimal(14,2) NOT NULL,
  `iban` varchar(27) DEFAULT NULL,
  `codice_cliente` int(11) NOT NULL,
  `commissione` decimal(4,2) DEFAULT '0.00',
  PRIMARY KEY (`codice_conto`),
  KEY `codice_cliente_fk_idx` (`codice_cliente`),
  CONSTRAINT `codice_cliente_fk1` FOREIGN KEY (`codice_cliente`) REFERENCES `cliente` (`codice_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conto`
--

/*!40000 ALTER TABLE `conto` DISABLE KEYS */;
INSERT INTO `conto` VALUES (4,12961.70,'IT0000000000000000000000000',1,1.00);
/*!40000 ALTER TABLE `conto` ENABLE KEYS */;

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

/*!40000 ALTER TABLE `transazione` DISABLE KEYS */;
INSERT INTO `transazione` VALUES ('2cbac6f7-72bf-490c-a33a-2487e2f0d925',12,'2019-02-18',1,'Gino Rossi','16165379886',1,4,'IT32A0305801604100571333068',NULL,'EUR','Bonifico per saldo fattura numero 123'),('5f80e545-732a-48e2-bea0-a6f78084451d',12,'2019-02-18',1,'Gino Rossi','89077602640',1,4,'IT32A0305801604100571333068',NULL,'EUR','Bonifico per saldo fattura numero 123'),('e474d5fc-fd68-4ac0-b3fc-8cac97c7d8d0',12,'2019-02-18',1,'Gino Rossi','61219803523',1,4,'IT32A0305801604100571333068',NULL,'EUR','Bonifico per saldo fattura numero 123');
/*!40000 ALTER TABLE `transazione` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-11 22:45:44
