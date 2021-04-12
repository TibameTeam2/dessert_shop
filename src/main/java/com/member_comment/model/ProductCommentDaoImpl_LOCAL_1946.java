package com.member_comment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.util.JDBCUtil;

public class ProductCommentDaoImpl implements ProductCommentDao {

	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;

	private static final String FindProductComment = "select om.member_account, member_name, member_photo, p.product_id, mc.review_id, rating, comment_content, comment_time, review_image_id, review_image, reply_id, reply_content, reply_time\r\n"
			+ "from member_comment mc\r\n" + "left join review_image_upload riu on mc.review_id = riu.review_id\r\n"
			+ "left join dealer_reply dr on mc.review_id = dr.review_id\r\n"
			+ "left join product p on mc.product_id = p.product_id\r\n"
			+ "left join order_detail od on mc.order_detail_id = od.order_detail_id\r\n"
			+ "left join order_master om on od.order_master_id = om.order_master_id\r\n"
			+ "left join member m on om.member_account = m.member_account\r\n"
			+ "where p.product_id = ? and mc.review_id is not null\r\n" + "group by mc.review_id\r\n"
			+ "order by mc.comment_time asc;";

	@Override
	public List<ProductCommentBean> findByProductId(Integer product_id) {

		List<ProductCommentBean> list = new ArrayList<ProductCommentBean>();
		ProductCommentBean pcBean = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FindProductComment);

			pstmt.setInt(1, product_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pcBean = new ProductCommentBean();
				pcBean.setMember_account(rs.getString("member_account"));
				pcBean.setMember_name(rs.getString("member_name"));
//				pcBean.setMember_photo("/member/backend_getPhoto?member_account=" + pcBean.getMember_account());
				pcBean.setMember_photo("/member/backend_getPhoto?id=" + pcBean.getMember_account());
				pcBean.setProduct_id(rs.getInt("product_id"));
				pcBean.setReview_id(rs.getInt("review_id"));
				pcBean.setRating(rs.getInt("rating"));
				pcBean.setComment_content(rs.getString("comment_content"));
				pcBean.setComment_time(rs.getTimestamp("comment_time"));
				pcBean.setReview_image_id(rs.getInt("review_image_id"));
				pcBean.setReply_id(rs.getInt("reply_id"));
				pcBean.setReply_content(rs.getString("reply_content"));
				pcBean.setReply_time(rs.getTimestamp("reply_time"));
				
				//憑product_id找到相對應的review_image_id, review_image們
				pstmt1 = con.prepareStatement("select review_image_id, review_image\r\n" + 
						"from review_image_upload riu\r\n" + 
						"join member_comment mc on riu.review_id = mc.review_id\r\n" + 
						"where mc.product_id = ? and mc.review_id = ?\r\n" + 
						"order by mc.comment_time asc;");
				
				List<String> review_img_url = new ArrayList<String>();
				pstmt1.setInt(1, rs.getInt("product_id"));
				pstmt1.setInt(2, rs.getInt("review_id"));
				ResultSet rs_image = pstmt1.executeQuery(); //查詢「憑product_id找到相對應的多張review_image」的review_image結果
				
				while (rs_image.next()) {
					//將查詢出來的bean型別的review_image_id轉成字串拼接進網址, 將多個網址放入集合
					review_img_url.add("/reviewImageUpload/getReviewImage?review_image_id=" + rs_image.getString("review_image_id"));
				}
				
				pcBean.setReview_image(review_img_url);
				list.add(pcBean);
				
			} // Handle any driver errors
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

}
