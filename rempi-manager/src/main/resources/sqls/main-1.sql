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
DROP DATABASE IF EXISTS `rempi`;
CREATE DATABASE IF NOT EXISTS `rempi` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `rempi`;


-- Dumping structure for tábla rempi.device
DROP TABLE IF EXISTS `device`;
CREATE TABLE IF NOT EXISTS `device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `serial` varchar(255) NOT NULL,
  `device_type_id` bigint(20) NOT NULL,
  `wiring_id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_serial` (`serial`),
  KEY `FK_6xl4jvqdalelcfj2w297pvbe4` (`device_type_id`),
  KEY `FK_ae63pkrp29jkmthhecukxd9mg` (`wiring_id`),
  CONSTRAINT `FK_6xl4jvqdalelcfj2w297pvbe4` FOREIGN KEY (`device_type_id`) REFERENCES `device_type` (`id`),
  CONSTRAINT `FK_ae63pkrp29jkmthhecukxd9mg` FOREIGN KEY (`wiring_id`) REFERENCES `wiring` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.device: ~3 rows (approximately)
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` (`id`, `name`, `serial`, `device_type_id`, `wiring_id`, `active`) VALUES
	(1, 'Home-test', '001', 1, 2, b'1'),
	(4, 'Prototype-1', '000000002a5992c7', 1, 3, b'1'),
	(5, '003', '004', 1, 2, b'1');
/*!40000 ALTER TABLE `device` ENABLE KEYS */;


-- Dumping structure for tábla rempi.device_metadata
DROP TABLE IF EXISTS `device_metadata`;
CREATE TABLE IF NOT EXISTS `device_metadata` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `last_checkin` datetime DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `device_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6cdhvsbk2s5gtmmg9iq42gy4u` (`device_id`),
  CONSTRAINT `FK_6cdhvsbk2s5gtmmg9iq42gy4u` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.device_metadata: ~3 rows (approximately)
/*!40000 ALTER TABLE `device_metadata` DISABLE KEYS */;
INSERT INTO `device_metadata` (`id`, `last_checkin`, `notes`, `device_id`) VALUES
	(1, '2015-03-22 18:38:50', 'Ez a kerti csapsfs', 1),
	(2, '2015-03-25 18:16:48', 'tel: \r\nSIM PIN: 6352', 4),
	(3, NULL, '', 5);
/*!40000 ALTER TABLE `device_metadata` ENABLE KEYS */;


-- Dumping structure for tábla rempi.device_mode
DROP TABLE IF EXISTS `device_mode`;
CREATE TABLE IF NOT EXISTS `device_mode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_65c98p9eno5fkhg2px2xycwss` (`device_id`),
  UNIQUE KEY `unique_for_device` (`device_id`),
  CONSTRAINT `FK_65c98p9eno5fkhg2px2xycwss` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.device_mode: ~3 rows (approximately)
/*!40000 ALTER TABLE `device_mode` DISABLE KEYS */;
INSERT INTO `device_mode` (`id`, `device_id`) VALUES
	(1, 1),
	(2, 4),
	(3, 5);
/*!40000 ALTER TABLE `device_mode` ENABLE KEYS */;


