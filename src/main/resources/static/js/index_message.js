$.ajax({
        url: "/crony/messageSize",
        type: "post",
        success: function (data){
            document.getElementById("message_size").innerHTML="";
            if(data!="0"){
                document.getElementById("message_size").innerText=data;
            }
        },
        error: {

        }
    })

