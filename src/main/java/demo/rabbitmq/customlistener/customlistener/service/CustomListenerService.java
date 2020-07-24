package demo.rabbitmq.customlistener.customlistener.service;

/**
 * @Author: sush4
 * @Description:
 * @Date: 2020/7/24
 */
public interface CustomListenerService {
    /**
     * 新增监听
     * @param queueName
     */
    void addNewListener(String queueName);

    /**
     * 删除监听
     * @param queueName
     * @return 返回 true 成功
     */
    boolean removeListener(String queueName);
}
