-- MySQL dump 10.16  Distrib 10.2.12-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: social_blog
-- ------------------------------------------------------
-- Server version	10.2.12-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Column`
--

DROP TABLE IF EXISTS `Column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Column` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(100) DEFAULT NULL COMMENT '专题名称',
  `image` varchar(100) DEFAULT NULL COMMENT '专题图片',
  `c_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='文章专栏表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Column`
--

LOCK TABLES `Column` WRITE;
/*!40000 ALTER TABLE `Column` DISABLE KEYS */;
INSERT INTO `Column` VALUES (1,'Java','/images/3db5f5b6a73b4530bc1e26c872d053ad.jpeg','2017-11-26 17:58:12');
/*!40000 ALTER TABLE `Column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Column_paper`
--

DROP TABLE IF EXISTS `Column_paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Column_paper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paper_id` int(11) DEFAULT NULL,
  `Column_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Column_paper_paper_FK` (`paper_id`),
  KEY `Column_paper_Column_FK` (`Column_id`),
  CONSTRAINT `Column_paper_Column_FK` FOREIGN KEY (`Column_id`) REFERENCES `Column` (`id`),
  CONSTRAINT `Column_paper_paper_FK` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Column_paper`
--

LOCK TABLES `Column_paper` WRITE;
/*!40000 ALTER TABLE `Column_paper` DISABLE KEYS */;
INSERT INTO `Column_paper` VALUES (1,3,1),(2,4,1);
/*!40000 ALTER TABLE `Column_paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `email` varchar(100) DEFAULT NULL,
  `comments_content` text DEFAULT NULL COMMENT '评论内容',
  `comments_time` datetime DEFAULT NULL COMMENT '评论时间',
  `comments_paper` int(11) DEFAULT NULL COMMENT '评论文章',
  `comments_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `comments_paper_FK` (`comments_paper`),
  CONSTRAINT `comments_paper_FK` FOREIGN KEY (`comments_paper`) REFERENCES `paper` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,'2013894@qq.com','费城无法哦ijo家具佛i骄傲发d但是方式大概和发是否的方式方式公开偶家哦就哦近两年你你你空间你空间你你今年空间你可能从解决安抚偶家佛金额哦isj按考虑每次骄傲ijeoijfal卡就哦idfj的开发了你们你今年就了拉风。在爱积分','2018-01-01 10:00:00',3,1);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faves`
--

DROP TABLE IF EXISTS `faves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faves` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) DEFAULT NULL COMMENT 'user表id',
  `paper_id` int(11) DEFAULT NULL COMMENT 'paper表id',
  PRIMARY KEY (`id`),
  KEY `faves_paper_FK` (`paper_id`),
  KEY `faves_users_FK` (`user_id`),
  CONSTRAINT `faves_paper_FK` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `faves_users_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='文章收藏表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faves`
--

LOCK TABLES `faves` WRITE;
/*!40000 ALTER TABLE `faves` DISABLE KEYS */;
INSERT INTO `faves` VALUES (2,1,2),(3,2,3);
/*!40000 ALTER TABLE `faves` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friends` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `friend_id` int(11) DEFAULT NULL COMMENT '好友id',
  PRIMARY KEY (`id`),
  KEY `friends_users_user_FK` (`user_id`),
  KEY `friends_users_friend_FK` (`friend_id`),
  CONSTRAINT `friends_users_friend_FK` FOREIGN KEY (`friend_id`) REFERENCES `users` (`id`),
  CONSTRAINT `friends_users_user_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` VALUES (1,2,1),(2,1,2);
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paper`
--

DROP TABLE IF EXISTS `paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paper` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(100) DEFAULT NULL COMMENT '文章标题',
  `content` text DEFAULT NULL COMMENT '文章内容',
  `create_time` datetime DEFAULT NULL COMMENT '文章的创建时间',
  `mark` int(11) DEFAULT NULL COMMENT '点亮标记',
  `paper_image` varchar(100) DEFAULT NULL COMMENT '文章的图片',
  `author` int(11) DEFAULT NULL COMMENT '作者',
  `segment` text DEFAULT NULL COMMENT '文章的片段预览',
  `is_pass` varchar(100) DEFAULT NULL COMMENT '文章状态',
  PRIMARY KEY (`id`),
  KEY `paper_users_FK` (`author`),
  CONSTRAINT `paper_users_FK` FOREIGN KEY (`author`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='文章表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paper`
--

LOCK TABLES `paper` WRITE;
/*!40000 ALTER TABLE `paper` DISABLE KEYS */;
INSERT INTO `paper` VALUES (2,'新建文章','新建文章','2017-11-25 17:08:24',2,'/main/images/article1.jpg',1,'新建','审核通过'),(3,'代码整洁之道','代码整洁之道\n','2017-11-26 18:00:06',1,'/main/images/article4.jpg',1,'代码整洁之道','审核通过'),(4,'个人简介','my name is zhangsan 1212','2017-11-27 10:13:54',1,'/main/images/article3.jpg',2,'个人简介','审核通过');
/*!40000 ALTER TABLE `paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `permission_name` varchar(100) DEFAULT NULL COMMENT '权限名',
  `describe` varchar(100) DEFAULT NULL COMMENT '权限名称描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'can_write',NULL),(2,'can_manage',NULL);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名(英文)',
  `describe` varchar(100) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin','管理员'),(2,'vip','普通注册用户');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sysLog`
--

DROP TABLE IF EXISTS `sysLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sysLog` (
  `id` varchar(100) DEFAULT NULL COMMENT 'uuid',
  `descript` varchar(100) DEFAULT NULL COMMENT '描述',
  `logIp` varchar(100) DEFAULT NULL COMMENT 'ip地址',
  `createBy` varchar(100) DEFAULT NULL COMMENT 'session的用户',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志存储';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sysLog`
--

LOCK TABLES `sysLog` WRITE;
/*!40000 ALTER TABLE `sysLog` DISABLE KEYS */;
INSERT INTO `sysLog` VALUES (NULL,'用户登录','0:0:0:0:0:0:0:1',NULL,'2017-11-25 15:11:55'),(NULL,'用户登录','0:0:0:0:0:0:0:1',NULL,'2017-11-25 15:12:02'),(NULL,'用户登录','0:0:0:0:0:0:0:1',NULL,'2017-11-25 15:13:23'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-25 15:16:54'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-25 16:27:42'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-25 16:59:46'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-25 17:08:36'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-26 16:45:36'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-26 17:41:47'),(NULL,'用户登录','0:0:0:0:0:0:0:1','zhangsan','2017-11-26 18:02:55'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-26 18:12:37'),(NULL,'用户登录','0:0:0:0:0:0:0:1','zhangsan','2017-11-27 10:13:03'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 10:55:57'),(NULL,'用户登录','0:0:0:0:0:0:0:1','zhangsan','2017-11-27 10:59:32'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 11:02:22'),(NULL,'用户登录','0:0:0:0:0:0:0:1',NULL,'2017-11-27 11:10:21'),(NULL,'用户登录','0:0:0:0:0:0:0:1',NULL,'2017-11-27 11:10:57'),(NULL,'用户登录','0:0:0:0:0:0:0:1',NULL,'2017-11-27 11:14:57'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 11:17:30'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 11:22:56'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 11:27:41'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 11:28:03'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 11:28:27'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 11:29:05'),(NULL,'用户登录','0:0:0:0:0:0:0:1','zhangsan','2017-11-27 11:29:13'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 11:29:46'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 11:33:47'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 11:40:15'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 11:43:35'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 11:45:03'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 11:47:01'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 11:47:07'),(NULL,'用户登录','0:0:0:0:0:0:0:1','zhangsan','2017-11-27 11:49:19'),(NULL,'用户登录','0:0:0:0:0:0:0:1','zhangsan','2017-11-27 11:50:42'),(NULL,'用户登录','0:0:0:0:0:0:0:1','zhangsan','2017-11-27 11:53:42'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 11:57:12'),(NULL,'用户登录','0:0:0:0:0:0:0:1','zhangsan','2017-11-27 12:01:14'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 12:09:28'),(NULL,'用户登录','0:0:0:0:0:0:0:1','zhangsan','2017-11-27 12:09:49'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 12:32:42'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 12:34:44'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 12:35:35'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 13:43:18'),(NULL,'用户登录','0:0:0:0:0:0:0:1','zhangsan','2017-11-27 13:43:53'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-27 14:04:29'),(NULL,'用户登录','0:0:0:0:0:0:0:1','zhangsan','2017-11-27 14:04:52'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-28 11:50:27'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-28 14:18:15'),(NULL,'用户登录','0:0:0:0:0:0:0:1','zhangsan','2017-11-28 14:20:30'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-28 15:36:06'),(NULL,'用户登录','0:0:0:0:0:0:0:1','zhangsan','2017-11-28 15:36:18'),(NULL,'用户登录','0:0:0:0:0:0:0:1','zhangsan','2017-11-28 15:37:12'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-28 15:55:30'),(NULL,'用户登录','0:0:0:0:0:0:0:1','zhangsan','2017-11-28 15:55:40'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-29 09:21:48'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-29 09:23:49'),(NULL,'用户登录','0:0:0:0:0:0:0:1','zhangsan','2017-11-29 10:05:57'),(NULL,'用户登录','0:0:0:0:0:0:0:1','zhangsan','2017-11-29 11:52:40'),(NULL,'用户登录','0:0:0:0:0:0:0:1','zhangsan','2017-11-29 13:09:58'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-29 13:42:22'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-29 14:56:54'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-29 15:44:19'),(NULL,'用户登录','0:0:0:0:0:0:0:1','nanyin','2017-11-29 16:02:09');
/*!40000 ALTER TABLE `sysLog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tag_name` varchar(100) DEFAULT NULL COMMENT '标签名称',
  `paper_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tag_paper_FK` (`paper_id`),
  CONSTRAINT `tag_paper_FK` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='标签表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'文章',2),(2,'code',3),(3,'个人简介',4),(4,'宜家',4);
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userDetail`
--

DROP TABLE IF EXISTS `userDetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userDetail` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `position` varchar(100) DEFAULT NULL COMMENT '职位',
  `birthday` datetime DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `sketch` varchar(100) DEFAULT NULL COMMENT '简述',
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`detail_id`),
  KEY `userDetail_users_FK` (`user_id`),
  CONSTRAINT `userDetail_users_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户的增加信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userDetail`
--

LOCK TABLES `userDetail` WRITE;
/*!40000 ALTER TABLE `userDetail` DISABLE KEYS */;
INSERT INTO `userDetail` VALUES (1,'student','1995-09-19 00:00:00','北京','苍茫的天涯我的爱',1),(2,'老师','1995-09-12 00:00:00','天津','大智若愚',2);
/*!40000 ALTER TABLE `userDetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role_permission`
--

DROP TABLE IF EXISTS `user_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) DEFAULT NULL COMMENT 'user表外键',
  `permisssion_id` int(11) DEFAULT NULL COMMENT 'permission表外键',
  `role_id` int(11) DEFAULT NULL COMMENT 'role表外键',
  PRIMARY KEY (`id`),
  KEY `user_role_permission_users_FK` (`user_id`),
  KEY `user_role_permission_role_FK` (`role_id`),
  KEY `user_role_permission_permission_FK` (`permisssion_id`),
  CONSTRAINT `user_role_permission_permission_FK` FOREIGN KEY (`permisssion_id`) REFERENCES `permission` (`id`),
  CONSTRAINT `user_role_permission_role_FK` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `user_role_permission_users_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role_permission`
--

LOCK TABLES `user_role_permission` WRITE;
/*!40000 ALTER TABLE `user_role_permission` DISABLE KEYS */;
INSERT INTO `user_role_permission` VALUES (1,1,1,1),(2,1,2,1),(3,2,1,2),(4,3,1,2);
/*!40000 ALTER TABLE `user_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `login_name` varchar(100) DEFAULT NULL COMMENT '登录名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `real_name` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `sex` varchar(100) DEFAULT NULL COMMENT '性别',
  `head` varchar(100) DEFAULT NULL COMMENT '头像',
  `status` int(11) DEFAULT NULL COMMENT '账号状态 1 表示正在使用 0表示停用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'nanyin','123','2017-10-08 10:00:00','197713379@qq.com','南音','男','/images/head2.jpg',1),(2,'zhangsan','123','2017-11-02 10:22:10','1977713379@qq.com','张三','男','/images/head3.jpg',1),(3,'lisi','123','2017-12-04 08:03:40','1977713379@qq.com','李四','女','/images/head3.jpg',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'social_blog'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-02 19:48:05
