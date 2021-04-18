package com.member_comment.model;

import com.review_image_upload.model.ReviewImageUploadBean;
import com.util.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.*;

//import org.junit.Test;

public class MemberCommentDaoImpl implements MemberCommentDao {

	private static JdbcTemplate jdbcTemplate;
	private String DRIVER = JDBCUtil.driver;
	private String URL = JDBCUtil.url;
	private String USERID = JDBCUtil.user;
	private String PASSWORD = JDBCUtil.password;

	private static final String GetReviewImageUploadByReviewId = "SELECT review_image_id, review_image, review_id FROM review_image_upload where review_id = ? order by review_image_id";

	// insert
	public int insert(MemberCommentBean MemberCommentBean) {

		Connection con = null;
		PreparedStatement pstmt = null;
		int key = 0;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(
					"INSERT INTO member_comment(order_detail_id, comment_content, rating, product_id, comment_status) VALUES (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, MemberCommentBean.getOrder_detail_id());
			pstmt.setString(2, MemberCommentBean.getComment_content());
			pstmt.setInt(3, MemberCommentBean.getRating());
			pstmt.setInt(4, MemberCommentBean.getProduct_id());
			pstmt.setInt(5, 1);
			pstmt.executeUpdate();

			// getGeneratedKeys() : 擷取執行這個 SQLServerStatement 物件最後所建立的任何自動產生索引鍵。
			ResultSet rsKey = pstmt.getGeneratedKeys();
			System.out.println("rsKey = " + rsKey); // com.mysql.cj.jdbc.result.ResultSetImpl@199d585c
			
			/*
			System.out.println("key.getMetaData() = " + rsKey.getMetaData());
			 com.mysql.cj.jdbc.result.ResultSetMetaData@3b195b60 - Field level
			 information:
			 com.mysql.cj.result.Field@48b5abe7[dbName=null,tableName=,originalTableName=null,columnName=GENERATED_KEY,originalColumnName=null,mysqlType=-1(FIELD_TYPE_BIGINT
			 UNSIGNED),sqlType=-5,flags= UNSIGNED, charsetIndex=0, charsetName=US-ASCII]
			*/

			if (rsKey.next()) {
				key = rsKey.getInt(1); // 顧客剛寫完送出的評論的review_id(自增主鍵)的編號
				System.out.println("rsKey.getInt(1) = " + rsKey.getInt(1));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return key; // 顧客剛寫完送出的評論的review_id(自增主鍵)的編號

	}

	// 測試修改
	public void update(MemberCommentBean MemberCommentBean) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(
					"UPDATE member_comment SET order_detail_id=?, comment_content=?, rating=?, product_id=? WHERE review_id=?");

			pstmt.setInt(1, MemberCommentBean.getOrder_detail_id());
			pstmt.setString(2, MemberCommentBean.getComment_content());
			pstmt.setInt(3, MemberCommentBean.getRating());
			pstmt.setInt(4, MemberCommentBean.getProduct_id());
//			pstmt.setInt(5, MemberCommentBean.getComment_status());
			pstmt.setInt(6, MemberCommentBean.getReview_id());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	// 測試刪除
	public void delete(Integer review_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement("DELETE FROM member_comment WHERE review_id=?");

			pstmt.setInt(1, review_id);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	// 測試查主鍵
	public MemberCommentBean findByPrimaryKey(Integer review_id) {

		MemberCommentBean MemberCommentBean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement("SELECT * FROM member_comment where review_id=?");

			pstmt.setInt(1, review_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberCommentBean = new MemberCommentBean();
				MemberCommentBean.setReview_id(rs.getInt("review_id"));
				MemberCommentBean.setOrder_detail_id(rs.getInt("order_detail_id"));
				MemberCommentBean.setComment_content(rs.getString("comment_content"));
				MemberCommentBean.setRating(rs.getInt("rating"));
				MemberCommentBean.setComment_time(rs.getTimestamp("comment_time"));
				MemberCommentBean.setProduct_id(rs.getInt("product_id"));
				MemberCommentBean.setComment_status(rs.getInt("comment_status"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return MemberCommentBean;
	}

	// 測試查全部
	public List<MemberCommentBean> getAll() {

		List<MemberCommentBean> list = new ArrayList<MemberCommentBean>();
		MemberCommentBean MemberCommentBean = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement("SELECT * FROM member_comment");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberCommentBean = new MemberCommentBean();
				MemberCommentBean.setReview_id(rs.getInt("review_id"));
				MemberCommentBean.setOrder_detail_id(rs.getInt("order_detail_id"));
				MemberCommentBean.setComment_content(rs.getString("comment_content"));
				MemberCommentBean.setRating(rs.getInt("rating"));
				MemberCommentBean.setComment_time(rs.getTimestamp("comment_time"));
				MemberCommentBean.setProduct_id(rs.getInt("product_id"));
				MemberCommentBean.setComment_status(rs.getInt("comment_status"));
				list.add(MemberCommentBean);
			}

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
	public Set<ReviewImageUploadBean> getReviewImageUploadsByReviewId(Integer review_id) {

		Set<ReviewImageUploadBean> set = new LinkedHashSet<ReviewImageUploadBean>();
		ReviewImageUploadBean reviewImageUploadBean = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(GetReviewImageUploadByReviewId);
			pstmt.setInt(1, review_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reviewImageUploadBean = new ReviewImageUploadBean();
				reviewImageUploadBean.setReview_image_id(rs.getInt("review_image_id"));
				reviewImageUploadBean.setReview_image(rs.getBytes("review_image"));
				reviewImageUploadBean.setReview_id(rs.getInt("review_id"));
				set.add(reviewImageUploadBean);
			}

			// Handle any SQL errors
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
		return set;
	}

	@Override
	public MemberCommentBean findReviewIdByOrderDetailId(Integer order_detail_id) {
		MemberCommentBean MemberCommentBean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement("select mc.review_id from member_comment mc where order_detail_id = ?;");

			pstmt.setInt(1, order_detail_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberCommentBean = new MemberCommentBean();
				MemberCommentBean.setReview_id(rs.getInt("review_id"));
				MemberCommentBean.setOrder_detail_id(rs.getInt("order_detail_id"));
				MemberCommentBean.setComment_content(rs.getString("comment_content"));
				MemberCommentBean.setRating(rs.getInt("rating"));
				MemberCommentBean.setComment_time(rs.getTimestamp("comment_time"));
				MemberCommentBean.setProduct_id(rs.getInt("product_id"));
				MemberCommentBean.setComment_status(rs.getInt("comment_status"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return MemberCommentBean;
	}

//	public static void main(String[] args) {
//
//		MemberCommentDao dao = new MemberCommentDao();
//
//		// 新增
//		MemberCommentBean memberCommentBean_insert = new MemberCommentBean();
//		memberCommentBean_insert.setOrder_detail_id(2);
//		memberCommentBean_insert.setComment_content("好吃好吃");
//		memberCommentBean_insert.setRating(4);
//		memberCommentBean_insert.setProduct_id(1);
//		memberCommentBean_insert.setComment_status(1);
//		dao.insert(memberCommentBean_insert);
//		System.out.println("----------有跑新增----------");
//
//		// 修改
//		MemberCommentBean memberCommentBean_update = new MemberCommentBean();
//		memberCommentBean_update.setOrder_detail_id(1);
//		memberCommentBean_update.setComment_content("好吃好");
//		memberCommentBean_update.setRating(5);
//		memberCommentBean_update.setProduct_id(1);
//		memberCommentBean_update.setComment_status(1);
//		memberCommentBean_update.setReview_id(3);
//		dao.update(memberCommentBean_update);
//		System.out.println("----------有跑修改----------");
//
//		// 刪除
//		dao.delete(6);
//		System.out.println("----------有跑刪除----------");
//
//		// 查詢主鍵
//		MemberCommentBean memeber_commentBean_findByPrimaryKey = dao.findByPK(2);
//		System.out.println("review_id: "+memeber_commentBean_findByPK.getReview_id() + ",");
//		System.out.println("order_detail_id: "+memeber_commentBean_findByPK.getOrder_detail_id() + ",");
//		System.out.println("comment_content: "+memeber_commentBean_findByPK.getComment_content() + ",");
//		System.out.println("rating: "+memeber_commentBean_findByPK.getRating() + ",");
//		System.out.println("current_time: "+memeber_commentBean_findByPK.getComment_time() + ",");
//		System.out.println("product_id: "+memeber_commentBean_findByPK.getProduct_id() + ",");
//		System.out.println("comment_status: "+memeber_commentBean_findByPK.getComment_status() + ",");
//		System.out.println("----------有跑查主鍵----------");
//
//		// 查全部
//		List<MemberCommentBean> list = dao.getAll();
//		for(MemberCommentBean mcb : list) {
//			System.out.println("review_id: "+mcb.getReview_id() + ",");
//			System.out.println("order_detail_id: "+mcb.getOrder_detail_id() + ",");
//			System.out.println("comment_content: "+mcb.getComment_content() +",");
//			System.out.println("rating: "+mcb.getRating() +",");
//			System.out.println("comment_time: "+mcb.getComment_time() +",");
//			System.out.println("product_id: "+mcb.getProduct_id() +",");
//			System.out.println("comment_status: "+mcb.getComment_status() +",");
//			System.out.println();
//		}
//		System.out.println("----------有跑查全部----------");
//	}

}