-- Dumping structure for tábla rempi.device_type
DROP TABLE IF EXISTS `device_type`;
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
DROP TABLE IF EXISTS `device_type_commands`;
CREATE TABLE IF NOT EXISTS `device_type_commands` (
  `device_type_id` bigint(20) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  KEY `FK_mf5p3k07dkr10v0hx6je4diig` (`device_type_id`),
  CONSTRAINT `FK_mf5p3k07dkr10v0hx6je4diig` FOREIGN KEY (`device_type_id`) REFERENCES `device_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.device_type_commands: ~2 rows (approximately)
/*!40000 ALTER TABLE `device_type_commands` DISABLE KEYS */;
INSERT INTO `device_type_commands` (`device_type_id`, `type`) VALUES
	(1, 'org.landasource.rempi.manager.core.devicecommand.GpioCommand'),
	(1, 'org.landasource.rempi.manager.core.devicecommand.SensorCommand');
/*!40000 ALTER TABLE `device_type_commands` ENABLE KEYS */;


-- Dumping structure for tábla rempi.operation_modes
DROP TABLE IF EXISTS `operation_modes`;
CREATE TABLE IF NOT EXISTS `operation_modes` (
  `device_mode_id` bigint(20) NOT NULL,
  `gpio_pin_mode` varchar(100) NOT NULL,
  `gpio_pin` varchar(50) NOT NULL,
  PRIMARY KEY (`device_mode_id`,`gpio_pin`),
  CONSTRAINT `FK_hnai8h5ojh8y0qpl6s6bo9et4` FOREIGN KEY (`device_mode_id`) REFERENCES `device_mode` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.operation_modes: ~15 rows (approximately)
/*!40000 ALTER TABLE `operation_modes` DISABLE KEYS */;
INSERT INTO `operation_modes` (`device_mode_id`, `gpio_pin_mode`, `gpio_pin`) VALUES
	(1, 'ON', 'GPIO_17'),
	(1, 'ON', 'GPIO_18'),
	(1, 'ON', 'GPIO_22'),
	(1, 'ON', 'GPIO_23'),
	(1, 'ON', 'GPIO_24'),
	(2, 'ON', 'GPIO_14'),
	(2, 'OFF', 'GPIO_15'),
	(2, 'OFF', 'GPIO_17'),
	(2, 'OFF', 'GPIO_18'),
	(2, 'OFF', 'GPIO_2'),
	(2, 'OFF', 'GPIO_22'),
	(2, 'OFF', 'GPIO_23'),
	(2, 'OFF', 'GPIO_24'),
	(2, 'OFF', 'GPIO_4'),
	(2, 'OFF', 'GPIO_8');
/*!40000 ALTER TABLE `operation_modes` ENABLE KEYS */;


-- Dumping structure for tábla rempi.pintables
DROP TABLE IF EXISTS `pintables`;
CREATE TABLE IF NOT EXISTS `pintables` (
  `wiring_id` bigint(20) NOT NULL,
  `gpio_pin_name` varchar(100) NOT NULL,
  `gpio_pin` varchar(50) NOT NULL,
  PRIMARY KEY (`wiring_id`,`gpio_pin`),
  CONSTRAINT `FK_miruo6uk294aydpvnfibtwt2q` FOREIGN KEY (`wiring_id`) REFERENCES `wiring` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.pintables: ~13 rows (approximately)
/*!40000 ALTER TABLE `pintables` DISABLE KEYS */;
INSERT INTO `pintables` (`wiring_id`, `gpio_pin_name`, `gpio_pin`) VALUES
	(2, 'CH1', 'GPIO_17'),
	(2, 'CH2', 'GPIO_18'),
	(2, 'CH4', 'GPIO_22'),
	(2, 'CH3', 'GPIO_23'),
	(2, 'CH5', 'GPIO_24'),
	(3, 'CH3', 'GPIO_15'),
	(3, 'CH4', 'GPIO_17'),
	(3, 'CH5', 'GPIO_18'),
	(3, 'CH6', 'GPIO_22'),
	(3, 'CH7', 'GPIO_23'),
	(3, 'CH8', 'GPIO_24'),
	(3, 'CH1', 'GPIO_4'),
	(3, 'CH2', 'GPIO_8');
/*!40000 ALTER TABLE `pintables` ENABLE KEYS */;


-- Dumping structure for tábla rempi.qrtz_blob_triggers
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE IF NOT EXISTS `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.qrtz_blob_triggers: ~0 rows (approximately)
/*!40000 ALTER TABLE `qrtz_blob_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_blob_triggers` ENABLE KEYS */;


-- Dumping structure for tábla rempi.qrtz_calendars
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE IF NOT EXISTS `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.qrtz_calendars: ~0 rows (approximately)
/*!40000 ALTER TABLE `qrtz_calendars` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_calendars` ENABLE KEYS */;


-- Dumping structure for tábla rempi.qrtz_cron_triggers
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE IF NOT EXISTS `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.qrtz_cron_triggers: ~0 rows (approximately)
/*!40000 ALTER TABLE `qrtz_cron_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_cron_triggers` ENABLE KEYS */;


-- Dumping structure for tábla rempi.qrtz_fired_triggers
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE IF NOT EXISTS `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(19) NOT NULL,
  `SCHED_TIME` bigint(19) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` tinyint(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.qrtz_fired_triggers: ~1 rows (approximately)
/*!40000 ALTER TABLE `qrtz_fired_triggers` DISABLE KEYS */;
INSERT INTO `qrtz_fired_triggers` (`SCHED_NAME`, `ENTRY_ID`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `INSTANCE_NAME`, `FIRED_TIME`, `SCHED_TIME`, `PRIORITY`, `STATE`, `JOB_NAME`, `JOB_GROUP`, `IS_NONCONCURRENT`, `REQUESTS_RECOVERY`) VALUES
	('schedulerFactoryBean', 'Zsolti-PC14273180730471427318073364', 'sampleJobTrigger', 'DEFAULT', 'Zsolti-PC1427318073047', 1427318735009, 1427318736990, 0, 'ACQUIRED', NULL, NULL, 0, 0);
/*!40000 ALTER TABLE `qrtz_fired_triggers` ENABLE KEYS */;


-- Dumping structure for tábla rempi.qrtz_job_details
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE IF NOT EXISTS `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` tinyint(1) NOT NULL,
  `IS_NONCONCURRENT` tinyint(1) NOT NULL,
  `IS_UPDATE_DATA` tinyint(1) NOT NULL,
  `REQUESTS_RECOVERY` tinyint(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.qrtz_job_details: ~0 rows (approximately)
/*!40000 ALTER TABLE `qrtz_job_details` DISABLE KEYS */;
INSERT INTO `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `JOB_CLASS_NAME`, `IS_DURABLE`, `IS_NONCONCURRENT`, `IS_UPDATE_DATA`, `REQUESTS_RECOVERY`, `JOB_DATA`) VALUES
	('schedulerFactoryBean', 'sampleJobDetail', 'DEFAULT', NULL, 'org.landasource.rempi.manager.core.schedule.SampleJob', 1, 0, 0, 0, _binary 0x230D0A23576564204D61722032352032323A31343A33332043455420323031350D0A);
