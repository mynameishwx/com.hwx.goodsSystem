create table message(
    id int primary key auto_increment,
    write_Id int not null,
    read_Id int not null,
    message_Class int not null, #信息类型 0为文本，1为图片
    message_power int not null,   #权限
    message_state int default 0,  #0 未读 ,1 已读
    write_Read varchar(50) not null,
    message varchar(200) not null,
    create_Time datetime,
    update_Time datetime
)