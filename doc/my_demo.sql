/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 127.0.0.1:3306
 Source Schema         : my_demo

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 24/02/2021 14:12:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单地址',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `sort` int(32) NULL DEFAULT NULL COMMENT '菜单排序',
  `show_status` smallint(1) NULL DEFAULT 0 COMMENT '是否显示   0不显示  1显示',
  `parent_id` bigint(64) NULL DEFAULT 0 COMMENT '父菜单ID   0为顶级菜单',
  `create_user` bigint(64) NULL DEFAULT NULL COMMENT '创建人员ID',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` bigint(64) NULL DEFAULT NULL COMMENT '修改人员ID',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '首页', '/index', NULL, 1, 1, 0, NULL, '2021-01-16 16:22:59', NULL, '2021-01-16 16:23:03');
INSERT INTO `menu` VALUES (2, '系统设置', '', NULL, 2, 1, 0, NULL, '2021-01-16 16:23:32', NULL, '2021-01-16 16:23:36');
INSERT INTO `menu` VALUES (3, '菜单管理', '/menuView', NULL, 1, 1, 2, NULL, '2021-01-16 16:23:56', NULL, '2021-01-23 15:09:10');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `age` int(16) NULL DEFAULT NULL COMMENT '年龄',
  `sex` smallint(1) NULL DEFAULT NULL COMMENT '性别',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '注册时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '313821660', 'cedeece8b1ba8ac230f18a11c60416d1', '池臣', NULL, NULL, NULL, '2021-02-22 10:59:09', '2021-02-22 10:59:09');

SET FOREIGN_KEY_CHECKS = 1;