/*!40000 ALTER TABLE `qrtz_job_details` ENABLE KEYS */;


-- Dumping structure for tábla rempi.qrtz_locks
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE IF NOT EXISTS `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.qrtz_locks: ~2 rows (approximately)
/*!40000 ALTER TABLE `qrtz_locks` DISABLE KEYS */;
INSERT INTO `qrtz_locks` (`SCHED_NAME`, `LOCK_NAME`) VALUES
	('schedulerFactoryBean', 'STATE_ACCESS'),
	('schedulerFactoryBean', 'TRIGGER_ACCESS');
/*!40000 ALTER TABLE `qrtz_locks` ENABLE KEYS */;


-- Dumping structure for tábla rempi.qrtz_paused_trigger_grps
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE IF NOT EXISTS `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.qrtz_paused_trigger_grps: ~0 rows (approximately)
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` ENABLE KEYS */;


-- Dumping structure for tábla rempi.qrtz_scheduler_state
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE IF NOT EXISTS `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(19) NOT NULL,
  `CHECKIN_INTERVAL` bigint(19) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.qrtz_scheduler_state: ~1 rows (approximately)
/*!40000 ALTER TABLE `qrtz_scheduler_state` DISABLE KEYS */;
INSERT INTO `qrtz_scheduler_state` (`SCHED_NAME`, `INSTANCE_NAME`, `LAST_CHECKIN_TIME`, `CHECKIN_INTERVAL`) VALUES
	('schedulerFactoryBean', 'Zsolti-PC1427318073047', 1427318733860, 20000);
