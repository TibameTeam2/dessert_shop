package com.order_master.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.util.JDBCUtil;

public class HistoryCommentOrderMasterDaoImpl implements HistoryCommentOrderMasterDao {

	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;

	private static final String HEHEHE = "select member_account, om.order_master_id, order_time, payment_time, invoice_number, coupon_id, payment_method, order_remarks, comment_time\r\n" + 
			"from order_master om\r\n" + 
			"left join order_detail od on om.order_master_id = od.order_master_id\r\n" + 
			"left join member_comment mc on od.order_detail_id = mc.order_detail_id\r\n" + 
			"where member_account = ?\r\n" + 
			"group by order_master_id; ";


//	@Override
//	public void insert(HistoryCommentOrderMasterBean historyCommentOrderMasterBean) {
//	}

//	@Override
//	public void update(HistoryCommentOrderMasterBean historyCommentOrderMasterBean) {
//	}

//	@Override
//	public void delete(Integer order_master_id) {
//	}
	
	
	
	/**
	 * 撈出member_account='jason' 的order_master們 (撈父選單:
	 * 使用HistoryCommentOrderMasterBean)依order_master_id 續撈 order_detail們
	 */
	@Override
	public List<HistoryCommentOrderMasterBean> findByMemberAccount(String member_account) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<HistoryCommentOrderMasterBean> list = new ArrayList<HistoryCommentOrderMasterBean>();
		HistoryCommentOrderMasterBean hcomBean = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(HEHEHE);
			
			pstmt.setString(1, member_account);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				hcomBean = new HistoryCommentOrderMasterBean();
				hcomBean.setMember_account(rs.getString("member_account"));
				hcomBean.setOrder_master_id(rs.getInt("order_master_id"));
				hcomBean.setOrder_time(rs.getTimestamp("order_time"));
				hcomBean.setPayment_time(rs.getTimestamp("payment_time"));
				hcomBean.setInvoice_number(rs.getString("invoice_number"));
				hcomBean.setCoupon_id(rs.getInt("coupon_id"));
				hcomBean.setPayment_method(rs.getInt("payment_method"));
				hcomBean.setOrder_remarks(rs.getString("order_remarks"));
				hcomBean.setComment_time(rs.getTimestamp("comment_time"));;
				list.add(hcomBean);
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
	

	

//	@Override
//	public List<HistoryCommentOrderMasterBean> getAll() {
//		List<HistoryCommentOrderMasterBean> list = new ArrayList<HistoryCommentOrderMasterBean>();
//		HistoryCommentOrderMasterBean hcomBean = null;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				hcomBean = new HistoryCommentOrderMasterBean();
//				hcomBean.setMember_account(rs.getString("member_account"));
//				hcomBean.setOrder_master_id(rs.getInt("order_master_id"));
//				hcomBean.setOrder_time(rs.getTimestamp("order_time"));
//				hcomBean.setPayment_time(rs.getTimestamp("payment_time"));
//				hcomBean.setInvoice_number(rs.getString("invoice_number"));
//				hcomBean.setCoupon_id(rs.getInt("coupon_id"));
//				hcomBean.setPayment_method(rs.getInt("payment_method"));
//				hcomBean.setOrder_remarks(rs.getString("order_remarks"));
//				hcomBean.setComment_time(rs.getTimestamp("comment_time"));
//				list.add(hcomBean);
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
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
//		return list;
//	}



	public static void main(String[] args) {

		HistoryCommentOrderMasterDaoImpl daoImpl = new HistoryCommentOrderMasterDaoImpl();

		// 查詢主鍵-ok
		List<HistoryCommentOrderMasterBean> list = daoImpl.findByMemberAccount("jason");
		for(HistoryCommentOrderMasterBean hcomb : list) {
			System.out.println("member_account: " + hcomb.getMember_account() + ",");
			System.out.println("order_master_id: " + hcomb.getOrder_master_id() + ",");
			System.out.println("order_time: " + hcomb.getOrder_time() + ",");
			System.out.println("payment_time: " + hcomb.getPayment_time() + ",");
			System.out.println("invoice_number: " + hcomb.getInvoice_number() + ",");
			System.out.println("coupon_id: " + hcomb.getCoupon_id()+",");
			System.out.println("payment_method: " + hcomb.getPayment_method() + ",");
			System.out.println("order_remarks: " + hcomb.getOrder_remarks() + ",");
			System.out.println("comment_time: " + hcomb.getComment_time());
		}
		System.out.println("----------有跑查member_account----------");

	}
}
