create table goods(

    id int primary key  auto_increment,

    shop_Id  int not null,

    goods_Name varchar(50) not null,

    goods_Suggest varchar(200) ,

    goods_ImageUrl VARCHAR(50) ,

    goods_Money INT NOT NULL ,

    create_Time DATETIME ,

    update_Time DATETIME
)