package com.daily_special.model;

import com.live_support.model.LiveSupportBean;
import com.live_support.model.LiveSupportDAO;
import com.util.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class DailySpecialDAO implements DailySpecialDAO_interface {
	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;

	
	String INSERT;
	String UPDATE;
	String DELETE;
	String SELECT_ALL;
	String SELECT_ALL_VALID; // 取得"有效"的每日優惠
	String SELECT_PK;
	
	public void init() {
//		driver = "com.mysql.cj.jdbc.Driver";
//		url = "jdbc:mysql://localhost:3306/sweet?serverTimezone=Asia/Taipei";
//		userid = "root";
//		passwd = "root";
	}
	
	public int insert(DailySpecialBean dsBean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		INSERT = "INSERT INTO daily_special (product_id, discount_price, discount_start_time, discount_deadline) VALUES (?, ?, ?, ?) ";
		

		int count = 0;
		try {
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(INSERT);
		
		pstmt.setInt(1, dsBean.getProduct_id());
		pstmt.setInt(2, dsBean.getDiscount_price());
		pstmt.setTimestamp(3, dsBean.getDiscount_start_time());
		pstmt.setTimestamp(4, dsBean.getDiscount_deadline());
		
		pstmt.executeUpdate();
		
		pstmt = con.prepareStatement("select LAST_INSERT_ID()");
		
		ResultSet rs = null;
		rs = pstmt.executeQuery();
		if(rs.next()) {			
			count =rs.getInt(1);
			System.out.println(count);
		}
		rs.close();
		
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."
					+ e.getMessage());
		}catch (SQLException se) {
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
		return count;
	}
	
	public void update(DailySpecialBean dsBean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		UPDATE = "UPDATE daily_special set product_id = ?, discount_price = ?, discount_start_time = ?, discount_deadline = ?"
				+ "where discount_product_id = ?";
		

		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, dsBean.getProduct_id());
			pstmt.setInt(2, dsBean.getDiscount_price());
			pstmt.setTimestamp(3, dsBean.getDiscount_start_time());
			pstmt.setTimestamp(4, dsBean.getDiscount_deadline());
			
			pstmt.setInt(5,  dsBean.getDiscount_product_id());
			
			pstmt.executeUpdate();
			
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
	
	public void delete(Integer discount_product_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		DELETE = "DELETE FROM daily_special WHERE discount_product_id = ?";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1,  discount_product_id);
			
			pstmt.executeUpdate();
			
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

// 後台的getAll
	public List<DailySpecialBean> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SELECT_ALL = "SELECT * FROM daily_special";	
		List<DailySpecialBean> list_dsBean = new ArrayList<DailySpecialBean>();
		DailySpecialBean dsBean = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ALL);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dsBean = new DailySpecialBean();
				dsBean.setDiscount_product_id(rs.getInt("discount_product_id"));
				dsBean.setProduct_id(rs.getInt("product_id"));
				dsBean.setDiscount_price(rs.getInt("discount_price"));
				dsBean.setDiscount_start_time(rs.getTimestamp("discount_start_time"));
				dsBean.setDiscount_deadline(rs.getTimestamp("discount_deadline"));
				list_dsBean.add(dsBean);
//				System.out.println(dsBean);
				
		
			}
			
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
		return list_dsBean;
	}	
	
// 前台的 getAll，只有"有效"的	
	public List<DailySpecialBean> getAllValid() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SELECT_ALL_VALID = "SELECT ds.*, p.* FROM (SELECT * FROM daily_special ds WHERE now() BETWEEN discount_start_time AND discount_deadline)ds join (SELECT * FROM product p WHERE product_status = 1)p  on ds.product_id = p.product_id";	
		List<DailySpecialBean> list_dsBean = new ArrayList<DailySpecialBean>();
		DailySpecialBean dsBean = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ALL_VALID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dsBean = new DailySpecialBean();
				dsBean.setDiscount_product_id(rs.getInt("discount_product_id"));
				dsBean.setProduct_id(rs.getInt("product_id"));
				dsBean.setDiscount_price(rs.getInt("discount_price"));
				dsBean.setDiscount_start_time(rs.getTimestamp("discount_start_time"));
				dsBean.setDiscount_deadline(rs.getTimestamp("discount_deadline"));
				list_dsBean.add(dsBean);
//				System.out.println(dsBean);
				
				
			}
			
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
		return list_dsBean;
	}	
		
	public DailySpecialBean findByPrimaryKey(Integer discount_product_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SELECT_PK = "SELECT * FROM daily_special WHERE discount_product_id = ?";	
		
		// 設定資料
		DailySpecialBean dsBean = null;

		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_PK);
			
			pstmt.setInt(1, discount_product_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dsBean = new DailySpecialBean();
				dsBean.setDiscount_product_id(rs.getInt("discount_product_id"));
				dsBean.setProduct_id(rs.getInt("product_id"));
				dsBean.setDiscount_price(rs.getInt("discount_price"));
				dsBean.setDiscount_start_time(rs.getTimestamp("discount_start_time"));
				dsBean.setDiscount_deadline(rs.getTimestamp("discount_deadline"));
			
//				System.out.println(dsBean);
				
			}
			
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
		return dsBean;
	}



	public static void main(String[] args) {
		DailySpecialDAO dao = new DailySpecialDAO();


        // 新增
		// 設定資料
//		DailySpecialBean dsBean = new DailySpecialBean();
//		dsBean.setProduct_id(2);
//		dsBean.setDiscount_price(150);
//		dsBean.setDiscount_start_time(Timestamp.valueOf("2021-02-28 00:00:00"));
//		dsBean.setDiscount_deadline(Timestamp.valueOf("2021-02-28 23:59:59"));
//		dao.insert(dsBean);

        // 修改
		// 設定資料
//		DailySpecialBean dsBean = new DailySpecialBean();
//		dsBean.setProduct_id(3);
//		dsBean.setDiscount_price(240);
//		dsBean.setDiscount_start_time(Timestamp.valueOf("2021-04-04 00:00:00"));
//		dsBean.setDiscount_deadline(Timestamp.valueOf("2021-04-04 23:59:59"));
//
//		dsBean.setDiscount_product_id(2);
//		dao.update(dsBean);


//        // 刪除
//		dao.delete(6);

        // 查詢
//		DailySpecialBean dsBean = dao.findByPrimaryKey(4);
//		System.out.println(dsBean);


		// 查詢所有優惠，無論有效或失效
        List<DailySpecialBean> list = dao.getAll();
        for (DailySpecialBean dsBean : list) {
            System.out.println(dsBean);
        }
		
		
		// 查詢有效的優惠
//		DailySpecialService dsSvc = new DailySpecialService();
//		
//		List<DailySpecialBean> list = dsSvc.getValidDailySpecial();
//		 for (DailySpecialBean dsBean : list) {
//           System.out.println(dsBean);
//       }
		
		

	}
}
