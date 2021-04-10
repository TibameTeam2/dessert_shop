package com.coupon_code.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coupon_code.model.CouponCodeBean;
import com.coupon_code.model.CouponCodeService;

public class CouponCodeServletJsp extends HttpServlet {
	
public void doGet(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer id = new Integer(req.getParameter("coupon_code_id").trim()); 
		
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/coupon_code_jsp/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer coupon_code_id = null;
				try {
					coupon_code_id = new Integer(id);
				} catch (Exception e) {
					errorMsgs.add("優惠碼格式不正確");
				}
		
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/coupon_code_jsp/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				CouponCodeService ccSvc = new CouponCodeService();
				CouponCodeBean CCB = ccSvc.getOneCC(coupon_code_id);
				System.out.println("controller:"+CCB);
				
				if (coupon_code_id == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/coupon_code_jsp/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("CCB", CCB); 
				String url = "/coupon_code_jsp/listOneCc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/coupon_code_jsp/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer coupon_code_id = new Integer(req.getParameter("coupon_code_id"));
				
				/***************************2.開始查詢資料****************************************/
				CouponCodeService ccSvc = new CouponCodeService();
				
				CouponCodeBean CCB = ccSvc.getOneCC(coupon_code_id);
				
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("CCB", CCB);         
				String url = "/coupon_code_jsp/update_cc_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/coupon_code_jsp/listAllCc.jsp");
				failureView.forward(req, res);
			}
		}
		
		
