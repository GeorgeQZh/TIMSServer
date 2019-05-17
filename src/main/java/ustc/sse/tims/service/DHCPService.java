package ustc.sse.tims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ustc.sse.tims.bean.Device;
import ustc.sse.tims.bean.FingerPrint;
import ustc.sse.tims.mapper.DHCPMapper;


/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.service
 * @date 2019/5/14-13:18
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:   同步 将数据库的操作 与 redis缓存 操作
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


    @Cacheable(cacheNames={"fingerPrints"},key="#{option55}")
    public FingerPrint qureyFingerPrint(String option55){
        FingerPrint fp = dhcpMapper.getFingerPrintByOpt55(option55);
        return fp;
    }



}
