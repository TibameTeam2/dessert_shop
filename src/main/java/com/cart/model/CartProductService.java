package com.cart.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.card_detail.model.CardDetailBean;
import com.coupon.model.CouponBean;
import com.coupon_code.model.CouponCodeBean;
import com.order_detail.model.OrderDetailBean;
import com.order_master.model.OrderMasterBean;

import cn.hutool.core.util.RandomUtil;

public class CartProductService {

	CartProductDAO dao = new CartProductDAO();

	// 拿cart_id, cart.product_id, product_quantity, product_name, product_price/
	// product_image的src
	public List<List> getCartDataByMemberAccount(String member_account, String contextPath) {

		List<List> list = new ArrayList<List>();

		List<CartProductBean> list_cartproductBean = dao.selectByMemberAccount(member_account);
		list.add(list_cartproductBean);

		List<String> list_img_src = new ArrayList<String>();
		List<Integer> list_max_product_quantity = new ArrayList<Integer>();
		for (int i = 0; i < list_cartproductBean.size(); i++) {
			// img_src
			String img_src = contextPath + "/cart/getProductImage?product_id=" + list_cartproductBean.get(i).getProduct_id();
			list_img_src.add(img_src);
			// max_product_quantity
			Integer max_product_quantity = dao.selectProductAvailableQty(list_cartproductBean.get(i).getProduct_id());
			list_max_product_quantity.add(max_product_quantity);
		}
		list.add(list_img_src);
		list.add(list_max_product_quantity);

		return list;

	}

	// 拿coupon資料
	public List<CouponBean> getCouponDataByMemberAccount(String member_account) {

		return dao.selectCouponByMemberAccount(member_account);

	}

	// 回傳圖片資料流
	public InputStream getProductImageByProductId(Integer product_id) {

		return dao.getFirstImageByProductId(product_id);

	}

	// update購物車內商品數量並回傳更新後的數量值
	public List<Integer> updateQuantityAtCart(Integer cart_id, Integer product_id, Integer product_quantity) {
		
		List<Integer> list = new ArrayList<Integer>();
		Integer max_product_quantity = dao.selectProductAvailableQty(product_id);
		if (product_quantity <= max_product_quantity) {
			dao.updateProductQuantity(cart_id, product_quantity);
			list.add(product_quantity);
			list.add(max_product_quantity);
		} else if (max_product_quantity == null || max_product_quantity == 0) {	
			dao.deleteCart(cart_id);
			list.add(0);
			list.add(0);
		} else {
			dao.updateProductQuantity(cart_id, max_product_quantity);
			list.add(max_product_quantity);
			list.add(max_product_quantity);
		}
		
		return list;
		
	}

	// delete購物車內商品
	public void deleteProductAtCart(Integer cart_id) {

		dao.deleteCart(cart_id);

	}
	
	// 查詢商品現貨數量
	public Integer getProductAvailableQty(Integer product_id) {
		
		return dao.selectProductAvailableQty(product_id);
		
	}	
	

	// 查詢優惠碼
	public CouponCodeBean selectCouponCode(String coupon_code) {

		return dao.selectCouponCodeData(coupon_code);

	}

	// 使用會員帳號+優惠碼查詢單筆優惠券對照 以及回傳用
	public CouponBean selectCoupon(String member_account, Integer coupon_code_id) {

		return dao.selectCouponData(member_account, coupon_code_id);

	}

	// insert優惠券
	public void insertCoupon(CouponBean CB) {

		dao.insertCouponData(CB);

	}

	// 查詢全部信用卡
	public List<CardDetailBean> selectAllCard(String member_account) {

		return dao.selectCardByMember(member_account);

	}

	// insert信用卡並回傳Id
	public Integer insertCard(CardDetailBean card_detailBean) {

		return dao.insertCard(card_detailBean);

	}

	// 查詢單筆信用卡ById
	public CardDetailBean selectOneCard(Integer card_id) {

		return dao.selectCardById(card_id);

	}

	// 刪除信用卡
	public void deleteCardById(Integer card_id) {

		dao.deleteCard(card_id);

	}

	// 拿cart_id, cart.product_id, product_quantity, product_name, product_price
	public List<CartProductBean> getCartDataBeforeOrder(String member_account) {

		return dao.selectByMemberAccount(member_account);

	}

	// 新增訂單資料
	public Integer insertOrder(OrderMasterBean orderMasterBean, List<OrderDetailBean> list_orderDetailBean) {

		return dao.insertOrderMaster(orderMasterBean, list_orderDetailBean);

	}

	// 發票亂生成
	public String invoice_random() {

		return RandomUtil.randomString(RandomUtil.BASE_CHAR, 2).toUpperCase() + RandomUtil.randomNumbers(8);

	}

	// 匯款帳戶亂生成
	public String payCode_random() {

		return RandomUtil.randomNumbers(16);

	}
	
	// 刪除購物車商品+減少商品現貨數量
	public void deleteCartAndReduceProductQty(List<CartProductBean> list_cartProductBean) {
		
		dao.deleteCartAndUpdateProductQty(list_cartProductBean);
		
	}
	
	
	
	

	// 修改優惠券狀態
	public void updateCouponStatus(Integer coupon_id, Integer coupon_status) {

		dao.updateCouponStatusById(coupon_id, coupon_status);

	}
	
	// Line發通知
	public void lineMessage(String member_account, String message) {
		
		
		
	}
	
	
	
	
	/* ==================== 商品頁面新增商品到購物車 ==================== */
	public void insertOrUpdateCart(CartBean cartBean) {
		
		String member_account = cartBean.getMember_account();
		Integer product_id = cartBean.getProduct_id();
		Integer product_quantity = cartBean.getProduct_quantity();
		Integer max_product_quantity = dao.selectProductAvailableQty(product_id);
		if (product_quantity <= 0 || max_product_quantity == 0) {
			return ;
		}
			
		CartBean cartBean_exist = dao.findCart(member_account, product_id);
		if (cartBean_exist == null) {
			dao.insertCart(cartBean);
		} else {
			Integer new_product_quantity = cartBean_exist.getProduct_quantity() + product_quantity;
			if (new_product_quantity <= max_product_quantity) {
				cartBean_exist.setProduct_quantity(new_product_quantity);
				dao.updateCart(cartBean_exist);		
			} else {
				cartBean_exist.setProduct_quantity(max_product_quantity);
				dao.updateCart(cartBean_exist);
			}
			
		}
		
	}
	
	
	

}
