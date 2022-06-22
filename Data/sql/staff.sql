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
)