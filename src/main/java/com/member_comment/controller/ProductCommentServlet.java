package com.member_comment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member_comment.model.ProductCommentBean;
import com.member_comment.model.ProductCommentService;
import com.product.model.ProductBean;
import com.util.BaseServlet;
import com.util.ResultInfo;

import cn.hutool.core.convert.Convert;

public class ProductCommentServlet extends BaseServlet {
       
	
	public void getProductComment(HttpServletRequest req, HttpServletResponse res) {
		
		String productId = req.getParameter("productId");
		ProductCommentService productCommentSvc = new ProductCommentService();
		List<ProductCommentBean> pcBlist = productCommentSvc.findByProductId(Convert.toInt(productId));
		
		ResultInfo info = new ResultInfo();
		info.setFlag(true);
		info.setData(pcBlist);
		info.setMsg("productId = " + productId + "    商品的評論資料");
		writeValueByStream(res, info); // 把info這個物件, 轉成JSON之後寫回給前端
	}

}
