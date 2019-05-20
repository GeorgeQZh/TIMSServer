package ustc.sse.tims.excutor;

import ustc.sse.tims.model.Command;


/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.excutor
 * @date 2019/3/8-9:40
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */


public interface CommandExecutorObserver {

    public void finishedCommand(Command cmd);

}
