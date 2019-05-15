package ustc.sse.tims.bean;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.bean
 * @date 2019/5/14-12:44
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */
public class Device {
    private int id;
    private String ip_hold;
    private String option55;
    private String dev_name;
    private String os_name;
    private String version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp_hold() {
        return ip_hold;
    }

    public void setIp_hold(String ip_hold) {
        this.ip_hold = ip_hold;
    }

    public String getOption55() {
        return option55;
    }

    public void setOption55(String option55) {
        this.option55 = option55;
    }

    public String getDev_name() {
        return dev_name;
    }

    public void setDev_name(String dev_name) {
        this.dev_name = dev_name;
    }

    public String getOs_name() {
        return os_name;
    }

    public void setOs_name(String os_name) {
        this.os_name = os_name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", ip_hold='" + ip_hold + '\'' +
                ", option55='" + option55 + '\'' +
                ", dev_name='" + dev_name + '\'' +
                ", os_name='" + os_name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

}
