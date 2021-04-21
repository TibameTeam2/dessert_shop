package com.product_image.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.product_image.model.ProductImageBean;
import com.util.BaseServlet;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class ProductImageServlet extends BaseServlet {
    
//	http://localhost:8081/dessert_shop/productImage/test
	public void test(HttpServletRequest req, HttpServletResponse res) {
    	System.out.println("ProductImage:test");
    } 
	
//  圖片使用	
//	http://localhost:8081/dessert_shop/productImage/backend_addProdcutImage
	public void backend_addProdcutImage(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		List<ProductImageBean> product_image = new ArrayList<ProductImageBean>();

	    Collection<Part> parts = req.getParts();
	    
	    for(Part part: parts) {
	     String header = part.getHeader("Content-Disposition");
	     System.out.println(header);
	     if(header.contains("product_image")) {
	     System.out.println("OKOK");
	    	
// 這裡要馬上知道product_id
	      ProductImageBean piBean = new ProductImageBean();
	      Integer product_id = new Integer(req.getParameter("product_id"));
	      System.out.println("PiServlet addProduct_image的product_id"+product_id);
	      System.out.println("PiServlet sessrion的product_id"+req.getSession().getAttribute("product_id"));
	      piBean.setProduct_id(product_id);
	      
//	      InputStream in = part.getInputStream();
//	      byte[] buf = new byte[in.available()];
//	      in.read(buf);
//	 
//	      System.out.println(Arrays.toString(buf));
//	      lapostimagebean.setLapostImage(buf);
//	      laimgbeans.add(lapostimagebean); 
//	      in.close();
	     }
	       

	    }
//	    System.out.println(laimgbeans);
//	    LApostImageService lapostimageSvc = new LApostImageService();
//	    lapostimageSvc.addallLApostImage(laimgbeans);
//	    
//	    String url = "/views/front-end/lapost/lapost.jsp";
//	    RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//	    successView.forward(req, res);
//
	}

}
