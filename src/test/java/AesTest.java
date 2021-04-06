import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.google.gson.Gson;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class AesTest {
    @Test
    public void encrypt() throws Exception {
        Map<String, String> content = new HashMap<String, String>();
        content.put("member_account", "jason");
        content.put("time", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.sql.Timestamp(System.currentTimeMillis())));
        Gson gson = new Gson();
        String contentJson = gson.toJson(content);
        System.out.println("contentJson = " + contentJson);
        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        String keyStr = Base64.encode(key);
        System.out.println("keyStr = " + keyStr);
        // 构建
        AES aes = SecureUtil.aes(Base64.decode("mhWv/FOMFa6MPCCBjLcnnA=="));

        // 加密
        String encrypt = aes.encryptBase64(contentJson);
        System.out.println("encrypt = " + encrypt);

        // 解密为字符串
        String decryptStr = aes.decryptStr(encrypt, CharsetUtil.CHARSET_UTF_8);
        System.out.println("decryptStr = " + decryptStr);
    }
}
