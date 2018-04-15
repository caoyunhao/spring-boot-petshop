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

 Date: 04/15/2018 13:47:24 PM
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `verify_record`
-- ----------------------------
DROP TABLE IF EXISTS `verify_record`;
CREATE TABLE `verify_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `verify_token` varchar(128) NOT NULL,
  `verify_code` varchar(10) NOT NULL,
  `overdue_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1023 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
