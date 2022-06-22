//导航条信息条数显示
$.ajax({
    url: "/crony/messageSize",
    type: "post",
    success: function (data){
        document.getElementById("message_size").innerText="";
        if(data!="0"){
            document.getElementById("message_size").innerText=data;
        }
    }
})
//每种信息条数展示
$.ajax({
    url: "/crony/messageSum",
    type: "post",
    success: function (data){
        document.getElementById("my_message").innerText="";
        if(data[0]!=0){
            document.getElementById("my_message").innerText=data[0];
        }
        document.getElementById("admin_message").innerText="";
        if(data[1]!=0){
            document.getElementById("admin_message").innerText=data[1];
        }
        document.getElementById("cronyCreate_message").innerText="";
        if(data[2]!=0){
            document.getElementById("cronyCreate_message").innerText=data[2];
        }
    }
})
//普通信息展示
$.ajax({
   url: "/crony/my_message",
   type: "post",
   success: function (data){
       document.getElementById("my_message_List").innerHTML="";
       let  message_Position="";
       let  message_Common="";
       for (let i = 0; i < data.length; i++) {
            if(data[i].messageClass=="1") {
                message_Common+="<li class=\"more2_itemCrony\">";
                message_Common+="<button type=\"button\" class=\"btn btn-default btn-lg btn-block\">";
                message_Common+="<a>"+data[i].extendOne+"  :"+data[i].message.slice(1,6);
                message_Common+="</button>";
                message_Common+="</li>";
            }else if(data[i].messageClass=="1"){
                message_Common+="<li class=\"more2_itemCrony\">";
                message_Common+="<button type=\"button\" class=\"btn btn-default btn-lg btn-block\">";
                message_Common+="<a>"+data[i].extendOne+"给你发了一张图片";
                message_Common+="</button>";
                message_Common+="</li>";
            }else if(data[i].messageClass=="3"){
                    message_Position+="<li class=\"more2_itemCrony\">";
                    message_Position+="<button type=\"button\" class=\"btn btn-primary btn-lg btn-block\">";
                    message_Position+="<a style=\"color:#FFFFFF\">"+data[i].extendOne+"邀请你入职</a>";
                    message_Position+="</button>";
                    message_Position+="</li>";
                }
       }
       if(data.length!=0){
           if(message_Position==""){
               document.getElementById("my_message_List").innerHTML=message_Common;
           }else {
               document.getElementById("my_message_List").innerHTML=message_Position+message_Common;
           }
       }
   }
})
//系统信息
function get_admin_message(){
    $.ajax({
        url: "/crony/admin_message",
        type: "post",
        success: function (data){
            document.getElementById("admin_message_List").innerHTML="";
            let  html_admin_message_List="";
            for (let i = 0; i < data.length; i++) {
                html_admin_message_List+="<li class=\"more2_itemCrony\">";
                html_admin_message_List+="<button type=\"button\" class=\"btn btn-default btn-lg btn-block\">";
                html_admin_message_List+="<a>"+"  :"+data[i].message.slice(1,6);
                html_admin_message_List+="</button>";
                html_admin_message_List+="</li>";
            }
            document.getElementById("admin_message_List").innerHTML=html_admin_message_List;
        }
    })
}
//好友申请
function get_cronyCreate_message(){
    $.ajax({
        url: "/crony/cronyCreate_message",
        type: "post",
        success: function (data){

        }
    })
}