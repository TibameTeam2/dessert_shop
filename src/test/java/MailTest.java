import cn.hutool.extra.mail.MailUtil;
import org.junit.Test;

public class MailTest {
    @Test
    public void sendMail() {
        MailUtil.send("jasonwu1994@gmail.com", "嗜甜，信箱驗證", "❤❤❤", false);
    }
}
