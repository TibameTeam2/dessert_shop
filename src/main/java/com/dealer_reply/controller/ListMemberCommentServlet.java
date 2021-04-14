package com.dealer_reply.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dealer_reply.model.ListMemberCommentBean;
import com.dealer_reply.model.ListMemberCommentService;
import com.util.BaseServlet;
import com.util.ResultInfo;


public class ListMemberCommentServlet extends BaseServlet {
	
	public void getListMemberComment(HttpServletRequest req, HttpServletResponse res) {

		ListMemberCommentService service = new ListMemberCommentService();
		List<ListMemberCommentBean> list = service.findAllCommentContent();
		System.out.println("List<ListMemberCommentBean> list = " + list);

		ResultInfo info = new ResultInfo();
		info.setFlag(true);
		info.setData(list);
		info.setMsg("ListMemberComment: 在後台顯示所有顧客評論的資料");
		writeValueByStream(res, info); // 把info這個物件, 轉成JSON之後寫回給前端

	}

}
