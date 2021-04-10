package com.coupon_code.model;

import java.sql.Timestamp;
import java.util.List;

public class CouponCodeService {

	private CouponCodeDAO_interface dao;

	public CouponCodeService() {
		dao = new CouponCodeDAO();
	}

	public CouponCodeBean addCC(String coupon_code,Timestamp coupon_code_effective_date,Timestamp coupon_code_expire_date, 
			String coupon_code_text_content,Float coupon_code_text, Integer discount_type, String employee_account) {
		
		CouponCodeBean ccBean = new CouponCodeBean();
		
		ccBean.setCoupon_code(coupon_code);
		ccBean.setCoupon_code_effective_date(coupon_code_effective_date);
		ccBean.setCoupon_code_expire_date(coupon_code_expire_date);
		ccBean.setCoupon_code_text_content(coupon_code_text_content);
		ccBean.setCoupon_code_content(coupon_code_text);
		ccBean.setDiscount_type(discount_type);
		ccBean.setEmployee_account(employee_account);
		
		dao.insert(ccBean);
		
		return ccBean;

	}
	
	public CouponCodeBean updateCC(String coupon_code,Timestamp coupon_code_effective_date,Timestamp coupon_code_expire_date, 
			String coupon_code_text_content,Float coupon_code_text, Integer discount_type, String employee_account,Integer coupon_code_id) {
		
		CouponCodeBean ccBean = new CouponCodeBean();
		
		ccBean.setCoupon_code(coupon_code);
		ccBean.setCoupon_code_effective_date(coupon_code_effective_date);
		ccBean.setCoupon_code_expire_date(coupon_code_expire_date);
		ccBean.setCoupon_code_text_content(coupon_code_text_content);
		ccBean.setCoupon_code_content(coupon_code_text);
		ccBean.setDiscount_type(discount_type);
		ccBean.setEmployee_account(employee_account);
		ccBean.setCoupon_code_id(coupon_code_id);
		
		dao.update(ccBean);
		
		return ccBean;
		
	}
	
	public void deleteCC(Integer coupon_code_id) {
		
		dao.delete(coupon_code_id);
		
	}
	
	public CouponCodeBean getOneCC(Integer coupon_code_id) {
		return dao.findByPrimaryKey(coupon_code_id);
	}
	
	public List<CouponCodeBean> getAll(){
		return dao.getAll();
	}

}
