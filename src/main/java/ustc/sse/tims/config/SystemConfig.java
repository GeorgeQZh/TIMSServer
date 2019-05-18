package ustc.sse.tims.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

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
@Service
public class SystemConfig {

    @Value("${finger_print_api_url}")
    private String finger_print_api_url;

    @Value("${finger_print_api_permission}")
    private String finger_print_api_permission;

    @Value("${redis_host}")
    private String redis_host;

    @Value("${redis_port}")
    private int redis_port;

    @Value("${redis_password}")
    private String redis_password;

    @Value("${ip_pattern_regex}")
    private String ip_pattern_regex;

    //server 不支持动态配置
    @Value("${server.address}")
    private String tims_server_address;

    @Value("${server.port}")
    private String tims_server_port;

    public String getFinger_print_api_url() {
        return finger_print_api_url;
    }

    public String getFinger_print_api_permission() {
        return finger_print_api_permission;
    }

    public String getRedis_host() {
        return redis_host;
    }

    public int getRedis_port() {
        return redis_port;
    }

    public String getRedis_password() {
        return redis_password;
    }

    public String getIp_pattern_regex() {
        return ip_pattern_regex;
    }

    public String getTims_server_address() {
        return tims_server_address;
    }

    public String getTims_server_port() {
        return tims_server_port;
    }

    //todo 如何支持动态修改系统设置


    public void setFinger_print_api_url(String finger_print_api_url) {
        this.finger_print_api_url = finger_print_api_url;
    }

    public void setFinger_print_api_permission(String finger_print_api_permission) {
        this.finger_print_api_permission = finger_print_api_permission;
    }

    public void setRedis_host(String redis_host) {
        this.redis_host = redis_host;
    }

    public void setRedis_port(int redis_port) {
        this.redis_port = redis_port;
    }

    public void setRedis_password(String redis_password) {
        this.redis_password = redis_password;
    }

    public void setIp_pattern_regex(String ip_pattern_regex) {
        this.ip_pattern_regex = ip_pattern_regex;
    }

}
