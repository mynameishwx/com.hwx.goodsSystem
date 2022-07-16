create table goods(

    id int primary key  auto_increment,

    shop_Id  int not null,

    goods_Name varchar(50) not null,

    goods_Suggest varchar(200) ,

    goods_ImageUrl VARCHAR(50) ,

    goods_Money INT NOT NULL ,

    keyword varchar(50),

    extend_One  varchar(50), # 扩展字段1

    extend_Two  varchar(50), # 扩展字段2

    create_Time DATETIME ,

    update_Time DATETIME
)