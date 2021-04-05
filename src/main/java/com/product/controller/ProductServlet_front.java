package com.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductBean;
import com.product.model.ProductService;
import com.util.BaseServlet;
import com.util.ResultInfo;


public class ProductServlet_front extends BaseServlet {
	
    public void test(HttpServletRequest req, HttpServletResponse res) {
    	System.out.println("test");
    }   
    
// 呈現在http://localhost:8081/dessert_shop/product/getAllProduct
    public void getAllProduct(HttpServletRequest req, HttpServletResponse res) {
    	ProductService productSvc = new ProductService();
    	List<ProductBean> productList = productSvc.getAll();
    	   	
		ResultInfo info = new ResultInfo();

		info.setFlag(true);
		info.setMsg("資料取得成功!");
		info.setData(productList);

		writeValueByWriter(res, info);

    }
    
    // http://localhost:8081/dessert_shop/product/getOneProduct?id=?
    public void getOneProduct(HttpServletRequest req, HttpServletResponse res) {
    	Integer product_id = new Integer(req.getParameter("id"));
    	ProductService productSvc = new ProductService();
    	ProductBean productBean = productSvc.getOneProduct(product_id);

		ResultInfo info = new ResultInfo();

		info.setFlag(true);
		info.setMsg("資料取得成功!");
		info.setData(productBean);

		writeValueByWriter(res, info);

    }
    
    //  http://localhost:8081/dessert_shop/product/getAllSortByPurchase
    public void getAllSortByPurchase(HttpServletRequest req, HttpServletResponse res) {
    	System.out.println("銷售量排序");
    	ProductService productSvc = new ProductService();
    	List<ProductBean> productList = productSvc.getAllSortByPurchase();
    	   	
		ResultInfo info = new ResultInfo();

		info.setFlag(true);
		info.setMsg("資料取得成功!");
		info.setData(productList);

		writeValueByWriter(res, info);
    }
    
    //  http://localhost:8081/dessert_shop/product/getAllSortByPrice
    public void getAllSortByPrice(HttpServletRequest req, HttpServletResponse res) {
    	System.out.println("價格排序");
    	ProductService productSvc = new ProductService();
    	List<ProductBean> productList = productSvc.getAllSortByPrice();
    	
    	ResultInfo info = new ResultInfo();
    	
    	info.setFlag(true);
    	info.setMsg("資料取得成功!");
    	info.setData(productList);
    	
    	writeValueByWriter(res, info);
    }
 
    //  http://localhost:8081/dessert_shop/product/getAllSortByCalorie
    public void getAllSortByCalorie(HttpServletRequest req, HttpServletResponse res) {
    	System.out.println("熱量排序");
    	ProductService productSvc = new ProductService();
    	List<ProductBean> productList = productSvc.getAllSortByCalorie();
    	
    	ResultInfo info = new ResultInfo();
    	
    	info.setFlag(true);
    	info.setMsg("資料取得成功!");
    	info.setData(productList);
    	
    	writeValueByWriter(res, info);
    }

    //  http://localhost:8081/dessert_shop/product/getAllSortBySweetness
    public void getAllSortBySweetness(HttpServletRequest req, HttpServletResponse res) {
    	System.out.println("甜度排序");
    	ProductService productSvc = new ProductService();
    	List<ProductBean> productList = productSvc.getAllSortBySweetness();
    	
    	ResultInfo info = new ResultInfo();
    	
    	info.setFlag(true);
    	info.setMsg("資料取得成功!");
    	info.setData(productList);
    	
    	writeValueByWriter(res, info);
    }
   
    
}
