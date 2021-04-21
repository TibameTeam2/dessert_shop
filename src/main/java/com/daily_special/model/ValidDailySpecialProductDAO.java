package com.daily_special.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.product.model.ProductBean;
import com.util.JDBCUtil;


public class ValidDailySpecialProductDAO implements ValidDailySpecialProductDAO_interface {
//	
//	private static JdbcTemplate jdbcTemplate;
//	private String driver = JDBCUtil.driver;
//	private String url = JDBCUtil.url;
//	private String userid = JDBCUtil.user;
//	private String passwd = JDBCUtil.password;
//	
//	String SELECT_ALL_VALID;
//	
//	
//	public List<ValidDailySpecialProductBean> getAll() {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		SELECT_ALL_VALID = "SELECT ds.*, p.* FROM (SELECT * FROM daily_special ds WHERE now() BETWEEN discount_start_time AND discount_deadline)ds "
//				+ "join (SELECT * FROM product p WHERE product_status = 1)p "
//				+ "on ds.product_id = p.product_id";
//
//		List<ValidDailySpecialProductBean> list = new ArrayList<ValidDailySpecialProductBean>();
//		ValidDailySpecialProductBean vds_productBean = null;
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(SELECT_ALL_VALID);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				vds_productBean = new ValidDailySpecialProductBean();
//				vds_productBean.setDiscount_product_id(rs.getInt("discount_product_id"));
//				vds_productBean.setProduct_id(rs.getInt("product_id"));
//				vds_productBean.setDiscount_price(rs.getInt("discount_price"));
//				vds_productBean.setDiscount_start_time(rs.getTimestamp("discount_start_time"));
//				vds_productBean.setDiscount_deadline(rs.getTimestamp("discount_deadline"));
////				vds_productBean.setProduct_name(product_name);(rs.getTimestamp("discount_deadline"));
//				
//				
////				list.add(ytlcBean);
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//		return list;
//
//	}
	
	public ValidDailySpecialProductBean getOneValidDSProduct(DailySpecialBean dsBean, ProductBean productBean) {
		ValidDailySpecialProductBean vds_productBean = new ValidDailySpecialProductBean();
		vds_productBean = new ValidDailySpecialProductBean();
		
		vds_productBean.setDiscount_product_id(dsBean.getDiscount_product_id());
		vds_productBean.setProduct_id(dsBean.getProduct_id());
		vds_productBean.setDiscount_price(dsBean.getDiscount_price());
		vds_productBean.setDiscount_start_time(dsBean.getDiscount_start_time());
		vds_productBean.setDiscount_deadline(dsBean.getDiscount_deadline());
		
		vds_productBean.setProduct_name(productBean.getProduct_name());
		vds_productBean.setProduct_type(productBean.getProduct_type());
		vds_productBean.setProduct_subtype(productBean.getProduct_subtype());
		vds_productBean.setProduct_intro(productBean.getProduct_intro());
		vds_productBean.setProduct_ingredient(productBean.getProduct_ingredient());
		vds_productBean.setProduct_price(productBean.getProduct_price());
		vds_productBean.setProduct_available_qty(productBean.getProduct_available_qty());
		vds_productBean.setProduct_status(productBean.getProduct_status());
		vds_productBean.setExpiry_after_buying(productBean.getExpiry_after_buying());
		vds_productBean.setProduct_calorie(productBean.getProduct_calorie());
		vds_productBean.setDegree_of_sweetness(productBean.getDegree_of_sweetness());
		vds_productBean.setTotal_star(productBean.getTotal_star());
		vds_productBean.setTotal_review(productBean.getTotal_review());
		vds_productBean.setTotal_purchase(productBean.getTotal_purchase());
		vds_productBean.setAverage_star(productBean.getAverage_star());
		vds_productBean.setImage_url(productBean.getImage_url());
		
		
		
		return vds_productBean;
		
	}
	
	

}
