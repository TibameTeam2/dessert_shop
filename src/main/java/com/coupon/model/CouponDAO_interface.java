package com.coupon.model;

import java.util.List;

public interface CouponDAO_interface {
	
	public void insert(CouponBean couponBean);
	public void update(CouponBean couponBean);
	public void delete(Integer coupon_id);
	public CouponBean findByPrimaryKey(Integer coupon_id);
	public List<CouponBean> getAll();

}
