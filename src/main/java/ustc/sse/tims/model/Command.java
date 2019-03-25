package ustc.sse.tims.model;

import org.springframework.stereotype.Service;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.model
 * @date 2019/3/8-9:09
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */

@Service
public class Command {

    private String text;  //命令
    private boolean finished = false; //是否执行结束
    private Output output = new Output(); //结果
    private boolean chkUpdateFlag = false; //??检测更新标志？？

    public Command() {}

    public Command(String text) {
        this();
        setText(text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    public boolean isChkUpdateFlag() {
        return chkUpdateFlag;
    }

    public void setChkUpdateFlag(boolean chkUpdateFlag) {
        this.chkUpdateFlag = chkUpdateFlag;
    }


}