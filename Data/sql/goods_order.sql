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

                        )