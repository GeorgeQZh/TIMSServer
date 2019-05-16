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
    public String ip;
    public FingerPrint fingerPrint;

    public IpAssignment(){}

    public IpAssignment(String ip,FingerPrint fingerPrint){
        this.ip = ip;
        this.fingerPrint =fingerPrint;
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
                "ip='" + ip + '\'' +
                ", fingerPrint=" + fingerPrint +
                '}';
    }
}
