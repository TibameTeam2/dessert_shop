package com.newsLetter.model;

import com.util.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class NewsLetterDaoImpl implements NewsLetterDAO{

	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;


	private static final String INSERT_STMT ="INSERT INTO sweet.newsletter (newsletter_content,newsletter_image, newsletter_releasing_time,newsletter_status,employee_account) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE ="UPDATE newsletter set newsletter_content=?, newsletter_image=?, newsletter_releasing_time=? where newsletter_id = ?";
	private static final String DELETE ="DELETE FROM sweet.newsletter where newsletter_id = ?";
	private static final String GET_ONE_STMT ="SELECT * FROM newsletter where newsletter_id = ?";
	private static final String GET_ALL_STMT ="select * from sweet.newsletter";		
	
	/********************************************* 新增   *******************************************/
	@Override
	public void insert(NewsLetterBean newsletterBean){

		Connection con = null;
		PreparedStatement pstmt = null;		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, newsletterBean.getNewsletter_content());
			pstmt.setBytes(2, newsletterBean.getNewsletter_image());
			pstmt.setTimestamp(3, newsletterBean.getNewsletter_releasing_time());
			pstmt.setInt(4, newsletterBean.getNewsletter_status());
			pstmt.setString(5, newsletterBean.getEmployee_account());

			// 更新筆數
			int count = pstmt.executeUpdate();
			System.out.println(count);

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	
	/********************************************* 修改   *******************************************/
	@Override
	public void update(NewsLetterBean newsletterBean){

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, newsletterBean.getNewsletter_content());
			pstmt.setBytes(2, newsletterBean.getNewsletter_image());
			pstmt.setTimestamp(3, newsletterBean.getNewsletter_releasing_time());
			pstmt.setInt(4, newsletterBean.getNewsletter_id());

			// 更新筆數
			int count = pstmt.executeUpdate();
			System.out.println(count);

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	/********************************************* 刪除   *******************************************/
	@Override
	public void delete(Integer newsletter_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, newsletter_id);

			// 更新筆數
			int count = pstmt.executeUpdate();
			System.out.println(count);

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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

	
	/********************************************* 查一筆(PK) *******************************************/
	@Override
	public NewsLetterBean findByPrimaryKey(Integer newsletter_id) {

		NewsLetterBean newsletterBean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, newsletter_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				newsletterBean = new NewsLetterBean();

				newsletterBean.setNewsletter_id(rs.getInt("newsletter_id"));
				newsletterBean.setNewsletter_content(rs.getString("newsletter_content"));
				newsletterBean.setNewsletter_image(rs.getBytes("newsletter_image"));				
				newsletterBean.setNewsletter_releasing_time(rs.getTimestamp("newsletter_releasing_time"));
				newsletterBean.setNewsletter_status(rs.getInt("newsletter_status"));
				newsletterBean.setEmployee_account(rs.getString("employee_account"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return newsletterBean;
	}

	/******************************************** 查全部   *******************************************/
	@Override
	public List<NewsLetterBean> getAll() {
		List<NewsLetterBean> list = new ArrayList<NewsLetterBean>();
		NewsLetterBean newsletterBean = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				newsletterBean = new NewsLetterBean();

				newsletterBean.setNewsletter_id(rs.getInt("newsletter_id"));
				newsletterBean.setNewsletter_content(rs.getString("newsletter_content"));
            	newsletterBean.setNewsletter_image(rs.getBytes("newsletter_image"));
				newsletterBean.setNewsletter_releasing_time(rs.getTimestamp("newsletter_releasing_time"));
				newsletterBean.setNewsletter_status(rs.getInt("newsletter_status"));
				newsletterBean.setEmployee_account(rs.getString("employee_account"));

				list.add(newsletterBean);
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	// 使用byte[]方式 writeBlob
	public static byte[] getPicture(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];// 陣列空間為資料大小
		fis.read(buffer);
		fis.close();
		return buffer;
	}

	public static void main(String[] args) throws IOException {
		NewsLetterDaoImpl dao = new NewsLetterDaoImpl();


//        // 新增
//		NewsLetterBean newsletterBean= new NewsLetterBean();
//		newsletterBean.setNewsletter_content("abcdefghijklmnopqrstuvwxyz");
//		byte[] pic = getPicture("C:\\project\\images\\newsletter\\1.jpg");
//		newsletterBean.setNewsletter_image(pic);
//		newsletterBean.setNewsletter_releasing_time(java.sql.Timestamp.valueOf("2020-10-10 10:00:00"));
//		newsletterBean.setNewsletter_status(0);
//		newsletterBean.setEmployee_account("peter");
//		dao.insert(newsletterBean);

//        // 修改
//		NewsLetterBean newsletterBean= new NewsLetterBean();
//		newsletterBean.setNewsletter_id(2);
//		newsletterBean.setNewsletter_content("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
//		byte[] pic = getPicture("C:\\project\\images\\newsletter\\1.jpg");
//		newsletterBean.setNewsletter_image(pic);
//		newsletterBean.setNewsletter_releasing_time(java.sql.Timestamp.valueOf("2020-10-20 12:00:00"));
//        dao.update(newsletterBean);


//        // 刪除
//       dao.delete(4);
//
//        // 查詢
//		NewsLetterBean newsletterBean = dao.findByPrimaryKey(3);
//		System.out.println(newsletterBean);


		// 查詢
//        List<NewsLetterBean> list = dao.getAll();
//        for (NewsLetterBean newsletterBean : list) {
//            System.out.println(newsletterBean);
//        }

	}

}
