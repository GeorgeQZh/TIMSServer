package ustc.sse.tims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ustc.sse.tims.api.RedisAPI;
import ustc.sse.tims.bean.IpAssignment;
import ustc.sse.tims.config.SystemConfig;
import ustc.sse.tims.util.FingerPrintUtil;

import java.io.IOException;
import java.util.ArrayList;
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
public class WebController{

    @Autowired
    SystemConfig config;

    @Autowired
    FingerPrintUtil fingerPrintUtil;

    @GetMapping("/index")
    public String toPageIndex() {
        return "index";
    }

//    @GetMapping("/table")
//    public String toPageTable() {
//        return "dhcps";
//    }

    @GetMapping("/scan")
    public String toPageScan() {
        return "scan";
    }

    @GetMapping("/setting")
    public String toSetting(Model model){
        model.addAttribute("config",config);
        return "setting";}

    @GetMapping("/help")
    public String toHelp(){
        return "help";
    }

    @GetMapping("/management")
    public String toManagement(Model model) throws IOException {

        Map<String,String> ipOpts = RedisAPI.getIpOpts();
        //调用在线API
        ArrayList<IpAssignment> ipAssignments = fingerPrintUtil.getIpAssignments(ipOpts);
        model.addAttribute("ipAssignments", ipAssignments);
        return "management";}

    @GetMapping("/map")
    public String toMap(){return "map";}

}