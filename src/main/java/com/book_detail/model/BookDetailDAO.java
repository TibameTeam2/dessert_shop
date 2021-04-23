package com.book_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.book_detail.model.BookDetailBean;
import com.util.JDBCUtil;

public class BookDetailDAO {
	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;
	
	String INSERT;
	String UPDATE;
	String DELETE;
	String SELECT_ALL;
	String SELECT_PK;
	
	
	public void insert(BookDetailBean bdBean) {
		INSERT =
			"INSERT INTO book_detail (member_account, booking_time, people_num, booking_status, book_postscript, contact_num, booking_name) VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(INSERT);
		
		pstmt.setString(1, bdBean.getMember_account());
		pstmt.setTimestamp(2, bdBean.getBooking_time());
		pstmt.setInt(3, bdBean.getPeople_num());
		pstmt.setInt(4, bdBean.getBooking_status());
		pstmt.setString(5, bdBean.getBook_postscript());
		pstmt.setString(6, bdBean.getContact_num());
		pstmt.setString(7, bdBean.getBooking_name());

		
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
	
	
	public void update(BookDetailBean bdBean) {
		UPDATE =
			"UPDATE book_detail set member_account = ?, booking_time = ?, people_num = ?, booking_status = ?, book_postscript = ?, contact_num = ?, booking_name = ? where booking_detail_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, bdBean.getMember_account());
			pstmt.setTimestamp(2, bdBean.getBooking_time());
			pstmt.setInt(3, bdBean.getPeople_num());
			pstmt.setInt(4, bdBean.getBooking_status());
			pstmt.setString(5, bdBean.getBook_postscript());
			pstmt.setString(6, bdBean.getContact_num());
			pstmt.setString(7, bdBean.getBooking_name());
			pstmt.setInt(8, bdBean.getBooking_detail_id());

			
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
	
	
	public void delete(Integer booking_detail_id) {
		DELETE =
			"DELETE FROM book_detail where booking_detail_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, booking_detail_id);
			
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
	
	
	public BookDetailBean findByPrimaryKey(Integer booking_detail_id) {
		SELECT_PK =
			"SELECT * FROM book_detail where booking_detail_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BookDetailBean bdBean = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_PK);
			
			pstmt.setInt(1, booking_detail_id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bdBean = new BookDetailBean();
				bdBean.setBooking_detail_id(rs.getInt("booking_detail_id"));
				bdBean.setMember_account(rs.getString("member_account"));
				bdBean.setBooking_establish_time(rs.getTimestamp("booking_establish_time"));
				bdBean.setBooking_time(rs.getTimestamp("booking_time"));
				bdBean.setPeople_num(rs.getInt("people_num"));
				bdBean.setBooking_status(rs.getInt("booking_status"));
				bdBean.setBook_postscript(rs.getString("book_postscript"));
				bdBean.setContact_num(rs.getString("contact_num"));
				bdBean.setBooking_name(rs.getString("booking_name"));
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
		
		return bdBean;
		
	}
	
	
	public List<BookDetailBean> getAll() {
		SELECT_ALL =
			"SELECT * FROM book_detail";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BookDetailBean> list_BookDetailBean = new ArrayList<BookDetailBean>();
		BookDetailBean bdBean = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ALL);
			
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bdBean = new BookDetailBean();
				bdBean.setBooking_detail_id(rs.getInt("booking_detail_id"));
				bdBean.setMember_account(rs.getString("member_account"));
				bdBean.setBooking_establish_time(rs.getTimestamp("booking_establish_time"));
				bdBean.setBooking_time(rs.getTimestamp("booking_time"));
				bdBean.setPeople_num(rs.getInt("people_num"));
				bdBean.setBooking_status(rs.getInt("booking_status"));
				bdBean.setBook_postscript(rs.getString("book_postscript"));
				bdBean.setContact_num(rs.getString("contact_num"));
				bdBean.setBooking_name(rs.getString("booking_name"));
				list_BookDetailBean.add(bdBean);
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
		
		return list_BookDetailBean;
		
	}
	
	
	public List<BookDetailBean> getAllByMemberAccount(String member_account) {
		SELECT_ALL =
			"SELECT * FROM book_detail where member_account = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BookDetailBean> list_BookDetailBean = new ArrayList<BookDetailBean>();
		BookDetailBean bdBean = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, member_account);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bdBean = new BookDetailBean();
				bdBean.setBooking_detail_id(rs.getInt("booking_detail_id"));
				bdBean.setMember_account(rs.getString("member_account"));
				bdBean.setBooking_establish_time(rs.getTimestamp("booking_establish_time"));
				bdBean.setBooking_time(rs.getTimestamp("booking_time"));
				bdBean.setPeople_num(rs.getInt("people_num"));
				bdBean.setBooking_status(rs.getInt("booking_status"));
				bdBean.setBook_postscript(rs.getString("book_postscript"));
				bdBean.setContact_num(rs.getString("contact_num"));
				bdBean.setBooking_name(rs.getString("booking_name"));
				list_BookDetailBean.add(bdBean);
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
		
		return list_BookDetailBean;
		
	}
	
	
	public void update_status(BookDetailBean bdBean) {
		UPDATE =
			"UPDATE book_detail set booking_status = ? where booking_detail_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, bdBean.getBooking_status());
			pstmt.setInt(2, bdBean.getBooking_detail_id());
			
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
	
	
	public static void main(String[] args) {
		BookDetailDAO dao = new BookDetailDAO();
	
		//insert
		//設定資料
//		BookDetailBean bdBean = new BookDetailBean();
//		bdBean.setMember_account("tom");
//		Timestamp ts = Timestamp.valueOf("2021-02-01 14:30:00");
//		bdBean.setBooking_time(ts);
//		bdBean.setPeople_num(5);
//		bdBean.setBooking_status(1);
//		bdBean.setBook_postscript("紅色死神666");
//		bdBean.setContact_num("09-87654321");
//		bdBean.setBooking_name("紅色死神AAA");
//		dao.insert(bdBean);
		
		//update
		//設定資料
//		BookDetailBean bdBean = new BookDetailBean();
//		bdBean.setBooking_detail_id(3);
//		bdBean.setMember_account("tom");
//		Timestamp ts = Timestamp.valueOf("2021-02-03 16:30:00");
//		bdBean.setBooking_time(ts);
//		bdBean.setPeople_num(3);
//		bdBean.setBooking_status(1);
//		bdBean.setBook_postscript("紅色死神777");
//		bdBean.setContact_num("09-87123456");
//		bdBean.setBooking_name("紅色死神CCC");
//		dao.update(bdBean);
		
		//delete
//		dao.delete(4);
		
		//select_pk
//		BookDetailBean bdBean = dao.findByPrimaryKey(1);
//		System.out.print(bdBean);
		
		//select_all
		List<BookDetailBean> list = dao.getAll();
		for (BookDetailBean bdBean : list) {
			System.out.println(bdBean);
		}
		
		
	}

	
}
