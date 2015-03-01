-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.22-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Verzió:              9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for rempi
CREATE DATABASE IF NOT EXISTS `rempi` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `rempi`;


-- Dumping structure for tábla rempi.device
CREATE TABLE IF NOT EXISTS `device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `serial` varchar(255) NOT NULL,
  `device_type_id` bigint(20) NOT NULL,
  `wiring_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_serial` (`serial`),
  KEY `FK_6xl4jvqdalelcfj2w297pvbe4` (`device_type_id`),
  KEY `FK_ae63pkrp29jkmthhecukxd9mg` (`wiring_id`),
  CONSTRAINT `FK_6xl4jvqdalelcfj2w297pvbe4` FOREIGN KEY (`device_type_id`) REFERENCES `device_type` (`id`),
  CONSTRAINT `FK_ae63pkrp29jkmthhecukxd9mg` FOREIGN KEY (`wiring_id`) REFERENCES `wiring` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.device: ~0 rows (approximately)
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` (`id`, `name`, `serial`, `device_type_id`, `wiring_id`) VALUES
	(1, 'Home-test', '001', 1, 2);
/*!40000 ALTER TABLE `device` ENABLE KEYS */;


-- Dumping structure for tábla rempi.device_type
CREATE TABLE IF NOT EXISTS `device_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.device_type: ~0 rows (approximately)
/*!40000 ALTER TABLE `device_type` DISABLE KEYS */;
INSERT INTO `device_type` (`id`, `name`) VALUES
	(1, 'Water-sensor');
/*!40000 ALTER TABLE `device_type` ENABLE KEYS */;


-- Dumping structure for tábla rempi.device_type_commands
CREATE TABLE IF NOT EXISTS `device_type_commands` (
  `device_type_id` bigint(20) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  KEY `FK_mf5p3k07dkr10v0hx6je4diig` (`device_type_id`),
  CONSTRAINT `FK_mf5p3k07dkr10v0hx6je4diig` FOREIGN KEY (`device_type_id`) REFERENCES `device_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.device_type_commands: ~0 rows (approximately)
/*!40000 ALTER TABLE `device_type_commands` DISABLE KEYS */;
INSERT INTO `device_type_commands` (`device_type_id`, `type`) VALUES
	(1, 'org.landasource.rempi.manager.core.devicecommand.GpioCommand');
/*!40000 ALTER TABLE `device_type_commands` ENABLE KEYS */;


-- Dumping structure for tábla rempi.pintables
CREATE TABLE IF NOT EXISTS `pintables` (
  `wiring_id` bigint(20) NOT NULL,
  `gpio_pin_name` varchar(100) NOT NULL,
  `gpio_pin` varchar(50) NOT NULL,
  PRIMARY KEY (`wiring_id`,`gpio_pin`),
  CONSTRAINT `FK_miruo6uk294aydpvnfibtwt2q` FOREIGN KEY (`wiring_id`) REFERENCES `wiring` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.pintables: ~0 rows (approximately)
/*!40000 ALTER TABLE `pintables` DISABLE KEYS */;
INSERT INTO `pintables` (`wiring_id`, `gpio_pin_name`, `gpio_pin`) VALUES
	(2, 'CH1', 'GPIO_17'),
	(2, 'CH2', 'GPIO_18'),
	(2, 'CH4', 'GPIO_22'),
	(2, 'CH3', 'GPIO_23'),
	(2, 'CH5', 'GPIO_24'),
	(2, 'kj', 'GPIO_7');
/*!40000 ALTER TABLE `pintables` ENABLE KEYS */;


-- Dumping structure for tábla rempi.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.roles: ~2 rows (approximately)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `name`) VALUES
	(1, 'ADMIN'),
	(2, 'CUSTOMER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;


-- Dumping structure for tábla rempi.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.users: ~1 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `enabled`, `full_name`, `password`, `username`) VALUES
	(1, b'1', 'admin', '$2a$10$VsBlC.U9EidrC0IANRIwp.N9Iams9atVJZO8nMP5KpYvUdfQ1V1zC', 'admin'),
	(2, b'1', 'user', '$2a$10$qbH84GdZL2VZnxdD4M29auY/ns2bI/BzW4Nk2xuP3yCjIIWmJ7Y22', 'user');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


-- Dumping structure for tábla rempi.user_roles
CREATE TABLE IF NOT EXISTS `user_roles` (
  `users_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  PRIMARY KEY (`users_id`,`roles_id`),
  KEY `FK_amwlmdeik2qdnksxgd566knop` (`roles_id`),
  CONSTRAINT `FK_amwlmdeik2qdnksxgd566knop` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FK_p1dw20xq41q8rdkd8oe78vfhe` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.user_roles: ~1 rows (approximately)
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` (`users_id`, `roles_id`) VALUES
	(1, 1),
	(2, 2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;


-- Dumping structure for tábla rempi.wiring
CREATE TABLE IF NOT EXISTS `wiring` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.wiring: ~0 rows (approximately)
/*!40000 ALTER TABLE `wiring` DISABLE KEYS */;
INSERT INTO `wiring` (`id`, `name`) VALUES
	(2, 'watering');
/*!40000 ALTER TABLE `wiring` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
