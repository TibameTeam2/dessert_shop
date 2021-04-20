package com.daily_special.model;

import java.util.List;

import com.product.model.ProductBean;

public class ValidDailySpecialProductService {
	
	private ValidDailySpecialProductDAO_interface dao;
	
	public ValidDailySpecialProductService() {
		dao = new ValidDailySpecialProductDAO();
	}
	
	public ValidDailySpecialProductBean getOneValidDailySpecialProduct(DailySpecialBean dsBean, ProductBean productBean) {
		
		return dao.getOneValidDSProduct(dsBean, productBean);
	}
	
	
}
