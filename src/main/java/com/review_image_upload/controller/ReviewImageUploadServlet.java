package com.review_image_upload.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order_master.model.HistoryCommentProductBean;
import com.order_master.model.HistoryCommentProductService;
import com.review_image_upload.model.ReviewImageUploadBean;
import com.util.BaseServlet;
import com.util.JDBCUtil;
import com.util.ResultInfo;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class ReviewImageUploadServlet extends BaseServlet {

	public void test(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("hello");
	}

	//用在
	//在web.xml註冊/reviewImageUpload/*的網址會來到這支Servlet
	//http://localhost:8081/dessert_shop/reviewImageUpload/getReviewImage?review_image_id=1
	public void getReviewImage(HttpServletRequest req, HttpServletResponse res) {

		String review_image_id = req.getParameter("review_image_id").trim();
		System.out.println("review_id = " + review_image_id);

		res.setContentType("image/png");
		Connection con = null;
		String driver = JDBCUtil.driver;
		String url = JDBCUtil.url;
		String userid = JDBCUtil.user;
		String passwd = JDBCUtil.password;

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			//SQL語句 : 欲憑一個review_image_id查詢一筆review_image_id
			pstmt = con.prepareStatement("SELECT review_image FROM sweet.review_image_upload where review_image_id=?");

			pstmt.setString(1, review_image_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
//            BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("member_photo"));
				IoUtil.write(res.getOutputStream(), true, IoUtil.readBytes(rs.getBinaryStream("review_image"), true));
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	
	
	
	
	
	
	
	

}
