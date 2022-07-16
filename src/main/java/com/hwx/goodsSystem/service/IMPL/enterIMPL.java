package com.hwx.goodsSystem.service.IMPL;

import com.hwx.goodsSystem.entity.goods;
import com.hwx.goodsSystem.entity.goodsMark;
import com.hwx.goodsSystem.service.goodsMarkService;
import com.hwx.goodsSystem.service.goodsService;
import com.hwx.goodsSystem.service.sessionService;
import com.hwx.goodsSystem.util.goodsThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class enterIMPL {


    private goodsService goodsService;

    private com.hwx.goodsSystem.util.goodsThreadLocal goodsThreadLocal;

    private goodsMarkService goodsMarkService;

    private sessionService sessionService;

    /**
     * set注入goodsService
     *
     * @param goodsService
     */
    @Autowired
    public void setGoodsService(goodsService goodsService) {
        this.goodsService = goodsService;
    }

    ;

    /**
     * set注入goodsThreadLocal
     *
     * @param goodsThreadLocal
     */
    @Autowired
    public void setGoodsThreadLocal(goodsThreadLocal goodsThreadLocal) {
        this.goodsThreadLocal = goodsThreadLocal;
    }

    /**
     * set注入goodsMarkService
     *
     * @param goodsMarkService
     */
    @Autowired
    public void setGoodsMarkService(goodsMarkService goodsMarkService) {
        this.goodsMarkService = goodsMarkService;
    }


    /**
     * set注入sessionService
     *
     * @param sessionService
     */
    @Autowired
    public void setSessionService(sessionService sessionService) {
        this.sessionService = sessionService;
    }


    /**
     * 首页商品展示
     *
     * @return
     */
    public Map<String, Object> enterGoods(Map<String, Object> map) {
        List<goods> goodsList = new ArrayList<>();
        goodsList = goodsService.getGoodsLiMit(0, 4, goodsThreadLocal.getUser().getId());
        map.put("goodsList", goodsList);
        return map;
    }

    /**
     * 分页获取商品数据
     *
     * @param start
     * @param stop
     * @param stars
     * @param map
     * @return
     */
    public Map<String, Object> enterGoodsList(Integer start, Integer stop, Integer stars, Map<String, Object> map) {
        List<goods> goods = new ArrayList<>(20);
        Integer goodsSize = goodsService.getGoods();
        /**
         * 获取前一页
         */
        if (stars < 0) {
            goods = goodsService.getGoodsLiMit((stop - 1) * 4, (stop * 4), null);

        }
        return map;
    }

    public List<goods> enterGoodsMark(Integer start, Integer stop, Integer goodsMarkClass) {
        List<goods> goodsList = new ArrayList<>();

        goodsMark goodsMark = new goodsMark();

        if (goodsMarkClass == 0) {
            goodsMark.setGoodsId(0);
            goodsMark.setId(1);
        } else if (goodsMarkClass == 1) {
            goodsMark.setGoodsId(0);
            goodsMark.setId(2);
        } else if (goodsMarkClass == 2) {
            goodsMark.setGoodsId(0);
            goodsMark.setId(3);
        } else {
            log.warn("无该类型加权参数！");
            return null;
        }

        /**
         * 获取参数
         */
        ArrayList<goodsMark> goodsMarkArray = new ArrayList<>();
        goodsMarkArray = goodsMarkService.selectByid(goodsMark);

        if (goodsMarkArray.size() != 0) {
            goodsMark = goodsMarkArray.get(0);
        } else {
            log.warn("无商品加权参数，请先初始化参数");
            return null;
        }
        goodsMark goodsMarkTwo = new goodsMark();
        /**
         * 查询商品
         */
        goodsMarkArray = goodsMarkService.selectByid(goodsMarkTwo);
        Iterator<goodsMark> goodsMarkIterator = goodsMarkArray.iterator();

        /**
         * 加权数据完毕
         */
        int ENTERSYNTHESIZE = 0;
        List<goodsMark> goodsMarkList = new ArrayList<>();
        while (goodsMarkIterator.hasNext()) {
            goodsMarkTwo = goodsMarkIterator.next();
            /**
             * 根据加权参数修改数值
             */
            ENTERSYNTHESIZE =
                    goodsMarkTwo.getCollectNumber() * goodsMark.getCollectNumber() +
                            goodsMarkTwo.getForwardNumber() * goodsMark.getForwardNumber() +
                            goodsMarkTwo.getNegativeNumber() * goodsMark.getNegativeNumber() +
                            goodsMarkTwo.getPraiseNumber() * goodsMark.getPraiseNumber() +
                            goodsMarkTwo.getPurchaseNumber() * goodsMark.getPurchaseNumber() +
                            goodsMarkTwo.getSearchNumber() * goodsMark.getSearchNumber() +
                            goodsMarkTwo.getSystemNumber() * goodsMark.getSystemNumber() +
                            goodsMarkTwo.getThumbsNumber() * goodsMark.getThumbsNumber();
            goodsMarkTwo.setSumMark(ENTERSYNTHESIZE);
            goodsMarkList.add(goodsMarkTwo);
            ENTERSYNTHESIZE = 0;
        }
        /**
         * List自然排序
         */
        Collections.sort(goodsMarkList);

        for (int i = start; i < stop; i++) {
            if (i + 1 <= goodsMarkList.size()) {
                /**
                 * 根据排序完的goodsMark来查询goodsId,继而查询goods
                 */
                goodsList.add(i, goodsService.getGoodsById(goodsMarkList.get(i).getGoodsId()));
            } else break;
        }
        if (goodsList.size() == 0) {
            log.warn("查询异常: start:" + start + " ,stop:" + stop + "  ,获取的商品数为0");
        }
        return goodsList;
    }
}
