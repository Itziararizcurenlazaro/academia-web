/*M!999999\- enable the sandbox mode */
-- MariaDB dump 10.19-11.7.2-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: academia
-- ------------------------------------------------------
-- Server version	8.0.44

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `alumno`
--

DROP TABLE IF EXISTS `alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumno` (
                          `id` int unsigned NOT NULL AUTO_INCREMENT,
                          `nombre` varchar(100) NOT NULL,
                          `apellidos` varchar(150) NOT NULL,
                          `email` varchar(100) NOT NULL,
                          `password` varchar(255) NOT NULL,
                          `rol` enum('admin','alumno') NOT NULL DEFAULT 'alumno',
                          `fecha_nacimiento` date NOT NULL,
                          `nivel` enum('principiante','intermedio','avanzado') NOT NULL,
                          `activo` tinyint(1) NOT NULL DEFAULT '1',
                          `telefono` varchar(20) NOT NULL,
                          `direccion` varchar(100) NOT NULL,
                          `foto` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
INSERT INTO `alumno` VALUES
                         (1,'Ana','Gómez','ana.gomez@example.com','1234','alumno','2010-05-11','principiante',1,'600123459','Calle 1','Usuario.png'),
                         (2,'Luis','Martínez','luis.martinez@example.com','1234','alumno','2008-09-20','intermedio',1,'600234567','Calle 2',NULL),
                         (3,'María','Pérez','maria.perez@example.com','1234','alumno','2012-03-15','principiante',1,'600345678','Calle 3',NULL),
                         (4,'Carlos','López','carlos.lopez@example.com','1234','alumno','2005-11-30','avanzado',1,'600456789','Calle 4',NULL),
                         (5,'Lucía','Sánchez','lucia.sanchez@example.com','1234','alumno','2007-07-25','intermedio',1,'600567890','Calle 5',NULL),
                         (6,'Javier','Ramírez','javier.ramirez@example.com','1234','alumno','2011-01-10','principiante',1,'600678901','Calle 6',NULL),
                         (7,'Sara','Torres','sara.torres@example.com','1234','alumno','2009-04-18','intermedio',1,'600789012','Calle 7',NULL),
                         (8,'Miguel','Flores','miguel.flores@example.com','1234','alumno','2006-12-05','avanzado',1,'600890123','Calle 8',NULL),
                         (10,'David','Hernández','david.hernandez@example.com','1234','alumno','2008-02-22','intermedio',1,'600012345','Calle 10',NULL),
                         (11,'Admin','Admin','admin@admin.com','1234','admin','2000-01-01','avanzado',1,'600000000','Admin',NULL),
                         (12,'Elena','Garcia','elena.garcia@gmail.com','1234','alumno','2012-12-12','principiante',1,'666666666','Calle de la prueba ',NULL);
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso` (
                         `id` int unsigned NOT NULL AUTO_INCREMENT,
                         `nombre` varchar(100) NOT NULL,
                         `tipo_manualidad` varchar(100) NOT NULL,
                         `nivel` enum('basico','medio','avanzado') NOT NULL,
                         `duracion_horas` int NOT NULL,
                         `precio` decimal(8,2) NOT NULL,
                         `fecha_inicio` date NOT NULL,
                         `activo` tinyint(1) NOT NULL DEFAULT '1',
                         `id_profesor` int unsigned DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `id_profesor` (`id_profesor`),
                         CONSTRAINT `curso_ibfk_1` FOREIGN KEY (`id_profesor`) REFERENCES `profesor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES
                        (1,'Pintura Básica','Pintura','basico',20,150.00,'2023-09-01',0,1),
                        (2,'Cerámica Inicial','Cerámica','medio',25,200.00,'2023-09-05',1,2),
                        (3,'Costura Creativa','Costura','basico',15,120.00,'2023-09-10',1,3),
                        (4,'Joyería Artesanal','Joyería','medio',18,180.00,'2023-09-20',1,4),
                        (5,'Carpintería Básica','Carpintería','basico',30,300.00,'2023-09-25',1,5),
                        (6,'Pintura Avanzada','Pintura','avanzado',30,250.00,'2023-10-01',1,1);
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matricula`
--

DROP TABLE IF EXISTS `matricula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `matricula` (
                             `id` int unsigned NOT NULL AUTO_INCREMENT,
                             `fecha_matricula` date NOT NULL,
                             `estado` enum('activa','cancelada','finalizada') NOT NULL,
                             `pagado` tinyint(1) NOT NULL DEFAULT '0',
                             `nota_final` decimal(4,2) DEFAULT NULL,
                             `importe_total` decimal(8,2) NOT NULL,
                             `metodo_pago` enum('efectivo','tarjeta','transferencia') NOT NULL,
                             `fecha_baja` date DEFAULT NULL,
                             `id_alumno` int unsigned DEFAULT NULL,
                             `id_curso` int unsigned DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `id_alumno` (`id_alumno`),
                             KEY `id_curso` (`id_curso`),
                             CONSTRAINT `matricula_ibfk_1` FOREIGN KEY (`id_alumno`) REFERENCES `alumno` (`id`),
                             CONSTRAINT `matricula_ibfk_2` FOREIGN KEY (`id_curso`) REFERENCES `curso` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matricula`
--

LOCK TABLES `matricula` WRITE;
/*!40000 ALTER TABLE `matricula` DISABLE KEYS */;
INSERT INTO `matricula` VALUES
                            (1,'2025-12-01','activa',1,0.00,152.00,'tarjeta',NULL,1,1),
                            (2,'2025-12-02','activa',0,NULL,200.00,'efectivo',NULL,2,2),
                            (3,'2025-12-03','finalizada',1,8.50,120.00,'tarjeta',NULL,3,3),
                            (4,'2025-12-04','activa',1,NULL,180.00,'transferencia',NULL,4,4),
                            (5,'2025-12-05','cancelada',0,NULL,300.00,'efectivo','2025-12-10',5,5),
                            (6,'2025-12-06','activa',1,NULL,250.00,'tarjeta',NULL,6,6),
                            (7,'2025-12-07','finalizada',1,7.50,150.00,'transferencia',NULL,7,1),
                            (8,'2025-12-08','activa',0,NULL,200.00,'efectivo',NULL,8,2),
                            (10,'2025-12-10','finalizada',1,9.00,180.00,'transferencia',NULL,10,4);
/*!40000 ALTER TABLE `matricula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor`
--

DROP TABLE IF EXISTS `profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesor` (
                            `id` int unsigned NOT NULL AUTO_INCREMENT,
                            `nombre` varchar(100) NOT NULL,
                            `apellidos` varchar(150) NOT NULL,
                            `email` varchar(100) NOT NULL,
                            `especialidad` varchar(100) NOT NULL,
                            `fecha_contratacion` date NOT NULL,
                            `salario` decimal(10,2) NOT NULL,
                            `activo` tinyint(1) NOT NULL DEFAULT '1',
                            `id_supervisor` int unsigned DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `email` (`email`),
                            KEY `id_supervisor` (`id_supervisor`),
                            CONSTRAINT `profesor_ibfk_1` FOREIGN KEY (`id_supervisor`) REFERENCES `profesor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor`
--

LOCK TABLES `profesor` WRITE;
/*!40000 ALTER TABLE `profesor` DISABLE KEYS */;
INSERT INTO `profesor` VALUES
                           (1,'Ana','Garcías','ana@academia.com','Pintura','2023-01-15',1200.00,1,NULL),
                           (2,'Luis','Martínez','luis@academia.com','Cerámica','2023-02-10',1300.00,1,1),
                           (3,'Elena','Rodríguez','elena@academia.com','Costura','2023-03-05',1250.00,1,1),
                           (4,'Carlos','López','carlos@academia.com','Joyería','2023-04-20',1400.00,1,2),
                           (5,'Marta','Sánchez','marta@academia.com','Carpintería','2023-05-10',1500.00,1,2);
/*!40000 ALTER TABLE `profesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'academia'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2026-05-04  3:04:34
