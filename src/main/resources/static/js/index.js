let start = 0;  //获取商品的开始位置

let stop = 12;  //结束位置

let index_number = 0;  //本次获取的商品数

let goodsClass = 0;  //请求的商品类型

/**
 * 商品展示
 */
function showGoods(start, stop, goodsClass, indexBool) {
    $.ajax({
        url: "/index/getGoodsList",
        type: "post",
        data: {
            start: start,
            stop: stop,
            goodsClass: goodsClass
        },
        success: function (data) {
            //本次获取商品的个数
            index_number = data.length;

            let index_goods_list = document.getElementById("index_goodsList");

            //商品信息拼接
            let indexGoods = "";
            for (let i = 0; i < data.length; i++) {
                indexGoods += "<li class=\" more2_item hover-on \">";
                indexGoods += "<img height=\"210\"" + " ";
                indexGoods += "src=" + "\"" + "/goodsImg/" + data[i].goodsImageUrl + "\"" + " " + "width=\"190\">";
                indexGoods += "&emsp;&emsp;&emsp;<a>" + data[i].goodsName + "</a>";
                indexGoods += " <br>";
                indexGoods += "&emsp;&emsp;&emsp;&emsp; <a class=\" text-danger\" >¥" + data[i].goodsMoney + "</a>";
                indexGoods += " </li>";
            }
            ;
            if (indexBool == false) {
                index_goods_list.innerHTML = indexGoods;
            } else {
                index_goods_list.innerHTML += indexGoods;
            }
        }
    });
}

/**
 * 获取新品商品
 */
function getGoodsNew() {
    start = 0;
    stop = 12;
    goodsClass = 2;
    index_number = 0;
    showGoods(start, stop, goodsClass, false);
}

/**
 * 获取热点商品
 */
function getGoodsCountMax() {
    start = 0;
    stop = 12;
    index_number = 0;
    goodsClass = 1;
    showGoods(start, stop, goodsClass, false);
}

//滚动到底部触发事件
function scrollFunc() {
    let minAwayBtm = 100;  // 距离页面底部的最小距离
    window.addEventListener("scroll", function (e) {
        let awayBtm = $(document).height() - $(window).scrollTop() - $(window).height();
        if (awayBtm <= minAwayBtm) {
            if (index_number == 12 && index_number != 0) {
                index_number = 0;
                showGoods(start + 12, stop + 12, goodsClass, true);
            }
        }
    });
}

//调用 底部触发事件
scrollFunc();

//调用获取精品商品列表
showGoods(start, stop, goodsClass, false);