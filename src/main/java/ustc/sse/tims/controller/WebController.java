package ustc.sse.tims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


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

    @GetMapping("/index")
    public String toPageIndex() {
        return "index";
    }

    @GetMapping("/table")
    public String toPageTable() {
        return "table";
    }

    @GetMapping("/scan")
    public String toPageScan() {
        return "scan";
    }

    @GetMapping("/setting")
    public String toSetting(){return "setting";}

    @GetMapping("/help")
    public String toHelp(){
        return "help";
    }

    @GetMapping("/management")
    public String toManagement(){return "management";}

    @GetMapping("/map")
    public String toMap(){return "map";}

}