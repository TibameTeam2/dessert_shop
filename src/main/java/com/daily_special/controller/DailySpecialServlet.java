package com.daily_special.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.daily_special.model.DailySpecialBean;
import com.daily_special.model.DailySpecialService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
			vds_productBean = vdspSvc.getOneDailySpecialProduct(dsBean, productBean);
			
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
			ds_productBean = dspSvc.getOneDailySpecialProduct(dsBean, productBean);
			
			System.out.println("ds_productBean:"+ds_productBean);
			allDSProduct_list.add(ds_productBean);
		}
		System.out.println("dsProduct_list:"+allDSProduct_list);
		
		ResultInfo info = new ResultInfo();

		info.setFlag(true);
		info.setMsg("後台:每日優惠資料取得成功!");
		info.setData(allDSProduct_list);

		writeValueByWriter(res, info);
	}
    
	// 後台 新增優惠
	// http://localhost:8081/dessert_shop/dailySpecial/backend_addDailySpecial
	public void backend_addDailySpecial(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException {
		System.out.println("後台 Servlet 新增優惠");//
		
		// 獲取addDailySpecial_form的數據
    	Map<String, String[]> map = req.getParameterMap();
    	Map<String, String[]> new_map = new HashMap<String, String[]>(map);
    	
    	System.out.println("new_map= " + new ObjectMapper().writeValueAsString(new_map));
    	String[] str_start = new_map.get("discount_start_time")[0].split("T");
		String discount_start_time = str_start[0] + " " + str_start[1];
		
		
		String[] temp_start = new String[1];
		temp_start[0] = discount_start_time;
		new_map.replace("discount_start_time", temp_start);
		
		String[] str_end = new_map.get("discount_deadline")[0].split("T");
		String discount_deadline = str_end[0] + " " + str_end[1];
		
		String[] temp_end = new String[1];
		temp_end[0] = discount_deadline;
		new_map.replace("discount_deadline", temp_end);
		
		System.out.println("replace完後的new_map= " + new ObjectMapper().writeValueAsString(new_map));
    	
     	DailySpecialBean dsBean = new DailySpecialBean();
     	ResultInfo info = new ResultInfo();
    	try {
    		BeanUtils.populate(dsBean, new_map);
    		System.out.println("封裝好的dsBean:" + dsBean);
    		
    		// 調用service將商品"新增"至DB
    		DailySpecialService dsSvc = new DailySpecialService();
    		dsSvc.addDailySpecial(dsBean);
    		
			info.setFlag(true);
			info.setMsg("新增優惠成功!");

			writeValueByWriter(res, info);
    		
    	}catch(IllegalAccessException | InvocationTargetException e) {
    	    e.printStackTrace();
    	    
    	    info.setFlag(false);
			info.setMsg("新增優惠失敗!");

			writeValueByWriter(res, info);
    	}
		
	}

	// 後台 修改優惠
	// http://localhost:8081/dessert_shop/dailySpecial/backend_updateDailySpecial
	public void backend_updateDailySpecial(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException {
		System.out.println("後台 Servlet 修改優惠");//
		// 獲取addDailySpecial_form的數據
    	Map<String, String[]> map = req.getParameterMap();
    	Map<String, String[]> new_map = new HashMap<String, String[]>(map);
    	
    	System.out.println("new_map= " + new ObjectMapper().writeValueAsString(new_map));
    	String[] str_start = new_map.get("discount_start_time")[0].split("T");
		String discount_start_time = str_start[0] + " " + str_start[1];
		
		
		String[] temp_start = new String[1];
		temp_start[0] = discount_start_time;
		new_map.replace("discount_start_time", temp_start);
		
		String[] str_end = new_map.get("discount_deadline")[0].split("T");
		String discount_deadline = str_end[0] + " " + str_end[1];
		
		String[] temp_end = new String[1];
		temp_end[0] = discount_deadline;
		new_map.replace("discount_deadline", temp_end);
		
		System.out.println("replace完後的new_map= " + new_map);
    	
     	DailySpecialBean dsBean = new DailySpecialBean();
     	ResultInfo info = new ResultInfo();
    	try {
    		BeanUtils.populate(dsBean, new_map);
    		System.out.println("封裝好的dsBean:" + dsBean);
    		
    		// 調用service將優惠"新增"至DB
    		DailySpecialService dsSvc = new DailySpecialService();
    		dsSvc.updateDailySpecial(dsBean);
    		
			info.setFlag(true);
			info.setMsg("修改優惠成功!");

			writeValueByWriter(res, info);
    		
    	}catch(IllegalAccessException | InvocationTargetException e) {
    	    e.printStackTrace();
    	    
    	    info.setFlag(false);
			info.setMsg("修改優惠失敗!");

			writeValueByWriter(res, info);
    	}
		
		
	}
	
	// 後台 刪除每日優惠
	// http://localhost:8081/dessert_shop/dailySpecial/backend_deleteDailySpecial
	public void backend_deleteDailySpecial(HttpServletRequest req, HttpServletResponse res) {

		Integer discount_product_id = new Integer(req.getParameter("discount_product_id"));
		
		DailySpecialService dsSvc = new DailySpecialService();

		ResultInfo info = new ResultInfo();
		try {
		
			dsSvc.deleteDailySpecial(discount_product_id);
			info.setFlag(true);
			info.setMsg("刪除優惠成功!");
			
			writeValueByWriter(res, info);
			
		}catch(Exception e) {
			
			info.setFlag(false);
			info.setMsg("刪除優惠失敗!");
			writeValueByWriter(res, info);
		}
		
		
	}
	
	

}
