create table  shop(
    id int primary key auto_increment,
    shop_Name varchar(50) not null,
    shop_Class varchar(50) not null,
    shop_Admin int not null,
    shop_State int default 0,  #0为关闭
    shop_Address varchar(200),
    shop_Concern int default 0,
    create_Time datetime,
    update_Time datetime
)