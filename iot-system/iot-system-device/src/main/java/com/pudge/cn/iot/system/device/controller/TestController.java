package com.pudge.cn.iot.system.device.controller;




import com.alibaba.fastjson.JSONObject;
import com.pudge.cn.iot.common.response.R;
import com.pudge.cn.iot.system.device.config.PuMQTTProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping
@RefreshScope
@Api(value = "测试接口", tags = "设备管理接口")
@Slf4j
public class TestController {

    @Autowired
    private PuMQTTProvider puMQTTProvider;



    @Value("${iot.name}")
    private String name;
    /**
     * 生成32位大写MD5值
     */
    private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public static String getMD5String(String str) {
        try {
            if (str == null || str.trim().length() == 0) {
                return "";
            }
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            bytes = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(HEX_DIGITS[(bytes[i] & 0xf0) >> 4] + ""
                        + HEX_DIGITS[bytes[i] & 0xf]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping("/test")
    @ApiOperation(value = "测试获取", notes = "测试Get")
    public R test() throws MqttException {

        return R.success(puMQTTProvider.publish("light","hahah",0,false));
    }
    @PostMapping("/testDouyin")
    @ApiOperation(value = "测试获取", notes = "测试Get")
    public String testDouYin(@RequestBody String requestBody, HttpServletRequest request)  {
        log.info("requestBody:"+requestBody);
        String sign = request.getParameter("sign");
        String timestamp = request.getParameter("timestamp");
        String app_key = request.getParameter("app_key");
        String sign_method = request.getParameter("sign_method");
        String logId  = request.getHeader("logId");
        log.info("sign:" + sign + "timestamp" + timestamp + "app_key" + app_key
                + "sign_method" + sign_method + "logId" + logId);
//        requestBody:{"trackNo":"776335538848643"} >> c.p.c.i.s.d.c.TestController
//         sign:a93554dc71e5cb8c89e926c06151a787timestamp2023-04-27 17:05:55app_key7225969226018752058sign_methodmd5logIdnull >> c.p.c.i.s.d.c.TestController

        JSONObject j = new JSONObject();
        j.put("timestamp","123");
        j.put("track_no","456");
        j.put("traces","789");
        return j.toJSONString();
    }

    @GetMapping("/test/tms")
    public String testTMS(){
        String account = "EMS";
        String password = "2wsx!QAZ";
        String url = "https://tms-api-uat.adidas.com.cn/API/ECTrackingNode";
        SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmm");
        String token = getMD5String(account+sdf.format(new Date())+password);
        System.out.println("token:"+token);
        String body = "{\n" +
                "\t\"CourierTrackingNode\": {\n" +
                "\t\t\"update_occured_at\": \"2022-05-26 10:27:26\",\n" +
                "\t\t\"update_type\": \"SALES_SHIPMENT\",\n" +
                "\t\t\"carrier_name\": \"EMS\",\n" +
                "\t\t\"country_code\": \"CN\",\n" +
                "\t\t\"shipment_tracking_no\": \"8400000006203\",\n" +
                "\t\t\"status_code\": \"80\",\n" +
                "\t\t\"status_name\": \"签收\",\n" +
                "\t\t\"status_description\": \"压力测试\",\n" +
                "\t\t\"extn_status_description\": \"本人签收\"\n" +
                "\t}\n" +
                "}";
//        String result = post(url,token,jsonObject);
        String result = doPost(url,token,body);
        System.out.println("返回:----------"+result);

        return null;
    }



    public String post(String url,String token,JSONObject jsonObject) {
        //创建HttpClients对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建post请求对象
        HttpPost httpPost = new HttpPost(url);
        //创建封装请求参数对象，设置post请求参数
        StringEntity myEntity = new StringEntity(jsonObject.toJSONString(), ContentType.APPLICATION_JSON);
        httpPost.setEntity(myEntity);
        // 设置连接属性
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setSocketTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setRedirectsEnabled(true)
                .build();
        httpPost.setConfig(config);
        httpPost.setHeader("Token",token);
        try {
            //执行POST请求，获取请求结果
            HttpResponse httpResponse = httpClient.execute(httpPost);
            int code = httpResponse.getStatusLine().getStatusCode();
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                // 发送成功
                String resutlEntity = EntityUtils.toString(httpResponse.getEntity());
                return resutlEntity;
            } else {
                // 发送失败
                return code+"";
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                if (httpClient != null) {
                    // 释放资源
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 原生字符串发送post请求
     *
     * @param url
     * @param jsonStr
     * @return
     * @throws IOException
     */
    public static String doPost(String url, String token, String jsonStr) {
        log.info("url:"+url);
        log.info("params:"+jsonStr);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000).setConnectionRequestTimeout(35000).setSocketTimeout(60000).build();
        httpPost.setConfig(requestConfig);
        // 首先Header部分需要设定字符集为：uft-8
        httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
        // 此处也需要设定
        httpPost.setHeader("Accept", "application/json,text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        httpPost.setHeader("Accept-Encoding", "gzip, deflate");
        httpPost.setHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
        httpPost.setHeader("Connection", "keep-alive");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36");
        httpPost.setHeader("Token", token);

        CloseableHttpResponse httpResponse = null;
        try {
            httpPost.setEntity(new StringEntity(jsonStr,"utf-8"));
            httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            String result = EntityUtils.toString(entity);
            log.info("result:"+result);
            return result;
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            log.info("ClientProtocolException:"+e.getMessage(),e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.info("IOException:"+e.getMessage(),e);
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    log.info("IOException:"+e.getMessage(),e);
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    log.info("IOException:"+e.getMessage(),e);
                }
            }
        }
        return null;
    }

    @PostMapping("test/yto")
    public void testYTO(@RequestBody String json){
        String secret = "5DDFAF50CFFF1126";
        JSONObject jsonObject = JSONObject.parseObject(json);
        QpsYunDaTraceInfo logisticsInterface = (QpsYunDaTraceInfo) jsonObject.get("logisticsInterface");
        System.out.println("mailNos----------"+logisticsInterface.getMailNos());
    }

}
