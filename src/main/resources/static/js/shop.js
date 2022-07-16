//商品信息首页默认展示
window.goodsList(0,5);


//店铺信息
function shop_shop(){

    $.ajax({
        url: "/shop/shopMessage",
        type: "post",
        success: function (data){
            let htmlShopMessage="";
            htmlShopMessage+="<img  class=\"img-circle\" height=\"100\" src=\" /shopImg/"+data.shopImgUrl+"\" width=\"100\">";
            htmlShopMessage+="<h3><a>"+data.shopName+"</a></h3>";
            document.getElementById("shop_shopMessage").innerHTML=htmlShopMessage;
        }
    })
}
//商品类型获取
function get_Keyword(){
    let goodsKeywordValue=document.getElementById("goods_class").value;
    if(goodsKeywordValue=="请选择类型"){
        $.ajax({
            url: "/goods/getKeyword",
            type: "post",
            success: function (data){
                let goods_keyword=document.getElementById("goods_class");
                let goods_keyword_html="";
                for (let i = 0; i < data.length; i++) {
                    goods_keyword_html+="<option>";
                    goods_keyword_html+=data[i].classText;
                    goods_keyword_html+="</option>"
                }
                goods_keyword.innerHTML=goods_keyword_html;
            }
        });
    }
}

//店铺员工列表
function shop_staffList(){
    $.ajax({
        url: "/shop/staffList",
        type: "post",
        data: {
            start: 0,
            stop: 5
        },
        success: function (data){
            document.getElementById("shop_staffList_table").innerHTML="";
            let htmlStaff="";
            for (let i = 0; i < data.length; i++) {
                htmlStaff+="<tr>";
                htmlStaff+="<td>"+data[i].extendOne +"</td>";
                if(data[i].roleId=="1"){
                    htmlStaff+="<td>销售</td>";
                }else {
                    htmlStaff+="<td>客服</td>";
                }
                htmlStaff+="<td>"+data[i].money+"</td>";
                htmlStaff+="<td>"+data[i].updateTime+"</td>";
                htmlStaff+="<td>操作</td>";
                htmlStaff+="</tr>";
            };
            document.getElementById("shop_staffList_table").innerHTML=htmlStaff;

        }
    })
}
//获取商品信息
function getGoodsList(state){

  //当前页数
  let index=document.getElementById("shop_goods_list_size").innerText;

  //防止出错到0页
  if(index==0){
      window.goodsList(0,5);
  }
  //上一页
  if(state==-1 || state=="-1"){
      if(index!=1 && index>0){
          window.goodsList((index-2)*5,5);
          document.getElementById("shop_goods_list_size").innerText=parseInt(index)-1;
      }
  }
  //下一页
  if(state==1 || state=="1"){
      let goods_sum= document.getElementById("shop_goodsSum").innerText;
      goods_sum=goods_sum.replace("共","");
      goods_sum=goods_sum.replace("页","");
      index=parseInt(index);
      goods_sum=parseInt(goods_sum);
      if(index>=goods_sum){
          alert("已经到最后一页哦");
      }else {
          window.goodsList(index*5,5);
          document.getElementById("shop_goods_list_size").innerText=parseInt(index)+1;
      }
  }

}
//商品信息公共部分提取
function goodsList(start,stop){
    $.ajax({
        url: "/goods/showList",
        type: "post",
        data: {
            start: start,
            stop: stop
        },
        success: function (data){
            //商品信息拼接
            let  htmlGoods="";
            document.getElementById("shop_goodsList").innerText="";
            for (let i = 0; i < data.length; i++) {
                htmlGoods+="<li class=\" more2_item hover-on \">";
                htmlGoods+="<img height=\"210\""+" ";
                htmlGoods+="src="+"\""+"/goodsImg/"+data[i].goodsImageUrl+"\""+" "+"width=\"190\">";
                htmlGoods+="&emsp;&emsp;&emsp;<a>"+data[i].goodsName+"</a>";
                htmlGoods+=" <br>";
                htmlGoods+="&emsp;&emsp;&emsp;&emsp; <a class=\" text-danger\" >¥"+data[i].goodsMoney+"</a>";
                htmlGoods+=" </li>";
            };
            document.getElementById("shop_goodsList").innerHTML=htmlGoods;

            if(data!=""){
                //总条数
                document.getElementById("shop_goodsSum").innerText="共"+data[0].extend_Two+"页";
            }else {
                //总条数
                document.getElementById("shop_goodsSum").innerText="共0个商品";
            }
        }
    })
}
//商品搜索
function SearchGoods(){
    let Search_text=document.getElementById("SearchGoods_text").innerText;
    $.ajax({
        url: "/goods/SearchGoods",
        type: "post",
        data: {
            Search_Goods_text: Search_text
        },
        success: function (data){

        }
    })
}