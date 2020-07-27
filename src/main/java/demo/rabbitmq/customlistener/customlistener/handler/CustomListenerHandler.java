package demo.rabbitmq.customlistener.customlistener.handler;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @Author: sush4
 * @Description:
 * @Date: 2020/7/24
 */

@Component
@Slf4j
public class CustomListenerHandler implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        //解析message属性
        MessageProperties messageProperties = message.getMessageProperties();
        //解析body消息体
        String body = new String(message.getBody(), messageProperties.getContentEncoding());

        log.info("-----------------触发自定义监听器-------------------");
        log.info("messageProperties:" + messageProperties.toString());
        log.info("body:" + body);
    }
}
