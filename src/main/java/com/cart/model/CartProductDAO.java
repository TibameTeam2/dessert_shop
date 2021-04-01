package com.cart.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.util.JDBCUtil;

public class CartProductDAO {
	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;
	
	public List<CartProductBean> selectByMemberAccount(String member_account) {
		String SELECT_ALL =
			"SELECT cart.product_id, product_quantity, product_name, product_price FROM sweet.cart left join product on product.product_id = cart.product_id where member_account = ?;";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<CartProductBean> list_CartProductBean = new ArrayList<CartProductBean>();
		CartProductBean cpBean = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, member_account);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				cpBean = new CartProductBean();
				cpBean.setProduct_id(rs.getInt("product_id"));
				cpBean.setProduct_quantity(rs.getInt("product_quantity"));
				cpBean.setProduct_name(rs.getString("product_name"));
				cpBean.setProduct_price(rs.getInt("product_price"));
				list_CartProductBean.add(cpBean);
			}
			
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		
		return list_CartProductBean;
		
	}

}
