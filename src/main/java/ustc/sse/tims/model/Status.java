package ustc.sse.tims.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.model
 * @date 2019/3/8-9:16
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */
public class Status {

    @XmlType(name="hostState")
    public enum State {up, down, you};

    private State state = State.up;
    private String reason;

    @XmlAttribute(name="state")
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }


    @XmlAttribute(name="reason")
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Status [state=" + getState() + ", reason=" + getReason() + "]";
    }

}
