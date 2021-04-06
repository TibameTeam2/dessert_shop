package com.order_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.order_detail.model.OrderDetailBean;
import com.util.JDBCUtil;

public class OrderDetailDaoImpl implements OrderDetailDao {
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
	private static final String GetAllProductNameImageIncluded = "select order_master_id, od.order_detail_id, od.product_id, product_qty, od.product_price, image_id, product_image, product_name\r\n"
			+ "from order_detail od \r\n" + "join product p on od.product_id = p.product_id\r\n"
			+ "join product_image pi on p.product_id = pi.product_id\r\n" + "where order_master_id = ?\r\n"
			+ "order by order_master_id;";

	public void insert(OrderDetailBean odBean) {
		INSERT = "INSERT INTO order_detail (order_master_id, product_id, product_qty, product_price) VALUES (?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, odBean.getOrder_master_id());
			pstmt.setInt(2, odBean.getProduct_id());
			pstmt.setInt(3, odBean.getProduct_qty());
			pstmt.setInt(4, odBean.getProduct_price());

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

	public void update(OrderDetailBean odBean) {
		UPDATE = "UPDATE order_detail set order_master_id = ?, product_id = ?, product_qty = ?, product_price = ? where order_detail_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, odBean.getOrder_master_id());
			pstmt.setInt(2, odBean.getProduct_id());
			pstmt.setInt(3, odBean.getProduct_qty());
			pstmt.setInt(4, odBean.getProduct_price());
			pstmt.setInt(5, odBean.getOrder_detail_id());

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

	public void delete(Integer order_detail_id) {
		DELETE = "DELETE FROM order_detail where order_detail_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, order_detail_id);

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

	public OrderDetailBean findByPrimaryKey(Integer order_detail_id) {
		SELECT_PK = "SELECT * FROM order_detail where order_detail_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		OrderDetailBean odBean = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_PK);

			pstmt.setInt(1, order_detail_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				odBean = new OrderDetailBean();
				odBean.setOrder_detail_id(rs.getInt("order_detail_id"));
				odBean.setOrder_master_id(rs.getInt("order_master_id"));
				odBean.setProduct_id(rs.getInt("product_id"));
				odBean.setProduct_qty(rs.getInt("product_qty"));
				odBean.setProduct_price(rs.getInt("product_price"));
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

		return odBean;

	}

	public List<OrderDetailBean> getAll() {
		SELECT_ALL = "SELECT * FROM order_detail";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<OrderDetailBean> list_OrderDetailBean = new ArrayList<OrderDetailBean>();
		OrderDetailBean odBean = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				odBean = new OrderDetailBean();
				odBean.setOrder_detail_id(rs.getInt("order_detail_id"));
				odBean.setOrder_master_id(rs.getInt("order_master_id"));
				odBean.setProduct_id(rs.getInt("product_id"));
				odBean.setProduct_qty(rs.getInt("product_qty"));
				odBean.setProduct_price(rs.getInt("product_price"));
				list_OrderDetailBean.add(odBean);
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

		return list_OrderDetailBean;

	}

	@Override
	public List<OrderDetailBean> findByOrderMasterId(Integer order_master_id) {

		List<OrderDetailBean> list_OrderDetailBean = new ArrayList<OrderDetailBean>();
		OrderDetailBean odBean = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GetAllProductNameImageIncluded);

			pstmt.setInt(1, order_master_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				odBean = new OrderDetailBean();
				odBean.setOrder_detail_id(rs.getInt("order_detail_id"));
				odBean.setOrder_master_id(rs.getInt("order_master_id"));
				odBean.setProduct_id(rs.getInt("product_id"));
				odBean.setProduct_qty(rs.getInt("product_qty"));
				odBean.setProduct_price(rs.getInt("product_price"));
				odBean.setProduct_name(rs.getString("product_name"));
				odBean.setImage_id(rs.getInt("image_id"));
				odBean.setProduct_image(rs.getBytes("product_image"));
				list_OrderDetailBean.add(odBean);
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

		return list_OrderDetailBean;

	}

	public static void main(String[] args) {

		OrderDetailDaoImpl dao = new OrderDetailDaoImpl();

		// insert
		// 設定資料
//		OrderDetailBean odBean = new OrderDetailBean();
//		odBean.setOrder_master_id(3);
//		odBean.setProduct_id(2);;
//		odBean.setProduct_qty(10);
//		odBean.setProduct_price(500);
//		dao.insert(odBean);

		// update
		// 設定資料
//		OrderDetailBean odBean = new OrderDetailBean();
//		odBean.setOrder_detail_id(3);
//		odBean.setOrder_master_id(1);
//		odBean.setProduct_id(1);
//		odBean.setProduct_qty(20);
//		odBean.setProduct_price(1200);
//		dao.update(odBean);

		// delete
//		dao.delete(4);

		// select_pk
//		OrderDetailBean odBean = dao.findByPrimaryKey(1);
//		System.out.print(odBean);

		// select_all
//		List<OrderDetailBean> list = dao.getAll();
//		for (OrderDetailBean odBean : list) {
//			System.out.println(odBean);
//		}

		// findByOrderMasterId
		List<OrderDetailBean> list = dao.findByOrderMasterId(3);
		for (OrderDetailBean odBean : list) {
			System.out.println("order_detail_id: " + odBean.getOrder_detail_id() + ",");
			System.out.println("order_master_id: " + odBean.getOrder_master_id() + ",");
			System.out.println("product_id: " + odBean.getProduct_id() + ",");
			System.out.println("product_qty: " + odBean.getProduct_qty() + ",");
			System.out.println("product_price: " + odBean.getProduct_price() + ",");
			System.out.println("product_name: " + odBean.getProduct_name() + ",");
			System.out.println("image_id: " + odBean.getImage_id() + ",");
			System.out.println("product_image: " + odBean.getProduct_image());
			System.out.println("---------------------------------------------------------");
		}
		System.out.println("----------有跑查order_master_id----------");
	}

}
