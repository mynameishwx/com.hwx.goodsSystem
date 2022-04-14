package com.hwx.goodsSystem.Config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 延迟队列配置文件
 */
@Configuration
public class TtlQueueConfig {

    /**
     * 普通订单交换机
     */
    public static final  String  ORDER_COMMON_EXCHANGE="order_common_exchange";

    /**
     * 死信订单交换机
     */
    public static final String   ORDER_DEAD_EXCHANGE="order_dead_exchange";
    /**
     * 普通订单队列
     */
    public static final String   ORDER_COMMON_QUEUE="order_common_queue";
    /**
     * 普通订单队列ORDER_COMMON_QUEUE的routingKey
     */
    public static final String   OC_ROUTING_KEY="order_common_queue_1";
    /**
     * 死信订单队列
     */
    public static final String   ORDER_DEAD_QUEUE="order_dead_queue";
    /**
     * 死信订单队列ORDER_DEAD_QUEUE的routingKey
     */
    public static final String   OD_ROUTING_KEY="order_dead_queue_1";

    /**
     * 声明一个Direct(直接类型)普通交换机的交换机
     * @return
     */
    @Bean("OC_Exchange")
    public DirectExchange OC_Exchange(){
        return  new DirectExchange(ORDER_COMMON_EXCHANGE);
    }

    /**
     * 声明一个Direct(直接类型)死信交换机的交换机
     * @return
     */
    @Bean("OD_Exchange")
    public DirectExchange OD_Exchange(){
        return  new DirectExchange(ORDER_DEAD_EXCHANGE);
    }

    /**
     * 超时时间为3分钟的普通队列
     * @return
     */
    @Bean("OC_Queue")
    public Queue  OC_Queue(){
        Map<String,Object> arguments=new HashMap<>(3);
//        设置死信交换机
        arguments.put("x-dead-letter-exchange",ORDER_DEAD_EXCHANGE);
//        设置死信队列
        arguments.put("x-dead-letter-routing-key",OD_ROUTING_KEY);
//        设置Ttl   单位ms  (表示过去多少ms后,将信息转到死信队列,这里是3分钟)
        arguments.put("x-message-ttl",300000);

        return QueueBuilder.durable(ORDER_COMMON_QUEUE).withArguments(arguments).build();
    }

    /**
     * 声明一个死信队列
     * @return
     */
    @Bean("OD_Queue")
    public Queue OD_Queue(){
        return  QueueBuilder.durable(ORDER_DEAD_QUEUE).build();
    }

    /**
     * 死信队列与死信交换机通过RoutingKEY绑定
     *
     * @param OD_Queue
     * 死信队列
     * @param OD_Exchange
     * 死信交换机
     * @return
     */
    @Bean
    public Binding Dead_Key_Queue(@Qualifier("OD_Queue") Queue OD_Queue
            ,@Qualifier("OD_Exchange") DirectExchange OD_Exchange){
        return BindingBuilder.bind(OD_Queue).to(OD_Exchange).with(OD_ROUTING_KEY);
    }

    /**
     * 普通订单队列与普通订单交换机通过RoutingKEY绑定
     * @param OC_Queue
     * 普通订单队列
     * @param OC_Exchange
     * 普通订单交换机
     * @return
     */
    @Bean Binding Common_Key_Queue(@Qualifier("OC_Queue")Queue OC_Queue,
                                   @Qualifier("OC_Exchange")DirectExchange OC_Exchange){
        return  BindingBuilder.bind(OC_Queue).to(OC_Exchange).with(OC_ROUTING_KEY);
    }
}
