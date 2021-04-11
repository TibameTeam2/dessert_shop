package com.coupon.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.coupon.model.CouponBean;
import com.coupon.model.CouponService;

public class CouponServletJsp extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer id = new Integer(req.getParameter("coupon_id"));
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/coupon_jsp/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer coupon_id = null;
				try {
					coupon_id = new Integer(id);
				} catch (Exception e) {
					errorMsgs.add("流水號格式不正確");
				}
			
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/coupon_jsp/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				CouponService cSvc = new CouponService();
				CouponBean cBean = cSvc.getOneCoupon(coupon_id);
				System.out.println("controller 1 :" + cBean);
				
				if (cBean == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/coupon_jsp/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				
				System.out.println("controller 2 :" + cBean);
				
				req.setAttribute("cBean", cBean); 
				
				System.out.println("controller 3 :" + cBean);
				
				String url = "/coupon_jsp/listOneCoupon.jsp";
				
				System.out.println("controller 4 :" + cBean);
				
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				
				System.out.println("controller 5 :" + cBean);
				
				successView.forward(req, res);
				
				System.out.println("controller 6 :" + cBean);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/coupon_jsp/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
//-----------------------------------------------
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer coupon_id = new Integer(req.getParameter("coupon_id"));
				
				/***************************2.開始查詢資料****************************************/
				CouponService cSvc = new CouponService();
				CouponBean cBean = cSvc.getOneCoupon(coupon_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("cBean", cBean);         
				String url = "/coupon_jsp/update_coupon_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/coupon_jsp/listAllCoupon.jsp");
				failureView.forward(req, res);
			}
		}
		
