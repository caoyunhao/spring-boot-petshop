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

 Date: 04/15/2018 13:46:52 PM
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `purchase_record`
-- ----------------------------
DROP TABLE IF EXISTS `purchase_record`;
CREATE TABLE `purchase_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `custom_id` bigint(20) NOT NULL,
  `commodity_id` bigint(20) NOT NULL,
  `commodity_name` varchar(20) NOT NULL,
  `commodity_description` varchar(255) DEFAULT NULL,
  `purchase_order_time` timestamp NOT NULL DEFAULT '1970-01-01 12:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `purchase_quantity` bigint(20) unsigned NOT NULL,
  `purchase_total_price` double NOT NULL,
  `purchase_statement` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
