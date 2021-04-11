package com.coupon.model;

import java.sql.Timestamp;
import java.util.List;

public class CouponService {

	private CouponDAO_interface dao;

	public CouponService() {

		dao = new CouponDAO();
	}

	public CouponBean addCoupon(String member_account, Timestamp coupon_sending_time, Timestamp coupon_effective_date,
			Timestamp coupon_expire_date, String coupon_text_content, Float coupon_content, Integer discount_type,
			Integer coupon_status, String employee_account, Integer coupon_code_id) {

		CouponBean cBean = new CouponBean();

		cBean.setMember_account(member_account);
		cBean.setCoupon_sending_time(coupon_sending_time);
		cBean.setCoupon_effective_date(coupon_effective_date);
		cBean.setCoupon_expire_date(coupon_expire_date);
		cBean.setCoupon_text_content(coupon_text_content);
		cBean.setCoupon_content(coupon_content);
		cBean.setDiscount_type(discount_type);
		cBean.setCoupon_status(coupon_status);
		cBean.setEmployee_account(employee_account);
		cBean.setCoupon_code_id(coupon_code_id);

		dao.insert(cBean);
		
		return cBean;

	}
	
	public CouponBean updateCoupon(String member_account, Timestamp coupon_sending_time, Timestamp coupon_effective_date,
			Timestamp coupon_expire_date, String coupon_text_content, Float coupon_content, Integer discount_type,
			Integer coupon_status, String employee_account, Integer coupon_code_id , Integer coupon_id) {

		CouponBean cBean = new CouponBean();

		cBean.setMember_account(member_account);
		cBean.setCoupon_sending_time(coupon_sending_time);
		cBean.setCoupon_effective_date(coupon_effective_date);
		cBean.setCoupon_expire_date(coupon_expire_date);
		cBean.setCoupon_text_content(coupon_text_content);
		cBean.setCoupon_content(coupon_content);
		cBean.setDiscount_type(discount_type);
		cBean.setCoupon_status(coupon_status);
		cBean.setEmployee_account(employee_account);
		cBean.setCoupon_code_id(coupon_code_id);
		cBean.setCoupon_code_id(coupon_id);

		dao.update(cBean);
		
		return cBean;

	}
	
	
	
	public void deleteCoupon(Integer coupon_id) {
		
		dao.delete(coupon_id);
	}
	
	public CouponBean getOneCoupon(Integer coupon_id) {
		
		return dao.findByPrimaryKey(coupon_id);
	}
	
	public List<CouponBean> getAll(){
		
		return dao.getAll();
	}

}
