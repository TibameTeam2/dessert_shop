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

	private static final String GET_ONE_STMT = "select member_account, om.order_master_id, order_time, payment_time, invoice_number, coupon_id, payment_method, order_remarks, comment_time from order_master om join order_detail od on om.order_master_id = od.order_master_id join member_comment mc on od.order_detail_id = mc.order_detail_id where member_account = ? order by om.order_master_id;";


//	@Override
//	public void insert(HistoryCommentOrderMasterBean historyCommentOrderMasterBean) {
//	}

//	@Override
//	public void update(HistoryCommentOrderMasterBean historyCommentOrderMasterBean) {
//	}

//	@Override
//	public void delete(Integer order_master_id) {
//	}

	/*
	 * 撈出member_account='jason' 的order_master們 (撈父選單:
	 * 使用HistoryCommentOrderMasterBean)依order_master_id 續撈 order_detail們
	 */

	@Override
	public HistoryCommentOrderMasterBean findByPrimaryKey(String member_account) {
		

		HistoryCommentOrderMasterBean hcomBean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1,  member_account);
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
				hcomBean.setComment_time(rs.getTimestamp("comment_time"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return hcomBean;
	}

//	@Override
//	public List<HistoryCommentOrderMasterBean> getAll() {
//		
//		List<HistoryCommentOrderMasterBean> list = new ArrayList();
//		HistoryCommentOrderMasterBean hcomBean = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		
//		// TODO Auto-generated method stub
//		return null;
//	}

	public static void main(String[] args) {

		HistoryCommentOrderMasterDaoImpl daoImpl = new HistoryCommentOrderMasterDaoImpl();

		// 查詢主鍵
		HistoryCommentOrderMasterBean hcomBean_findByPK = daoImpl.findByPrimaryKey("jason");
		System.out.println("member_account: " + hcomBean_findByPK.getMember_account() + ",");
		System.out.println("order_master_id: " + hcomBean_findByPK.getOrder_master_id() + ",");
		System.out.println("order_time: " + hcomBean_findByPK.getOrder_time() + ",");
		System.out.println("payment_time: " + hcomBean_findByPK.getPayment_time() + ",");
		System.out.println("invoice_number: " + hcomBean_findByPK.getInvoice_number() + ",");
		System.out.println("coupon_id: " + hcomBean_findByPK.getCoupon_id()+",");
		System.out.println("payment_method: " + hcomBean_findByPK.getPayment_method() + ",");
		System.out.println("order_remarks: " + hcomBean_findByPK.getOrder_remarks() + ",");
		System.out.println("comment_time: " + hcomBean_findByPK.getComment_time());
		System.out.println("----------有跑查主鍵----------");

	}
}
