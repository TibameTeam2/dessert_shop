package com.notice.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.live_support.model.LiveSupportBean;
import com.live_support.model.LiveSupportDAO;
import com.util.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;

public class NoticeDaoImpl implements NoticeDao {

	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;

	private static final String INSERT_STMT = "INSERT INTO sweet.notice (notice_type,notice_content,read_status,member_account, notice_dispatcher) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE sweet.notice set notice_type=?,notice_content=?, read_status=?, member_account=? where notice_id = ?";
	private static final String DELETE = "DELETE FROM sweet.notice where notice_id = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM sweet.notice where notice_id = ?";
	private static final String GET_ALL_STMT = "SELECT * from sweet.notice "; // ORDER BY notice_time DESC,notice_id
																				// DESC

	private static final String GET_TYPE_STMT = "SELECT * FROM sweet.notice where notice_type = ?";
	private static final String GET_MEMBER_STMT = "SELECT * FROM sweet.notice where member_account = ?";

	/************************************ 新增    ************************************/
	@Override
	public void insert(NoticeBean noticeBean) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, noticeBean.getNotice_type());
			pstmt.setString(2, noticeBean.getNotice_content());
			pstmt.setInt(3, noticeBean.getRead_status());
			pstmt.setString(4, noticeBean.getMember_account());
			pstmt.setString(5, noticeBean.getNotice_dispatcher());

			int count = pstmt.executeUpdate();
			System.out.println(count);

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	/************************************ 修改  ************************************/
	@Override
	public void update(NoticeBean noticeBean) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, noticeBean.getNotice_type());
			pstmt.setString(2, noticeBean.getNotice_content());
			pstmt.setInt(3, noticeBean.getRead_status());
			pstmt.setString(4, noticeBean.getMember_account());
			pstmt.setInt(5, noticeBean.getNotice_id());

			// 更新筆數
			int count = pstmt.executeUpdate();
			System.out.println(count);

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	/************************************ 刪除  ************************************/
	@Override
	public void delete(Integer notice_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, notice_id);

			// 更新筆數
			int count = pstmt.executeUpdate();
			System.out.println(count);

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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

	/************************************ 查一筆(PK) ************************************/
	@Override
	public NoticeBean findByPrimaryKey(Integer notice_id) {

		NoticeBean noticeBean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, notice_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				noticeBean = new NoticeBean();

				noticeBean.setNotice_id(rs.getInt("notice_id"));
				noticeBean.setNotice_type(rs.getInt("notice_type"));
				noticeBean.setNotice_content(rs.getString("notice_content"));
				noticeBean.setNotice_time(rs.getTimestamp("notice_time"));
				noticeBean.setRead_status(rs.getInt("read_status"));
				noticeBean.setMember_account(rs.getString("member_account"));
				noticeBean.setNotice_dispatcher(rs.getString("notice_dispatcher"));
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
		return noticeBean;
	}

	/************************************ 查全部 ************************************/
	@Override
	public List<NoticeBean> getAll() {
		List<NoticeBean> list = new ArrayList<NoticeBean>();
		NoticeBean noticeBean = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				noticeBean = new NoticeBean();

				noticeBean.setNotice_id(rs.getInt("notice_id"));
				noticeBean.setNotice_type(rs.getInt("notice_type"));
				noticeBean.setNotice_content(rs.getString("notice_content"));
				noticeBean.setNotice_time(rs.getTimestamp("notice_time"));
				noticeBean.setRead_status(rs.getInt("read_status"));
				noticeBean.setMember_account(rs.getString("member_account"));
				noticeBean.setNotice_dispatcher(rs.getString("notice_dispatcher"));

				list.add(noticeBean);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	/************************************ 查類型  ************************************/
	@Override
	public List<NoticeBean> getType(Integer notice_type) {
		List<NoticeBean> list = new ArrayList<NoticeBean>();
		NoticeBean noticeBean = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_TYPE_STMT);

			pstmt.setInt(1, notice_type);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				noticeBean = new NoticeBean();

				noticeBean.setNotice_id(rs.getInt("notice_id"));
				noticeBean.setNotice_type(rs.getInt("notice_type"));
				noticeBean.setNotice_content(rs.getString("notice_content"));
				noticeBean.setNotice_time(rs.getTimestamp("notice_time"));
				noticeBean.setRead_status(rs.getInt("read_status"));
				noticeBean.setMember_account(rs.getString("member_account"));
				noticeBean.setNotice_dispatcher(rs.getString("notice_dispatcher"));

				list.add(noticeBean);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	/************************************ 查會員  ************************************/
	@Override
	public List<NoticeBean> getMember(String member_account) {
		List<NoticeBean> list = new ArrayList<NoticeBean>();
		NoticeBean noticeBean = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MEMBER_STMT);

			pstmt.setString(1, member_account);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				noticeBean = new NoticeBean();

				noticeBean.setNotice_id(rs.getInt("notice_id"));
				noticeBean.setNotice_type(rs.getInt("notice_type"));
				noticeBean.setNotice_content(rs.getString("notice_content"));
				noticeBean.setNotice_time(rs.getTimestamp("notice_time"));
				noticeBean.setRead_status(rs.getInt("read_status"));
				noticeBean.setMember_account(rs.getString("member_account"));
				noticeBean.setNotice_dispatcher(rs.getString("notice_dispatcher"));

//				noticeBean.setMember_account(rs.getString("member_account"));
//				noticeBean.setNotice_content(rs.getString("notice_content"));

				list.add(noticeBean);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) {

		NoticeDaoImpl dao = new NoticeDaoImpl();

		// 新增
//		NoticeBean noticeBean = new NoticeBean();
//		noticeBean.setNotice_type(3);
//		noticeBean.setNotice_content("QQQQQQQQQQQQQQQQQ");
//		noticeBean.setRead_status(0);
//		noticeBean.setMember_account("jason");
//		dao.insert(noticeBean);

		// 修改
//		NoticeBean noticeBean = new NoticeBean();
//		noticeBean.setNotice_id(1);
//		noticeBean.setNotice_type(2);
//		noticeBean.setNotice_content("貴賓您好：您有訂購岩融巧克力蛋糕乙客，請準時來提取，謝謝!");
//		noticeBean.setRead_status(1);
//		noticeBean.setMember_account("tom");
//      dao.update(noticeBean);

		// 刪除
//	    dao.delete(4);

		// 查詢(PK)
//		NoticeBean noticeBean = dao.findByPrimaryKey(3);
//		System.out.println(noticeBean);

		// 查詢(全部)
//        List<NoticeBean> list = dao.getAll();
//        for (NoticeBean NoticeBean : list) {
//            System.out.println(NoticeBean);
//		}

		// 查詢(類型)
//      List<NoticeBean> list = dao.getType(1);
//      for (NoticeBean NoticeBean : list) {
//          System.out.println(NoticeBean);
//		}

		// 查詢(會員)
//		List<NoticeBean> list = dao.getMember("amy");
//		for (NoticeBean NoticeBean : list) {
//			System.out.println(NoticeBean);
		}

	}
