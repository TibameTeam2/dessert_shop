package dao;

import com.member.model.MemberBean;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class memberDAOTest {

    private JdbcTemplate jdbcTemplate;

    /**
     *初始化
     */
    @Before
    public void init() {
        // 得到Spring配置文件
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 取得JDBC模板物件
        jdbcTemplate = (JdbcTemplate) app.getBean("jdbcTemplate");




    }

    /**
     *查詢member中總共有幾筆
     */
    @Test
    public void test1(){
        String sql = "select count(1) from sweet.member";
        // 執行查詢操作
        Integer total= jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println("總筆數:" + total);
    }

    /**
     *顯示所有帳號，自動封裝成JAVA物件
     */
    @Test
    public void test2(){
        String sql = "select * from member";
        List<MemberBean> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<MemberBean>(MemberBean.class));
        for (MemberBean member : list) {
            System.out.println(member);
        }
    }

    /**
     *修改特定帳號的密碼
     */
    @Test
    public void test3(){
        String sql = "update member set member_password = (?) where member_account = (?)";
        int count = jdbcTemplate.update(sql,456,"tom");
        System.out.println(count);
    }


    /**
     *刪除一筆資料
     */
    @Test
    public void test4(){
        String sql = "delete from member where member_account = ?";
        int count = jdbcTemplate.update(sql, "tom");
        System.out.println(count);
    }

    /**
     * 插入一筆資料
     */
    @Test
    public void test5(){
        String sql = "insert into member(member_account,member_password,member_name," +
                "member_phone,member_email,member_gender,member_birthday,regist_method,member_status) " +
                "values(?,?,?,?,?,?,?,?,?)";
        int count = jdbcTemplate.update(sql, "andy", "123", "ANDY","0938439241","andy@gmail.com",0,"1994-09-24",0,0);
        System.out.println(count);
    }
}

