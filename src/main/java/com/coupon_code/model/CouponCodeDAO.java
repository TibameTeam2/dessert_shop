package com.coupon_code.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import com.util.JDBCUtil;

public class CouponCodeDAO implements CouponCodeDAO_interface{

	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String password = JDBCUtil.password;

	String INSERT;
	String DELETE;
	String UPDATE;
	String SELECT;
	String SELECTPK;

	public void init() {
//		driver = "com.mysql.cj.jdbc.Driver";
//		url = "jdbc:mysql://localhost:3306/sweet?serverTimezone=Asia/Taipei";
//		userid = "root";
//		password = "1qaz2wsx";
//
//		INSERT = "INSERT INTO coupon_code(coupon_code,coupon_code_effective_date,\r\n"
//				+ "coupon_code_expire_date,coupon_code_text_content,coupon_code_text,discount_type,employee_account) "
//				+ "VALUES(?,?,?,?,?,?,?)";
//
//		UPDATE = "UPDATE coupon_code set coupon_code = ?,coupon_code_effective_date = ?,"
//				+ "coupon_code_expire_date = ?,coupon_code_text_content = ?,coupon_code_text = ?,"
//				+ "discount_type = ?,employee_account = ? WHERE coupon_code_id = ?";
//
//		DELETE = "DELETE FROM coupon_code WHERE coupon_code_id = ?";
//
//		SELECT = "SELECT * FROM coupon_code";
//
//		SELECTPK = "SELECT * FROM coupon_code WHERE coupon_code_id = ?";

	}

	public void insert(CouponCodeBean CCB) {

		Connection con = null;
		PreparedStatement pstmt = null;

		INSERT = "INSERT INTO coupon_code(coupon_code,coupon_code_effective_date,\r\n"
				+ "coupon_code_expire_date,coupon_code_text_content,coupon_code_text,discount_type,employee_account) "
				+ "VALUES(?,?,?,?,?,?,?)";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, CCB.getCoupon_code());
			pstmt.setTimestamp(2, CCB.getCoupon_code_effective_date());
			pstmt.setTimestamp(3, CCB.getCoupon_code_expire_date());
			pstmt.setString(4, CCB.getCoupon_code_text_content());
			pstmt.setFloat(5, CCB.getCoupon_code_text());
			pstmt.setInt(6, CCB.getDiscount_type());
			pstmt.setString(7, CCB.getEmployee_account());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
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

	public void update(CouponCodeBean CCB) {

		Connection con = null;
		PreparedStatement pstmt = null;

		UPDATE = "UPDATE coupon_code set coupon_code = ?,coupon_code_effective_date = ?,"
				+ "coupon_code_expire_date = ?,coupon_code_text_content = ?,coupon_code_text = ?,"
				+ "discount_type = ?,employee_account = ? WHERE coupon_code_id = ?";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, CCB.getCoupon_code());
			pstmt.setTimestamp(2, CCB.getCoupon_code_effective_date());
			pstmt.setTimestamp(3, CCB.getCoupon_code_expire_date());
			pstmt.setString(4, CCB.getCoupon_code_text_content());
			pstmt.setFloat(5, CCB.getCoupon_code_text());
			pstmt.setInt(6, CCB.getDiscount_type());
			pstmt.setString(7, CCB.getEmployee_account());
			pstmt.setInt(8, CCB.getCoupon_code_id());

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

	public void delete(Integer coupon_code_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		DELETE = "DELETE FROM coupon_code WHERE coupon_code_id = ?";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, coupon_code_id);
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

