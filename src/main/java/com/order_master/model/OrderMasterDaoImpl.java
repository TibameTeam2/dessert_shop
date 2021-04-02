package com.order_master.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.order_master.model.OrderMasterBean;
import com.util.JDBCUtil;

public class OrderMasterDaoImpl implements OrderMasterDao {
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
	
	
	public void insert(OrderMasterBean omBean) {
		INSERT =
			"INSERT INTO order_master (member_account, payment_time, payment_method, coupon_id, order_status, invoice_number, order_total, order_remarks) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(INSERT);
		
		pstmt.setString(1, omBean.getMember_account());
		pstmt.setTimestamp(2, omBean.getPayment_time());
		pstmt.setInt(3, omBean.getPayment_method());
		pstmt.setInt(4, omBean.getCoupon_id());
		pstmt.setInt(5, omBean.getOrder_status());
		pstmt.setString(6, omBean.getInvoice_number());
		pstmt.setInt(7, omBean.getOrder_total());
		pstmt.setString(8, omBean.getOrder_remarks());
		
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
	
	
	public void update(OrderMasterBean omBean) {
		UPDATE =
			"UPDATE order_master set member_account = ?, payment_time = ?, payment_method = ?, coupon_id = ?, order_status = ?, invoice_number = ?, order_total = ?, order_remarks = ? where order_master_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, omBean.getMember_account());
			pstmt.setTimestamp(2, omBean.getPayment_time());
			pstmt.setInt(3, omBean.getPayment_method());
			pstmt.setInt(4, omBean.getCoupon_id());
			pstmt.setInt(5, omBean.getOrder_status());
			pstmt.setString(6, omBean.getInvoice_number());
			pstmt.setInt(7, omBean.getOrder_total());
			pstmt.setString(8, omBean.getOrder_remarks());
			pstmt.setInt(9, omBean.getOrder_master_id());
			
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
	
	
	public void delete(Integer order_master_id) {
		String DELETE =
				"DELETE FROM order_master where order_master_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, order_master_id);
			
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
	
	
	public OrderMasterBean findByPrimaryKey(Integer order_master_id) {
		SELECT_PK =
			"SELECT * FROM order_master where order_master_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		OrderMasterBean omBean = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_PK);
			
			pstmt.setInt(1, order_master_id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				omBean = new OrderMasterBean();
				omBean.setOrder_master_id(rs.getInt("order_master_id"));
				omBean.setMember_account(rs.getString("member_account"));
				omBean.setOrder_time(rs.getTimestamp("order_time"));
				omBean.setPayment_time(rs.getTimestamp("payment_time"));
				omBean.setPayment_method(rs.getInt("payment_method"));
				omBean.setCoupon_id(rs.getInt("coupon_id"));
				omBean.setOrder_status(rs.getInt("order_status"));
				omBean.setInvoice_number(rs.getString("invoice_number"));
				omBean.setOrder_total(rs.getInt("order_total"));
				omBean.setOrder_remarks(rs.getString("order_remarks"));
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
		
		return omBean;
		
	}
	
	
	//當作不存在, 詳HistoryCommentOrderMasterDaoImpl
//	public List<OrderMasterBean> findByMemberAccount(String member_account) {
//		SELECT_ALL =
//			"SELECT * FROM order_master where member_account=?";
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		List<OrderMasterBean> list_OrderMasterBean = new ArrayList<OrderMasterBean>();
//		OrderMasterBean omBean = null;
//		
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(SELECT_ALL);
//			
//			pstmt.setString(1, member_account);
//
//			rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				omBean = new OrderMasterBean();
//				omBean.setOrder_master_id(rs.getInt("order_master_id"));
//				omBean.setMember_account(rs.getString("member_account"));
//				omBean.setOrder_time(rs.getTimestamp("order_time"));
//				omBean.setPayment_time(rs.getTimestamp("payment_time"));
//				omBean.setPayment_method(rs.getInt("payment_method"));
//				omBean.setCoupon_id(rs.getInt("coupon_id"));
//				omBean.setOrder_status(rs.getInt("order_status"));
//				omBean.setInvoice_number(rs.getString("invoice_number"));
//				omBean.setOrder_total(rs.getInt("order_total"));
//				omBean.setOrder_remarks(rs.getString("order_remarks"));
//				list_OrderMasterBean.add(omBean);
//			}
//			
//			
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		
//		return list_OrderMasterBean;
//		
//	}
	
	
	
	
	
	
	
	
	public List<OrderMasterBean> getAll() {
		SELECT_ALL =
			"SELECT * FROM order_master";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<OrderMasterBean> list_OrderMasterBean = new ArrayList<OrderMasterBean>();
		OrderMasterBean omBean = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ALL);
			
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				omBean = new OrderMasterBean();
				omBean.setOrder_master_id(rs.getInt("order_master_id"));
				omBean.setMember_account(rs.getString("member_account"));
				omBean.setOrder_time(rs.getTimestamp("order_time"));
				omBean.setPayment_time(rs.getTimestamp("payment_time"));
				omBean.setPayment_method(rs.getInt("payment_method"));
				omBean.setCoupon_id(rs.getInt("coupon_id"));
				omBean.setOrder_status(rs.getInt("order_status"));
				omBean.setInvoice_number(rs.getString("invoice_number"));
				omBean.setOrder_total(rs.getInt("order_total"));
				omBean.setOrder_remarks(rs.getString("order_remarks"));
				list_OrderMasterBean.add(omBean);
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
		
		return list_OrderMasterBean;
		
	}

	@Override
	public List<OrderMasterBean> findByMemberAccount(String member_account) {
		return null;
	}


	public OrderMasterBean getOrderMaster(String member_account) {
		
		SELECT_PK =
				"SELECT * FROM order_master where member_account = ?";
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
					
			OrderMasterBean omBean = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(SELECT_PK);
				
				pstmt.setString(1, member_account);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					omBean = new OrderMasterBean();
					omBean.setOrder_master_id(rs.getInt("order_master_id"));
					omBean.setMember_account(rs.getString("member_account"));
					omBean.setOrder_time(rs.getTimestamp("order_time"));
					omBean.setPayment_time(rs.getTimestamp("payment_time"));
					omBean.setPayment_method(rs.getInt("payment_method"));
					omBean.setCoupon_id(rs.getInt("coupon_id"));
					omBean.setOrder_status(rs.getInt("order_status"));
					omBean.setInvoice_number(rs.getString("invoice_number"));
					omBean.setOrder_total(rs.getInt("order_total"));
					omBean.setOrder_remarks(rs.getString("order_remarks"));
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
			
			return omBean;
			
		}
		
		
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		OrderMasterDaoImpl dao = new OrderMasterDaoImpl();
		
		//insert
		//設定資料
//		OrderMasterBean omBean = new OrderMasterBean();
//		omBean.setMember_account("tom");
//		Timestamp ts = Timestamp.valueOf("2021-02-01 01:02:03");
//		omBean.setPayment_time(ts);
//		omBean.setPayment_method(1);
//		omBean.setCoupon_id(1);
//		omBean.setOrder_status(1);
//		omBean.setInvoice_number("BB12345678");
//		omBean.setOrder_total(877);
//		omBean.setOrder_remarks("紅色死神4");
//		dao.insert(omBean);
		
		//update
		//設定資料
//		OrderMasterBean omBean = new OrderMasterBean();
//		omBean.setOrder_master_id(3);
//		omBean.setMember_account("jason");
//		Timestamp ts = Timestamp.valueOf("2021-02-02 03:04:05");
//		omBean.setPayment_time(ts);
//		omBean.setPayment_method(1);
//		omBean.setCoupon_id(2);
//		omBean.setOrder_status(1);
//		omBean.setInvoice_number("CC12345678");
//		omBean.setOrder_total(8777);
//		omBean.setOrder_remarks("紅色死神666");
//		dao.update(omBean);
		
		//delete
//		dao.delete(4);
		
		//select_pk
//		OrderMasterBean omBean = dao.findByPrimaryKey(1);
//		System.out.print(omBean);
		
		//select_all
//		List<OrderMasterBean> list = dao.getAll();
//		for (OrderMasterBean omBean : list) {
//			System.out.println(omBean);
//		}
		
		
//		select_member
		OrderMasterBean omBean = dao.getOrderMaster("tom");
		System.out.print(omBean);
		
	}
	
	
}
