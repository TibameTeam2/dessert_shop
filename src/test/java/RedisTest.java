import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.mail.MailUtil;
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
        jedis.expire("Member_account", 15);
        jedis.close();

    }
    @Test
    public void uuid() {
        String uuid = IdUtil.randomUUID();
        System.out.println("uuid = " + uuid);
        Jedis jedis = JedisUtil.getJedis();
        jedis.set(uuid,"member_account");
        jedis.del(uuid); //刪除
        //設定有效期限
        jedis.expire(uuid, 600);
        jedis.close();
    }
    @Test
    public void register(){
        String activeCode = IdUtil.randomUUID();
        Jedis jedis = JedisUtil.getJedis();
        jedis.set(activeCode,"jason");
        jedis.expire(activeCode, 6000);
        jedis.close();
    }
}
