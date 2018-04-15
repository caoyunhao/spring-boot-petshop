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

 Date: 04/15/2018 13:46:20 PM
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `custom_image`
-- ----------------------------
DROP TABLE IF EXISTS `custom_image`;
CREATE TABLE `custom_image` (
  `custom_id` bigint(20) NOT NULL,
  `image_id` bigint(20) NOT NULL,
  `image_number` bigint(20) NOT NULL,
  PRIMARY KEY (`custom_id`,`image_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

SET FOREIGN_KEY_CHECKS = 1;
