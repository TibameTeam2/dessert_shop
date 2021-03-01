package com.util;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC工具類
 */
public class JDBCUtil {
    public static String url;
    public static String user;
    public static String password;
    public static String driver;
    /**
     * 檔案讀取
     */
    static{
        //读取资源文件，获取值。
        try {
            //1. 创建Properties集合类。
            Properties pro = new Properties();

            //获取src路径下的文件的方式--->ClassLoader 类加载器
            ClassLoader classLoader = JDBCUtil.class.getClassLoader();
            URL res  = classLoader.getResource("jdbc.properties");
            String path = null;
            try {
                path = res.toURI().getPath();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            //2. 加载文件  絕對路徑可行，但不好用
//            pro.load(new FileReader("C:\\project\\dessert_shop\\target\\classes\\jdbc.properties"));

            pro.load(new FileReader(path));

            //3. 获取数据，赋值
            url = pro.getProperty("jdbc.url");
            user = pro.getProperty("jdbc.username");
            password = pro.getProperty("jdbc.password");
            driver = pro.getProperty("jdbc.driver");

            System.out.println("url = " + url);
            System.out.println("driver = " + driver);
            System.out.println("user = " + user);
            System.out.println("password = " + password);
            //4. 注册驱动
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//
//    /**
//     * 获取连接
//     * @return 连接对象
//     */
//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(url, user, password);
//    }
//
//    /**
//     * 释放资源
//     * @param stmt
//     * @param conn
//     */
//    public static void close(Statement stmt,Connection conn){
//        if( stmt != null){
//            try {
//                stmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        if( conn != null){
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//    /**
//     * 释放资源
//     * @param stmt
//     * @param conn
//     */
//    public static void close(ResultSet rs,Statement stmt, Connection conn){
//        if( rs != null){
//            try {
//                rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        if( stmt != null){
//            try {
//                stmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        if( conn != null){
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}

