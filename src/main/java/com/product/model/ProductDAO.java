package com.product.model;

import com.daily_special.model.DailySpecialBean;
import com.daily_special.model.DailySpecialDAO;
import com.util.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDAO {
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
	
	public void init() {
//		driver = "com.mysql.cj.jdbc.Driver";
//		url = "jdbc:mysql://localhost:3306/sweet?serverTimezone=Asia/Taipei";
//		userid = "root";
//		passwd = "root";
	}
	
	public void insert(ProductBean productBean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		INSERT = "INSERT INTO product (product_name, product_type, product_intro, product_price,product_available_qty,"
									+ "product_status, product_calorie, degree_of_sweetness, total_star, total_review) "
									+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		

		
		try {
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(INSERT);
		
		pstmt.setString(1, productBean.getProduct_name());
		pstmt.setString(2, productBean.getProduct_type());
		pstmt.setString(3, productBean.getProduct_intro());
		pstmt.setInt(4, productBean.getProduct_price());
		pstmt.setInt(5, productBean.getProduct_available_qty());
		pstmt.setInt(6, productBean.getProduct_status());
		pstmt.setInt(7, productBean.getProduct_calorie());
		pstmt.setInt(8, productBean.getDegree_of_sweetness());
		pstmt.setInt(9, productBean.getTotal_star());
		pstmt.setInt(10, productBean.getTotal_review());
		
		
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
	
	public void update(ProductBean productBean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		UPDATE = "UPDATE product set product_name = ?, product_type = ?, product_intro = ?, product_price = ?, product_available_qty = ?, product_status = ?, product_calorie = ?, degree_of_sweetness = ?, total_star = ?, total_review = ?"
				+ " where product_id = ?";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, productBean.getProduct_name());
			pstmt.setString(2, productBean.getProduct_type());
			pstmt.setString(3, productBean.getProduct_intro());
			pstmt.setInt(4, productBean.getProduct_price());
			pstmt.setInt(5, productBean.getProduct_available_qty());
			pstmt.setInt(6, productBean.getProduct_status());
			pstmt.setInt(7, productBean.getProduct_calorie());
			pstmt.setInt(8, productBean.getDegree_of_sweetness());
			pstmt.setInt(9, productBean.getTotal_star());
			pstmt.setInt(10, productBean.getTotal_review());
			
			pstmt.setInt(11, productBean.getProduct_id());
			
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
	
	public void delete(Integer product_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		DELETE = "DELETE FROM product where product_id = ?";

				
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, product_id);
			
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
	
	public List<ProductBean> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SELECT_ALL = "SELECT * FROM product";
		List<ProductBean> list_productBean = new ArrayList<ProductBean>();
		ProductBean productBean = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ALL);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				productBean = new ProductBean();
				productBean.setProduct_id(rs.getInt("product_id"));
				productBean.setProduct_name(rs.getString("product_name"));
				productBean.setProduct_type(rs.getString("product_type"));
				productBean.setProduct_intro(rs.getString("product_intro"));
				productBean.setProduct_price(rs.getInt("product_price"));
				productBean.setProduct_available_qty(rs.getInt("product_available_qty"));
				productBean.setProduct_status(rs.getInt("product_status"));
				productBean.setProduct_calorie(rs.getInt("product_calorie"));
				productBean.setDegree_of_sweetness(rs.getInt("degree_of_sweetness"));
				productBean.setTotal_star(rs.getInt("total_star"));
				productBean.setTotal_review(rs.getInt("total_review"));
				list_productBean.add(productBean);
//				System.out.println(productBean);
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
		return list_productBean;
	}
	
	public ProductBean findByPrimaryKey(Integer product_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SELECT_PK = "SELECT * FROM product where product_id = ?";
		
		//設定資料
		ProductBean productBean = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_PK);
			
			pstmt.setInt(1, product_id);
				
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productBean = new ProductBean();
				productBean.setProduct_id(rs.getInt("product_id"));
				productBean.setProduct_name(rs.getString("product_name"));
				productBean.setProduct_type(rs.getString("product_type"));
				productBean.setProduct_intro(rs.getString("product_intro"));
				productBean.setProduct_price(rs.getInt("product_price"));
				productBean.setProduct_available_qty(rs.getInt("product_available_qty"));
				productBean.setProduct_status(rs.getInt("product_status"));
				productBean.setProduct_calorie(rs.getInt("product_calorie"));
				productBean.setDegree_of_sweetness(rs.getInt("degree_of_sweetness"));
				productBean.setTotal_star(rs.getInt("total_star"));
				productBean.setTotal_review(rs.getInt("total_review"));
			
//				System.out.println(productBean);
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
		
		return productBean;
	}



	public static void main(String[] args) {
		ProductDAO dao = new ProductDAO();

		// 新增
		//設定資料
//		ProductBean productBean = new ProductBean();
//		productBean.setProduct_name("蜂蜜蛋糕");
//		productBean.setProduct_type("蛋糕");
//		productBean.setProduct_intro("使用台灣產蜂蜜......");
//		productBean.setProduct_price(900);
//		productBean.setProduct_available_qty(50);
//		productBean.setProduct_status(1);
//		productBean.setProduct_calorie(435);
//		productBean.setDegree_of_sweetness(5);
//		productBean.setTotal_star(4);
//		productBean.setTotal_review(139);
//		dao.insert(productBean);

		// 修改
		//設定資料
//		ProductBean productBean = new ProductBean();
//		productBean.setProduct_name("貓掌餅乾");
//		productBean.setProduct_type("餅乾");
//		productBean.setProduct_intro("使用頂級麵粉......");
//		productBean.setProduct_price(300);
//		productBean.setProduct_available_qty(20);
//		productBean.setProduct_status(1);
//		productBean.setProduct_calorie(55);
//		productBean.setDegree_of_sweetness(1);
//		productBean.setTotal_star(5);
//		productBean.setTotal_review(19);
//		productBean.setProduct_id(4);
//		dao.update(productBean);


//        // 刪除
//		dao.delete(4);

		// 查詢
//		ProductBean productBean = dao.findByPrimaryKey(2);
//		System.out.println(productBean);


		// 查詢
		List<ProductBean> list = dao.getAll();
		for (ProductBean productBean : list) {
			System.out.println(productBean);
		}

	}
}
