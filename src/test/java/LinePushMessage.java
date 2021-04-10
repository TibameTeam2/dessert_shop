import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class LinePushMessage {
    final String AES_KEY = "mhWv/FOMFa6MPCCBjLcnnA==";
    final String url="https://sc.linebotweb.xyz:5002/pushMessage";
    @Test
    public void pushMessageTest(){
        String member_account="jason33";
        String message="提醒您!明日下午3點有訂位哦!!";

        System.out.println(pushMessage(member_account,message));
    }
    public String pushMessage(String member_account,String message){
        Map<String, String> content = new HashMap<>();
        content.put("member_account",member_account);
        content.put("message",message);
        content.put("time", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.sql.Timestamp(System.currentTimeMillis())));
        Gson gson = new Gson();
        String contentJson = gson.toJson(content);
//        System.out.println("contentJson = " + contentJson);
        // 使用AES加密
        AES aes = SecureUtil.aes(Base64.decode(AES_KEY));
        String encrypt = aes.encryptBase64(contentJson);

        System.out.println("encrypt = " + encrypt);

        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("token", encrypt);

        String result= HttpUtil.post(url, paramMap);
//        System.out.println("result = " + result);
        return result;
    }
}
