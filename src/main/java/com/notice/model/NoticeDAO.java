package com.notice.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.live_support.model.LiveSupportBean;
import com.live_support.model.LiveSupportDAO;
import com.util.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;


public class NoticeDAO {

	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;

	/******************************************* 初始化 *******************************************/
	public void init() {

//		driver = "com.mysql.cj.jdbc.Driver";
//		url = "jdbc:mysql://localhost:3306/sweet?serverTimezone=Asia/Taipei";
//		userid = "root";
//		passwd = "1qaz2wsx";

	}

	/******************************************* 新增 *******************************************/
	public void insert(NoticeBean noticeBean) {
//		NoticeBean noticeBean = new NoticeBean();

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);	
			pstmt = con.prepareStatement(
					"INSERT INTO sweet.notice (notice_type,notice_content,read_status,member_account) VALUES (?, ?, ?, ?)");

			pstmt.setInt(1, noticeBean.getNotice_type());
			pstmt.setString(2, noticeBean.getNotice_content());
			pstmt.setInt(3, noticeBean.getRead_status());
			pstmt.setString(4, noticeBean.getMember_account());

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
	    public void delete(Integer notice_id) {
//	        Integer notice_id = 4;

	        Connection con = null;
	        PreparedStatement pstmt = null;

	        try {

	            Class.forName(driver);
	            con = DriverManager.getConnection(url, userid, passwd);
	            pstmt = con.prepareStatement("DELETE FROM sweet.notice where notice_id = ?");

	            pstmt.setInt(1, notice_id);

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
	    public void update(NoticeBean noticeBean) {
//		 NoticeBean noticeBean = new NoticeBean();
		 


	        Connection con = null;
	        PreparedStatement pstmt = null;

	        try {

	            Class.forName(driver);
	            con = DriverManager.getConnection(url, userid, passwd);
	            pstmt = con.prepareStatement("UPDATE notice set notice_content=?, read_status=? where notice_id = ?");

	            pstmt.setString(1, noticeBean.getNotice_content());
	            pstmt.setInt(2, noticeBean.getRead_status());
	            pstmt.setInt(3, noticeBean.getNotice_id());

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
	    public NoticeBean findByPrimaryKey(Integer notice_id) {
//	        Integer notice_id = 2;   //要找的PK

	        NoticeBean noticeBean = null;
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        try {

	            Class.forName(driver);
	            con = DriverManager.getConnection(url, userid, passwd);
	            pstmt = con.prepareStatement("SELECT * FROM notice where notice_id = ?");

	            pstmt.setInt(1,notice_id);

	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	                // empVo 也稱為 Domain objects
	            	noticeBean = new NoticeBean();
	            	
	            	noticeBean.setNotice_id(rs.getInt("notice_id"));
	            	noticeBean.setNotice_type(rs.getInt("notice_type"));
	            	noticeBean.setNotice_content(rs.getString("notice_content"));
	            	noticeBean.setNotice_time(rs.getTimestamp("notice_time"));
	            	noticeBean.setRead_status(rs.getInt("read_status"));
	            	noticeBean.setMember_account(rs.getString("member_account"));	            	
	            
//	                System.out.println(noticeBean);
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
	        return noticeBean;
	    }
	 
	    /******************************************* 查全部   *******************************************/
	    public List<NoticeBean> getAll() {
	        List<NoticeBean> list = new ArrayList<NoticeBean>();
	        NoticeBean noticeBean = null;

	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        
	        try {
	            Class.forName(driver);
	            con = DriverManager.getConnection(url, userid, passwd);
	            pstmt = con.prepareStatement("select * from sweet.notice");
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	                
	            	noticeBean = new NoticeBean();
	            	
	            	noticeBean.setNotice_id(rs.getInt("notice_id"));
	            	noticeBean.setNotice_type(rs.getInt("notice_type"));
	            	noticeBean.setNotice_content(rs.getString("notice_content"));
	            	noticeBean.setNotice_time(rs.getTimestamp("notice_time"));
	            	noticeBean.setRead_status(rs.getInt("read_status"));
	            	noticeBean.setMember_account(rs.getString("member_account"));	
	                
	                list.add(noticeBean);
//	                System.out.println(noticeBean);
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
		NoticeDAO dao = new NoticeDAO();


//        // 新增
//		NoticeBean noticeBean = new NoticeBean();
//		noticeBean.setNotice_type(1);
//		noticeBean.setNotice_content("貴賓您好：您有訂購岩融巧克力蛋糕乙客，請準時來提取，謝謝!");
//		noticeBean.setRead_status(0);
//		noticeBean.setMember_account("jason");
//		dao.insert(noticeBean);

//        // 修改
//		NoticeBean noticeBean = new NoticeBean();
//		noticeBean.setNotice_id(1);
//		noticeBean.setNotice_content("貴賓您好：您有訂購岩融巧克力蛋糕乙客，請準時來提取，謝謝!");
//		noticeBean.setRead_status(1);
//        dao.update(noticeBean);


//        // 刪除
//       dao.delete(4);
//
//        // 查詢
//		NoticeBean noticeBean = dao.findByPrimaryKey(3);
//		System.out.println(noticeBean);


		// 查詢
        List<NoticeBean> list = dao.getAll();
        for (NoticeBean NoticeBean : list) {
            System.out.println(NoticeBean);
        }

	}
	    
}
