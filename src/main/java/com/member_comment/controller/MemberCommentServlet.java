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

	// 接收前台送來的資料, 用在使用者填完尚未評價後、評價資料進資料庫
	public void addMemberComment(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String order_detail_id = req.getParameter("OrderDetailId");
		String product_id = req.getParameter("ProductId");
		String rating = req.getParameter("Rating");
		String comment_content = req.getParameter("CommentContent");

		
		Integer review_id = memberCommentSvc.addMemberComment(Convert.toInt(order_detail_id), Convert.toInt(product_id),
				Convert.toInt(rating), comment_content, 1);

		
		
		Part part1 = req.getPart("upload1");
		Part part2 = req.getPart("upload2");
		Part part3 = req.getPart("upload3");
//      String dir = getServletContext().getRealPath("/images_uploaded");
//  	String filename = getFileNameFromPart(part);
//  	System.out.println("filename = " + filename);
		InputStream in1 = part1.getInputStream();
		InputStream in2 = part2.getInputStream();
		InputStream in3 = part3.getInputStream();
		byte[] buf1 = new byte[in1.available()];
		byte[] buf2 = new byte[in2.available()];
		byte[] buf3 = new byte[in3.available()];
		in1.read(buf1);
		in2.read(buf2);
		in3.read(buf3);
		in1.close();
		in2.close();
		in3.close();
		
//		ReviewImageUploadBean reviewImageUploadBean = new ReviewImageUploadBean();
		
//		reviewImageUploadBean.setReview_image(buf1);
//		reviewImageUploadBean.setReview_image(buf2);
//		reviewImageUploadBean.setReview_image(buf3);
		
        if (buf1.length != 0) {
        	ReviewImageUploadService riuSvc = new ReviewImageUploadService();
        	ReviewImageUploadBean reviewImageUploadBean1 = riuSvc.addReviewImageUpload(buf1,review_id);
        }
        if (buf2.length != 0) {
        	ReviewImageUploadService riuSvc = new ReviewImageUploadService();
        	ReviewImageUploadBean reviewImageUploadBean2 = riuSvc.addReviewImageUpload(buf2,review_id);
        }
        if (buf3.length != 0) {
        	ReviewImageUploadService riuSvc = new ReviewImageUploadService();
        	ReviewImageUploadBean reviewImageUploadBean3 = riuSvc.addReviewImageUpload(buf3,review_id);
        }
        

		ResultInfo info = new ResultInfo();

		if (review_id != 0) {
			System.out.println("成功");
			info.setFlag(true);
			info.setMsg("成功新增!!!");
			info.setRedirect("/dessert_shop/TEA103G2/front-end/member-comment.html"); // 請記得確認正確網址
		} else {
			System.out.println("失敗");
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
