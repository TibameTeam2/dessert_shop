package com.authority_content.model;


import com.util.JDBCUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AuthorityContentDAO {

    private static JdbcTemplate jdbcTemplate;
    private String driver = JDBCUtil.driver;
    private String url = JDBCUtil.url;
    private String userid = JDBCUtil.user;
    private String passwd = JDBCUtil.password;

    /**
     * 初始化
     */
    public static void init() {
        // 得到Spring配置文件
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 取得JDBC模板物件
        jdbcTemplate = (JdbcTemplate) app.getBean("jdbcTemplate");


//        driver = "com.mysql.cj.jdbc.Driver";
//        url = "jdbc:mysql://localhost:3306/sweet?serverTimezone=Asia/Taipei";
//        userid = "root";
//        passwd = "root";
    }


    public void insert(AuthorityContentBean ath_Content_Insert) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("INSERT INTO sweet.authority_content (authority_content_id,authority_content)\n" +
                    "VALUES (?,?)");

            pstmt.setInt(1, ath_Content_Insert.getAuthority_content_id());
            pstmt.setString(2, ath_Content_Insert.getAuthority_content());

//            pstmt.setInt(7, EmployeeBean.getEmployee_status());

            int count;
            count=pstmt.executeUpdate();
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



    public void update(AuthorityContentBean ath_ContentInsert_Update) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("UPDATE sweet.authority_content set " +
                    "authority_content_id=?, authority_content=? where authority_content = ?");

            pstmt.setInt(1, ath_ContentInsert_Update.getAuthority_content_id());
            pstmt.setString(2, ath_ContentInsert_Update.getAuthority_content());


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


    public void delete(String authority_content) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("DELETE FROM authority_content where authority_content=?");

            pstmt.setString(1, authority_content);

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


    public AuthorityContentBean findByPrimaryKey(String authority_content) {

        AuthorityContentBean Empone = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("SELECT * FROM sweet.authority_content where authority_content=?");

            pstmt.setString(1, authority_content);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // Empone 也稱為 Domain objects
                Empone = new AuthorityContentBean();
                Empone.setAuthority_content_id(rs.getInt("authority_id"));
                Empone.setAuthority_content(rs.getString("employee_account"));

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
        return Empone;
    }
    //
//
    public List<AuthorityContentBean> selectAll() {
        List<AuthorityContentBean> list = new ArrayList<AuthorityContentBean>();
        AuthorityContentBean EmpgetAll = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("SELECT * FROM sweet.authority_content");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // EmpgetAll 也稱為 Domain objects
                EmpgetAll = new AuthorityContentBean();
                EmpgetAll.setAuthority_content_id(rs.getInt("authority_content_id"));
                EmpgetAll.setAuthority_content(rs.getString("authority_content"));
                list.add(EmpgetAll); // Store the row in the list

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

    public static void main(String[] args) {

        AuthorityContentDAO dao = new AuthorityContentDAO();


/*
//        // 新增
        Authority_ContentBean ath_Content_Insert = new Authority_ContentBean();
        ath_Content_Insert.setAuthority_content_id(3);
        ath_Content_Insert.setAuthority_content("管理權限");
//        EmployeeBeanInsert.setEmployee_status(1);
        dao.insert(ath_Content_Insert);

//        // 修改
        Authority_ContentBean ath_ContentInsert_Update = new Authority_ContentBean();
        ath_ContentInsert_Update.setAuthority_content_id(3);
        ath_ContentInsert_Update.setAuthority_content("管理權限2");

        dao.update(ath_ContentInsert_Update);



//
        // 刪除
        dao.delete("張貼公告");

//        // 查詢
        Authority_ContentBean ath_Content_one = dao.findByPrimaryKey("張貼公告");
        System.out.println(ath_Content_one);*/


        // 查詢
        List<AuthorityContentBean> list = dao.selectAll();
        for (AuthorityContentBean aEmp : list) {
            System.out.println(aEmp);
        }

    }

}
