//package com.pudge.cn.iot.system.device.controller;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.math.BigInteger;
//import java.net.URL;
//import java.net.URLConnection;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.security.InvalidKeyException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.*;
//
///**
// * @author mu_zhen
// * @description
// * @Date 2023/3/2 10:47
// */
//@Slf4j
//@RestController
//@RequestMapping("/douyin")
//public class TestDouYinController {
//
//
//    @RequestMapping("/test")
//    public String testDouyin() throws IOException {
//        Map<String, Object> jsonMap = new TreeMap<>();
//        Map<String, Object> param = new HashMap<>();
//        jsonMap.put("bizType", "COURIER_STATION");
//        jsonMap.put("orderId", "123456789");
//        jsonMap.put("cancelCode", "10803");
//        jsonMap.put("cancelReason", "消费者取消寄件");
//        jsonMap.put("status", "Canceled");
//        jsonMap.put("operationTime", "2023-01-19 15:12:23");
//        jsonMap.put("partnerCode", "youzhengguonei");
//        jsonMap.put("brandCode", "youzhengyizhan");
//        jsonMap.put("postName", "测试站点");
//        jsonMap.put("postCode", "001");
//        String method = "logistics.courierStation/cancelTerminalOrder";
//        param.put("method", method);
//        //TODO
//        String appKey = "7201035434913171000";
//        param.put("app_key", appKey);
//        // TODO
//        param.put("access_token", "ac683239-4afd-459e-a1d6-ba59be9c470a");
//        JSONObject jsonObject = new JSONObject(jsonMap);
//        param.put("param_json", jsonObject.toJSONString());
//        String timestamp = String.valueOf(new Date().getTime());
//        param.put("timestamp", timestamp);
//        param.put("v", "2");
//        param.put("sign_method", "hmac-sha256");
//        //     $paramPattern = 'app_key'.$appKey.'method'.$method.'param_json'.$paramJson.'timestamp'.$timestamp.'v'.$v;
//// 拼接后的格式差不多是这样：
//// app_key***method***param_json***timestamp***v*
//        String paramPattern = "app_key" + appKey + "method" + method + "param_json" + jsonObject.toJSONString() + "timestamp" + timestamp + "v2";
//
//        // TODO
//        String appSecret = "5349d12d-e4e0-4cc6-be0d-248526f262b9";
//        String signPattern = appSecret + paramPattern + appSecret;
//        String sign = hmac(signPattern, appSecret);
//        param.put("sign", sign);
//
//        //  System.out.println(HttpClientSend.sendDataForDouYin(param,jsonObject.toJSONString()));
//        //   sendPostGetToken("create",appKey,appSecret,"ems");
//        sendMsgToDouyin(method, param);
//        return "ok";
//    }
//
//    public static String hmac(String plainText, String appSecret) {
//        Mac mac;
//        try {
//            byte[] secret = appSecret.getBytes(StandardCharsets.UTF_8);
//            SecretKeySpec keySpec = new SecretKeySpec(secret, "HmacSHA256");
//
//            mac = Mac.getInstance("HmacSHA256");
//            mac.init(keySpec);
//        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
//            return "";
//        }
//
//        byte[] plainBytes = plainText.getBytes(StandardCharsets.UTF_8);
//        byte[] digest = mac.doFinal(plainBytes);
//        StringBuilder sb = new StringBuilder();
//        for (byte b : digest) {
//            sb.append(String.format("%02x", b));
//        }
//        return sb.toString();
//    }
//
//
//    public static void sendPostGetToken(String isRefresh, String app_key, String accessSecret, String userType) {
//        String timestamp = String.valueOf(new Date().getTime()); // 秒级的时间戳字符串
//        Long expires_in = null;
//        String refresh_token = "";
//        String paramJson = "";
//
//        String method = "";
//        if ("refresh".equals(isRefresh)) {
//            method = "token.refresh";
//            // 业务参数
//            if (StringUtils.isBlank(paramJson)) {
//                paramJson = "{\"refresh_token\":\"" + userType + "\",\"grant_type\":\"refresh_token\"}";
//            }
//        } else {
//            method = "token.create";
//            if (StringUtils.isBlank(paramJson)) {
//                paramJson = "{\"auth_id\":\"" + userType
//                        + "\",\"auth_subject_type\":\"WuLiuShang\",\"code\":\"\",\"grant_type\":\"authorization_self\"}";
//            }
//        }
//        log.info("paramJson" + paramJson);
//        String sign = null; // 签名
//        String sign_method = "hmac-sha256";
//        try {
//            // 排序
//            paramJson = getSerializationJson(paramJson);
//
//            String parameSign = getSecretParam(app_key, method, paramJson, timestamp, "2");
//            log.info("preSign:" + parameSign);
//            // 加密
//            sign = getDouyinSign(parameSign, accessSecret, sign_method);
//            log.info("生成的tokensign:" + sign);
//        } catch (Exception e) {
//            log.info("tokensign获取签名异常：" + e);
//        }
//        Map<String, Object> hashMap = new HashMap<>();
//        hashMap.put("method", method);
//        hashMap.put("app_key", app_key);
//        hashMap.put("timestamp", timestamp);
//        hashMap.put("v", "2");
//        hashMap.put("sign", sign);
//        hashMap.put("sign_method", sign_method);
//        hashMap.put("param_json", paramJson);
//
//        String sendPost = sendMsgToDouyin(method, hashMap);
//        log.info(method + "抖音请求token返回的报文为:" + sendPost);
//        JSONObject parseObject = JSONObject.parseObject(sendPost);
//        if (parseObject.containsKey("code") && "10000".equals(parseObject.getString("code"))) {
//            String access_token = parseObject.getJSONObject("data").getString("access_token");
//            log.info("access_token:" + access_token);
//            expires_in = parseObject.getJSONObject("data").getLong("expires_in");
//            log.info("expires_in:" + expires_in);
//            String refreshToken = parseObject.getJSONObject("data").getString("refresh_token");
//            log.info("refreshToken:" + refreshToken);
//            // 存redis
//            // expires_in
//            // refresh_token
//
//        } else {
//            // 当刷新token时候 异常 则主动绑定新的token
//            if ("refresh".equals(isRefresh)) {
//                sendPostGetToken("create", app_key, accessSecret, userType);
//            }
//            log.info("未获取到token");
//        }
//    }
//
//    /**
//     * 获取请求地址 请求抖音接口
//     *
//     * @param method
//     * @param params
//     * @return
//     */
//    private static String sendMsgToDouyin(String method, Map<String, Object> params) {
//        try {
//
//            String url = "https://openapi-fxg.jinritemai.com";
//
//            // 区分是否为测试环境 抖音测试环境需要对增加请求头
//
//            String methodPath = method.replace('.', '/');
//            url = url + "/" + methodPath;
//            // 发送报文得到抖音
//            String sendPost = sendPost(url, params, "uat");
//            return sendPost;
//        } catch (Exception e) {
//            log.error(method + "请求抖音异常", e);
//        }
//        return null;
//    }
//
//
//    public static String sendPost(String url, Map<String, Object> params, String isUat) {
//        StringBuffer resultBuffer = null;
//        // 构建请求参数
//        StringBuffer sbParams = new StringBuffer();
//        if (params != null && params.size() > 0) {
//            for (Map.Entry<String, Object> e : params.entrySet()) {
//                sbParams.append(e.getKey());
//                sbParams.append("=");
//                sbParams.append(e.getValue());
//                sbParams.append("&");
//            }
//        }
//        URLConnection con = null;
//        OutputStreamWriter osw = null;
//        BufferedReader br = null;
//        try {
//            URL realUrl = new URL(url);
//            // 打开和URL之间的连接
//            con = realUrl.openConnection();
//            // 设置通用的请求属性
//            con.setRequestProperty("accept", "*/*");
//            con.setRequestProperty("connection", "Keep-Alive");
//            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 抖音测试环境需求增加请求头。
//            if (StringUtils.isNotBlank(isUat) && "uat".equals(isUat)) {
//                log.info("uat环境测试");
//                con.setRequestProperty("x-tt-env", "boe_8865287");
//                con.setRequestProperty("Origin-From", "djt_prod");
//                con.setRequestProperty("X-USE-BOE", "1");
//            }
//            // 发送POST请求必须设置如下两行
//            con.setDoOutput(true);
//            con.setDoInput(true);
//            // 获取URLConnection对象对应的输出流
//            osw = new OutputStreamWriter(con.getOutputStream(), "utf-8");
//            if (sbParams != null && sbParams.length() > 0) {
//                // 发送请求参数
//                osw.write(sbParams.substring(0, sbParams.length() - 1));
//                // flush输出流的缓冲
//                osw.flush();
//            }
//            // 定义BufferedReader输入流来读取URL的响应
//            resultBuffer = new StringBuffer();
//            int contentLength = Integer.parseInt(con.getHeaderField("Content-Length"));
//            if (contentLength > 0) {
//                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
//                String temp;
//                while ((temp = br.readLine()) != null) {
//                    resultBuffer.append(temp);
//                }
//            }
//        } catch (Exception e) {
//            log.info("sendPostexception");
//            e.printStackTrace();
//        } finally {
//            if (osw != null) {
//                try {
//                    osw.close();
//                } catch (IOException e) {
//                    osw = null;
//                }
//            }
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    br = null;
//                }
//            }
//        }
//        log.info("方法抖音返回信息" + resultBuffer);
//        return String.valueOf(resultBuffer);
//    }
//
//
//    public static String getDouyinSign(String signPattern, String accessSecret, String signMethod) {
//        // 加签报文两边拼接密钥
//        signPattern = accessSecret + signPattern + accessSecret;
//        String sign = null;
//        if ("hmac-sha256".equals(signMethod.toLowerCase())) {
//            sign = stringToHmac(signPattern, accessSecret);
//        } else {
//            sign = stringToMD5(signPattern);
//        }
//        return sign;
//    }
//
//    /**
//     * 计算MD5值
//     *
//     * @param plainText
//     * @return
//     */
//    public static String stringToMD5(String plainText) {
//        byte[] secretBytes = null;
//        try {
//            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes(StandardCharsets.UTF_8));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        StringBuilder md5code = new StringBuilder(new BigInteger(1, secretBytes).toString(16));
//        while (md5code.length() < 32) {
//            md5code.insert(0, "0");
//        }
//        return md5code.toString();
//    }
//
//
//    /**
//     * 计算HmacSHA256值
//     *
//     * @param plainText
//     * @param appSecret
//     * @return
//     */
//    private static String stringToHmac(String plainText, String appSecret) {
//        Mac mac;
//        try {
//            byte[] secret = appSecret.getBytes(StandardCharsets.UTF_8);
//            SecretKeySpec keySpec = new SecretKeySpec(secret, "HmacSHA256");
//            mac = Mac.getInstance("HmacSHA256");
//            mac.init(keySpec);
//        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
//            return null;
//        }
//
//        byte[] plainBytes = plainText.getBytes(StandardCharsets.UTF_8);
//        byte[] digest = mac.doFinal(plainBytes);
//        StringBuilder sb = new StringBuilder();
//        for (byte b : digest) {
//            sb.append(String.format("%02x", b));
//        }
//        if (sb.length() > 0) {
//            return sb.toString();
//        } else {
//            return null;
//        }
//    }
//
//    /**
//     * 获取需要加密的字符串
//     *
//     * @param jsonParam
//     * @param appKey
//     * @param method
//     * @param timestamp
//     * @param v
//     * @return
//     */
//    public static String getSecretParam(String appKey, String method, String jsonParam, String timestamp, String v) {
//        return "app_key" + appKey + "method" + method + "param_json" + jsonParam + "timestamp" + timestamp + "v" + v;
//
//    }
//
//    /**
//     * json序列化并按照字母排序
//     *
//     * @param json
//     * @return
//     */
//    public static String getSerializationJson(String json) {
//        Map<String, Object> jsonToMap = jsonToMap(json);
//        if (jsonToMap.isEmpty()) {
//            return null;
//        }
//        JSONObject jsonObject = new JSONObject(jsonToMap);
//        return jsonObject.toString();
//    }
//
//
//    /**
//     * 将json转为map并序列化
//     *
//     * @param jsonStr
//     * @return
//     */
//    public static Map<String, Object> jsonToMap(String jsonStr) {
//        Map<String, Object> treeMap = new TreeMap();
//        JSONObject json = JSONObject.parseObject(jsonStr);// Feature.OrderedField实现解析后保存不乱序
//        Iterator<String> keys = json.keySet().iterator();
//        while (keys.hasNext()) {
//            String key = keys.next();
//            Object value = json.get(key);
//            // 判断传入kay-value中是否含有""或null
//            if (json.get(key) == null) {
//                // 当JSON字符串存在null时,不将该kay-value放入Map中,即显示的结果不包括该kay-value
//                continue;
//            }
//            // 判断是否为JSONArray(json数组)
//            if (value instanceof JSONArray) {
//                JSONArray jsonArray = (JSONArray) value;
//                List<Object> arrayList = new ArrayList<>();
//                for (Object object : jsonArray) {
//                    // 判断是否为JSONObject，如果是 转化成TreeMap
//                    if (object instanceof JSONObject) {
//                        object = jsonToMap(object.toString());
//                    }
//                    arrayList.add(object);
//                }
//                treeMap.put(key, arrayList);
//            } else {
//                // 判断该JSON中是否嵌套JSON
//                if (!"extra".equals(key) && !"serviceValue".equals(key)) {
//                    boolean flag = isJSONValid(value.toString());
//                    if (flag) {
//                        // 若嵌套json了,通过递归再对嵌套的json(即子json)进行排序
//                        value = jsonToMap(value.toString());
//                    }
//                }
//                // 其他基础类型直接放入treeMap
//                // JSONObject可进行再次解析转换
//                treeMap.put(key, value);
//            }
//        }
//        return treeMap;
//    }
//
//
//    /**
//     * 校验是否是JSON字符串
//     *
//     * @param json 传入数据
//     * @return 是JSON返回true, 否则false
//     */
//    private final static boolean isJSONValid(String json) {
//        try {
//            if (null != json && !"".equals(json.trim())) {
//                JSONObject.parseObject(json);
//            } else {
//                return false;
//            }
//        } catch (Exception ex) {
//            return false;
//        }
//        return true;
//    }
//}
