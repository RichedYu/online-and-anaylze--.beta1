/*
 Navicat Premium Data Transfer

 Source Server         : MySQL5.7
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : online_exam

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 02/04/2025 21:52:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answers
-- ----------------------------
DROP TABLE IF EXISTS `answers`;
CREATE TABLE `answers`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'answerID',
  `question_id` bigint(20) NULL DEFAULT NULL,
  `answer` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of answers
-- ----------------------------
INSERT INTO `answers` VALUES (1, 2, '基本表,视图,行,列 ');
INSERT INTO `answers` VALUES (2, 5, '基本表,视图,实表,虚表');
INSERT INTO `answers` VALUES (3, 11, '应用程序,终端用户');
INSERT INTO `answers` VALUES (4, 3, '答：SQL的SELECT语句的基本句法来自于关系代数表达式πL（ςF（R1×„×Rm）），\r\n并且SQL中有并（UNION）、交（INTERSECT）和差（EXCEPT）等操作，因此 SQL\r\n具有关系代数特点。 \r\nSELECT 语句中出现的基本表名，都应该理解成基本表中的元组变量，而列名应理解\r\n成元组分量，这样SQL就具有了元组演算的特点。');
INSERT INTO `answers` VALUES (5, 6, '答：对于SELECT语句中SELECT子句，若用“SELECT  DISTINCT”形式，则查询结\r\n果中不允许有重复元组；若不写DISTINCT字样，则查询结果中允许出现重复元组。');
INSERT INTO `answers` VALUES (6, 12, '答：在基本 SQL 中，SELECT 语句中使用的基本表名都应该理解成表中的元组变量，而\r\n列名就成了元组分量。这样就使SELECT语句带有元组演算的特点。 \r\n（注：实际上，在基本SQL中，把关系变量和元组变量混为一谈了。这在面向对象数据库\r\n中得到了纠正，在引用表时，都要为表定义一个元组变量。）');
INSERT INTO `answers` VALUES (7, 4, '解：(1) ER图如图5.2所示。 \r\n公司编号 \r\n仓库编号 \r\n公司名 \r\n地址 \r\n公司 \r\n1 \r\n隶属 \r\n仓库名 \r\nN \r\n仓库 \r\n地址 \r\n1 \r\n聘用 \r\n聘期 \r\n工资 \r\nN \r\n职工 \r\n职工编号 \r\n姓名 \r\n图5.2 \r\n（2）这个ER图可转换3个关系模式： \r\n性别 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--61 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--62 \r\n  公司（公司编号，公司名，地址） \r\n  仓库（仓库编号，仓库名，地址，公司编号） \r\n  职工（职工编号，姓名，性别，仓库编号，聘期，工资）');
INSERT INTO `answers` VALUES (8, 7, '答：对于SELECT语句中SELECT子句，若用“SELECT  DISTINCT”形式，则查询结\r\n果中不允许有重复元组；若不写DISTINCT字样，则查询结果中允许出现重复元组。');
INSERT INTO `answers` VALUES (9, 13, '答：SQL的SELECT语句的基本句法来自于关系代数表达式πL（ςF（R1×„×Rm）），\r\n并且SQL中有并（UNION）、交（INTERSECT）和差（EXCEPT）等操作，因此 SQL\r\n具有关系代数特点。 \r\nSELECT 语句中出现的基本表名，都应该理解成基本表中的元组变量，而列名应理解\r\n成元组分量，这样SQL就具有了元组演算的特点。');
INSERT INTO `answers` VALUES (10, 46, '和数据资源组成的系统，即采用数据库技术的计算机系统。 ');
INSERT INTO `answers` VALUES (11, 47, '答：人工管理阶段主要有四个特点：数据不保存在计算机内；没有专用的软件对数据进行\r\n管理；只有程序的概念，没有文件的概念；数据面向程序。 \r\n文件系统阶段主要有五个特点： 数据以“文件”形式长期保存；数据的逻辑结构与物理\r\n结构有了区别；文件组织已多样化；数据面向应用；对数据的操作以记录为单位。 ');
INSERT INTO `answers` VALUES (12, 48, '答：主要有三个缺陷：数据冗余；数据不一致性；数据联系弱。 \r\n例如学校里教务处、财务处、保健处建立的文件中都有学生详细资料，譬如联系电话，\r\n家庭住址等。这就是“数据”冗余；如果某个学生搬家，就要修改三个部门文件中的数据，\r\n否则会引起同一数据在三个部门中不一致；产生上述问题的原因是这三个部门的文件中数据\r\n没有联系。');
INSERT INTO `answers` VALUES (13, 49, '答：进入数据库阶段的标志是20世纪60年代末发生的三件事件： \r\n·1968 年IBM公司研制的IMS系统是一个典型的层次DBS； \r\n·1969 年美国CODASYL组织DBTG报告，提出网状DBS的概念； \r\n·1970 年美国IBM公司的E.F.Codd发表论文，提出关系模型的思想。');
INSERT INTO `answers` VALUES (14, 50, '答：主要有五个特点： \r\n采用数据模型表示复杂的数据结构；有较高的数据独立性；为用户提供了方便的用户接\r\n口；提供了四个方面的数据控制功能；对数据的操作以数据项为单位，增加了系统的灵活性。');
INSERT INTO `answers` VALUES (15, 51, '答：DDBS主要有三个特点： \r\n·数据物理上分布在各地，但逻辑上是一个整体； \r\n·每个场地既可以执行局部应用，也可以执行全局应用； \r\n·各地的计算机由数据通信网络相连接。 \r\n面向对象数据系统主要有两个特点： \r\n·面向对象数据模型能完整地描述现实世界的数据结构，能表达数据间嵌套、递归的联\r\n系。 \r\n·具有面向对象技术的封装性和继承性的特点，提高了软件的可重用性。');
INSERT INTO `answers` VALUES (16, 52, '答：ER模型直接表示实体类型及实体间联系，与计算机系统无关，充分反映用户的需求，\r\n用户容易理解。 \r\n层次模型的数据结构为树结构，记录之间联系通过指针实现，查询较快，但DML属于过\r\n程化的，操作复杂。 \r\n网状模型的数据结构为有向图，记录之间联系通过指针实现，查询较快，并且容易实现\r\nM:N联系，但DML属于过程化的语言，编程较复杂。 \r\n关系模型的数据结构为二维表格，容易为初学者理解。记录之间联系通过关键码实现。\r\nDML属于非过程化语言，编程较简单。 \r\n面向对象模型能完整描述现实世界的数据结构，具有丰富的表达能力，能表达嵌套、递\r\n归的数据结构。但涉及的知识面较广，用户较难理解，这种模型尚未普及。 ');
INSERT INTO `answers` VALUES (17, 53, '硬件,软件,计算机应用');
INSERT INTO `answers` VALUES (18, 54, '程序设计,数据,数据');
INSERT INTO `answers` VALUES (19, 55, '设备');
INSERT INTO `answers` VALUES (20, 56, '数据的传输,格式的转换');
INSERT INTO `answers` VALUES (21, 57, '数据 ');
INSERT INTO `answers` VALUES (22, 58, '数据字典（DD）');
INSERT INTO `answers` VALUES (23, 59, '集中式,C/S式,并行式,分布式');
INSERT INTO `answers` VALUES (24, 60, '应用程序 ');
INSERT INTO `answers` VALUES (25, 82, '解：(1) ER图如图5.2所示。 \r\n公司编号 \r\n仓库编号 \r\n公司名 \r\n地址 \r\n公司 \r\n1 \r\n隶属 \r\n仓库名 \r\nN \r\n仓库 \r\n地址 \r\n1 \r\n聘用 \r\n聘期 \r\n工资 \r\nN \r\n职工 \r\n职工编号 \r\n姓名 \r\n图5.2 \r\n（2）这个ER图可转换3个关系模式： \r\n性别 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--61 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--62 \r\n  公司（公司编号，公司名，地址） \r\n  仓库（仓库编号，仓库名，地址，公司编号） \r\n  职工（职工编号，姓名，性别，仓库编号，聘期，工资）');
INSERT INTO `answers` VALUES (26, 83, '答：对于SELECT语句中SELECT子句，若用“SELECT  DISTINCT”形式，则查询结\r\n果中不允许有重复元组；若不写DISTINCT字样，则查询结果中允许出现重复元组。');
INSERT INTO `answers` VALUES (27, 84, '答：SQL的SELECT语句的基本句法来自于关系代数表达式πL（ςF（R1×„×Rm）），\r\n并且SQL中有并（UNION）、交（INTERSECT）和差（EXCEPT）等操作，因此 SQL\r\n具有关系代数特点。 \r\nSELECT 语句中出现的基本表名，都应该理解成基本表中的元组变量，而列名应理解\r\n成元组分量，这样SQL就具有了元组演算的特点。');

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '章节ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '章节名',
  `subject_id` int(11) NULL DEFAULT NULL COMMENT '课程ID',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chapter
-- ----------------------------
INSERT INTO `chapter` VALUES (1, '线性表', 1, '2024-12-27 10:06:13', NULL, 0);
INSERT INTO `chapter` VALUES (2, '栈和队列', 1, '2025-01-22 10:02:46', NULL, 0);
INSERT INTO `chapter` VALUES (3, '串', 1, '2025-01-22 10:02:48', NULL, 0);
INSERT INTO `chapter` VALUES (4, '树与二叉树', 1, '2025-01-22 10:02:51', NULL, 0);
INSERT INTO `chapter` VALUES (5, '图', 1, '2025-01-22 10:02:53', NULL, 0);
INSERT INTO `chapter` VALUES (6, '查找', 1, '2025-02-14 20:40:33', NULL, 0);
INSERT INTO `chapter` VALUES (7, '图论', 2, '2025-01-22 10:14:57', '2025-01-22 10:22:48', 1);
INSERT INTO `chapter` VALUES (8, '操作系统概览', 3, '2025-01-23 22:33:29', NULL, 0);
INSERT INTO `chapter` VALUES (9, '进程描述和控制', 3, '2025-01-23 22:35:42', NULL, 0);
INSERT INTO `chapter` VALUES (10, '线程', 3, '2025-01-23 22:35:56', NULL, 0);
INSERT INTO `chapter` VALUES (11, '互斥和同步', 3, '2025-01-23 22:36:40', NULL, 0);
INSERT INTO `chapter` VALUES (12, '内存管理', 3, '2025-01-23 22:37:24', NULL, 0);
INSERT INTO `chapter` VALUES (13, '虚拟内存', 3, '2025-01-23 22:37:39', NULL, 0);
INSERT INTO `chapter` VALUES (14, '绪论', 1, '2025-02-14 20:20:11', NULL, 0);
INSERT INTO `chapter` VALUES (15, '数组和广义表', 1, '2025-02-14 20:23:45', NULL, 0);
INSERT INTO `chapter` VALUES (16, '动态存储管理', 1, '2025-02-14 20:23:48', NULL, 0);
INSERT INTO `chapter` VALUES (17, '内部排序', 1, NULL, NULL, 0);
INSERT INTO `chapter` VALUES (18, '外部排序', 1, NULL, NULL, 0);
INSERT INTO `chapter` VALUES (19, '文件', 1, NULL, NULL, 0);
INSERT INTO `chapter` VALUES (20, '数据库概论', 4, '2025-03-12 23:15:25', NULL, 0);
INSERT INTO `chapter` VALUES (21, '关系模型与关系运算理论', 4, NULL, NULL, 0);
INSERT INTO `chapter` VALUES (22, '关系数据库语言SQL', 4, NULL, NULL, 0);
INSERT INTO `chapter` VALUES (23, '模式设计与理论', 4, NULL, NULL, 0);
INSERT INTO `chapter` VALUES (24, '数据库设计与ER模型', 4, NULL, NULL, 0);
INSERT INTO `chapter` VALUES (25, '数据库的存储结构', 4, NULL, NULL, 0);
INSERT INTO `chapter` VALUES (26, '系统实现技术', 4, NULL, NULL, 0);
INSERT INTO `chapter` VALUES (27, '对象数据库系统', 4, NULL, NULL, 0);
INSERT INTO `chapter` VALUES (28, '分布式数据库系统', 4, NULL, NULL, 0);
INSERT INTO `chapter` VALUES (29, '中间件技术', 4, NULL, NULL, 0);
INSERT INTO `chapter` VALUES (30, '数据库与WWW', 4, NULL, NULL, 0);
INSERT INTO `chapter` VALUES (31, 'XML技术', 4, NULL, NULL, 0);
INSERT INTO `chapter` VALUES (32, '图论', 2, '2025-04-01 22:32:48', NULL, 0);

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '班级ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级名',
  `teacher_id` int(11) NULL DEFAULT NULL COMMENT '教师ID',
  `grade` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年级',
  `major_id` int(11) NULL DEFAULT NULL COMMENT '专业ID',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES (1, '21软件3班', 1, '2021', 1, '2025-01-21 17:46:07', NULL, 0);
INSERT INTO `class` VALUES (2, '21软件2班', 1, '2021', 1, '2025-01-22 22:39:03', NULL, 0);
INSERT INTO `class` VALUES (3, '21计科1班', 2, '2021', 2, '2025-01-21 17:46:15', NULL, 0);
INSERT INTO `class` VALUES (6, '21计科2班', NULL, '2021', 2, '2025-01-22 21:07:35', NULL, 0);
INSERT INTO `class` VALUES (7, '21计科3班', NULL, '2021', 2, '2025-01-22 21:09:19', NULL, 0);
INSERT INTO `class` VALUES (8, '21人工智能1班', NULL, '2021', 4, '2025-01-22 21:10:58', '2025-01-22 21:10:58', 1);
INSERT INTO `class` VALUES (9, '21软件4班', NULL, '2021', 1, '2025-01-23 22:46:16', NULL, 0);
INSERT INTO `class` VALUES (10, '21软件1班', NULL, '2021', 1, '2025-04-01 22:47:13', NULL, 0);

-- ----------------------------
-- Table structure for class_paper
-- ----------------------------
DROP TABLE IF EXISTS `class_paper`;
CREATE TABLE `class_paper`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '班级_试卷ID',
  `paper_id` int(11) NULL DEFAULT NULL COMMENT '试卷ID',
  `class_id` int(11) NULL DEFAULT NULL COMMENT '班级ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class_paper
-- ----------------------------
INSERT INTO `class_paper` VALUES (1, 6, 1, '2024-12-23 15:29:29', NULL, 0);
INSERT INTO `class_paper` VALUES (2, 2, 1, '2024-12-26 20:02:43', NULL, 0);
INSERT INTO `class_paper` VALUES (3, 3, 1, '2024-12-26 20:02:46', NULL, 0);
INSERT INTO `class_paper` VALUES (4, 4, 1, '2024-12-26 20:02:41', NULL, 0);
INSERT INTO `class_paper` VALUES (5, 5, 1, '2024-12-26 20:02:53', NULL, 0);
INSERT INTO `class_paper` VALUES (6, 1, 1, '2025-01-05 02:04:29', NULL, 0);
INSERT INTO `class_paper` VALUES (7, 8, 1, NULL, NULL, 0);
INSERT INTO `class_paper` VALUES (8, 9, 1, NULL, NULL, 0);
INSERT INTO `class_paper` VALUES (9, 10, 1, NULL, NULL, 0);
INSERT INTO `class_paper` VALUES (10, 11, 1, NULL, NULL, 0);
INSERT INTO `class_paper` VALUES (11, 12, 1, NULL, NULL, 0);
INSERT INTO `class_paper` VALUES (12, 13, 1, NULL, NULL, 0);
INSERT INTO `class_paper` VALUES (23, 19, 1, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `class_paper` VALUES (24, 20, 1, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `class_paper` VALUES (25, 20, 2, NULL, NULL, 0);
INSERT INTO `class_paper` VALUES (26, 21, 1, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `class_paper` VALUES (27, 21, 9, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `class_paper` VALUES (28, 21, 2, '2025-03-29 12:17:33', NULL, 0);

-- ----------------------------
-- Table structure for institution
-- ----------------------------
DROP TABLE IF EXISTS `institution`;
CREATE TABLE `institution`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '学院ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学院名',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of institution
-- ----------------------------
INSERT INTO `institution` VALUES (1, '信息工程学院', '2024-12-14 15:00:00', '2025-01-20 16:40:59', 0);
INSERT INTO `institution` VALUES (2, '体育学院', '2025-01-20 16:40:56', '2025-03-28 15:03:25', 1);
INSERT INTO `institution` VALUES (3, '美术学院', '2025-01-20 16:41:18', '2025-03-28 15:03:27', 1);
INSERT INTO `institution` VALUES (4, '机电工程学院', '2025-01-20 16:41:52', '2025-01-20 16:41:55', 0);
INSERT INTO `institution` VALUES (5, '政法学院', '2025-01-20 16:42:06', '2025-03-28 15:03:31', 1);
INSERT INTO `institution` VALUES (6, '公共管理学院', '2025-01-20 16:43:00', '2025-03-28 15:03:33', 1);
INSERT INTO `institution` VALUES (7, '马克思主义学院', '2025-01-20 16:45:59', '2025-01-20 16:46:03', 0);
INSERT INTO `institution` VALUES (9, '人文学院', '2025-01-20 16:46:47', '2025-01-20 16:46:50', 0);
INSERT INTO `institution` VALUES (15, '商学院', '2025-01-20 23:53:59', '2025-03-28 15:03:36', 1);
INSERT INTO `institution` VALUES (16, '物理学院', '2025-01-20 23:57:35', NULL, 0);
INSERT INTO `institution` VALUES (20, '音乐学院', '2025-01-22 10:19:37', NULL, 0);
INSERT INTO `institution` VALUES (22, '生命科学学院', '2025-01-23 22:45:32', '2025-04-01 21:16:21', 1);

-- ----------------------------
-- Table structure for judge
-- ----------------------------
DROP TABLE IF EXISTS `judge`;
CREATE TABLE `judge`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '判断题ID',
  `question_id` int(11) NULL DEFAULT NULL COMMENT '试题ID',
  `correct_answer` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0：错误，1：正确',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of judge
-- ----------------------------
INSERT INTO `judge` VALUES (1, 36, '1', '2024-12-27 10:56:34', NULL, 0);
INSERT INTO `judge` VALUES (2, 37, '0', '2024-12-27 10:56:37', NULL, 0);
INSERT INTO `judge` VALUES (3, 38, '0', '2024-12-27 10:56:43', NULL, 0);
INSERT INTO `judge` VALUES (4, 39, '0', '2024-12-27 10:56:46', NULL, 0);
INSERT INTO `judge` VALUES (5, 40, '1', '2024-12-27 10:56:48', NULL, 0);
INSERT INTO `judge` VALUES (6, 41, '0', '2024-12-27 10:56:50', NULL, 0);
INSERT INTO `judge` VALUES (7, 42, '1', '2024-12-27 10:56:53', NULL, 0);
INSERT INTO `judge` VALUES (8, 43, '1', '2024-12-27 10:56:55', NULL, 0);
INSERT INTO `judge` VALUES (9, 44, '1', '2024-12-27 10:56:57', NULL, 0);
INSERT INTO `judge` VALUES (10, 45, '0', '2024-12-27 10:57:00', NULL, 0);
INSERT INTO `judge` VALUES (11, 76, '0', NULL, NULL, 0);
INSERT INTO `judge` VALUES (12, 77, '1', NULL, NULL, 0);
INSERT INTO `judge` VALUES (13, 78, '1', NULL, NULL, 0);
INSERT INTO `judge` VALUES (14, 79, '0', NULL, NULL, 0);
INSERT INTO `judge` VALUES (15, 80, '1', NULL, NULL, 0);

-- ----------------------------
-- Table structure for knowledge_points
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_points`;
CREATE TABLE `knowledge_points`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `chapter_id` bigint(20) NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL,
  `is_deleted` tinyint(4) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 160 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of knowledge_points
-- ----------------------------
INSERT INTO `knowledge_points` VALUES (1, '什么是数据结构', 14, '2025-02-14 20:24:15', NULL, 0);
INSERT INTO `knowledge_points` VALUES (2, '基本概念和术语', 14, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (3, '抽象数据类型的表现与实现', 14, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (4, '算法和算法分析', 14, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (5, '线性表的类型定义', 1, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (6, '线性表的顺序表示和实现', 1, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (7, '线性表的链式表示和实现', 1, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (8, '一元多项式的表示及相加', 1, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (9, '栈', 2, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (10, '栈的应有和举例', 2, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (11, '栈与递归的实现\r\n', 2, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (12, '队列', 2, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (13, '离散事件模拟', 2, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (14, '串类型的定义', 3, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (15, '串的表示和实现', 3, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (16, '串的模式匹配算法', 3, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (17, '串操作应用举例', 3, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (18, '数组的定义', 15, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (19, '数组的顺序表现和实现', 15, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (20, '矩阵的压缩存储', 15, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (21, '广义表的定义', 15, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (22, '广义表的储存结构', 15, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (23, 'm元多项式的表示', 15, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (24, '广义表的递归算法第6章 树和二叉树', 15, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (25, '树的定义和基本术语', 4, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (26, '二叉树', 4, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (27, '二叉树的定义', 4, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (28, '二叉树的性质', 4, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (29, '二叉树的存储结构', 4, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (30, '遍历二叉树和线索二叉树', 4, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (31, '遍历二叉树', 4, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (32, '线索二叉树', 4, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (33, '树和森林', 4, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (34, '树的存储结构', 4, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (35, '森林与二叉树的转换', 4, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (36, '树和森林的遍历', 4, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (37, '树与等价问题', 4, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (38, '赫夫曼树及其应用', 4, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (39, '最优二叉树(赫夫曼树)', 4, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (40, '赫夫曼编码', 4, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (41, '回溯法与树的遍历', 4, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (42, '树的计数', 4, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (43, '图的定义和术语', 5, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (44, '图的存储结构', 5, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (45, '数组表示法', 5, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (46, '邻接表', 5, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (47, '十字链表', 5, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (48, '邻接多重表', 5, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (49, '图的遍历', 5, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (50, '深度优先搜索', 5, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (51, '广度优先搜索', 5, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (52, '图的连通性问题', 5, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (53, '无向图的连通分量和生成树', 5, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (54, '有向图的强连通分量', 5, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (55, '最小生成树', 5, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (56, '关节点和重连通分量', 5, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (57, '有向无环图及其应用', 5, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (58, '拓扑排序', 5, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (59, '关键路径', 5, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (60, '最短路径', 5, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (61, '从某个源点到其余各顶点的最短路径', 5, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (62, '每一对顶点之间的最短路径', 5, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (63, '可利用空间表及分配方法', 16, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (64, '边界标识法', 16, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (65, '可利用空间表的结构', 16, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (66, '分配算法', 16, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (67, '回收算法', 16, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (68, '伙伴系统', 16, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (69, '可利用空间表的结构', 16, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (70, '无用单元收集', 16, '2025-02-14 20:39:14', NULL, 0);
INSERT INTO `knowledge_points` VALUES (71, '存储紧缩', 16, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (72, '静态查找表', 6, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (73, '顺序表的查找', 6, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (74, '有序表的查找', 6, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (75, '静态树表的查找', 6, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (76, '索引顺序表的查找', 6, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (77, '动态查找表', 6, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (78, '二叉排序树和平衡二叉树', 6, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (79, 'B树和B+树', 6, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (80, '键树', 6, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (81, '哈希表', 6, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (82, '什么是哈希表', 6, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (83, '哈希函数的构造方法', 6, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (84, '处理冲突的方法', 6, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (85, '哈希表的查找及其分析', 6, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (86, '插入排序', 17, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (87, '直接插入排序', 17, '2025-02-14 20:44:23', NULL, 0);
INSERT INTO `knowledge_points` VALUES (88, '其他插入排序', 17, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (89, '希尔排序', 17, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (90, '快速排序', 17, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (91, '选择排序', 17, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (92, '简单选择排序', 17, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (93, '树形选择排序', 17, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (94, '堆排序', 17, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (95, '归并排序', 17, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (96, '基数排序', 17, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (97, '多关键字的排序', 17, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (98, '链式基数排序', 17, '2025-02-14 20:47:04', NULL, 0);
INSERT INTO `knowledge_points` VALUES (99, '外存信息的存取', 18, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (100, '外部排序的方法', 18, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (101, '多路平衡归并的实现', 18, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (102, '置换一选择排序', 18, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (103, '最佳归并树', 18, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (104, '有关文件的基本概念', 19, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (105, '顺序文件', 19, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (106, '索引文件', 20, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (107, 'ISAM文件和VSAM文件', 20, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (108, 'ISAM文件', 20, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (109, 'VSAM文件', 20, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (110, '直接存取文件(散列文件)', 20, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (111, '多关键字文件', 20, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (112, '多重表文件', 20, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (113, '倒排文件', 20, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (114, '关系模型的基本概念', 21, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (115, '关系运算', 21, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (116, '关系完整性约束', 21, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (117, '关系代数', 21, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (118, '关系演算', 21, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (119, '关系模式的规范化', 21, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (120, 'SQL数据定义语言（DDL）', 22, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (121, 'SQL数据查询语言（DQL）', 22, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (122, 'SQL数据操作语言（DML）', 22, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (123, 'SQL数据控制语言（DCL）', 22, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (124, '视图与索引', 22, '2025-03-13 00:05:11', NULL, 0);
INSERT INTO `knowledge_points` VALUES (125, '存储过程与触发器', 22, '2025-03-13 00:05:14', NULL, 0);
INSERT INTO `knowledge_points` VALUES (126, '数据库设计原则', 23, '2025-03-13 00:06:29', NULL, 0);
INSERT INTO `knowledge_points` VALUES (127, '数据库设计步骤', 23, '2025-03-13 00:06:30', NULL, 0);
INSERT INTO `knowledge_points` VALUES (128, 'ER模型', 23, '2025-03-13 00:06:31', NULL, 0);
INSERT INTO `knowledge_points` VALUES (129, '数据字典', 23, '2025-03-13 00:06:32', NULL, 0);
INSERT INTO `knowledge_points` VALUES (130, '规范化与反规范化', 23, '2025-03-13 00:06:32', NULL, 0);
INSERT INTO `knowledge_points` VALUES (131, '模式分解', 23, '2025-03-13 00:06:34', NULL, 0);
INSERT INTO `knowledge_points` VALUES (132, 'ER模型到关系模型的转换', 24, '2025-03-13 00:06:36', NULL, 0);
INSERT INTO `knowledge_points` VALUES (133, '数据约束的实现', 24, '2025-03-13 00:07:12', NULL, 0);
INSERT INTO `knowledge_points` VALUES (134, '数据库安全性设计', 24, '2025-03-13 00:07:13', NULL, 0);
INSERT INTO `knowledge_points` VALUES (135, '数据库备份与恢复', 24, '2025-03-13 00:07:14', NULL, 0);
INSERT INTO `knowledge_points` VALUES (136, '数据库性能调优', 24, '2025-03-13 00:07:15', NULL, 0);
INSERT INTO `knowledge_points` VALUES (137, '存储结构的基本概念', 25, '2025-03-13 00:07:57', NULL, 0);
INSERT INTO `knowledge_points` VALUES (138, '索引结构', 25, '2025-03-13 00:07:58', NULL, 0);
INSERT INTO `knowledge_points` VALUES (139, '存储路径', 25, '2025-03-13 00:07:58', NULL, 0);
INSERT INTO `knowledge_points` VALUES (140, '存储分配策略', 25, '2025-03-13 00:07:59', NULL, 0);
INSERT INTO `knowledge_points` VALUES (141, '存储管理', 25, '2025-03-13 00:08:00', NULL, 0);
INSERT INTO `knowledge_points` VALUES (142, '数据库管理系统架构', 26, '2025-03-13 00:08:59', NULL, 0);
INSERT INTO `knowledge_points` VALUES (143, '数据存储与检索机制', 26, '2025-03-13 00:09:00', NULL, 0);
INSERT INTO `knowledge_points` VALUES (144, '并发控制与事务管理', 26, '2025-03-13 00:09:01', NULL, 0);
INSERT INTO `knowledge_points` VALUES (145, '系统性能监控与优化', 26, '2025-03-13 00:09:02', NULL, 0);
INSERT INTO `knowledge_points` VALUES (146, '对象数据库的基本概念', 27, '2025-03-13 00:09:04', NULL, 0);
INSERT INTO `knowledge_points` VALUES (147, '对象存储与检索方法', 27, '2025-03-13 00:09:32', NULL, 0);
INSERT INTO `knowledge_points` VALUES (148, '对象持久化机制', 27, '2025-03-13 00:09:34', NULL, 0);
INSERT INTO `knowledge_points` VALUES (149, '对象数据库管理系统设计', 27, '2025-03-13 00:09:35', NULL, 0);
INSERT INTO `knowledge_points` VALUES (150, '分布式数据库架构与组成', 28, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (151, '数据分布与存储策略', 28, '2025-03-13 00:10:31', NULL, 0);
INSERT INTO `knowledge_points` VALUES (152, '分布式查询处理与优化', 28, '2025-03-13 00:10:33', NULL, 0);
INSERT INTO `knowledge_points` VALUES (153, '分布式事务管理与并发控制', 28, '2025-03-13 00:10:35', NULL, 0);
INSERT INTO `knowledge_points` VALUES (154, 'DB、DBMS和DBS的定义 ', 20, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (155, '数据管理技术的发展阶段 ', 20, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (156, '数据描述 ', 20, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (157, '数据模型 ', 20, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (158, 'DB的体系结构 ', 20, NULL, NULL, 0);
INSERT INTO `knowledge_points` VALUES (159, 'DBMS ', 20, NULL, NULL, 0);

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '专业ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业名称',
  `institution_id` int(11) NULL DEFAULT NULL COMMENT '学院ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES (1, '软件工程', 1, '2024-12-23 15:12:46', '2025-01-20 16:29:07', 0);
INSERT INTO `major` VALUES (2, '计算机科学与技术', 1, '2025-01-20 15:43:53', '2025-01-20 16:29:13', 0);
INSERT INTO `major` VALUES (4, '人工智能', 1, '2025-01-21 21:24:38', NULL, 0);
INSERT INTO `major` VALUES (5, '交响乐', 20, '2025-01-22 10:20:01', '2025-01-22 21:10:01', 1);
INSERT INTO `major` VALUES (6, '机械设计制造及其自动化', 4, '2025-01-23 22:39:49', '2025-04-01 21:35:20', 1);
INSERT INTO `major` VALUES (7, '电气工程技术', 4, '2025-01-23 22:41:01', NULL, 0);

-- ----------------------------
-- Table structure for multi_choice
-- ----------------------------
DROP TABLE IF EXISTS `multi_choice`;
CREATE TABLE `multi_choice`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '选择题ID',
  `question_id` int(11) NULL DEFAULT NULL COMMENT '试题ID',
  `optionA` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项A',
  `optionB` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项B',
  `optionC` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项C',
  `optionD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项D',
  `correct_answer` enum('A','B','C','D','AB','AC','AD','BC','BD','CD','ABC','ABD','ACD','BCD','ABCD') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '答案',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of multi_choice
-- ----------------------------
INSERT INTO `multi_choice` VALUES (1, 1, '将IP地址翻译为计算机名，为客户机分配IP地址', '将IP地址翻译为计算机名、解析计算机的MAC地址', '将计算机名翻译为IP地址、为客户机分配IP地址', '将计算机名翻译为IP地址、解析计算机的MAC地址', 'C', '2024-12-27 10:04:14', NULL, 0);
INSERT INTO `multi_choice` VALUES (2, 2, 'ARP', 'DHCP', 'UDP', 'TCP', 'D', '2024-12-27 10:15:18', NULL, 0);
INSERT INTO `multi_choice` VALUES (3, 3, 'ipconfig/displaydns', 'nslookup', 'ipconfig/release', 'ipconfig/flushdns', 'A', '2024-12-27 10:16:34', NULL, 0);
INSERT INTO `multi_choice` VALUES (4, 4, 'DhcpDisover', 'DhcpOffer', 'DhcpAck', 'DhcpNack', 'A', '2024-12-27 10:37:55', NULL, 0);
INSERT INTO `multi_choice` VALUES (5, 5, '196.254.109.100', '169.254.12.42', '69.254.48.45', '96.254.54.15', 'B', '2024-12-27 10:37:58', NULL, 0);
INSERT INTO `multi_choice` VALUES (6, 6, 'IP，掩码，网关，DNS', 'IP，掩码，域名，SMTP', '网关，掩码，浏览器，FTP', 'IP，网关，DNS，服务器', 'A', '2024-12-27 10:38:01', NULL, 0);
INSERT INTO `multi_choice` VALUES (7, 7, '本地缓存记录→区域记录→转发域名服务器→根域名服务器', '区域记录→本地缓存记录→转发域名服务器→根域名服务器', '本地缓存记录→区域记录→根域名服务器→转发域名服务器', '区域记录→本地缓存记录→根域名服务器→转发域名服务器', 'A', '2024-12-27 10:38:03', NULL, 0);
INSERT INTO `multi_choice` VALUES (8, 8, '1024', '64', '256', '128', 'A', '2024-12-27 10:38:06', NULL, 0);
INSERT INTO `multi_choice` VALUES (9, 9, 'UDP协议的低开销特性', 'UDP协议的高开销特性', 'TCP协议的低开销特性', 'TCP协议的高开销特性', 'A', '2024-12-27 10:38:11', NULL, 0);
INSERT INTO `multi_choice` VALUES (10, 10, '端口号', 'IP地址', '协议类型', 'MAC地址', 'A', '2024-12-27 10:38:13', NULL, 0);
INSERT INTO `multi_choice` VALUES (11, 11, '使用面向连接的会话', '使用“尽力而为”的传输', '使用滑动窗口来维持可靠性', '使用确认重传机制来确保传输的数据不丢失', 'D', '2024-12-27 10:38:15', NULL, 0);
INSERT INTO `multi_choice` VALUES (12, 12, '50KB', '80KB', '130KB', '30KB', 'A', '2024-12-27 10:38:19', NULL, 0);
INSERT INTO `multi_choice` VALUES (13, 13, '（SYN=0,ACK=0,seq=2001,ack=2001）', '（SYN=1,ACK=1,seq=2000,ack=2000）', '（SYN=1,ACK=1,seq=2001,ack=2001）', '（SYN=0,ACK=1,seq=2000,ack=2000）', 'C', '2024-12-27 10:38:23', NULL, 0);
INSERT INTO `multi_choice` VALUES (14, 14, ' 70', '30', '100', '170', 'B', '2024-12-27 10:38:26', NULL, 0);
INSERT INTO `multi_choice` VALUES (15, 15, '采样、量化、编码', '量化、编码、采样', '编码、量化、采样', '采样、编码、量化', 'A', '2024-12-27 10:38:30', NULL, 0);
INSERT INTO `multi_choice` VALUES (16, 16, '600Baud', '1200Baud', '4800Baud', '9600Baud', 'B', '2024-12-27 10:38:33', NULL, 0);
INSERT INTO `multi_choice` VALUES (17, 17, '数据传输速率表示每秒钟传输构成数据代码的二进制比特数', '对于二进制数据，数据传输速率为S=1/T (bps)', '常用的数据传输速率单位有: 1Mbps=1.024×106bps', '数据传输速率是描述数据传输系统性能的重要技术指标之一', 'C', '2024-12-27 10:38:36', NULL, 0);
INSERT INTO `multi_choice` VALUES (18, 18, '时分多路复用将线路使用的时间分成多个时间片', '时分多路复用分为同步时分多路复用与统计时分多路复用', '时分多路复用使用“帧”与数据链路层“帧”的概念、作用是不同的', '统计时分多路复用将时间片预先分配给各个信道', 'D', '2024-12-27 10:38:38', NULL, 0);
INSERT INTO `multi_choice` VALUES (19, 19, '双绞线', '同轴电缆', '光纤', '无线电', 'A', '2024-12-27 10:38:40', NULL, 0);
INSERT INTO `multi_choice` VALUES (20, 20, '直通交换', '无碎片交换', '无差错交换', '存储转发交换', 'D', '2024-12-27 10:38:43', NULL, 0);
INSERT INTO `multi_choice` VALUES (21, 21, '一个新的交换机没有配置VLAN', '通过配置VLAN减少了冲突域的数量', '一个VLAN不能跨越多个交换机', '各个VLAN属于不同的广播域', 'D', '2024-12-27 10:38:46', NULL, 0);
INSERT INTO `multi_choice` VALUES (22, 22, '.用于不同子网中的主机进行通信', '作为第二层设备的唯一标识', '用于区别第二层第三层的协议数据单元', '保存主机可检测未知的远程设备', 'B', '2024-12-27 10:38:48', NULL, 0);
INSERT INTO `multi_choice` VALUES (23, 23, '冲突次数越多，后退的时间越短', '平均后退次数的多少与负载大小有关', '后退时延的平均值与负载大小有关', '重发次数达到一定极限后放弃发送', 'A', '2024-12-27 10:38:51', NULL, 0);
INSERT INTO `multi_choice` VALUES (24, 24, '交换机从路由表中提取设备的MAC地址', '交换机检查端口流入分组的源地址', '交换机之间互相交换地址表', '网络管理员手工输入设备的MAC地址', 'B', '2024-12-27 10:38:53', NULL, 0);
INSERT INTO `multi_choice` VALUES (25, 25, '1101011001', '101011011', '11011011', '1011001', 'B', '2024-12-27 10:38:55', NULL, 0);
INSERT INTO `multi_choice` VALUES (26, 26, 'HTTP', 'FTP', 'SMTP', 'UDP', 'ABCD', '2024-12-27 10:38:57', NULL, 0);
INSERT INTO `multi_choice` VALUES (27, 27, '物理层', '数据链路层', '网络层', '传输层', 'ABD', '2024-12-27 10:39:00', NULL, 0);
INSERT INTO `multi_choice` VALUES (28, 28, '集线器', '交换机', '路由器', '调制解调器', 'ABD', '2024-12-27 10:39:03', NULL, 0);
INSERT INTO `multi_choice` VALUES (29, 29, '网络拓扑结构', ' 数据包大小', '网络延迟', '传输介质', 'ABCD', '2024-12-27 10:39:05', NULL, 0);
INSERT INTO `multi_choice` VALUES (30, 30, '加密技术', '防火墙', '数据备份', '访问控制', 'ABCD', '2024-12-27 10:39:07', NULL, 0);
INSERT INTO `multi_choice` VALUES (31, 31, ' ping', 'traceroute', ' ifconfig', 'arp', 'ABC', '2024-12-27 10:39:10', NULL, 0);
INSERT INTO `multi_choice` VALUES (32, 32, 'IPv6地址长度为128位', 'IPv6地址使用冒分十六进制表示法', 'IPv6地址中允许包含全零的压缩表示', ' IPv6地址中必须包含网络前缀', 'ABC', '2024-12-27 10:39:13', NULL, 0);
INSERT INTO `multi_choice` VALUES (33, 33, '链路聚合', '虚拟局域网（VLAN）', '端口镜像', '负载均衡', 'ABCD', '2024-12-27 10:39:15', NULL, 0);
INSERT INTO `multi_choice` VALUES (34, 34, 'TCP/IP', 'UDP', 'HTTP', 'Sockets', 'ABD', '2024-12-27 10:39:17', NULL, 0);
INSERT INTO `multi_choice` VALUES (35, 35, '信号干扰', '传输距离', '网络拓扑结构', '障碍物', 'ABCD', '2024-12-27 10:39:20', NULL, 0);
INSERT INTO `multi_choice` VALUES (36, 61, '并发运行 ', '相互调用', 'OS调用DBMS ', 'DBMS调用OS ', 'D', '2025-03-12 22:37:21', NULL, 0);
INSERT INTO `multi_choice` VALUES (37, 62, '磁盘 ', '程序', '数据', '内存', 'C', '2025-03-12 22:38:25', NULL, 0);
INSERT INTO `multi_choice` VALUES (38, 63, '存储器', '数据', '程序', '外存 ', 'B', '2025-03-12 22:41:19', NULL, 0);
INSERT INTO `multi_choice` VALUES (39, 64, '子模式', '外模式', '概念模式', '内模式', 'D', '2025-03-12 22:48:13', NULL, 0);
INSERT INTO `multi_choice` VALUES (40, 65, '行数 ', '元组个数', '关系个数', '列数 ', 'D', '2025-03-12 22:50:27', NULL, 0);
INSERT INTO `multi_choice` VALUES (41, 66, '第三代语言', '高级程序设计语言', '过程性语言 ', '非过程性语言 ', 'D', '2025-03-12 22:51:23', NULL, 0);
INSERT INTO `multi_choice` VALUES (42, 67, '10', '30', '1000', '不确定（与计算结果有关）', 'D', '2025-03-12 22:52:51', NULL, 0);
INSERT INTO `multi_choice` VALUES (43, 68, '（a1>b1）OR（（a1=b1）AND（a2>=b2）） ', '（a1>=b1）OR（（a1=b1）AND（a2>=b2））', '（a1>b1）OR（（a1=b1）AND（a2>b2）） ', '（a1>=b1）OR（（a1=b1）AND（a2>b2）', 'C', '2025-03-12 23:00:49', NULL, 0);
INSERT INTO `multi_choice` VALUES (44, 69, 'F=G ', 'F+=G', 'F=G+', 'F+=G+ ', 'D', '2025-03-12 23:03:32', NULL, 0);
INSERT INTO `multi_choice` VALUES (45, 70, '面向对象的方法 ', '回溯的方法 ', '自底向上的方法', '自顶向下的方法 ', 'C', '2025-03-12 23:05:07', NULL, 0);
INSERT INTO `multi_choice` VALUES (46, 71, 'SELECT sname FROM student,sc WHERE student.sno=sc.sno AND cno≠\'C2\'', 'SELECT sname FROM student WHERE sno NOT IN (SELECT sno FROM sc WHERE cno=\'C2\')', 'SELECT sname FROM student WHERE NOT EXISTS (SELECT * FROM sc WHERE sc.sno=student.sno AND cno=\'C2\')', 'SELECT sname FROM student EXCEPT SELECT sname FROM student,sc WHERE sc.sno=student.sno AND cno=\'C2\'', 'BCD', '2025-03-12 23:25:05', NULL, 0);
INSERT INTO `multi_choice` VALUES (47, 72, '需求分析', '概念结构设计', '逻辑结构设计', '物理结构设计', 'ABCD', '2025-03-12 23:26:59', NULL, 0);
INSERT INTO `multi_choice` VALUES (48, 73, '实体集是具有相同属性的实体的集合', '属性是描述实体集或关系集的特征或性质', '关系是描述两个或多个实体集之间联系的方式', '码是唯一标识实体集中每个实体的属性或属性组', 'ABCD', '2025-03-12 23:28:10', NULL, 0);
INSERT INTO `multi_choice` VALUES (49, 74, '数据库的存储结构主要包括层次结构、网络结构和关系结构', '数据库的存储结构只包括逻辑结构和物理结构两个方面', '网络结构允许每个数据元素有多个父元素和多个子元素，形成网状关系', '关系结构将数据存储在二维表格中，每个数据元素都有唯一的行列坐标', 'ACD', '2025-03-12 23:29:52', NULL, 0);
INSERT INTO `multi_choice` VALUES (50, 75, '关系模型用二维表格表示数据，每个表格称为一个关系', '关系中的每一行称为一个元组，每一列称为一个属性', '关系运算主要包括选择、投影、连接、除等基本操作', '连接运算只能用于两个关系之间的合并，且合并时必须满足特定的连接条件', 'ABC', '2025-03-12 23:32:34', NULL, 0);

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '试卷ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '试卷名',
  `subject_id` int(11) NULL DEFAULT NULL COMMENT '课程ID',
  `exam_time` timestamp NULL DEFAULT NULL COMMENT '考试时间',
  `exam_duration` int(11) NULL DEFAULT NULL COMMENT '考试时长',
  `total_score` int(11) NULL DEFAULT NULL COMMENT '总分值',
  `single_choice_num` int(11) NULL DEFAULT NULL COMMENT '单选题数目',
  `more_choice_num` int(11) NULL DEFAULT NULL COMMENT '多选题数目',
  `judge_num` int(11) NULL DEFAULT NULL COMMENT '判断题数目',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考生须知',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paper
-- ----------------------------
INSERT INTO `paper` VALUES (1, '2025上学期水平测试', 1, '2025-03-11 20:28:54', 120, 100, 25, 10, 10, '2025-03-11 21:02:25', '2025-03-11 21:02:25', 0, '考试禁止作弊');
INSERT INTO `paper` VALUES (3, '2025上学期期末考试', 3, '2025-01-24 07:29:08', 120, 100, 25, 10, 10, '2025-01-24 08:18:21', '2025-01-24 08:18:21', 0, '禁止喧哗');
INSERT INTO `paper` VALUES (6, '2025上学期期末考试', 11, '2025-03-05 17:29:22', 120, 100, 25, 10, 10, '2025-03-05 17:30:24', '2025-03-05 17:30:24', 0, '考试禁止作弊，请文明考试');
INSERT INTO `paper` VALUES (10, '25年3月水平测试', 1, '2025-03-11 20:10:55', 100, 100, 25, 10, 10, '2025-03-11 21:02:57', '2025-03-11 21:02:57', 0, NULL);
INSERT INTO `paper` VALUES (11, '25年3月水平测试', 4, '2025-03-29 12:00:05', 240, 100, NULL, NULL, NULL, '2025-03-29 12:15:21', '2025-03-29 12:15:21', 0, NULL);
INSERT INTO `paper` VALUES (12, '25年1月水平测试', 4, '2025-01-01 07:34:41', 120, 100, NULL, NULL, NULL, '2025-03-13 07:54:03', '2025-03-13 07:54:03', 0, NULL);
INSERT INTO `paper` VALUES (13, '25年2月水平测试', 4, '2025-02-01 07:38:58', 120, 100, NULL, NULL, NULL, '2025-03-13 07:54:07', '2025-03-13 07:54:07', 0, NULL);
INSERT INTO `paper` VALUES (19, '数据库系统教程测试试卷', 4, '2025-03-16 23:00:00', 160, 100, NULL, NULL, NULL, '2025-03-16 23:49:43', NULL, 0, NULL);
INSERT INTO `paper` VALUES (20, '数据库系统教程测试卷2', 4, '2025-03-17 08:00:00', 260, 100, NULL, NULL, NULL, '2025-03-16 23:58:08', '2025-03-16 23:58:08', 0, NULL);
INSERT INTO `paper` VALUES (21, '25年5月水平测试', 4, '2025-03-29 12:16:59', 120, 100, NULL, NULL, NULL, '2025-03-29 12:17:33', NULL, 0, NULL);

-- ----------------------------
-- Table structure for paper_question
-- ----------------------------
DROP TABLE IF EXISTS `paper_question`;
CREATE TABLE `paper_question`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '试卷_试题ID',
  `paper_id` int(11) NULL DEFAULT NULL COMMENT '试卷ID',
  `question_id` int(11) NULL DEFAULT NULL COMMENT '试题ID',
  `sequence` int(11) NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 460 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paper_question
-- ----------------------------
INSERT INTO `paper_question` VALUES (1, 6, 1, 1, '2024-12-27 10:11:56', NULL, 0);
INSERT INTO `paper_question` VALUES (2, 6, 2, 2, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (3, 6, 3, 3, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (4, 6, 4, 4, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (5, 6, 5, 5, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (6, 6, 6, 6, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (7, 6, 7, 7, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (8, 6, 8, 8, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (9, 6, 9, 9, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (10, 6, 10, 10, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (11, 6, 11, 11, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (12, 6, 12, 12, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (13, 6, 13, 13, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (14, 6, 14, 14, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (15, 6, 15, 15, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (16, 6, 16, 16, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (17, 6, 17, 17, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (18, 6, 18, 18, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (19, 6, 19, 19, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (20, 6, 20, 20, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (21, 6, 21, 21, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (22, 6, 22, 22, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (23, 6, 23, 23, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (24, 6, 24, 24, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (25, 6, 25, 25, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (26, 6, 26, 26, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (27, 6, 27, 27, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (28, 6, 28, 28, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (29, 6, 29, 29, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (30, 6, 30, 30, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (31, 6, 31, 31, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (32, 6, 32, 32, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (33, 6, 33, 33, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (34, 6, 34, 34, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (35, 6, 35, 35, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (36, 6, 36, 36, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (37, 6, 37, 37, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (38, 6, 38, 38, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (39, 6, 39, 39, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (40, 6, 40, 40, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (41, 6, 41, 41, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (42, 6, 42, 42, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (43, 6, 43, 43, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (44, 6, 44, 44, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (45, 6, 45, 45, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (46, 1, 1, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (47, 1, 2, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (48, 1, 3, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (49, 1, 4, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (50, 1, 5, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (51, 1, 6, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (52, 1, 7, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (53, 1, 8, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (54, 1, 9, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (55, 1, 10, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (56, 1, 11, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (57, 1, 12, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (58, 1, 13, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (59, 1, 14, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (60, 1, 15, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (61, 1, 16, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (62, 1, 17, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (63, 1, 18, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (64, 1, 19, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (65, 1, 20, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (66, 1, 21, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (67, 1, 22, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (68, 1, 23, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (69, 1, 24, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (70, 1, 25, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (71, 1, 26, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (72, 1, 27, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (73, 1, 28, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (74, 1, 29, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (75, 1, 30, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (76, 1, 31, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (77, 1, 32, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (78, 1, 33, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (79, 1, 34, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (80, 1, 35, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (81, 1, 36, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (82, 1, 37, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (83, 1, 38, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (84, 1, 39, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (85, 1, 40, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (86, 1, 41, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (87, 1, 42, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (88, 1, 43, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (89, 1, 44, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (90, 1, 45, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (92, 3, 1, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (93, 3, 2, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (94, 3, 3, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (95, 3, 4, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (96, 3, 5, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (97, 3, 6, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (98, 3, 7, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (99, 3, 8, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (100, 3, 9, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (101, 3, 10, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (102, 3, 11, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (103, 3, 12, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (104, 3, 13, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (105, 3, 14, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (106, 3, 15, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (107, 3, 16, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (108, 3, 17, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (109, 3, 18, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (110, 3, 19, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (111, 3, 20, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (112, 3, 21, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (113, 3, 22, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (114, 3, 23, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (115, 3, 24, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (116, 3, 25, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (117, 3, 26, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (118, 3, 27, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (119, 3, 28, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (120, 3, 29, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (121, 3, 30, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (122, 3, 31, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (123, 3, 32, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (124, 3, 33, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (125, 3, 34, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (126, 3, 35, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (127, 3, 36, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (128, 3, 37, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (129, 3, 38, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (130, 3, 39, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (131, 3, 40, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (132, 3, 41, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (133, 3, 42, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (134, 3, 43, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (135, 3, 44, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (136, 3, 45, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (137, 10, 1, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (138, 10, 2, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (139, 10, 3, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (140, 10, 4, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (141, 10, 5, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (142, 10, 6, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (143, 10, 7, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (144, 10, 8, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (145, 10, 9, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (146, 10, 10, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (147, 10, 11, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (148, 10, 12, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (149, 10, 13, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (150, 10, 14, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (151, 10, 15, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (152, 10, 16, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (153, 10, 17, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (154, 10, 18, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (155, 10, 19, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (156, 10, 20, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (157, 10, 21, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (158, 10, 22, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (159, 10, 23, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (160, 10, 24, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (161, 10, 25, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (162, 10, 26, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (163, 10, 27, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (164, 10, 28, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (165, 10, 29, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (166, 10, 30, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (167, 10, 31, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (168, 10, 32, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (169, 10, 33, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (170, 10, 34, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (171, 10, 35, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (172, 10, 36, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (173, 10, 37, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (174, 10, 38, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (175, 10, 39, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (176, 10, 40, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (177, 10, 41, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (178, 10, 42, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (179, 10, 43, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (180, 10, 44, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (181, 10, 45, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (182, 11, 61, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (183, 11, 62, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (184, 11, 63, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (185, 11, 64, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (186, 11, 65, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (187, 11, 66, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (188, 11, 67, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (189, 11, 68, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (190, 11, 69, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (191, 11, 70, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (192, 11, 71, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (193, 11, 72, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (194, 11, 73, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (195, 11, 74, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (196, 11, 75, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (197, 11, 76, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (198, 11, 77, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (199, 11, 78, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (200, 11, 79, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (201, 11, 80, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (202, 11, 53, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (203, 11, 54, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (204, 11, 55, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (205, 11, 56, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (206, 11, 57, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (207, 11, 48, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (208, 11, 49, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (209, 11, 50, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (210, 11, 82, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (211, 11, 83, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (212, 12, 61, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (213, 12, 62, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (214, 12, 63, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (215, 12, 64, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (216, 12, 65, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (217, 12, 66, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (218, 12, 67, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (219, 12, 68, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (220, 12, 69, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (221, 12, 70, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (222, 12, 71, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (223, 12, 72, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (224, 12, 73, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (225, 12, 74, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (226, 12, 75, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (227, 12, 76, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (228, 12, 77, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (229, 12, 78, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (230, 12, 79, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (231, 12, 80, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (232, 12, 53, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (233, 12, 54, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (234, 12, 55, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (235, 12, 56, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (236, 12, 57, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (237, 12, 48, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (238, 12, 49, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (239, 12, 50, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (240, 12, 82, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (241, 12, 83, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (242, 13, 61, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (243, 13, 62, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (244, 13, 63, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (245, 13, 64, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (246, 13, 65, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (247, 13, 66, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (248, 13, 67, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (249, 13, 68, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (250, 13, 69, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (251, 13, 70, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (252, 13, 71, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (253, 13, 72, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (254, 13, 73, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (255, 13, 74, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (256, 13, 75, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (257, 13, 76, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (258, 13, 77, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (259, 13, 78, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (260, 13, 79, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (261, 13, 80, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (262, 13, 53, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (263, 13, 54, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (264, 13, 55, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (265, 13, 56, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (266, 13, 57, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (267, 13, 48, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (268, 13, 49, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (269, 13, 50, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (270, 13, 82, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (271, 13, 83, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (272, 13, 52, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (273, 12, 52, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (274, 11, 52, NULL, NULL, NULL, 0);
INSERT INTO `paper_question` VALUES (365, 19, 67, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (366, 19, 61, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (367, 19, 62, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (368, 19, 68, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (369, 19, 66, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (370, 19, 70, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (371, 19, 65, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (372, 19, 64, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (373, 19, 69, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (374, 19, 63, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (375, 19, 71, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (376, 19, 75, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (377, 19, 72, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (378, 19, 73, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (379, 19, 74, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (380, 19, 77, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (381, 19, 80, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (382, 19, 79, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (383, 19, 78, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (384, 19, 76, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (385, 19, 60, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (386, 19, 55, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (387, 19, 59, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (388, 19, 57, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (389, 19, 58, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (390, 19, 56, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (391, 19, 50, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (392, 19, 51, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (393, 19, 48, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (394, 19, 49, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (395, 19, 7, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (396, 19, 13, NULL, '2025-03-16 23:49:43', NULL, 0);
INSERT INTO `paper_question` VALUES (397, 20, 70, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (398, 20, 69, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (399, 20, 61, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (400, 20, 62, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (401, 20, 63, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (402, 20, 64, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (403, 20, 67, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (404, 20, 66, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (405, 20, 65, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (406, 20, 68, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (407, 20, 75, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (408, 20, 73, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (409, 20, 71, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (410, 20, 72, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (411, 20, 74, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (412, 20, 79, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (413, 20, 77, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (414, 20, 76, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (415, 20, 80, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (416, 20, 78, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (417, 20, 57, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (418, 20, 55, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (419, 20, 53, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (420, 20, 58, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (421, 20, 54, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (422, 20, 60, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (423, 20, 47, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (424, 20, 51, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (425, 20, 46, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (426, 20, 48, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (427, 20, 83, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (428, 20, 84, NULL, '2025-03-16 23:54:52', NULL, 0);
INSERT INTO `paper_question` VALUES (429, 21, 62, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (430, 21, 63, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (431, 21, 68, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (432, 21, 70, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (433, 21, 65, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (434, 21, 64, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (435, 21, 66, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (436, 21, 69, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (437, 21, 61, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (438, 21, 67, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (439, 21, 75, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (440, 21, 72, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (441, 21, 71, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (442, 21, 74, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (443, 21, 73, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (444, 21, 79, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (445, 21, 80, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (446, 21, 77, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (447, 21, 78, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (448, 21, 76, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (449, 21, 57, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (450, 21, 54, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (451, 21, 56, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (452, 21, 53, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (453, 21, 60, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (454, 21, 49, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (455, 21, 52, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (456, 21, 51, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (457, 21, 48, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (458, 21, 83, NULL, '2025-03-29 12:17:33', NULL, 0);
INSERT INTO `paper_question` VALUES (459, 21, 82, NULL, '2025-03-29 12:17:33', NULL, 0);

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '试题ID',
  `question` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题',
  `analysis` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '试题解析',
  `score_value` int(11) NULL DEFAULT NULL COMMENT '分值',
  `type` enum('1','2','3','4','5','6') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '1：单选题 2：多选题 3：判断题，4：填空题，5：简答题，6：应用题',
  `chapter_id` int(11) NULL DEFAULT NULL,
  `level` enum('1','2','3','4','5') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '难度级别1,2,3,4,5',
  `knowledge_point_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知识点标注，用 , 号隔开',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, 'DNS 服务器和DHCP服务器的作用是（）', '该问题解析已省略大是大非胜多负少防守打法是打发受打击考拉发撒老大飞机上老大飞机算啦代发塑料袋副科级塑料袋咖啡机是老大开发机送达了开发机失蜡法垃圾的卡飞机算啦开发水电费山东科技逢山开道激发花洒代发好卡手打很发达手机卡数控刀具发货速度快减肥法发发发发发发户籍卡手打发哈科技', 2, '1', 15, '2', '[1],[2]', '2024-12-27 10:04:42', NULL, 0);
INSERT INTO `question` VALUES (2, '在 SQL 中，关系模式称为__________，子模式称为__________，元组称为__________，\r\n\r\n     \r\n属性称为__________。', '该问题解析已省略', 2, '4', 15, '2', '[6],[7],[8]', '2024-12-27 10:13:31', NULL, 0);
INSERT INTO `question` VALUES (3, '试叙述SQL的关系代数特点和元组演算特点。', '该问题解析已省略', 2, '5', 16, '2', '[3]', '2024-12-27 10:17:04', NULL, 0);
INSERT INTO `question` VALUES (4, '[http://192.168.164.128:9000/exam-bucket/20250207/44af9ff6-9099-49db-b826-0f45c5ca2016-捕获.PNG,http://192.168.164.128:9000/exam-bucket/20250207/f1eeec65-c172-43cb-a24a-171b218c08b7-图片1.PNG]', '该问题解析已省略', 2, '6', 12, '2', '[132],[133]', '2024-12-27 10:19:15', NULL, 0);
INSERT INTO `question` VALUES (5, 'SQL中，表有两种：__________和__________，也称为__________和__________。 ', '该问题解析已省略', 2, '4', 17, '2', '[16],[17]', '2024-12-27 10:19:27', NULL, 0);
INSERT INTO `question` VALUES (6, 'SQL语言对于“查询结果是否允许存在重复元组”是如何实现的？ ', '该问题解析已省略', 2, '5', 19, '2', '[45],[46]', '2024-12-27 10:19:35', NULL, 0);
INSERT INTO `question` VALUES (7, '[http://192.168.164.128:9000/exam-bucket/20250208/bc89aa7d-d6de-45ce-a005-eb847d22c772-捕获1.PNG,http://192.168.164.128:9000/exam-bucket/20250208/9f0c8819-a6b5-4e31-8b94-f4ad39648f6a-捕获2.PNG]', '该问题解析已省略', 2, '6', 13, '2', '[134]', '2024-12-27 10:19:44', NULL, 0);
INSERT INTO `question` VALUES (8, '在TCP/IP协议中，序号小于（  ）的端口称为熟知端口(well-known port)。', '该问题解析已省略', 2, '1', 2, '2', '[35],[36]', '2024-12-27 10:20:07', NULL, 0);
INSERT INTO `question` VALUES (9, '在Internet上用TCP/IP播放视频，想用传输层的最快协议，以减少时延，要使用（ ）', '在Internet上用TCP/IP播放视频，若追求传输层的最快协议以减少时延，应当选择UDP协议。这是因为UDP协议具有低开销特性，相较于TCP协议，它在传输过程中不需要建立连接、维护连接状态以及进行复杂的错误恢复机制，因此能够减少时延，更适合用于对实时性要求较高的流媒体传输场景。', 2, '1', 6, '2', '[67]', '2024-12-27 10:20:19', NULL, 0);
INSERT INTO `question` VALUES (10, '在TCP协议中采用（ ）来区分不同的应用进程', '该问题解析已省略', 2, '1', 2, '2', '[68],[69],[70]', '2024-12-27 10:20:30', NULL, 0);
INSERT INTO `question` VALUES (11, 'SQL中，用户有两种：__________和__________。', '该问题解析已省略', 2, '4', 2, '2', '[78],[79],[80]', '2024-12-27 10:20:41', NULL, 0);
INSERT INTO `question` VALUES (12, '试对SELECT语句中使用的基本表名和列名的语义作详细的解释。', '该问题解析已省略', 2, '5', 6, '2', '[83],[84],[85]', '2024-12-27 10:20:51', NULL, 0);
INSERT INTO `question` VALUES (13, '[http://192.168.164.128:9000/exam-bucket/20250208/ed317dbf-f270-45a3-b605-ce6016ce85ce-捕获3.PNG]', '该问题解析已省略', 2, '6', 12, '2', '[135]', '2024-12-27 10:21:00', NULL, 0);
INSERT INTO `question` VALUES (14, '主机A向主机B连续发送了两个TCP报文段，其序号分别为70和100。试问： （1）第一个报文段携带了（）个字节的数据？', '该问题解析已省略', 2, '1', 2, '2', '[101],[102],[103]', '2024-12-27 10:21:08', NULL, 0);
INSERT INTO `question` VALUES (15, 'PCM脉码调制的过程（ ）', '该问题解析已省略', 2, '1', 6, '2', '[81],[82]', '2024-12-27 10:21:17', NULL, 0);
INSERT INTO `question` VALUES (16, '若某采用4相位调制的通信链路的数据传输速率为2400bps，则该链路的波特率为（）', '该问题解析已省略', 2, '1', 3, '2', '[61],[62],[63]', '2024-12-27 10:21:26', NULL, 0);
INSERT INTO `question` VALUES (17, '以下关于数据传输速率的描述中，错误的是( )', '该问题解析已省略', 2, '1', 3, '2', '[34],[35]', '2024-12-27 10:21:34', NULL, 0);
INSERT INTO `question` VALUES (18, '以下关于时分多路复用概念的描述中，错误的是.(  ).', '该问题解析已省略', 2, '1', 3, '2', '[67],[68]', '2024-12-27 10:21:43', NULL, 0);
INSERT INTO `question` VALUES (19, '1000BASE-T标准支持的传输介质是（）', '该问题解析已省略', 2, '1', 3, '2', '[64],[65]', '2024-12-27 10:21:55', NULL, 0);
INSERT INTO `question` VALUES (20, '一个以太网交换机，读取整个数据帧，对数据帧进行差错校验后再转发出去，这种交换方式称为 （）', '该问题解析已省略', 2, '1', 4, '2', '[73],[74],[75]', '2024-12-27 10:22:04', NULL, 0);
INSERT INTO `question` VALUES (21, '关于VLAN，下面的描述中正确的是（）', '该问题解析已省略', 2, '1', 6, '2', '[81],[82]', '2024-12-27 10:22:17', NULL, 0);
INSERT INTO `question` VALUES (22, '以太网协议中使用物理地址作用是什么？', '该问题解析已省略', 2, '1', 4, '4', '[13],[14],[15]', '2024-12-27 10:22:25', NULL, 0);
INSERT INTO `question` VALUES (23, '以太网采用的CSMA/CD协议，当冲突发生时要通过二进制指数后退算法计算后退延时， 关于这个算法，以下论述中错误的是 （）', '该问题解析已省略', 2, '1', 4, '4', '[21],[22]', '2024-12-27 10:22:35', NULL, 0);
INSERT INTO `question` VALUES (24, '以下关于交换机获取与其端口连接设备的MAC地址的叙述中，正确的是（）', '该问题解析已省略', 2, '1', 6, '5', '[34],[35]', '2024-12-27 10:22:45', NULL, 0);
INSERT INTO `question` VALUES (25, '如果G (x）为11010010，以下4个CRC校验比特序列中只有哪个可能是正确的 ？', '该问题解析已省略', 2, '1', 4, '5', '[41],[42]', '2024-12-27 10:22:54', NULL, 0);
INSERT INTO `question` VALUES (26, '以下哪些协议属于TCP/IP协议族？', '该问题解析已省略', 3, '2', 1, '5', '[48],[49]', '2024-12-27 10:23:38', NULL, 0);
INSERT INTO `question` VALUES (27, '在OSI七层模型中，哪些层负责数据的传输和控制？', '该问题解析已省略', 3, '2', 1, '4', '[62],[63],[64]', '2024-12-27 10:23:47', NULL, 0);
INSERT INTO `question` VALUES (28, '下列哪些设备可以用于构建局域网（LAN）？', '该问题解析已省略', 3, '2', 2, '5', '[73],[74]', '2024-12-27 10:23:56', NULL, 0);
INSERT INTO `question` VALUES (29, '以下哪些因素会影响网络带宽的利用率？', '该问题解析已省略', 3, '2', 2, '2', '[94],[95]', '2024-12-27 10:24:11', NULL, 0);
INSERT INTO `question` VALUES (30, '在网络安全中，以下哪些措施可以用来保护数据的安全性？', '该问题解析已省略', 3, '2', 3, '2', '[104],[105]', '2024-12-27 10:24:23', NULL, 0);
INSERT INTO `question` VALUES (31, '下列哪些命令可以用于网络故障排查？', '该问题解析已省略', 3, '2', 3, '2', '[37],[38]', '2024-12-27 10:24:33', NULL, 0);
INSERT INTO `question` VALUES (32, '在IPv6地址中，以下哪些表述是正确的？', '该问题解析已省略', 3, '2', 4, '2', '[59],[60]', '2024-12-27 10:24:44', NULL, 0);
INSERT INTO `question` VALUES (33, '以下哪些技术可以用于实现网络的冗余和容错？', '该问题解析已省略', 3, '2', 4, '2', '[22],[23]', '2024-12-27 10:24:55', NULL, 0);
INSERT INTO `question` VALUES (34, '在网络编程中，以下哪些协议和库常用于实现客户端与服务器之间的通信？', '该问题解析已省略', 3, '2', 6, '2', '[84],[85]', '2024-12-27 10:25:06', NULL, 0);
INSERT INTO `question` VALUES (35, '以下哪些因素会影响无线网络的性能？', '该问题解析已省略', 3, '2', 5, '2', '[9]', '2024-12-27 10:25:17', NULL, 0);
INSERT INTO `question` VALUES (36, '与有线网相比,无线网的数据传输率一般相对较慢', '该问题解析已省略', 2, '3', 1, '1', '[78],[79]', '2024-12-27 10:25:34', NULL, 0);
INSERT INTO `question` VALUES (37, 'OSI参考模型中,不同节点的同等层具有不同的功能', '该问题解析已省略', 2, '3', 1, '1', '[41],[42]', '2024-12-27 10:25:45', NULL, 0);
INSERT INTO `question` VALUES (38, '普通电脑不能作为服务器', '该问题解析已省略', 2, '3', 2, '1', '[39],[40]', '2024-12-27 10:25:56', NULL, 0);
INSERT INTO `question` VALUES (39, '没有网线的电脑不能连入互联网', '该问题解析已省略', 2, '3', 2, '1', '[30],[31]', '2024-12-27 10:26:06', NULL, 0);
INSERT INTO `question` VALUES (40, '网卡必须安装驱动程序', '该问题解析已省略', 2, '3', 3, '2', '[52],[53],[54]', '2024-12-27 10:26:17', NULL, 0);
INSERT INTO `question` VALUES (41, 'UTP为屏蔽双绞线', '该问题解析已省略', 2, '3', 6, '2', '[57]', '2024-12-27 10:26:25', NULL, 0);
INSERT INTO `question` VALUES (42, '网络接口卡又称为网卡,它是构成网络的基本部件', '该问题解析已省略', 2, '3', 4, '2', '[59]', '2024-12-27 10:26:35', NULL, 0);
INSERT INTO `question` VALUES (43, '无线AP可以成倍地扩展网络覆盖范围', '该问题解析已省略', 2, '3', 4, '3', '[75]', '2024-12-27 10:26:47', NULL, 0);
INSERT INTO `question` VALUES (44, 'SMTP是一组用于由源地址到目的地址传送邮件的协议', '该问题解析已省略', 2, '3', 5, '3', '[108]', '2024-12-27 10:26:57', NULL, 0);
INSERT INTO `question` VALUES (45, '任务管理器可以关闭所有的进程', '该问题解析已省略', 2, '3', 5, '3', '[101]', '2024-12-27 10:27:07', NULL, 0);
INSERT INTO `question` VALUES (46, '试解释DB、DBMS和DBS三个概念。 ', '：DB是长期存储在计算机内、有组织的、统一管理的相关数据的集合。 \r\nDBMS是位于用户与OS之间的一层数据管理软件，它为用户或应用程序提供访问DB的\r\n方法。 \r\nDBS 是实现有组织地、动态地存储大量关联数据、方便多用户访问的计算机硬件、软件\r\n和数据资源组成的系统，即采用数据库技术的计算机系统。 ', 5, '5', 20, '2', '[154],[158]', '2025-03-12 21:37:58', NULL, 0);
INSERT INTO `question` VALUES (47, '人工管理阶段和文件系统阶段的数据管理各有哪些特点？', '答：人工管理阶段主要有四个特点：数据不保存在计算机内；没有专用的软件对数据进行\r\n管理；只有程序的概念，没有文件的概念；数据面向程序。 \r\n文件系统阶段主要有五个特点： 数据以“文件”形式长期保存；数据的逻辑结构与物理\r\n结构有了区别；文件组织已多样化；数据面向应用；对数据的操作以记录为单位。 ', 5, '5', 20, '3', '[155]', '2025-03-12 21:38:54', NULL, 0);
INSERT INTO `question` VALUES (48, '文件系统阶段的数据管理有些什么缺陷？试举例说明。', '答：主要有三个缺陷：数据冗余；数据不一致性；数据联系弱。 \r\n例如学校里教务处、财务处、保健处建立的文件中都有学生详细资料，譬如联系电话，\r\n家庭住址等。这就是“数据”冗余；如果某个学生搬家，就要修改三个部门文件中的数据，\r\n否则会引起同一数据在三个部门中不一致；产生上述问题的原因是这三个部门的文件中数据\r\n没有联系。', 5, '5', 21, '3', '[117]', '2025-03-12 21:39:32', NULL, 0);
INSERT INTO `question` VALUES (49, '数据管理的数据库阶段产生的标志是哪三件事情？ ', '答：进入数据库阶段的标志是20世纪60年代末发生的三件事件： \r\n·1968 年IBM公司研制的IMS系统是一个典型的层次DBS； \r\n·1969 年美国CODASYL组织DBTG报告，提出网状DBS的概念； \r\n·1970 年美国IBM公司的E.F.Codd发表论文，提出关系模型的思想。', 5, '5', 21, '4', '[118]', '2025-03-12 21:40:06', NULL, 0);
INSERT INTO `question` VALUES (50, '数据库阶段的数据管理有哪些特点？', '答：主要有五个特点： \r\n采用数据模型表示复杂的数据结构；有较高的数据独立性；为用户提供了方便的用户接\r\n口；提供了四个方面的数据控制功能；对数据的操作以数据项为单位，增加了系统的灵活性。', 5, '5', 22, '5', '[121]', '2025-03-12 21:40:46', NULL, 0);
INSERT INTO `question` VALUES (51, '分布式数据库系统和面向对象数据库系统各有哪些特点？ ', '答：DDBS主要有三个特点： \r\n·数据物理上分布在各地，但逻辑上是一个整体； \r\n·每个场地既可以执行局部应用，也可以执行全局应用； \r\n·各地的计算机由数据通信网络相连接。 \r\n面向对象数据系统主要有两个特点： \r\n·面向对象数据模型能完整地描述现实世界的数据结构，能表达数据间嵌套、递归的联\r\n系。 \r\n·具有面向对象技术的封装性和继承性的特点，提高了软件的可重用性。', 5, '5', 23, '3', '[122]', '2025-03-12 21:41:55', NULL, 0);
INSERT INTO `question` VALUES (52, ' 试述ER模型、层次模型、网状模型、关系模型和面向对象模型的主要特点。', '答：ER模型直接表示实体类型及实体间联系，与计算机系统无关，充分反映用户的需求，\r\n用户容易理解。 \r\n层次模型的数据结构为树结构，记录之间联系通过指针实现，查询较快，但DML属于过\r\n程化的，操作复杂。 \r\n网状模型的数据结构为有向图，记录之间联系通过指针实现，查询较快，并且容易实现\r\nM:N联系，但DML属于过程化的语言，编程较复杂。 \r\n关系模型的数据结构为二维表格，容易为初学者理解。记录之间联系通过关键码实现。\r\nDML属于非过程化语言，编程较简单。 \r\n面向对象模型能完整描述现实世界的数据结构，具有丰富的表达能力，能表达嵌套、递\r\n归的数据结构。但涉及的知识面较广，用户较难理解，这种模型尚未普及。 ', 5, '5', 24, '5', '[133]', '2025-03-12 21:47:18', NULL, 0);
INSERT INTO `question` VALUES (53, '数据管理技术的发展，与__________、__________和__________有密切的联系。 ', '硬件,软件,计算机应用', 3, '4', 24, '3', '[134]', '2025-03-12 21:50:24', NULL, 0);
INSERT INTO `question` VALUES (54, '．就信息处理的方式而言，在文件系统阶段，__________处于主导地位，_________只起着\r\n服从程序设计需要的作用；而在数据库方式下，_________占据了中心位置。', '程序设计,数据,数据', 3, '4', 24, '3', '[135]', '2025-03-12 21:51:27', NULL, 0);
INSERT INTO `question` VALUES (55, '文件系统中的数据独立性是指__________独立性。', '设备', 1, '4', 25, '1', '[138]', '2025-03-12 21:51:58', NULL, 0);
INSERT INTO `question` VALUES (56, '．DBMS为应用程序运行时开辟的DB系统缓冲区，主要用于__________和__________。', '数据的传输,格式的转换', 2, '4', 26, '2', '[143]', '2025-03-12 21:52:24', NULL, 0);
INSERT INTO `question` VALUES (57, '数据库的三级模式结构是对__________的三个抽象级别。 ', '数据 ', 1, '4', 25, '2', '[139]', '2025-03-12 21:53:07', NULL, 0);
INSERT INTO `question` VALUES (58, '．DBS中存放三级结构定义的DB称为__________。', '数据字典（DD）', 1, '4', 21, '2', '[119]', '2025-03-12 21:53:29', NULL, 0);
INSERT INTO `question` VALUES (59, '．根据计算机的系统结构，DBS可分成四种类型：_______、_______、_______和_______。', '集中式,C/S式,并行式,分布式', 4, '4', 26, '3', '[144]', '2025-03-12 21:53:59', NULL, 0);
INSERT INTO `question` VALUES (60, '．数据独立性使得修改DB结构时尽量不影响已有的__________。', '应用程序 ', 1, '4', 27, '2', '[147]', '2025-03-12 21:54:17', NULL, 0);
INSERT INTO `question` VALUES (61, '在DBS中，DBMS和OS之间关系是 ', NULL, 2, '1', 22, '2', '[122]', '2025-03-12 21:55:24', NULL, 0);
INSERT INTO `question` VALUES (62, '在数据库方式下，信息处理中占据中心位置的是  ', NULL, 2, '1', 24, '2', '[135]', '2025-03-12 21:55:43', NULL, 0);
INSERT INTO `question` VALUES (63, '．DB的三级体系结构是对_________抽象的三个级别。', NULL, 2, '1', 22, '3', '[122]', '2025-03-12 22:15:42', NULL, 0);
INSERT INTO `question` VALUES (64, '．DB的三级模式结构中最接近外部存储器的是 ', NULL, 2, '1', 26, '3', '[144]', '2025-03-12 22:16:20', NULL, 0);
INSERT INTO `question` VALUES (65, '在关系中，“元数”（arity）是指  ', NULL, 2, '1', 29, '2', '[143]', NULL, NULL, 0);
INSERT INTO `question` VALUES (66, '由系统进行数据导航的语言称为 ', NULL, 2, '1', 23, '3', '[123]', NULL, NULL, 0);
INSERT INTO `question` VALUES (67, '设关系R、S、W各有10个元组，那么这三个关系的自然联接的元组个数为', NULL, 2, '1', 28, '4', '[152]', NULL, NULL, 0);
INSERT INTO `question` VALUES (68, '元组比较操作（a1，a2）>（b1，b2）的意义是 ', NULL, 2, '1', 31, '1', '[123]', NULL, NULL, 0);
INSERT INTO `question` VALUES (69, '两个函数依赖集F和G等价的充分必要条件是 ', NULL, 2, '1', 27, '3', '[133]', NULL, NULL, 0);
INSERT INTO `question` VALUES (70, '概念设计阶段设计概念模型通常采用', NULL, 2, '1', 30, '2', '[145]', NULL, NULL, 0);
INSERT INTO `question` VALUES (71, '以下哪些SQL语句可以实现查询“没有选修C2（C2为课程号）课程的学生姓名”：', NULL, 4, '2', 26, '3', '[126]', NULL, NULL, 0);
INSERT INTO `question` VALUES (72, '数据库设计通常包括哪些阶段？', NULL, 4, '2', 27, '2', '[119]', NULL, NULL, 0);
INSERT INTO `question` VALUES (73, '在ER模型中，以下哪些概念是正确的？', NULL, 4, '2', 21, '4', '[116]', NULL, NULL, 0);
INSERT INTO `question` VALUES (74, '数据库的存储结构涉及多个方面，以下哪些选项是关于数据库存储结构的正确描述？', NULL, 4, '2', 22, '5', '[137]', NULL, NULL, 0);
INSERT INTO `question` VALUES (75, '在关系模型与关系运算理论中，以下哪些选项是正确的描述？', NULL, 4, '2', 21, '1', '[152]', NULL, NULL, 0);
INSERT INTO `question` VALUES (76, '数据库系统只包括数据库本身，不包括数据库管理系统（DBMS）。', '数据库系统不仅包括数据库本身，还包括数据库管理系统（DBMS）、数据库管理员（DBA）、应用程序以及用户等组成部分。DBMS是管理数据库的软件，它提供了数据的存储、检索、更新和保护等功能。', 2, '3', 25, '3', '[146]', NULL, NULL, 0);
INSERT INTO `question` VALUES (77, '在关系数据库中，一个关系（表）中的主键可以由多个属性（列）组成。', '在关系数据库中，主键是用来唯一标识表中每一行的属性或属性组。一个关系中的主键可以由单个属性组成，也可以由多个属性组合而成，这种组合主键被称为复合主键。', 2, '3', 23, '2', '[138]', NULL, NULL, 0);
INSERT INTO `question` VALUES (78, '数据库规范化是为了消除数据冗余和提高数据一致性，但过度的规范化可能会导致查询性能下降。', '数据库规范化是一个过程，旨在通过减少数据冗余和提高数据一致性来优化数据库设计。然而，过度的规范化可能会导致表的数量增加，从而增加查询的复杂性和执行时间，因此在实际应用中需要权衡规范化的程度和查询性能。', 2, '3', 29, '3', '[122]', NULL, NULL, 0);
INSERT INTO `question` VALUES (79, '在SQL中，SELECT语句用于从数据库中检索数据，但它不能用于更新或删除数据。', '虽然SELECT语句主要用于从数据库中检索数据，但SQL还提供了其他语句用于更新（UPDATE）和删除（DELETE）数据。此外，INSERT语句用于向数据库中插入新数据。', 2, '3', 21, '2', '[145]', NULL, NULL, 0);
INSERT INTO `question` VALUES (80, '数据库事务的ACID特性包括原子性、一致性、隔离性和持久性，其中持久性意味着事务一旦提交，其对数据库的影响将永远存在，即使系统崩溃。', '数据库事务的ACID特性是确保数据库可靠性和一致性的关键。其中，持久性（Durability）确保事务一旦提交，其对数据库的影响将被永久保存，即使系统发生故障或崩溃。这意味着已提交的事务的结果在系统重新启动后仍然有效。', 2, '3', 24, '3', '[131]', NULL, NULL, 0);
INSERT INTO `question` VALUES (82, '[http://192.168.164.128:9000/exam-bucket/20250207/44af9ff6-9099-49db-b826-0f45c5ca2016-捕获.PNG,http://192.168.164.128:9000/exam-bucket/20250207/f1eeec65-c172-43cb-a24a-171b218c08b7-图片1.PNG]', NULL, 10, '6', 24, '3', '[132],[133]', NULL, NULL, 0);
INSERT INTO `question` VALUES (83, '[http://192.168.164.128:9000/exam-bucket/20250208/bc89aa7d-d6de-45ce-a005-eb847d22c772-捕获1.PNG,http://192.168.164.128:9000/exam-bucket/20250208/9f0c8819-a6b5-4e31-8b94-f4ad39648f6a-捕获2.PNG]', NULL, 10, '6', 24, '4', '[134]', NULL, NULL, 0);
INSERT INTO `question` VALUES (84, '[http://192.168.164.128:9000/exam-bucket/20250208/ed317dbf-f270-45a3-b605-ce6016ce85ce-捕获3.PNG]', NULL, 10, '6', 24, '3', '[135]', NULL, NULL, 0);

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '分数表ID',
  `score` double(11, 1) NULL DEFAULT NULL COMMENT '分数',
  `student_id` bigint(20) NULL DEFAULT NULL COMMENT '学生ID',
  `paper_id` int(11) NULL DEFAULT NULL COMMENT '试卷ID',
  `answer_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '答题时间',
  `time` int(11) NULL DEFAULT NULL COMMENT '总耗时，单位分钟',
  `review` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0：待批阅，1：已批阅',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES (53, 32.0, 25010002, 6, '2025-03-05 19:26:36', 2, '1', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score` VALUES (55, 37.5, 25010003, 11, '2025-03-13 00:46:22', 1, '1', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score` VALUES (56, 70.5, 25010004, 11, '2025-03-13 20:40:58', 1, '1', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score` VALUES (57, 92.0, 25010005, 11, '2025-03-13 20:40:42', 1, '1', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score` VALUES (58, 76.0, 25010002, 13, '2025-03-13 20:41:31', 1, '1', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score` VALUES (59, 72.5, 25010002, 12, '2025-03-13 20:40:34', 1, '1', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score` VALUES (60, 86.0, 25010006, 11, '2025-03-13 20:40:24', 2, '1', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score` VALUES (61, 51.5, 25010002, 11, '2025-03-16 23:21:34', 1, '1', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score` VALUES (62, 37.0, 25010002, 19, '2025-03-16 23:53:24', 1, '1', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score` VALUES (63, 16.5, 25010002, 20, '2025-03-17 09:21:43', 2, '1', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score` VALUES (64, NULL, 25010002, 21, '2025-03-29 13:39:53', 1, '0', '2025-03-29 13:40:55', NULL, 0);

-- ----------------------------
-- Table structure for score_detail
-- ----------------------------
DROP TABLE IF EXISTS `score_detail`;
CREATE TABLE `score_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '成绩详情表',
  `score_id` int(11) NULL DEFAULT NULL COMMENT '成绩表ID',
  `question_id` bigint(20) NULL DEFAULT NULL COMMENT '试题ID',
  `paper_question_id` int(11) NULL DEFAULT NULL COMMENT '试卷_试题ID',
  `answer` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户答案',
  `score` double NULL DEFAULT NULL COMMENT '赋分',
  `correct_answer` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参考答案',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 934 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score_detail
-- ----------------------------
INSERT INTO `score_detail` VALUES (136, 28, NULL, 22, '0001', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (137, 28, NULL, 44, '1', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (138, 28, NULL, 23, '0001', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (139, 28, NULL, 45, '0', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (140, 28, NULL, 24, '0010', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (141, 28, NULL, 25, '0010', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (142, 28, NULL, 26, '0110', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (143, 28, NULL, 27, '1110', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (144, 28, NULL, 28, '1111', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (145, 28, NULL, 29, '1110', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (146, 28, NULL, 30, '1111', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (147, 28, NULL, 31, '1110', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (148, 28, NULL, 10, '0010', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (149, 28, NULL, 32, '1110', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (150, 28, NULL, 11, '0001', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (151, 28, NULL, 33, '0111', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (152, 28, NULL, 12, '1000', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (153, 28, NULL, 34, '1110', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (154, 28, NULL, 13, '0100', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (155, 28, NULL, 35, '1111', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (156, 28, NULL, 14, '0100', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (157, 28, NULL, 36, '1', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (158, 28, NULL, 15, '0001', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (159, 28, NULL, 37, '0', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (160, 28, NULL, 16, '1000', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (161, 28, NULL, 38, '0', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (162, 28, NULL, 17, '0001', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (163, 28, NULL, 39, '1', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (164, 28, NULL, 18, '0100', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (165, 28, NULL, 19, '1000', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (166, 28, NULL, 1, '1000', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (167, 28, NULL, 2, '0100', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (168, 28, NULL, 3, '0100', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (169, 28, NULL, 4, '0010', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (170, 28, NULL, 5, '1000', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (171, 28, NULL, 6, '0001', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (172, 28, NULL, 7, '0010', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (173, 28, NULL, 8, '0100', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (174, 28, NULL, 9, '1000', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (175, 28, NULL, 40, '0', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (176, 28, NULL, 41, '1', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (177, 28, NULL, 20, '0010', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (178, 28, NULL, 42, '1', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (179, 28, NULL, 21, '0100', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (180, 28, NULL, 43, '0', NULL, NULL, '2025-01-18 20:38:16', NULL, 0);
INSERT INTO `score_detail` VALUES (181, 29, NULL, 67, '1000', NULL, NULL, '2025-01-18 22:09:17', NULL, 0);
INSERT INTO `score_detail` VALUES (182, 29, NULL, 89, '1', NULL, NULL, '2025-01-18 22:09:17', NULL, 0);
INSERT INTO `score_detail` VALUES (183, 29, NULL, 68, '0001', NULL, NULL, '2025-01-18 22:09:17', NULL, 0);
INSERT INTO `score_detail` VALUES (184, 29, NULL, 90, '1', NULL, NULL, '2025-01-18 22:09:17', NULL, 0);
INSERT INTO `score_detail` VALUES (185, 29, NULL, 69, '0010', NULL, NULL, '2025-01-18 22:09:17', NULL, 0);
INSERT INTO `score_detail` VALUES (186, 29, NULL, 70, '1000', NULL, NULL, '2025-01-18 22:09:17', NULL, 0);
INSERT INTO `score_detail` VALUES (187, 29, NULL, 71, '1110', NULL, NULL, '2025-01-18 22:09:17', NULL, 0);
INSERT INTO `score_detail` VALUES (188, 29, NULL, 72, '1111', NULL, NULL, '2025-01-18 22:09:17', NULL, 0);
INSERT INTO `score_detail` VALUES (189, 29, NULL, 73, '1111', NULL, NULL, '2025-01-18 22:09:17', NULL, 0);
INSERT INTO `score_detail` VALUES (190, 29, NULL, 74, '1111', NULL, NULL, '2025-01-18 22:09:17', NULL, 0);
INSERT INTO `score_detail` VALUES (191, 29, NULL, 75, '1111', NULL, NULL, '2025-01-18 22:09:17', NULL, 0);
INSERT INTO `score_detail` VALUES (192, 29, NULL, 76, '1110', NULL, NULL, '2025-01-18 22:09:17', NULL, 0);
INSERT INTO `score_detail` VALUES (193, 29, NULL, 55, '0010', NULL, NULL, '2025-01-18 22:09:17', NULL, 0);
INSERT INTO `score_detail` VALUES (194, 29, NULL, 77, '1110', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (195, 29, NULL, 56, '0100', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (196, 29, NULL, 78, '1101', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (197, 29, NULL, 57, '0010', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (198, 29, NULL, 79, '1110', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (199, 29, NULL, 58, '1000', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (200, 29, NULL, 80, '1111', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (201, 29, NULL, 59, '0001', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (202, 29, NULL, 81, '1', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (203, 29, NULL, 60, '0100', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (204, 29, NULL, 82, '0', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (205, 29, NULL, 61, '0100', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (206, 29, NULL, 83, '0', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (207, 29, NULL, 62, '0010', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (208, 29, NULL, 84, '1', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (209, 29, NULL, 63, '0100', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (210, 29, NULL, 64, '0100', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (211, 29, NULL, 46, '0100', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (212, 29, NULL, 47, '0010', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (213, 29, NULL, 48, '1000', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (214, 29, NULL, 49, '0001', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (215, 29, NULL, 50, '0100', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (216, 29, NULL, 51, '1000', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (217, 29, NULL, 52, '0010', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (218, 29, NULL, 53, '0100', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (219, 29, NULL, 54, '0001', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (220, 29, NULL, 85, '0', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (221, 29, NULL, 86, '1', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (222, 29, NULL, 65, '1000', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (223, 29, NULL, 87, '0', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (224, 29, NULL, 66, '0010', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (225, 29, NULL, 88, '0', NULL, NULL, '2025-01-18 22:09:18', NULL, 0);
INSERT INTO `score_detail` VALUES (226, 30, NULL, 113, '0100', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (227, 30, NULL, 135, '1', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (228, 30, NULL, 114, '0010', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (229, 30, NULL, 136, '0', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (230, 30, NULL, 115, '1000', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (231, 30, NULL, 116, '0010', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (232, 30, NULL, 117, '0111', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (233, 30, NULL, 118, '1101', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (234, 30, NULL, 119, '1111', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (235, 30, NULL, 120, '1111', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (236, 30, NULL, 121, '1111', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (237, 30, NULL, 122, '1110', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (238, 30, NULL, 101, '0100', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (239, 30, NULL, 123, '1110', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (240, 30, NULL, 102, '1000', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (241, 30, NULL, 124, '1111', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (242, 30, NULL, 103, '1000', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (243, 30, NULL, 125, '1110', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (244, 30, NULL, 104, '1000', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (245, 30, NULL, 126, '1110', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (246, 30, NULL, 105, '0100', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (247, 30, NULL, 127, '1', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (248, 30, NULL, 106, '0100', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (249, 30, NULL, 128, '0', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (250, 30, NULL, 107, '0100', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (251, 30, NULL, 129, '1', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (252, 30, NULL, 108, '0010', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (253, 30, NULL, 130, '0', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (254, 30, NULL, 109, '0100', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (255, 30, NULL, 110, '1000', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (256, 30, NULL, 92, '1000', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (257, 30, NULL, 93, '0100', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (258, 30, NULL, 94, '0100', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (259, 30, NULL, 95, '0010', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (260, 30, NULL, 96, '0100', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (261, 30, NULL, 97, '1000', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (262, 30, NULL, 98, '0100', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (263, 30, NULL, 99, '0100', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (264, 30, NULL, 100, '1000', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (265, 30, NULL, 131, '1', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (266, 30, NULL, 132, '1', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (267, 30, NULL, 111, '1000', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (268, 30, NULL, 133, '0', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (269, 30, NULL, 112, '0100', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (270, 30, NULL, 134, '0', NULL, NULL, '2025-01-24 08:34:41', NULL, 0);
INSERT INTO `score_detail` VALUES (274, 47, 22, NULL, 'B', 2, 'B', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (275, 47, 44, NULL, '0', 0, '1', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (276, 47, 23, NULL, 'C', 0, 'A', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (277, 47, 45, NULL, '1', 0, '0', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (278, 47, 24, NULL, 'A', 0, 'B', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (279, 47, 25, NULL, 'D', 0, 'B', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (280, 47, 26, NULL, 'BD', 0, 'CD', '2025-02-08 23:40:50', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (281, 47, 27, NULL, 'BC', 3, 'BC', '2025-02-08 23:34:16', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (282, 47, 28, NULL, 'ABC', 3, 'ABC', '2025-02-08 23:34:19', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (283, 47, 29, NULL, 'BCD', 0, 'BD', '2025-02-08 23:34:24', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (284, 47, 30, NULL, 'ABC', 3, 'ABC', '2025-02-08 23:34:27', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (285, 47, 31, NULL, 'BCD', 0, 'BD', '2025-02-08 23:34:29', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (286, 47, 10, NULL, 'B', 0, 'A', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (287, 47, 32, NULL, 'ABC', 0, 'ABCD', '2025-02-08 23:34:34', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (288, 47, 11, NULL, '6,7', 1, '应用程序,终端用户', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (289, 47, 33, NULL, 'BC', 0, 'AC', '2025-02-08 23:34:37', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (290, 47, 12, NULL, '梵蒂冈回复挂电话', 1, '答：在基本 SQL 中，SELECT 语句中使用的基本表名都应该理解成表中的元组变量，而\r\n列名就成了元组分量。这样就使SELECT语句带有元组演算的特点。 \r\n（注：实际上，在基本SQL中，把关系变量和元组变量混为一谈了。这在面向对象数据库\r\n中得到了纠正，在引用表时，都要为表定义一个元组变量。）', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (291, 47, 34, NULL, 'AC', 0, 'BD', '2025-02-08 23:34:39', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (292, 47, 13, NULL, '电饭锅电饭锅', 1, NULL, '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (293, 47, 35, NULL, 'CD', 3, 'CD', '2025-02-08 23:34:44', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (294, 47, 14, NULL, 'C', 0, 'B', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (295, 47, 36, NULL, '1', 2, '1', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (296, 47, 15, NULL, 'D', 0, 'A', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (297, 47, 37, NULL, '0', 2, '0', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (298, 47, 16, NULL, 'B', 2, 'B', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (299, 47, 38, NULL, '1', 0, '0', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (300, 47, 17, NULL, 'C', 2, 'C', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (301, 47, 39, NULL, '0', 2, '0', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (302, 47, 18, NULL, 'D', 2, 'D', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (303, 47, 19, NULL, 'A', 2, 'A', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (304, 47, 1, NULL, 'B', 0, 'C', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (305, 47, 2, NULL, 'cvbc,cvb,vn,4', 1, '基本表,视图,行,列 ', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (306, 47, 3, NULL, 'fdgdfgdfgdf', 1, '答：SQL的SELECT语句的基本句法来自于关系代数表达式πL（ςF（R1×„×Rm）），\r\n并且SQL中有并（UNION）、交（INTERSECT）和差（EXCEPT）等操作，因此 SQL\r\n具有关系代数特点。 \r\nSELECT 语句中出现的基本表名，都应该理解成基本表中的元组变量，而列名应理解\r\n成元组分量，这样SQL就具有了元组演算的特点。', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (307, 47, 4, NULL, 'http://192.168.164.128:9000/exam-bucket/20250208/a3516aff-f035-4b52-8e2b-77e3869e1385-Linux系统命令.pdf', 1, '解：(1) ER图如图5.2所示。 \r\n公司编号 \r\n仓库编号 \r\n公司名 \r\n地址 \r\n公司 \r\n1 \r\n隶属 \r\n仓库名 \r\nN \r\n仓库 \r\n地址 \r\n1 \r\n聘用 \r\n聘期 \r\n工资 \r\nN \r\n职工 \r\n职工编号 \r\n姓名 \r\n图5.2 \r\n（2）这个ER图可转换3个关系模式： \r\n性别 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--61 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--62 \r\n  公司（公司编号，公司名，地址） \r\n  仓库（仓库编号，仓库名，地址，公司编号） \r\n  职工（职工编号，姓名，性别，仓库编号，聘期，工资）', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (308, 47, 5, NULL, '4,5,6,7', 1, '基本表,视图,实表,虚表', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (309, 47, 6, NULL, 'dfgdfg东莞讽德诵功', 1, '答：对于SELECT语句中SELECT子句，若用“SELECT  DISTINCT”形式，则查询结\r\n果中不允许有重复元组；若不写DISTINCT字样，则查询结果中允许出现重复元组。', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (310, 47, 7, NULL, '电饭锅电饭锅', 1, NULL, '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (311, 47, 8, NULL, 'D', 0, 'A', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (312, 47, 9, NULL, 'A', 2, 'A', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (313, 47, 40, NULL, '1', 2, '1', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (314, 47, 41, NULL, '1', 0, '0', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (315, 47, 20, NULL, 'B', 0, 'D', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (316, 47, 42, NULL, '0', 0, '1', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (317, 47, 21, NULL, 'C', 0, 'D', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (318, 47, 43, NULL, '1', 2, '1', '2025-02-08 16:43:57', '2025-02-09 15:47:32', 0);
INSERT INTO `score_detail` VALUES (493, 52, 22, NULL, 'B', 2, 'B', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (494, 52, 44, NULL, '1', 2, '1', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (495, 52, 23, NULL, 'C', 0, 'A', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (496, 52, 45, NULL, '0', 2, '0', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (497, 52, 24, NULL, 'D', 0, 'B', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (498, 52, 25, NULL, 'B', 2, 'B', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (499, 52, 26, NULL, 'BCD', 0, 'ABCD', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (500, 52, 27, NULL, 'BCD', 0, 'ABD', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (501, 52, 28, NULL, 'BCD', 0, 'ABD', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (502, 52, 29, NULL, 'BCD', 0, 'ABCD', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (503, 52, 30, NULL, 'ABC', 0, 'ABCD', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (504, 52, 31, NULL, 'ABC', 3, 'ABC', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (505, 52, 10, NULL, 'D', 0, 'A', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (506, 52, 32, NULL, 'AB', 0, 'ABC', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (507, 52, 11, NULL, '1,2', 1, '应用程序,终端用户', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (508, 52, 33, NULL, 'BCD', 0, 'ABCD', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (509, 52, 12, NULL, 'dfgdfgdsfg', 2, '答：在基本 SQL 中，SELECT 语句中使用的基本表名都应该理解成表中的元组变量，而\r\n列名就成了元组分量。这样就使SELECT语句带有元组演算的特点。 \r\n（注：实际上，在基本SQL中，把关系变量和元组变量混为一谈了。这在面向对象数据库\r\n中得到了纠正，在引用表时，都要为表定义一个元组变量。）', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (510, 52, 34, NULL, 'BCD', 0, 'ABD', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (511, 52, 13, NULL, 'sdfsdfdsf', 1, NULL, '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (512, 52, 35, NULL, 'ABC', 0, 'ABCD', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (513, 52, 14, NULL, 'B', 2, 'B', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (514, 52, 36, NULL, '1', 2, '1', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (515, 52, 15, NULL, 'A', 2, 'A', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (516, 52, 37, NULL, '0', 2, '0', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (517, 52, 16, NULL, 'C', 0, 'B', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (518, 52, 38, NULL, '1', 0, '0', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (519, 52, 17, NULL, 'B', 0, 'C', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (520, 52, 39, NULL, '0', 2, '0', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (521, 52, 18, NULL, 'C', 0, 'D', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (522, 52, 19, NULL, 'D', 0, 'A', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (523, 52, 1, NULL, 'A', 0, 'C', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (524, 52, 2, NULL, '5,1,4,3', 1, '基本表,视图,行,列 ', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (525, 52, 3, NULL, 'dfgdsgsdfg', 1, '答：SQL的SELECT语句的基本句法来自于关系代数表达式πL（ςF（R1×„×Rm）），\r\n并且SQL中有并（UNION）、交（INTERSECT）和差（EXCEPT）等操作，因此 SQL\r\n具有关系代数特点。 \r\nSELECT 语句中出现的基本表名，都应该理解成基本表中的元组变量，而列名应理解\r\n成元组分量，这样SQL就具有了元组演算的特点。', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (526, 52, 4, NULL, 'sdlkafjls;adkf', 2, '解：(1) ER图如图5.2所示。 \r\n公司编号 \r\n仓库编号 \r\n公司名 \r\n地址 \r\n公司 \r\n1 \r\n隶属 \r\n仓库名 \r\nN \r\n仓库 \r\n地址 \r\n1 \r\n聘用 \r\n聘期 \r\n工资 \r\nN \r\n职工 \r\n职工编号 \r\n姓名 \r\n图5.2 \r\n（2）这个ER图可转换3个关系模式： \r\n性别 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--61 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--62 \r\n  公司（公司编号，公司名，地址） \r\n  仓库（仓库编号，仓库名，地址，公司编号） \r\n  职工（职工编号，姓名，性别，仓库编号，聘期，工资）', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (527, 52, 5, NULL, '1,2,3,4', 2, '基本表,视图,实表,虚表', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (528, 52, 6, NULL, 'sdfgdsfgsdf', 2, '答：对于SELECT语句中SELECT子句，若用“SELECT  DISTINCT”形式，则查询结\r\n果中不允许有重复元组；若不写DISTINCT字样，则查询结果中允许出现重复元组。', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (529, 52, 7, NULL, 'http://192.168.164.128:9000/exam-bucket/20250215/4f4d6b12-c9c2-4043-9b90-8cd4439a98d8-Linux系统命令.pdf', 1, NULL, '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (530, 52, 8, NULL, 'B', 0, 'A', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (531, 52, 9, NULL, 'C', 0, 'A', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (532, 52, 40, NULL, '1', 2, '1', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (533, 52, 41, NULL, '0', 2, '0', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (534, 52, 20, NULL, 'B', 0, 'D', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (535, 52, 42, NULL, '1', 2, '1', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (536, 52, 21, NULL, 'C', 0, 'D', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (537, 52, 43, NULL, '0', 0, '1', '2025-02-15 11:16:55', '2025-02-15 11:18:02', 0);
INSERT INTO `score_detail` VALUES (538, 53, 22, NULL, 'C', 0, 'B', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (539, 53, 44, NULL, '0', 0, '1', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (540, 53, 23, NULL, 'D', 0, 'A', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (541, 53, 45, NULL, '1', 0, '0', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (542, 53, 24, NULL, 'B', 2, 'B', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (543, 53, 25, NULL, 'A', 0, 'B', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (544, 53, 26, NULL, 'BC', 0, 'ABCD', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (545, 53, 27, NULL, 'CD', 0, 'ABD', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (546, 53, 28, NULL, 'BCD', 0, 'ABD', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (547, 53, 29, NULL, 'ABC', 0, 'ABCD', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (548, 53, 30, NULL, 'BCD', 0, 'ABCD', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (549, 53, 31, NULL, 'ABC', 3, 'ABC', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (550, 53, 10, NULL, 'D', 0, 'A', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (551, 53, 32, NULL, 'ABC', 3, 'ABC', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (552, 53, 11, NULL, 'sdf,rerw', 1, '应用程序,终端用户', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (553, 53, 33, NULL, 'BC', 0, 'ABCD', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (554, 53, 12, NULL, 'sdf', 3, '答：在基本 SQL 中，SELECT 语句中使用的基本表名都应该理解成表中的元组变量，而\r\n列名就成了元组分量。这样就使SELECT语句带有元组演算的特点。 \r\n（注：实际上，在基本SQL中，把关系变量和元组变量混为一谈了。这在面向对象数据库\r\n中得到了纠正，在引用表时，都要为表定义一个元组变量。）', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (555, 53, 34, NULL, 'CD', 0, 'ABD', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (556, 53, 13, NULL, 'dsdf', 2, NULL, '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (557, 53, 35, NULL, 'ABC', 0, 'ABCD', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (558, 53, 14, NULL, 'A', 0, 'B', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (559, 53, 36, NULL, '0', 0, '1', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (560, 53, 15, NULL, 'B', 0, 'A', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (561, 53, 37, NULL, '0', 2, '0', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (562, 53, 16, NULL, 'C', 0, 'B', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (563, 53, 38, NULL, '1', 0, '0', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (564, 53, 17, NULL, 'A', 0, 'C', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (565, 53, 39, NULL, '0', 2, '0', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (566, 53, 18, NULL, 'C', 0, 'D', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (567, 53, 19, NULL, 'B', 0, 'A', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (568, 53, 1, NULL, 'A', 0, 'C', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (569, 53, 2, NULL, 'sf,df,dsf,sf', 1, '基本表,视图,行,列 ', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (570, 53, 3, NULL, 'sdf', 1, '答：SQL的SELECT语句的基本句法来自于关系代数表达式πL（ςF（R1×„×Rm）），\r\n并且SQL中有并（UNION）、交（INTERSECT）和差（EXCEPT）等操作，因此 SQL\r\n具有关系代数特点。 \r\nSELECT 语句中出现的基本表名，都应该理解成基本表中的元组变量，而列名应理解\r\n成元组分量，这样SQL就具有了元组演算的特点。', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (571, 53, 4, NULL, 'sdf', 1, '解：(1) ER图如图5.2所示。 \r\n公司编号 \r\n仓库编号 \r\n公司名 \r\n地址 \r\n公司 \r\n1 \r\n隶属 \r\n仓库名 \r\nN \r\n仓库 \r\n地址 \r\n1 \r\n聘用 \r\n聘期 \r\n工资 \r\nN \r\n职工 \r\n职工编号 \r\n姓名 \r\n图5.2 \r\n（2）这个ER图可转换3个关系模式： \r\n性别 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--61 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--62 \r\n  公司（公司编号，公司名，地址） \r\n  仓库（仓库编号，仓库名，地址，公司编号） \r\n  职工（职工编号，姓名，性别，仓库编号，聘期，工资）', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (572, 53, 5, NULL, 'sdf,sdf,sdf,fdg', 2, '基本表,视图,实表,虚表', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (573, 53, 6, NULL, 'dsf', 2, '答：对于SELECT语句中SELECT子句，若用“SELECT  DISTINCT”形式，则查询结\r\n果中不允许有重复元组；若不写DISTINCT字样，则查询结果中允许出现重复元组。', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (574, 53, 7, NULL, 'sdf', 1, NULL, '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (575, 53, 8, NULL, 'B', 0, 'A', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (576, 53, 9, NULL, 'C', 0, 'A', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (577, 53, 40, NULL, '1', 2, '1', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (578, 53, 41, NULL, '0', 2, '0', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (579, 53, 20, NULL, 'C', 0, 'D', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (580, 53, 42, NULL, '1', 2, '1', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (581, 53, 21, NULL, 'A', 0, 'D', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (582, 53, 43, NULL, '0', 0, '1', '2025-03-05 18:12:05', '2025-03-05 19:26:37', 0);
INSERT INTO `score_detail` VALUES (583, 54, 22, NULL, 'A', 0, 'B', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (584, 54, 44, NULL, '1', 2, '1', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (585, 54, 23, NULL, 'A', 2, 'A', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (586, 54, 45, NULL, '0', 2, '0', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (587, 54, 24, NULL, 'B', 2, 'B', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (588, 54, 25, NULL, 'A', 0, 'B', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (589, 54, 26, NULL, 'B', 0, 'ABCD', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (590, 54, 27, NULL, 'CD', 0, 'ABD', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (591, 54, 28, NULL, 'AB', 0, 'ABD', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (592, 54, 29, NULL, 'BCD', 0, 'ABCD', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (593, 54, 30, NULL, 'ABC', 0, 'ABCD', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (594, 54, 31, NULL, 'BCD', 0, 'ABC', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (595, 54, 10, NULL, 'D', 0, 'A', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (596, 54, 32, NULL, 'ABC', 3, 'ABC', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (597, 54, 11, NULL, ',2', 1.5, '应用程序,终端用户', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (598, 54, 33, NULL, 'ABCD', 3, 'ABCD', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (599, 54, 12, NULL, '2', 2, '答：在基本 SQL 中，SELECT 语句中使用的基本表名都应该理解成表中的元组变量，而\r\n列名就成了元组分量。这样就使SELECT语句带有元组演算的特点。 \r\n（注：实际上，在基本SQL中，把关系变量和元组变量混为一谈了。这在面向对象数据库\r\n中得到了纠正，在引用表时，都要为表定义一个元组变量。）', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (600, 54, 34, NULL, 'ABC', 0, 'ABD', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (601, 54, 13, NULL, '2', 0.5, NULL, '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (602, 54, 35, NULL, 'BCD', 0, 'ABCD', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (603, 54, 14, NULL, 'A', 0, 'B', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (604, 54, 36, NULL, '1', 2, '1', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (605, 54, 15, NULL, 'B', 0, 'A', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (606, 54, 37, NULL, '0', 2, '0', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (607, 54, 16, NULL, 'C', 0, 'B', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (608, 54, 38, NULL, '1', 0, '0', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (609, 54, 17, NULL, 'D', 0, 'C', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (610, 54, 39, NULL, '0', 2, '0', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (611, 54, 18, NULL, 'A', 0, 'D', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (612, 54, 19, NULL, 'B', 0, 'A', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (613, 54, 1, NULL, 'A', 0, 'C', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (614, 54, 2, NULL, '1,,,2', 0.5, '基本表,视图,行,列 ', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (615, 54, 3, NULL, '2', 1, '答：SQL的SELECT语句的基本句法来自于关系代数表达式πL（ςF（R1×„×Rm）），\r\n并且SQL中有并（UNION）、交（INTERSECT）和差（EXCEPT）等操作，因此 SQL\r\n具有关系代数特点。 \r\nSELECT 语句中出现的基本表名，都应该理解成基本表中的元组变量，而列名应理解\r\n成元组分量，这样SQL就具有了元组演算的特点。', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (616, 54, 4, NULL, 'http://192.168.164.128:9000/exam-bucket/20250305/68ad17f6-66c8-4e27-b73f-ee2cfa848e01-Linux系统命令.pdf', 1, '解：(1) ER图如图5.2所示。 \r\n公司编号 \r\n仓库编号 \r\n公司名 \r\n地址 \r\n公司 \r\n1 \r\n隶属 \r\n仓库名 \r\nN \r\n仓库 \r\n地址 \r\n1 \r\n聘用 \r\n聘期 \r\n工资 \r\nN \r\n职工 \r\n职工编号 \r\n姓名 \r\n图5.2 \r\n（2）这个ER图可转换3个关系模式： \r\n性别 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--61 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--62 \r\n  公司（公司编号，公司名，地址） \r\n  仓库（仓库编号，仓库名，地址，公司编号） \r\n  职工（职工编号，姓名，性别，仓库编号，聘期，工资）', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (617, 54, 5, NULL, ',,2', 1, '基本表,视图,实表,虚表', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (618, 54, 6, NULL, '2', 2, '答：对于SELECT语句中SELECT子句，若用“SELECT  DISTINCT”形式，则查询结\r\n果中不允许有重复元组；若不写DISTINCT字样，则查询结果中允许出现重复元组。', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (619, 54, 7, NULL, 'http://192.168.164.128:9000/exam-bucket/20250305/0dc5165a-b068-4143-93f0-5383e55c5480-数据库系统教程_课后答案(施伯乐)(第三版).pdf', 1.5, NULL, '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (620, 54, 8, NULL, 'B', 0, 'A', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (621, 54, 9, NULL, 'C', 0, 'A', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (622, 54, 40, NULL, '1', 2, '1', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (623, 54, 41, NULL, '0', 2, '0', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (624, 54, 20, NULL, 'C', 0, 'D', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (625, 54, 42, NULL, '1', 2, '1', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (626, 54, 21, NULL, 'D', 2, 'D', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (627, 54, 43, NULL, '0', 0, '1', '2025-03-05 19:40:19', '2025-03-07 14:51:28', 0);
INSERT INTO `score_detail` VALUES (628, 55, 66, NULL, 'D', 2, 'D', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (629, 55, 67, NULL, 'A', 0, 'D', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (630, 55, 68, NULL, 'C', 2, 'C', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (631, 55, 69, NULL, 'A', 0, 'D', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (632, 55, 48, NULL, '地方水电费是的', 1, '答：主要有三个缺陷：数据冗余；数据不一致性；数据联系弱。 \r\n例如学校里教务处、财务处、保健处建立的文件中都有学生详细资料，譬如联系电话，\r\n家庭住址等。这就是“数据”冗余；如果某个学生搬家，就要修改三个部门文件中的数据，\r\n否则会引起同一数据在三个部门中不一致；产生上述问题的原因是这三个部门的文件中数据\r\n没有联系。', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (633, 55, 49, NULL, '收到发斯蒂芬', 1.5, '答：进入数据库阶段的标志是20世纪60年代末发生的三件事件： \r\n·1968 年IBM公司研制的IMS系统是一个典型的层次DBS； \r\n·1969 年美国CODASYL组织DBTG报告，提出网状DBS的概念； \r\n·1970 年美国IBM公司的E.F.Codd发表论文，提出关系模型的思想。', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (634, 55, 70, NULL, 'C', 2, 'C', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (635, 55, 71, NULL, 'BCD', 4, 'BCD', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (636, 55, 50, NULL, '史蒂夫深度的', 3, '答：主要有五个特点： \r\n采用数据模型表示复杂的数据结构；有较高的数据独立性；为用户提供了方便的用户接\r\n口；提供了四个方面的数据控制功能；对数据的操作以数据项为单位，增加了系统的灵活性。', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (637, 55, 72, NULL, 'ABCD', 4, 'ABCD', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (638, 55, 73, NULL, 'BCD', 0, 'ABCD', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (639, 55, 74, NULL, 'ABC', 0, 'ACD', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (640, 55, 53, NULL, '1,2,3', 1, '硬件,软件,计算机应用', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (641, 55, 75, NULL, 'ACD', 0, 'ABC', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (642, 55, 54, NULL, '1,2,3', 1, '程序设计,数据,数据', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (643, 55, 76, NULL, '1', 0, '0', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (644, 55, 55, NULL, '1', 1, '设备', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (645, 55, 77, NULL, '0', 0, '1', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (646, 55, 56, NULL, '1,2', 2, '数据的传输,格式的转换', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (647, 55, 78, NULL, '1', 2, '1', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (648, 55, 57, NULL, '2', 0.5, '数据 ', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (649, 55, 79, NULL, '0', 2, '0', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (650, 55, 80, NULL, '1', 2, '1', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (651, 55, 82, NULL, '是的冯绍峰', 2, '解：(1) ER图如图5.2所示。 \r\n公司编号 \r\n仓库编号 \r\n公司名 \r\n地址 \r\n公司 \r\n1 \r\n隶属 \r\n仓库名 \r\nN \r\n仓库 \r\n地址 \r\n1 \r\n聘用 \r\n聘期 \r\n工资 \r\nN \r\n职工 \r\n职工编号 \r\n姓名 \r\n图5.2 \r\n（2）这个ER图可转换3个关系模式： \r\n性别 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--61 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--62 \r\n  公司（公司编号，公司名，地址） \r\n  仓库（仓库编号，仓库名，地址，公司编号） \r\n  职工（职工编号，姓名，性别，仓库编号，聘期，工资）', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (652, 55, 61, NULL, 'B', 0, 'D', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (653, 55, 83, NULL, '史蒂夫', 2.5, '答：对于SELECT语句中SELECT子句，若用“SELECT  DISTINCT”形式，则查询结\r\n果中不允许有重复元组；若不写DISTINCT字样，则查询结果中允许出现重复元组。', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (654, 55, 62, NULL, 'C', 2, 'C', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (655, 55, 63, NULL, 'A', 0, 'B', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (656, 55, 64, NULL, 'C', 0, 'D', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (657, 55, 65, NULL, 'A', 0, 'D', '2025-03-13 00:43:21', '2025-03-13 00:46:23', 0);
INSERT INTO `score_detail` VALUES (658, 56, 66, NULL, 'C', 0, 'D', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (659, 56, 67, NULL, 'D', 2, 'D', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (660, 56, 68, NULL, 'B', 0, 'C', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (661, 56, 69, NULL, 'C', 0, 'D', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (662, 56, 48, NULL, '2水电费水电费', 4, '答：主要有三个缺陷：数据冗余；数据不一致性；数据联系弱。 \r\n例如学校里教务处、财务处、保健处建立的文件中都有学生详细资料，譬如联系电话，\r\n家庭住址等。这就是“数据”冗余；如果某个学生搬家，就要修改三个部门文件中的数据，\r\n否则会引起同一数据在三个部门中不一致；产生上述问题的原因是这三个部门的文件中数据\r\n没有联系。', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (663, 56, 49, NULL, '水电费十多个', 3, '答：进入数据库阶段的标志是20世纪60年代末发生的三件事件： \r\n·1968 年IBM公司研制的IMS系统是一个典型的层次DBS； \r\n·1969 年美国CODASYL组织DBTG报告，提出网状DBS的概念； \r\n·1970 年美国IBM公司的E.F.Codd发表论文，提出关系模型的思想。', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (664, 56, 70, NULL, 'A', 0, 'C', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (665, 56, 71, NULL, 'BCD', 4, 'BCD', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (666, 56, 50, NULL, '东方故事的', 5, '答：主要有五个特点： \r\n采用数据模型表示复杂的数据结构；有较高的数据独立性；为用户提供了方便的用户接\r\n口；提供了四个方面的数据控制功能；对数据的操作以数据项为单位，增加了系统的灵活性。', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (667, 56, 72, NULL, 'ABCD', 4, 'ABCD', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (668, 56, 73, NULL, 'BCD', 0, 'ABCD', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (669, 56, 74, NULL, 'ABC', 0, 'ACD', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (670, 56, 53, NULL, '1,2,3', 2, '硬件,软件,计算机应用', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (671, 56, 75, NULL, 'BCD', 0, 'ABC', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (672, 56, 54, NULL, '1,2,3', 2.5, '程序设计,数据,数据', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (673, 56, 76, NULL, '1', 0, '0', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (674, 56, 55, NULL, '1', 1, '设备', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (675, 56, 77, NULL, '0', 0, '1', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (676, 56, 56, NULL, '2,3', 2, '数据的传输,格式的转换', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (677, 56, 78, NULL, '1', 2, '1', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (678, 56, 57, NULL, '1', 1, '数据 ', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (679, 56, 79, NULL, '0', 2, '0', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (680, 56, 80, NULL, '1', 2, '1', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (681, 56, 82, NULL, '第三方给对方', 6, '解：(1) ER图如图5.2所示。 \r\n公司编号 \r\n仓库编号 \r\n公司名 \r\n地址 \r\n公司 \r\n1 \r\n隶属 \r\n仓库名 \r\nN \r\n仓库 \r\n地址 \r\n1 \r\n聘用 \r\n聘期 \r\n工资 \r\nN \r\n职工 \r\n职工编号 \r\n姓名 \r\n图5.2 \r\n（2）这个ER图可转换3个关系模式： \r\n性别 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--61 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--62 \r\n  公司（公司编号，公司名，地址） \r\n  仓库（仓库编号，仓库名，地址，公司编号） \r\n  职工（职工编号，姓名，性别，仓库编号，聘期，工资）', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (682, 56, 61, NULL, 'B', 0, 'D', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (683, 56, 83, NULL, '森岛帆高史蒂夫', 8, '答：对于SELECT语句中SELECT子句，若用“SELECT  DISTINCT”形式，则查询结\r\n果中不允许有重复元组；若不写DISTINCT字样，则查询结果中允许出现重复元组。', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (684, 56, 62, NULL, 'D', 0, 'C', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (685, 56, 63, NULL, 'A', 0, 'B', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (686, 56, 64, NULL, 'B', 0, 'D', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (687, 56, 65, NULL, 'A', 0, 'D', '2025-03-13 00:49:59', '2025-03-13 00:50:49', 0);
INSERT INTO `score_detail` VALUES (688, 57, 66, NULL, 'D', 2, 'D', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (689, 57, 67, NULL, 'A', 0, 'D', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (690, 57, 68, NULL, 'C', 2, 'C', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (691, 57, 69, NULL, 'B', 0, 'D', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (692, 57, 48, NULL, '沙发斯蒂芬', 5, '答：主要有三个缺陷：数据冗余；数据不一致性；数据联系弱。 \r\n例如学校里教务处、财务处、保健处建立的文件中都有学生详细资料，譬如联系电话，\r\n家庭住址等。这就是“数据”冗余；如果某个学生搬家，就要修改三个部门文件中的数据，\r\n否则会引起同一数据在三个部门中不一致；产生上述问题的原因是这三个部门的文件中数据\r\n没有联系。', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (693, 57, 49, NULL, '舒舒服服', 5, '答：进入数据库阶段的标志是20世纪60年代末发生的三件事件： \r\n·1968 年IBM公司研制的IMS系统是一个典型的层次DBS； \r\n·1969 年美国CODASYL组织DBTG报告，提出网状DBS的概念； \r\n·1970 年美国IBM公司的E.F.Codd发表论文，提出关系模型的思想。', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (694, 57, 70, NULL, 'A', 0, 'C', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (695, 57, 71, NULL, 'BCD', 4, 'BCD', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (696, 57, 50, NULL, '帝国时代', 5, '答：主要有五个特点： \r\n采用数据模型表示复杂的数据结构；有较高的数据独立性；为用户提供了方便的用户接\r\n口；提供了四个方面的数据控制功能；对数据的操作以数据项为单位，增加了系统的灵活性。', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (697, 57, 72, NULL, 'ABCD', 4, 'ABCD', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (698, 57, 73, NULL, 'ABCD', 4, 'ABCD', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (699, 57, 74, NULL, 'BCD', 0, 'ACD', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (700, 57, 53, NULL, '2,2,3', 3, '硬件,软件,计算机应用', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (701, 57, 75, NULL, 'ABC', 4, 'ABC', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (702, 57, 54, NULL, '3,4,4', 3, '程序设计,数据,数据', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (703, 57, 76, NULL, '1', 0, '0', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (704, 57, 55, NULL, '2', 1, '设备', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (705, 57, 77, NULL, '0', 0, '1', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (706, 57, 56, NULL, '2', 2, '数据的传输,格式的转换', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (707, 57, 78, NULL, '1', 2, '1', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (708, 57, 57, NULL, '23', 1, '数据 ', '2025-03-13 00:53:01', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (709, 57, 79, NULL, '1', 0, '0', '2025-03-13 00:53:02', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (710, 57, 80, NULL, '0', 0, '1', '2025-03-13 00:53:02', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (711, 57, 82, NULL, '艾师傅as', 12, '解：(1) ER图如图5.2所示。 \r\n公司编号 \r\n仓库编号 \r\n公司名 \r\n地址 \r\n公司 \r\n1 \r\n隶属 \r\n仓库名 \r\nN \r\n仓库 \r\n地址 \r\n1 \r\n聘用 \r\n聘期 \r\n工资 \r\nN \r\n职工 \r\n职工编号 \r\n姓名 \r\n图5.2 \r\n（2）这个ER图可转换3个关系模式： \r\n性别 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--61 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--62 \r\n  公司（公司编号，公司名，地址） \r\n  仓库（仓库编号，仓库名，地址，公司编号） \r\n  职工（职工编号，姓名，性别，仓库编号，聘期，工资）', '2025-03-13 00:53:02', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (712, 57, 61, NULL, 'B', 0, 'D', '2025-03-13 00:53:02', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (713, 57, 83, NULL, '阿斯顿发生', 13, '答：对于SELECT语句中SELECT子句，若用“SELECT  DISTINCT”形式，则查询结\r\n果中不允许有重复元组；若不写DISTINCT字样，则查询结果中允许出现重复元组。', '2025-03-13 00:53:02', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (714, 57, 62, NULL, 'A', 0, 'C', '2025-03-13 00:53:02', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (715, 57, 63, NULL, 'C', 0, 'B', '2025-03-13 00:53:02', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (716, 57, 64, NULL, 'A', 0, 'D', '2025-03-13 00:53:02', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (717, 57, 65, NULL, 'C', 0, 'D', '2025-03-13 00:53:02', '2025-03-13 00:53:41', 0);
INSERT INTO `score_detail` VALUES (718, 58, 66, NULL, 'B', 0, 'D', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (719, 58, 67, NULL, 'B', 0, 'D', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (720, 58, 68, NULL, 'C', 2, 'C', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (721, 58, 69, NULL, 'A', 0, 'D', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (722, 58, 48, NULL, '23沙发是', 3.5, '答：主要有三个缺陷：数据冗余；数据不一致性；数据联系弱。 \r\n例如学校里教务处、财务处、保健处建立的文件中都有学生详细资料，譬如联系电话，\r\n家庭住址等。这就是“数据”冗余；如果某个学生搬家，就要修改三个部门文件中的数据，\r\n否则会引起同一数据在三个部门中不一致；产生上述问题的原因是这三个部门的文件中数据\r\n没有联系。', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (723, 58, 49, NULL, '甘肃省分公司', 5, '答：进入数据库阶段的标志是20世纪60年代末发生的三件事件： \r\n·1968 年IBM公司研制的IMS系统是一个典型的层次DBS； \r\n·1969 年美国CODASYL组织DBTG报告，提出网状DBS的概念； \r\n·1970 年美国IBM公司的E.F.Codd发表论文，提出关系模型的思想。', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (724, 58, 70, NULL, 'C', 2, 'C', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (725, 58, 71, NULL, 'ABC', 0, 'BCD', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (726, 58, 50, NULL, '顺丰到付', 4, '答：主要有五个特点： \r\n采用数据模型表示复杂的数据结构；有较高的数据独立性；为用户提供了方便的用户接\r\n口；提供了四个方面的数据控制功能；对数据的操作以数据项为单位，增加了系统的灵活性。', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (727, 58, 72, NULL, 'ABCD', 4, 'ABCD', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (728, 58, 73, NULL, 'BC', 0, 'ABCD', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (729, 58, 74, NULL, 'ABC', 0, 'ACD', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (730, 58, 53, NULL, '2,2,3', 1, '硬件,软件,计算机应用', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (731, 58, 75, NULL, 'BC', 0, 'ABC', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (732, 58, 54, NULL, '2,3,2', 1.5, '程序设计,数据,数据', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (733, 58, 76, NULL, '0', 2, '0', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (734, 58, 55, NULL, '3', 1, '设备', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (735, 58, 77, NULL, '1', 2, '1', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (736, 58, 56, NULL, '22,3', 2, '数据的传输,格式的转换', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (737, 58, 78, NULL, '0', 0, '1', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (738, 58, 57, NULL, '3', 1, '数据 ', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (739, 58, 79, NULL, '1', 0, '0', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (740, 58, 80, NULL, '0', 0, '1', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (741, 58, 82, NULL, 'http://192.168.164.128:9000/exam-bucket/20250313/13b3f0ab-c66e-410b-bfb3-09571795b08c-Linux系统命令.pdf', 6, '解：(1) ER图如图5.2所示。 \r\n公司编号 \r\n仓库编号 \r\n公司名 \r\n地址 \r\n公司 \r\n1 \r\n隶属 \r\n仓库名 \r\nN \r\n仓库 \r\n地址 \r\n1 \r\n聘用 \r\n聘期 \r\n工资 \r\nN \r\n职工 \r\n职工编号 \r\n姓名 \r\n图5.2 \r\n（2）这个ER图可转换3个关系模式： \r\n性别 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--61 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--62 \r\n  公司（公司编号，公司名，地址） \r\n  仓库（仓库编号，仓库名，地址，公司编号） \r\n  职工（职工编号，姓名，性别，仓库编号，聘期，工资）', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (742, 58, 61, NULL, 'B', 0, 'D', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (743, 58, 83, NULL, '水电费是的', 9, '答：对于SELECT语句中SELECT子句，若用“SELECT  DISTINCT”形式，则查询结\r\n果中不允许有重复元组；若不写DISTINCT字样，则查询结果中允许出现重复元组。', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (744, 58, 62, NULL, 'D', 0, 'C', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (745, 58, 63, NULL, 'A', 0, 'B', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (746, 58, 64, NULL, 'B', 0, 'D', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (747, 58, 65, NULL, 'A', 0, 'D', '2025-03-13 07:44:07', '2025-03-13 07:51:59', 0);
INSERT INTO `score_detail` VALUES (748, 59, 66, NULL, 'C', 0, 'D', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (749, 59, 67, NULL, 'A', 0, 'D', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (750, 59, 68, NULL, 'D', 0, 'C', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (751, 59, 69, NULL, 'B', 0, 'D', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (752, 59, 48, NULL, '东方闪电', 3, '答：主要有三个缺陷：数据冗余；数据不一致性；数据联系弱。 \r\n例如学校里教务处、财务处、保健处建立的文件中都有学生详细资料，譬如联系电话，\r\n家庭住址等。这就是“数据”冗余；如果某个学生搬家，就要修改三个部门文件中的数据，\r\n否则会引起同一数据在三个部门中不一致；产生上述问题的原因是这三个部门的文件中数据\r\n没有联系。', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (753, 59, 49, NULL, '史蒂夫', 4, '答：进入数据库阶段的标志是20世纪60年代末发生的三件事件： \r\n·1968 年IBM公司研制的IMS系统是一个典型的层次DBS； \r\n·1969 年美国CODASYL组织DBTG报告，提出网状DBS的概念； \r\n·1970 年美国IBM公司的E.F.Codd发表论文，提出关系模型的思想。', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (754, 59, 70, NULL, 'A', 0, 'C', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (755, 59, 71, NULL, 'BCD', 4, 'BCD', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (756, 59, 50, NULL, '史蒂夫', 5, '答：主要有五个特点： \r\n采用数据模型表示复杂的数据结构；有较高的数据独立性；为用户提供了方便的用户接\r\n口；提供了四个方面的数据控制功能；对数据的操作以数据项为单位，增加了系统的灵活性。', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (757, 59, 72, NULL, 'ABCD', 4, 'ABCD', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (758, 59, 73, NULL, 'BCD', 0, 'ABCD', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (759, 59, 74, NULL, 'ABC', 0, 'ACD', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (760, 59, 53, NULL, '2,2,3', 1, '硬件,软件,计算机应用', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (761, 59, 75, NULL, 'ABC', 4, 'ABC', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (762, 59, 54, NULL, '32,,3', 1.5, '程序设计,数据,数据', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (763, 59, 76, NULL, '1', 0, '0', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (764, 59, 55, NULL, '1', 1, '设备', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (765, 59, 77, NULL, '0', 0, '1', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (766, 59, 56, NULL, '3,3', 1, '数据的传输,格式的转换', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (767, 59, 78, NULL, '1', 2, '1', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (768, 59, 57, NULL, '3', 1, '数据 ', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (769, 59, 79, NULL, '0', 2, '0', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (770, 59, 80, NULL, '0', 0, '1', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (771, 59, 82, NULL, 'http://192.168.164.128:9000/exam-bucket/20250313/2829456d-c8f1-4b89-a80a-86fe49ff9e23-Linux系统命令.pdf', 8, '解：(1) ER图如图5.2所示。 \r\n公司编号 \r\n仓库编号 \r\n公司名 \r\n地址 \r\n公司 \r\n1 \r\n隶属 \r\n仓库名 \r\nN \r\n仓库 \r\n地址 \r\n1 \r\n聘用 \r\n聘期 \r\n工资 \r\nN \r\n职工 \r\n职工编号 \r\n姓名 \r\n图5.2 \r\n（2）这个ER图可转换3个关系模式： \r\n性别 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--61 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--62 \r\n  公司（公司编号，公司名，地址） \r\n  仓库（仓库编号，仓库名，地址，公司编号） \r\n  职工（职工编号，姓名，性别，仓库编号，聘期，工资）', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (772, 59, 61, NULL, 'A', 0, 'D', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (773, 59, 83, NULL, '水岸东方', 9, '答：对于SELECT语句中SELECT子句，若用“SELECT  DISTINCT”形式，则查询结\r\n果中不允许有重复元组；若不写DISTINCT字样，则查询结果中允许出现重复元组。', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (774, 59, 62, NULL, 'C', 2, 'C', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (775, 59, 63, NULL, 'D', 0, 'B', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (776, 59, 64, NULL, 'A', 0, 'D', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (777, 59, 65, NULL, 'B', 0, 'D', '2025-03-13 07:50:17', '2025-03-13 07:51:12', 0);
INSERT INTO `score_detail` VALUES (778, 60, 66, NULL, 'A', 0, 'D', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (779, 60, 67, NULL, 'C', 0, 'D', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (780, 60, 68, NULL, 'B', 0, 'C', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (781, 60, 69, NULL, 'A', 0, 'D', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (782, 60, 48, NULL, '啊实打实', 4, '答：主要有三个缺陷：数据冗余；数据不一致性；数据联系弱。 \r\n例如学校里教务处、财务处、保健处建立的文件中都有学生详细资料，譬如联系电话，\r\n家庭住址等。这就是“数据”冗余；如果某个学生搬家，就要修改三个部门文件中的数据，\r\n否则会引起同一数据在三个部门中不一致；产生上述问题的原因是这三个部门的文件中数据\r\n没有联系。', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (783, 60, 49, NULL, '啊实打实', 5, '答：进入数据库阶段的标志是20世纪60年代末发生的三件事件： \r\n·1968 年IBM公司研制的IMS系统是一个典型的层次DBS； \r\n·1969 年美国CODASYL组织DBTG报告，提出网状DBS的概念； \r\n·1970 年美国IBM公司的E.F.Codd发表论文，提出关系模型的思想。', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (784, 60, 70, NULL, 'C', 2, 'C', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (785, 60, 71, NULL, 'BCD', 4, 'BCD', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (786, 60, 50, NULL, '阿萨德', 5, '答：主要有五个特点： \r\n采用数据模型表示复杂的数据结构；有较高的数据独立性；为用户提供了方便的用户接\r\n口；提供了四个方面的数据控制功能；对数据的操作以数据项为单位，增加了系统的灵活性。', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (787, 60, 72, NULL, 'ABCD', 4, 'ABCD', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (788, 60, 73, NULL, 'ABC', 0, 'ABCD', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (789, 60, 74, NULL, 'ACD', 4, 'ACD', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (790, 60, 53, NULL, '122,2,3', 3, '硬件,软件,计算机应用', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (791, 60, 75, NULL, 'ABC', 4, 'ABC', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (792, 60, 54, NULL, '1,2,3', 3, '程序设计,数据,数据', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (793, 60, 76, NULL, '1', 0, '0', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (794, 60, 55, NULL, '1', 1, '设备', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (795, 60, 77, NULL, '1', 2, '1', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (796, 60, 56, NULL, '23,3', 2, '数据的传输,格式的转换', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (797, 60, 78, NULL, '0', 0, '1', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (798, 60, 57, NULL, '2', 1, '数据 ', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (799, 60, 79, NULL, '0', 2, '0', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (800, 60, 80, NULL, '0', 0, '1', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (801, 60, 82, NULL, '啊实打实', 12, '解：(1) ER图如图5.2所示。 \r\n公司编号 \r\n仓库编号 \r\n公司名 \r\n地址 \r\n公司 \r\n1 \r\n隶属 \r\n仓库名 \r\nN \r\n仓库 \r\n地址 \r\n1 \r\n聘用 \r\n聘期 \r\n工资 \r\nN \r\n职工 \r\n职工编号 \r\n姓名 \r\n图5.2 \r\n（2）这个ER图可转换3个关系模式： \r\n性别 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--61 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--62 \r\n  公司（公司编号，公司名，地址） \r\n  仓库（仓库编号，仓库名，地址，公司编号） \r\n  职工（职工编号，姓名，性别，仓库编号，聘期，工资）', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (802, 60, 61, NULL, 'A', 0, 'D', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (803, 60, 83, NULL, '阿斯顿撒', 12, '答：对于SELECT语句中SELECT子句，若用“SELECT  DISTINCT”形式，则查询结\r\n果中不允许有重复元组；若不写DISTINCT字样，则查询结果中允许出现重复元组。', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (804, 60, 62, NULL, 'C', 2, 'C', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (805, 60, 63, NULL, 'B', 2, 'B', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (806, 60, 64, NULL, 'D', 2, 'D', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (807, 60, 65, NULL, 'B', 0, 'D', '2025-03-13 13:19:36', '2025-03-13 13:20:37', 0);
INSERT INTO `score_detail` VALUES (808, 61, 66, NULL, 'C', 0, 'D', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (809, 61, 67, NULL, 'D', 2, 'D', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (810, 61, 68, NULL, 'A', 0, 'C', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (811, 61, 69, NULL, 'C', 0, 'D', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (812, 61, 48, NULL, 'sada', 4, '答：主要有三个缺陷：数据冗余；数据不一致性；数据联系弱。 \r\n例如学校里教务处、财务处、保健处建立的文件中都有学生详细资料，譬如联系电话，\r\n家庭住址等。这就是“数据”冗余；如果某个学生搬家，就要修改三个部门文件中的数据，\r\n否则会引起同一数据在三个部门中不一致；产生上述问题的原因是这三个部门的文件中数据\r\n没有联系。', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (813, 61, 49, NULL, 'sadf', 4, '答：进入数据库阶段的标志是20世纪60年代末发生的三件事件： \r\n·1968 年IBM公司研制的IMS系统是一个典型的层次DBS； \r\n·1969 年美国CODASYL组织DBTG报告，提出网状DBS的概念； \r\n·1970 年美国IBM公司的E.F.Codd发表论文，提出关系模型的思想。', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (814, 61, 70, NULL, 'B', 0, 'C', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (815, 61, 71, NULL, 'CD', 0, 'BCD', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (816, 61, 50, NULL, 'sdf', 3, '答：主要有五个特点： \r\n采用数据模型表示复杂的数据结构；有较高的数据独立性；为用户提供了方便的用户接\r\n口；提供了四个方面的数据控制功能；对数据的操作以数据项为单位，增加了系统的灵活性。', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (817, 61, 72, NULL, 'ABCD', 0, 'ABCD', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (818, 61, 73, NULL, 'ABCD', 0, 'ABCD', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (819, 61, 52, NULL, 'sdf', 5, '答：ER模型直接表示实体类型及实体间联系，与计算机系统无关，充分反映用户的需求，\r\n用户容易理解。 \r\n层次模型的数据结构为树结构，记录之间联系通过指针实现，查询较快，但DML属于过\r\n程化的，操作复杂。 \r\n网状模型的数据结构为有向图，记录之间联系通过指针实现，查询较快，并且容易实现\r\nM:N联系，但DML属于过程化的语言，编程较复杂。 \r\n关系模型的数据结构为二维表格，容易为初学者理解。记录之间联系通过关键码实现。\r\nDML属于非过程化语言，编程较简单。 \r\n面向对象模型能完整描述现实世界的数据结构，具有丰富的表达能力，能表达嵌套、递\r\n归的数据结构。但涉及的知识面较广，用户较难理解，这种模型尚未普及。 ', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (820, 61, 74, NULL, 'ABCD', 0, 'ACD', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (821, 61, 53, NULL, '1,2', 3, '硬件,软件,计算机应用', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (822, 61, 75, NULL, 'ABCD', 0, 'ABC', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (823, 61, 54, NULL, '2,,3', 2.5, '程序设计,数据,数据', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (824, 61, 76, NULL, '1', 0, '0', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (825, 61, 55, NULL, '1', 1, '设备', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (826, 61, 77, NULL, '0', 0, '1', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (827, 61, 56, NULL, '1', 2, '数据的传输,格式的转换', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (828, 61, 78, NULL, '1', 2, '1', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (829, 61, 57, NULL, '2', 1, '数据 ', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (830, 61, 79, NULL, '0', 2, '0', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (831, 61, 80, NULL, '0', 0, '1', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (832, 61, 82, NULL, 'http://192.168.164.128:9000/exam-bucket/20250316/5f6fe726-211a-4331-963b-429096d3ee78-面试题.pdf', 9, '解：(1) ER图如图5.2所示。 \r\n公司编号 \r\n仓库编号 \r\n公司名 \r\n地址 \r\n公司 \r\n1 \r\n隶属 \r\n仓库名 \r\nN \r\n仓库 \r\n地址 \r\n1 \r\n聘用 \r\n聘期 \r\n工资 \r\nN \r\n职工 \r\n职工编号 \r\n姓名 \r\n图5.2 \r\n（2）这个ER图可转换3个关系模式： \r\n性别 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--61 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--62 \r\n  公司（公司编号，公司名，地址） \r\n  仓库（仓库编号，仓库名，地址，公司编号） \r\n  职工（职工编号，姓名，性别，仓库编号，聘期，工资）', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (833, 61, 61, NULL, 'A', 0, 'D', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (834, 61, 83, NULL, 'sdf', 9, '答：对于SELECT语句中SELECT子句，若用“SELECT  DISTINCT”形式，则查询结\r\n果中不允许有重复元组；若不写DISTINCT字样，则查询结果中允许出现重复元组。', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (835, 61, 62, NULL, 'B', 0, 'C', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (836, 61, 63, NULL, 'C', 0, 'B', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (837, 61, 64, NULL, 'D', 2, 'D', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (838, 61, 65, NULL, 'B', 0, 'D', '2025-03-16 23:20:03', '2025-03-16 23:21:35', 0);
INSERT INTO `score_detail` VALUES (839, 62, 66, NULL, 'A', 0, 'D', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (840, 62, 67, NULL, 'B', 0, 'D', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (841, 62, 68, NULL, 'C', 2, 'C', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (842, 62, 69, NULL, 'A', 0, 'D', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (843, 62, 48, NULL, '阿萨德', 2, '答：主要有三个缺陷：数据冗余；数据不一致性；数据联系弱。 \r\n例如学校里教务处、财务处、保健处建立的文件中都有学生详细资料，譬如联系电话，\r\n家庭住址等。这就是“数据”冗余；如果某个学生搬家，就要修改三个部门文件中的数据，\r\n否则会引起同一数据在三个部门中不一致；产生上述问题的原因是这三个部门的文件中数据\r\n没有联系。', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (844, 62, 49, NULL, '阿萨德', 1, '答：进入数据库阶段的标志是20世纪60年代末发生的三件事件： \r\n·1968 年IBM公司研制的IMS系统是一个典型的层次DBS； \r\n·1969 年美国CODASYL组织DBTG报告，提出网状DBS的概念； \r\n·1970 年美国IBM公司的E.F.Codd发表论文，提出关系模型的思想。', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (845, 62, 70, NULL, 'C', 2, 'C', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (846, 62, 71, NULL, 'ABCD', 0, 'BCD', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (847, 62, 50, NULL, '阿萨德', 4, '答：主要有五个特点： \r\n采用数据模型表示复杂的数据结构；有较高的数据独立性；为用户提供了方便的用户接\r\n口；提供了四个方面的数据控制功能；对数据的操作以数据项为单位，增加了系统的灵活性。', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (848, 62, 72, NULL, 'BCD', 0, 'ABCD', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (849, 62, 51, NULL, '阿萨德', 3, '答：DDBS主要有三个特点： \r\n·数据物理上分布在各地，但逻辑上是一个整体； \r\n·每个场地既可以执行局部应用，也可以执行全局应用； \r\n·各地的计算机由数据通信网络相连接。 \r\n面向对象数据系统主要有两个特点： \r\n·面向对象数据模型能完整地描述现实世界的数据结构，能表达数据间嵌套、递归的联\r\n系。 \r\n·具有面向对象技术的封装性和继承性的特点，提高了软件的可重用性。', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (850, 62, 73, NULL, 'ABCD', 4, 'ABCD', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (851, 62, 74, NULL, 'ABCD', 0, 'ACD', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (852, 62, 75, NULL, 'ABCD', 0, 'ABC', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (853, 62, 76, NULL, '0', 2, '0', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (854, 62, 55, NULL, '2', 1, '设备', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (855, 62, 77, NULL, '1', 2, '1', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (856, 62, 56, NULL, '2,2', 2, '数据的传输,格式的转换', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (857, 62, 78, NULL, '0', 0, '1', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (858, 62, 13, NULL, '阿萨德', 2, '答：SQL的SELECT语句的基本句法来自于关系代数表达式πL（ςF（R1×„×Rm）），\r\n并且SQL中有并（UNION）、交（INTERSECT）和差（EXCEPT）等操作，因此 SQL\r\n具有关系代数特点。 \r\nSELECT 语句中出现的基本表名，都应该理解成基本表中的元组变量，而列名应理解\r\n成元组分量，这样SQL就具有了元组演算的特点。', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (859, 62, 57, NULL, '2', 1, '数据 ', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (860, 62, 79, NULL, '1', 0, '0', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (861, 62, 58, NULL, '3', 1, '数据字典（DD）', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (862, 62, 59, NULL, '1,,,3', 3, '集中式,C/S式,并行式,分布式', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (863, 62, 7, NULL, '阿萨德', 2, '答：对于SELECT语句中SELECT子句，若用“SELECT  DISTINCT”形式，则查询结\r\n果中不允许有重复元组；若不写DISTINCT字样，则查询结果中允许出现重复元组。', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (864, 62, 80, NULL, '0', 0, '1', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (865, 62, 60, NULL, '1', 1, '应用程序 ', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (866, 62, 61, NULL, 'A', 0, 'D', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (867, 62, 62, NULL, 'B', 0, 'C', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (868, 62, 63, NULL, 'B', 2, 'B', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (869, 62, 64, NULL, 'C', 0, 'D', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (870, 62, 65, NULL, 'B', 0, 'D', '2025-03-16 23:51:45', '2025-03-16 23:53:25', 0);
INSERT INTO `score_detail` VALUES (871, 63, 66, NULL, 'D', 2, 'D', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (872, 63, 67, NULL, 'C', 0, 'D', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (873, 63, 46, NULL, '3', 0.5, '和数据资源组成的系统，即采用数据库技术的计算机系统。 ', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (874, 63, 68, NULL, 'D', 0, 'C', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (875, 63, 47, NULL, '3', 0.5, '答：人工管理阶段主要有四个特点：数据不保存在计算机内；没有专用的软件对数据进行\r\n管理；只有程序的概念，没有文件的概念；数据面向程序。 \r\n文件系统阶段主要有五个特点： 数据以“文件”形式长期保存；数据的逻辑结构与物理\r\n结构有了区别；文件组织已多样化；数据面向应用；对数据的操作以记录为单位。 ', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (876, 63, 69, NULL, 'A', 0, 'D', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (877, 63, 48, NULL, '3', 0.5, '答：主要有三个缺陷：数据冗余；数据不一致性；数据联系弱。 \r\n例如学校里教务处、财务处、保健处建立的文件中都有学生详细资料，譬如联系电话，\r\n家庭住址等。这就是“数据”冗余；如果某个学生搬家，就要修改三个部门文件中的数据，\r\n否则会引起同一数据在三个部门中不一致；产生上述问题的原因是这三个部门的文件中数据\r\n没有联系。', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (878, 63, 70, NULL, 'A', 0, 'C', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (879, 63, 71, NULL, 'D', 0, 'BCD', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (880, 63, 72, NULL, 'C', 0, 'ABCD', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (881, 63, 51, NULL, '娥', 0.5, '答：DDBS主要有三个特点： \r\n·数据物理上分布在各地，但逻辑上是一个整体； \r\n·每个场地既可以执行局部应用，也可以执行全局应用； \r\n·各地的计算机由数据通信网络相连接。 \r\n面向对象数据系统主要有两个特点： \r\n·面向对象数据模型能完整地描述现实世界的数据结构，能表达数据间嵌套、递归的联\r\n系。 \r\n·具有面向对象技术的封装性和继承性的特点，提高了软件的可重用性。', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (882, 63, 73, NULL, 'D', 0, 'ABCD', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (883, 63, 74, NULL, 'C', 0, 'ACD', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (884, 63, 53, NULL, '的,3,3', 2, '硬件,软件,计算机应用', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (885, 63, 75, NULL, 'CD', 0, 'ABC', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (886, 63, 54, NULL, '3,3,3', 0.5, '程序设计,数据,数据', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (887, 63, 76, NULL, '0', 2, '0', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (888, 63, 55, NULL, '3', 1, '设备', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (889, 63, 77, NULL, '0', 0, '1', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (890, 63, 78, NULL, '0', 0, '1', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (891, 63, 57, NULL, '3', 1, '数据 ', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (892, 63, 79, NULL, '0', 2, '0', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (893, 63, 58, NULL, '3', 0.5, '数据字典（DD）', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (894, 63, 80, NULL, '0', 0, '1', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (895, 63, 60, NULL, '3', 0.5, '应用程序 ', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (896, 63, 61, NULL, 'B', 0, 'D', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (897, 63, 83, NULL, '3', 0.5, '答：对于SELECT语句中SELECT子句，若用“SELECT  DISTINCT”形式，则查询结\r\n果中不允许有重复元组；若不写DISTINCT字样，则查询结果中允许出现重复元组。', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (898, 63, 62, NULL, 'C', 2, 'C', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (899, 63, 84, NULL, 'http://192.168.164.128:9000/exam-bucket/20250317/d3968109-4755-4239-a2bf-23895c1ab728-Linux系统命令.pdf', 0.5, '答：SQL的SELECT语句的基本句法来自于关系代数表达式πL（ςF（R1×„×Rm）），\r\n并且SQL中有并（UNION）、交（INTERSECT）和差（EXCEPT）等操作，因此 SQL\r\n具有关系代数特点。 \r\nSELECT 语句中出现的基本表名，都应该理解成基本表中的元组变量，而列名应理解\r\n成元组分量，这样SQL就具有了元组演算的特点。', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (900, 63, 63, NULL, 'D', 0, 'B', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (901, 63, 64, NULL, 'C', 0, 'D', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (902, 63, 65, NULL, 'C', 0, 'D', '2025-03-17 09:20:03', '2025-03-17 09:21:44', 0);
INSERT INTO `score_detail` VALUES (903, 64, 66, NULL, 'C', NULL, 'D', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (904, 64, 67, NULL, 'D', NULL, 'D', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (905, 64, 68, NULL, 'B', NULL, 'C', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (906, 64, 69, NULL, 'B', NULL, 'D', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (907, 64, 48, NULL, '2', NULL, '答：主要有三个缺陷：数据冗余；数据不一致性；数据联系弱。 \r\n例如学校里教务处、财务处、保健处建立的文件中都有学生详细资料，譬如联系电话，\r\n家庭住址等。这就是“数据”冗余；如果某个学生搬家，就要修改三个部门文件中的数据，\r\n否则会引起同一数据在三个部门中不一致；产生上述问题的原因是这三个部门的文件中数据\r\n没有联系。', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (908, 64, 49, NULL, '22', NULL, '答：进入数据库阶段的标志是20世纪60年代末发生的三件事件： \r\n·1968 年IBM公司研制的IMS系统是一个典型的层次DBS； \r\n·1969 年美国CODASYL组织DBTG报告，提出网状DBS的概念； \r\n·1970 年美国IBM公司的E.F.Codd发表论文，提出关系模型的思想。', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (909, 64, 70, NULL, 'C', NULL, 'C', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (910, 64, 71, NULL, 'BCD', NULL, 'BCD', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (911, 64, 72, NULL, 'ABCD', NULL, 'ABCD', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (912, 64, 51, NULL, '2', NULL, '答：DDBS主要有三个特点： \r\n·数据物理上分布在各地，但逻辑上是一个整体； \r\n·每个场地既可以执行局部应用，也可以执行全局应用； \r\n·各地的计算机由数据通信网络相连接。 \r\n面向对象数据系统主要有两个特点： \r\n·面向对象数据模型能完整地描述现实世界的数据结构，能表达数据间嵌套、递归的联\r\n系。 \r\n·具有面向对象技术的封装性和继承性的特点，提高了软件的可重用性。', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (913, 64, 73, NULL, 'BC', NULL, 'ABCD', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (914, 64, 52, NULL, '2', NULL, '答：ER模型直接表示实体类型及实体间联系，与计算机系统无关，充分反映用户的需求，\r\n用户容易理解。 \r\n层次模型的数据结构为树结构，记录之间联系通过指针实现，查询较快，但DML属于过\r\n程化的，操作复杂。 \r\n网状模型的数据结构为有向图，记录之间联系通过指针实现，查询较快，并且容易实现\r\nM:N联系，但DML属于过程化的语言，编程较复杂。 \r\n关系模型的数据结构为二维表格，容易为初学者理解。记录之间联系通过关键码实现。\r\nDML属于非过程化语言，编程较简单。 \r\n面向对象模型能完整描述现实世界的数据结构，具有丰富的表达能力，能表达嵌套、递\r\n归的数据结构。但涉及的知识面较广，用户较难理解，这种模型尚未普及。 ', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (915, 64, 74, NULL, 'AB', NULL, 'ACD', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (916, 64, 53, NULL, '2,2,2', NULL, '硬件,软件,计算机应用', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (917, 64, 75, NULL, 'BD', NULL, 'ABC', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (918, 64, 54, NULL, '2,4,3', NULL, '程序设计,数据,数据', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (919, 64, 76, NULL, '0', NULL, '0', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (920, 64, 77, NULL, '0', NULL, '1', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (921, 64, 56, NULL, '2,2', NULL, '数据的传输,格式的转换', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (922, 64, 78, NULL, '1', NULL, '1', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (923, 64, 57, NULL, '2', NULL, '数据 ', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (924, 64, 79, NULL, '0', NULL, '0', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (925, 64, 80, NULL, '1', NULL, '1', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (926, 64, 60, NULL, '2', NULL, '应用程序 ', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (927, 64, 82, NULL, '2', NULL, '解：(1) ER图如图5.2所示。 \r\n公司编号 \r\n仓库编号 \r\n公司名 \r\n地址 \r\n公司 \r\n1 \r\n隶属 \r\n仓库名 \r\nN \r\n仓库 \r\n地址 \r\n1 \r\n聘用 \r\n聘期 \r\n工资 \r\nN \r\n职工 \r\n职工编号 \r\n姓名 \r\n图5.2 \r\n（2）这个ER图可转换3个关系模式： \r\n性别 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--61 \r\n（2003/9/21）  （GJ-DA） （共2页）  目录--62 \r\n  公司（公司编号，公司名，地址） \r\n  仓库（仓库编号，仓库名，地址，公司编号） \r\n  职工（职工编号，姓名，性别，仓库编号，聘期，工资）', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (928, 64, 61, NULL, 'C', NULL, 'D', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (929, 64, 83, NULL, '2', NULL, '答：对于SELECT语句中SELECT子句，若用“SELECT  DISTINCT”形式，则查询结\r\n果中不允许有重复元组；若不写DISTINCT字样，则查询结果中允许出现重复元组。', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (930, 64, 62, NULL, 'B', NULL, 'C', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (931, 64, 63, NULL, 'C', NULL, 'B', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (932, 64, 64, NULL, 'B', NULL, 'D', '2025-03-29 13:40:55', NULL, 0);
INSERT INTO `score_detail` VALUES (933, 64, 65, NULL, 'D', NULL, 'D', '2025-03-29 13:40:55', NULL, 0);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '学生ID',
  `student_id` bigint(20) NULL DEFAULT NULL COMMENT '学号',
  `user_id` bigint(11) NULL DEFAULT NULL COMMENT '用户ID',
  `class_id` int(11) NULL DEFAULT NULL COMMENT '班级ID',
  `institution_id` int(11) NULL DEFAULT NULL COMMENT '学院ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`) USING BTREE,
  UNIQUE INDEX `student_id`(`student_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 25010001, 4, 1, 1, '2024-12-13 20:42:21', '2024-12-13 20:42:23', 0);
INSERT INTO `student` VALUES (2, 25010002, 5, 1, 1, '2024-12-13 22:32:46', '2024-12-13 22:32:46', 0);
INSERT INTO `student` VALUES (4, 25010003, 6, 1, 1, '2024-12-13 22:33:12', '2024-12-13 22:33:12', 0);
INSERT INTO `student` VALUES (6, 25010004, 8, 1, 1, '2025-01-17 16:59:34', NULL, 0);
INSERT INTO `student` VALUES (7, 25010005, 9, 1, 1, '2025-01-17 17:01:59', NULL, 0);
INSERT INTO `student` VALUES (8, 25010006, 10, 1, 1, '2025-01-17 17:02:14', NULL, 0);
INSERT INTO `student` VALUES (9, 25010007, 11, 1, 1, '2025-01-17 17:02:34', NULL, 0);
INSERT INTO `student` VALUES (10, 25010008, 12, 3, 1, '2025-01-17 17:02:51', NULL, 0);
INSERT INTO `student` VALUES (11, 25010009, 13, 3, 1, '2025-01-17 17:03:15', NULL, 0);
INSERT INTO `student` VALUES (12, 25010057, 14, 2, 1, '2025-01-21 17:44:25', NULL, 0);

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES (1, '数据结构与算法', '2024-12-14 15:07:21', NULL, 0);
INSERT INTO `subject` VALUES (2, '离散数学', '2024-12-14 21:13:47', '2024-12-14 21:41:19', 0);
INSERT INTO `subject` VALUES (3, '操作系统', '2024-12-23 17:42:21', NULL, 0);
INSERT INTO `subject` VALUES (4, '数据库系统教程', '2024-12-23 18:07:23', NULL, 0);
INSERT INTO `subject` VALUES (5, 'C语言', '2024-12-23 18:07:40', NULL, 0);
INSERT INTO `subject` VALUES (6, '高等数学', '2024-12-23 18:07:54', NULL, 0);
INSERT INTO `subject` VALUES (7, '软件测试', '2024-12-23 18:08:12', NULL, 0);
INSERT INTO `subject` VALUES (8, '软件工程导论', '2024-12-23 18:08:32', NULL, 0);
INSERT INTO `subject` VALUES (9, '计算机导论', '2024-12-23 18:08:51', NULL, 0);
INSERT INTO `subject` VALUES (10, '可视化与uml设计', '2024-12-23 18:09:20', '2025-01-22 09:35:52', 1);
INSERT INTO `subject` VALUES (11, '计算机网络', '2024-12-27 10:06:01', NULL, 0);
INSERT INTO `subject` VALUES (12, '微积分', '2025-01-22 09:22:15', '2025-01-22 09:36:28', 1);
INSERT INTO `subject` VALUES (14, '线性代数', '2025-01-22 09:36:01', '2025-01-22 09:36:30', 1);
INSERT INTO `subject` VALUES (15, '概率论', '2025-01-22 09:36:24', '2025-01-22 09:36:32', 1);
INSERT INTO `subject` VALUES (16, '计算机组成原理', '2025-01-23 17:33:29', NULL, 0);
INSERT INTO `subject` VALUES (17, '形势与政策', '2025-01-23 22:38:35', '2025-03-28 15:04:02', 1);
INSERT INTO `subject` VALUES (20, 'Python编程', '2025-04-01 21:38:52', NULL, 0);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '教师ID',
  `eno` bigint(20) NULL DEFAULT NULL,
  `user_id` bigint(11) NULL DEFAULT NULL COMMENT '用户ID',
  `institute_id` int(11) NULL DEFAULT NULL COMMENT '学院ID',
  `position` enum('1','2','3','4') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '\'助教\',\'讲师\',\'副教授\',\'教授\':1,2,3,4',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`) USING BTREE,
  UNIQUE INDEX `eno`(`eno`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, 21020001, 2, 1, '1', '2024-12-26 16:14:12', NULL, 0);
INSERT INTO `teacher` VALUES (2, 21020002, 7, 2, '2', '2024-12-26 16:14:08', NULL, 0);
INSERT INTO `teacher` VALUES (6, 2025020003, 21, 4, NULL, '2025-01-23 22:30:07', NULL, 0);
INSERT INTO `teacher` VALUES (7, 2025020004, 22, 6, NULL, '2025-01-23 22:30:30', NULL, 0);
INSERT INTO `teacher` VALUES (8, 2025020005, 23, 7, NULL, '2025-01-23 22:31:56', NULL, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '默认用户' COMMENT '姓名',
  `sex` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别（0：女，1：男）',
  `tel` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `pwd` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '123' COMMENT '密码',
  `card_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `role` enum('0','1','2') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色（0：管理员，1：老师，2：学生）',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `tel`(`tel`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '1', '15907903128', '1846105820@qq.com', '123', '360502200201223613', '0', '2025-01-02 19:47:06', '2024-12-14 22:12:24', 0);
INSERT INTO `user` VALUES (2, '老王', '1', '123', '123@qq.com', '123', NULL, '1', '2025-01-22 20:47:45', NULL, 0);
INSERT INTO `user` VALUES (4, '张三', '1', '1234', '1234@qq.com', '123', NULL, '2', '2024-12-25 17:32:04', NULL, 0);
INSERT INTO `user` VALUES (5, '李四', '1', '15907957545', '12345@qq.com', '123', '360502200201223613', '2', '2025-03-15 23:37:36', '2025-03-15 23:37:37', 0);
INSERT INTO `user` VALUES (6, '王五', '0', '13133779020', '123456@qq.com', '123', NULL, '2', '2025-01-03 18:14:06', NULL, 0);
INSERT INTO `user` VALUES (7, '老张', '1', '321', '321@qq.com', '123', NULL, '1', '2025-01-22 20:47:49', NULL, 0);
INSERT INTO `user` VALUES (8, '默认用户', '1', NULL, NULL, '123', NULL, '2', '2025-01-17 17:03:57', NULL, 0);
INSERT INTO `user` VALUES (9, '默认用户', '0', NULL, NULL, '123', NULL, '2', '2025-01-17 17:04:07', NULL, 0);
INSERT INTO `user` VALUES (10, '默认用户', '1', NULL, NULL, '123', NULL, '2', '2025-01-17 17:04:20', NULL, 0);
INSERT INTO `user` VALUES (11, '默认用户', '1', NULL, NULL, '123', NULL, '2', '2025-01-17 17:04:31', NULL, 0);
INSERT INTO `user` VALUES (12, '默认用户', '1', NULL, NULL, '123', NULL, '2', '2025-01-17 17:04:44', NULL, 0);
INSERT INTO `user` VALUES (13, '默认用户', '0', NULL, NULL, '123', NULL, '2', '2025-01-17 17:04:54', NULL, 0);
INSERT INTO `user` VALUES (14, '默认用户', '1', NULL, NULL, '123', NULL, '2', '2025-01-21 17:44:46', NULL, 0);
INSERT INTO `user` VALUES (21, '老李', NULL, NULL, NULL, '123', NULL, '1', '2025-01-23 22:30:07', NULL, 0);
INSERT INTO `user` VALUES (22, '老赵', NULL, NULL, NULL, '123', NULL, '1', '2025-01-23 22:30:30', NULL, 0);
INSERT INTO `user` VALUES (23, '老夏', NULL, NULL, NULL, '123', NULL, '1', '2025-01-23 22:31:55', NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
