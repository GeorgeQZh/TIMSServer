# Host: 127.0.0.1  (Version 5.5.15)
# Date: 2019-05-24 16:07:34
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "devices"
#

CREATE TABLE `devices` (
  `Id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `virtual_parent_id` int(11) DEFAULT NULL,
  `child_devices_count` int(11) DEFAULT NULL,
  `child_virtual_devices_count` int(11) DEFAULT NULL,
  `can_be_more_precise` tinyint(3) DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "devices"
#

INSERT INTO `devices` VALUES (1,'Windows OS','2014-09-09','2018-11-09',16879,NULL,NULL,NULL,0),
								(5,'Linux OS','2014-09-09','2017-09-20',16879,NULL,NULL,NULL,0),
								(117,'Debian-based Linux','2014-09-09','2015-03-24',5,NULL,1,0,1),
								(7406,'Microsoft Windows Kernel 10.0','2015-08-05','2015-09-18',1,NULL,0,0,0),
								(16879,'Operating System','2017-09-14','2017-09-18',NULL,NULL,NULL,NULL,0),
								(33449,'Apple OS','2017-09-18','2017-09-20',16879,NULL,4,0,1),
								(33450,'iOS','2017-09-18','2017-09-18',33449,NULL,0,4,1),
								(33452,'Google OS','2017-09-18','2017-09-20',16879,NULL,NULL,NULL,0),
								(33453,'Android OS','2017-09-18','2018-11-09',33452,NULL,1,6,1);

#
# Structure for table "fingerprints"
#

CREATE TABLE `fingerprints` (
  `opt55` varchar(100) NOT NULL DEFAULT '',
  `dev_name` varchar(255) DEFAULT '',
  `version` varchar(255) DEFAULT '',
  `score` int(4) DEFAULT NULL,
  `dev_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`opt55`),
  KEY `FK_fp_dev_01` (`dev_id`),
  CONSTRAINT `FK_fp_dev_01` FOREIGN KEY (`dev_id`) REFERENCES `devices` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "fingerprints"
#

INSERT INTO `fingerprints` VALUES ('1,121,3,6,15,119,252','Apple OS','',87,33449),
									('1,121,3,6,15,119,252,95,44,46','Apple OS','',87,33449),
									('1,15,3,6,44,46,47,31,33,121,249,43','Windows OS','',87,1),
									('1,28,2,3,15,6,119,12,44,47,26,121,42,121,249,33,252,42','Debian-based Linux','',87,117),
									('1,3,6,15,119,252','iOS','',87,33450),
									('1,3,6,15,26,28,51,58,59,43','Android OS','',87,33453),
									('1,3,6,15,31,33,43,44,46,47,119,121,249,252','Microsoft Windows Kernel 10.0','10',87,7406),
									('1,3,6,15,31,33,43,44,46,47,121,249,252','Windows OS','',87,1),
									('1,33,3,6,15,28,51,58,59','Android OS','',87,33453);

#
# Structure for table "ipassignments"
#

CREATE TABLE `ipassignments` (
  `opt55` varchar(40) NOT NULL DEFAULT '',
  `ip` varchar(40) NOT NULL DEFAULT '',
  `aceess` tinyint(3) DEFAULT '0',
  PRIMARY KEY (`ip`,`opt55`),
  KEY `FK_ipa_fp_01` (`opt55`),
  CONSTRAINT `FK_ipa_fp_01` FOREIGN KEY (`opt55`) REFERENCES `fingerprints` (`opt55`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "ipassignments"
#


#
# Structure for table "ips"
#

CREATE TABLE `ips` (
  `ip` varchar(20) NOT NULL DEFAULT '',
  `assigned` tinyint(3) DEFAULT NULL,
  PRIMARY KEY (`ip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "ips"
#


#
# Structure for table "users"
#

CREATE TABLE `users` (
  `userName` varchar(11) NOT NULL DEFAULT '',
  `passWord` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "users"
#

INSERT INTO `users` VALUES ('admin','admin'),('zgq','123456');
