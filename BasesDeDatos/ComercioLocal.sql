CREATE DATABASE  IF NOT EXISTS `comerciolocal` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `comerciolocal`;
-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: comerciolocal
-- ------------------------------------------------------
-- Server version	8.0.45

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `codigo` int NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `direccionEnvio` varchar(50) DEFAULT NULL,
  `telefono` varchar(9) NOT NULL,
  `correo` varchar(50) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (4441,'Felipe Galarza','2005-11-25','Avenida San Juan 12','654879521','Felip@gmail.com'),(4442,'Carolina Varon','1998-09-22','Calle Alcala 337','658789421','carol@gmail.es'),(4443,'Camilo Arturo','1995-07-18','Calle de Vergara 204','621354789','camilo@gmail.com'),(4444,'Thomas Paz','2001-03-08','Sabino Arana Etorbidea 12','632998877','thomas@hotmail.es');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fabricante`
--

DROP TABLE IF EXISTS `fabricante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fabricante` (
  `codigo` int NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `anyoFundacion` int NOT NULL,
  `lugarSede` varchar(50) NOT NULL,
  `empleados` int NOT NULL,
  `sitioWeb` varchar(50) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fabricante`
--

LOCK TABLES `fabricante` WRITE;
/*!40000 ALTER TABLE `fabricante` DISABLE KEYS */;
INSERT INTO `fabricante` VALUES (1111,'Santiago Bermudez',2016,'PLAZA, Zaragoza',20,'www.san.com'),(1112,'Diego Silvestre',2014,'VENECIA, Zaragoza',15,'www.die.com'),(1113,'Fernando Costa',2010,'COGULLADA,Zaragoza',25,'www.fer.com');
/*!40000 ALTER TABLE `fabricante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lineapedido`
--

DROP TABLE IF EXISTS `lineapedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lineapedido` (
  `codigoPedido` int NOT NULL,
  `codigoProducto` int NOT NULL,
  `unidadesCompradas` int NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  PRIMARY KEY (`codigoPedido`,`codigoProducto`),
  KEY `cod_Pro_fk` (`codigoProducto`),
  CONSTRAINT `cod_Ped_fk` FOREIGN KEY (`codigoPedido`) REFERENCES `pedido` (`codigo`) ON DELETE CASCADE,
  CONSTRAINT `cod_Pro_fk` FOREIGN KEY (`codigoProducto`) REFERENCES `producto` (`codigo`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lineapedido`
--

LOCK TABLES `lineapedido` WRITE;
/*!40000 ALTER TABLE `lineapedido` DISABLE KEYS */;
INSERT INTO `lineapedido` VALUES (0,2221,2,1457.98),(0,2222,1,15.99),(1,2221,1,728.99),(1,2223,2,199.80),(2,2221,3,2186.97),(3,2221,1,728.99),(3,2225,2,131.98),(4,2221,1,728.99),(4,2224,2,1299.10),(5,2221,3,2186.97);
/*!40000 ALTER TABLE `lineapedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `codigo` int NOT NULL,
  `codigoVendedor` int NOT NULL,
  `codigoCliente` int NOT NULL,
  `fechaRealizacion` date NOT NULL,
  `fechaEntrega` date NOT NULL,
  `estado` varchar(20) NOT NULL,
  `importe` decimal(10,2) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `cof_ven_fk` (`codigoVendedor`),
  KEY `cof_cli_fk` (`codigoCliente`),
  CONSTRAINT `cof_cli_fk` FOREIGN KEY (`codigoCliente`) REFERENCES `cliente` (`codigo`) ON DELETE CASCADE,
  CONSTRAINT `cof_ven_fk` FOREIGN KEY (`codigoVendedor`) REFERENCES `vendedor` (`codigo`) ON DELETE CASCADE,
  CONSTRAINT `pedido_chk_1` CHECK ((`estado` in (_utf8mb4'realizado',_utf8mb4'cancelado',_utf8mb4'enviado',_utf8mb4'entregado',_utf8mb4'devuelto')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (0,3331,4441,'2026-05-22','2026-05-23','entregado',1473.97),(1,3332,4442,'2026-05-22','2026-05-29','realizado',928.79),(2,3331,4443,'2026-05-22','2026-05-29','realizado',2186.97),(3,3333,4444,'2026-05-22','2026-05-29','realizado',860.97),(4,3334,4442,'2026-05-22','2026-05-29','realizado',2028.09),(5,3331,4442,'2026-05-22','2026-05-29','realizado',2186.97);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `codigo` int NOT NULL,
  `codigoFabricante` int NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `categoria` varchar(50) NOT NULL,
  `disponibilidad` varchar(20) NOT NULL,
  `precioVenta` decimal(10,2) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fkProductoFabricante` (`codigoFabricante`),
  CONSTRAINT `fkProductoFabricante` FOREIGN KEY (`codigoFabricante`) REFERENCES `fabricante` (`codigo`) ON DELETE CASCADE,
  CONSTRAINT `producto_chk_1` CHECK ((`disponibilidad` in (_utf8mb4'disponible',_utf8mb4'pocas unidades',_utf8mb4'no disponible')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (2221,1111,'Portatil Lenovo 3','Informatica','disponible',728.99),(2222,1112,'Raton Inalambrico M185','Accesorios','disponible',15.99),(2223,1112,'Tecla inalambrico MX-S','Accesorios','pocas unidades',99.90),(2224,1111,'Portatil-Samsung Galaxy','Informatica','disponible',649.55),(2225,1113,'Cascos JBL','Audio','disponible',65.99),(2226,1113,'Auriculares Sony WH','Audio','no disponible',49.99);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendedor`
--

DROP TABLE IF EXISTS `vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendedor` (
  `codigo` int NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `fechaAlta` date NOT NULL,
  `domicilio` varchar(50) NOT NULL,
  `salario` decimal(10,2) NOT NULL,
  `porcentaje` decimal(10,2) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedor`
--

LOCK TABLES `vendedor` WRITE;
/*!40000 ALTER TABLE `vendedor` DISABLE KEYS */;
INSERT INTO `vendedor` VALUES (3331,'Marcelas Reyes','2016-12-01','Jose Ramon 11',1200.99,0.10),(3332,'Daniela Garcia','2020-05-01','Via Hispanidad 18',1567.98,0.05),(3333,'Jose Ojeda','2015-05-01','Avenida Salvador Allende 12',1254.87,0.15),(3334,'Jesus Zambrano','2015-02-18','Calle Jenorimo 20',1450.70,0.15);
/*!40000 ALTER TABLE `vendedor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-22  2:40:09
