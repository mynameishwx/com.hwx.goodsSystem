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