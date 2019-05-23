package ustc.sse.tims.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ustc.sse.tims.api.RedisAPI;
import ustc.sse.tims.bean.Device;
import ustc.sse.tims.bean.FingerPrint;
import ustc.sse.tims.bean.IpAssignment;
import ustc.sse.tims.service.DHCPService;
import ustc.sse.tims.util.FingerPrintUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

    ArrayList<IpAssignment> ipAssignments;


    @GetMapping("/dhcps")
    public String getDHCP(Model model) throws IOException {

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

        //数据库中没有的 再调用在线API ; 结果存储 在fpUtil中进行
        ipAssignments.addAll(fingerPrintUtil.getIpAssignments(ns_ipOpts));

        model.addAttribute("ipAssignments",ipAssignments);
        return "dhcps";
    }
}