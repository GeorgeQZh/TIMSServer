package ustc.sse.tims.model;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.model
 * @date 2019/3/8-9:16
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */
public class Hop {

    private String address;
    private String host;

    @XmlAttribute(name="ipaddr")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlAttribute(name="host")
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "Hop [address=" + getAddress() + ", host=" + getHost() + "]";
    }

    public Host toHost(){
        Host host = new Host();
        host.setAddress(new Address(address));
        Set<Hostname> set = new HashSet<Hostname>();
        if (host!=null)
            set.add(new Hostname(this.host));
        host.setHostNames(set);

        return host;
    }
}
