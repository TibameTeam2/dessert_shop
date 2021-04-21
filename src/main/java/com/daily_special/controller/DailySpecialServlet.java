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
import com.daily_special.model.DailySpecialProductBean;
import com.daily_special.model.DailySpecialProductService;
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
	
	// 前台，取得優惠有效且上架中的商品，
	// http://localhost:8081/dessert_shop/dailySpecial/getValidDailySpecialProduct
	public void getValidDailySpecialProduct(HttpServletRequest req, HttpServletResponse res){
		System.out.println("VDSPB");//
		
		DailySpecialService dsSvc = new DailySpecialService();
		List<DailySpecialBean> ds_list = dsSvc.getValidDailySpecial();
		List<DailySpecialProductBean> validProduct_list = new ArrayList<DailySpecialProductBean>(); // 有效的優惠商品
		ProductService productSvc = new ProductService();
		ProductBean productBean = new ProductBean();
		DailySpecialProductService vdspSvc = new DailySpecialProductService();
		DailySpecialProductBean vds_productBean = new DailySpecialProductBean();
		
		for(DailySpecialBean dsBean : ds_list) {
			Integer product_id = dsBean.getProduct_id();
			System.out.println("dsBean的product_id:" + product_id);
			// 用這個id撈產品
			productBean = productSvc.getOneProduct(product_id);
			vds_productBean = vdspSvc.getOneValidDailySpecialProduct(dsBean, productBean);
			
			System.out.println("vds_productBean:"+vds_productBean);
			validProduct_list.add(vds_productBean);
		}
		System.out.println("dsProduct_list:"+validProduct_list);
		
		ResultInfo info = new ResultInfo();

		info.setFlag(true);
		info.setMsg("每日優惠資料取得成功!");
		info.setData(validProduct_list);

		writeValueByWriter(res, info);
		
	}
	
	
	// 後台，取得所有每日優惠的商品，不論效期及商品狀態(=DB)
	// http://localhost:8081/dessert_shop/dailySpecial/backend_getAllDailySpecialProduct
	public void backend_getAllDailySpecialProduct(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("後台 所有DSP");//
		
		DailySpecialService dsSvc = new DailySpecialService();
		List<DailySpecialBean> ds_list = dsSvc.getAllDailySpecial(); // 所有DB優惠清單
		List<DailySpecialProductBean> allDSProduct_list = new ArrayList<DailySpecialProductBean>(); // 所有商品
		ProductService productSvc = new ProductService();
		ProductBean productBean = new ProductBean();
		DailySpecialProductService dspSvc = new DailySpecialProductService();
		DailySpecialProductBean ds_productBean = new DailySpecialProductBean();
		
		for(DailySpecialBean dsBean : ds_list) {
			Integer product_id = dsBean.getProduct_id();
			System.out.println("dsBean的product_id:" + product_id);
			// 用這個id撈產品
			productBean = productSvc.getOneProduct(product_id);
			ds_productBean = dspSvc.getOneValidDailySpecialProduct(dsBean, productBean);
			
			System.out.println("ds_productBean:"+ds_productBean);
			allDSProduct_list.add(ds_productBean);
		}
		System.out.println("dsProduct_list:"+allDSProduct_list);
		
		ResultInfo info = new ResultInfo();

		info.setFlag(true);
		info.setMsg("後台:歷史每日優惠資料取得成功!");
		info.setData(allDSProduct_list);

		writeValueByWriter(res, info);
	}
       

}
