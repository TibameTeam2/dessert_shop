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
    
    
    
    
}
