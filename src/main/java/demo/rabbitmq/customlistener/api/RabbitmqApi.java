package demo.rabbitmq.customlistener.api;

import java.io.IOException;

/**
 * @Author: sush4
 * @Description:
 * @Date: 2020/7/24
 */
public interface RabbitmqApi {
    /**
     * 创建exchange交换机
     * 注意事项：不能amq开头
     *
     * @param exchangeName 交换机名称
     * @param durable      持久化：存入磁盘，重启还在
     * @param autoDelete   自动删除：至少有一个queue或者exchange和该exchange绑定，否则自动删除
     */
    void createExchange(String exchangeName, boolean durable, boolean autoDelete);

    /**
     * 删除exchange交换机
     *
     * @param exchangeName 交换机名称
     */
    void deleteExchange(String exchangeName);

    /**
     * 创建queue队列
     *
     * @param queueName  队列名称
     * @param durable    持久化
     * @param exclusive  是否独占
     * @param autoDelete 自动删除
     */
    void createQueue(String queueName, boolean durable, boolean exclusive, boolean autoDelete);

    /**
     * 删除队列
     *
     * @param queueName 队列名称
     */
    void deleteQueue(String queueName);

    /**
     * 绑定
     *
     * @param queueName    队列名称
     * @param exchangeName 交换机名称
     * @param routingKey   路由键
     */
    void bind(String queueName, String exchangeName, String routingKey);
}
