package ustc.sse.tims.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ustc.sse.tims.config.SystemConfig;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.util
 * @date 2019/5/18-12:46
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */
@Component
public class Constant {

    public static String tims_server_addr;
    public static String tims_server_port;

    public static String redis_host;
    public static int redis_port;
    public static String redis_password;
    public static String ip_regex;

    public static String fp_api_url;
    public static String fp_api_key;



    @Autowired
    public void setParams(SystemConfig conf){

        tims_server_addr = conf.getTims_server_address();
        tims_server_port = conf.getTims_server_port();

        redis_host = conf.getRedis_host();
        redis_port = conf.getRedis_port();
        redis_password = conf.getRedis_password();
        ip_regex = conf.getIp_pattern_regex();

        fp_api_key = conf.getFinger_print_api_permission();
        fp_api_url = conf.getFinger_print_api_url();

    }


}
