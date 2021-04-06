package com.announcement_management.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.announcement_management.model.AnnouncementManagementService;
import com.announcement_management.model.AnnouncementManagementBean;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class AmServlet extends HttpServlet{
	
	
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
				Integer id = new Integer(req.getParameter("announcement_id").trim()); //getParameter ��U�ӳ��O�r��
		
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/am/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer announcement_id = null;
				try {
					announcement_id = new Integer(id);
				} catch (Exception e) {
					errorMsgs.add("公告編號格式不正確");
				}
		
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/am/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				AnnouncementManagementService amSvc = new AnnouncementManagementService();
				AnnouncementManagementBean AMB = amSvc.getOneAnn(announcement_id);
				System.out.println("controller:"+AMB);
				
				if (announcement_id == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/am/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("AMB", AMB); 
				String url = "/am/listOneAm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/am/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer announcement_id = new Integer(req.getParameter("announcement_id"));
				
				/***************************2.開始查詢資料****************************************/
				AnnouncementManagementService amSvc = new AnnouncementManagementService();
				
				AnnouncementManagementBean AMB = amSvc.getOneAnn(announcement_id);
				
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("AMB", AMB);         
				String url = "/am/update_am_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/am/listAllAm.jsp");
				failureView.forward(req, res);
			}
		}
		
		
if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				Integer announcement_id = new Integer(req.getParameter("announcement_id").trim());
				 System.out.println(announcement_id+"id");
				String announcement_name = req.getParameter("announcement_name");
//				String announcement_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10,10}$";
				if (announcement_name == null || announcement_name.trim().length() == 0) {
					errorMsgs.add("公告名稱: 請勿空白");
				} 
//					else if(!announcement_name.trim().matches(announcement_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("���i�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
//	            }
				
				String announcement_content = req.getParameter("announcement_content").trim();
				if (announcement_content == null || announcement_content.trim().length() == 0) {
					errorMsgs.add("公告內容請勿空白");
				}	
				
				
				java.sql.Timestamp announcement_time = null;
				try {
					announcement_time = java.sql.Timestamp.valueOf(req.getParameter("announcement_time").trim());
				} catch (IllegalArgumentException e) {
					announcement_time=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				
				
				Part part = req.getPart("upfile1");
		        InputStream in = part.getInputStream();
		        byte[] announcement_image = new byte[in.available()];
		        in.read(announcement_image);
		        in.close();
				if (announcement_image.length == 0) {
					AnnouncementManagementService amSvc = new AnnouncementManagementService();
					AnnouncementManagementBean VO = amSvc.getOneAnn(announcement_id);
					announcement_image = VO.getAnnouncement_image();
				}
				

				Integer announcement_type = null;
				try {
					announcement_type = new Integer(req.getParameter("announcement_type").trim());
				} catch (NumberFormatException e) {
					announcement_type = 0;
					errorMsgs.add("種類請填數字.");
				}
				
				Integer announcement_status = null;
				try {
					announcement_status = new Integer(req.getParameter("announcement_status").trim());
				} catch (NumberFormatException e) {
					announcement_status = 0;
					errorMsgs.add("狀態請填數字.");
				}
				
				String employee_account = req.getParameter("employee_account");
			
				try {
					employee_account = new String(req.getParameter("employee_account").trim());
				} catch (Exception e) {
					System.out.println(e);
					employee_account = "";
					errorMsgs.add("請填寫員工姓名.");
				}

				AnnouncementManagementBean AMB = new AnnouncementManagementBean();
				AMB.setAnnouncement_id(announcement_id);
				AMB.setAnnouncement_name(announcement_name);
				AMB.setAnnouncement_content(announcement_content);

				AMB.setAnnouncement_time(announcement_time);
				AMB.setAnnouncement_type(announcement_type);
				AMB.setAnnouncement_status(announcement_status);
				AMB.setEmployee_account(employee_account);
				
		
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("AMB", AMB); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/am/update_am_input.jsp");
					System.out.println("svc3");
					System.out.println(errorMsgs);
					failureView.forward(req, res);
					return; 
				}
		
				/***************************2.開始修改資料*****************************************/
				AnnouncementManagementService amSvc = new AnnouncementManagementService();
				AMB = amSvc.updateAnn(announcement_name, announcement_content, announcement_image,
						announcement_time, announcement_type, announcement_status,employee_account,announcement_id);
			
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("AMB", AMB); 
				String url = "/am/listOneAm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				System.out.println(e);
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/am/update_am_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				System.out.println("請求參數成功");
				
				String announcement_name = req.getParameter("announcement_name");
