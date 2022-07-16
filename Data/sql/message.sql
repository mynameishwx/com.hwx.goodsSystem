create table message(
    id int primary key auto_increment,
    write_Id int not null,
    read_Id int not null,
    message_Class int not null,
    # 信息类型
    # 0:系统信息
    # 1:用户聊天信息(文本)
    # 2:用户聊天信息(图片)
    # 3:店铺类员工信息
    # 4:好友申请信息
    #
    message_power int not null,   #权限
    message_state int default 0,  #0 未读 ,1 已读
    write_Read varchar(50) not null,
    message varchar(200) not null,
    extend_One  varchar(50), # 扩展字段1
    extend_Two  varchar(50), # 扩展字段2
    create_Time datetime,
    update_Time datetime
)