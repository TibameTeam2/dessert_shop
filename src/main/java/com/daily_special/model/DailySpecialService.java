package com.daily_special.model;

public class DailySpecialService {

	private DailySpecialDao dao;

	public DailySpecialService() {
		dao = new DailySpecialDaoImpl();
	}

	
	//用在收藏, 主要撈discount_price
	public DailySpecialBean findDiscountPriceByProductId(Integer product_id) {

		DailySpecialBean dailySpecialBean = new DailySpecialBean();
		dailySpecialBean.setDiscount_product_id(product_id);
		dao.findDiscountPriceByProductId(product_id);

		return dailySpecialBean;
	}

}
