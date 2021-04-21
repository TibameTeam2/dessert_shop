package com.bucket_list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;

import com.util.JDBCUtil;

public class BucketListDaoImpl implements BucketListDao {

	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;

	// 計數???
	@Override
	public Integer insert(BucketListBean bucketListBean) {

		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(
					"INSERT INTO bucket_list (member_account, product_id, bucket_list_status) VALUES (?, ?, ?)");

			pstmt.setString(1, bucketListBean.getMember_account());
			pstmt.setInt(2, bucketListBean.getProduct_id());
			pstmt.setInt(3, bucketListBean.getBucket_list_status());

			count++;
			count = pstmt.executeUpdate();
			System.out.println("count = " + count);

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
		return count;
	}

	@Override
	public void update(BucketListBean bucketListBean) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(
					"UPDATE sweet.bucket_list set bucket_list_status = ? where member_account = ? and product_id = ?");

			pstmt.setInt(1, bucketListBean.getBucket_list_status());
			pstmt.setString(2, bucketListBean.getMember_account());
			pstmt.setInt(3, bucketListBean.getProduct_id());
			pstmt.executeUpdate();

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

	@Override
	public void delete(String member_account, Integer product_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement("Delete from sweet.bucket_list where member_account = ? and product_id = ?");

			pstmt.setString(1, member_account);
			pstmt.setInt(2, product_id);
			pstmt.executeUpdate();

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

	@Override
	public BucketListBean findByPrimaryKey(String member_account, Integer product_id) {

		BucketListBean bucketListBean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement("SELECT * FROM sweet.bucket_list where member_account = ? and product_id = ?");

			pstmt.setString(1, member_account);
			pstmt.setInt(2, product_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				bucketListBean = new BucketListBean();
				bucketListBean.setBucket_list_id(rs.getInt("bucket_list_id"));
				bucketListBean.setMember_account(rs.getString("member_account"));
				bucketListBean.setProduct_id(rs.getInt("product_id"));
				bucketListBean.setBucket_list_status(rs.getInt("bucket_list_status"));
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
		return bucketListBean;
	}

	@Override
	public List<BucketListBean> getAll() {

		List<BucketListBean> list = new ArrayList<BucketListBean>();
		BucketListBean bucketListBean = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement("SELECT * FROM sweet.bucket_list");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bucketListBean = new BucketListBean();
				bucketListBean.setBucket_list_id(rs.getInt("bucket_list_id"));
				bucketListBean.setMember_account(rs.getString("member_account"));
				bucketListBean.setProduct_id(rs.getInt("product_id"));
				bucketListBean.setBucket_list_status(rs.getInt("bucket_list_status"));
				list.add(bucketListBean);

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
	public void changeBucketListStatusToZero(String member_account, Integer product_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(
					"UPDATE sweet.bucket_list set bucket_list_status = 0 where member_account = ? and product_id = ?");

			pstmt.setString(1, member_account);
			pstmt.setInt(2, product_id);
			pstmt.executeUpdate();

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

	// 傳入member_account要顯示該顧客的收藏清單, 商品不重複
	@Override
	public List<BucketListBean> getMyBucketList(String member_account) {

		List<BucketListBean> list = new ArrayList<BucketListBean>();
		BucketListBean bucketListBean = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(
					"select bucket_list_id, member_account, product_id, bucket_list_status from bucket_list where member_account = ? and bucket_list_status = 1;");
			pstmt.setString(1, member_account);
			rs = pstmt.executeQuery();
			System.out.println(rs); //com.mysql.cj.jdbc.result.ResultSetImpl@5762806e

			while (rs.next()) {
				bucketListBean = new BucketListBean();
				bucketListBean.setBucket_list_id(rs.getInt("bucket_list_id"));
				bucketListBean.setMember_account(rs.getString("member_account"));
				bucketListBean.setProduct_id(rs.getInt("product_id"));
				bucketListBean.setBucket_list_status(rs.getInt("bucket_list_status"));
				list.add(bucketListBean);
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

	public static void main(String[] args) {

		BucketListDaoImpl dao = new BucketListDaoImpl();

//		不知道怎麼寫
//		Integer b = dao.insert("amy", 4);
//		System.out.println(count);
//		System.out.println("----------有跑新增----------");

//		BucketListBean c = new BucketListBean();
//		c.setMember_account("tom");
//		c.setProduct_id(2);
//		c.setBucket_list_status(0);
//		dao.update(c);
//		System.out.println("----------有跑修改----------");

//		dao.delete("amy", 1);
//		System.out.println("----------有跑刪除----------");

//		BucketListBean d = dao.findByPrimaryKey("jason", 1);
//		System.out.println("bucket_list_id = " + d.getBucket_list_id());
//		System.out.println("member_account = " + d.getMember_account());
//		System.out.println("product_id = " + d.getProduct_id());
//		System.out.println("bucket_list_status = " + d.getBucket_list_status());
//		System.out.println("----------有跑查主鍵----------");

//		List<BucketListBean> list = dao.getAll();
//		for(BucketListBean e : list) {
//			System.out.println("bucket_list_id = " + e.getBucket_list_id());
//			System.out.println("member_account = " + e.getMember_account());
//			System.out.println("product_id = " + e.getProduct_id());
//			System.out.println("bucket_list_status = " + e.getBucket_list_status());
//			System.out.println("--------------------");
//		}
//		System.out.println("----------有跑查全部----------");

		List<BucketListBean> list = dao.getMyBucketList("amy");
		for(BucketListBean e : list) {
			System.out.println("bucket_list_id = " + e.getBucket_list_id());
			System.out.println("member_account = " + e.getMember_account());
			System.out.println("product_id = " + e.getProduct_id());
			System.out.println("bucket_list_status = " + e.getBucket_list_status());
			System.out.println("--------------------");
		}
		System.out.println("----------有跑getMyBucketList----------");

	}

}
