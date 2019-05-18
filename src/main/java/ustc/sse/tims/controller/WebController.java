package ustc.sse.tims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ustc.sse.tims.config.SystemConfig;


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
    public String toManagement(){return "management";}

    @GetMapping("/map")
    public String toMap(){return "map";}

}