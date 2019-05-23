package ustc.sse.tims.service;

import org.apache.ibatis.annotations.Param;
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

    @Cacheable(cacheNames={"devices"},key="#id")
    public Device getDevice(Integer id){
        Device dev = dhcpMapper.getDevById(id);
        return dev;
    }

    @Cacheable(cacheNames = {"devices"},key="#dev.id")
    public void SetDevice(Device dev){
        dhcpMapper.setDev(dev);
    }


    @Cacheable(cacheNames={"fingerPrints"},key="#opt55")
    public FingerPrint getFingerPrint(String opt55){
        FingerPrint fp = dhcpMapper.getFingerPrintByOpt55(opt55);
        return fp;
    }


    @Cacheable(cacheNames = {"fingerPrints"})
    public void setFingerPrint(FingerPrint fp){
        dhcpMapper.setFingerPrintByOpt55(fp);
    }
}