//				String announcement_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10,10}$";
				if (announcement_name == null || announcement_name.trim().length() == 0) {
					errorMsgs.add("公告名稱: 請勿空白");
				} 
//				else if(!announcement_name.trim().matches(announcement_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("���i�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb10��10����");
//	            }
				
				String announcement_content = req.getParameter("announcement_content").trim();
				if (announcement_content == null || announcement_content.trim().length() == 0) {
					errorMsgs.add("公告內容請勿空白");
				}
				
				System.out.println("到公告為止參數請求成功");
				
				System.out.println("是時間的問題嗎?");
				
				java.sql.Timestamp announcement_time = null;
				try {
					announcement_time = java.sql.Timestamp.valueOf(req.getParameter("announcement_time").trim());
				} catch (IllegalArgumentException e) {
					announcement_time=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				

				Part part = req.getPart("upfile1");
		        InputStream in = part.getInputStream();
		        byte[] announcement_image = new byte[in.available()];
		        in.read(announcement_image);
		        in.close();
				if (announcement_image.length == 0) {
					errorMsgs.add("請上傳圖片!");
				}
				

				Integer announcement_type = null;
				try {
					announcement_type = new Integer(req.getParameter("announcement_type").trim());
				} catch (NumberFormatException e) {
					announcement_type = 0;
					errorMsgs.add("種類請填數字.");
				}
				

				Integer announcement_status = null;
				try {
					announcement_status = new Integer(req.getParameter("announcement_status").trim());
				} catch (NumberFormatException e) {
					announcement_status = 0;
					errorMsgs.add("狀態請填數字r.");
				}
				
				
				String employee_account = req.getParameter("employee_account").trim();
				if (employee_account == null || employee_account.trim().length() == 0) {
					errorMsgs.add("員工請勿空白");
				}
				
				
//				String employee_account = null;
//				try {
//					employee_account = new String(req.getParameter("employee_account").trim());
//				} catch (NumberFormatException e) {
//					employee_account = "";
//					errorMsgs.add("請填寫員工姓名.");
//				}

				AnnouncementManagementBean AMB = new AnnouncementManagementBean();
				AMB.setAnnouncement_name(announcement_name);
				AMB.setAnnouncement_content(announcement_content);
				AMB.setAnnouncement_image(announcement_image);
				AMB.setAnnouncement_time(announcement_time);
				AMB.setAnnouncement_type(announcement_type);
				AMB.setAnnouncement_status(announcement_status);
				AMB.setEmployee_account(employee_account);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("AMB", AMB); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/am/addAm.jsp");
					failureView.forward(req, res);
					
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				AnnouncementManagementService amSvc = new AnnouncementManagementService();
				AMB = amSvc.addAnn(announcement_name, announcement_content, announcement_image,
						 announcement_time, announcement_type,  announcement_status, employee_account);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/am/listAllAm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/am/addAm.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer announcement_id = new Integer(req.getParameter("announcement_id"));
				
				/***************************2.開始刪除資料***************************************/
				AnnouncementManagementService amSvc = new AnnouncementManagementService();
				amSvc.deleteAnn(announcement_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/am/listAllAm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/am/listAllAm.jsp");
				failureView.forward(req, res);

			}
		}
	}
	
	

}
