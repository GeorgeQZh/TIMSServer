package ustc.sse.tims.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
 * @Description:  处理指纹库查询相关操作
 */

@Component
public class FingerPrintUtil {

    private ObjectMapper mapper = new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(getClass());

//    @Autowired
//    SystemConfig config;


    /**
     * 发送get请求访问在线指纹库api,查询指纹信息
     *
     * @param opt55 opt55字符串
     * @return 指纹信息json串
     */
    public String getFingerPrintByOpt55(String opt55){

        Map<String, String> params = new HashMap<String, String>();
        params.put("dhcp_fingerprint", opt55);   //API的格式要求
        params.put("key",Constant.fp_api_key);
        String result = HttpRequestHelper.GetPostUrl(Constant.fp_api_url, params,
                "GET",null, 0, 0);
        return result;

    }


    /**
     *
     * @param ip_opts 传入map <ip,option55>
     * @return 返回分配情况 IpAssignments 列表
     * @throws IOException
     */
    public ArrayList<IpAssignment> getIpAssignments (Map<String,String> ip_opts) throws IOException {

        ArrayList <IpAssignment> ipAssignments = new ArrayList<>();
        for(String ip: ip_opts.keySet()){
            String option55 = ip_opts.get(ip);
            String fp_str = getFingerPrintByOpt55(option55);
            FingerPrint fingerPrint = resolveJson(fp_str);

            logger.debug("fp:"+fp_str);
            IpAssignment ipAssignment = new IpAssignment(ip,fingerPrint);
            ipAssignments.add(ipAssignment);
        }
        return ipAssignments;
    }

    /**
     * 解析json 结果
     *
     * @param finger_str
     * @return
     * @throws IOException
     */
    public FingerPrint resolveJson(String finger_str) throws IOException {

        FingerPrint fingerPrint = mapper.readValue(finger_str, FingerPrint.class);

        logger.debug("fingerPrint:"+fingerPrint.toString());
        return fingerPrint;

    }

}
