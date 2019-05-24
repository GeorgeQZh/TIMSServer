package ustc.sse.tims.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ustc.sse.tims.api.RedisAPI;
import ustc.sse.tims.bean.Device;
import ustc.sse.tims.bean.FingerPrint;
import ustc.sse.tims.bean.IpAssignment;
import ustc.sse.tims.mapper.DHCPMapper;
import ustc.sse.tims.service.DHCPService;
import ustc.sse.tims.util.FingerPrintUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims
 * @date 2019/3/8-9:47
 * @Copyright: (c) 2019 USTC. All rights reserved.
 *
 * @Description:  处理DHCP页面设备识别结果请求。
 *  过程：
 *      1、使用Jedis查询redis，得到IP与指纹 缓存数据
 *      2、根据指纹信息查询设备信息
 *          1）优先在数据库或缓存中查询
 *          2）本地没有调用在线API,并将结果存入数据库中
 */

@Controller
public class DHCPController {

    @Autowired
    DHCPService dhcpService;

    @Autowired
    FingerPrintUtil fingerPrintUtil;

    @Autowired
    DHCPMapper dhcpMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());


    @GetMapping("/dhcps")
    public String getDHCP(Model model) throws IOException {

        //保存ip分配情况
        List<IpAssignment> ipAssignments =new ArrayList<>();

        //查询ip与option55缓存
        Map<String,String> ipOpts = RedisAPI.getIpOpts();

        FingerPrint fp;
        Map<String,String> ns_ipOpts = new HashMap<>();

        //先 查询数据库 或 缓存
        for(String ip : ipOpts.keySet()){
            if(null != ( fp= dhcpService.getFingerPrint(ipOpts.get(ip)))){
                Device dev = dhcpService.getDevice(fp.device.id);
                IpAssignment ia = new IpAssignment(ip,fp);
                ipAssignments.add(ia);
            }
            else {
                ns_ipOpts.put(ip,ipOpts.get(ip));
            }
        }

        //数据库中没有的 再调用在线API
        List<IpAssignment> ias = fingerPrintUtil.getIpAssignments(ns_ipOpts);
        for(IpAssignment ia : ias){
            //保存fingerprint
            saveFingerPrint(ia.fingerPrint);
        }

        ipAssignments.addAll(ias);
        model.addAttribute("ipAssignments",ipAssignments);
        return "dhcps";
    }


    /**
     * 判断 数据库中 是否已存在 fingerprint 及其 parents 和 device
     *      不存在则进行持久化
     * @param fp
     */
    private void saveFingerPrint(FingerPrint fp){

        List<Device> dev_parents ;

        //parents
        if(null != (dev_parents = fp.device.getParents())){

            for(Device dev : dev_parents){
                //先查询是否已存在
                if(null == dhcpService.getDevice(dev.id)){
                    try{dhcpService.SetDevice(dev);}
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    finally {
                        dhcpMapper.updateDev(dev);
                    }
                }
            }
        }

        //device
        if(null == dhcpService.getDevice(fp.device.id)){
            try{dhcpService.SetDevice(fp.device);}
            catch (Exception e){e.printStackTrace();}
            finally {
                dhcpMapper.updateDev(fp.device);
            }
        }

        //FingerPrint
        if(null == dhcpService.getFingerPrint(fp.opt55)){
            try{dhcpService.setFingerPrint(fp);}
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                dhcpMapper.updateFingerPrint(fp);
            }
        }

    }
}