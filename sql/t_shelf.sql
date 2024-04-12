/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50560
Source Host           : 127.0.0.1:3306
Source Database       : shell_no

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2024-04-12 09:40:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_shelf
-- ----------------------------
DROP TABLE IF EXISTS `t_shelf`;
CREATE TABLE `t_shelf` (
  `barcode` varchar(255) NOT NULL COMMENT '条码号',
  `location_name` varchar(255) DEFAULT NULL COMMENT '馆藏地',
  `call_no` varchar(255) DEFAULT NULL COMMENT '索书号',
  `book_name` varchar(255) DEFAULT NULL COMMENT '书名',
  `shell_no` varchar(255) DEFAULT NULL COMMENT '排架号',
  `update_time` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`barcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
