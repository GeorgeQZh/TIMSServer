package ustc.sse.tims.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ustc.sse.tims.InitialConfigurator;
import ustc.sse.tims.excutor.CommandExecutor;
import ustc.sse.tims.excutor.CommandExecutorImpl;
import ustc.sse.tims.excutor.CommandExecutorObserver;
import ustc.sse.tims.model.Command;
import ustc.sse.tims.util.Filefinder;
import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
    public String toPageIdex() {
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

}