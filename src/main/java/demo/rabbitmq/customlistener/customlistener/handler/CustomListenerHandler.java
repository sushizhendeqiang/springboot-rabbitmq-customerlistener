package demo.rabbitmq.customlistener.customlistener.handler;

import com.rabbitmq.client.Channel;
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
public class CustomListenerHandler implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        //解析message属性
        MessageProperties messageProperties = message.getMessageProperties();
        //解析body消息体
        String body = new String(message.getBody(), messageProperties.getContentEncoding());

        System.out.println("-----------------触发自定义监听器-------------------");
        System.out.println("messageProperties:" + messageProperties.toString());
        System.out.println("body:" + body);
    }
}
