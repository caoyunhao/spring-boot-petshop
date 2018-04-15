/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost
 Source Database       : petshop

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : utf-8

 Date: 04/15/2018 13:46:15 PM
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `custom`
-- ----------------------------
DROP TABLE IF EXISTS `custom`;
CREATE TABLE `custom` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `custom_name` varchar(100) NOT NULL,
  `custom_name_encrypted` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `custom_nickname` varchar(100) DEFAULT NULL,
  `custom_phone_number` varchar(20) DEFAULT NULL,
  `custom_email` varchar(30) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT '1970-01-01 12:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NOT NULL DEFAULT '1970-01-01 12:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
