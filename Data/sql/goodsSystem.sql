create database goodsSystem;
use goodsSystem;
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
create  table  user_role(
                            id int primary key  auto_increment,
                            user_Id int  not null ,
                            role_Id int  not null ,
                            create_Time datetime,
                            update_Time datetime
);
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
create table session(
                        id int primary key auto_increment,

                        user_Id  int  not null,

                        session  varchar(200) not null,

                        create_Time datetime,

                        update_Time datetime
);
create  table  role_power(
                             id int primary key  auto_increment,
                             role_Id int not null ,
                             power_Id int not null ,
                             create_Time datetime,
                             update_Time datetime
);
create table  role (
                       id int primary key  auto_increment,
                       role_Name varchar(50) not null ,
                       create_Time datetime,
                       update_Time datetime
);
create  table  power (
                         id int primary key  auto_increment,
                         url varchar(20) not null,
                         create_Time datetime,
                         update_Time datetime
);
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
create table goods(

                      id int primary key  auto_increment,

                      shop_Id  int not null,

                      goods_Name varchar(50) not null,

                      goods_Suggest varchar(200) ,

                      goods_ImageUrl VARCHAR(50) ,

                      goods_Money INT NOT NULL ,

                      create_Time DATETIME ,

                      update_Time DATETIME
);


# role
INSERT INTO `role` VALUES (1, 'user', '2022-04-17 09:07:24', '2022-04-17 09:07:24');
INSERT INTO `role` VALUES (2, 'admin', '2022-04-17 09:07:24', '2022-04-17 09:07:24');
INSERT INTO `role` VALUES (3, 'goodsAdmin', '2022-04-18 20:28:40', '2022-04-18 20:28:40');
INSERT INTO `role` VALUES (4, 'shopAdmin', '2022-04-18 20:28:40', '2022-04-18 20:28:40');
INSERT INTO `role` VALUES (5, 'commonAdmin', '2022-04-18 20:28:40', '2022-04-18 20:28:40');

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
