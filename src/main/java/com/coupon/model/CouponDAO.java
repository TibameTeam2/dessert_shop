package com.coupon.model;

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

public class CouponDAO {

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
//		INSERT = "INSERT INTO coupon(member_account,coupon_sending_time,\r\n"
//				+ "coupon_effective_date,coupon_expire_date,coupon_text_content,coupon_content,"
//				+ "discount_type,coupon_status,employee_account,coupon_code_id) " + "VALUES(?,?,?,?,?,?,?,?,?,?)";
//
//		UPDATE = "UPDATE coupon set member_account = ?,coupon_sending_time = ?,"
//				+ "coupon_effective_date = ?,coupon_expire_date = ?,coupon_text_content = ?,"
//				+ "coupon_content = ?,discount_type = ?,coupon_status = ?,employee_account = ?,coupon_code_id = ?"
//				+ " WHERE coupon_id = ?";
//
//		DELETE = "DELETE FROM coupon WHERE coupon_id = ?";
//
//		SELECT = "SELECT * FROM coupon";
//
//		SELECTPK = "SELECT * FROM coupon WHERE coupon_id = ?";

	}

	public void insert(CouponBean CB) {

		Connection con = null;
		PreparedStatement pstmt = null;

		INSERT = "INSERT INTO coupon(member_account,coupon_sending_time,\r\n"
				+ "coupon_effective_date,coupon_expire_date,coupon_text_content,coupon_content,"
				+ "discount_type,coupon_status,employee_account,coupon_code_id) " + "VALUES(?,?,?,?,?,?,?,?,?,?)";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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

	public void update(CouponBean CB) {

		Connection con = null;
		PreparedStatement pstmt = null;

		UPDATE = "UPDATE coupon set member_account = ?,coupon_sending_time = ?,"
				+ "coupon_effective_date = ?,coupon_expire_date = ?,coupon_text_content = ?,"
				+ "coupon_content = ?,discount_type = ?,coupon_status = ?,employee_account = ?,coupon_code_id = ?"
				+ " WHERE coupon_id = ?";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE);

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
			pstmt.setInt(11, CB.getCoupon_id());

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

	public void delete(Integer coupon_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		DELETE = "DELETE FROM coupon WHERE coupon_id = ?";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, coupon_id);
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

	public List<CouponBean> getAll() {

		Connection con = null;
		PreparedStatement pstmt = null;

		SELECT = "SELECT * FROM coupon";

		CouponBean CB;
		ResultSet rs = null;

		List<CouponBean> list_couponBean = new ArrayList<CouponBean>();

		try {
			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, password);

			pstmt = con.prepareStatement(SELECT);

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
//				System.out.println(CB + "\n");
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

	public CouponBean findByPrimaryKey(Integer coupon_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		SELECTPK = "SELECT * FROM coupon WHERE coupon_id = ?";

		ResultSet rs = null;

		CouponBean CB = null;

		try {
			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, password);

			pstmt = con.prepareStatement(SELECTPK);

			pstmt.setInt(1, coupon_id);

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

//				System.out.println(CB);
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

	public static void main(String[] args) {

		CouponDAO dao = new CouponDAO();

		// -------------------------------------新增設定資料-------------------------------------//

//		CouponBean CB = new CouponBean();
//
//		CB.setMember_account("amy");
//
//		String times = "2021-02-26 11:11:11";
//		Timestamp tss = Timestamp.valueOf(times);
//
//		CB.setCoupon_sending_time(tss);
//
//		String timess = "2021-02-27 12:12:12";
//		Timestamp tse = Timestamp.valueOf(timess);
//
//		CB.setCoupon_effective_date(tse);
//
//		String timesss = "2021-02-28 13:13:13";
//		Timestamp tsed = Timestamp.valueOf(timesss);
//
//		CB.setCoupon_expire_date(tsed);
//
//		CB.setCoupon_text_content("月底即期出清");
//		CB.setCoupon_content(1f);
//		CB.setDiscount_type(1);
//		CB.setCoupon_status(0);
//		CB.setEmployee_account("james");
//		CB.setCoupon_code_id(1);
//
//		dao.insert(CB);

		// -------------------------------------修改設定資料-------------------------------------//

//		CouponBean CB = new CouponBean();
//
//		CB.setMember_account("tom");
//
//		String times = "2021-02-26 11:11:11";
//		Timestamp tss = Timestamp.valueOf(times);
//
//		CB.setCoupon_sending_time(tss);
//
//		String timess = "2021-02-27 12:12:12";
//		Timestamp tse = Timestamp.valueOf(timess);
//
//		CB.setCoupon_effective_date(tse);
//
//		String timesss = "2021-02-28 14:14:14";
//		Timestamp tsed = Timestamp.valueOf(timesss);
//
//		CB.setCoupon_expire_date(tsed);
//
//		CB.setCoupon_text_content("月光族的月底大出清");
//		CB.setCoupon_content(1f);
//		CB.setDiscount_type(1);
//		CB.setCoupon_status(0);
//		CB.setEmployee_account("james");
//		CB.setCoupon_code_id(1);
//		CB.setCoupon_id(3);
//		
//		dao.update(CB);

		// --------------------------------------刪除資料-----------------------------------------//

//		dao.delete(3);

		// -------------------------------------查詢單一筆資料------------------------------------//

		CouponBean CB = dao.findByPrimaryKey(2);
		System.out.println(CB);

		// -------------------------------------查詢整筆資料-------------------------------------//

//		List<CouponBean> list = dao.getAll();
//		for (CouponBean CB : list) {
//			System.out.println(CB);
//		}
	}
}
