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
// 給評論用	
//	public Boolean addReviewStar(ProductBean productBean) {
//		
//		dao.updateReviewStar(productBean);
//		
//		return productBean;
//	}
	
// 給更新銷售	
	public Boolean addProductPurchase(Integer product_id, Integer single_purchase) {
	
		ProductBean productBean = new ProductBean();
		productBean = dao.findByPrimaryKey(product_id);
		if(productBean == null ) {
			return false;
		}else {
			Integer databaseTotal_purchase = productBean.getTotal_purchase();
			Integer newTotal_purchase = databaseTotal_purchase + single_purchase;
			productBean.setTotal_purchase(newTotal_purchase);
			dao.update(productBean);
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
	
	
	public List<ProductBean> getAll(){
		return dao.getAll();
	}
// 排序*4	
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
