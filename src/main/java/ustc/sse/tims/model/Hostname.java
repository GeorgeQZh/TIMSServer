package ustc.sse.tims.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.model
 * @date 2019/3/8-9:15
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */
public class Hostname {

    private String hostname;

    public Hostname(String hostname) {
        this();
        setHostname(hostname);
    }
    public Hostname(){}

    @XmlAttribute(name="name")
    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public String toString() {
        return "Hostname [hostname=" + hostname + "]";
    }


}