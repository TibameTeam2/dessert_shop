import com.card_detail.model.card_detailBean;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class card_detailDAOTest {
    private JdbcTemplate jdbcTemplate;
    String driver;
    String url;
    String userid;
    String passwd;

    /**
     * 初始化
     */
    @Before
    public void init() {
        // 得到Spring配置文件
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 取得JDBC模板物件
        jdbcTemplate = (JdbcTemplate) app.getBean("jdbcTemplate");


        driver = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/sweet?serverTimezone=Asia/Taipei";
        userid = "root";
        passwd = "root";
    }

    /**
     * 查詢全部並封裝成Bean
     */
    @Test
    public void getAll() {
        List<card_detailBean> list = new ArrayList<card_detailBean>();
        card_detailBean card_detailBean = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("select * from sweet.card_detail");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // deptVO 也稱為 Domain objects
                card_detailBean = new card_detailBean();
                card_detailBean.setCard_id(rs.getInt("card_id"));
                card_detailBean.setMember_account(rs.getString("member_account"));
                card_detailBean.setCard_number(rs.getString("card_number"));
                card_detailBean.setCard_expired_day(rs.getString("card_expired_day"));
                card_detailBean.setCard_cvc(rs.getString("card_cvc"));
                card_detailBean.setCard_addedDate(rs.getTimestamp("card_addedDate"));
                list.add(card_detailBean);
                System.out.println(card_detailBean);
            }
            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
//        System.out.println(list);
//        return list;
    }

    /**
     * 查一筆主鍵 並封裝成Bean
     */
    @Test
    public void findByPrimaryKey() {
        Integer card_id = 2;   //要找的PK0

        card_detailBean card_detailBean = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("SELECT * FROM card_detail where card_id = ?");

            pstmt.setInt(1, card_id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empVo 也稱為 Domain objects
                card_detailBean = new card_detailBean();
                card_detailBean.setCard_id(rs.getInt("card_id"));
                card_detailBean.setMember_account(rs.getString("member_account"));
                card_detailBean.setCard_number(rs.getString("card_number"));
                card_detailBean.setCard_expired_day(rs.getString("card_expired_day"));
                card_detailBean.setCard_cvc(rs.getString("card_cvc"));
                card_detailBean.setCard_addedDate(rs.getTimestamp("card_addedDate"));
                System.out.println(card_detailBean);
            }

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
//        return card_detailBean;
    }

    /**
     * 插入一筆資料
     */
    @Test
    public void insert() {
        card_detailBean card_detailBean = new card_detailBean();
        card_detailBean.setMember_account("amy");
        card_detailBean.setCard_number("8412 4661 4712 8871");
        card_detailBean.setCard_expired_day("07/23");
        card_detailBean.setCard_cvc("182");

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("INSERT INTO sweet.card_detail (member_account,card_number,card_expired_day,card_cvc) VALUES (?, ?, ?, ?)");

            pstmt.setString(1, card_detailBean.getMember_account());
            pstmt.setString(2, card_detailBean.getCard_number());
            pstmt.setString(3, card_detailBean.getCard_expired_day());
            pstmt.setString(4, card_detailBean.getCard_cvc());

            int count = pstmt.executeUpdate();
            System.out.println(count);

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }

    }

    /**
     * 刪除指定主鍵
     */
    @Test
    public void delete() {
        Integer card_id = 4;

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("DELETE FROM sweet.card_detail where card_id = ?");

            pstmt.setInt(1, card_id);

            int count = pstmt.executeUpdate();
            System.out.println(count);

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }

    }


    /**
     * 修改
     */
    @Test
    public void update() {
        card_detailBean card_detailBean = new card_detailBean();
        card_detailBean.setCard_id(5);
        card_detailBean.setMember_account("amy");
        card_detailBean.setCard_number("8888 8888 8888 8888");
        card_detailBean.setCard_expired_day("06/02");
        card_detailBean.setCard_cvc("123");

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("UPDATE card_detail set member_account=?, card_number=?, card_expired_day=?, card_cvc=? where card_id = ?");

            pstmt.setString(1, card_detailBean.getMember_account());
            pstmt.setString(2, card_detailBean.getCard_number());
            pstmt.setString(3, card_detailBean.getCard_expired_day());
            pstmt.setString(4, card_detailBean.getCard_cvc());
            pstmt.setInt(5, card_detailBean.getCard_id());


            int count = pstmt.executeUpdate();
            System.out.println(count);
            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }

    }


}