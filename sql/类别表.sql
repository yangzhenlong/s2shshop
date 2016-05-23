drop database if exists shop;

create database shop default character set utf8;

use shop;

drop table if exists category;

create table category
(
   /* 类别编号,自动增长 */
   id  int not null auto_increment,
   /* 类别名称 */
   type varchar(20),
   /* 类别是否为热点类别,热点类别才有可能显示在首页 */
   hot  bool default false,
   /* 设置类别编号为主键 */
   primary key (id)
);



insert into `category` (`id`, `type`, `hot`, `aid`) values('1','男士休闲','1','1');
insert into `category` (`id`, `type`, `hot`, `aid`) values('2','女式休闲','1','3');
insert into `category` (`id`, `type`, `hot`, `aid`) values('3','儿童休闲','1','2');
insert into `category` (`id`, `type`, `hot`, `aid`) values('4','111','0','3');
insert into `category` (`id`, `type`, `hot`, `aid`) values('5','测试的','0','1');
insert into `category` (`id`, `type`, `hot`, `aid`) values('6','测试的2','0','2');
insert into `category` (`id`, `type`, `hot`, `aid`) values('7','测试的2','0','1');
insert into `category` (`id`, `type`, `hot`, `aid`) values('8','测试的2','0',NULL);
insert into `category` (`id`, `type`, `hot`, `aid`) values('9','测试的2','0','1');
insert into `category` (`id`, `type`, `hot`, `aid`) values('10','测试的2','0',NULL);
insert into `category` (`id`, `type`, `hot`, `aid`) values('11','测试的2','0','1');
insert into `category` (`id`, `type`, `hot`, `aid`) values('12','测试的2','0','1');
insert into `category` (`id`, `type`, `hot`, `aid`) values('13','测试的2','0',NULL);
insert into `category` (`id`, `type`, `hot`, `aid`) values('14',NULL,'0','1');