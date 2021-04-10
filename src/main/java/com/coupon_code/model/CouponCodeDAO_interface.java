package com.coupon_code.model;

import java.util.List;

public interface CouponCodeDAO_interface {
	
	
	public void insert(CouponCodeBean couponCodeBean);
	public void update(CouponCodeBean couponCodeBean);
	public void delete(Integer coupon_code_id);
	public CouponCodeBean findByPrimaryKey(Integer coupon_code_id);
	public List<CouponCodeBean> getAll();

}
