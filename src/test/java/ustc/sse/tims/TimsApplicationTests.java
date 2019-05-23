package ustc.sse.tims;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ustc.sse.tims.api.RedisAPI;
import ustc.sse.tims.bean.IpAssignment;
import ustc.sse.tims.util.FingerPrintUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TimsApplicationTests {

    FingerPrintUtil fingerPrintUtil = new FingerPrintUtil();

    @Autowired
    RedisAPI redisAPI;

    private String dhcp_option55 = "1,121,3,6,15,119,252,95,44,46";
    //private String dhcp_option55 = "1,33,3,6,15,26,28,51,58,59";
    //private String dhcp_option55 = "1,3,6,15,26,28,51,58,59";
    //private String dhcp_option55 = "1,28,2,3,15,6,119,12,44,47,26,121,42,121,249,252,42";

    @Test
    public void contextLoads() {
    }

    @Test
    public void testDHCPBank() throws IOException {
        String result = fingerPrintUtil.getFingerPrintByOpt55(dhcp_option55);
//        System.out.println(result);

    }

    @Test
    public void testRedis(){

        Map<String,String> map = new HashMap<>();
        map.put("172.16.72.151","1,121,3,6,15,119,252,95,44,46");
        map.put("172.16.72.152","1,33,3,6,15,26,28,51,58,59");
        map.put("172.16.72.153","1,3,6,15,26,28,51,58,59");
//        map.put("172.16.72.154","1,28,2,3,15,6,119,12,44,47,26,121,42,121,249,252,42");
//        redisAPI.setIpOpts(map);
    }

    @Test
    public void testRedisGetKeys(){

//        redisAPI.getIpOpts();

    }

    @Test
    public void DHCPQuery() throws IOException {

//        Map<String,String> ipOpts = redisAPI.getIpOpts();

        Map<String,String> map = new HashMap<>();
        map.put("172.16.72.151","1,121,3,6,15,119,252,95,44,46");
        map.put("172.16.72.152","1,33,3,6,15,26,28,51,58,59");

        FingerPrintUtil fingerPrintUtil =new FingerPrintUtil();
        ArrayList<IpAssignment> ipAssignments = fingerPrintUtil.getIpAssignments(map);

    }

}
