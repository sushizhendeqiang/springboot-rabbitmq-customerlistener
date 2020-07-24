package demo.rabbitmq.customlistener.customlistener.config;

import demo.rabbitmq.customlistener.config.RabbitmqConfig;
import demo.rabbitmq.customlistener.customlistener.handler.CustomListenerHandler;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author: baiyongqing
 * @Description:
 * @Date: 2020/7/24
 */

@Configuration
public class CustomListenerConfig {
    @Resource
    private RabbitmqConfig rabbitConfig;
    @Resource
    public CustomListenerHandler customListenerHandler;

    @Bean
    public SimpleMessageListenerContainer mqMessageContainer() throws AmqpException, IOException {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(rabbitConfig.connectionFactory());
        //设置启动监听超时时间
        container.setConsumerStartTimeout(3000L);
        container.setExposeListenerChannel(true);
        //设置确认模式
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        //监听处理类
        container.setMessageListener(customListenerHandler);
        return container;
    }

    @Bean
    public void start() {
        try {
            mqMessageContainer().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
