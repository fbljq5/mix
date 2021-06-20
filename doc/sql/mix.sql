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

 Date: 21/06/2021 06:32:16
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
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, '权限管理', 0, 1, '/admin/user', NULL, NULL, 1, NULL, '<RotateLeftOutlined />', NULL, '2021-04-25 17:09:54', NULL);
INSERT INTO `t_menu` VALUES (2, '用户管理', 1, 2, '/admin/user', NULL, NULL, 1, NULL, NULL, NULL, '2021-04-25 17:09:54', NULL);
INSERT INTO `t_menu` VALUES (3, '角色管理', 1, 3, '/admin/role', NULL, NULL, 1, NULL, NULL, NULL, '2021-04-25 17:09:54', NULL);
INSERT INTO `t_menu` VALUES (4, '菜单管理', 1, 4, '/admin/menu', NULL, NULL, 1, NULL, NULL, NULL, '2021-04-25 17:09:54', NULL);
INSERT INTO `t_menu` VALUES (5, '监控中心', 0, 5, '/monitor/server', NULL, NULL, 1, NULL, NULL, NULL, '2021-04-25 17:09:54', NULL);
INSERT INTO `t_menu` VALUES (6, '服务器监控', 5, 6, '/monitor/server', NULL, NULL, 1, NULL, NULL, NULL, '2021-04-25 17:09:54', NULL);
INSERT INTO `t_menu` VALUES (7, '注册中心监控', 5, 7, '/monitor/register', NULL, NULL, 1, NULL, NULL, NULL, '2021-05-27 06:44:22', NULL);
INSERT INTO `t_menu` VALUES (8, '系统管理', 0, 8, '/system/param', NULL, NULL, 1, NULL, NULL, NULL, '2021-05-27 06:45:56', NULL);
INSERT INTO `t_menu` VALUES (9, '参数配置', 8, 9, '/system/param', NULL, NULL, 1, NULL, NULL, NULL, '2021-05-27 06:45:58', NULL);
INSERT INTO `t_menu` VALUES (10, '接口管理', 8, 10, '/system/interface', NULL, NULL, 1, NULL, NULL, NULL, '2021-05-27 06:46:02', NULL);
INSERT INTO `t_menu` VALUES (11, '日志管理', 8, 11, '/system/log', NULL, NULL, 1, NULL, NULL, NULL, '2021-05-27 06:46:04', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '管理员', 'ADMIN', 1, NULL, 1, '管理员', '2021-03-25 11:41:23', '2021-05-26 22:50:15');
INSERT INTO `t_role` VALUES (2, '普通用户', 'USER', 2, NULL, 1, '普通用户1', '2021-03-25 11:41:43', '2021-06-10 06:34:13');
INSERT INTO `t_role` VALUES (3, '游客', 'GUEST', 4, NULL, 0, 'TTT1213', '2021-04-25 17:05:48', '2021-05-27 06:39:06');

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
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES (36, 1, 5, '2021-05-27 23:58:40', '2021-05-27 23:58:40');
INSERT INTO `t_role_menu` VALUES (37, 1, 6, '2021-05-27 23:58:40', '2021-05-27 23:58:40');
INSERT INTO `t_role_menu` VALUES (38, 1, 7, '2021-05-27 23:58:40', '2021-05-27 23:58:40');
INSERT INTO `t_role_menu` VALUES (39, 1, 8, '2021-05-27 23:58:40', '2021-05-27 23:58:40');
INSERT INTO `t_role_menu` VALUES (40, 1, 9, '2021-05-27 23:58:40', '2021-05-27 23:58:40');
INSERT INTO `t_role_menu` VALUES (41, 1, 10, '2021-05-27 23:58:40', '2021-05-27 23:58:40');
INSERT INTO `t_role_menu` VALUES (42, 1, 11, '2021-05-27 23:58:40', '2021-05-27 23:58:40');
INSERT INTO `t_role_menu` VALUES (43, 2, 5, '2021-05-28 00:00:24', '2021-05-28 00:00:24');
INSERT INTO `t_role_menu` VALUES (44, 2, 6, '2021-05-28 00:00:24', '2021-05-28 00:00:24');
INSERT INTO `t_role_menu` VALUES (45, 2, 7, '2021-05-28 00:00:24', '2021-05-28 00:00:24');
INSERT INTO `t_role_menu` VALUES (58, 3, 2, '2021-05-28 00:05:53', '2021-05-28 00:05:53');
INSERT INTO `t_role_menu` VALUES (59, 3, 3, '2021-05-28 00:05:53', '2021-05-28 00:05:53');
INSERT INTO `t_role_menu` VALUES (60, 3, 6, '2021-05-28 00:05:53', '2021-05-28 00:05:53');
INSERT INTO `t_role_menu` VALUES (61, 3, 8, '2021-05-28 00:05:53', '2021-05-28 00:05:53');
INSERT INTO `t_role_menu` VALUES (62, 3, 9, '2021-05-28 00:05:53', '2021-05-28 00:05:53');
INSERT INTO `t_role_menu` VALUES (63, 3, 10, '2021-05-28 00:05:53', '2021-05-28 00:05:53');
INSERT INTO `t_role_menu` VALUES (64, 3, 11, '2021-05-28 00:05:53', '2021-05-28 00:05:53');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `avatar` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `is_delete` tinyint(0) NULL DEFAULT NULL COMMENT '是否已删除（0正常 1已删除）',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '帐号状态（0正常 1停用）',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注，介绍',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', '2', '4@qq.com', '32', '/src/assets/images/header.jpg', 'e10adc3949ba59abbe56e057f20f883e', 0, 1, '2021-06-20 21:26:27', '这家伙很懒，没有留下什么介绍1', '2021-03-24 16:11:45', '2021-06-10 06:33:42');
INSERT INTO `t_user` VALUES (8, 'admin1', '3', '4', '32', NULL, '96e79218965eb72c92a549dd5a330112', 0, 1, '2021-03-24 16:11:52', NULL, '2021-03-24 16:11:45', '2021-05-22 18:30:38');
INSERT INTO `t_user` VALUES (9, 'admin2', '1', '4', '32', NULL, 'e10adc3949ba59abbe56e057f20f883e', 0, 1, '2021-03-24 16:11:52', NULL, '2021-03-24 16:11:45', '2021-03-24 16:11:47');
INSERT INTO `t_user` VALUES (10, 'admin3', '4', '4', '32', NULL, 'e10adc3949ba59abbe56e057f20f883e', 0, 1, '2021-03-24 16:11:52', NULL, '2021-03-24 16:11:45', '2021-03-24 16:11:47');
INSERT INTO `t_user` VALUES (11, 'admin4', '5', '4', '32', NULL, 'e10adc3949ba59abbe56e057f20f883e', 0, 1, '2021-03-24 16:11:52', NULL, '2021-03-24 16:11:45', '2021-03-24 16:11:47');
INSERT INTO `t_user` VALUES (12, 'admin5', '6', '4', '32', NULL, 'e10adc3949ba59abbe56e057f20f883e', 0, 1, '2021-03-24 16:11:52', NULL, '2021-03-24 16:11:45', '2021-03-24 16:11:47');
INSERT INTO `t_user` VALUES (13, 'admin6', '7', '4', '32', NULL, 'e10adc3949ba59abbe56e057f20f883e', 0, 1, '2021-03-24 16:11:52', NULL, '2021-03-24 16:11:45', '2021-03-24 16:11:47');
INSERT INTO `t_user` VALUES (14, 'admin7', '8', '4', '32', NULL, 'e10adc3949ba59abbe56e057f20f883e', 0, 1, '2021-03-24 16:11:52', NULL, '2021-03-24 16:11:45', '2021-03-24 16:11:47');
INSERT INTO `t_user` VALUES (15, 'admin8', '9', '4', '32', NULL, '96e79218965eb72c92a549dd5a330112', 0, 1, '2021-03-24 16:11:52', NULL, '2021-03-24 16:11:45', '2021-05-22 18:33:19');
INSERT INTO `t_user` VALUES (20, 'abc', '12', 'dsafd@qq.com', '15928372123', NULL, 'e10adc3949ba59abbe56e057f20f883e', 0, 1, NULL, NULL, '2021-04-25 11:29:47', '2021-05-20 06:36:00');
INSERT INTO `t_user` VALUES (21, 'abc1', '11', 'dsafd@qq.com', '15928372123', NULL, 'e10adc3949ba59abbe56e057f20f883e', 0, 1, NULL, NULL, '2021-04-25 11:31:43', '2021-05-20 06:35:57');
INSERT INTO `t_user` VALUES (22, 'abc2', '13', 'dsafd@qq.com', '15928372123', NULL, 'e10adc3949ba59abbe56e057f20f883e', 0, 1, NULL, NULL, '2021-04-25 11:31:47', '2021-05-20 06:35:55');
INSERT INTO `t_user` VALUES (24, 'abc3', '14', 'dsafd@qq.com', '15928372123', NULL, 'e10adc3949ba59abbe56e057f20f883e', 0, 1, NULL, NULL, '2021-04-25 11:34:01', '2021-05-20 06:35:53');
INSERT INTO `t_user` VALUES (25, 'abc31', '15', 'dsafd@qq.com', '15928372123', NULL, '202cb962ac59075b964b07152d234b70', 0, 1, NULL, NULL, '2021-04-25 18:33:28', '2021-05-20 06:35:50');
INSERT INTO `t_user` VALUES (26, '43212321', '16', '3243@qq.com', '3543', NULL, '96e79218965eb72c92a549dd5a330112', 0, 1, NULL, NULL, '2021-05-22 11:52:28', '2021-05-22 17:08:13');
INSERT INTO `t_user` VALUES (27, '12345', '17', '232@qq.com', '123', NULL, '96e79218965eb72c92a549dd5a330112', 0, 1, NULL, NULL, '2021-05-22 12:29:42', '2021-05-22 17:08:05');
INSERT INTO `t_user` VALUES (28, 'TEST1', '18', 'TEST1@163.com', '142', NULL, '96e79218965eb72c92a549dd5a330112', 0, 1, '2021-05-22 17:07:36', NULL, '2021-05-22 17:07:15', '2021-05-22 17:08:16');

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
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (27, 8, 1, '2021-04-25 15:23:15', '2021-04-25 15:23:15');
INSERT INTO `t_user_role` VALUES (28, 8, 2, '2021-04-25 15:23:15', '2021-04-25 15:23:15');
INSERT INTO `t_user_role` VALUES (44, 29, 1, NULL, NULL);
INSERT INTO `t_user_role` VALUES (51, 1, 1, NULL, NULL);
INSERT INTO `t_user_role` VALUES (52, 1, 2, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
