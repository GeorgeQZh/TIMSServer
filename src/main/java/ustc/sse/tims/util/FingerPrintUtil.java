package ustc.sse.tims.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ustc.sse.tims.bean.Device;
import ustc.sse.tims.bean.FingerPrint;
import ustc.sse.tims.bean.IpAssignment;
import ustc.sse.tims.service.DHCPService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.util
 * @date 2019/5/15-23:20
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:  处理指纹库查询相关操作
 *
 * 使用Jackson解析json
 */

@Component
public class FingerPrintUtil {

    private ObjectMapper mapper = new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired DHCPService dhcpService;


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
     * 解析 json 结果  并进行持久化
     *
     * @param finger_str
     * @return fingerPrint
     * @throws IOException
     */
    public FingerPrint resolveJson(String finger_str) throws IOException {

        JsonNode node = mapper.readTree(finger_str);
        String device_str = node.get("device").toString();


        //Jackson解析json
        FingerPrint fingerPrint = mapper.readValue(finger_str, FingerPrint.class);
        Device device = mapper.readValue(device_str,Device.class);

        JsonNode device_node = mapper.readTree(device_str);
        String parents_str = device_node.get("parents").toString();

        JsonNode parents_node = mapper.readTree(parents_str);

        int i= 0;

        JsonNode parent_node;
        List<Device> parents = new ArrayList<>();

        while (true) {
            parent_node = parents_node.get(i++);

            if (parent_node == null) break;
            else {
                Device parent = mapper.readValue(parent_node.toString(), Device.class);
                parents.add(parent);
                //先将parents保存到数据库
                dhcpService.SetDevice(parent);
            }
        }

        device.setParents(parents);
        //再 将 device 保存到数据库
        dhcpService.SetDevice(device);
        fingerPrint.setDevice(device);
        //最后将fingerPrint 保存到数据库
        dhcpService.setFingerPrint(fingerPrint);

        logger.debug("fingerPrint:"+fingerPrint.toString());
        return fingerPrint;

    }

}
