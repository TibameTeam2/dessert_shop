package com.live_support.model;


import com.util.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LiveSupportDAO {

	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;
	
	
	/******************************************* 初始化 *******************************************/
	public void init() {
//
//		driver = "com.mysql.cj.jdbc.Driver";
//		url = "jdbc:mysql://localhost:3306/sweet?serverTimezone=Asia/Taipei";
//		userid = "root";
//		passwd = "1qaz2wsx";

	}
	
	
	/******************************************* 新增 *******************************************/
	public void insert(LiveSupportBean LiveSupportBean) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);	
			pstmt = con.prepareStatement(
					"INSERT INTO sweet.live_support(chat_history,sender,member_account,employee_account) VALUES (?, ?, ?, ?)");

			pstmt.setString(1, LiveSupportBean.getChat_history());
			pstmt.setInt(2, LiveSupportBean.getSender());
			pstmt.setString(3, LiveSupportBean.getMember_account());
			pstmt.setString(4, LiveSupportBean.getEmployee_account());

			//更新筆數
			int count = pstmt.executeUpdate();
			System.out.println(count);

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

	/******************************************* 刪除 *******************************************/
	    public void delete(Integer customer_service_id) {
//	        Integer customer_service_id = 4;

	        Connection con = null;
	        PreparedStatement pstmt = null;

	        try {

	            Class.forName(driver);
	            con = DriverManager.getConnection(url, userid, passwd);
	            pstmt = con.prepareStatement("DELETE FROM sweet.live_support where customer_service_id = ?");

	            pstmt.setInt(1, customer_service_id);

	             //更新筆數
	            int count = pstmt.executeUpdate();
	            System.out.println(count);
	            
	            // Handle any driver errors
	        } catch (ClassNotFoundException e) {
	            throw new RuntimeException("Couldn't load database driver."
	                    + e.getMessage());
	            // Handle any SQL errors
	        } catch (SQLException se) {
	            throw new RuntimeException("A database error occured."
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
	 
	 
 /******************************************* 修改 *******************************************/
	 
	    public void update(LiveSupportBean LiveSupportBean) {
//		 LiveSupportBean LiveSupportBean = new LiveSupportBean();
		 

	
	        Connection con = null;
	        PreparedStatement pstmt = null;

	        try {

	            Class.forName(driver);
	            con = DriverManager.getConnection(url, userid, passwd);
	            pstmt = con.prepareStatement("UPDATE live_support set chat_history=? where customer_service_id = ?");

	            pstmt.setString(1, LiveSupportBean.getChat_history());
	            pstmt.setInt(2, LiveSupportBean.getCustomer_service_id());
	     


	            //更新筆數
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

	 /******************************************* 查一筆(PK) *******************************************/
	    public LiveSupportBean findByPrimaryKey(Integer customer_service_id) {
	        LiveSupportBean LiveSupportBean = null;
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        try {

	            Class.forName(driver);
	            con = DriverManager.getConnection(url, userid, passwd);
	            pstmt = con.prepareStatement("SELECT * FROM live_support where customer_service_id = ?");

	            pstmt.setInt(1,customer_service_id);

	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	                // empVo 也稱為 Domain objects
	            	LiveSupportBean = new LiveSupportBean();
	            	
	            	LiveSupportBean.setCustomer_service_id(rs.getInt("customer_service_id"));
	            	LiveSupportBean.setChat_history(rs.getString("chat_history"));
	            	LiveSupportBean.setSender(rs.getInt("sender"));
	            	LiveSupportBean.setChat_time(rs.getTimestamp("chat_time"));
	            	LiveSupportBean.setMember_account(rs.getString("member_account"));
	            	LiveSupportBean.setEmployee_account(rs.getString("employee_account"));
	            	          	
	            
//	                System.out.println(LiveSupportBean);
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
	        return LiveSupportBean;
	    }
	    

	    /******************************************* 查全部   *******************************************/
	    

	    public List<LiveSupportBean> getAll() {
	        List<LiveSupportBean> list = new ArrayList<LiveSupportBean>();
			LiveSupportBean LiveSupportBean = null;
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        
	        try {
	            Class.forName(driver);
	            con = DriverManager.getConnection(url, userid, passwd);
	            pstmt = con.prepareStatement("select * from sweet.live_support");
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	                
	            	LiveSupportBean = new LiveSupportBean();
	            	
	            	LiveSupportBean.setCustomer_service_id(rs.getInt("customer_service_id"));
	            	LiveSupportBean.setChat_history(rs.getString("chat_history"));
	            	LiveSupportBean.setSender(rs.getInt("sender"));
	            	LiveSupportBean.setChat_time(rs.getTimestamp("chat_time"));
	            	LiveSupportBean.setMember_account(rs.getString("member_account"));
	            	LiveSupportBean.setEmployee_account(rs.getString("employee_account"));
	            	          	               
	                
	                list.add(LiveSupportBean);
//	                System.out.println(LiveSupportBean);
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
		LiveSupportDAO dao = new LiveSupportDAO();


//        // 新增
		LiveSupportBean LiveSupportBean = new LiveSupportBean();
		LiveSupportBean.setChat_history("請問連假的營業時間?");
		LiveSupportBean.setSender(1);
		LiveSupportBean.setMember_account("amy");
		LiveSupportBean.setEmployee_account("james");
        dao.insert(LiveSupportBean);

//        // 修改
//		LiveSupportBean LiveSupportBean = new LiveSupportBean();
//		LiveSupportBean.setCustomer_service_id(1);
//		LiveSupportBean.setChat_history("那會掛掉");
//        dao.update(LiveSupportBean);


//        // 刪除
//       dao.delete(3);
//
//        // 查詢
//		LiveSupportBean LiveSupportBean = dao.findByPrimaryKey(3);
//		System.out.println(LiveSupportBean);


		// 查詢
//        List<LiveSupportBean> list = dao.getAll();
//        for (LiveSupportBean LiveSupportBean : list) {
//            System.out.println(LiveSupportBean);
//
//        }

	}
}
