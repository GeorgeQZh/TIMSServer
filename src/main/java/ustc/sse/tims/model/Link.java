package ustc.sse.tims.model;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.model
 * @date 2019/3/8-9:13
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */
public class Link {

    private Host source;
    private Host target;

    public Link(Host source, Host target) {
        super();
        setSource(source);
        setTarget(target);
    }

    public Host getSource() {
        return source;
    }
    public void setSource(Host source) {
        this.source = source;
    }
    public Host getTarget() {
        return target;
    }
    public void setTarget(Host target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "Link [source=" + getSource() + ", target=" + getTarget() + "]";
    }

}