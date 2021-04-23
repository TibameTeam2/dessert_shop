package com.book_record.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.book_record.model.BookRecordBean;
import com.util.JDBCUtil;

public class BookRecordDAO {
	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;
	
	String INSERT;
	String UPDATE;
	String DELETE;
	String SELECT_ALL;
	String SELECT_ALL_OPEN;
	String SELECT_PK;
	
	
	public void insert(BookRecordBean brBean) {
		INSERT =
			"INSERT INTO book_record (booking_date, ten_total_count, twelve_total_count, fourteen_total_count, sixteen_total_count, eighteen_total_count, twenty_total_count) VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(INSERT);
		
		pstmt.setDate(1, brBean.getBooking_date());
		pstmt.setInt(2, brBean.getTen_total_count());
		pstmt.setInt(3, brBean.getTwelve_total_count());
		pstmt.setInt(4, brBean.getFourteen_total_count());
		pstmt.setInt(5, brBean.getSixteen_total_count());
		pstmt.setInt(6, brBean.getEighteen_total_count());
		pstmt.setInt(7, brBean.getTwenty_total_count());

		
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
	
	
	public void update(BookRecordBean brBean) {
		UPDATE =
			"UPDATE book_record set booking_date = ?, ten_total_count = ?, twelve_total_count = ?, fourteen_total_count = ?, sixteen_total_count = ?, eighteen_total_count = ?, twenty_total_count = ? where book_record_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setDate(1, brBean.getBooking_date());
			pstmt.setInt(2, brBean.getTen_total_count());
			pstmt.setInt(3, brBean.getTwelve_total_count());
			pstmt.setInt(4, brBean.getFourteen_total_count());
			pstmt.setInt(5, brBean.getSixteen_total_count());
			pstmt.setInt(6, brBean.getEighteen_total_count());
			pstmt.setInt(7, brBean.getTwenty_total_count());
			pstmt.setInt(8, brBean.getBook_record_id());

			
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
	
	
	public void delete(Integer book_record_id) {
		DELETE =
			"DELETE FROM book_record where book_record_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, book_record_id);
			
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
	
	
	public BookRecordBean findByPrimaryKey(Integer book_record_id) {
		SELECT_PK =
			"SELECT * FROM book_record where book_record_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		BookRecordBean brBean = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_PK);
			
			pstmt.setInt(1, book_record_id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				brBean = new BookRecordBean();
				brBean.setBook_record_id(rs.getInt("book_record_id"));
				brBean.setBooking_date(rs.getDate("booking_date"));
				brBean.setTen_total_count(rs.getInt("ten_total_count"));
				brBean.setTwelve_total_count(rs.getInt("twelve_total_count"));
				brBean.setFourteen_total_count(rs.getInt("fourteen_total_count"));
				brBean.setSixteen_total_count(rs.getInt("sixteen_total_count"));
				brBean.setEighteen_total_count(rs.getInt("eighteen_total_count"));
				brBean.setTwenty_total_count(rs.getInt("twenty_total_count"));
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
		
		return brBean;
		
	}
	
	
	public BookRecordBean findByBookingDate(java.sql.Date booking_date) {
		SELECT_PK =
			"SELECT * FROM book_record where booking_date = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		BookRecordBean brBean = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_PK);
			
			pstmt.setDate(1, booking_date);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				brBean = new BookRecordBean();
				brBean.setBook_record_id(rs.getInt("book_record_id"));
				brBean.setBooking_date(rs.getDate("booking_date"));
				brBean.setTen_total_count(rs.getInt("ten_total_count"));
				brBean.setTwelve_total_count(rs.getInt("twelve_total_count"));
				brBean.setFourteen_total_count(rs.getInt("fourteen_total_count"));
				brBean.setSixteen_total_count(rs.getInt("sixteen_total_count"));
				brBean.setEighteen_total_count(rs.getInt("eighteen_total_count"));
				brBean.setTwenty_total_count(rs.getInt("twenty_total_count"));
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
		
		return brBean;
		
	}
	
	
	public List<BookRecordBean> getAll() {
		SELECT_ALL =
			"SELECT * FROM book_record";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BookRecordBean> list_BookRecordBean = new ArrayList<BookRecordBean>();
		BookRecordBean brBean = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ALL);
			
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				brBean = new BookRecordBean();
				brBean.setBook_record_id(rs.getInt("book_record_id"));
				brBean.setBooking_date(rs.getDate("booking_date"));
				brBean.setTen_total_count(rs.getInt("ten_total_count"));
				brBean.setTwelve_total_count(rs.getInt("twelve_total_count"));
				brBean.setFourteen_total_count(rs.getInt("fourteen_total_count"));
				brBean.setSixteen_total_count(rs.getInt("sixteen_total_count"));
				brBean.setEighteen_total_count(rs.getInt("eighteen_total_count"));
				brBean.setTwenty_total_count(rs.getInt("twenty_total_count"));
				list_BookRecordBean.add(brBean);
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
		
		return list_BookRecordBean;
		
	}
	
	
	public List<BookRecordBean> getAllOpen() {
		SELECT_ALL_OPEN =
			"SELECT * FROM book_record where booking_date > current_date() order by booking_date";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BookRecordBean> list_BookRecordBean = new ArrayList<BookRecordBean>();
		BookRecordBean brBean = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ALL_OPEN);
			
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				brBean = new BookRecordBean();
				brBean.setBook_record_id(rs.getInt("book_record_id"));
				brBean.setBooking_date(rs.getDate("booking_date"));
				brBean.setTen_total_count(rs.getInt("ten_total_count"));
				brBean.setTwelve_total_count(rs.getInt("twelve_total_count"));
				brBean.setFourteen_total_count(rs.getInt("fourteen_total_count"));
				brBean.setSixteen_total_count(rs.getInt("sixteen_total_count"));
				brBean.setEighteen_total_count(rs.getInt("eighteen_total_count"));
				brBean.setTwenty_total_count(rs.getInt("twenty_total_count"));
				list_BookRecordBean.add(brBean);
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
		
		return list_BookRecordBean;
		
	}
	
	
	public BookRecordBean findByBookingDate(String booking_date) {
		SELECT_PK =
			"SELECT * FROM book_record where booking_date = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		BookRecordBean brBean = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_PK);
			
			pstmt.setString(1, booking_date);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				brBean = new BookRecordBean();
				brBean.setBook_record_id(rs.getInt("book_record_id"));
				brBean.setBooking_date(rs.getDate("booking_date"));
				brBean.setTen_total_count(rs.getInt("ten_total_count"));
				brBean.setTwelve_total_count(rs.getInt("twelve_total_count"));
				brBean.setFourteen_total_count(rs.getInt("fourteen_total_count"));
				brBean.setSixteen_total_count(rs.getInt("sixteen_total_count"));
				brBean.setEighteen_total_count(rs.getInt("eighteen_total_count"));
				brBean.setTwenty_total_count(rs.getInt("twenty_total_count"));
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
		
		return brBean;
		
	}
	
	
	
	
	public static void main(String[] args) {
		BookRecordDAO dao = new BookRecordDAO();
		
		//insert
		//設定資料
//		BookRecordBean brBean = new BookRecordBean();
//		Date date = Date.valueOf("2021-02-06");
//		brBean.setBooking_date(date);
//		brBean.setTen_total_count(3);
//		brBean.setTwelve_total_count(3);
//		brBean.setFourteen_total_count(3);
//		brBean.setSixteen_total_count(6);
//		brBean.setEighteen_total_count(6);
//		brBean.setTwenty_total_count(6);
//		dao.insert(brBean);
		
		//update
		//設定資料
//		BookRecordBean brBean = new BookRecordBean();
//		brBean.setBook_record_id(3);
//		Date date = Date.valueOf("2021-02-28");
//		brBean.setBooking_date(date);
//		brBean.setTen_total_count(4);
//		brBean.setTwelve_total_count(4);
//		brBean.setFourteen_total_count(4);
//		brBean.setSixteen_total_count(7);
//		brBean.setEighteen_total_count(7);
//		brBean.setTwenty_total_count(7);
//		dao.update(brBean);
		
		//delete
//		dao.delete(4);
		
		//select_pk
//		BookRecordBean brBean = dao.findByPrimaryKey(1);
//		System.out.print(brBean);
		
		//select_all
		List<BookRecordBean> list = dao.getAll();
		for (BookRecordBean brBean : list) {
			System.out.println(brBean);
		}
		
		
	}


}
