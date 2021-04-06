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

public class HistoryCommentProductDaoImpl implements HistoryCommentProductDao {

	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;

	private static final String HAHAHA = "select om.order_master_id, od.order_detail_id, od.product_id, mc.review_id, review_image_id, review_image\r\n" + 
			"from order_master om\r\n" + 
			"left join order_detail od on om.order_master_id = od.order_master_id\r\n" + 
			"left join member_comment mc on od.order_detail_id = mc.order_detail_id\r\n" + 
			"left join review_image_upload riu on mc.review_id = riu.review_id\r\n" + 
			"where mc.order_detail_id = ?\r\n" + 
			"group by review_image_id\r\n" + 
			"order by order_detail_id;";

//	@Override
//	public void insert(HistoryCommentProductBean historyCommentProductBean) {
//	}
//
//	@Override
//	public void update(HistoryCommentProductBean historyCommentProductBean) {
//	}
//
//	@Override
//	public void delete(Integer order_detail_id) {
//	}


	@Override
	public List<HistoryCommentProductBean> findByOrderDetailId(Integer order_detail_id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<HistoryCommentProductBean> list = new ArrayList<HistoryCommentProductBean>();
		HistoryCommentProductBean hcpBean = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(HAHAHA);

			pstmt.setInt(1, order_detail_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				hcpBean = new HistoryCommentProductBean();
				hcpBean.setOrder_master_id(rs.getInt("order_master_id"));
				hcpBean.setOrder_detail_id(rs.getInt("order_detail_id"));
				hcpBean.setProduct_id(rs.getInt("product_id"));
				hcpBean.setReview_id(rs.getInt("review_id"));
				hcpBean.setReview_image_id(rs.getInt("review_image_id"));
				hcpBean.setReview_image("/reviewImageUpload/getReviewImage?review_image_id="+rs.getInt("review_image_id"));
//				hcpBean.setReview_image("/reviewImageUpload/getReviewImage?review_id="+rs.getInt("review_image_id"));
				list.add(hcpBean);
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

//	@Override
//	public List<HistoryCommentProductBean> getAll() {
//	return null;
//}

	public static void main(String[] args) {

		HistoryCommentProductDaoImpl dao = new HistoryCommentProductDaoImpl();

		// findByOrderDetailId
		List<HistoryCommentProductBean> list = dao.findByOrderDetailId(3);
		for (HistoryCommentProductBean hcpBean : list) {
			System.out.println("order_master_id = " + hcpBean.getOrder_master_id() + ",");
			System.out.println("order_detail_id = " + hcpBean.getOrder_detail_id() + ",");
			System.out.println("product_id = " + hcpBean.getProduct_id() + ",");
			System.out.println("review_id = " + hcpBean.getReview_id() + ",");
			System.out.println("review_image_id = " + hcpBean.getReview_image_id() + ",");
			System.out.println("review_image = " + hcpBean.getReview_image() + ",");
			System.out.println("---------------------------------------------------------");
		}
		System.out.println("----------有跑查order_detail_id----------");

	}
}
