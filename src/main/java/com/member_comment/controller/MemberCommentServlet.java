package com.member_comment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member_comment.model.MemberCommentService;
import com.util.BaseServlet;
import com.util.ResultInfo;

import cn.hutool.core.convert.Convert;

public class MemberCommentServlet extends BaseServlet {

	MemberCommentService memberCommentSvc = new MemberCommentService();

	public void test(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("hello");
	}

	public void addMemberComment(HttpServletRequest req, HttpServletResponse res) {

		String order_detail_id = req.getParameter("OrderDetailId");
		String product_id = req.getParameter("ProductId");
		String rating = req.getParameter("Rating");
		String comment_content = req.getParameter("CommentContent");

		if (rating == null) {
			// 確認送出按鈕不能按
			// 跳出「請評價星等」提醒
		} else {
			// 確認送出按鈕可以按
		}

		memberCommentSvc.addMemberComment(Convert.toInt(order_detail_id), Convert.toInt(product_id),
				Convert.toInt(rating), comment_content, 1);
		

		ResultInfo info = new ResultInfo();
		info.setFlag(true);
		info.setMsg("快看資料庫有沒有成功新增!!!");
		info.setRedirect("/dessert_shop/TEA103G2/front-end/member-comment.html");
		
	}

}
