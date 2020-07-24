package demo.rabbitmq.customlistener.api.service;

import demo.rabbitmq.customlistener.api.RabbitmqApi;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

import javax.annotation.Resource;

/**
 * @Author: sush4
 * @Description:
 * @Date: 2020/7/24
 */
public class RabbitmqApiImpl implements RabbitmqApi {
    @Resource
    private RabbitAdmin rabbitAdmin;

    @Override
    public void createExchange(String exchangeName, boolean durable, boolean autoDelete) {
        TopicExchange topicExchange = new TopicExchange(exchangeName, durable, autoDelete);
        rabbitAdmin.declareExchange(topicExchange);
    }

    @Override
    public void deleteExchange(String exchangeName) {
        rabbitAdmin.deleteExchange(exchangeName);
    }

    @Override
    public void createQueue(String queueName, boolean durable, boolean exclusive, boolean autoDelete) {
        Queue queue = new Queue(queueName, durable, exclusive, autoDelete);
        rabbitAdmin.declareQueue(queue);
    }

    @Override
    public void deleteQueue(String queueName) {
        rabbitAdmin.deleteQueue(queueName);
    }

    @Override
    public void bind(String queueName, String exchangeName, String routingKey) {
        rabbitAdmin.declareBinding(new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, routingKey, null));
    }
}
