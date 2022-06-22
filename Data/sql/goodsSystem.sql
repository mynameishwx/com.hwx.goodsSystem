use goodsSystem;
drop table user;
drop table role;
drop table user_role;
drop table goods_order;
drop table role_power;
drop table power;
drop table session;
drop table shop;
drop table staff;
drop table message;
drop table goods;
#用户表
create  table  user(
                       id int  primary key  auto_increment,
                       user_Name varchar(50) not null,
                       user_Password varchar(50) not null ,
                       sex  int ,
                       age  int ,
                       salt  varchar(10)  not null ,
                       pet_Name varchar(50) ,
                       image_Url varchar(200),
                       dwell varchar(50),
                       signature varchar(200),
                       create_Time datetime,
                       update_Time datetime
);
#用户角色表
create  table  user_role(
                            id int primary key  auto_increment,
                            user_Id int  not null ,
                            role_Id int  not null ,
                            create_Time datetime,
                            update_Time datetime
);
#店铺表
create table  shop(
                      id int primary key auto_increment,
                      shop_Name varchar(50) not null,
                      shop_ImgUrl varchar(200),
                      shop_Class varchar(50) not null,
                      shop_Admin int not null,
                      shop_State int default 0,
                      shop_Address varchar(200),
                      shop_Concern int default 0,
                      create_Time datetime,
                      update_Time datetime
);
#sessionID表
create table session(
                        id int primary key auto_increment,

                        user_Id  int  not null,

                        session  varchar(200) not null,

                        create_Time datetime,

                        update_Time datetime
);
#角色权限表
create  table  role_power(
                             id int primary key  auto_increment,
                             role_Id int not null ,
                             power_Id int not null ,
                             create_Time datetime,
                             update_Time datetime
);
#角色表
create table  role (
                       id int primary key  auto_increment,
                       role_Name varchar(50) not null ,
                       create_Time datetime,
                       update_Time datetime
);
#权限表
create  table  power (
                         id int primary key  auto_increment,
                         url varchar(50) not null,
                         create_Time datetime,
                         update_Time datetime
);
#订单表
create table goods_order(
                            id int primary key  auto_increment,
                            user_ID  int not null,
                            goods_Id int not null,
                            goods_Number varchar(50) not null ,
                            goods_Get_Time datetime not null ,
                            goods_Payment_state int not null,
                            goods_Payment_Time datetime,
                            create_Time datetime,
                            update_Time datetime

);
#商品表
create table goods(

                      id int primary key  auto_increment,

                      shop_Id  int not null,

                      goods_Name varchar(50) not null,

                      goods_Suggest varchar(200) ,

                      goods_ImageUrl VARCHAR(50) ,

                      goods_Money INT NOT NULL ,


                      extend_One  varchar(50), # 扩展字段1

                      extend_Two  varchar(50), # 扩展字段2

                      create_Time DATETIME ,

                      update_Time DATETIME
);
#信息表
create table message(
                        id int primary key auto_increment,
                        write_Id int not null,
                        read_Id int not null,
                        message_Class int not null, #信息类型 0为文本，1为图片
                        message_power int not null,   #权限
                        message_state int default 0,  #0 未读 ,1 已读
                        write_Read varchar(50) not null,
                        message varchar(200) not null,
                        extend_One  varchar(50), # 扩展字段1
                        extend_Two  varchar(50), # 扩展字段2
                        create_Time datetime,
                        update_Time datetime
);
#员工表
create table  staff(
                       id int primary key auto_increment,
                       user_Id int not null,
                       shop_Id int not null,
                       role_Id int not null,
                       staff_State int default 0, #0为未确认
                       money int , #工资
                       out_Time datetime,  #到期时间
                       extend_One  varchar(50), # 扩展字段
                       create_Time datetime,
                       update_Time datetime
);
#敏感词表
create table goods_sensitive(
    id int primary key  auto_increment,
    sensitive_text varchar(50) not null,
    update_user int not null,
    create_Time datetime,
    update_Time datetime
);
#标签表
create table keyword(
    id int primary key auto_increment,
    class_Text varchar(50),
    superior int,
    extend_One  varchar(50), # 扩展字段1
    extend_Two  varchar(50), # 扩展字段2
    create_Time datetime,
    update_Time datetime
);
#商品标签表
create table goodsKeyword(
    id int primary key auto_increment,
    goods_Id int not null,
    superior_Id int not null,
    create_Time datetime,
    update_Time datetime
);
#user
INSERT INTO `user` VALUES (1, 'admin', '50433ae85d25bd3ad9b412358acfaa2d', NULL, NULL, '5', NULL, NULL, NULL, NULL, '2022-06-09 22:35:04', '2022-06-09 22:35:04');

#user_role
INSERT INTO `user_role` VALUES (1, 1, 2, '2022-06-09 22:35:04', '2022-06-09 22:39:01');
# role
INSERT INTO `role` VALUES (1, 'user', '2022-04-17 09:07:24', '2022-04-17 09:07:24');
INSERT INTO `role` VALUES (2, 'admin', '2022-04-17 09:07:24', '2022-04-17 09:07:24');
INSERT INTO `role` VALUES (3, 'goodsAdmin', '2022-04-18 20:28:40', '2022-04-18 20:28:40');
INSERT INTO `role` VALUES (4, 'shopAdmin', '2022-04-18 20:28:40', '2022-04-18 20:28:40');
INSERT INTO `role` VALUES (5, 'commonAdmin', '2022-04-18 20:28:40', '2022-04-18 20:28:40');
INSERT INTO `role` VALUES (6, 'serverAdmin', '2022-04-18 20:28:40', '2022-04-18 20:28:40');

