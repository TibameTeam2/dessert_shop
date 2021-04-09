package com.member_comment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.order_detail.model.HistoryCommentOrderDetailBean;
import com.util.JDBCUtil;

public class YetToLeaveCommentDaoImpl implements YetToLeaveCommentDao {

	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;

	// 某order_master_id沒有comment_time表示是尚未評價, select這些資料顯示在尚未評價頁面
	private static final String YetToLeaveComment_FindByMemberAccount = "select member_account, om.order_master_id, od.order_detail_id, order_time, payment_time, invoice_number, coupon_id, payment_method, order_remarks, comment_time\r\n" + 
			"from order_master om\r\n" + 
			"left join order_detail od on om.order_master_id = od.order_master_id\r\n" + 
			"left join product p on od.product_id = p.product_id\r\n" + 
			"left join product_image pi on p.product_id = pi.product_id\r\n" + 
			"left join member_comment mc on od.order_detail_id = mc.order_detail_id\r\n" + 
			"where member_account = ? and comment_time is null\r\n" + 
			"group by od.order_master_id\r\n" + 
			"order by order_master_id; ";
	
	private static final String YetToLeaveComment_FindByOrderMasterId = "select om.order_master_id, od.order_detail_id, p.product_id, product_name, product_image, comment_time\r\n" + 
			"from order_master om\r\n" + 
			"left join order_detail od on om.order_master_id = od.order_master_id\r\n" + 
			"left join product p on od.product_id = p.product_id\r\n" + 
			"left join product_image pi on p.product_id = pi.product_id\r\n" + 
			"left join member_comment mc on od.order_detail_id = mc.order_detail_id\r\n" + 
			"where om.order_master_id = ? and comment_time is null\r\n" + 
			"group by od.order_detail_id\r\n" + 
			"order by order_master_id;  "; 

	@Override
	public List<YetToLeaveCommentBean> findByMemberAccount(String member_account) {

		List<YetToLeaveCommentBean> list = new ArrayList<YetToLeaveCommentBean>();
		YetToLeaveCommentBean ytlcBean = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(YetToLeaveComment_FindByMemberAccount);

			pstmt.setString(1, member_account);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ytlcBean = new YetToLeaveCommentBean();
				ytlcBean.setMember_account(rs.getString("member_account"));
				ytlcBean.setOrder_master_id(rs.getInt("order_master_id"));
				ytlcBean.setOrder_detail_id(rs.getInt("order_detail_id"));
				ytlcBean.setOrder_time(rs.getTimestamp("order_time"));
				ytlcBean.setPayment_time(rs.getTimestamp("payment_time"));
				ytlcBean.setInvoice_number(rs.getString("invoice_number"));
				ytlcBean.setCoupon_id(rs.getInt("coupon_id"));
				ytlcBean.setPayment_method(rs.getInt("payment_method"));
				ytlcBean.setOrder_remarks(rs.getString("order_remarks"));
				ytlcBean.setComment_time(rs.getTimestamp("comment_time"));
//				ytlcBean.setProduct_image(rs.getBytes("product_image"));
//				ytlcBean.setProduct_name(rs.getString("product_name"));
//				ytlcBean.setProduct_id(rs.getInt("product_id"));
				list.add(ytlcBean);
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

	
	
	@Override
	public List<YetToLeaveCommentBean> findByOrderMasterId(Integer order_master_id) {
		List<YetToLeaveCommentBean> list = new ArrayList<YetToLeaveCommentBean>();
		YetToLeaveCommentBean ytlcBean = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(YetToLeaveComment_FindByOrderMasterId);

			pstmt.setInt(1, order_master_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ytlcBean = new YetToLeaveCommentBean();
				ytlcBean.setOrder_master_id(rs.getInt("order_master_id"));
				ytlcBean.setOrder_detail_id(rs.getInt("order_detail_id"));
				ytlcBean.setProduct_id(rs.getInt("product_id"));
				ytlcBean.setProduct_name(rs.getString("product_name"));
				ytlcBean.setProduct_image(rs.getBytes("product_image"));
				ytlcBean.setComment_time(rs.getTimestamp("comment_time"));
				list.add(ytlcBean);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {

		YetToLeaveCommentDaoImpl dao = new YetToLeaveCommentDaoImpl();
		// findByMemberAccount();
		List<YetToLeaveCommentBean> list = dao.findByMemberAccount("amy");
		for (YetToLeaveCommentBean ytlcBean : list) {
			System.out.println("member_account = " + ytlcBean.getMember_account() + ",");
			System.out.println("order_master_id = " + ytlcBean.getOrder_master_id() + ",");
			System.out.println("order_detail_id = " + ytlcBean.getOrder_detail_id() + ",");
			System.out.println("order_time = " + ytlcBean.getOrder_time() + ",");
			System.out.println("payment_time = " + ytlcBean.getPayment_time() + ",");
			System.out.println("invoice_number = " + ytlcBean.getInvoice_number()+ ",");
			System.out.println("coupon_id = " + ytlcBean.getCoupon_id() + ",");
			System.out.println("payment_method = " + ytlcBean.getPayment_method() + ",");
			System.out.println("order_remarks = " + ytlcBean.getOrder_remarks() + ",");
			System.out.println("comment_time = " + ytlcBean.getComment_time() + ",");
//			System.out.println("product_image = " + ytlcBean.getProduct_image() + ",");
//			System.out.println("product_name = " + ytlcBean.getProduct_name() + ",");
//			System.out.println("product_id = " + ytlcBean.getProduct_id());
			System.out.println("-----------------------------");
		}
		System.out.println("----------有跑查member_account----------");

		
		
		List<YetToLeaveCommentBean> list1 = dao.findByOrderMasterId(6);
		for (YetToLeaveCommentBean ytlcBean1 : list1) {
			System.out.println("order_master_id = " + ytlcBean1.getOrder_master_id() + ",");
			System.out.println("order_detail_id = " + ytlcBean1.getOrder_detail_id() + ",");
			System.out.println("product_id = " + ytlcBean1.getProduct_id() + ",");
			System.out.println("product_name = " + ytlcBean1.getProduct_name() + ",");
			System.out.println("product_image = " + ytlcBean1.getProduct_image()+ ",");
			System.out.println("comment_time = " + ytlcBean1.getComment_time()+ ",");
			System.out.println("-----------------------------");
		}
		System.out.println("----------有跑查order_master_id----------");
		
	}


}
