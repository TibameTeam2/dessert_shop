package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import cn.hutool.core.io.IoUtil;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import com.product.model.ProductBean;
import com.product.model.ProductService;
import com.product_image.model.ProductImageBean;
import com.product_image.model.ProductImageService;
import com.util.JDBCUtil;

import cn.hutool.core.io.IoUtil;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
//		PrintWriter out = res.getWriter();
//		out.print("this is product servlet");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html;charset=UTF-8");
		
		
		if ("getProductImage".equals(action)) { //from ???的請求
			System.out.println("getProductImage");
		        res.setContentType("image/png");
		        Connection con = null;
		        String driver = JDBCUtil.driver;
		        String url = JDBCUtil.url;
		        String userid = JDBCUtil.user;
		        String passwd = JDBCUtil.password;
		        ResultSet rs=null;
		        PreparedStatement pstmt = null;
		        try {
			        con = DriverManager.getConnection(url, userid, passwd);
			        ServletOutputStream out = res.getOutputStream();
			        Statement stmt = con.createStatement();
			        String id = req.getParameter("id").trim();
//			        ResultSet rs = stmt.executeQuery(
//			                "SELECT member_photo FROM sweet.member WHERE member_account =" + id);

			       
			        pstmt = con.prepareStatement("SELECT product_image FROM sweet.product_image where image_id=?");

			        pstmt.setString(1, id);

			        rs = pstmt.executeQuery();

		            if (rs.next()) {
//		            BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("member_photo"));
		                IoUtil.write(res.getOutputStream(), true, IoUtil.readBytes(rs.getBinaryStream("product_image"), true));
		            }
		        } catch (Exception e) {

		        } finally {
		            
		            try {
		            	rs.close();
						pstmt.close();
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            
		        }
			 
			 
			 
		}
		
		
		if ("getOne_For_Display".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("product_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product_jsp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer product_id = null;
				try {
					product_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product_jsp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ProductService productSvc = new ProductService();
				ProductBean productBean = productSvc.getOneProduct(product_id);
				if (productBean == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product_jsp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productBean", productBean); // 資料庫取出的productBean物件,存入req
				String url = "/product_jsp/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_jsp/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllProduct.jsp的請求
			System.out.println("getOne_For_Update1");//
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer product_id = new Integer(req.getParameter("product_id"));
				
				/***************************2.開始查詢資料****************************************/
				ProductService productSvc = new ProductService();
				ProductBean productBean = productSvc.getOneProduct(product_id);
				
				System.out.println("getOne_For_Update裡面的:"+productBean);//
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("productBean", productBean);         // 資料庫取出的productBean物件,存入req
				System.out.println("getOne_For_Update裡面的準備轉交");//
				System.out.println("getOne_For_Update準備轉交的productBean"+productBean);//
				
				String url = "/product_jsp/update_product_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				System.out.println("getOne_For_Update準備轉交最後一行");//

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_jsp/listAllProduct.jsp");
				failureView.forward(req, res);
				System.out.println("getOne_For_Update的錯誤");//
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_product_input.jsp的請求
			System.out.println("update");//
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer product_id = new Integer(req.getParameter("product_id").trim());
				System.out.println(product_id);//
				String product_name = req.getParameter("product_name");
				System.out.println(product_name);//
				String product_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (product_name == null || product_name.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if(!product_name.trim().matches(product_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				// 商品種類
				String category = req.getParameter("product_type");
				String product_type = null;
				String product_subtype = null;
				if (category == null || category.trim().length() == 0) {
					errorMsgs.add("請選擇商品種類");
				}else {
				 String[] tokens = category.split(":");
				 product_type = tokens[0];
				 product_subtype = tokens[1];
				}
				
				String product_intro = req.getParameter("product_intro").trim();
				if (product_intro == null || product_intro.trim().length() == 0) {
					errorMsgs.add("商品介紹請勿空白");
				}	
				
				System.out.println(product_intro);//
				
				String product_ingredient = req.getParameter("product_ingredient").trim();
				if (product_ingredient == null || product_ingredient.trim().length() == 0) {
					errorMsgs.add("商品成份請勿空白");
				}
				
//				System.out.println(product_ingredient);//

				Integer product_price = null;
				try {
					product_price = new Integer(req.getParameter("product_price").trim());
				} catch (NumberFormatException e) {
					product_price = 0;
					errorMsgs.add("商品售價請填數字.");
				}
				
//				System.out.println(product_price);//

				Integer product_available_qty = null;
				try {
					product_available_qty = new Integer(req.getParameter("product_available_qty").trim());
				} catch (NumberFormatException e) {
					product_available_qty = 0;
					errorMsgs.add("現貨數量請填數字.");
				}
				
//				System.out.println(product_available_qty);//
				
				Integer product_status = null;
				try {
					product_status = new Integer(req.getParameter("product_status").trim());
				} catch (NumberFormatException e) {
					product_status = 0;
					errorMsgs.add("商品狀態請選擇");
				}
				
				Integer expiry_after_buying = null;
				try {
					expiry_after_buying = new Integer(req.getParameter("expiry_after_buying").trim());
				} catch (NumberFormatException e) {
					expiry_after_buying = 0;
					errorMsgs.add("請輸入賞味天數");
				}
				
				Integer product_calorie = null;
				try {
					product_calorie = new Integer(req.getParameter("product_calorie").trim());
				} catch (NumberFormatException e) {
					product_calorie = 0;
					errorMsgs.add("請輸入商品熱量");
				}
				
				Integer degree_of_sweetness = null;
				try {
					degree_of_sweetness = new Integer(req.getParameter("degree_of_sweetness").trim());
				} catch (NumberFormatException e) {
					degree_of_sweetness = 0;
					errorMsgs.add("請輸入商品甜度");
				}
				
				//不是輸入而來的數字
				Integer total_star = null;
				try {
					total_star = new Integer(req.getParameter("total_star").trim());
				} catch (NumberFormatException e) {
					total_star = 0;
					errorMsgs.add("請輸入商品累計星等");
				}
				
				//不是輸入而來的數字
				Integer total_review = null;
				try {
					total_review = new Integer(req.getParameter("total_review").trim());
				} catch (NumberFormatException e) {
					total_review = 0;
					errorMsgs.add("請輸入商品評價數量");
				}
				
				System.out.println(total_review);//
				
				Integer total_purchase = null;
				try {
					total_purchase = new Integer(req.getParameter("total_purchase").trim());
				} catch (NumberFormatException e) {
					total_purchase = 0;
					errorMsgs.add("請輸入商品累計銷售量");
				}
				
				System.out.println("圖片驗證update");//
// 商品圖片的驗證
				Part part = req.getPart("upfile1");
		        InputStream in = part.getInputStream();
		        byte[] product_image = new byte[in.available()];
		        in.read(product_image);
		        in.close();
		   
		        System.out.println("圖片驗證update2222");//
		        
///這裡要拿到img id

		        
				if (product_image.length == 0) {

					ProductService productSvc = new ProductService();
					ProductBean productBean = productSvc.getOneProduct(product_id);
					product_image =productBean.getProduct_image();					

				}
				
				
				System.out.println("商品照片:"+product_image);
								
				
				ProductBean productBean = new ProductBean();
				
				ProductImageBean piBean = new ProductImageBean();//
				
				productBean.setProduct_id(product_id);
				productBean.setProduct_name(product_name);
				productBean.setProduct_type(product_type);
				productBean.setProduct_subtype(product_subtype);
				productBean.setProduct_intro(product_intro);
				productBean.setProduct_ingredient(product_ingredient);
				productBean.setProduct_price(product_price);
				productBean.setProduct_available_qty(product_available_qty);
				productBean.setProduct_status(product_status);
				productBean.setExpiry_after_buying(expiry_after_buying);
				productBean.setProduct_calorie(product_calorie);
				productBean.setDegree_of_sweetness(degree_of_sweetness);
				productBean.setTotal_star(total_star);
				productBean.setTotal_review(total_review);
				productBean.setTotal_purchase(total_purchase);
				
//				productBean.setProduct_image(product_image);//
				
				piBean.setProduct_id(productBean.getProduct_id());//
				
				piBean.setProduct_image(product_image);//
				piBean.setImage_id(productBean.getImage_id());	
				
				System.out.println("image_id:"+productBean.getImage_id());//
				System.out.println("update的:"+productBean);//
				System.out.println("update的:"+piBean);//
				
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productBean", productBean); // 含有輸入格式錯誤的productBean物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product_jsp/update_product_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				System.out.println("uu");
				
				/***************************2.開始修改資料*****************************************/
				ProductService productSvc = new ProductService();
//				productBean = productSvc.updateProduct(product_id, product_name, product_type, product_intro, product_price, product_available_qty, product_status, product_calorie, degree_of_sweetness, total_star, total_review);
				productSvc.updateProduct(productBean);
				System.out.println("productBean update成功");
				
				ProductImageService piSvc = new ProductImageService();
// update照片會失敗		
				piBean.setProduct_id(productBean.getProduct_id()); //
				piSvc.updateProductImage(piBean);
				
				System.out.println("piBean update成功");
				
				System.out.println("修改資料"+ productBean);//
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productBean", productBean); // 資料庫update成功後,正確的的productBean物件,存入req
				String url = "/product_jsp/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				System.out.println("error");//
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_jsp/listAllProduct.jsp");		
											//失敗應該在原地 update_product_input.jsp
											//因為會nullPointerException 故先轉交給listAllProduct.jsp
				failureView.forward(req, res);
			}
		}
			
		if ("insert".equals(action)) { // 來自addProduct.jsp的請求  
			
			System.out.println("insert");//
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String product_name = req.getParameter("product_name");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (product_name == null || product_name.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if(!product_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression) // matches回傳boolean
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
//				System.out.println(product_name);//
				
				// 商品種類
				String category = req.getParameter("product_type");
				String product_type = null;
				String product_subtype = null;
				if (category == null || category.trim().length() == 0) {
					errorMsgs.add("請選擇商品種類");
				}else {
				 String[] tokens = category.split(":");
				 product_type = tokens[0];
				 product_subtype = tokens[1];
				}
				
//				System.out.println(product_type);//
//				System.out.println(product_subtype);//
				

				String product_intro = req.getParameter("product_intro").trim();
				if (product_intro == null || product_intro.trim().length() == 0) {
					errorMsgs.add("商品介紹請勿空白");
				}	
				
//				System.out.println(product_intro);//
				
				String product_ingredient = req.getParameter("product_ingredient").trim();
				if (product_ingredient == null || product_ingredient.trim().length() == 0) {
					errorMsgs.add("商品成份請勿空白");
				}	

//				System.out.println(product_ingredient);//
				
				Integer product_price = null;
				try {
					product_price = new Integer(req.getParameter("product_price").trim());
				} catch (NumberFormatException e) {
					product_price = 0;
					errorMsgs.add("商品售價請填數字.");
				}

//				System.out.println(product_price);//
				
				Integer product_available_qty = null;
				try {
					product_available_qty = new Integer(req.getParameter("product_available_qty").trim());
				} catch (NumberFormatException e) {
					product_available_qty = 0;
					errorMsgs.add("現貨數量請填數字.");
				}
				
//				System.out.println(product_available_qty);//
			
				Integer product_status = null;
				try {
					product_status = new Integer(req.getParameter("product_status").trim());
				} catch (NumberFormatException e) {
					product_status = 0;
					errorMsgs.add("請選擇商品狀態");
				}
				
//				System.out.println(product_status);//
				
				Integer expiry_after_buying = null;
				try {
					expiry_after_buying = new Integer(req.getParameter("expiry_after_buying").trim());
				} catch (NumberFormatException e) {
					expiry_after_buying = 0;
					errorMsgs.add("請輸入賞味天數");
				}
				
//				System.out.println(expiry_after_buying);//
				
				Integer product_calorie = null;
				try {
					product_calorie = new Integer(req.getParameter("product_calorie").trim());
				} catch (NumberFormatException e) {
					product_calorie = 0;
					errorMsgs.add("請輸入商品熱量");
				}
				
//				System.out.println(product_calorie);//
				
				Integer degree_of_sweetness = null;
				try {
					degree_of_sweetness = new Integer(req.getParameter("degree_of_sweetness").trim());
				} catch (NumberFormatException e) {
					degree_of_sweetness = 0;
					errorMsgs.add("請輸入商品甜度");
				}
				
//				System.out.println(degree_of_sweetness);//
				
				//不是輸入而來的數字
				Integer total_star = null;
				try {
					total_star = new Integer(req.getParameter("total_star").trim());
				} catch (NumberFormatException e) {
					total_star = 0;
					errorMsgs.add("請輸入商品累計星等");
				}
				
//				System.out.println(total_star);//
								
				//不是輸入而來的數字
				Integer total_review = null;
				try {
					total_review = new Integer(req.getParameter("total_review").trim());
				} catch (NumberFormatException e) {
					total_review = 0;
					errorMsgs.add("請輸入商品評價數量");
				}
				
//				System.out.println(total_review);//
				
				Integer total_purchase = null;
				try {
					total_purchase = new Integer(req.getParameter("total_purchase").trim());
				} catch (NumberFormatException e) {
					total_purchase = 0;
					errorMsgs.add("請輸入商品累計銷售量");
				}
				
//				System.out.println(total_purchase);//
				
				System.out.println("圖片驗證from insert");//
// 商品圖片的驗證
				Part part = req.getPart("upfile1");
		        InputStream in = part.getInputStream();
		        byte[] product_image = new byte[in.available()];
		        in.read(product_image);
		        in.close();
				if (product_image.length == 0) {
					errorMsgs.add("請上傳圖片!");
				}
				System.out.println(product_image);
				
				
			
				ProductBean productBean = new ProductBean();
				ProductImageBean piBean = new ProductImageBean();//
				
				productBean.setProduct_name(product_name);
				productBean.setProduct_type(product_type);
				productBean.setProduct_subtype(product_subtype);
				productBean.setProduct_intro(product_intro);
				productBean.setProduct_ingredient(product_ingredient);
				productBean.setProduct_price(product_price);
				productBean.setProduct_available_qty(product_available_qty);
				productBean.setProduct_status(product_status);
				productBean.setExpiry_after_buying(expiry_after_buying);
				productBean.setProduct_calorie(product_calorie);
				productBean.setDegree_of_sweetness(degree_of_sweetness);
				productBean.setTotal_star(total_star);
				productBean.setTotal_review(total_review);
				productBean.setTotal_purchase(total_purchase);
				
				piBean.setProduct_image(product_image);//
				
				
//				System.out.println(productBean);//

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productBean", productBean); // 含有輸入格式錯誤的productBean物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product_jsp/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}
				System.out.println("aa");
				/***************************2.開始新增資料***************************************/
				ProductService productSvc = new ProductService();
//				productBean = productSvc.addProduct(product_name, product_type, product_intro, product_price, product_available_qty, product_status, product_calorie, degree_of_sweetness, total_star, total_review);
				productBean = productSvc.addProduct(productBean);

				System.out.println("新增資料:"+productBean);//
				
				ProductImageService piSvc = new ProductImageService();
				
				piBean.setProduct_id(productBean.getProduct_id());
				
				System.out.println("id:"+productBean.getProduct_id());//
				
				piSvc.addProductImage(piBean);
				
				System.out.println("新增圖片:"+piBean);//
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/product_jsp/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_jsp/addProduct.jsp");
				failureView.forward(req, res);
				}
			}
		
		
		if ("delete".equals(action)) { // 來自listAllProduct.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer product_id = new Integer(req.getParameter("product_id"));
				
				/***************************2.開始刪除資料***************************************/
				// 要先刪除照片
				ProductImageService piSvc = new ProductImageService();
				piSvc.deleteProductImage(product_id);
				System.out.println("product_id:"+product_id+"照片刪除成功");
				
				ProductService productSvc = new ProductService();
				productSvc.deleteProduct(product_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/product_jsp/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_jsp/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

}
