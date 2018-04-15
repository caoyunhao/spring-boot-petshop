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

 Date: 04/15/2018 13:46:06 PM
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `commodity_image`
-- ----------------------------
DROP TABLE IF EXISTS `commodity_image`;
CREATE TABLE `commodity_image` (
  `commodity_id` bigint(20) NOT NULL,
  `image_id` bigint(20) NOT NULL,
  `image_number` int(11) NOT NULL,
  PRIMARY KEY (`commodity_id`,`image_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
