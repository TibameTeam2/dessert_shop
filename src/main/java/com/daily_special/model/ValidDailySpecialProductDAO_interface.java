package com.daily_special.model;

import java.util.List;

import com.product.model.ProductBean;

public interface ValidDailySpecialProductDAO_interface {
//	public List<ValidDailySpecialProductBean> getAllValidDSProeuct(DailySpecialBean dsBean, ProductBean productBean);
	public ValidDailySpecialProductBean getOneValidDSProduct(DailySpecialBean dsBean, ProductBean productBean);
}
