package com.cart.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.cart.model.CartBean;
import com.util.JDBCUtil;

public class CartDAO {
	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;
	
	String INSERT;
	String UPDATE;
	String DELETE;
	String SELECT_ALL;
	String SELECT_PK;
	

	public void insert(CartBean cartBean) {
		INSERT =
			"INSERT INTO cart (member_account, product_id, product_quantity) VALUES (?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		

		
		try {
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(INSERT);
		
		pstmt.setString(1, cartBean.getMember_account());
		pstmt.setInt(2, cartBean.getProduct_id());
		pstmt.setInt(3, cartBean.getProduct_quantity());
		
		int a = pstmt.executeUpdate();
		System.out.println(a);
		
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

	}
	

	public void update(CartBean cartBean) {
		UPDATE =
			"UPDATE cart set member_account = ?, product_id = ?, product_quantity = ? where cart_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		

		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, cartBean.getMember_account());
			pstmt.setInt(2, cartBean.getProduct_id());
			pstmt.setInt(3, cartBean.getProduct_quantity());
			pstmt.setInt(4, cartBean.getCart_id());
			
			pstmt.executeUpdate();
			
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
		
	}
	

	public void delete(Integer cart_id) {
		DELETE =
			"DELETE FROM cart where cart_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, cart_id);
			
			pstmt.executeUpdate();
			
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
		
	}
	

	public CartBean findByPrimaryKey(Integer cart_id) {
		SELECT_PK =
			"SELECT * FROM cart where cart_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		CartBean cartBean = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_PK);
			
			pstmt.setInt(1, cart_id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				cartBean = new CartBean();
				cartBean.setCart_id(rs.getInt("cart_id"));
				cartBean.setMember_account(rs.getString("member_account"));
				cartBean.setProduct_id(rs.getInt("product_id"));
				cartBean.setProduct_quantity(rs.getInt("product_quantity"));
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
		
		return cartBean;
		
	}
	

	public List<CartBean> getAll() {
		SELECT_ALL =
			"SELECT * FROM cart";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<CartBean> list_CartBean = new ArrayList<CartBean>();
		CartBean cartBean = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ALL);
			
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				cartBean = new CartBean();
				cartBean.setCart_id(rs.getInt("cart_id"));
				cartBean.setMember_account(rs.getString("member_account"));
				cartBean.setProduct_id(rs.getInt("product_id"));
				cartBean.setProduct_quantity(rs.getInt("product_quantity"));
				list_CartBean.add(cartBean);
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
		
		return list_CartBean;
		
	}
	
	
	
	public static void main(String[] args) {
		CartDAO dao = new CartDAO();
		
		//insert
		//設定資料
//		CartBean cartBean = new CartBean();
//		cartBean.setMember_account("tom");
//		cartBean.setProduct_id(3);
//		cartBean.setProduct_quantity(870);
//		dao.insert(cartBean);
		
		//update
		//設定資料
//		CartBean cartBean = new CartBean();
//		cartBean.setCart_id(2);
//		cartBean.setMember_account("amy");
//		cartBean.setProduct_id(1);
//		cartBean.setProduct_quantity(588);
//		dao.update(cartBean);
		
		//delete
//		dao.delete(2);
		
		//select_pk
//		CartBean cartBean = dao.findByPrimaryKey(1);
//		System.out.print(cartBean);
		
		//select_all
		List<CartBean> list = dao.getAll();
		for (CartBean cartBean : list) {
			System.out.println(cartBean);
		}
		
		
	}
	
	
}
