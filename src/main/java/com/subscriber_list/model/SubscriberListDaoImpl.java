package com.subscriber_list.model;

import com.live_support.model.LiveSupportBean;
import com.live_support.model.LiveSupportDAO;
import com.util.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriberListDaoImpl implements SubscriberListDao {

	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;

	private static final String INSERT_STMT = "INSERT INTO sweet.subscriber_list (subscriber_email,subscriber_status,member_account) VALUES (?, ?, ?)";
	private static final String UPDATE = "UPDATE subscriber_list set subscriber_email=?, subscriber_status=?, member_account=? where subscriber_id = ?";
	private static final String DELETE = "DELETE FROM sweet.subscriber_list where subscriber_id = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM subscriber_list where subscriber_id = ?";
	private static final String GET_ALL_STMT = "select * from sweet.subscriber_list";

	// Eail
	private static final String GET_EMAIL = "SELECT * FROM subscriber_list where subscriber_email = ?";

	/******************************** 新增 ********************************/
	@Override
	public void insert(SubscriberListBean SubscriberListBean) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, SubscriberListBean.getSubscriber_email());
			pstmt.setInt(2, SubscriberListBean.getSubscriber_status());
			pstmt.setString(3, SubscriberListBean.getMember_account());

			// 更新筆數
			int count = pstmt.executeUpdate();
			System.out.println(count);

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

	/******************************** 修改 ********************************/
	@Override
	public void update(SubscriberListBean SubscriberListBean) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, SubscriberListBean.getSubscriber_email());
			pstmt.setInt(2, SubscriberListBean.getSubscriber_status());
			pstmt.setString(3, SubscriberListBean.getMember_account());
			pstmt.setInt(4, SubscriberListBean.getSubscriber_id());

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

	/******************************** 刪除 ********************************/
	@Override
	public void delete(Integer subscriber_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, subscriber_id);

			// 更新筆數
			int count = pstmt.executeUpdate();
			System.out.println(count);

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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

	/******************************** 查一筆(PK) ********************************/
	@Override
	public SubscriberListBean findByPrimaryKey(Integer subscriber_id) {

		SubscriberListBean SubscriberListBean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, subscriber_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				SubscriberListBean = new SubscriberListBean();

				SubscriberListBean.setSubscriber_id(rs.getInt("subscriber_id"));
				SubscriberListBean.setSubscriber_email(rs.getString("subscriber_email"));
				SubscriberListBean.setSubscriber_status(rs.getInt("subscriber_status"));
				SubscriberListBean.setSubscriber_date(rs.getTimestamp("subscriber_date"));
				SubscriberListBean.setMember_account(rs.getString("member_account"));

//	                System.out.println(SubscriberListBean);
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
		return SubscriberListBean;
	}

	/******************************** 查全部 ********************************/
	@Override
	public List<SubscriberListBean> getAll() {
		List<SubscriberListBean> list = new ArrayList<SubscriberListBean>();
		SubscriberListBean SubscriberListBean = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				SubscriberListBean = new SubscriberListBean();

				SubscriberListBean.setSubscriber_id(rs.getInt("subscriber_id"));
				SubscriberListBean.setSubscriber_email(rs.getString("subscriber_email"));
				SubscriberListBean.setSubscriber_status(rs.getInt("subscriber_status"));
				SubscriberListBean.setSubscriber_date(rs.getTimestamp("subscriber_date"));
				SubscriberListBean.setMember_account(rs.getString("member_account"));

				list.add(SubscriberListBean);
//	                System.out.println(SubscriberListBean);
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

	/******************************** 查Email ********************************/
	@Override
	public SubscriberListBean findByPrimaryEmail(String subscriber_email) {

		SubscriberListBean SubscriberListBean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_EMAIL);

			pstmt.setString(1, subscriber_email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				SubscriberListBean = new SubscriberListBean();
				SubscriberListBean.setSubscriber_email(rs.getString("subscriber_email"));

				System.out.println("findByPrimaryEmail" + SubscriberListBean);
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
		return SubscriberListBean;
	}

	public static void main(String[] args) {
		SubscriberListDaoImpl dao = new SubscriberListDaoImpl();

        // 新增
//		SubscriberListBean SubscriberListBean = new SubscriberListBean();
//		SubscriberListBean.setSubscriber_email("ablee123@gmail.com");
//		SubscriberListBean.setSubscriber_status(1);
//		SubscriberListBean.setMember_account("amy");
//		dao.insert(SubscriberListBean);

        // 修改
//		SubscriberListBean SubscriberListBean = new SubscriberListBean();
//		SubscriberListBean.setSubscriber_id(3);
//		SubscriberListBean.setSubscriber_email("amy123@gmail.com");
//		SubscriberListBean.setSubscriber_status(1);
//		SubscriberListBean.setMember_account("amy");
//        dao.update(SubscriberListBean);

        // 刪除
//       dao.delete(3);
//
        // 查詢
//		SubscriberListBean SubscriberListBean = dao.findByPrimaryKey(3);
//		System.out.println(SubscriberListBean);

		// 查詢
//		List<SubscriberListBean> list = dao.getAll();
//		for (SubscriberListBean SubscriberListBean : list) {
//			System.out.println(SubscriberListBean);
//		}
		
		// 查詢
//		SubscriberListBean SubscriberListBean=dao.findByPrimaryEmail("ffff@wwwww.fffffff");
//		System.out.println(SubscriberListBean);
	}
}