/*!40000 ALTER TABLE `qrtz_scheduler_state` ENABLE KEYS */;


-- Dumping structure for tábla rempi.qrtz_simple_triggers
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE IF NOT EXISTS `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.qrtz_simple_triggers: ~1 rows (approximately)
/*!40000 ALTER TABLE `qrtz_simple_triggers` DISABLE KEYS */;
INSERT INTO `qrtz_simple_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `REPEAT_COUNT`, `REPEAT_INTERVAL`, `TIMES_TRIGGERED`) VALUES
	('schedulerFactoryBean', 'sampleJobTrigger', 'DEFAULT', -1, 2000, 332);
/*!40000 ALTER TABLE `qrtz_simple_triggers` ENABLE KEYS */;


-- Dumping structure for tábla rempi.qrtz_simprop_triggers
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE IF NOT EXISTS `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` tinyint(1) DEFAULT NULL,
  `BOOL_PROP_2` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.qrtz_simprop_triggers: ~0 rows (approximately)
/*!40000 ALTER TABLE `qrtz_simprop_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` ENABLE KEYS */;


-- Dumping structure for tábla rempi.qrtz_triggers
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE IF NOT EXISTS `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(19) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(19) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(19) NOT NULL,
  `END_TIME` bigint(19) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.qrtz_triggers: ~1 rows (approximately)
/*!40000 ALTER TABLE `qrtz_triggers` DISABLE KEYS */;
INSERT INTO `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `NEXT_FIRE_TIME`, `PREV_FIRE_TIME`, `PRIORITY`, `TRIGGER_STATE`, `TRIGGER_TYPE`, `START_TIME`, `END_TIME`, `CALENDAR_NAME`, `MISFIRE_INSTR`, `JOB_DATA`) VALUES
	('schedulerFactoryBean', 'sampleJobTrigger', 'DEFAULT', 'sampleJobDetail', 'DEFAULT', NULL, 1427318736990, 1427318734990, 0, 'ACQUIRED', 'SIMPLE', 1427318072990, 0, NULL, 4, _binary '');
/*!40000 ALTER TABLE `qrtz_triggers` ENABLE KEYS */;


-- Dumping structure for tábla rempi.roles
DROP TABLE IF EXISTS `roles`;
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
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.users: ~2 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `enabled`, `full_name`, `password`, `username`) VALUES
	(1, b'1', 'admin', '$2a$10$VsBlC.U9EidrC0IANRIwp.N9Iams9atVJZO8nMP5KpYvUdfQ1V1zC', 'admin'),
	(2, b'1', 'user', '$2a$10$qbH84GdZL2VZnxdD4M29auY/ns2bI/BzW4Nk2xuP3yCjIIWmJ7Y22', 'user');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


-- Dumping structure for tábla rempi.user_roles
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE IF NOT EXISTS `user_roles` (
  `users_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  PRIMARY KEY (`users_id`,`roles_id`),
  KEY `FK_amwlmdeik2qdnksxgd566knop` (`roles_id`),
  CONSTRAINT `FK_amwlmdeik2qdnksxgd566knop` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FK_p1dw20xq41q8rdkd8oe78vfhe` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.user_roles: ~2 rows (approximately)
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` (`users_id`, `roles_id`) VALUES
	(1, 1),
	(2, 2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;


-- Dumping structure for tábla rempi.wiring
DROP TABLE IF EXISTS `wiring`;
CREATE TABLE IF NOT EXISTS `wiring` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table rempi.wiring: ~2 rows (approximately)
/*!40000 ALTER TABLE `wiring` DISABLE KEYS */;
INSERT INTO `wiring` (`id`, `name`) VALUES
	(3, 'prototype-pi2'),
	(2, 'watering');
/*!40000 ALTER TABLE `wiring` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
