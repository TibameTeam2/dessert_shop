package com.product.model;


import com.util.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ProductDAO implements ProductDAO_interface {
	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;
	
	String INSERT;
	String UPDATE;
	String DELETE;
	String SELECT_ALL;
	String SELECT_ALL_AVA;
	String SELECT_PK;
	String SELECT_SORT_PURCHASE;
	String SELECT_SORT_STAR;
	String SELECT_SORT_PRICE;
	String SELECT_SORT_CALORIE;
    String SELECT_SORT_SWEETNESS;
    String UPDATE_REVIEW;
    String SELECT_PNAME;
    
	
	String SELECT_ONEIMGID;
	String SELECT_ALLIMGID;
	String SELECT_ONEIMG;
	String SELECT_ALLIMG;
	
	public void init() {
//		driver = "com.mysql.cj.jdbc.Driver";
//		url = "jdbc:mysql://localhost:3306/sweet?serverTimezone=Asia/Taipei";
//		userid = "root";
//		passwd = "root";
	}
	
	public int insert(ProductBean productBean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		INSERT = "INSERT INTO product (product_name, product_type, product_subtype, product_intro, product_ingredient, product_price,product_available_qty,"
									+ "product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase) "
									+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		

		int count=0;
		try {
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(INSERT);
		
		pstmt.setString(1, productBean.getProduct_name());
		pstmt.setString(2, productBean.getProduct_type());
		pstmt.setString(3, productBean.getProduct_subtype());
		pstmt.setString(4, productBean.getProduct_intro());
		pstmt.setString(5, productBean.getProduct_ingredient());		
		pstmt.setInt(6, productBean.getProduct_price());
		pstmt.setInt(7, productBean.getProduct_available_qty());
		pstmt.setInt(8, productBean.getProduct_status());
		pstmt.setInt(9, productBean.getExpiry_after_buying());
		pstmt.setInt(10, productBean.getProduct_calorie());
		pstmt.setInt(11, productBean.getDegree_of_sweetness());
		pstmt.setInt(12, productBean.getTotal_star());
		pstmt.setInt(13, productBean.getTotal_review());
		pstmt.setInt(14, productBean.getTotal_purchase());
		
		
		pstmt.executeUpdate();
		
		pstmt = con.prepareStatement("select LAST_INSERT_ID()");
		
		
		ResultSet rs = null;
		rs = pstmt.executeQuery();
		if(rs.next()) {			
			count =rs.getInt(1);
			System.out.println(count);
		}
		rs.close();
		
		
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
		return count;
	}
	
	public void update(ProductBean productBean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		UPDATE = "UPDATE product set product_name = ?, product_type = ?, product_subtype = ?, product_intro = ?, product_ingredient = ?, product_price = ?, product_available_qty = ?, "
				+ "product_status = ?, expiry_after_buying = ?, product_calorie = ?, degree_of_sweetness = ?, total_star = ?, total_review = ?, total_purchase=?"
				+ " where product_id = ?";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, productBean.getProduct_name());
			pstmt.setString(2, productBean.getProduct_type());
			pstmt.setString(3, productBean.getProduct_subtype());
			pstmt.setString(4, productBean.getProduct_intro());
			pstmt.setString(5, productBean.getProduct_ingredient());
			pstmt.setInt(6, productBean.getProduct_price());
			pstmt.setInt(7, productBean.getProduct_available_qty());
			pstmt.setInt(8, productBean.getProduct_status());
			pstmt.setInt(9, productBean.getExpiry_after_buying());
			pstmt.setInt(10, productBean.getProduct_calorie());
			pstmt.setInt(11, productBean.getDegree_of_sweetness());
			pstmt.setInt(12, productBean.getTotal_star());
			pstmt.setInt(13, productBean.getTotal_review());
			pstmt.setInt(14, productBean.getTotal_purchase());
			
			pstmt.setInt(15, productBean.getProduct_id());
			
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
// 後台用，全部商品皆顯示	
	public List<ProductBean> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		SELECT_ALL = "SELECT *,ROUND(total_star/total_review, 1) as average_star FROM product order by product_id desc";
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
				productBean.setProduct_subtype(rs.getString("product_subtype"));
				productBean.setProduct_intro(rs.getString("product_intro"));
				productBean.setProduct_ingredient(rs.getString("Product_ingredient"));
				productBean.setProduct_price(rs.getInt("product_price"));
				productBean.setProduct_available_qty(rs.getInt("product_available_qty"));
				productBean.setProduct_status(rs.getInt("product_status"));
				productBean.setExpiry_after_buying(rs.getInt("expiry_after_buying"));
				productBean.setProduct_calorie(rs.getInt("product_calorie"));
				productBean.setDegree_of_sweetness(rs.getInt("degree_of_sweetness"));
				productBean.setTotal_star(rs.getInt("total_star"));
				productBean.setTotal_review(rs.getInt("total_review"));
				productBean.setTotal_purchase(rs.getInt("total_purchase"));
				productBean.setAverage_star(rs.getDouble("average_star"));
				list_productBean.add(productBean);
				
				pstmt1 = con.prepareStatement("SELECT image_id FROM sweet.product_image where product_id="+productBean.getProduct_id());
				List<String> img_url=new ArrayList<String>();
				ResultSet rs_image = pstmt1.executeQuery();
//照片的地方
				while (rs_image.next()) {
					img_url.add("/product_jsp/product.do?action=getProductImage&id="+rs_image.getString("image_id"));
				}
				productBean.setImage_url(img_url);
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
	public List<ProductBean> getAllAvailable() {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		SELECT_ALL_AVA = "SELECT *,ROUND(total_star/total_review, 1) as average_star FROM product where product_status = 1 order by product_id desc";
		List<ProductBean> list_productBean = new ArrayList<ProductBean>();
		ProductBean productBean = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ALL_AVA);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				productBean = new ProductBean();
				productBean.setProduct_id(rs.getInt("product_id"));
				productBean.setProduct_name(rs.getString("product_name"));
				productBean.setProduct_type(rs.getString("product_type"));
				productBean.setProduct_subtype(rs.getString("product_subtype"));
				productBean.setProduct_intro(rs.getString("product_intro"));
				productBean.setProduct_ingredient(rs.getString("Product_ingredient"));
				productBean.setProduct_price(rs.getInt("product_price"));
				productBean.setProduct_available_qty(rs.getInt("product_available_qty"));
				productBean.setProduct_status(rs.getInt("product_status"));
				productBean.setExpiry_after_buying(rs.getInt("expiry_after_buying"));
				productBean.setProduct_calorie(rs.getInt("product_calorie"));
				productBean.setDegree_of_sweetness(rs.getInt("degree_of_sweetness"));
				productBean.setTotal_star(rs.getInt("total_star"));
				productBean.setTotal_review(rs.getInt("total_review"));
				productBean.setTotal_purchase(rs.getInt("total_purchase"));
				
				productBean.setAverage_star(rs.getDouble("average_star"));
			
				list_productBean.add(productBean);

				pstmt1 = con.prepareStatement("SELECT image_id FROM sweet.product_image where product_id="+productBean.getProduct_id());
				List<String> img_url=new ArrayList<String>();
				ResultSet rs_image = pstmt1.executeQuery();
//照片的地方
				while (rs_image.next()) {
					img_url.add("/product_jsp/product.do?action=getProductImage&id="+rs_image.getString("image_id"));
				}
				productBean.setImage_url(img_url);
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
	
// 排序的方法*5
	public List<ProductBean> getAllSortByPurchase() {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		SELECT_SORT_PURCHASE = "SELECT * FROM product where product_status = 1 order by total_purchase desc";
		List<ProductBean> list_productBean = new ArrayList<ProductBean>();
		ProductBean productBean = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_SORT_PURCHASE);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				productBean = new ProductBean();
				productBean.setProduct_id(rs.getInt("product_id"));
				productBean.setProduct_name(rs.getString("product_name"));
				productBean.setProduct_type(rs.getString("product_type"));
				productBean.setProduct_subtype(rs.getString("product_subtype"));
				productBean.setProduct_intro(rs.getString("product_intro"));
				productBean.setProduct_ingredient(rs.getString("Product_ingredient"));
				productBean.setProduct_price(rs.getInt("product_price"));
				productBean.setProduct_available_qty(rs.getInt("product_available_qty"));
				productBean.setProduct_status(rs.getInt("product_status"));
				productBean.setExpiry_after_buying(rs.getInt("expiry_after_buying"));
				productBean.setProduct_calorie(rs.getInt("product_calorie"));
				productBean.setDegree_of_sweetness(rs.getInt("degree_of_sweetness"));
				productBean.setTotal_star(rs.getInt("total_star"));
				productBean.setTotal_review(rs.getInt("total_review"));
				productBean.setTotal_purchase(rs.getInt("total_purchase"));
				list_productBean.add(productBean);
				
				pstmt1 = con.prepareStatement("SELECT image_id FROM sweet.product_image where product_id="+productBean.getProduct_id());
				List<String> img_url=new ArrayList<String>();
				ResultSet rs_image = pstmt1.executeQuery();
//照片的地方
				while (rs_image.next()) {
					img_url.add("/product_jsp/product.do?action=getProductImage&id="+rs_image.getString("image_id"));
				}
				productBean.setImage_url(img_url);

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
	
	public List<ProductBean> getAllSortByPrice() {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		SELECT_SORT_PRICE = "SELECT * FROM product where product_status = 1 order by product_price desc";
		List<ProductBean> list_productBean = new ArrayList<ProductBean>();
		ProductBean productBean = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_SORT_PRICE);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				productBean = new ProductBean();
				productBean.setProduct_id(rs.getInt("product_id"));
				productBean.setProduct_name(rs.getString("product_name"));
				productBean.setProduct_type(rs.getString("product_type"));
				productBean.setProduct_subtype(rs.getString("product_subtype"));
				productBean.setProduct_intro(rs.getString("product_intro"));
				productBean.setProduct_ingredient(rs.getString("Product_ingredient"));
				productBean.setProduct_price(rs.getInt("product_price"));
				productBean.setProduct_available_qty(rs.getInt("product_available_qty"));
				productBean.setProduct_status(rs.getInt("product_status"));
				productBean.setExpiry_after_buying(rs.getInt("expiry_after_buying"));
				productBean.setProduct_calorie(rs.getInt("product_calorie"));
				productBean.setDegree_of_sweetness(rs.getInt("degree_of_sweetness"));
				productBean.setTotal_star(rs.getInt("total_star"));
				productBean.setTotal_review(rs.getInt("total_review"));
				productBean.setTotal_purchase(rs.getInt("total_purchase"));
				list_productBean.add(productBean);
				
				pstmt1 = con.prepareStatement("SELECT image_id FROM sweet.product_image where product_id="+productBean.getProduct_id());
				List<String> img_url=new ArrayList<String>();
				ResultSet rs_image = pstmt1.executeQuery();
//照片的地方
				while (rs_image.next()) {
					img_url.add("/product_jsp/product.do?action=getProductImage&id="+rs_image.getString("image_id"));
				}
				productBean.setImage_url(img_url);
				
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
	
	public List<ProductBean> getAllSortByCalorie() {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		SELECT_SORT_CALORIE = "SELECT * FROM product where product_status = 1 order by product_calorie desc";
		List<ProductBean> list_productBean = new ArrayList<ProductBean>();
		ProductBean productBean = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_SORT_CALORIE);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				productBean = new ProductBean();
				productBean.setProduct_id(rs.getInt("product_id"));
				productBean.setProduct_name(rs.getString("product_name"));
				productBean.setProduct_type(rs.getString("product_type"));
				productBean.setProduct_subtype(rs.getString("product_subtype"));
				productBean.setProduct_intro(rs.getString("product_intro"));
				productBean.setProduct_ingredient(rs.getString("Product_ingredient"));
				productBean.setProduct_price(rs.getInt("product_price"));
				productBean.setProduct_available_qty(rs.getInt("product_available_qty"));
				productBean.setProduct_status(rs.getInt("product_status"));
				productBean.setExpiry_after_buying(rs.getInt("expiry_after_buying"));
				productBean.setProduct_calorie(rs.getInt("product_calorie"));
				productBean.setDegree_of_sweetness(rs.getInt("degree_of_sweetness"));
				productBean.setTotal_star(rs.getInt("total_star"));
				productBean.setTotal_review(rs.getInt("total_review"));
				productBean.setTotal_purchase(rs.getInt("total_purchase"));
				list_productBean.add(productBean);
				
				pstmt1 = con.prepareStatement("SELECT image_id FROM sweet.product_image where product_id="+productBean.getProduct_id());
				List<String> img_url=new ArrayList<String>();
				ResultSet rs_image = pstmt1.executeQuery();
//照片的地方
				while (rs_image.next()) {
					img_url.add("/product_jsp/product.do?action=getProductImage&id="+rs_image.getString("image_id"));
				}
				productBean.setImage_url(img_url);
				
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
	
	public List<ProductBean> getAllSortBySweetness() {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		SELECT_SORT_SWEETNESS = "SELECT * FROM product where product_status = 1 order by degree_of_sweetness desc";
		List<ProductBean> list_productBean = new ArrayList<ProductBean>();
		ProductBean productBean = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_SORT_SWEETNESS);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				productBean = new ProductBean();
				productBean.setProduct_id(rs.getInt("product_id"));
				productBean.setProduct_name(rs.getString("product_name"));
				productBean.setProduct_type(rs.getString("product_type"));
				productBean.setProduct_subtype(rs.getString("product_subtype"));
				productBean.setProduct_intro(rs.getString("product_intro"));
				productBean.setProduct_ingredient(rs.getString("Product_ingredient"));
				productBean.setProduct_price(rs.getInt("product_price"));
				productBean.setProduct_available_qty(rs.getInt("product_available_qty"));
				productBean.setProduct_status(rs.getInt("product_status"));
				productBean.setExpiry_after_buying(rs.getInt("expiry_after_buying"));
				productBean.setProduct_calorie(rs.getInt("product_calorie"));
				productBean.setDegree_of_sweetness(rs.getInt("degree_of_sweetness"));
				productBean.setTotal_star(rs.getInt("total_star"));
				productBean.setTotal_review(rs.getInt("total_review"));
				productBean.setTotal_purchase(rs.getInt("total_purchase"));
				list_productBean.add(productBean);
				
				pstmt1 = con.prepareStatement("SELECT image_id FROM sweet.product_image where product_id="+productBean.getProduct_id());
				List<String> img_url=new ArrayList<String>();
				ResultSet rs_image = pstmt1.executeQuery();
//照片的地方
				while (rs_image.next()) {
					img_url.add("/product_jsp/product.do?action=getProductImage&id="+rs_image.getString("image_id"));
				}
				productBean.setImage_url(img_url);
				
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
	
	public List<ProductBean> getAllSortByStar() {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		SELECT_SORT_STAR = "SELECT *, ROUND(total_star/total_review, 1) as average_star FROM product where product_status = 1 order by average_star desc";
		List<ProductBean> list_productBean = new ArrayList<ProductBean>();
		ProductBean productBean = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_SORT_STAR);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				productBean = new ProductBean();
				productBean.setProduct_id(rs.getInt("product_id"));
				productBean.setProduct_name(rs.getString("product_name"));
				productBean.setProduct_type(rs.getString("product_type"));
				productBean.setProduct_subtype(rs.getString("product_subtype"));
				productBean.setProduct_intro(rs.getString("product_intro"));
				productBean.setProduct_ingredient(rs.getString("Product_ingredient"));
				productBean.setProduct_price(rs.getInt("product_price"));
				productBean.setProduct_available_qty(rs.getInt("product_available_qty"));
				productBean.setProduct_status(rs.getInt("product_status"));
				productBean.setExpiry_after_buying(rs.getInt("expiry_after_buying"));
				productBean.setProduct_calorie(rs.getInt("product_calorie"));
				productBean.setDegree_of_sweetness(rs.getInt("degree_of_sweetness"));
				productBean.setTotal_star(rs.getInt("total_star"));
				productBean.setTotal_review(rs.getInt("total_review"));
				productBean.setTotal_purchase(rs.getInt("total_purchase"));
				productBean.setAverage_star(rs.getDouble("average_star"));//
				list_productBean.add(productBean);
				
				pstmt1 = con.prepareStatement("SELECT image_id FROM sweet.product_image where product_id="+productBean.getProduct_id());
				List<String> img_url=new ArrayList<String>();
				ResultSet rs_image = pstmt1.executeQuery();
//照片的地方
				while (rs_image.next()) {
					img_url.add("/product_jsp/product.do?action=getProductImage&id="+rs_image.getString("image_id"));
				}
				productBean.setImage_url(img_url);
				
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
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		SELECT_PK = "SELECT *,ROUND(total_star/total_review, 1) as average_star FROM product where product_id = ?";
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
				productBean.setProduct_subtype(rs.getString("product_subtype"));
				productBean.setProduct_intro(rs.getString("product_intro"));
				productBean.setProduct_ingredient(rs.getString("Product_ingredient"));
				productBean.setProduct_price(rs.getInt("product_price"));
				productBean.setProduct_available_qty(rs.getInt("product_available_qty"));
				productBean.setProduct_status(rs.getInt("product_status"));
				productBean.setExpiry_after_buying(rs.getInt("expiry_after_buying"));
				productBean.setProduct_calorie(rs.getInt("product_calorie"));
				productBean.setDegree_of_sweetness(rs.getInt("degree_of_sweetness"));
				productBean.setTotal_star(rs.getInt("total_star"));
				productBean.setTotal_review(rs.getInt("total_review"));
				productBean.setTotal_purchase(rs.getInt("total_purchase"));

				productBean.setAverage_star(rs.getDouble("average_star"));
// 照片
				pstmt1 = con.prepareStatement("SELECT image_id FROM sweet.product_image WHERE product_id="+productBean.getProduct_id());
				List<String> img_url = new ArrayList<String>();
				ResultSet rs_image = pstmt1.executeQuery();
				
				while(rs_image.next()) {
					img_url.add("/product_jsp/product.do?action=getProductImage&id="+rs_image.getString("image_id"));
				}
				
				productBean.setImage_url(img_url);
				
				
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
	
	// 使用procut_name找商品，backend_checkProductName用
	public ProductBean findByProductName(String product_name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		SELECT_PNAME = "SELECT * FROM product where product_name = ?";
		
		//設定資料
		ProductBean productBean = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_PNAME);
			
			pstmt.setString(1, product_name);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productBean = new ProductBean();
				
//				productBean.setProduct_id(rs.getInt("product_id"));
				productBean.setProduct_name(rs.getString("product_name"));
//				productBean.setProduct_type(rs.getString("product_type"));
//				productBean.setProduct_subtype(rs.getString("product_subtype"));
//				productBean.setProduct_intro(rs.getString("product_intro"));
//				productBean.setProduct_ingredient(rs.getString("Product_ingredient"));
//				productBean.setProduct_price(rs.getInt("product_price"));
//				productBean.setProduct_available_qty(rs.getInt("product_available_qty"));
//				productBean.setProduct_status(rs.getInt("product_status"));
//				productBean.setExpiry_after_buying(rs.getInt("expiry_after_buying"));
//				productBean.setProduct_calorie(rs.getInt("product_calorie"));
//				productBean.setDegree_of_sweetness(rs.getInt("degree_of_sweetness"));
//				productBean.setTotal_star(rs.getInt("total_star"));
//				productBean.setTotal_review(rs.getInt("total_review"));
//				productBean.setTotal_purchase(rs.getInt("total_purchase"));
//				
// 照片
//				pstmt1 = con.prepareStatement("SELECT image_id FROM sweet.product_image WHERE product_id="+productBean.getProduct_id());
//				List<String> img_url = new ArrayList<String>();
//				ResultSet rs_image = pstmt1.executeQuery();
				
//				while(rs_image.next()) {
//					img_url.add("/product_jsp/product.do?action=getProductImage&id="+rs_image.getString("image_id"));
//				}
				
//				productBean.setImage_url(img_url);
				
				
				System.out.println("DAO : findByProductName" + productBean);
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
	
	
	
	
	// with one picture
	public List<ProductBean> getAllWithOneImage() {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		SELECT_ONEIMG = "SELECT p.*, pi.image_id, pi.product_image FROM product p "
				+ "left join(select min(image_id) as image_id, product_id, product_image from  product_image pi group by product_id)pi "
				+ "on p.product_id = pi.product_id";
		
		//設定資料
		List<ProductBean> list_productBean = new ArrayList<ProductBean>();
		ProductBean productBean = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ONEIMG);
//			
//			pstmt.setInt(1, product_id);
//			pstmt.setInt(2, product_id);
//			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productBean = new ProductBean();
				
				productBean.setProduct_id(rs.getInt("product_id"));
				productBean.setProduct_name(rs.getString("product_name"));
				productBean.setProduct_type(rs.getString("product_type"));
				productBean.setProduct_subtype(rs.getString("product_subtype"));
				productBean.setProduct_intro(rs.getString("product_intro"));
				productBean.setProduct_ingredient(rs.getString("Product_ingredient"));
				productBean.setProduct_price(rs.getInt("product_price"));
				productBean.setProduct_available_qty(rs.getInt("product_available_qty"));
				productBean.setProduct_status(rs.getInt("product_status"));
				productBean.setExpiry_after_buying(rs.getInt("expiry_after_buying"));
				productBean.setProduct_calorie(rs.getInt("product_calorie"));
				productBean.setDegree_of_sweetness(rs.getInt("degree_of_sweetness"));
				productBean.setTotal_star(rs.getInt("total_star"));
				productBean.setTotal_review(rs.getInt("total_review"));
				productBean.setTotal_purchase(rs.getInt("total_purchase"));
				productBean.setImage_id(rs.getInt("image_id"));
// 照片
				productBean.setImage_url(Arrays.asList("/product_jsp/product.do?action=getProductImage&id="+rs.getString("image_id")));
				
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

	
	
	// One product with one picture
		public ProductBean getOneProductOneImageId(Integer product_id) {
			Connection con = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt1 = null;
			ResultSet rs = null;
			SELECT_ONEIMG = "SELECT p.*, pi.image_id, pi.product_image FROM product p "
					+ "left join(select min(image_id) as image_id, product_id, product_image from  product_image pi where product_id = ?)pi "
					+ "on p.product_id = pi.product_id where p.product_id = ?";
			
//			SELECT_ONEIMG2 = "SELECT p.*, pi.image_id, pi.product_image FROM product p "
//					+ "left join(select * from  product_image pi where product_id = ? and image_id = ?)pi "
//					+ "on p.product_id = pi.product_id where p.product_id = ?";
			
			//設定資料
			ProductBean productBean = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(SELECT_ONEIMG);
				
				pstmt.setInt(1, product_id);
				pstmt.setInt(2, product_id);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					productBean = new ProductBean();
					
					productBean.setProduct_id(rs.getInt("product_id"));
					productBean.setProduct_name(rs.getString("product_name"));
					productBean.setProduct_type(rs.getString("product_type"));
					productBean.setProduct_subtype(rs.getString("product_subtype"));
					productBean.setProduct_intro(rs.getString("product_intro"));
					productBean.setProduct_ingredient(rs.getString("Product_ingredient"));
					productBean.setProduct_price(rs.getInt("product_price"));
					productBean.setProduct_available_qty(rs.getInt("product_available_qty"));
					productBean.setProduct_status(rs.getInt("product_status"));
					productBean.setExpiry_after_buying(rs.getInt("expiry_after_buying"));
					productBean.setProduct_calorie(rs.getInt("product_calorie"));
					productBean.setDegree_of_sweetness(rs.getInt("degree_of_sweetness"));
					productBean.setTotal_star(rs.getInt("total_star"));
					productBean.setTotal_review(rs.getInt("total_review"));
					productBean.setTotal_purchase(rs.getInt("total_purchase"));
					productBean.setImage_id(rs.getInt("image_id"));
	// 照片
					productBean.setImage_url(Arrays.asList("/product_jsp/product.do?action=getProductImage&id="+rs.getString("image_id")));
					
//					System.out.println(productBean);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
// 新增找商品照片id的方法 getOne getAll
	
	public ProductBean getOneImageId(Integer product_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SELECT_ONEIMGID = "SELECT image_id FROM product_image where product_id = ? LIMIT 1";
		
		//設定資料
		ProductBean productBean = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ONEIMGID);
			
			pstmt.setInt(1, product_id);
				
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productBean = new ProductBean();
				
				productBean.setImage_id(rs.getInt("image_id"));
				
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
	public List<ProductBean> getAllImageId(Integer product_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SELECT_ALLIMGID = "SELECT image_id FROM product_image where product_id = ? ";
		
		//設定資料
		ProductBean productBean = null;
		List<ProductBean> list_imageId = new ArrayList<ProductBean>();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ALLIMGID);
			
			pstmt.setInt(1, product_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productBean = new ProductBean();
				
				productBean.setImage_id(rs.getInt("image_id"));
				
				list_imageId.add(productBean);
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
		return list_imageId;	
	}
	
// 取得一張照片 與所有照片的方法
	
public ProductBean getOneImage(Integer product_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SELECT_ONEIMG = "SELECT product_image FROM product_image where product_id = ? LIMIT 1";
		
		//設定資料
		ProductBean productBean = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ONEIMG);
			
			pstmt.setInt(1, product_id);
				
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productBean = new ProductBean();
				
				productBean.setProduct_image(rs.getBytes("product_image"));
				
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
public List<ProductBean> getAllImage(Integer product_id) {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	SELECT_ALLIMG = "SELECT product_image FROM product_image where product_id = ? ";
	
	//設定資料
	ProductBean productBean = null;
	List<ProductBean> list_image = new ArrayList<ProductBean>();
	
	try {
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(SELECT_ALLIMG);
		
		pstmt.setInt(1, product_id);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			productBean = new ProductBean();
		
			productBean.setProduct_image(rs.getBytes("product_image"));
			
			list_image.add(productBean);
		
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
	
	return list_image;
	
}
	


	public static void main(String[] args) {
		ProductDAO dao = new ProductDAO();

//		// 新增
//		// 設定資料
//		ProductBean productBean = new ProductBean();
//		productBean.setProduct_name("柑橘檸檬磅蛋糕");
//		productBean.setProduct_type("蛋糕");
//		productBean.setProduct_subtype("磅蛋糕");
//		productBean.setProduct_intro("新鮮柑橘與檸檬片......");
//		productBean.setProduct_ingredient("柑橘、檸檬、檸檬乾...");
//		productBean.setProduct_price(100);
//		productBean.setProduct_available_qty(50);
//		productBean.setProduct_status(1);
//		productBean.setExpiry_after_buying(5);
//		productBean.setProduct_calorie(328);
//		productBean.setDegree_of_sweetness(3);
//		productBean.setTotal_star(577);
//		productBean.setTotal_review(139);
//		productBean.setTotal_purchase(166);
//		dao.insert(productBean);
		
		
		
//		// 修改
//		// 設定資料
//		ProductBean productBean = new ProductBean();
//		
//		productBean.setProduct_name("蜂蜜蛋糕");
//		productBean.setProduct_type("蛋糕");
//		productBean.setProduct_subtype("磅蛋糕");
//		productBean.setProduct_intro("使用台灣產蜂蜜......修改測試");
//		productBean.setProduct_ingredient("蜂蜜、奶油");
//		productBean.setProduct_price(100);
//		productBean.setProduct_available_qty(50);
//		productBean.setProduct_status(1);
//		productBean.setExpiry_after_buying(5);
//		productBean.setProduct_calorie(328);
//		productBean.setDegree_of_sweetness(3);
//		productBean.setTotal_star(+4);
//		productBean.setTotal_review(+1);
//		productBean.setTotal_purchase(166);
//		productBean.setProduct_id(22);
//		
//		dao.update(productBean);
		
		
//給別人用，測試:		
		//更新銷售
//		ProductService productSvc = new ProductService();
//		productSvc.addProductPurchase(25, 5);
		
		
		// 更新評論與星等
//		ProductService productSvc = new ProductService();
//		productSvc.addReviewStar(25, 4);

		
		// 更新商品狀態
//		ProductService productSvc = new ProductService();
//		productSvc.updateProductStatus(22, 1);
		
		
		
		

//        // 刪除
//		dao.delete(4);

		// 查詢
//		ProductBean productBean = dao.findByPrimaryKey(2);
//		System.out.println(productBean);


		// 查詢
//		List<ProductBean> list = dao.getAll();
//		for (ProductBean productBean : list) {
//			System.out.println(productBean);
//		}
		
		
		// 找商品第一張照片的id
//		ProductBean productBean = dao.getOneImageId(5);
//		System.out.println(productBean);
//		System.out.println(productBean.getImage_id());
		
		
//		//找該商品的所有照片id
//		List<ProductBean> list_imageId = dao.getAllImageId(4);
////		System.out.println(list_imageId);
//		System.out.println(list_imageId.size());
		
		// 找商品第一張照片
//		ProductBean productBean = dao.getOneImage(4);
//		System.out.println(productBean);
//		System.out.println(productBean.getProduct_image());
//		
		
//		//找該商品的所有照片
//		List<ProductBean> list_image = dao.getAllImage(4);
//		for(ProductBean productBean : list_image) {
//			
//			System.out.println(productBean.getProduct_image());
//		}
//		System.out.println(list_image.size());
	
	
	
	}
}
