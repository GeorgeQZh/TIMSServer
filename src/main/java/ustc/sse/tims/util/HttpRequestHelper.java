package ustc.sse.tims.util;

import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.util
 * @date 2019/4/16-17:00
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description: 向在线指纹库API发送请求
 */

@Component
public class HttpRequestHelper {
    public static String GetPostUrl(String sendUrl, Map<String, String> params, String sendType, String charset,
                                    int repeat_request_count, int repeat_request_max_count) {
        URL url = null;
        HttpURLConnection httpurlconnection = null;

        try {
            // 构建请求参数
            StringBuffer paramSb = new StringBuffer();
            if (params != null) {
                for (java.util.Map.Entry<String, String> e : params.entrySet()) {
                    paramSb.append(e.getKey());
                    paramSb.append("=");
                    // 将参数值urlEncode编码,防止传递中乱码
                    paramSb.append(URLEncoder.encode(e.getValue(), "UTF-8"));
                    paramSb.append("&");
                }
                paramSb.substring(0, paramSb.length() - 1);
            }
            url = new URL(sendUrl + "?" + paramSb.toString());

            System.out.println("APIurl:"+ url);

            httpurlconnection = (HttpURLConnection) url.openConnection();
            httpurlconnection.setRequestMethod("GET");
            httpurlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpurlconnection.setDoInput(true);
            httpurlconnection.setDoOutput(true);

            // 设置http请求超时时间30000毫秒（30秒）
            httpurlconnection.setConnectTimeout(30000);
            httpurlconnection.setReadTimeout(30000);
            httpurlconnection.setUseCaches(true);

            /*
             * if (submitMethod.equalsIgnoreCase("POST")) {
             * httpurlconnection.getOutputStream().write(postData.getBytes("GBK"
             * )); httpurlconnection.getOutputStream().flush();
             * httpurlconnection.getOutputStream().close(); }
             */

            int code = httpurlconnection.getResponseCode();
            if (code == 200) {
                DataInputStream in = new DataInputStream(httpurlconnection.getInputStream());
                int len = in.available();
                byte[] by = new byte[len];
                in.readFully(by);
                String rev = new String(by, "UTF-8");

                in.close();

                return rev;
            } else {
                // http 请求返回非 200状态时处理
                return "<?xml version=\"1.0\" encoding=\"utf-8\" ?><error>发送第三方请求失败</error>";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpurlconnection != null) {
                httpurlconnection.disconnect();
            }
        }
        return null;
    }
}
