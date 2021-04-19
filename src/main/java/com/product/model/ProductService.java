package com.product.model;

import java.util.List;

public class ProductService {
	
	private ProductDAO_interface dao;
	
	public ProductService() {
		dao = new ProductDAO();
	}
	
	public ProductBean addProduct(ProductBean productBean) {
		
		
		int count = dao.insert(productBean);
		productBean.setProduct_id(count);
				
		return productBean;
	}
	
	public ProductBean updateProduct(ProductBean productBean) {

		dao.update(productBean);
		
		return productBean;
	}
// 給評論與星等用
	// 星等依傳過來的數字，review直接加1
	public Boolean addReviewStar(Integer product_id, Integer single_star) {
		ProductBean productBean = new ProductBean();
		productBean = dao.findByPrimaryKey(product_id);
		if(productBean == null) {
			System.out.println("更新商品評價失敗！");
			return false;
		}else {
			Integer databaseTotal_star = productBean.getTotal_star();
			Integer newTotal_star = databaseTotal_star + single_star;
			productBean.setTotal_star(newTotal_star);
			
			Integer newTotal_view = productBean.getTotal_review()+1;
			productBean.setTotal_review(newTotal_view);
			dao.update(productBean);
			System.out.println("新增商品評價成功！");
			return true;
		}
		
	}
	
// 給更新銷售	
	public Boolean addProductPurchase(Integer product_id, Integer single_purchase) {
	
		ProductBean productBean = new ProductBean();
		productBean = dao.findByPrimaryKey(product_id);
		if(productBean == null | single_purchase <= 0 ) {
			System.out.println("更新商品銷售失敗！");
			return false;
		}else {
			Integer databaseTotal_purchase = productBean.getTotal_purchase();
			Integer newTotal_purchase = databaseTotal_purchase + single_purchase;
			productBean.setTotal_purchase(newTotal_purchase);
			dao.update(productBean);
			System.out.println("更新商品銷售成功！");
			return true;
		}
	}
	
// 更新商品狀態
	public Boolean updateProductStatus(Integer product_id, Integer product_status) {
		
		ProductBean productBean = new ProductBean();
		productBean = dao.findByPrimaryKey(product_id);
		if(productBean == null) {
			System.out.println("更新商品狀態失敗！");
			return false;
		}else {
			productBean.setProduct_status(product_status);
			dao.update(productBean);
			System.out.println("更新商品狀態成功！");
			return true;
		}
	}
	
	
	
	
	public void deleteProduct(Integer product_id) {
		dao.delete(product_id);
	}
	
	public ProductBean getOneProduct(Integer product_id) {		
		return dao.findByPrimaryKey(product_id);
	}
	
// 檢查新增商品的名稱
	public ProductBean getOneProductByName(String product_name) {
		return dao.findByProductName(product_name);
	}
	
	
// 後台getAll，全部都要顯示	
	public List<ProductBean> getAll(){
		return dao.getAll();
	}
	
	// 後台，取得"所有"每日優惠
	public List<ProductBean> getAllDailySpecial(){
		return dao.getAll();
	}
	
	
	
	// 前台，取得"在效期內"且"上架中"的每日優惠
	public List<ProductBean> getAvailableDailySpecial(){
		return dao.getAll();
	}
	
	
	
	
	

// 前台的方法，需過慮上架狀態	
	public List<ProductBean> getAllAvailable(){
		return dao.getAllAvailable();
	}
// 前台排序*5	
	public List<ProductBean> getAllSortByPurchase(){
		return dao.getAllSortByPurchase();
	}
	public List<ProductBean> getAllSortByPrice(){
		return dao.getAllSortByPrice();
	}
	public List<ProductBean> getAllSortByCalorie(){
		return dao.getAllSortByCalorie();
	}
	public List<ProductBean> getAllSortBySweetness(){
		return dao.getAllSortBySweetness();
	}
	public List<ProductBean> getAllSortByStar(){
		return dao.getAllSortByStar();
	}
	
	
	
	
	
	public ProductBean getOneProductOneImageId(Integer product_id) {		
		return dao.getOneProductOneImageId(product_id);
	}
	
	public List<ProductBean> getAllWithOneImage() {
		return dao.getAllWithOneImage();
	}
	
	public ProductBean getOneImageId(Integer product_id) {
		return dao.getOneImageId(product_id);
	}
}
