package demo.rabbitmq.customlistener.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Author: sush4
 * @Description:
 * @Date: 2020/7/24
 */

@Configuration
public class RabbitmqConfig {
    @Resource
    private RabbitmqConstants rabbitmqConstants;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setAddresses(rabbitmqConstants.addresses);
        cachingConnectionFactory.setUsername(rabbitmqConstants.username);
        cachingConnectionFactory.setPassword(rabbitmqConstants.password);
        cachingConnectionFactory.setVirtualHost(rabbitmqConstants.virtualHost);
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);

        //autoStartup 必须要设为 true ，否则Spring容器不会加载RabbitAdmin类
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }
}
