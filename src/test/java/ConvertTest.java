import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.member.model.MemberBean;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.sql.Date;
import java.sql.Timestamp;


public class ConvertTest {

    @Test
    public void convert() {
        String dateTimeStr2 = "2018-11-29";
//        java.sql.Timestamp ts = java.sql.Timestamp.valueOf(Convert.toDate(dateTimeStr2).getTime());
//        System.out.println(Convert.toDate("26:22:33"));
        java.sql.Timestamp ts2 =  new java.sql.Timestamp(Convert.toDate("2018-11-28").getTime());
        java.sql.Timestamp ts3 =  new java.sql.Timestamp(java.sql.Date.valueOf("2018-11-27").getTime());

        System.out.println(ts2.getTime()-ts3.getTime());
        System.out.println("ts2 = " + ts2);
        System.out.println("ts3 = " + ts3);
//        System.out.println(Convert.toStr(ts2));
//        System.out.println(Convert.toLong(ts2));
//
//
//        System.out.println(Convert.toLong(Convert.toDate("2025/01/19 11:22:33")));
//        System.out.println(Convert.toDate("2025/01/19 11:22:33").getTime());



//        MemberBean m=new MemberBean();
//        m.setMember_gender(Convert.toInt("2"));
//
//        System.out.println(new java.sql.Date(cn.hutool.core.convert.Convert.toDate("2020/01/09").getTime()));

//        BufferedInputStream in = FileUtil.getInputStream("d:/test.txt");
//        BufferedOutputStream out = FileUtil.getOutputStream("d:/test2.txt");
//        long copySize = IoUtil.copy(in, out, IoUtil.DEFAULT_BUFFER_SIZE);



        //字串轉sql.Date
//        java.sql.Date d1 = new java.sql.Date(Convert.toDate("2020/03/14").getTime());

    }
}
