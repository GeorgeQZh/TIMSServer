package ustc.sse.tims.excutor;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.excutor
 * @date 2019/3/8-9:38
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */
public interface CommandExecutor {
    boolean execute();
    public void addObserver(CommandExecutorObserver observer) ;
    public void removeObserver(CommandExecutorObserver observer);
    public void notifyEnd();

}
