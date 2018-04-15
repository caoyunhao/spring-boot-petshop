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

 Date: 04/15/2018 13:46:12 PM
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `commodity_store`
-- ----------------------------
DROP TABLE IF EXISTS `commodity_store`;
CREATE TABLE `commodity_store` (
  `commodity_id` bigint(20) NOT NULL,
  `commodity_sold` bigint(20) NOT NULL DEFAULT '0',
  `commodity_last` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`commodity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
