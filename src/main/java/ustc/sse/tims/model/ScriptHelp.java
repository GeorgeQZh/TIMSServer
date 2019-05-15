package ustc.sse.tims.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.model
 * @date 2019/3/8-9:11
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */

@XmlRootElement(name = "nse-scripts")
public class ScriptHelp {

    private List<Script> scripts;

    @XmlElement(name="script")
    public List<Script> getScripts() {
        return scripts;
    }

    public void setScripts(List<Script> scripts) {
        this.scripts = scripts;
    }

    @Override
    public String toString() {
        return "Scripts [" + getScripts() + "]";
    }
}
