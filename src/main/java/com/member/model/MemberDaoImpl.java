package com.member.model;


import com.util.JDBCUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl implements MemberDao {

    private static JdbcTemplate jdbcTemplate;
    private String driver = JDBCUtil.driver;
    private String url = JDBCUtil.url;
    private String userid = JDBCUtil.user;
    private String passwd = JDBCUtil.password;

    /**
     * 初始化
     */
    public void init() {
        // 得到Spring配置文件
//        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 取得JDBC模板物件
//        jdbcTemplate = (JdbcTemplate) app.getBean("jdbcTemplate");
//        driver = "com.mysql.cj.jdbc.Driver";
//        url = "jdbc:mysql://localhost:3306/sweet?serverTimezone=Asia/Taipei";
//        userid = "root";
//        passwd = "123456";
    }

    public void insert(MemberBean memberBean) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("INSERT INTO sweet.member " +
                    "(member_account,member_password,member_name,member_phone,member_email," +
                    "member_photo,member_gender,member_birthday,register_method,member_status)\n" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?)");

            pstmt.setString(1, memberBean.getMember_account());
            pstmt.setString(2, memberBean.getMember_password());
            pstmt.setString(3, memberBean.getMember_name());
            pstmt.setString(4, memberBean.getMember_phone());
            pstmt.setString(5, memberBean.getMember_email());
            pstmt.setBytes(6, memberBean.getMember_photo());
            pstmt.setInt(7, memberBean.getMember_gender());
            pstmt.setDate(8, memberBean.getMember_birthday());
//            pstmt.setTimestamp(9, memberBean.getRegister_time());
            pstmt.setInt(9, memberBean.getRegister_method());
            pstmt.setInt(10, memberBean.getMember_status());


            int count;
            count = pstmt.executeUpdate();
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


    public void update(MemberBean memberBean) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("UPDATE sweet.member set " +
                    "member_password=?, member_name=?, member_phone=?, member_email=? ," +
                    "member_photo=?,member_gender=?,member_birthday=?,register_method=?," +
                    "member_status=? where member_account = ?");

            pstmt.setString(1, memberBean.getMember_password());
            pstmt.setString(2, memberBean.getMember_name());
            pstmt.setString(3, memberBean.getMember_phone());
            pstmt.setString(4, memberBean.getMember_email());
            pstmt.setBytes(5, memberBean.getMember_photo());
            pstmt.setInt(6, memberBean.getMember_gender());
            pstmt.setDate(7, memberBean.getMember_birthday());
//            pstmt.setTimestamp(9, memberBean.getRegister_time());
            pstmt.setInt(8, memberBean.getRegister_method());
            pstmt.setInt(9, memberBean.getMember_status());
            pstmt.setString(10, memberBean.getMember_account());
            pstmt.executeUpdate();

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


    public void delete(String member_account) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("Delete from sweet.member where member_account=?");

            pstmt.setString(1, member_account);

            pstmt.executeUpdate();

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


    public MemberBean findByPrimaryKey(String member_account) {
        MemberBean memberBean = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("SELECT * FROM sweet.member where member_account=?");

            pstmt.setString(1, member_account);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // EmpgetAll 也稱為 Domain objects
                memberBean = new MemberBean();
                memberBean.setMember_account(rs.getString("member_account"));
                memberBean.setMember_password(rs.getString("member_password"));
                memberBean.setMember_name(rs.getString("member_name"));
                memberBean.setMember_phone(rs.getString("member_phone"));
                memberBean.setMember_email(rs.getString("member_email"));
                memberBean.setMember_photo(rs.getBytes("member_photo"));
                memberBean.setMember_gender(rs.getInt("member_gender"));
                memberBean.setMember_birthday(rs.getDate("member_birthday"));
                memberBean.setRegister_time(rs.getTimestamp("register_time"));
                memberBean.setRegister_method(rs.getInt("register_method"));
                memberBean.setMember_status(rs.getInt("member_status"));
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
        return memberBean;
    }

    //
//
    public List<MemberBean> selectAll() {
        List<MemberBean> list = new ArrayList<MemberBean>();
        MemberBean memberBean = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("SELECT * FROM sweet.member");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // EmpgetAll 也稱為 Domain objects
                memberBean = new MemberBean();
                memberBean.setMember_account(rs.getString("member_account"));
                memberBean.setMember_password(rs.getString("member_password"));
                memberBean.setMember_name(rs.getString("member_name"));
                memberBean.setMember_phone(rs.getString("member_phone"));
                memberBean.setMember_email(rs.getString("member_email"));
                memberBean.setMember_photo(rs.getBytes("member_photo"));
                memberBean.setMember_gender(rs.getInt("member_gender"));
                memberBean.setMember_birthday(rs.getDate("member_birthday"));
                memberBean.setRegister_time(rs.getTimestamp("register_time"));
                memberBean.setRegister_method(rs.getInt("register_method"));
                memberBean.setMember_status(rs.getInt("member_status"));
                list.add(memberBean); // Store the row in the list

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
        return list;
    }


    public MemberBean findByEmail(String member_email) {
        MemberBean memberBean = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("SELECT * FROM sweet.member where member_email=?");

            pstmt.setString(1, member_email);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                memberBean = new MemberBean();
                memberBean.setMember_account(rs.getString("member_account"));
                memberBean.setMember_password(rs.getString("member_password"));
                memberBean.setMember_name(rs.getString("member_name"));
                memberBean.setMember_phone(rs.getString("member_phone"));
                memberBean.setMember_email(rs.getString("member_email"));
                memberBean.setMember_photo(rs.getBytes("member_photo"));
                memberBean.setMember_gender(rs.getInt("member_gender"));
                memberBean.setMember_birthday(rs.getDate("member_birthday"));
                memberBean.setRegister_time(rs.getTimestamp("register_time"));
                memberBean.setRegister_method(rs.getInt("register_method"));
                memberBean.setMember_status(rs.getInt("member_status"));
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
        return memberBean;
    }
    public static void main(String[] args) {
        MemberDaoImpl dao = new MemberDaoImpl();

//        init();
//        // 新增
        MemberBean memberBean = new MemberBean();
//        memberBean.setMember_account("Kenny");
//        memberBean.setMember_password("1234");
//        memberBean.setMember_name("語心");
//        memberBean.setMember_phone("09123123123");
//        memberBean.setMember_email("test@gmail.com");
//        memberBean.setMember_photo(null);
//        memberBean.setMember_gender(0);
//        memberBean.setMember_birthday(java.sql.Date.valueOf("2021-03-01"));
////        memberBean.setRegister_time(null);
//        memberBean.setRegister_method(1);
//        memberBean.setMember_status(1);
//        dao.insert(memberBean);
////
////        // 修改
////        MemberBean memberBean = new MemberBean();
//        memberBean.setMember_account("Kenny");
//        memberBean.setMember_password("123455");
//        memberBean.setMember_name("語心1");
//        memberBean.setMember_phone("09123223223");
//        memberBean.setMember_email("test1@gmail.com");
//        memberBean.setMember_photo(null);
//        memberBean.setMember_gender(0);
//        memberBean.setMember_birthday(java.sql.Date.valueOf("2021-05-01"));
////        memberBean.setRegister_time(null);
//        memberBean.setRegister_method(1);
//        memberBean.setMember_status(1);
//        dao.update(memberBean);


//
//        // 刪除
//       dao.delete("kenny");
//
//        // 查詢
//        MemberBean memberBean = dao.findByPrimaryKey("jason");
//        System.out.println(memberBean);


        // 查詢
        List<MemberBean> list = dao.selectAll();
        for (MemberBean member : list) {
            System.out.println(member);

        }

    }
}


