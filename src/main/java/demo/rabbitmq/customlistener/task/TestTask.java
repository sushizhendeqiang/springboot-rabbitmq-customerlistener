package demo.rabbitmq.customlistener.task;

import demo.rabbitmq.customlistener.api.RabbitmqApi;
import demo.rabbitmq.customlistener.customlistener.service.CustomListenerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author: sush4
 * @Description:
 * @Date: 2020/7/24
 */

@Component
@EnableScheduling
@Slf4j
public class TestTask {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private RabbitmqApi rabbitmqApi;
    @Resource
    private CustomListenerService customListenerService;

    /**
     * 自定义监听测试
     */
    @Scheduled(initialDelay = 2000, fixedDelay = 500000)
    public void testAddMyListener() throws IOException {
        //创建交换机 队列 路由
        rabbitmqApi.createExchange("testexchange", false, false);
        rabbitmqApi.createQueue("testqueue", false, true, false);
        rabbitmqApi.bind("testqueue", "testexchange", "test.routingKey");
        //添加监听MQTT及RabbitMQ 关键词
        customListenerService.addNewListener("testqueue");
    }

    /**
     * 消息发送测试
     * @throws IOException
     */
    @Scheduled(initialDelay = 5000, fixedDelay = 5000)
    public void testlisten() throws IOException {
        rabbitTemplate.convertAndSend("testexchange", "test.routingKey", "sendto:myTestListener");
    }

    /**
     * 自定义监听测试
     * @throws IOException
     */
    @Scheduled(initialDelay = 20000, fixedDelay = 500000)
    public void testRemoveListener() throws IOException {
        //删除队列监听
        boolean result = customListenerService.removeListener("testqueue");
        if(result){
            log.info("------------------删除队列监听成功------------------");
        }else{
            log.info("------------------删除队列监听失败------------------");
        }
        //删除队列、交换机
        rabbitmqApi.deleteQueue("testqueue");
        rabbitmqApi.deleteExchange("testexchange");
        log.info("------------------删除队列、交换机成功------------------");
    }
}
