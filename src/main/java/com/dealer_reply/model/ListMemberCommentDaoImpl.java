package com.dealer_reply.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;

import com.member_comment.model.MemberCommentBean;
import com.review_image_upload.model.ReviewImageUploadBean;
import com.util.JDBCUtil;

public class ListMemberCommentDaoImpl implements ListMemberCommentDao {

	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;

	private static final String UPDATE = "";
	private static final String DELETE = "";
	private static final String GET_ONE = "";
	private static final String FindAllCommentContent = "select mc.review_id, mc.order_detail_id, mc.product_id, comment_content, rating, comment_time, comment_status, \r\n"
			+ "reply_id, dr.review_id, reply_content, reply_time, dr.employee_account, p.product_name\r\n"
			+ "from member_comment mc\r\n" + "left join dealer_reply dr on mc.review_id = dr.review_id\r\n"
			+ "left join product p on mc.product_id = p.product_id\r\n" + "group by mc.review_id;";
	private static final String FindReviewImage = "select review_image_id, review_image, mc.review_id\r\n" + 
			"from review_image_upload riu\r\n" + 
			"right join member_comment mc on riu.review_id = mc.review_id\r\n" + 
			"where mc.review_id = ?\r\n" + 
			"group by review_image_id;";

	@Override
	public void update(ListMemberCommentBean listMemberCommentBean) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer reply_id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ListMemberCommentBean findByPrimaryKey(Integer reply_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ListMemberCommentBean> findAllCommentContent() {

		List<ListMemberCommentBean> list = new ArrayList<ListMemberCommentBean>();
		ListMemberCommentBean listMemberCommentBean = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FindAllCommentContent);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				listMemberCommentBean = new ListMemberCommentBean();
				listMemberCommentBean.setReview_id(rs.getInt("review_id"));
				listMemberCommentBean.setOrder_detail_id(rs.getInt("order_detail_id"));
				listMemberCommentBean.setProduct_id(rs.getInt("product_id"));
				listMemberCommentBean.setComment_content(rs.getString("comment_content"));
				listMemberCommentBean.setRating(rs.getInt("rating"));
				listMemberCommentBean.setComment_time(rs.getTimestamp("comment_time"));
				listMemberCommentBean.setComment_status(rs.getInt("comment_status"));
				listMemberCommentBean.setReply_id(rs.getInt("reply_id"));
				listMemberCommentBean.setReply_content(rs.getString("reply_content"));
				listMemberCommentBean.setReply_time(rs.getTimestamp("reply_time"));
				listMemberCommentBean.setEmployee_account(rs.getString("employee_account"));
				listMemberCommentBean.setProduct_name(rs.getString("product_name"));

				pstmt1 = con.prepareStatement(FindReviewImage);

				List<String> review_img_url = new ArrayList<String>();
				pstmt1.setInt(1, rs.getInt("review_id"));
				ResultSet rs_image = pstmt1.executeQuery();

				while (rs_image.next()) {
					listMemberCommentBean.setReview_image_id(rs_image.getInt("review_image_id"));
					// 將查詢出來的bean型別的review_image_id轉成字串拼接進網址, 將多個網址放入集合
					review_img_url.add("/reviewImageUpload/getReviewImage?review_image_id="
							+ rs_image.getString("review_image_id"));
				}
				listMemberCommentBean.setReview_image(review_img_url);
				list.add(listMemberCommentBean);
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

		ListMemberCommentDaoImpl dao = new ListMemberCommentDaoImpl();

		List<ListMemberCommentBean> list = dao.findAllCommentContent();
		for (ListMemberCommentBean listMemberCommentBean : list) {
			System.out.println("review_id: " + listMemberCommentBean.getReview_id());
			System.out.println("order_detail_id: " + listMemberCommentBean.getOrder_detail_id() + ",");
			System.out.println("product_id: " + listMemberCommentBean.getProduct_id() + ",");
			System.out.println("comment_content: " + listMemberCommentBean.getComment_content() + ",");
			System.out.println("rating: " + listMemberCommentBean.getRating() + ",");
			System.out.println("comment_time: " + listMemberCommentBean.getComment_time() + ",");
			System.out.println("comment_status: " + listMemberCommentBean.getComment_status() + ",");
			System.out.println("reply_id: " + listMemberCommentBean.getReply_id() + ",");
			System.out.println("reply_content: " + listMemberCommentBean.getReply_content() + ",");
			System.out.println("reply_time: " + listMemberCommentBean.getReply_time() + ",");
			System.out.println("employee_account: " + listMemberCommentBean.getEmployee_account() + ",");
			System.out.println("product_name: " + listMemberCommentBean.getProduct_name() + ",");
			System.out.println("review_image_id: " + listMemberCommentBean.getReview_image_id() + ",");
			System.out.println("review_image: " + listMemberCommentBean.getReview_image() + ",");
			System.out.println("---------------------------------------------------------");
		}
		System.out.println("----------有跑findAllCommentContent()----------");
	}

}
