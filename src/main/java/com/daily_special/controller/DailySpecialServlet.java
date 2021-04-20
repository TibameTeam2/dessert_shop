package com.daily_special.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daily_special.model.DailySpecialBean;
import com.daily_special.model.DailySpecialService;
import com.daily_special.model.ValidDailySpecialProductBean;
import com.daily_special.model.ValidDailySpecialProductService;
import com.product.model.ProductBean;
import com.product.model.ProductService;
import com.util.BaseServlet;
import com.util.ResultInfo;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class DailySpecialServlet extends BaseServlet {
	
	// 呈現在http://localhost:8081/dessert_shop/dailySpecial/test
	public void test(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("test");
	}
	
	// 前台 
	// http://localhost:8081/dessert_shop/dailySpecial/getValidDailySpecialProduct
	public void getValidDailySpecialProduct(HttpServletRequest req, HttpServletResponse res){
		System.out.println("VDSPB");//
		
		DailySpecialService dsSvc = new DailySpecialService();
		List<DailySpecialBean> ds_list = dsSvc.getValidDailySpecial();
		List<ValidDailySpecialProductBean> validProduct_list = new ArrayList<ValidDailySpecialProductBean>(); 
		ProductService productSvc = new ProductService();
		ProductBean productBean = new ProductBean();
		ValidDailySpecialProductService vdspSvc = new ValidDailySpecialProductService();
		ValidDailySpecialProductBean vds_productBean = new ValidDailySpecialProductBean();
		
		for(DailySpecialBean dsBean : ds_list) {
			Integer product_id = dsBean.getProduct_id();
			System.out.println("dsBean的product_id:" + product_id);
			// 用這個id撈產品
			productBean = productSvc.getOneProduct(product_id);
			vds_productBean = vdspSvc.getOneValidDailySpecialProduct(dsBean, productBean);
			
			System.out.println("vds_productBean:"+vds_productBean);
			validProduct_list.add(vds_productBean);
		}
		System.out.println("validProduct_list:"+validProduct_list);
		
		ResultInfo info = new ResultInfo();

		info.setFlag(true);
		info.setMsg("每日優惠資料取得成功!");
		info.setData(validProduct_list);

		writeValueByWriter(res, info);
		
	}
	
       

}
