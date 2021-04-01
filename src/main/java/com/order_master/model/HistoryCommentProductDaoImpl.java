package com.order_master.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.util.JDBCUtil;

public class HistoryCommentProductDaoImpl implements HistoryCommentProductDao {
	
	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;
	
	private static final String HAHAHA = "select order_detail_id, product_id, mc.review_id, rating, comment_content, review_image_id, review_image, reply_id, reply_content\r\n" + 
			"from member_comment mc\r\n" + 
			"join review_image_upload riu on mc.review_id = riu.review_id\r\n" + 
			"join dealer_reply dr on mc.review_id = dr.review_id\r\n" + 
			"where order_detail_id = ?\r\n" + 
			"order by order_detail_id;";

//	@Override
//	public void insert(HistoryCommentProductBean historyCommentProductBean) {
//	}
//
//	@Override
//	public void update(HistoryCommentProductBean historyCommentProductBean) {
//	}
//
//	@Override
//	public void delete(Integer order_detail_id) {
//	}

	
	/*
	 * 撈出order_detail_id=1 的 product_id們(撈子選單:
	 * 使用HistoryCommentProductBean) 依product_id續撈review_id等
	 */
	
	@Override
	public HistoryCommentProductBean findByOrderDetailId(Integer order_detail_id) {
		
		HistoryCommentProductBean hcpBean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(HAHAHA);

		}
		
		return hcpBean;
	}

//	@Override
//	public List<HistoryCommentProductBean> getAll() {
//		return null;
//	}

}