	public List<CouponCodeBean> getAll() {

		Connection con = null;
		PreparedStatement pstmt = null;

		SELECT = "SELECT * FROM coupon_code";

		CouponCodeBean CCB;
		ResultSet rs = null;

		List<CouponCodeBean> list_coupon_codeBean = new ArrayList<CouponCodeBean>();

		try {
			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, password);

			pstmt = con.prepareStatement(SELECT);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				CCB = new CouponCodeBean();
				CCB.setCoupon_code_id(rs.getInt("coupon_code_id"));
				CCB.setCoupon_code(rs.getString("coupon_code"));
				CCB.setCoupon_code_effective_date(rs.getTimestamp("coupon_code_effective_date"));
				CCB.setCoupon_code_expire_date(rs.getTimestamp("coupon_code_expire_date"));
				CCB.setCoupon_code_text_content(rs.getString("coupon_code_text_content"));
				CCB.setCoupon_code_text(rs.getFloat("coupon_code_text"));
				CCB.setDiscount_type(rs.getInt("discount_type"));
				CCB.setEmployee_account(rs.getString("employee_account"));

				list_coupon_codeBean.add(CCB);
//				System.out.println(CCB + "\n");
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
		return list_coupon_codeBean;

	}

	public CouponCodeBean findByPrimaryKey(Integer coupon_code_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		SELECTPK = "SELECT * FROM coupon_code WHERE coupon_code_id = ?";

		ResultSet rs = null;

		CouponCodeBean CCB = null;

		try {
			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, password);

			pstmt = con.prepareStatement(SELECTPK);

			pstmt.setInt(1, coupon_code_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				CCB = new CouponCodeBean();
				CCB.setCoupon_code_id(rs.getInt("coupon_code_id"));
				CCB.setCoupon_code(rs.getString("coupon_code"));
				CCB.setCoupon_code_effective_date(rs.getTimestamp("coupon_code_effective_date"));
				CCB.setCoupon_code_expire_date(rs.getTimestamp("coupon_code_expire_date"));
				CCB.setCoupon_code_text_content(rs.getString("coupon_code_text_content"));
				CCB.setCoupon_code_text(rs.getFloat("coupon_code_text"));
				CCB.setDiscount_type(rs.getInt("discount_type"));
				CCB.setEmployee_account(rs.getString("employee_account"));

//				System.out.println(CCB);
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

	public static void main(String[] args) {

		CouponCodeDAO dao = new CouponCodeDAO();

		// -------------------------------------新增設定資料-------------------------------------//

//		CouponCodeBean CCB = new CouponCodeBean();
//
//		CCB.setCoupon_code("Kirito");
//
//		String times = "2021-02-28 02:22:22";
//		Timestamp tss = Timestamp.valueOf(times);
//
//		CCB.setCoupon_code_effective_date(tss);
//
//		String timese = "2021-03-02 02:22:22";
//		Timestamp tse = Timestamp.valueOf(timese);
//
//		CCB.setCoupon_code_expire_date(tse);
//
//		CCB.setCoupon_code_text_content("蛋糕要快,還要更快,蛋糕2折");
//
//		CCB.setCoupon_code_text(1f);
//
//		CCB.setDiscount_type(2);
//
//		CCB.setEmployee_account("peter");
//		
//		dao.insert(CCB);


		// -------------------------------------修改設定資料-------------------------------------//

//		CouponCodeBean CCB = new CouponCodeBean();
//
//		CCB.setCoupon_code("KiritoGG");
//
//		String times = "2021-03-01 03:33:33";
//		Timestamp tss = Timestamp.valueOf(times);
//
//		CCB.setCoupon_code_effective_date(tss);
//
//		String timese = "2021-03-03 03:33:33";
//		Timestamp tse = Timestamp.valueOf(timese);
//
//		CCB.setCoupon_code_expire_date(tse);
//
//		CCB.setCoupon_code_text_content("星爆都不星爆了,快不起來了,蛋糕9折");
//
//		CCB.setCoupon_code_text(1f);
//
//		CCB.setDiscount_type(2);
//
//		CCB.setEmployee_account("peter");
//
//		CCB.setCoupon_code_id(3);
//
//		dao.update(CCB);

		// --------------------------------------刪除資料-----------------------------------------//

//		dao.delete(3);

		// -------------------------------------查詢單一筆資料------------------------------------//

//		CouponCodeBean CCB = dao.findByPrimaryKey(2);
//		System.out.println(CCB);

		// -------------------------------------查詢整筆資料-------------------------------------//
		
//		List<CouponCodeBean> list = dao.getAll();
//		for (CouponCodeBean CCB : list) {
//			System.out.println(CCB);
//		}

	}
}
