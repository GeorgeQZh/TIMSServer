package ustc.sse.tims.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
 * @Description:
 */
@Controller
public class DHCPController {

    @Autowired
    DHCPService dhcpService;



    @GetMapping("/dhcps")
    public String getDHCP(Model model) throws IOException {

        //todo 查询redis中的 ip_options

//        //访问redis数据库，获取ips--dhcp_o_55 列表
//        Map<String,String> ips_fingers = dhcpService.getIps();

        Map<String,String> ipOpts = getIpOpts();

        //todo 查询数据库 或 缓存


        FingerPrintUtil fingerPrintUtil =new FingerPrintUtil();
        ArrayList<IpAssignment> ipAssignments = fingerPrintUtil.getIpAssignments(ipOpts);

        model.addAttribute("ipAssignments",ipAssignments);
        return "dhcps";
    }

    private Map< String,String> getIpOpts(){
        Map<String,String> map = new HashMap<>();
        map.put("172.16.72.151","1,121,3,6,15,119,252,95,44,46");
        map.put("172.16.72.152","1,33,3,6,15,26,28,51,58,59");
        map.put("172.16.72.153","1,3,6,15,26,28,51,58,59");
//        map.put("172.16.72.154","1,28,2,3,15,6,119,12,44,47,26,121,42,121,249,252,42");

        return map;
    }


}