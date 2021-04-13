package com.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Line主動推播訊息給用戶
 * 使用linePushMessage("jason","訊息內容");
 * 如果成功會回傳{"flag": true, "msg": "已成功推送訊息至jason"}
 */
public class LineUtil {
    final static String AES_KEY = "mhWv/FOMFa6MPCCBjLcnnA==";
    final static String url="https://sc.linebotweb.xyz:5002/pushMessage";
    static Gson gson = new Gson();

    public void pushMessageTest(){
        String member_account="jason";
        String message="提醒您!明日下午3點有訂位哦!!";

        System.out.println(linePushMessage(member_account,message));
    }

    /**
     * 請調用此方法
     */
    static public String linePushMessage(String member_account,String message){
        Map<String, String> content = new HashMap<>();
        content.put("member_account",member_account);
        content.put("message",message);
        content.put("time", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.sql.Timestamp(System.currentTimeMillis())));

        String contentJson = gson.toJson(content);
//        System.out.println("contentJson = " + contentJson);

        // 使用AES加密
        AES aes = SecureUtil.aes(Base64.decode(AES_KEY));
        String encrypt = aes.encryptBase64(contentJson);
//        System.out.println("encrypt = " + encrypt);

        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("token", encrypt);

        String result= HttpUtil.post(url, paramMap);
        System.out.println("result = " + result);
        return result;
    }
}
