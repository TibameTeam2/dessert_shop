package com.member_comment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member_comment.model.MemberCommentBean;
import com.member_comment.model.MemberCommentService;
import com.util.BaseServlet;
import com.util.ResultInfo;

import cn.hutool.core.convert.Convert;

public class MemberCommentServlet extends BaseServlet {

	MemberCommentService memberCommentSvc = new MemberCommentService();

	public void test(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("hello");
	}

	//用在使用者填完尚未評價後、評價資料進資料庫
	public void addMemberComment(HttpServletRequest req, HttpServletResponse res) {
		String order_detail_id = req.getParameter("OrderDetailId");
		String product_id = req.getParameter("ProductId");
		String rating = req.getParameter("Rating");
		String comment_content = req.getParameter("CommentContent");

		/*
		 * if (rating == null) { // 確認送出按鈕不能按 // 跳出「請評價星等」提醒 } else { // 確認送出按鈕可以按 }
		 */

		Boolean flag = memberCommentSvc.addMemberComment(Convert.toInt(order_detail_id), Convert.toInt(product_id),
				Convert.toInt(rating), comment_content, 1);

		ResultInfo info = new ResultInfo();

		if (flag == true) {
			info.setFlag(true);
			info.setMsg("成功新增!!!"); 
			info.setRedirect("/dessert_shop/TEA103G2/front-end/member-comment.html"); // 請記得確認正確網址
		} else {
			info.setFlag(false);
			info.setMsg("插入失敗");
			info.setRedirect("/dessert_shop/TEA103G2/front-end/member-comment.html"); // 請記得確認正確網址
		}
		writeValueByWriter(res, info);

	}

	// 用在使用者送出已填好的尚未評價資料, 查找資料庫自增的review_id
	public void getReviewIdByOrderDetaiId(HttpServletRequest req, HttpServletResponse res) {
		String order_detail_id = req.getParameter("orderDetailId");
		MemberCommentBean memberCommentBean = memberCommentSvc
				.findReviewIdByOrderDetailId(Convert.toInt(order_detail_id));

		ResultInfo info = new ResultInfo();
		info.setFlag(true);
		info.setData(memberCommentBean);
		info.setMsg("orderDetailId = " + order_detail_id + "的 review_id = " + memberCommentBean.getReview_id());

	}
}
