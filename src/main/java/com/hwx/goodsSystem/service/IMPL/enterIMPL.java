package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.entity.goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class enterIMPL {

    @Autowired
    private com.hwx.goodsSystem.service.goodsService goodsService;

    @Autowired
    private com.hwx.goodsSystem.util.goodsThreadLocal goodsThreadLocal;

    /**
     * 首页商品展示
     * @return
     */
    public Map<String,Object> enterGoods(Map<String,Object> map){
        List<goods> goodsList=new ArrayList<>();
        goodsList=goodsService.getGoodsLiMit(0,4,goodsThreadLocal.getUser().getId());
        map.put("goodsList",goodsList);
        return map;
    }

    public Map<String,Object> enterGoodsList(Integer start, Integer stop,Integer stars, Map<String,Object> map){
        List<goods> goods=new ArrayList<>(20);
        Integer goodsSize=goodsService.getGoods();
        /**
         * 获取前一页
         */
        if(stars<0){
            goods=goodsService.getGoodsLiMit((stop-1)*4,(stop*4),null);

        }
        return map;
    }

}