# power
INSERT INTO `power` VALUES (1, 'user:*:*', '2022-04-17 09:07:33', '2022-04-17 09:07:33');
INSERT INTO `power` VALUES (2, 'admin:shop:goods', '2022-04-17 09:07:33', '2022-04-17 09:07:33');
INSERT INTO `power` VALUES (3, 'admin:shop:*', '2022-04-17 09:07:16', '2022-04-17 09:07:16');
INSERT INTO `power` VALUES (4, 'admin:common:*', '2022-04-17 09:07:16', '2022-04-17 09:07:16');
INSERT INTO `power` VALUES (5, 'admin:*:*', '2022-04-17 09:07:16', '2022-04-17 09:07:16');

#role_power
INSERT INTO `role_power` VALUES (1, 1, 1, '2022-04-17 09:07:33', '2022-04-17 09:07:33');
INSERT INTO `role_power` VALUES (2, 2, 1, '2022-04-17 09:07:33', '2022-04-17 09:07:33');
INSERT INTO `role_power` VALUES (3, 2, 5, '2022-04-17 09:07:33', '2022-04-17 09:07:33');
INSERT INTO `role_power` VALUES (4, 3, 1, '2022-04-17 09:07:33', '2022-04-17 09:07:33');
INSERT INTO `role_power` VALUES (5, 3, 2, '2022-04-17 09:07:33', '2022-04-17 09:07:33');
INSERT INTO `role_power` VALUES (6, 4, 1, '2022-04-17 09:07:33', '2022-04-17 09:07:33');
INSERT INTO `role_power` VALUES (7, 4, 3, '2022-04-17 09:07:33', '2022-04-17 09:07:33');
INSERT INTO `role_power` VALUES (8, 5, 1, '2022-04-17 09:07:33', '2022-04-17 09:07:33');
INSERT INTO `role_power` VALUES (9, 5, 4, '2022-04-17 09:07:33', '2022-04-17 09:07:33');
INSERT INTO `role_power` VALUES (10, 6, 1, '2022-04-17 09:07:33', '2022-04-17 09:07:33');
INSERT INTO `role_power` VALUES (11, 6, 2, '2022-04-17 09:07:33', '2022-04-17 09:07:33');

#keyword
INSERT INTO `keyword` VALUES (1, '家用电器', NULL, NULL, NULL, '2022-06-18 16:56:04', '2022-06-18 16:56:04');
INSERT INTO `keyword` VALUES (2, '家具', NULL, NULL, NULL, '2022-06-18 16:56:04', '2022-06-18 16:56:04');
INSERT INTO `keyword` VALUES (3, '运动器材', NULL, NULL, NULL, '2022-06-18 16:56:04', '2022-06-18 16:56:04');
INSERT INTO `keyword` VALUES (4, '数码产品', NULL, NULL, NULL, '2022-06-18 16:56:04', '2022-06-18 16:56:04');
INSERT INTO `keyword` VALUES (5, '药品', NULL, NULL, NULL, '2022-06-18 16:56:04', '2022-06-18 16:56:04');
INSERT INTO `keyword` VALUES (6, '休闲食品', NULL, NULL, NULL, '2022-06-18 16:56:04', '2022-06-18 16:56:04');
INSERT INTO `keyword` VALUES (7, '常用工具', NULL, NULL, NULL, '2022-06-18 16:56:04', '2022-06-18 16:56:04');
INSERT INTO `keyword` VALUES (8, '医疗器材', NULL, NULL, NULL, '2022-06-18 16:56:04', '2022-06-18 16:56:04');
INSERT INTO `keyword` VALUES (9, '厨房用品', NULL, NULL, NULL, '2022-06-18 16:56:04', '2022-06-18 16:56:04');
INSERT INTO `keyword` VALUES (10,'家居用品', NULL, NULL, NULL, '2022-06-18 16:56:04', '2022-06-18 16:56:04');
INSERT INTO `keyword` VALUES (11,'服装', NULL, NULL, NULL, '2022-06-18 16:56:04', '2022-06-18 16:56:04');
INSERT INTO `keyword` VALUES (12, '化妆品', NULL, NULL, NULL, '2022-06-18 16:56:04', '2022-06-18 16:56:04');
INSERT INTO `keyword` VALUES (13, '植物花卉', NULL, NULL, NULL, '2022-06-18 16:56:04', '2022-06-18 16:56:04');
INSERT INTO `keyword` VALUES (14, '手机', 4, '2', NULL, '2022-06-18 16:56:04', '2022-06-18 16:56:04');
INSERT INTO `keyword` VALUES (15, '电脑', 4, '2', NULL, '2022-06-18 16:56:04', '2022-06-18 16:56:04');
INSERT INTO `keyword` VALUES (16, '乒乓球拍', 3, '2', NULL, '2022-06-18 16:56:04', '2022-06-18 16:56:04');
INSERT INTO `keyword` VALUES (17, '螺丝刀', 7, '2', NULL, '2022-06-18 16:56:04', '2022-06-18 16:56:04');
INSERT INTO `keyword` VALUES (18, '菜刀', 9, '2', NULL, '2022-06-18 16:56:04', '2022-06-18 16:56:04');