package ustc.sse.tims.bean;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.model
 * @date 2019/3/8-10:55
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */


public class User {

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

    private String userName;
    private String passWord;


}