//-----------------------------------------------		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer coupon_id = new Integer(req.getParameter("coupon_id"));
				
				System.out.println(coupon_id + "id");
				
				String member_account = req.getParameter("member_account").trim();
				if (member_account == null || member_account.trim().length() == 0) {
					errorMsgs.add("會員名稱請勿空白");
				}
				
				java.sql.Timestamp coupon_sending_time = null;
				try {
					coupon_sending_time = java.sql.Timestamp.valueOf(req.getParameter("coupon_sending_time").trim());
				} catch (IllegalArgumentException e) {
					coupon_sending_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Timestamp coupon_effective_date = null;
				try {
					coupon_effective_date = java.sql.Timestamp.valueOf(req.getParameter("coupon_effective_date").trim());
				} catch (IllegalArgumentException e) {
					coupon_effective_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Timestamp coupon_expire_date = null;
				try {
					coupon_expire_date = java.sql.Timestamp.valueOf(req.getParameter("coupon_expire_date").trim());
				} catch (IllegalArgumentException e) {
					coupon_expire_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String coupon_text_content = req.getParameter("coupon_text_content").trim();
				if (coupon_text_content == null || coupon_text_content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				
				Float coupon_content = null;
				try {
					coupon_content = new Float(req.getParameter("coupon_content").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("內容請填數字.");
				}
				
				Integer discount_type = null;
				try {
					discount_type = new Integer(req.getParameter("discount_type").trim());
				} catch (NumberFormatException e) {
					discount_type = 0;
					errorMsgs.add("折價種類請填數字.");
				}
				
				Integer coupon_status = null;
				try {
					coupon_status = new Integer(req.getParameter("coupon_status").trim());
				} catch (NumberFormatException e) {
					coupon_status = 0;
					errorMsgs.add("優惠券狀態填數字.");
				}
				
				String employee_account = req.getParameter("employee_account");
				
				try {
					employee_account = new String(req.getParameter("employee_account").trim());
				} catch (Exception e) {
					System.out.println(e);
					employee_account = "";
					errorMsgs.add("請填寫員工姓名.");
				}
				
				Integer coupon_code_id = null;
				try {
					coupon_code_id = new Integer(req.getParameter("coupon_code_id").trim());
				} catch (NumberFormatException e) {
					coupon_code_id = 0;
					errorMsgs.add("優惠碼流水號填數字.");
				}

				CouponBean cBean = new CouponBean();

				cBean.setCoupon_id(coupon_id);
				cBean.setMember_account(member_account);
				cBean.setCoupon_sending_time(coupon_sending_time);
				cBean.setCoupon_effective_date(coupon_effective_date);
				cBean.setCoupon_expire_date(coupon_expire_date);
				cBean.setCoupon_text_content(coupon_text_content);
				cBean.setCoupon_content(coupon_content);
				cBean.setDiscount_type(discount_type);
				cBean.setCoupon_status(coupon_status);
				cBean.setEmployee_account(employee_account);
				cBean.setCoupon_code_id(coupon_code_id);

				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cBean", cBean);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/coupon_jsp/update_coupon_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.開始修改資料*****************************************/
				CouponService cSvc = new CouponService();
			
				cBean = cSvc.updateCoupon( member_account, coupon_sending_time, coupon_effective_date, coupon_expire_date, coupon_text_content, coupon_content, discount_type,coupon_status,employee_account,coupon_code_id ,coupon_id);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("cBean", cBean); 
				String url = "/coupon_jsp/listOneCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/coupon_jsp/update_coupon_input.jsp");
				failureView.forward(req, res);
			}
		}
		
//-----------------------------------------------	

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				System.out.println("我有進來嗎?");
				
				String member_account = req.getParameter("member_account").trim();
				if (member_account == null || member_account.trim().length() == 0) {
					errorMsgs.add("會員名稱請勿空白");
				}
				
				java.sql.Timestamp coupon_sending_time = null;
				try {
					coupon_sending_time = java.sql.Timestamp.valueOf(req.getParameter("coupon_sending_time").trim());
				} catch (IllegalArgumentException e) {
					coupon_sending_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Timestamp coupon_effective_date = null;
				try {
					coupon_effective_date = java.sql.Timestamp.valueOf(req.getParameter("coupon_effective_date").trim());
				} catch (IllegalArgumentException e) {
					coupon_effective_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Timestamp coupon_expire_date = null;
				try {
					coupon_expire_date = java.sql.Timestamp.valueOf(req.getParameter("coupon_expire_date").trim());
				} catch (IllegalArgumentException e) {
					coupon_expire_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String coupon_text_content = req.getParameter("coupon_text_content").trim();
				if (coupon_text_content == null || coupon_text_content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				
				Float coupon_content = null;
				try {
					coupon_content = new Float(req.getParameter("coupon_content").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("內容請填數字.");
				}
				
				Integer discount_type = null;
				try {
					discount_type = new Integer(req.getParameter("discount_type").trim());
				} catch (NumberFormatException e) {
					discount_type = 0;
					errorMsgs.add("折價種類請填數字.");
				}
				
				Integer coupon_status = null;
				try {
					coupon_status = new Integer(req.getParameter("coupon_status").trim());
				} catch (NumberFormatException e) {
					coupon_status = 0;
					errorMsgs.add("優惠券狀態填數字.");
				}
				
				String employee_account = req.getParameter("employee_account");
				
				try {
					employee_account = new String(req.getParameter("employee_account").trim());
				} catch (Exception e) {
					System.out.println(e);
					employee_account = "";
					errorMsgs.add("請填寫員工姓名.");
				}
				
				Integer coupon_code_id = null;
				try {
					coupon_code_id = new Integer(req.getParameter("coupon_code_id").trim());
				} catch (NumberFormatException e) {
					coupon_code_id = 0;
					errorMsgs.add("優惠碼流水號填數字.");
				}

				CouponBean cBean = new CouponBean();

				cBean.setMember_account(member_account);
				cBean.setCoupon_sending_time(coupon_sending_time);
				cBean.setCoupon_effective_date(coupon_effective_date);
				cBean.setCoupon_expire_date(coupon_expire_date);
				cBean.setCoupon_text_content(coupon_text_content);
				cBean.setCoupon_content(coupon_content);
				cBean.setDiscount_type(discount_type);
				cBean.setCoupon_status(coupon_status);
				cBean.setEmployee_account(employee_account);
				cBean.setCoupon_code_id(coupon_code_id);
				
		
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cBean", cBean); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/coupon_jsp/addCoupon.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				
				/***************************2.開始新增資料***************************************/
				
				CouponService cSvc = new CouponService();
				
				cBean = cSvc.addCoupon(member_account, coupon_sending_time, coupon_effective_date, coupon_expire_date, coupon_text_content, coupon_content, discount_type,coupon_status,employee_account,coupon_code_id);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/coupon_jsp/listAllCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/coupon_jsp/addCoupon.jsp");
				failureView.forward(req, res);
			}
		}
		
//-----------------------------------------------			
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer coupon_id = new Integer(req.getParameter("coupon_id"));
				
				/***************************2.開始刪除資料***************************************/
				CouponService cSvc = new CouponService();
				cSvc.deleteCoupon(coupon_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/coupon_jsp/listAllCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/coupon_jsp/listAllCoupon.jsp");
				failureView.forward(req, res);
			}
		}
	}
	

}
