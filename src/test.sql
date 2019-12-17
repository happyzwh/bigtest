

CREATE DATABASE IF NOT EXISTS test;
USE test;

DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `des` varchar(100) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE DATABASE IF NOT EXISTS oavideo;
USE oavideo;

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` int(11) NOT NULL auto_increment,
  `username` varchar(512) default NULL,
  `password` varchar(512) default NULL,
  `other` varchar(521) default NULL,
  `ctime` timestamp NULL default NULL,
  PRIMARY KEY  (`uid`)
) ENGINE=InnoDB  AUTO_INCREMENT=1  DEFAULT CHARSET=utf8;
