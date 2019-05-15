package ustc.sse.tims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ustc.sse.tims.bean.Device;
import ustc.sse.tims.mapper.DHCPMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.service
 * @date 2019/5/14-13:18
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */

@Service
public class DHCPService {

    @Autowired
    DHCPMapper dhcpMapper;

    @Cacheable(cacheNames={"devs"},key="#{option55}")
    public Device getDev(String option55){
        Device dev = dhcpMapper.getDevByOpt55(option55);
        return dev;
    }


    //如何只对redis操作?????
    @Cacheable()
    public Map<String,String> getIps(){
        return new HashMap<String, String>();
    }



}
