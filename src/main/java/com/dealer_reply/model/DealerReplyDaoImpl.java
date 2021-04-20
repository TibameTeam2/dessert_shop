package com.dealer_reply.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.member_comment.model.MemberCommentBean;
import com.util.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;

public class DealerReplyDaoImpl implements DealerReplyDao {

	private static JdbcTemplate jdbcTemplate;
	private String DRIVER = JDBCUtil.driver;
	private String URL = JDBCUtil.url;
	private String USERID = JDBCUtil.user;
	private String PASSWORD = JDBCUtil.password;

	// insert
	public int insert(DealerReplyBean DealerReplyBean, MemberCommentBean memberCommentBean) {

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		int key = 0;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(
					"INSERT INTO dealer_reply (review_id, reply_content, employee_account) VALUES(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, DealerReplyBean.getReview_id());
			pstmt.setString(2, DealerReplyBean.getReply_content());
			pstmt.setString(3, DealerReplyBean.getEmployee_account());
			pstmt.executeUpdate();

			// getGeneratedKeys() : 擷取執行這個 SQLServerStatement 物件最後所建立的任何自動產生索引鍵。
			ResultSet rsKey = pstmt.getGeneratedKeys();
			System.out.println("rsKey = " + rsKey); 
			
			/*
			 * System.out.println("key.getMetaData() = " + rsKey.getMetaData());
			 * com.mysql.cj.jdbc.result.ResultSetMetaData@3b195b60 - Field level
			 * information:
			 * com.mysql.cj.result.Field@48b5abe7[dbName=null,tableName=,originalTableName=
			 * null,columnName=GENERATED_KEY,originalColumnName=null,mysqlType=-1(
			 * FIELD_TYPE_BIGINT UNSIGNED),sqlType=-5,flags= UNSIGNED, charsetIndex=0,
			 * charsetName=US-ASCII]
			 */

			if (rsKey.next()) {
				key = rsKey.getInt(1); // 業者剛寫完送出的回覆的reply_id(自增主鍵)的編號
				System.out.println("rsKey.getInt(1) = " + rsKey.getInt(1));
			}
			
			pstmt1 = con.prepareStatement("update member_comment mc set comment_status = 1 where mc.review_id = ?;");
			pstmt1.setInt(1, memberCommentBean.getReview_id());
			pstmt1.executeUpdate();

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
		return key; // 業者剛寫完送出的回覆的reply_id(自增主鍵)的編號

	}

	// update
	public void update(DealerReplyBean DealerReplyBean) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement("UPDATE dealer_reply SET reply_content=?, reply_time=?, employee_account=? WHERE review_id=?");

			pstmt.setString(1, DealerReplyBean.getReply_content());
			pstmt.setTimestamp(2, DealerReplyBean.getReply_time());
			pstmt.setString(3, DealerReplyBean.getEmployee_account());
			pstmt.setInt(4, DealerReplyBean.getReview_id());

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

	// delete
	public void delete(Integer reply_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement("DELETE FROM dealer_reply WHERE reply_id=?");

			pstmt.setInt(1, reply_id);

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

	// findByPrimaryKey
	public DealerReplyBean findByPrimaryKey(Integer reply_id) {

		DealerReplyBean DealerReplyBean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement("SELECT * FROM  dealer_reply WHERE reply_id=?");

			pstmt.setInt(1, reply_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				DealerReplyBean = new DealerReplyBean();
				DealerReplyBean.setReply_id(rs.getInt("reply_id"));
				DealerReplyBean.setReview_id(rs.getInt("review_id"));
				DealerReplyBean.setReply_content(rs.getString("reply_content"));
				DealerReplyBean.setReply_time(rs.getTimestamp("reply_time"));
				DealerReplyBean.setEmployee_account(rs.getString("employee_account"));
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
		return DealerReplyBean;
	}

	// test getAll
	public List<DealerReplyBean> getAll() {

		List<DealerReplyBean> list = new ArrayList<DealerReplyBean>();
		DealerReplyBean DealerReplyBean = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement("SELECT * FROM dealer_reply");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DealerReplyBean = new DealerReplyBean();
				DealerReplyBean.setReply_id(rs.getInt("reply_id"));
				DealerReplyBean.setReview_id(rs.getInt("review_id"));
				DealerReplyBean.setReply_content(rs.getString("reply_content"));
				DealerReplyBean.setReply_time(rs.getTimestamp("reply_time"));
				DealerReplyBean.setEmployee_account(rs.getString("employee_account"));
				list.add(DealerReplyBean);
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
		return list;
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * DealerReplyBeanDaoImpl dao = new DealerReplyBeanDaoImpl();
	 * 
	 * // 新增 DealerReplyBean drInsert = new DealerReplyBean();
	 * drInsert.setReview_id(3); drInsert.setReply_content("新增回覆內容成功");
	 * drInsert.setEmployee_account("員工四號"); dao.insert(drInsert);
	 * System.out.println("----------有跑新增----------");
	 * 
	 * //修改 DealerReplyBean drUpdate = new DealerReplyBean();
	 * drUpdate.setReply_content("謝謝您的惠顧2"); drUpdate.setReply_id(3);
	 * dao.update(drUpdate); System.out.println("----------有跑修改----------");
	 * 
	 * //test delete dao.delete(6); System.out.println("----------有跑刪除----------");
	 * 
	 * //查詢主鍵 DealerReplyBean drFindByPK = dao.findByPrimaryKey(1);
	 * System.out.println("reply_id: "+drFindByPK.getReply_id() + ",");
	 * System.out.println("review_id: "+drFindByPK.getReview_id() + ",");
	 * System.out.println("reply_content: "+drFindByPK.getReply_content() + ",");
	 * System.out.println("reply_time: "+drFindByPK.getReply_time() + ",");
	 * System.out.println("employee_account: "+drFindByPK.getEmployee_account() +
	 * ","); System.out.println("----------有跑查主鍵----------");
	 * 
	 * //查詢全部 List<DealerReplyBean> list = dao.getAll(); for (DealerReplyBean dr :
	 * list) { System.out.println("reply_id: " + dr.getReply_id() + ",");
	 * System.out.println("revire_id: " + dr.getReview_id() + ",");
	 * System.out.println("reply_content: " + dr.getReply_content() + ",");
	 * System.out.println("reply_time: " + dr.getReply_time() + ",");
	 * System.out.println("employee_account: " + dr.getEmployee_account() + ",");
	 * System.out.println(); } System.out.println("----------有跑查全部----------"); }
	 */

}
