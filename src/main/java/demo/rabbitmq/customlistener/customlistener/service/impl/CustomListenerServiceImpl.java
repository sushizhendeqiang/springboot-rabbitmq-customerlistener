package demo.rabbitmq.customlistener.customlistener.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import demo.rabbitmq.customlistener.customlistener.service.CustomListenerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: sush4
 * @Description:
 * @Date: 2020/7/24
 */

@Service
@Slf4j
@ComponentScan(basePackages={"cn.hutool.extra.spring"})
public class CustomListenerServiceImpl implements CustomListenerService {

    @Override
    public void addNewListener(String queueName) {
        if(StrUtil.isEmpty(queueName)){
            log.info("------------------添加失败，无效的queue!------------------");
        }
        SimpleMessageListenerContainer container = SpringUtil.getBean(SimpleMessageListenerContainer.class);
        String[] queues = container.getQueueNames();
        //没有监听的队列，直接添加
        if(queues == null || queues.length == 0){
            container.addQueueNames(queueName);
            log.info("------------------添加队列监听成功------------------");
        }else{
            //string[]转list<String>
            List<String> queueList = new ArrayList<>();
            Collections.addAll(queueList, queues);
            if(queueList.contains(queueName)){
                log.info("------------------添加失败," + queueName + "该队列的监听者已存在！------------------");
            }else{
                container.addQueueNames(queueName);
                log.info("------------------添加队列监听成功------------------");
            }
        }
    }

    @Override
    public boolean removeListener(String queueName) {
        if(StrUtil.isEmpty(queueName)){
            return false;
        }
        SimpleMessageListenerContainer container = SpringUtil.getBean(SimpleMessageListenerContainer.class);
        return container.removeQueueNames(queueName);
    }
}
