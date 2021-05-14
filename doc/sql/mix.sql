/*
 Navicat Premium Data Transfer

 Source Server         : docker mysql
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : mysql:3306
 Source Schema         : mix

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 14/05/2021 20:57:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父菜单ID',
  `order_sort` int(0) NULL DEFAULT NULL COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `visible` tinyint(0) NULL DEFAULT NULL COMMENT '菜单显示状态（0显示 1隐藏）',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, 'A', 0, 1, '/3242/234', '324/234/234/', NULL, 1, NULL, NULL, NULL, '2021-04-25 17:09:54', NULL);
INSERT INTO `t_menu` VALUES (2, 'b', 0, 1, '/3242/234', '324/234/234/', NULL, 1, NULL, NULL, NULL, '2021-04-25 17:09:54', NULL);
INSERT INTO `t_menu` VALUES (3, 'c', 0, 1, '/3242/234', '324/234/234/', NULL, 1, NULL, NULL, NULL, '2021-04-25 17:09:54', NULL);
INSERT INTO `t_menu` VALUES (4, 'd', 0, 1, '/3242/234', '324/234/234/', NULL, 1, NULL, NULL, NULL, '2021-04-25 17:09:54', NULL);
INSERT INTO `t_menu` VALUES (5, 'e', 0, 1, '/3242/234', '324/234/234/', NULL, 1, NULL, NULL, NULL, '2021-04-25 17:09:54', NULL);
INSERT INTO `t_menu` VALUES (6, 'f', 0, 1, '/3242/234', '324/234/234/', NULL, 1, NULL, NULL, NULL, '2021-04-25 17:09:54', NULL);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `role_sort` int(0) NULL DEFAULT NULL COMMENT '显示顺序',
  `is_delete` tinyint(0) NULL DEFAULT NULL COMMENT '是否删除(0正常1已删除)',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '角色状态（0正常 1停用）',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'ADMIN', 'ADMIN', 1, NULL, 1, '管理员', '2021-03-25 11:41:23', '2021-03-26 11:41:25');
INSERT INTO `t_role` VALUES (2, 'USER', 'USER', 2, NULL, 1, '普通用户', '2021-03-25 11:41:43', '2021-03-25 11:41:46');
INSERT INTO `t_role` VALUES (3, 'TTTT', 'RT1', 4, NULL, 0, 'TTT1213', '2021-04-25 17:05:48', '2021-04-25 17:08:19');
INSERT INTO `t_role` VALUES (4, 'TTTTaaaa', 'RTaaaa', 4, 1, 0, 'TTT1213', '2021-04-25 18:35:18', '2021-04-25 18:36:02');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(0) NOT NULL COMMENT '菜单ID',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES (1, 3, 1, '2021-04-25 17:10:54', '2021-04-25 17:10:54');
INSERT INTO `t_role_menu` VALUES (2, 3, 2, '2021-04-25 17:10:54', '2021-04-25 17:10:54');
INSERT INTO `t_role_menu` VALUES (3, 3, 3, '2021-04-25 17:10:54', '2021-04-25 17:10:54');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `is_delete` tinyint(0) NULL DEFAULT NULL COMMENT '是否已删除（0正常 1已删除）',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '帐号状态（0正常 1停用）',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', '1@qq.com', '32', 'e10adc3949ba59abbe56e057f20f883e', NULL, 24, '2021-03-24 16:11:52', '234', '2021-03-24 16:11:45', '2021-03-24 16:11:47');
INSERT INTO `t_user` VALUES (8, 'admin1', '2@qq.com', '32', 'e10adc3949ba59abbe56e057f20f883e', NULL, 24, '2021-03-24 16:11:52', '234', '2021-03-24 16:11:45', '2021-03-24 16:11:47');
INSERT INTO `t_user` VALUES (9, 'admin2', '3@qq.com', '32', 'e10adc3949ba59abbe56e057f20f883e', NULL, 24, '2021-03-24 16:11:52', '234', '2021-03-24 16:11:45', '2021-03-24 16:11:47');
INSERT INTO `t_user` VALUES (10, 'admin3', '4@qq.com', '32', 'e10adc3949ba59abbe56e057f20f883e', NULL, 24, '2021-03-24 16:11:52', '234', '2021-03-24 16:11:45', '2021-03-24 16:11:47');
INSERT INTO `t_user` VALUES (11, 'admin4', '5@qq.com', '32', 'e10adc3949ba59abbe56e057f20f883e', NULL, 24, '2021-03-24 16:11:52', '234', '2021-03-24 16:11:45', '2021-03-24 16:11:47');
INSERT INTO `t_user` VALUES (12, 'admin5', '6@qq.com', '32', 'e10adc3949ba59abbe56e057f20f883e', NULL, 24, '2021-03-24 16:11:52', '234', '2021-03-24 16:11:45', '2021-03-24 16:11:47');
INSERT INTO `t_user` VALUES (13, 'admin6', '7@qq.com', '32', 'e10adc3949ba59abbe56e057f20f883e', NULL, 24, '2021-03-24 16:11:52', '234', '2021-03-24 16:11:45', '2021-03-24 16:11:47');
INSERT INTO `t_user` VALUES (14, 'admin7', '8@qq.com', '32', 'e10adc3949ba59abbe56e057f20f883e', NULL, 24, '2021-03-24 16:11:52', '234', '2021-03-24 16:11:45', '2021-03-24 16:11:47');
INSERT INTO `t_user` VALUES (15, 'admin8', '9@qq.com', '32', 'e10adc3949ba59abbe56e057f20f883e', NULL, 24, '2021-03-24 16:11:52', '234', '2021-03-24 16:11:45', '2021-03-24 16:11:47');
INSERT INTO `t_user` VALUES (16, 'admin9', '10@qq.com', '32', 'e10adc3949ba59abbe56e057f20f883e', NULL, 24, '2021-03-24 16:11:52', '234', '2021-03-24 16:11:45', '2021-03-24 16:11:47');
INSERT INTO `t_user` VALUES (17, 'admin10', '11@qq.com', '32', 'e10adc3949ba59abbe56e057f20f883e', NULL, 24, '2021-03-24 16:11:52', '234', '2021-03-24 16:11:45', '2021-03-24 16:11:47');
INSERT INTO `t_user` VALUES (18, 'admin11', '12@qq.com', '32', 'e10adc3949ba59abbe56e057f20f883e', NULL, 24, '2021-03-24 16:11:52', '234', '2021-03-24 16:11:45', '2021-03-24 16:11:47');
INSERT INTO `t_user` VALUES (20, 'abc', '13@qq.com', '15928372123', 'e10adc3949ba59abbe56e057f20f883e', NULL, 1, NULL, '12k3ljafdsaf', '2021-04-25 11:29:47', '2021-04-25 11:42:43');
INSERT INTO `t_user` VALUES (21, 'abc1', '14@qq.com', '15928372123', 'e10adc3949ba59abbe56e057f20f883e', NULL, 1, NULL, '12k3ljafdsaf', '2021-04-25 11:31:43', '2021-04-25 11:42:01');
INSERT INTO `t_user` VALUES (22, 'abc2', '15@qq.com', '15928372123', 'e10adc3949ba59abbe56e057f20f883e', NULL, 1, NULL, '12k3ljafdsaf', '2021-04-25 11:31:47', NULL);
INSERT INTO `t_user` VALUES (24, 'abc3', '16@qq.com', '15928372123', 'e10adc3949ba59abbe56e057f20f883e', NULL, 1, NULL, '12k3ljafdsaf', '2021-04-25 11:34:01', NULL);
INSERT INTO `t_user` VALUES (25, 'abc31', '17@qq.com', '15928372123', 'e10adc3949ba59abbe56e057f20f883e', 1, 0, NULL, '12k3ljafdsaf', '2021-04-25 18:33:28', '2021-04-25 18:34:44');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1, 1, '2021-03-25 11:42:01', '2021-03-26 11:42:03');
INSERT INTO `t_user_role` VALUES (2, 1, 2, '2021-04-22 14:39:33', '2021-04-22 14:39:37');
INSERT INTO `t_user_role` VALUES (27, 8, 1, '2021-04-25 15:23:15', '2021-04-25 15:23:15');
INSERT INTO `t_user_role` VALUES (28, 8, 2, '2021-04-25 15:23:15', '2021-04-25 15:23:15');

SET FOREIGN_KEY_CHECKS = 1;
