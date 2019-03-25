package ustc.sse.tims.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
 * @Package ustc.sse.tims.controller
 * @date 2019/3/13-23:18
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */

@Controller
public class ScanController implements CommandExecutorObserver {

    private List<Command> ongoingCommands  = new ArrayList<Command>();
    private List<Command> finishedCommands = new ArrayList<Command>();
    private Command command;
    private boolean finishedCommandQueued=false;
    private String code ="nmap -O --osscan-guess ";
    private InitialConfigurator config = new InitialConfigurator();


    @PostConstruct
    public void init(){
        config.configure();
    }

    @PostMapping("/ip/{target-ip}")
    public String scanIp(@PathVariable("target-ip") String targetIp, Model model){

        code +=targetIp;
                command =  new Command(code);
        ongoingCommands.add(0,command);
        executeCommand(command);
        model.addAttribute("command", command);
        model.addAttribute("commands", ongoingCommands);
        System.out.println("开始扫描");

        return "scan";
    }

    @GetMapping("/ips/{first-ip},{last-ip}")
    public String scanIp(@PathVariable("first-ip") String fistIp,
                         @PathVariable("last-ip") String lastIP,
                         Model model){

        code+= fistIp+""+lastIP ;
        command =  new Command(code);
        ongoingCommands.add(0,command);
        executeCommand(command);
        model.addAttribute("command", command);
        model.addAttribute("commands", ongoingCommands);
        System.out.println("开始扫描");

        return "scan::output";
    }



    @GetMapping("/nmap/removeCommand")
    public String removeCommand(Model model, @RequestParam int index) {
        finishedCommands.remove(index);
        model.addAttribute("command", command);
        model.addAttribute("finishedCommands", finishedCommands);

        return "fragments/contents :: finished";
    }


    @GetMapping("/nmap/update")
    public String updateOut(Model model) {

        model.addAttribute("command", command);
        model.addAttribute("commands", ongoingCommands);

        return "fragments/contents :: ongoing";
    }

    @GetMapping("/nmap/update-finished")
    public String updateEnded(Model model) {

        model.addAttribute("command", command);
        model.addAttribute("finishedCommands", finishedCommands);
        finishedCommandQueued=false;
        return "fragments/contents :: finished";
    }

    @GetMapping("/nmap/finishedQueued")
    public @ResponseBody
    Boolean updateEnd() {
        return finishedCommandQueued;
    }
    @GetMapping("/nmap/stopUpdating")
    public @ResponseBody Boolean stopUpdating() {
        return ongoingCommands.isEmpty();
    }

    @GetMapping("/nmap/download/{filename}")
    public ResponseEntity<InputStreamResource> download(@PathVariable("filename") String filename) {

        InputStream file;
        try {
            file = new Filefinder().find(filename);
            InputStreamResource resource = new InputStreamResource(file);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("application/octect-stream"))
                    .body(resource);
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }



    public void finishedCommand(Command cmd){
        ongoingCommands.remove(cmd);
        finishedCommands.add(0,cmd);
        finishedCommandQueued = true;
    }

    public void executeCommand(Command command){
        CommandExecutor executor = new CommandExecutorImpl(command);
        executor.addObserver(this);
        executor.execute();
    }
}
