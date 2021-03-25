package com.employee.model;

import com.util.JDBCUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

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
//        passwd = "123456";
    }


    public void insert(EmployeeBean emp_Insert) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);

            pstmt = con.prepareStatement("INSERT INTO employee (employee_account,employee_name,employee_password,employee_position,employee_photo,hire_date,employee_status)\n" +
                    "VALUES (?,?,?,?,?,?,?)");


            pstmt.setString(1, emp_Insert.getEmployee_account());
            pstmt.setString(2, emp_Insert.getEmployee_name());
            pstmt.setString(3, emp_Insert.getEmployee_password());
            pstmt.setString(4, emp_Insert.getEmployee_position());
            pstmt.setBytes(5, emp_Insert.getEmployee_photo());
            pstmt.setDate(6, emp_Insert.getHire_date());
            pstmt.setInt(7, emp_Insert.getEmployee_status());

            
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



    public void update(EmployeeBean emp_Update) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("UPDATE sweet.employee set " +
                    "employee_name=?, employee_password=?, employee_position=?, employee_photo=?, hire_date=?, employee_status=?  where employee_account = ?");


            pstmt.setString(1, emp_Update.getEmployee_name());
            pstmt.setString(2, emp_Update.getEmployee_password());
            pstmt.setString(3, emp_Update.getEmployee_position());
            pstmt.setBytes(4, emp_Update.getEmployee_photo());
            pstmt.setDate(5, emp_Update.getHire_date());
            pstmt.setInt(6, emp_Update.getEmployee_status());
            pstmt.setString(7, emp_Update.getEmployee_account());

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


    public void delete(String employee_account) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("Delete from sweet.employee where employee_account=?");

            pstmt.setString(1, employee_account);

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


    public EmployeeBean findByPrimaryKey(String employee_account) {

        EmployeeBean EmployeeBean = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("SELECT * FROM sweet.employee where employee_account=?");

            pstmt.setString(1, employee_account);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // EmpgetAll 也稱為 Domain objects
                EmployeeBean = new EmployeeBean();
                EmployeeBean.setEmployee_account(rs.getString("employee_account"));
                EmployeeBean.setEmployee_name(rs.getString("employee_name"));
                EmployeeBean.setEmployee_password(rs.getString("employee_password"));
                EmployeeBean.setEmployee_position(rs.getString("employee_position"));
                EmployeeBean.setEmployee_photo(rs.getBytes("employee_photo"));
                EmployeeBean.setHire_date(rs.getDate("hire_date"));
                EmployeeBean.setEmployee_status(rs.getInt("employee_status"));
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
        return EmployeeBean;
    }
//
//

    public List<EmployeeBean> getAll() {

        List<EmployeeBean> list = new ArrayList<EmployeeBean>();
        EmployeeBean EmpgetAll = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement("SELECT * FROM sweet.employee");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // EmpgetAll 也稱為 Domain objects
                EmpgetAll = new EmployeeBean();
                EmpgetAll.setEmployee_account(rs.getString("employee_account"));
                EmpgetAll.setEmployee_name(rs.getString("employee_name"));
                EmpgetAll.setEmployee_password(rs.getString("employee_password"));
                EmpgetAll.setEmployee_position(rs.getString("employee_position"));
                EmpgetAll.setEmployee_photo(rs.getBytes("employee_photo"));
                EmpgetAll.setHire_date(rs.getDate("hire_date"));
                EmpgetAll.setEmployee_status(rs.getInt("employee_status"));

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

        EmployeeDAO dao = new EmployeeDAO();

//        init();
//        // 新增
//        EmployeeBean emp_Insert = new EmployeeBean();
//        emp_Insert.setEmployee_account("Alan");
//        emp_Insert.setEmployee_name("阿崙");
//        emp_Insert.setEmployee_password("1234");
//        emp_Insert.setEmployee_position("工讀生");
//        emp_Insert.setEmployee_photo(null);
//        emp_Insert.setHire_date(java.sql.Date.valueOf("2021-02-21"));
////        emp_Insert.setEmployee_status(1);
//        dao.insert(emp_Insert);
//
//        // 修改
//        EmployeeBean emp_Update = new EmployeeBean();
//        emp_Update.setEmployee_account("Alan");
//        emp_Update.setEmployee_name("阿崙2");
//        emp_Update.setEmployee_password("5678");
//        emp_Update.setEmployee_position("超級工讀生");
//        emp_Update.setEmployee_photo(null);
//        emp_Update.setHire_date(java.sql.Date.valueOf("2002-01-01"));
//        emp_Update.setEmployee_status(0);
//        dao.update(emp_Update);



//
//        // 刪除
//       dao.delete("james");
//
//        // 查詢
        EmployeeBean emp_selectone = dao.findByPrimaryKey("jason");
        System.out.println(emp_selectone);


        // 查詢
//        List<EmployeeBean> list = dao.getAll();

        

    }
}
