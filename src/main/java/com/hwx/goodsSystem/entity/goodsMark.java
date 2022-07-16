package com.hwx.goodsSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class goodsMark implements Serializable, Comparable<goodsMark> {

    /**
     * ID
     */
    private Integer id;

    /**
     * 商品ID
     */
    private Integer goodsId;

    /**
     * 系统推荐数
     */
    private Integer systemNumber;

    /**
     * 购买成功数
     */
    private Integer purchaseNumber;

    /**
     * 点赞数
     */
    private Integer thumbsNumber;

    /**
     * 好评数
     */
    private Integer praiseNumber;

    /**
     * 差评数
     */
    private Integer negativeNumber;

    /**
     * 阅读数
     */
    private Integer readNumber;

    /**
     * 搜索数
     */
    private Integer searchNumber;

    /**
     * 收藏数
     */
    private Integer collectNumber;

    /**
     * 转发数
     */
    private Integer forwardNumber;

    /**
     * 总分数
     */
    private Integer sumMark;

    /**
     * 创建时间
     */
    private Date create_Time;


    /**
     * 修改时间
     */
    private Date update_Time;

    /**
     * 重写 Comparable的 compareTo方法
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(goodsMark o) {
        if (o instanceof goodsMark) {
            goodsMark oOne = o;
            return new Double(oOne.getSumMark()).compareTo(new Double(this.getSumMark()));
//            return new Double(this.getSumMark()).compareTo(new Double(oOne.getSumMark()));
        }
        throw new ClassCastException("无法转换不是 goodsMark的类");
    }
}
