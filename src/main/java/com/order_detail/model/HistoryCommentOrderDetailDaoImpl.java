package com.order_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.util.JDBCUtil;

public class HistoryCommentOrderDetailDaoImpl implements HistoryCommentOrderDetailDao {

	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;
	private static final String HOHOHO = "select order_master_id, od.order_detail_id, od.product_id, product_name, image_id, mc.review_id, product_image, rating, comment_content, reply_id, reply_content\r\n" + 
			"from order_detail od\r\n" + 
			"left join member_comment mc on od.order_detail_id = mc.order_detail_id\r\n" + 
			"left join dealer_reply dr on mc.review_id = dr.review_id \r\n" + 
			"join product p on od.product_id = p.product_id\r\n" + 
			"left join product_image pi on p.product_id = pi.product_id\r\n" + 
			"where order_master_id = ? and mc.rating is not null\r\n" + 
			"group by order_detail_id\r\n" + 
			"order by order_detail_id;";

	
	@Override
	public List<HistoryCommentOrderDetailBean> findByOrderMasterId(Integer order_master_id) {

		List<HistoryCommentOrderDetailBean> list = new ArrayList<HistoryCommentOrderDetailBean>();
		HistoryCommentOrderDetailBean hcodBean = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(HOHOHO);

			
			pstmt.setInt(1, order_master_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				hcodBean = new HistoryCommentOrderDetailBean();
				hcodBean.setOrder_master_id(rs.getInt("order_master_id"));
				hcodBean.setOrder_detail_id(rs.getInt("order_detail_id"));
				hcodBean.setProduct_id(rs.getInt("product_id"));
				hcodBean.setProduct_name(rs.getString("product_name"));
				hcodBean.setImage_id(rs.getInt("image_id"));
				hcodBean.setProduct_image(rs.getBytes("product_image"));
				hcodBean.setRating(rs.getInt("rating"));
				hcodBean.setComment_content(rs.getString("comment_content"));
				hcodBean.setReply_id(rs.getInt("reply_id"));
				hcodBean.setReply_content(rs.getString("reply_content"));
				
				//憑order_detail_id找到相對應的review_image_id, review_image們
				pstmt1 = con.prepareStatement("select review_image_id, review_image\r\n" + 
						"from review_image_upload riu\r\n" + 
						"join member_comment mc on riu.review_id = mc.review_id\r\n" + 
						"where order_detail_id = ?\r\n" + 
						"order by order_detail_id;");
				
				
				List<String> review_img_url = new ArrayList<String>();
				pstmt1.setInt(1, rs.getInt("order_detail_id"));
				ResultSet rs_image = pstmt1.executeQuery(); //查詢「憑order_detail_id找到相對應的多張review_image」的review_image結果

				while (rs_image.next()) {
					//將查詢出來的bean型別的review_image_id轉成字串拼接進網址, 將多個網址放入集合
					review_img_url.add("/reviewImageUpload/getReviewImage?review_image_id=" + rs_image.getString("review_image_id"));
				}
				hcodBean.setReview_image(review_img_url);
				list.add(hcodBean);
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

		HistoryCommentOrderDetailDaoImpl dao = new HistoryCommentOrderDetailDaoImpl();
		// findByOrderMasterId
		List<HistoryCommentOrderDetailBean> list = dao.findByOrderMasterId(1);
		for (HistoryCommentOrderDetailBean hcodBean : list) {
			System.out.println("order_detail_id: " + hcodBean.getOrder_detail_id() + ",");
			System.out.println("order_master_id: " + hcodBean.getOrder_master_id() + ",");
			System.out.println("product_id: " + hcodBean.getProduct_id() + ",");
			System.out.println("product_name: " + hcodBean.getProduct_name() + ",");
			System.out.println("image_id: " + hcodBean.getImage_id() + ",");
			System.out.println("product_image: " + hcodBean.getProduct_image() + ",");
			System.out.println("rating: " + hcodBean.getRating() + ",");
			System.out.println("comment_content: " + hcodBean.getComment_content() + ",");
			System.out.println("reply_id: " + hcodBean.getReply_id() + ",");
			System.out.println("reply_content: " + hcodBean.getReply_content() + ",");
			System.out.println("review_image: " + hcodBean.getReview_image() + ",");
			System.out.println("---------------------------------------------------------");
		}
		System.out.println("----------有跑查order_master_id----------");
	}
}
