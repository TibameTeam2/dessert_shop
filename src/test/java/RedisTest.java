import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.util.JedisUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class RedisTest {
    @Test
    public void redis() {
        String activeCode = RandomUtil.randomString(16);
        System.out.println("activeCode = " + activeCode);
        Jedis jedis = JedisUtil.getJedis();
        jedis.set("Member_account",activeCode);
        //設定有效期限
        jedis.expire("Member_account", 8);
        jedis.close();
    }
}
