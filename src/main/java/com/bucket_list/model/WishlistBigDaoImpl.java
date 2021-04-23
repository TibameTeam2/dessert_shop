package com.bucket_list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;

import com.util.JDBCUtil;

public class WishlistBigDaoImpl implements WishlistBigDao {

	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;

	public WishlistBigBean getImage(Integer product_id) {

		WishlistBigBean wishlistBigBean = new WishlistBigBean();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(
					"select bl.product_id, pi.image_id, pi.product_image from bucket_list bl join product_image pi on bl.product_id = pi.product_id where bl.product_id = ? group by bl.product_id;");

			pstmt.setInt(1, product_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				wishlistBigBean.setProduct_id(rs.getInt("product_id"));
				wishlistBigBean.setImage_id(rs.getInt("image_id"));
				wishlistBigBean.setProduct_image(
						"/product_jsp/product.do?action=getProductImage&id=" + wishlistBigBean.getImage_id());
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
		return wishlistBigBean;
	}
	
	public static void main(String[] args) {
		WishlistBigDaoImpl dao = new WishlistBigDaoImpl();
		
		WishlistBigBean w = dao.getImage(1);
		System.out.println("product_id = " + w.getProduct_id());
		System.out.println("image_id = " + w.getImage_id());
		System.out.println("product_image = " + w.getProduct_image());
	}
}
