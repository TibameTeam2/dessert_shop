package com.bucket_list.model;


import com.util.JDBCUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Bucket_ListDAO {

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


    public void insert(Bucket_ListBean Bck_List_Insert) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("INSERT INTO bucket_list (member_account,product_id,bucket_list_status)\n" +
                    "VALUES (?,?,?)");

            pstmt.setString(1, Bck_List_Insert.getMember_account());
            pstmt.setInt(2, Bck_List_Insert.getProduct_id());
            pstmt.setInt(3, Bck_List_Insert.getBucket_list_status());


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



    public void update(Bucket_ListBean Bck_List_Update) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("UPDATE sweet.bucket_list set " +
                    " bucket_list_status = ? where member_account = ? and product_id = ?");


            pstmt.setInt(1, Bck_List_Update.getBucket_list_status());
            pstmt.setString(2, Bck_List_Update.getMember_account());
            pstmt.setInt(3, Bck_List_Update.getProduct_id());

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


    public void delete(String member_account,Integer product_id) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("Delete from sweet.bucket_list where member_account = ? and product_id = ?");

            pstmt.setString(1, member_account);
            pstmt.setInt(2, product_id);

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


    public Bucket_ListBean findByPrimaryKey(String member_account,Integer product_id) {

        Bucket_ListBean Bck_List_Select_One = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("SELECT * FROM sweet.bucket_list where member_account = ? and product_id = ?");

            pstmt.setString(1, member_account);
            pstmt.setInt(2, product_id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // EmpgetAll 也稱為 Domain objects
                Bck_List_Select_One = new Bucket_ListBean();
                Bck_List_Select_One.setBucket_list_id(rs.getInt("bucket_list_id"));
                Bck_List_Select_One.setMember_account(rs.getString("member_account"));
                Bck_List_Select_One.setProduct_id(rs.getInt("product_id"));
                Bck_List_Select_One.setBucket_list_status(rs.getInt("bucket_list_status"));
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
        return Bck_List_Select_One;
    }
    //
//
    public List<Bucket_ListBean> selectAll() {
        List<Bucket_ListBean> list = new ArrayList<Bucket_ListBean>();
        Bucket_ListBean Bck_List_All = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("SELECT * FROM sweet.bucket_list");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // Bck_List_All 也稱為 Domain objects
                Bck_List_All = new Bucket_ListBean();
                Bck_List_All.setBucket_list_id(rs.getInt("bucket_list_id"));
                Bck_List_All.setMember_account(rs.getString("member_account"));
                Bck_List_All.setProduct_id(rs.getInt("product_id"));
                Bck_List_All.setBucket_list_status(rs.getInt("bucket_list_status"));
                list.add(Bck_List_All); // Store the row in the list

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

        Bucket_ListDAO dao = new Bucket_ListDAO();

//        init();
        /*
//        // 新增
        Bucket_ListBean Bck_List_Insert = new Bucket_ListBean();
        Bck_List_Insert.setBucket_list_status(1);
        Bck_List_Insert.setMember_account("amy");
        Bck_List_Insert.setProduct_id(2);

        dao.insert(Bck_List_Insert);
//
//        // 修改
        Bucket_ListBean Bck_List_Update = new Bucket_ListBean();

        Bck_List_Update.setBucket_list_status(0);
        Bck_List_Update.setMember_account("jason");
        Bck_List_Update.setProduct_id(2);

        dao.update(Bck_List_Update);

//
//        // 刪除
        dao.delete("amy",3);
//
//        // 查詢
        Bucket_ListBean Bck_List_Select_One = dao.findByPrimaryKey("jason",2);
        System.out.println(Bck_List_Select_One);

        */
        // 查詢
        List<Bucket_ListBean> list = dao.selectAll();
        for (Bucket_ListBean aEmp : list) {
            System.out.println(aEmp);

        }

    }

}
