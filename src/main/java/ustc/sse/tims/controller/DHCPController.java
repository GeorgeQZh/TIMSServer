package ustc.sse.tims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ustc.sse.tims.bean.Device;
import ustc.sse.tims.service.DHCPService;
import ustc.sse.tims.util.HttpRequestHelper;

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

    private String baseUrl = "https://api.fingerbank.org/api/v2/combinations/interrogate";
    private String keyStr="3a6a11c4598c7e042043e93afcfca24a856b5b85";
    private String dhcp_option55 = "1,121,3,6,15,119,252,95,44,46";
    //private String dhcp_option55 = "1,33,3,6,15,26,28,51,58,59";
    //private String dhcp_option55 = "1,3,6,15,26,28,51,58,59";
    //private String dhcp_option55 = "1,28,2,3,15,6,119,12,44,47,26,121,42,121,249,252,42";

    @GetMapping("/dhcp")
    public String getDHCP() {

        Map<String, String> params = new HashMap<String, String>();
        params.put("dhcp_fingerprint", dhcp_option55);
        params.put("key",keyStr);
        String result1 = HttpRequestHelper.GetPostUrl(baseUrl, params,
                "GET",null, 0, 0);
        System.out.println(result1);



        //查询数据库。。。dhcp缓存或者dhcp代理服务器
        if(!dhcpService.getDev(dhcp_option55).getIp_hold().equals("")){

        }

        return "dhcp";
    }

    @GetMapping("/dhcps")
    public String getDHCPS(Model model) {


        //访问redis数据库，获取ips--dhcp_o_55 列表
        Map<String,String> ips_fingers = dhcpService.getIps();


        //访问数据库，查询DHCP_o_55
        for(String ip:ips_fingers.keySet()){

            Device dev = dhcpService.getDev(ips_fingers.get(ip));
            model.addAttribute(dev);
        }

        return "dhcps";
    }
}