/*
 Navicat Premium Data Transfer

 Source Server         : **********
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : nanfang

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 13/04/2020 23:04:53
*/
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `cover` varchar(255) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `title` varchar(50) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `author` varchar(50) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `date` varchar(20) CHARACTER SET utf8mb4 NULL DEFAULT '',
  `price` varchar(50) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `newOld` varchar(50) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `contact` varchar(50) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `phone` varchar(20) CHARACTER SET utf8mb4 NULL DEFAULT '',
  `qq` varchar(20) CHARACTER SET utf8mb4 NULL DEFAULT '',
  `weChat` varchar(35) CHARACTER SET utf8mb4 NULL DEFAULT '',
  `img_1` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT '',
  `img_2` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT '',
  `img_3` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT '',
  `img_4` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT '',
  `img_5` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT '',
  `abs` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL,
  `cid` varchar(15) CHARACTER SET utf8mb4 NULL DEFAULT NULL,
  `uid` varchar(15) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_book_category_on_cid`(`cid`) USING BTREE,
  CONSTRAINT `fk_book_category_on_cid` FOREIGN KEY (`cid`) REFERENCES `category` (`cId`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `cId` varchar(30) CHARACTER SET utf8mb4 NOT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`cId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` varchar(15) CHARACTER SET utf8mb4 NOT NULL,
  `username` varchar(35) CHARACTER SET utf8mb4 NULL DEFAULT NULL,
  `password` varchar(35) CHARACTER SET utf8mb4 NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT '',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `uid` varchar(20) CHARACTER SET utf8mb4 NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
