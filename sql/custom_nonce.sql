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

 Date: 04/15/2018 13:46:28 PM
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `custom_nonce`
-- ----------------------------
DROP TABLE IF EXISTS `custom_nonce`;
CREATE TABLE `custom_nonce` (
  `custom_id` bigint(20) NOT NULL,
  `custom_nonce` varchar(128) NOT NULL,
  `overdue_time` timestamp NOT NULL DEFAULT '1970-01-01 12:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`custom_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

SET FOREIGN_KEY_CHECKS = 1;
