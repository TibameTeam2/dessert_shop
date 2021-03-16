import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.mail.MailUtil;
import org.junit.Test;

/**
 * 獲取隨機數
 */
public class RandomTest {

    @Test
    public void random() {
        System.out.println(RandomUtil.randomString(8));
    }

}
