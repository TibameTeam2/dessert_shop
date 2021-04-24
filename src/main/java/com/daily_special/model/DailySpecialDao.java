package com.daily_special.model;

import java.util.List;

public interface DailySpecialDao {
	
	public void insert(DailySpecialBean dsBean);
	
	public void update(DailySpecialBean dsBean);
	
	public void delete(Integer discount_product_id);
	
	public List<DailySpecialBean> getAll();
	
	public DailySpecialBean findByPrimaryKey(Integer product_id);

	public List<DailySpecialBean> getAllValid();

	//用在收藏, 要撈discount_price
	public DailySpecialBean findDiscountPriceByProductId(Integer product_id);

}
