package ustc.sse.tims.model;


/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.model
 * @date 2019/4/3-14:52
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */
public class IPv4Address {
    private String ipstr;
    private String [] ipv4_addr;
    private String first_f=null;
    private String second_f=null;
    private String thrid_f=null;
    private String last_f=null;
    private String category=null;

    public IPv4Address(){
        ipv4_addr=null;
    }

    public IPv4Address(String ipstr) throws Exception {

        String [] tmp = ipstr.split("\\.");

        if(tmp.length!=4){
            throw new Exception("cannot resolve the given ipstr");
        }
        else if(!check_ip(tmp)){
            throw new Exception("cannot resolve the given ipstr");
        }else {
            category=setCategory(tmp[0]);
            ipv4_addr = tmp;
            first_f=ipv4_addr[0];
            second_f=ipv4_addr[1];
            thrid_f=ipv4_addr[2];
            last_f=ipv4_addr[3];
            this.ipstr=ipstr;
        }
    }

    private String setCategory(String s) {

        int a = Integer.parseInt(s);
        if(a<128){
            return "A";
        }else if(a<192){
            return "B";
        }else if(a<224){
            return "C";
        }else if(a<240){
            return "D";
        } else return "E";
    }


    private Boolean check_ip(String[] tmp){
        boolean b = true;
        for(String f:tmp){
            int x = Integer.parseInt(f);
            if(x<0 || x>255) b=false;
        }
        return b;
    }


    public Boolean inASeg(IPv4Address addr){
        System.out.println(this.getSeg());
        if(this.getSeg() == addr.getSeg()) return true;
        else return false;
    }


    public String getSeg(){
        return first_f+"."+second_f;
    }

    public String getHost(){
        return "-"+thrid_f+"."+last_f;
    }
}
