package com.daily_special.model;

import java.util.List;

import com.product.model.ProductBean;

public class DailySpecialProductService {
	
	private DailySpecialProductDAO_interface dao;
	
	public DailySpecialProductService() {
		dao = new DailySpecialProductDAO();
	}
	
	public DailySpecialProductBean getOneValidDailySpecialProduct(DailySpecialBean dsBean, ProductBean productBean) {
		
		return dao.getOneDailySpecialProduct(dsBean, productBean);
	}
	
	
	
}
