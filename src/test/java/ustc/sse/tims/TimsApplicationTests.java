package ustc.sse.tims;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ustc.sse.tims.util.FingerPrintUtil;
import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TimsApplicationTests {

    FingerPrintUtil fingerPrintUtil = new FingerPrintUtil();

    private String dhcp_option55 = "1,121,3,6,15,119,252,95,44,46";
    //private String dhcp_option55 = "1,33,3,6,15,26,28,51,58,59";
    //private String dhcp_option55 = "1,3,6,15,26,28,51,58,59";
    //private String dhcp_option55 = "1,28,2,3,15,6,119,12,44,47,26,121,42,121,249,252,42";

    @Test
    public void contextLoads() {
    }

    @Test
    public void testDHCPBank() throws IOException {
        String result = fingerPrintUtil.getFingerPrintbyOpt55(dhcp_option55);
        System.out.println(result);

    }

}
