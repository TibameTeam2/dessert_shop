package com.daily_special.model;

import java.util.List;

import com.product.model.ProductBean;

public interface DailySpecialProductDAO_interface {
//	public List<ValidDailySpecialProductBean> getAllValidDSProeuct(DailySpecialBean dsBean, ProductBean productBean);
	public DailySpecialProductBean getOneDailySpecialProduct(DailySpecialBean dsBean, ProductBean productBean);
}
