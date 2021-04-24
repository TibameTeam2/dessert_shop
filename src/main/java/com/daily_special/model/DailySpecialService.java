package com.daily_special.model;

import java.util.ArrayList;
import java.util.List;

import com.product.model.ProductBean;
import com.product.model.ProductService;


public class DailySpecialService {
	
	private DailySpecialDao dao;
	
	public DailySpecialService() {
		dao = new DailySpecialDaoImpl();
	}
	
	// 後台，取得"所有"的每日"優惠"，不包含商品
	public List<DailySpecialBean> getAllDailySpecial(){
		return dao.getAll();
	}
	
	// 前台，取得"上架中"且"優惠有效"的每日"優惠"，不包含商品
	public List<DailySpecialBean> getValidDailySpecial(){
		List<DailySpecialBean> list = dao.getAllValid();
		// 這裡拿到的每日優惠已經是確認為"產品狀態為上架中"的
//		for (DailySpecialBean dsBean : list) {
//			System.out.println("DS Service裡的 dsBean:" + dsBean);
//		}
		return list;
	}
	
	// 後台 刪除優惠
	public void deleteDailySpecial(Integer product_id){
		dao.delete(product_id);
	}
	
	// 後台 新增優惠
	public void addDailySpecial(DailySpecialBean dsBean) {
		dao.insert(dsBean);
	}
	
	// 後台 更新優惠
	public void updateDailySpecial(DailySpecialBean dsBean) {
		dao.update(dsBean);
	}
	
	
	
	
	
//  下面好像用不到...取到優惠後 在去product拿該商品	
	
	// 後台，取得"所有"的每日"優惠"，含商品*********************************************************
//	public List<ProductBean> getAllDailySpecialProduct(){
//		
//		 List<ProductBean> list_dsProduct = null;
//		 
//		 List<DailySpecialBean> list_dsBean = dao.getAll();// 所有優惠清單
//		 //拿出所有id 去拿商品
//		 Integer product_id;
//		 ProductService productSvc = new ProductService();
//		 ProductBean productBean = new ProductBean();
//		 for (DailySpecialBean dsBean : list_dsBean) {
//			 product_id = dsBean.getProduct_id();
//			 productBean = productSvc.getOneProduct(product_id);
//			 
//			 list_dsProduct.add(productBean);
//		 }
//		return list_dsProduct;// 所有在DB美日優惠的"商品"
//	}
//	
//	// 前台，取得"上架中"且"優惠有效"的每日"優惠"，含商品
//	public List<ProductBean> getValidDailySpecialProduct(){
//		List<DailySpecialBean> list = dao.getAllValid();
//		// 這裡拿到的每日優惠已經是確認為"產品狀態為上架中"的
//		
//		ProductService productSvc = new ProductService();
//		List<ProductBean> product_list = new ArrayList<ProductBean>();
//		
//		for (DailySpecialBean dsBean : list) {
//			Integer product_id = dsBean.getProduct_id();
//			System.out.println("dsBean的product_id:" + product_id);
//			// 用這個product_id去撈product
//			product_list.add(productSvc.getOneProduct(product_id));
//		}
//		return product_list;// 回傳為商品資訊
//	}


	//用在收藏, 主要撈discount_price
	public DailySpecialBean findDiscountPriceByProductId(Integer product_id) {

		DailySpecialBean dailySpecialBean = new DailySpecialBean();
		dailySpecialBean.setDiscount_product_id(product_id);
		dao.findDiscountPriceByProductId(product_id);

		return dailySpecialBean;
	}


}
