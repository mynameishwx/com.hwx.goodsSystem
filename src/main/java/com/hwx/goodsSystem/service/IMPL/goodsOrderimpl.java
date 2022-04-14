package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.Dao.goodsOrderDao;
import com.hwx.goodsSystem.entity.commonResult;
import com.hwx.goodsSystem.entity.goodsOrder;
import com.hwx.goodsSystem.service.goodsOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class goodsOrderimpl implements goodsOrderService {

    @Autowired
    private goodsOrderDao goodsOrderDao;

    @Override
    public commonResult<goodsOrder> createGoodsOrder(goodsOrder goodsOrder) {
        if(goodsOrderDao.createGoodsOrder(goodsOrder)!=0){
          return   new commonResult<goodsOrder>(200,"创建订单成功",goodsOrder);
        }else{
            log.info("goodsOrder创建订单失败 ：",goodsOrder.toString());
            return  new commonResult<goodsOrder>(500,"创建订单失败",null);
        }
    }

    @Override
    public commonResult<Integer> deleteGoodsOrder(Integer id) {
        if(goodsOrderDao.deleteGoodsOrder(id)!=0){
            return  new commonResult<>(200,"id:"+id+"删除成功");
        }else {
            return  new commonResult<>(400,"id:"+id+"没有该订单");
        }
    }

    @Override
    public commonResult<goodsOrder> updateGoodsOrder(goodsOrder goodsOrder) {
        if(goodsOrderDao.updateGoodsOrder(goodsOrder)!=0){
            return  new commonResult<>(200,"修改成功");
        }else{
            log.info("goodsOrder订单修改失败",goodsOrder.toString());
            return  new commonResult<goodsOrder>(500,"订单修改失败",goodsOrder);
        }
    }

    @Override
    public commonResult<goodsOrder> getGoodsOrder(Integer id) {
        goodsOrder goodsOrder=new goodsOrder();
        goodsOrder=goodsOrderDao.getGoodsOrder(id);
        if(goodsOrder!=null){
            return  new commonResult<>(200,"查找成功",goodsOrder);
        }else {
            return  new commonResult<>(400,"没有查询到该数据 , id :"+id,null);
        }
    }
}
