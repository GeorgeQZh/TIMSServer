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

    @Cacheable(cacheNames={"devices"})
    public Device getDevice(Integer id){
        return dhcpMapper.getDevById(id);
    }

    @Cacheable(cacheNames = {"devices"})
    public void SetDevice(@Param("dev") Device dev){
        dhcpMapper.setDev(dev);
    }


    @Cacheable(cacheNames={"fingerPrints"})
    public FingerPrint getFingerPrint(String opt55){
        return dhcpMapper.getFingerPrintByOpt55(opt55);
    }


    @Cacheable(cacheNames = {"fingerPrints"})
    public void setFingerPrint( FingerPrint fp){
        dhcpMapper.setFingerPrintByOpt55(fp);
    }
}
