package ustc.sse.tims.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.bean
 * @date 2019/5/15-23:33
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */
public class FingerPrint {
    private Integer score;
    private String version;
    public String device_name;
    @JsonIgnore
    public Device device;
    @JsonIgnore
    public String opt55;

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getOpt55() {
        return opt55;
    }

    public void setOpt55(String opt55) {
        this.opt55 = opt55;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }


    @Override
    public String toString() {
        return "FingerPrint{" +
                "score=" + score +
                ", version='" + version + '\'' +
                ", device_name='" + device_name + '\'' +
                ", device=" + device +
                ", opt55='" + opt55 + '\'' +
                '}';
    }
}
