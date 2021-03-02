package com.product_image.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.daily_special.model.DailySpecialBean;
import com.daily_special.model.DailySpecialDAO;
import com.util.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProductImageDAO {
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
	
	// 使用InputStream資料流方式
	public static InputStream getPictureStream(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		return fis;
	}
	
	
	
	// 使用Blob
	public static void  readPicture(Blob blob, Integer id) throws IOException, SQLException {
		InputStream is = blob.getBinaryStream();
		FileOutputStream fos = new FileOutputStream("Output/" + id + ".jfif");
		int i;
		while((i = is.read()) != -1) {
			fos.write(i);
		}
		fos.flush();
		fos.close();
		is.close();
	}
	
	
	
	public void init() {
//		driver = "com.mysql.cj.jdbc.Driver";
//		url = "jdbc:mysql://localhost:3306/sweet?serverTimezone=Asia/Taipei";
//		userid = "root";
//		passwd = "root";
	}
	
	public void insert() throws IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		INSERT = "INSERT INTO product_image (product_id, product_image) VALUES (?, ?) ";
		
		// 設定資料
		InputStream is = getPictureStream("Images/cake3.jfif");
		ProductImageBean piBean = new ProductImageBean();
		piBean.setProduct_id(6);
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, piBean.getProduct_id());
			pstmt.setBlob(2, is);

			pstmt.executeUpdate();
			is.close();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
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
	
	public void update() throws IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		UPDATE = "UPDATE product_image set product_id = ?, product_image = ? WHERE image_id = ?";
		
		// 設定資料
		InputStream is = getPictureStream("Images/cake2.jfif");
		
		ProductImageBean piBean = new ProductImageBean();
		piBean.setProduct_id(4);
		piBean.setImage_id(12);
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, piBean.getProduct_id());
			pstmt.setBlob(2, is);
		
			pstmt.setInt(3, piBean.getImage_id());

			pstmt.executeUpdate();
			is.close();
			
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
	
	public void delete(Integer image_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		DELETE = "DELETE FROM product_image WHERE image_id = ?";
		
		// 設定資料
//		Integer image_id = 3;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
		
			pstmt.setInt(1,  image_id);
			
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
		
	public List<ProductImageBean> getAll() throws IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SELECT_ALL = "SELECT * FROM product_image";	
		List<ProductImageBean> list_piBean = new ArrayList<ProductImageBean>();
		ProductImageBean piBean = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ALL);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				piBean = new ProductImageBean();
				piBean.setImage_id(rs.getInt("image_id"));
				piBean.setProduct_id(rs.getInt("product_id"));
				
				Blob blob = rs.getBlob("product_image");
				piBean.setProduct_image(blob);
				
				list_piBean.add(piBean);
				System.out.println(piBean);
				readPicture(blob, rs.getInt("image_id"));
		
			}
			
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
		return list_piBean;
	}	
		
	public ProductImageBean findByPrimaryKey(Integer image_id) throws IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SELECT_PK = "SELECT * FROM product_image WHERE image_id = ?";	
		
		// 設定資料
		ProductImageBean piBean = null;
//		Integer image_id = 12;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_PK);
			
			pstmt.setInt(1, image_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				piBean = new ProductImageBean();
				piBean.setImage_id(rs.getInt("image_id"));
				piBean.setProduct_id(rs.getInt("product_id"));
				
				Blob blob = rs.getBlob("product_image");
				piBean.setProduct_image(blob);
				
//				System.out.println(piBean);
				readPicture(blob, rs.getInt("image_id"));
				
			}
			
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
		return piBean;
	}



	public static void main(String[] args) throws IOException {
		ProductImageDAO dao = new ProductImageDAO();


		// 新增
		// 設定資料
//		DailySpecialBean dsBean = new DailySpecialBean();
//		dsBean.setProduct_id(3);
//		dsBean.setDiscount_price(150);
//		dsBean.setDiscount_start_time(Timestamp.valueOf("2021-02-28 00:00:00"));
//		dsBean.setDiscount_deadline(Timestamp.valueOf("2021-02-28 23:59:59"));
//		dao.insert(dsBean);

		// 修改
		// 設定資料
//		DailySpecialBean dsBean = new DailySpecialBean();
//		dsBean.setProduct_id(3);
//		dsBean.setDiscount_price(240);
//		dsBean.setDiscount_start_time(Timestamp.valueOf("2021-04-04 00:00:00"));
//		dsBean.setDiscount_deadline(Timestamp.valueOf("2021-04-04 23:59:59"));
//
//		dsBean.setDiscount_product_id(4);
//        dao.update(dsBean);


//        // 刪除
//       dao.delete(3);

		// 查詢
//		ProductImageBean piBean = dao.findByPrimaryKey(2);
//		System.out.println(piBean);


		// 查詢
//		List<ProductImageBean> list = dao.getAll();
//		for (ProductImageBean piBean : list) {
//			System.out.println(piBean);
//		}

	}
}
