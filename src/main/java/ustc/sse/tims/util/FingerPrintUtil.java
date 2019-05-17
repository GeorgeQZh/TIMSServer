package ustc.sse.tims.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ustc.sse.tims.bean.FingerPrint;
import ustc.sse.tims.bean.IpAssignment;
import ustc.sse.tims.config.SystemConfig;

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

    private ObjectMapper mapper = new ObjectMapper();

    private static String key;
    private static String url;
    private static Logger logger;

    @Autowired
    private void setParams(SystemConfig conf,LoggerFactory lf){
        key = conf.getFinger_print_api_permission();
        url = conf.getFinger_print_api_url();
        logger = lf.getLogger(getClass());
    }



    public String getFingerPrintByOpt55(String opt55){

        Map<String, String> params = new HashMap<String, String>();
        params.put("dhcp_fingerprint", opt55);   //API的格式要求
        params.put("key",key);
        String result = HttpRequestHelper.GetPostUrl(url, params,
                "GET",null, 0, 0);
        return result;

    }

    // 传入map <ip,option55>
    // 返回分配情况 IpAssignment
    public ArrayList<IpAssignment> getIpAssignments (Map<String,String> ip_opts) throws IOException {

        ArrayList <IpAssignment> ipAssignments = new ArrayList<>();
        for(String ip: ip_opts.keySet()){
            String option55 = ip_opts.get(ip);
            String fp_str = getFingerPrintByOpt55(option55);
            FingerPrint fingerPrint = resolveJason(fp_str);

            logger.debug("fp:"+fp_str);
            IpAssignment ipAssignment = new IpAssignment(ip,fingerPrint);
            ipAssignments.add(ipAssignment);
        }
        return ipAssignments;
    }

    public FingerPrint resolveJason(String finger_str) throws IOException {

        FingerPrint fingerPrint = mapper.readValue(finger_str, FingerPrint.class);

        logger.debug("fingerPrint:"+fingerPrint.toString());
        return fingerPrint;

    }

}
