package ustc.sse.tims.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import ustc.sse.tims.bean.FingerPrint;
import ustc.sse.tims.bean.IpAssignment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.util
 * @date 2019/5/15-23:20
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */
public class FingerPrintUtil {

    private String baseUrl = "https://api.fingerbank.org/api/v2/combinations/interrogate";
    private String keyStr="3a6a11c4598c7e042043e93afcfca24a856b5b85";

    private ObjectMapper mapper = new ObjectMapper();


    public String getFingerPrintbyOpt55(String opt55){

        Map<String, String> params = new HashMap<String, String>();
        params.put("dhcp_fingerprint", opt55);
        params.put("key",keyStr);
        String result = HttpRequestHelper.GetPostUrl(baseUrl, params,
                "GET",null, 0, 0);
        return result;

    }

    // 传入map <ip,option55>
    // 返回分配情况 IpAssignment
    public ArrayList<IpAssignment> getIpAssignments (Map<String,String> ip_opts) throws IOException {

        ArrayList <IpAssignment> ipAssignments = new ArrayList<>();
        for(String ip: ip_opts.keySet()){
            String option55 = ip_opts.get(ip);
            String fp_str = getFingerPrintbyOpt55(option55);
            FingerPrint fingerPrint = resolveJason(fp_str);

            System.out.println("fp:"+fp_str);
            IpAssignment ipAssignment = new IpAssignment(ip,fingerPrint);
            ipAssignments.add(ipAssignment);
        }
        return ipAssignments;
    }

    public FingerPrint resolveJason(String finger_str) throws IOException {

        FingerPrint fingerPrint = mapper.readValue(finger_str, FingerPrint.class);

        System.out.println("fingerPrint:" + fingerPrint.toString());
        return fingerPrint;

    }



}
