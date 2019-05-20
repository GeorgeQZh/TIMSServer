package ustc.sse.tims.model;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.model
 * @date 2019/3/8-9:27
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */

@XmlRegistry
public class ExecutionObjectFactory {

    public Scan createScan() {
        return new Scan();
    }

    public ScriptHelp createScriptHelp() {
        return new ScriptHelp();
    }
}