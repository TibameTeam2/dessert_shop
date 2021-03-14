import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;
import org.junit.Test;

/**
 * 單向加密，不可逆
 */
public class Md5Test {
    @Test
    public void md5() {
        String testStr="12A3454C321AB";
        String md5Hex1 = DigestUtil.md5Hex(testStr);
        System.out.println("md5Hex1 = " + md5Hex1);
    }
}
