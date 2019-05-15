package ustc.sse.tims;

import ustc.sse.tims.excutor.CommandExecutor;
import ustc.sse.tims.excutor.CommandExecutorImpl;
import ustc.sse.tims.excutor.CommandExecutorObserver;
import ustc.sse.tims.model.Command;
import ustc.sse.tims.model.Script;
import ustc.sse.tims.model.ScriptHelp;
import ustc.sse.tims.model.menu.Menu;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims
 * @date 2019/3/8-9:48
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */
public class InitialConfigurator implements CommandExecutorObserver {

    private Map<String,List<Script>> scriptCategories = new HashMap<>();
    private Menu menu;

    public Map<String, List<Script>> getScriptCategories() {
        return scriptCategories;
    }

    public void setScriptCategories(Map<String, List<Script>> scriptCategories) {
        this.scriptCategories = scriptCategories;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

//    public void configure(){
//
//        try {
//            loadMenu();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        Command command =  new Command("--script-help all");
//        CommandExecutor executor = new CommandExecutorImpl(command);
//        executor.addObserver(this);
//        executor.execute();
//    }

//    private void loadMenu() throws Exception {
//        InputStream xml = InitialConfigurator.class.getClassLoader()
//                .getResourceAsStream("menu.xml");
//        System.out.println("1");
//        JAXBContext jaxbContext = JAXBContext.newInstance(Menu.class);
//        System.out.println("2");
//        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//        Menu menu = (Menu)unmarshaller.unmarshal(xml);
//        this.setMenu(menu);
//
//    }

    @Override
    public void finishedCommand(Command cmd) {
        computeMap(cmd.getOutput().getScriptHelp());
    }

    private void computeMap(ScriptHelp scriptHelp){
        if (scriptHelp!=null){
            for (Script script : scriptHelp.getScripts()){
                for (String category : script.getCategories()){
                    if (!scriptCategories.containsKey(category))
                        scriptCategories.put(category,new ArrayList<Script>());
                    scriptCategories.get(category).add(script);
                }
            }
        }
    }
}
