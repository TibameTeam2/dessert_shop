package com.announcement_management.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.product.model.ProductBean;
import com.product.model.ProductDAO;
import com.util.JDBCUtil;

public class AnnouncementManagementDAO implements AnnouncementManagementDAO_interface{

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

//		INSERT = "INSERT INTO announcement_management(announcement_name,announcement_content,\r\n"
//				+ "announcement_image,announcement_time,announcement_type,announcement_status,employee_account) "
//				+ "VALUES(?,?,?,?,?,?,?)";
//
//		UPDATE = "UPDATE announcement_management set announcement_name = ?,announcement_content = ?,"
//				+ "announcement_image = ?,announcement_time = ?,announcement_type = ?,"
//				+ "announcement_status = ?,employee_account = ? WHERE announcement_id = ?";
//
//		DELETE = "DELETE FROM announcement_management WHERE announcement_id = ?";
//
//		SELECT = "SELECT * FROM announcement_management";
//
//		SELECTPK = "SELECT * FROM announcement_management WHERE announcement_id = ?";

	}

	public void insert(AnnouncementManagementBean AMB) {

		Connection con = null;
		PreparedStatement pstmt = null;

		INSERT = "INSERT INTO announcement_management(announcement_name,announcement_content,\r\n"
				+ "announcement_image,announcement_time,announcement_type,announcement_status,employee_account) "
				+ "VALUES(?,?,?,?,?,?,?)";

		try {

			// 載入驅動

			Class.forName(driver);

			// 建立連線

			con = DriverManager.getConnection(url, userid, password);

			// 準備送出sql指令

			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, AMB.getAnnouncement_name());
			pstmt.setString(2, AMB.getAnnouncement_content());
			pstmt.setBytes(3, AMB.getAnnouncement_image());
			pstmt.setTimestamp(4, AMB.getAnnouncement_time());
			pstmt.setInt(5, AMB.getAnnouncement_type());
			pstmt.setInt(6, AMB.getAnnouncement_status());
			pstmt.setString(7, AMB.getEmployee_account());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
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

	public void update(AnnouncementManagementBean AMB) {

		Connection con = null;
		PreparedStatement pstmt = null;

		UPDATE = "UPDATE announcement_management set announcement_name = ?,announcement_content = ?,"
				+ "announcement_image = ?,announcement_time = ?,announcement_type = ?,"
				+ "announcement_status = ?,employee_account = ? WHERE announcement_id = ?";

		try {

			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, password);

			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, AMB.getAnnouncement_name());
			pstmt.setString(2, AMB.getAnnouncement_content());
			pstmt.setBytes(3, AMB.getAnnouncement_image());
			pstmt.setTimestamp(4, AMB.getAnnouncement_time());
			pstmt.setInt(5, AMB.getAnnouncement_type());
			pstmt.setInt(6, AMB.getAnnouncement_status());
			pstmt.setString(7, AMB.getEmployee_account());
			pstmt.setInt(8, AMB.getAnnouncement_id());

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

	public void delete(Integer announcement_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		DELETE = "DELETE FROM announcement_management WHERE announcement_id = ?";

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, announcement_id);
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

	public List<AnnouncementManagementBean> getAll() {

		Connection con = null;
		PreparedStatement pstmt = null;

		SELECT = "SELECT * FROM announcement_management";

		AnnouncementManagementBean AMB;
		ResultSet rs = null;

		List<AnnouncementManagementBean> list_announcement_managementBean = new ArrayList<AnnouncementManagementBean>();

		try {
			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, password);

			pstmt = con.prepareStatement(SELECT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				AMB = new AnnouncementManagementBean();
				AMB.setAnnouncement_id(rs.getInt("announcement_id"));
				AMB.setAnnouncement_name(rs.getString("announcement_name"));
				AMB.setAnnouncement_content(rs.getString("announcement_content"));
				AMB.setAnnouncement_image(rs.getBytes("announcement_image"));
				AMB.setAnnouncement_time(rs.getTimestamp("announcement_time"));
				AMB.setAnnouncement_type(rs.getInt("announcement_type"));
				AMB.setAnnouncement_status(rs.getInt("announcement_status"));
				AMB.setEmployee_account(rs.getString("employee_account"));

				list_announcement_managementBean.add(AMB);
//				System.out.println(AMB + "\n");
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
		return list_announcement_managementBean;
	}

	public AnnouncementManagementBean findByPrimaryKey(Integer announcement_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		SELECTPK = "SELECT * FROM announcement_management WHERE announcement_id = ?";

		ResultSet rs = null;

		AnnouncementManagementBean AMB = null;

		try {
			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, password);

			pstmt = con.prepareStatement(SELECTPK);

			pstmt.setInt(1, announcement_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				AMB = new AnnouncementManagementBean();
				AMB.setAnnouncement_id(rs.getInt("announcement_id"));
				AMB.setAnnouncement_name(rs.getString("announcement_name"));
				AMB.setAnnouncement_content(rs.getString("announcement_content"));
				AMB.setAnnouncement_image(rs.getBytes("announcement_image"));
				AMB.setAnnouncement_time(rs.getTimestamp("announcement_time"));
				AMB.setAnnouncement_type(rs.getInt("announcement_type"));
				AMB.setAnnouncement_status(rs.getInt("announcement_status"));
				AMB.setEmployee_account(rs.getString("employee_account"));

//				System.out.println(AMB);
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
		return AMB;
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

	public static void main(String[] args) {
		AnnouncementManagementDAO dao = new AnnouncementManagementDAO();

		// -------------------------------------新增設定資料-------------------------------------//

//		AnnouncementManagementBean AMB = new AnnouncementManagementBean();
//
//		AMB.setAnnouncement_name("NG千百層蛋糕");
//		AMB.setAnnouncement_content("沒有千層只有百曾或十層");
//
//		byte[] pic;
//		try {
//			pic = getPictureByteArray("C:/project/images/announcement_management/d.png");
//
//			AMB.setAnnouncement_image(pic);
//
//		} catch (IOException e2) {
//			e2.printStackTrace();
//		}
//
//		String time = "2021-02-27 15:00:00";
//		Timestamp ts = Timestamp.valueOf(time);
//		
//		AMB.setAnnouncement_time(ts);
//		AMB.setAnnouncement_type(1);
//		AMB.setAnnouncement_status(1);
//		AMB.setEmployee_account("peter");
//		dao.insert(AMB);

		// -------------------------------------修改設定資料-------------------------------------//

//		AnnouncementManagementBean AMB = new AnnouncementManagementBean();
//
//		AMB.setAnnouncement_name("NG泡芙");
//		AMB.setAnnouncement_content("沒有千層只有泡芙");
//		AMB.setAnnouncement_image(null); //把圖片拿下來測試
//
//		String time = "2021-02-27 23:00:00";
//		Timestamp ts = Timestamp.valueOf(time);
//
//		AMB.setAnnouncement_time(ts);
//		AMB.setAnnouncement_type(1);
//		AMB.setAnnouncement_status(1);
//		AMB.setEmployee_account("peter");
//		AMB.setAnnouncement_id(3);
//      dao.update(AMB);

		// --------------------------------------刪除資料-----------------------------------------//

//       dao.delete(3);

		// -------------------------------------查詢單一筆資料------------------------------------//

		AnnouncementManagementBean AMB = dao.findByPrimaryKey(2);
		System.out.println(AMB);

		// -------------------------------------查詢整筆資料-------------------------------------//

//		List<AnnouncementManagementBean> list = dao.getAll();
//		for (AnnouncementManagementBean AMB : list) {
//			System.out.println(AMB);
//		}

	}

}
