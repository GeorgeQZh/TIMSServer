package ustc.sse.tims.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import redis.clients.jedis.Jedis;
import ustc.sse.tims.api.RedisAPI;
import ustc.sse.tims.bean.IpAssignment;
import ustc.sse.tims.service.DHCPService;
import ustc.sse.tims.util.FingerPrintUtil;
import ustc.sse.tims.util.RedisUtil;

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


    @GetMapping("/dhcps")
    public String getDHCP(Model model) throws IOException {

        //查询ip与option55缓存
        Map<String,String> ipOpts = RedisAPI.getIpOpts();

        //todo 查询数据库 或 缓存


        //调用在线API
        ArrayList<IpAssignment> ipAssignments = fingerPrintUtil.getIpAssignments(ipOpts);


        //todo 存储结果

        model.addAttribute("ipAssignments",ipAssignments);
        return "dhcps";
    }
}