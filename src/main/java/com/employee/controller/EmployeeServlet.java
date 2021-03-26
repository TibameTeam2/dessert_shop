package com.employee.controller;

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

import com.employee.model.EmployeeBean;
import com.employee.model.EmployeeService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class EmployeeServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String employee_account = req.getParameter("employee_account");
				if (employee_account == null || (employee_account.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/employee_jsp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
//				Integer empno = null;
//				try {
//					empno = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("員工編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				/***************************2.開始查詢資料*****************************************/
				EmployeeService empSvc = new EmployeeService();
				EmployeeBean empBean = empSvc.getOneEmp(employee_account);
				if (empBean == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/employee_jsp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empBean", empBean); // 資料庫取出的empVO物件,存入req
				String url = "/employee_jsp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/employee_jsp/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String employee_account = req.getParameter("employee_account").trim();
				
				/***************************2.開始查詢資料****************************************/
				EmployeeService empSvc = new EmployeeService();
				EmployeeBean empBean = empSvc.getOneEmp(employee_account);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("empBean", empBean);         // 資料庫取出的empVO物件,存入req
				String url = "/employee_jsp/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/employee_jsp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String employee_account = req.getParameter("employee_account").trim();
				
				String employee_name = req.getParameter("employee_name").trim();
				if (employee_name == null || employee_name.trim().length() == 0) {
					errorMsgs.add("名稱請勿空白");
				}
				
				String employee_password = req.getParameter("employee_password").trim();
				if (employee_password == null || employee_password.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				
				String employee_position = req.getParameter("employee_position").trim();
				if (employee_position == null || employee_position.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}
				
				
				
				
				Part part = req.getPart("upfile1");
		        InputStream in = part.getInputStream();
		        byte[] employee_photo = new byte[in.available()];
		        in.read(employee_photo);
		        in.close();
				if (employee_photo.length == 0) {
					EmployeeService empSvc = new EmployeeService();
					EmployeeBean Bean = empSvc.getOneEmp(employee_account);
					employee_photo = Bean.getEmployee_photo();
				}
				
				
				
				
				java.sql.Date hire_date = null;
				try {
					hire_date = java.sql.Date.valueOf(req.getParameter("hire_date").trim());
				} catch (IllegalArgumentException e) {
					hire_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				
				String employee_status_str = req.getParameter("employee_status");
				String enameReg = "^[(01)]{1}$";
				Integer employee_status = 1;
				if (employee_status_str == null || employee_status_str.trim().length() == 0) {
					errorMsgs.add("員工狀態: 請勿空白");
				} else if(!employee_status_str.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工狀態: 只能是0或1");
	            } else {
	            	employee_status = new Integer(employee_status_str);
	            }
				

				EmployeeBean empBean = new EmployeeBean();
				empBean.setEmployee_account(employee_account);
				empBean.setEmployee_name(employee_name);
				empBean.setEmployee_password(employee_password);
				empBean.setEmployee_position(employee_position);
				empBean.setEmployee_photo(employee_photo);
				empBean.setHire_date(hire_date);
				empBean.setEmployee_status(employee_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empBean", empBean); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/employee_jsp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				EmployeeService empSvc = new EmployeeService();
				empBean = empSvc.updateEmp(employee_name, employee_password, employee_position, employee_photo, hire_date, employee_status, employee_account);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empBean", empBean); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/employee_jsp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/employee_jsp/update_emp_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String employee_account = req.getParameter("employee_account");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (employee_account == null || employee_account.trim().length() == 0) {
					errorMsgs.add("員工帳號: 請勿空白");
//				} else if(!employee_account.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String employee_name = req.getParameter("employee_name").trim();
				if (employee_name == null || employee_name.trim().length() == 0) {
					errorMsgs.add("名稱請勿空白");
				}
				
				String employee_password = req.getParameter("employee_password").trim();
				if (employee_password == null || employee_password.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				
				String employee_position = req.getParameter("employee_position").trim();
				if (employee_position == null || employee_position.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}
				
				
				
				Part part = req.getPart("upfile1");
	            InputStream in = part.getInputStream();
	            byte[] employee_photo = new byte[in.available()];
	            in.read(employee_photo);
	            in.close();
	            if (employee_photo.length == 0) {
					errorMsgs.add("請上傳圖片!");
				}
				
							
				
				
				java.sql.Date hire_date = null;
				try {
					hire_date = java.sql.Date.valueOf(req.getParameter("hire_date").trim());
				} catch (IllegalArgumentException e) {
					hire_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				
				String employee_status_str = req.getParameter("employee_status");
				String enameReg = "^[(01)]{1}$";
				Integer employee_status = 1;
				if (employee_status_str == null || employee_status_str.trim().length() == 0) {
					errorMsgs.add("員工狀態: 請勿空白");
				} else if(!employee_status_str.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工狀態: 只能是0或1");
	            } else {
	            	employee_status = new Integer(employee_status_str);
	            }

				EmployeeBean empBean = new EmployeeBean();
				empBean.setEmployee_account(employee_account);
				empBean.setEmployee_name(employee_name);
				empBean.setEmployee_password(employee_password);
				empBean.setEmployee_position(employee_position);
				empBean.setEmployee_photo(employee_photo);
				empBean.setHire_date(hire_date);
				empBean.setEmployee_status(employee_status);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empBean", empBean); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/employee_jsp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				EmployeeService empSvc = new EmployeeService();
				empBean = empSvc.addEmp(employee_account, employee_name, employee_password, employee_position, employee_photo, hire_date, employee_status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/employee_jsp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/employee_jsp/addEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String employee_account = req.getParameter("employee_account").trim();
				
				/***************************2.開始刪除資料***************************************/
				EmployeeService empSvc = new EmployeeService();
				empSvc.deleteEmp(employee_account);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/employee_jsp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/employee_jsp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
