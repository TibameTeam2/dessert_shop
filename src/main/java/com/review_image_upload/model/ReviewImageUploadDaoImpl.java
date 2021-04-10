package com.review_image_upload.model;

import com.util.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewImageUploadDaoImpl implements ReviewImageUploadDao{

	private static JdbcTemplate jdbcTemplate;
	private String DRIVER = JDBCUtil.driver;
	private String URL = JDBCUtil.url;
	private String USERID = JDBCUtil.user;
	private String PASSWORD = JDBCUtil.password;
	
	private static final String INSERT_STMT = "INSERT INTO review_image_upload(review_image, review_id) VALUES (?, ?)";

	private static final String UPDATE = "UPDATE review_image_upload SET review_image=?, review_id=? WHERE review_image_id=?";

	private static final String DELETE = "DELETE FROM review_image_upload WHERE review_image_id=?";

	private static final String GET_ONE_STMT = "SELECT * FROM review_image_upload WHERE review_image_id=?";

	private static final String GET_ALL_STMT = "SELECT * FROM review_image_upload";
	

	// insert
	public void insert(ReviewImageUploadBean ReviewImageUploadBean) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setBytes(1, ReviewImageUploadBean.getReview_image());
			pstmt.setInt(2, ReviewImageUploadBean.getReview_id());
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

	// update
	public void update(ReviewImageUploadBean ReviewImageUploadBean) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setBytes(1, ReviewImageUploadBean.getReview_image());
			pstmt.setInt(2, ReviewImageUploadBean.getReview_id());
			pstmt.setInt(3, ReviewImageUploadBean.getReview_image_id());

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
	public void delete(Integer review_image_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, review_image_id);
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
	public ReviewImageUploadBean findByPrimaryKey(Integer review_image_id) {

		ReviewImageUploadBean ReviewImageUploadBean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, review_image_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReviewImageUploadBean = new ReviewImageUploadBean();
				ReviewImageUploadBean.setReview_image_id(rs.getInt("review_image_id"));
				ReviewImageUploadBean.setReview_image(rs.getBytes("review_image"));
				ReviewImageUploadBean.setReview_id(rs.getInt("review_id"));
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
		return ReviewImageUploadBean;
	}

	
	// getAll
	public List<ReviewImageUploadBean> getAll() {

		List<ReviewImageUploadBean> list = new ArrayList<ReviewImageUploadBean>();
		ReviewImageUploadBean ReviewImageUploadBean = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReviewImageUploadBean = new ReviewImageUploadBean();
				ReviewImageUploadBean.setReview_image_id(rs.getInt("review_image_id"));
				ReviewImageUploadBean.setReview_image(rs.getBytes("review_image"));
				ReviewImageUploadBean.setReview_id(rs.getInt("review_id"));
				list.add(ReviewImageUploadBean);
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
	public static void main(String[] args) throws IOException {

		ReviewImageUploadDao dao = new ReviewImageUploadDaoImpl();
		// 新增
		ReviewImageUploadBean riuInsert = new ReviewImageUploadBean();
		byte[] pic3 = getPictureByteArray("C:\\project\\images\\review_image_upload\\cake1.jpg");
		riuInsert.setReview_image(pic3);
		riuInsert.setReview_id(3);
		dao.insert(riuInsert);
		System.out.println("----------有跑新增----------");

		// 修改
		ReviewImageUploadBean riuUpdate = new ReviewImageUploadBean();
		byte[] pic2 = getPictureByteArray("C:\\project\\images\\review_image_upload\\cake2.jpg");
		riuUpdate.setReview_image(pic2);
		riuUpdate.setReview_id(1);
		riuUpdate.setReview_image_id(1);
		dao.update(riuUpdate);
		System.out.println("----------有跑修改----------");

		// 刪除
		dao.delete(5);
		System.out.println("----------有跑刪除----------");

		// 查主鍵
		ReviewImageUploadBean riuFindByPK = dao.findByPK(3);
		System.out.println(riuFindByPK.getReview_image_id() + ",");
		System.out.println(riuFindByPK.getReview_image() + ",");
		System.out.println(riuFindByPK.getReview_id() + ",");
		System.out.println("----------有跑查主鍵----------");

		// 查全部
		List<ReviewImageUploadBean> list = dao.getAll();
		for (ReviewImageUploadBean riu : list) {
			System.out.println("review_image_id: " + riu.getReview_image_id() + ",");
			System.out.println("review_image: " + riu.getReview_image() + ",");
			System.out.println("review_id: " + riu.getReview_id() + ",");
			System.out.println();
		}
		System.out.println("----------有跑查全部----------");
	}
*/

	// 使用byte[]方式
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

}
