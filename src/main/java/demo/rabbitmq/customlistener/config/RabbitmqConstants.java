package demo.rabbitmq.customlistener.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: sush4
 * @Description:
 * @Date: 2020/7/24
 */

@Component
@PropertySource("classpath:application.yml")
public class RabbitmqConstants {
    @Value("${spring.rabbitmq.managementurl}")
    public String managementurl;

    @Value("${spring.rabbitmq.addresses}")
    public String addresses;

    @Value("${spring.rabbitmq.username}")
    public String username;

    @Value("${spring.rabbitmq.password}")
    public String password;

    @Value("${spring.rabbitmq.virtual-host}")
    public String virtualHost;

    public String getManagementurl() {
        return managementurl;
    }

    public void setManagementurl(String managementurl) {
        this.managementurl = managementurl;
    }

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }
}
