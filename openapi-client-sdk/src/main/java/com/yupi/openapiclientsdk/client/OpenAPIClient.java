package com.yupi.openapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.yupi.openapiclientsdk.model.User;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.yupi.openapiclientsdk.utils.SignUtil.genSign;


/**
 *  调用第三方接口的客户端
 *
 * @author zuodong
 * @create 2023-08-05 21:38
 */

public class OpenAPIClient {

    private static final String GATEWAT_HOST = "http://localhost:8090";

    private String accessKey;
    private String secretKey;

    public OpenAPIClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }


    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result= HttpUtil.get(GATEWAT_HOST + "/api/name/", paramMap);
        System.out.println(result);
        return result;
    }


    public String getNameByPost( String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result= HttpUtil.post(GATEWAT_HOST + "/api/name/", paramMap);
        System.out.println(result);
        return result;
    }





    private Map<String, String> getHeaderMap(String body) {
        if (body == null) {
            body = "zw";
        }
        String encode = null;
        try {
            encode = URLEncoder.encode(body, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        /**
         * 一定不能直接发送给后端
         */
//        hashMap.put("secretKey", secretKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body", body);
        hashMap.put("timestamp ", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", genSign(body, secretKey));
        return hashMap;
    }



    public String getUsernameByPost( User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post(GATEWAT_HOST + "/api/name/user")
                .charset(StandardCharsets.UTF_8)
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        System.out.println(httpResponse.getStatus());
        String result = httpResponse.body();
        System.out.println(result);
        return result;
    }

    public String getAvatarUrlByPost(){

        HttpResponse httpResponse = HttpRequest.get(GATEWAT_HOST + "/api/avatar/avatarUrl")
                .addHeaders(getHeaderMap(""))
                .execute();
        System.out.println(httpResponse.getStatus());
        String body = httpResponse.body();
        System.out.println(body);
        return body;
    }


    public String getBaiduUrlByPost(){
        HttpResponse httpResponse = HttpRequest.get(GATEWAT_HOST + "/api/baidu/baiduInfo")
                .addHeaders(getHeaderMap(""))
                .execute();
        System.out.println(httpResponse.getStatus());
        String total = httpResponse.body();
        System.out.println(total);
        return total;
    }

    public String onlineInvoke(String parameters,String url) {
        HttpResponse httpResponse = HttpRequest.post(GATEWAT_HOST + url)
                .addHeaders(getHeaderMap(parameters))
                .body(parameters)
                .execute();
        System.out.println(httpResponse.getStatus());
        String result = httpResponse.body();
        return result;
    }

}
