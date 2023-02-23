/**
 * 中国邮政新一代寄递平台 <br/>
 * <br/>
 * 子系统名: <br/>
 * 模 块 名: <br/>
 * 文件名称: HttpClientSend.java <br/>
 * 创建日期: 2018年1月16日下午7:08:02 <br/>
 * 创 建 人: Administrator <br/>
 * 版权所有: 2018 中国邮政集团公司 保留所有权利 <br/>
 */

package com.pudge.cn.iot.system.device.controller;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.Map.Entry;

/**
 * 名称：类的中文名称 <br>
 * 功能：对类的功能进行说明 <br/>
 * <br/>
 * 
 * @since JDK 1.7
 * @see
 * @author Administrator
 */
@Slf4j
public class HttpClientSend {

    // 海航货运接口的账号密码
    private static final String ACCOUNT = "ems";
    private static final String PWD  = "ems@8090$vp";
    private static final String URL  = "https://openapi.hnacargo.com/transfer/hnacargo/WxService/AgentAreaService.asmx/GetAwbTracksJson";


    private static final String                       HTTP     = "http";
    private static final String                       HTTPS    = "https";
    private static SSLConnectionSocketFactory         sslsf    = null;
    private static SSLConnectionSocketFactory         sslsfHSQ = null;
    private static PoolingHttpClientConnectionManager cm       = null;
    private static PoolingHttpClientConnectionManager cmHSQ    = null;
    private static SSLContextBuilder                  builder  = null;
    static {
        try {
            builder = new SSLContextBuilder();
            // 全部信任 不做身份鉴定
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s)
                    throws CertificateException {
                    return true;
                }
            });
            sslsf = new SSLConnectionSocketFactory(builder.build(),
                new String[] { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2" }, null,
                NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder
                .<ConnectionSocketFactory> create()
                .register(HTTP, new PlainConnectionSocketFactory()).register(HTTPS, sslsf).build();
            cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(500);// max connection
            cm.setDefaultMaxPerRoute(200);
            // 请求的url支持的协议为 TLSv1.2
            sslsfHSQ = new SSLConnectionSocketFactory(builder.build(), new String[] { "TLSv1.2" },
                null, NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registryHSQ = RegistryBuilder
                .<ConnectionSocketFactory> create()
                .register(HTTP, new PlainConnectionSocketFactory()).register(HTTPS, sslsfHSQ)
                .build();
            cmHSQ = new PoolingHttpClientConnectionManager(registryHSQ);
            cmHSQ.setMaxTotal(200);// max connection
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /** 海航货运发起Http请求
     *
     * @return
     */
    public static String sendDataForHaiHang( String jsonstr) throws IOException {

        String result = "";
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(URL);

            // 设置头信息
            httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
            httpPost.addHeader("Accept", "application/json;charset=utf-8");
            httpPost.addHeader("account",ACCOUNT);
            httpPost.addHeader("pwd",PWD);
            log.info("海航货运发起HTTP查询---发送的json:"+jsonstr);

            // 设置请求参数
            StringEntity se = new StringEntity(jsonstr,"UTF-8");
            httpPost.setEntity(se);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = httpResponse.getEntity();
                result = EntityUtils.toString(resEntity);
            } else {
                Map<String,Object> map =new HashMap<>();
                map.put("data",null);
                map.put("status","Fail");
                map.put("message","查询失败");
                result = JSONObject.toJSONString(map);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpClient != null) {
                httpClient.close();
            }
        }
        return result;
    }






    public static CloseableHttpClient getHttpClient() throws Exception {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf)
            .setConnectionManager(cm).setConnectionManagerShared(true).build();
        return httpClient;
    }

    public static HttpClient getHttpClientNew() throws Exception {
        HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf)
            .setConnectionManager(cm).setConnectionManagerShared(true).build();
        return httpClient;
    }

    public static CloseableHttpClient getHttpClientHSQ() throws Exception {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsfHSQ)
            .setConnectionManager(cmHSQ).setConnectionManagerShared(true).build();
        return httpClient;
    }



}
