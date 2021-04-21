package com.daily_special.model;

import java.util.List;

public interface DailySpecialDAO_interface {
	public int insert(DailySpecialBean dsBean);
	public void update(DailySpecialBean dsBean);
	public void delete(Integer discount_product_id);
	public DailySpecialBean findByPrimaryKey(Integer discount_product_id);
	public List<DailySpecialBean> getAll();
	public List<DailySpecialBean> getAllValid();

	
	// 使用product_id(可能會用到，DAO還沒寫的方法)
//	public void deleteByProductId(Integer product_id);
//	public DailySpecialBean findByProductId(Integer product_id);
	
}
