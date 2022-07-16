//  异地登录检测
$.ajax({
    url: "/enter/error",
    type: "post",
    success: function (data) {
        if (data == "2") {
            alert("您的账户在异地登录, 即将退出!");
            location.reload(true);
        }
        if (data != "0") {
            //查询好友信息
            getMessage();
        }
    }
});

function getMessage() {
    $.ajax({
        url: "/crony/messageSize",
        type: "post",
        success: function (data) {
            if (data != "0") {
                document.getElementById("message_size").innerText = data;
            }
        },
        error: {}
    });
}