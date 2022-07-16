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
)