package ustc.sse.tims.bean;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.bean
 * @date 2019/5/16-9:25
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */
public class IpAssignment {

    public String opt55;
    public String ip;
    public FingerPrint fingerPrint;
    public Integer access;

    public IpAssignment(){}

    public IpAssignment(String ip,FingerPrint fingerPrint){
        this.ip = ip;
        this.fingerPrint =fingerPrint;
    }


    public String getOpt55() {
        return opt55;
    }

    public void setOpt55(String opt55) {
        this.opt55 = opt55;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public FingerPrint getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(FingerPrint fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    @Override
    public String toString() {
        return "IpAssignment{" +
                "opt55='" + opt55 + '\'' +
                ", ip='" + ip + '\'' +
                ", fingerPrint=" + fingerPrint +
                ", access=" + access +
                '}';
    }
}
