package com.member_comment.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.member.model.MemberBean;
import com.member.model.MemberService;
import com.member_comment.model.MemberCommentBean;
import com.member_comment.model.MemberCommentService;
import com.product.model.ProductService;
import com.review_image_upload.model.ReviewImageUploadBean;
import com.review_image_upload.model.ReviewImageUploadService;
import com.util.BaseServlet;
import com.util.ResultInfo;

import cn.hutool.core.convert.Convert;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class MemberCommentServlet extends BaseServlet {

	MemberCommentService memberCommentSvc = new MemberCommentService();

	public void test(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("hello");
	}

	// 接收前台送來的資料, 用在使用者填完尚未評價後, 將評價資料進資料庫
	public void addMemberComment(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String order_detail_id = req.getParameter("OrderDetailId");
		String product_id = req.getParameter("ProductId");
		String rating = req.getParameter("Rating");
		String comment_content = req.getParameter("CommentContent");

		
		Integer review_id = memberCommentSvc.addMemberComment(Convert.toInt(order_detail_id), Convert.toInt(product_id),
				Convert.toInt(rating), comment_content, 0);

		
		// 用getPart()抓前台送來的照片
		Part part1 = req.getPart("upload1");
		Part part2 = req.getPart("upload2");
		Part part3 = req.getPart("upload3");

		// getInputStream(): Return the content of this part as an InputStream
		InputStream in1 = part1.getInputStream();
		InputStream in2 = part2.getInputStream();
		InputStream in3 = part3.getInputStream();
		
		// 抽象父類別InputStream 類別提供int available()方法，以取得輸入資料流的資料大小(number of bytes)
		byte[] buf1 = new byte[in1.available()];
		byte[] buf2 = new byte[in2.available()];
		byte[] buf3 = new byte[in3.available()];
		
		// int read() 回傳值為檔案裡下一個byte資料，如回傳-1 代表已到檔案末端
		in1.read(buf1);
		in2.read(buf2);
		in3.read(buf3);
		
		System.out.println("buf1.length = " + buf1.length);
		System.out.println("buf2.length = " + buf2.length);
		System.out.println("buf3.length = " + buf3.length);
		
		in1.close();
		in2.close();
		in3.close();
		
		// addReviewImageUpload()將評論照片寫進資料庫
        if (buf1.length != 0) {
        	ReviewImageUploadService riuSvc = new ReviewImageUploadService(); 
        	riuSvc.addReviewImageUpload(buf1, review_id);
        }
        if (buf2.length != 0) {
        	ReviewImageUploadService riuSvc = new ReviewImageUploadService();
        	riuSvc.addReviewImageUpload(buf2, review_id);
        }
        if (buf3.length != 0) {
        	ReviewImageUploadService riuSvc = new ReviewImageUploadService();
        	riuSvc.addReviewImageUpload(buf3, review_id);
        }
        

		ResultInfo info = new ResultInfo();

		if (review_id != 0) {
			info.setFlag(true);
			info.setMsg("成功新增!!!");
			info.setRedirect(req.getContextPath() + req.getServletPath() + "/member-comment.html"); 
			System.out.println("成功的跳轉");
		} else {
			info.setFlag(false);
			info.setMsg("插入失敗");
			info.setRedirect(req.getContextPath() + req.getServletPath() + "/member-comment.html");
			System.out.println("失敗的跳轉");
		}
		writeValueByWriter(res, info);
		
		ProductService productSvc = new ProductService();
		productSvc.addReviewStar(product_id, rating);

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
