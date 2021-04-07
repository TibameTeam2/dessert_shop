package com.cart.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.coupon.model.CouponBean;
import com.coupon_code.model.CouponCodeBean;
import com.util.JDBCUtil;

public class CartProductDAO {
	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;
	
	//拿cart_id, cart.product_id, product_quantity, product_name, product_price
	public List<CartProductBean> selectByMemberAccount(String member_account) {
		String SELECT_ALL =
			"SELECT cart_id, cart.product_id, product_quantity, product_name, product_price FROM sweet.cart left join product on product.product_id = cart.product_id where member_account = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<CartProductBean> list = new ArrayList<CartProductBean>();
		CartProductBean cpBean = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, member_account);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				cpBean = new CartProductBean();
				cpBean.setCart_id(rs.getInt("cart_id"));
				cpBean.setProduct_id(rs.getInt("product_id"));
				cpBean.setProduct_quantity(rs.getInt("product_quantity"));
				cpBean.setProduct_name(rs.getString("product_name"));
				cpBean.setProduct_price(rs.getInt("product_price"));
				list.add(cpBean);
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
		
		return list;
		
	}
	
	
	//拿可使用的coupon
	public List<CouponBean> selectCouponByMemberAccount(String member_account) {

		Connection con = null;
		PreparedStatement pstmt = null;

		String SELECT_ALL = "SELECT * FROM sweet.coupon where member_account = ? and coupon_status = '0' and current_timestamp() < coupon_expire_date";

		CouponBean CB;
		ResultSet rs = null;

		List<CouponBean> list_couponBean = new ArrayList<CouponBean>();

		try {
			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, member_account);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				CB = new CouponBean();
				CB.setCoupon_id(rs.getInt("coupon_id"));
				CB.setMember_account(rs.getString("member_account"));
				CB.setCoupon_sending_time(rs.getTimestamp("coupon_sending_time"));
				CB.setCoupon_effective_date(rs.getTimestamp("coupon_effective_date"));
				CB.setCoupon_expire_date(rs.getTimestamp("coupon_expire_date"));
				CB.setCoupon_text_content(rs.getString("coupon_text_content"));
				CB.setCoupon_content(rs.getFloat("coupon_content"));
				CB.setDiscount_type(rs.getInt("discount_type"));
				CB.setCoupon_status(rs.getInt("coupon_status"));
				CB.setEmployee_account(rs.getString("employee_account"));
				CB.setCoupon_code_id(rs.getInt("coupon_code_id"));

				list_couponBean.add(CB);

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
		return list_couponBean;
	}
	
	
	//拿第一張圖片資料流
	public InputStream getFirstImageByProductId(Integer product_id) {
		String SELECT_FIRST =
				"SELECT product_image FROM sweet.product_image where product_id = ? LIMIT 1";
			Connection con = null;
			PreparedStatement pstmt = null;
			
			ResultSet rs = null;
			InputStream product_image = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(SELECT_FIRST);
				
				pstmt.setInt(1, product_id);
				
				rs = pstmt.executeQuery();
				

				if (rs.next()) {
					product_image = rs.getBinaryStream("product_image");
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
			
			
		return product_image;
		
	}
	
	
	//update購物車商品數量
	public void updateProductQuantity(Integer cart_id, Integer product_quantity) {
		String UPDATE =
				"UPDATE sweet.cart set product_quantity = ? where cart_id = ?";
			Connection con = null;
			PreparedStatement pstmt = null;
			

			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setInt(1, product_quantity);
				pstmt.setInt(2, cart_id);
				
				pstmt.executeUpdate();
				
				System.out.println(1);
				
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
	
	
	//delete商品
	public void delete(Integer cart_id) {
		String DELETE =
			"DELETE FROM sweet.cart where cart_id = ?";
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
	
	
	//select優惠碼
	public CouponCodeBean selectCouponCodeData(String coupon_code) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String SELECT_ID = "SELECT * FROM sweet.coupon_code WHERE coupon_code = ?";
		ResultSet rs = null;
		CouponCodeBean CCB = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ID);

			pstmt.setString(1, coupon_code);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CCB = new CouponCodeBean();
				CCB.setCoupon_code_id(rs.getInt("coupon_code_id"));
				CCB.setCoupon_code(rs.getString("coupon_code"));
				CCB.setCoupon_code_effective_date(rs.getTimestamp("coupon_code_effective_date"));
				CCB.setCoupon_code_expire_date(rs.getTimestamp("coupon_code_expire_date"));
				CCB.setcoupon_code_text_content(rs.getString("coupon_code_text_content"));
				CCB.setcoupon_code_content(rs.getFloat("coupon_code_content"));
				CCB.setDiscount_type(rs.getInt("discount_type"));
				CCB.setEmployee_account(rs.getString("employee_account"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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

		return CCB;
		
	}
	
	
	//insert優惠券
	public void insertCouponData(CouponBean CB) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		String INSERT = "INSERT INTO sweet.coupon(member_account,coupon_sending_time,"
				+ "coupon_effective_date,coupon_expire_date,coupon_text_content,coupon_content,"
				+ "discount_type,coupon_status,employee_account,coupon_code_id) VALUES(?,?,?,?,?,?,?,?,?,?)";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, CB.getMember_account());
			pstmt.setTimestamp(2, CB.getCoupon_sending_time());
			pstmt.setTimestamp(3, CB.getCoupon_effective_date());
			pstmt.setTimestamp(4, CB.getCoupon_expire_date());
			pstmt.setString(5, CB.getCoupon_text_content());
			pstmt.setFloat(6, CB.getCoupon_content());
			pstmt.setInt(7, CB.getDiscount_type());
			pstmt.setInt(8, CB.getCoupon_status());
			pstmt.setString(9, CB.getEmployee_account());
			pstmt.setInt(10, CB.getCoupon_code_id());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
	
	
	//select優惠券(對照用)
	public CouponBean selectCouponData(String member_account, Integer coupon_code_id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		String SELECT = "SELECT * FROM sweet.coupon WHERE member_account = ? and coupon_code_id = ?";
		ResultSet rs = null;
		CouponBean CB = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT);
			
			pstmt.setString(1, member_account);
			pstmt.setInt(2, coupon_code_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CB = new CouponBean();
				CB.setCoupon_id(rs.getInt("coupon_id"));
				CB.setMember_account(rs.getString("member_account"));
				CB.setCoupon_sending_time(rs.getTimestamp("coupon_sending_time"));
				CB.setCoupon_effective_date(rs.getTimestamp("coupon_effective_date"));
				CB.setCoupon_expire_date(rs.getTimestamp("coupon_expire_date"));
				CB.setCoupon_text_content(rs.getString("coupon_text_content"));
				CB.setCoupon_content(rs.getFloat("coupon_content"));
				CB.setDiscount_type(rs.getInt("discount_type"));
				CB.setCoupon_status(rs.getInt("coupon_status"));
				CB.setEmployee_account(rs.getString("employee_account"));
				CB.setCoupon_code_id(rs.getInt("coupon_code_id"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
		return CB;
		
		
	}
	
	

}