if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				Integer coupon_code_id = new Integer(req.getParameter("coupon_code_id").trim());
				System.out.println(coupon_code_id+"id");
				 
				String coupon_code = req.getParameter("coupon_code");

				if (coupon_code == null || coupon_code.trim().length() == 0) {
					errorMsgs.add("優惠碼名稱: 請勿空白");
				} 
				
				java.sql.Timestamp coupon_code_effective_date = null;
				try {
					coupon_code_effective_date = java.sql.Timestamp.valueOf(req.getParameter("coupon_code_effective_date").trim());
				} catch (IllegalArgumentException e) {
					coupon_code_effective_date=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				
				java.sql.Timestamp coupon_code_expire_date = null;
				try {
					coupon_code_expire_date = java.sql.Timestamp.valueOf(req.getParameter("coupon_code_expire_date").trim());
				} catch (IllegalArgumentException e) {
					coupon_code_expire_date=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String coupon_code_text_content = req.getParameter("coupon_code_text_content").trim();
				if (coupon_code_text_content == null || coupon_code_text_content.trim().length() == 0) {
					errorMsgs.add("優惠碼文字內容請勿空白");
				}
				

				Float coupon_code_text = null;
				try {
					coupon_code_text = new Float(req.getParameter("coupon_code_text").trim());
				} catch (NumberFormatException e) {
					coupon_code_text = 0f;
					errorMsgs.add("優惠碼請填數字 + f");
				}
				
				Integer discount_type = null;
				try {
					discount_type = new Integer(req.getParameter("discount_type").trim());
				} catch (NumberFormatException e) {
					discount_type = 0;
					errorMsgs.add("優惠方式請填數字.");
				}
				
				String employee_account = req.getParameter("employee_account");
			
				try {
					employee_account = new String(req.getParameter("employee_account").trim());
				} catch (Exception e) {
					System.out.println(e);
					employee_account = "";
					errorMsgs.add("請填寫員工姓名.");
				}

				CouponCodeBean CCB = new CouponCodeBean();
				CCB.setCoupon_code_id(coupon_code_id);
				CCB.setCoupon_code(coupon_code);
				CCB.setCoupon_code_effective_date(coupon_code_effective_date);
				CCB.setCoupon_code_expire_date(coupon_code_expire_date);
				CCB.setCoupon_code_text_content(coupon_code_text_content);
				CCB.setCoupon_code_content(coupon_code_text);
				CCB.setDiscount_type(discount_type);
				CCB.setEmployee_account(employee_account);
				
		
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("CCB", CCB); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/coupon_code_jsp/update_cc_input.jsp");
					System.out.println(errorMsgs);
					failureView.forward(req, res);
					return; 
				}
		
				/***************************2.開始修改資料*****************************************/
				CouponCodeService ccSvc = new CouponCodeService();
				CCB = ccSvc.updateCC(coupon_code, coupon_code_effective_date, coupon_code_expire_date,
						coupon_code_text_content, coupon_code_text, discount_type,employee_account,coupon_code_id);
			
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("CCB", CCB); 
				String url = "/coupon_code_jsp/listOneCc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				System.out.println(e);
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/coupon_code_jsp/update_cc_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) {
        	
        	System.out.println("我有進來嗎?");
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				System.out.println("請求參數成功");
				
				String coupon_code = req.getParameter("coupon_code");
				
				if (coupon_code == null || coupon_code.trim().length() == 0) {
					errorMsgs.add("優惠碼名稱: 請勿空白");
				} 
				System.out.println("這裡??");
				
				java.sql.Timestamp coupon_code_effective_date = null;
				try {
					coupon_code_effective_date = java.sql.Timestamp.valueOf(req.getParameter("coupon_code_effective_date").trim());
				} catch (IllegalArgumentException e) {
					coupon_code_effective_date=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入優惠碼生效日期!");
				}
				
				
				java.sql.Timestamp coupon_code_expire_date = null;
				try {
					coupon_code_expire_date = java.sql.Timestamp.valueOf(req.getParameter("coupon_code_expire_date").trim());
				} catch (IllegalArgumentException e) {
					coupon_code_expire_date=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入優惠碼到期日期!");
				}
				
				String coupon_code_text_content = req.getParameter("coupon_code_text_content").trim();
				if (coupon_code_text_content == null || coupon_code_text_content.trim().length() == 0) {
					errorMsgs.add("優惠碼文字內容請勿空白");
				}
				

				Float coupon_code_text = null;
				try {
					coupon_code_text = new Float(req.getParameter("coupon_code_text").trim());
				} catch (NumberFormatException e) {
					coupon_code_text = 0f;
					errorMsgs.add("優惠碼請填數字 + f");
				}
				
				Integer discount_type = null;
				try {
					discount_type = new Integer(req.getParameter("discount_type").trim());
				} catch (NumberFormatException e) {
					discount_type = 0;
					errorMsgs.add("優惠方式請填數字.");
				}
				
				String employee_account = req.getParameter("employee_account").trim();
				if (employee_account == null || employee_account.trim().length() == 0) {
					errorMsgs.add("員工請勿空白");
				}

				CouponCodeBean CCB = new CouponCodeBean();
				CCB.setCoupon_code(coupon_code);
				CCB.setCoupon_code_effective_date(coupon_code_effective_date);
				CCB.setCoupon_code_expire_date(coupon_code_expire_date);
				CCB.setCoupon_code_text_content(coupon_code_text_content);
				CCB.setCoupon_code_content(coupon_code_text);
				CCB.setDiscount_type(discount_type);
				CCB.setEmployee_account(employee_account);
				
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("CCB", CCB); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/coupon_code_jsp/addCc.jsp");
					failureView.forward(req, res);
					System.out.println("這裡?");
					return;
					
				}
				
				System.out.println("還是這裡?");
				
				/***************************2.開始新增資料***************************************/
				CouponCodeService ccSvc = new CouponCodeService();
				CCB = ccSvc.addCC(coupon_code, coupon_code_effective_date, coupon_code_expire_date,
						coupon_code_text_content, coupon_code_text,  discount_type, employee_account);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/coupon_code_jsp/listAllCc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/coupon_code_jsp/addCc.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer coupon_code_id = new Integer(req.getParameter("coupon_code_id"));
				
				/***************************2.開始刪除資料***************************************/
				CouponCodeService ccSvc = new CouponCodeService();
				ccSvc.deleteCC(coupon_code_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/coupon_code_jsp/listAllCc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/coupon_code_jsp/listAllCc.jsp");
				failureView.forward(req, res);

			}
		}
		
	}

}
