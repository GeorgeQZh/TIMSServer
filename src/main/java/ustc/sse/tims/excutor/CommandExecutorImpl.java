package ustc.sse.tims.excutor;

import ustc.sse.tims.model.Command;
import ustc.sse.tims.model.ExecutionObjectFactory;
import ustc.sse.tims.model.Scan;
import ustc.sse.tims.model.ScriptHelp;
import ustc.sse.tims.util.TransInfoHtml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.excutor
 * @date 2019/3/8-9:41
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */
public class CommandExecutorImpl  implements CommandExecutor{
    private Command cmd;
    //获取系统临时文件目录路径
    private String tempPath = System.getProperty("java.io.tmpdir")+"/";
    private Thread commandThread;
    private ArrayList<CommandExecutorObserver> observers = new ArrayList<CommandExecutorObserver>();

    //构造器：传入命令参数
    public CommandExecutorImpl(Command command) {
        this();
        cmd=command;
    }

    public CommandExecutorImpl(){};


    /**
     * 执行指令的关键部分！！！
     * @return
     */
    @Override
    public boolean execute(){

        String[] command = composeCommand();   //构造指令

        try {
            //1、调用系统命令行执行命令
            Process p = Runtime.getRuntime().exec(command);

            //获取连接的标准输入流
            final InputStream stream = p.getInputStream();
            //获取连接的错误输入流
            final InputStream errors = p.getErrorStream();

            //创建进程，执行命令
            commandThread = new Thread(new Runnable() {
                public void run() {
                    BufferedReader reader = null;
                    BufferedReader errorReader = null;

                    //2、处理返回结果
                    try {
                        boolean firstLine=true;
                        //标准输入流缓冲区
                        reader = new BufferedReader(new InputStreamReader(stream));
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            line=escape(line);
                            if (line.contains( " open "))
                                line="<span class=\"open\">"+line+"</span>";
                            else if (line.contains( " closed "))
                                line="<span class=\"closed\">"+line+"</span>";
                            else if (line.contains( " filtered "))
                                line="<span class=\"filtered\">"+line+"</span>";
                            String jump = "\n";
                            if(firstLine)
                                jump="";
                            cmd.getOutput().setText(cmd.getOutput().getText()+jump+line);
                            firstLine=false;
                        }

                        errorReader = new BufferedReader(new InputStreamReader(errors));
                        while ((line = errorReader.readLine()) != null) {
                            line=escape(line);
                            line="<span class=\"closed\">"+line+"</span>";
                            String jump = "\n";
                            if(firstLine)
                                jump="";
                            cmd.getOutput().setText(cmd.getOutput().getText()+jump+"<i>"+line+"</i>");
                            firstLine=false;

                        }

                        System.out.println("结果：   "+cmd.getOutput().getText());

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        readXML();             //??,有问题
                        cmd.setFinished(true);
                        notifyEnd();
                        if (reader != null) {
                            try {
                                reader.close();
                            } catch (IOException e) {
                                cmd.setFinished(true);
                                notifyEnd();
                            }
                        }
                    }
                }
            });
            commandThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 指令构造，添加结果重定向参数，输出到临时文件路径下
     * @return 构造的完整的nmap指令:  nmap -option -oX -tempPath --webxml
     */
    private String[] composeCommand()	{

        //创建扫描结果文件
        String filename= "nmap-scan_" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
                .format(new Date())+ ".xml";
        this.cmd.getOutput().setFilename(filename);
        tempPath=tempPath + filename;
        List<String> commandList = new ArrayList<String>();
        commandList.add("nmap");
        commandList.addAll(splitOptions());
        commandList.addAll(Arrays.asList(new String[]{"-oX" , getTempPath(), "--webxml"}));

        System.out.println(commandList.toString());

        return commandList.toArray(new String[]{});

    }

    /**
     * 解析选项参数
     * @return 选项参数数组
     */
    private List<String>  splitOptions(){
        List<String> options = new ArrayList<>();
        //Splits string by spaces other than the ones in substring quotes
        Matcher matcher = Pattern.compile("\\s*([^(\"|\')]\\S*|\".+?\"|\'.+?\')\\s*").matcher(cmd.getText());
        while (matcher.find())
            options.add(matcher.group(1));
        return options;
    }

    /**
     * 特殊字符替换
     * @param str
     * @return
     */
    private String escape(String str) {
        String line=str;
        line = line.replace("&", "&amp;");
        line = line.replace( "\"", "&quot;");
        line = line.replace( "<", "&lt;");
        line = line.replace( ">", "&gt;");
        return line;
    }
    public Command getCmd() {
        return cmd;
    }


    public void setCmd(Command cmd) {
        this.cmd = cmd;
    }


    public Thread getCommandThread() {
        return commandThread;
    }


    public String getTempPath() {
        return tempPath;
    }


    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }

    /**
     *
     */
    public void readXML() {

        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(tempPath))){
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                sb.append(sCurrentLine);
                System.out.println(sCurrentLine);
            }

            JAXBContext jaxbContext = JAXBContext.newInstance(ExecutionObjectFactory.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(sb.toString());
            System.out.println("0");

            //???
            Object execution = unmarshaller.unmarshal(reader);
            System.out.println("1");

            cmd.getOutput().setXml(TransInfoHtml.transformToHtml(sb.toString()));
            if (execution instanceof Scan){
                cmd.getOutput().setScan((Scan) execution);
            }

            else if (execution instanceof ScriptHelp){
                cmd.getOutput().setScriptHelp((ScriptHelp) execution);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //回调函数
    @Override
    public void addObserver(CommandExecutorObserver observer) {
        this.observers.add(observer);
    }
    @Override
    public void removeObserver(CommandExecutorObserver observer) {
        this.observers.remove(observer);
    }
    @Override
    public void notifyEnd() {
        if(!observers.isEmpty()){
            for(CommandExecutorObserver observer : observers){
                observer.finishedCommand(cmd);
            }
        }

    }

}
