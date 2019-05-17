package ustc.sse.tims.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.config
 * @date 2019/5/17-20:05
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */

@Configuration
@PropertySource("classpath:SystemConfig.properties")
public class SystemConfig {

    @Value("${finger_print_api_url}")
    public String finger_print_api_url;

    @Value("${finger_print_api_permission}")
    public String finger_print_api_permission;

    @Value("${redis_host}")
    public String redis_host;

    @Value("${redis_port}")
    public int redis_port;

    @Value("${redis_password}")
    public String redis_password;



    public String getFinger_print_api_url() {
        return finger_print_api_url;
    }

    public void setFinger_print_api_url(String finger_print_api_url) {
        this.finger_print_api_url = finger_print_api_url;
    }

    public String getFinger_print_api_permission() {
        return finger_print_api_permission;
    }

    public void setFinger_print_api_permission(String finger_print_api_permission) {
        this.finger_print_api_permission = finger_print_api_permission;
    }

    public String getRedis_host() {
        return redis_host;
    }

    public void setRedis_host(String redis_host) {
        this.redis_host = redis_host;
    }

    public int getRedis_port() {
        return redis_port;
    }

    public void setRedis_port(int redis_port) {
        this.redis_port = redis_port;
    }

    public String getRedis_password() {
        return redis_password;
    }

    public void setRedis_password(String redis_password) {
        this.redis_password = redis_password;
    }
}
