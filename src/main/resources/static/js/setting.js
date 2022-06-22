/**
 *  标签类别获取
 */
function keywordList(){
    let selectValue=document.getElementById("one_class").value;
    if(selectValue=="请选择类型"){
        $.ajax({
            url: "/shop/getKeyword",
            type: "post",
            success: function (data){
                let settingSelect= document.getElementById("one_class");
                settingSelect.innerHTML="";
                let classHtml="<option>请选择类型</option>";
                for (let i = 0; i < data.length; i++) {
                    classHtml+="<option>"+data[i].classText+"</option>";
                }
                settingSelect.innerHTML+=classHtml;
            }
        });
    }else {

    }
}
/**
 * 创建店铺
 */
function shop(){
    let div=document.getElementById("tab2");
    div.innerHTML="";
    div.innerHTML+="<form action=\"/shop/create\" enctype=\"multipart/form-data\" method=\"post\">\n" +
        "    <div class=\"form-group\">\n" +
        "        <label for=\"shopName\">店铺名称</label>\n" +
        "        <input class=\"form-control\" id=\"shopName\" name=\"shopName\"  type=\"text\" >\n" +
        "    </div>\n" +
        "    <div class=\"form-group\">\n" +
        "        <label for=\"shop_Class\">选择店铺主类型</label>\n" +
        "        <div class=\"col-sm-8 input-group\" id=\"shop_Class\">\n" +
        "            <select class=\"province form-control\" id=\"one_class\" name=\"province\" style=\"width:90px\" onclick=\" keywordList() \">\n" +
        "                <option>请选择类型</option>\n" +
        "            </select>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "    <div class=\"form-group\">\n" +
        "        <label for=\"exampleInputFile\">选择店铺形象</label>\n" +
        "        <input id=\"exampleInputFile\" name=\"shopImg\" type=\"file\">\n" +
        "        <p class=\"help-block\">请选择小于5MB的jpg,或者png格式的图片</p>\n" +
        "    </div>\n" +
        "    <div class=\"alert alert-danger\" role=\"alert\"><a th:text=\"${Tshi}\"></a></div>\n" +
        "    <button class=\"btn btn-default\" type=\"submit\">提交</button>\n" +
        "</form>";
}