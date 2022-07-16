create table session(
                        id int primary key auto_increment,

                        user_Id  int  not null,

                        session  varchar(200) not null,

                        create_Time datetime,

                        update_Time datetime
)