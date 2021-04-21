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
import com.daily_special.model.DailySpecialDao;
import com.util.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProductImageDAO implements ProductImageDAO_interface{
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
	String SELECT_FK;
	
	// 使用byte[]方式
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	// readPicture
	public static void readPicture(byte[] bytes, Integer image_id) throws IOException {
		FileOutputStream fos = new FileOutputStream("C:/Output/"+ image_id+".png");
//		FileOutputStream fos = new FileOutputStream("Output/"+ image_id+".png");
		fos.write(bytes);
		fos.flush();
		fos.close();	
	}
		
	public void init() {
//		driver = "com.mysql.cj.jdbc.Driver";
//		url = "jdbc:mysql://localhost:3306/sweet?serverTimezone=Asia/Taipei";
//		userid = "root";
//		passwd = "root";
	}
	
	public void insert(ProductImageBean piBean) throws IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		INSERT = "INSERT INTO product_image (product_id, product_image) VALUES (?, ?) ";
		
		int img_count = 0;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, piBean.getProduct_id());
			pstmt.setBytes(2, piBean.getProduct_image());

			pstmt.executeUpdate();
			
			
			//insert後馬上拿到照片id
			pstmt = con.prepareStatement("select LAST_INSERT_ID()");
			
			
			ResultSet rs = null;
			rs = pstmt.executeQuery();
			if(rs.next()) {			
				img_count =rs.getInt(1);
				System.out.println("piDAO裡面的img_count:" + img_count);
			}
			rs.close();

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
	
	public void update(ProductImageBean piBean) throws IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		UPDATE = "UPDATE product_image set product_id = ?, product_image = ? WHERE image_id = ?";
				
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, piBean.getProduct_id());
			pstmt.setBytes(2, piBean.getProduct_image());
			pstmt.setInt(3, piBean.getImage_id());

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
	
	public void delete(Integer image_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		DELETE = "DELETE FROM product_image WHERE image_id = ?";
		
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
		System.out.println("getAll()");
		
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
				piBean.setProduct_image(rs.getBytes("product_image"));
				
				System.out.println("piDao 讀取照片前");//
				list_piBean.add(piBean);
				readPicture(piBean.getProduct_image(), piBean.getImage_id());
//				System.out.println(piBean);
		
				System.out.println("piDao 讀取照片後");//
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
		ProductImageBean piBean = null;
		
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
				piBean.setProduct_image(rs.getBytes("product_image"));

//				System.out.println(piBean);
				readPicture(piBean.getProduct_image(), piBean.getImage_id());
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

	//撈出相同product_id的所有照片List<ProductImageBean>
	public List<ProductImageBean> findImgByProductId(Integer product_id) throws IOException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SELECT_FK = "SELECT * FROM product_image WHERE product_id = ?";
		List<ProductImageBean> list_piBean = new ArrayList<ProductImageBean>();
		ProductImageBean piBean = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_FK);
			
			pstmt.setInt(1, product_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				piBean = new ProductImageBean();
				piBean.setImage_id(rs.getInt("image_id"));
				piBean.setProduct_id(rs.getInt("product_id"));
				piBean.setProduct_image(rs.getBytes("product_image"));

				list_piBean.add(piBean);
//				System.out.println(piBean);
				readPicture(piBean.getProduct_image(), piBean.getImage_id());
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
	

	public static void main(String[] args) throws IOException {
		ProductImageDAO dao = new ProductImageDAO();

		// 新增
//		 設定資料
//		byte[] pic = getPictureByteArray("Images/bOrangePound-1.jpg");
//		ProductImageBean piBean = new ProductImageBean();
//		piBean.setProduct_id(5);
//		piBean.setProduct_image(pic);
//		dao.insert(piBean);

		// 修改
		// 設定資料
//		byte[] pic = getPictureByteArray("Images/chocoMacaron2.jpg");
//
//		ProductImageBean piBean = new ProductImageBean();
//		piBean.setProduct_id(1);
//		piBean.setProduct_image(pic);
//		piBean.setImage_id(1);
//		dao.update(piBean);
		
		// 刪除
//		dao.delete(4);

		// 查詢
		ProductImageBean piBean = dao.findByPrimaryKey(4);
		System.out.println(piBean);


		// 查詢
//		List<ProductImageBean> list = dao.getAll();
//		for (ProductImageBean piBean : list) {
//			System.out.println(piBean);	
//		}
		
//		System.out.println(list.size());
		
		//撈出相同product_id的所有照片List<ProductImageBean>
//		List<ProductImageBean> list = dao.findImgByProductId(4);
//		for(ProductImageBean piBean : list) {
//			System.out.println(piBean);
//		}
//		System.out.println(list.size());
		
	}
}
