package com.employee_authority.model;


import com.util.JDBCUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAuthorityDAO {

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
//        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 取得JDBC模板物件
//        jdbcTemplate = (JdbcTemplate) app.getBean("jdbcTemplate");

//        driver = JDBCUtil.driver;
//        url = JDBCUtil.url;
//        userid = JDBCUtil.user;
//        passwd = JDBCUtil.password;
    }


    public void insert(EmployeeAuthorityBean emp_ath_Insert) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("INSERT INTO employee_authority (employee_account,authority_Content_id)\n" +
                    "VALUES (?,?)");

            pstmt.setString(1, emp_ath_Insert.getEmployee_account());
            pstmt.setInt(2, emp_ath_Insert.getAuthority_Content_id());


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


    public void update(EmployeeAuthorityBean emp_ath_Update) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("UPDATE sweet.employee_authority set " +
                    "employee_account=?, authority_Content_id=? where employee_account=? and  authority_Content_id=?");


            pstmt.setString(1, emp_ath_Update.getEmployee_account());
            pstmt.setInt(2, emp_ath_Update.getAuthority_Content_id());
            pstmt.setInt(3, emp_ath_Update.getAuthority_id());

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


    public void delete(Integer authority_id) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("DELETE FROM employee_authority where authority_id=?");

            pstmt.setInt(1, authority_id);

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


    public EmployeeAuthorityBean findByPrimaryKey(Integer authority_id) {

        EmployeeAuthorityBean Empone = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("SELECT * FROM sweet.employee_authority where authority_id=?");

            pstmt.setInt(1, authority_id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // EmpgetAll 也稱為 Domain objects
                Empone = new EmployeeAuthorityBean();
                Empone.setAuthority_id(rs.getInt("authority_id"));
                Empone.setEmployee_account(rs.getString("employee_account"));
                Empone.setAuthority_Content_id(rs.getInt("authority_Content_id"));

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
    public List<EmployeeAuthorityBean> findByEmployee(String employee_account) {
        List<EmployeeAuthorityBean> list = new ArrayList<EmployeeAuthorityBean>();
        EmployeeAuthorityBean Empone = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("SELECT * FROM sweet.employee_authority where employee_account = ?");

            pstmt.setString(1, employee_account);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // EmpgetAll 也稱為 Domain objects
                Empone = new EmployeeAuthorityBean();
                Empone.setAuthority_id(rs.getInt("authority_id"));
                Empone.setEmployee_account(rs.getString("employee_account"));
                Empone.setAuthority_Content_id(rs.getInt("authority_Content_id"));
                list.add(Empone); // Store the row in the list
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

    public List<EmployeeAuthorityBean> selectAll() {
        List<EmployeeAuthorityBean> list = new ArrayList<EmployeeAuthorityBean>();
        EmployeeAuthorityBean EmpgetAll;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("SELECT * FROM sweet.employee_authority");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // EmpgetAll 也稱為 Domain objects
                EmpgetAll = new EmployeeAuthorityBean();
                EmpgetAll.setAuthority_id(rs.getInt("authority_id"));
                EmpgetAll.setEmployee_account(rs.getString("employee_account"));
                EmpgetAll.setAuthority_Content_id(rs.getInt("authority_Content_id"));
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
        EmployeeAuthorityDAO dao = new EmployeeAuthorityDAO();

        // 新增
        EmployeeAuthorityBean emp_ath_Insert = new EmployeeAuthorityBean();
        emp_ath_Insert.setEmployee_account("alan");
        emp_ath_Insert.setAuthority_Content_id(1);
//        EmployeeBeanInsert.setEmployee_status(1);
        dao.insert(emp_ath_Insert);
/*
//       // 修改
        EmployeeAuthorityBean emp_ath_Update = new EmployeeAuthorityBean();
        emp_ath_Update.setAuthority_id(1);
        emp_ath_Update.setEmployee_account("alan");
        emp_ath_Update.setAuthority_Content_id(2);

        dao.update(emp_ath_Update);

//
        // 刪除
        dao.delete(1);

//        // 查詢
        EmployeeAuthorityBean emp_ath_seone = dao.findByPrimaryKey(2);
        System.out.println(emp_ath_seone);*/


        // 查詢
        List<EmployeeAuthorityBean> list = dao.selectAll();
        for (EmployeeAuthorityBean aEmp : list) {
            System.out.println(aEmp);
        }

    }
}
