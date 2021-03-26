package com.product.model;

import java.util.List;

public class ProductService {
	
	private ProductDAO_interface dao;
	
	public ProductService() {
		dao = new ProductDAO();
	}
	
	public ProductBean addProduct(String product_name, String product_type, String product_intro, Integer product_price, 
								Integer product_available_qty, Integer product_status, Integer product_calorie, Integer degree_of_sweetness, 
								Integer total_star, Integer total_review) {
		
		ProductBean productBean=  new ProductBean();
		
		productBean.setProduct_name(product_name);
		productBean.setProduct_type(product_type);
		productBean.setProduct_intro(product_intro);
		productBean.setProduct_price(product_price);
		productBean.setProduct_available_qty(product_available_qty);
		productBean.setProduct_status(product_status);
		productBean.setProduct_calorie(product_calorie);
		productBean.setDegree_of_sweetness(degree_of_sweetness);
		productBean.setTotal_star(total_star);
		productBean.setTotal_review(total_review);
		dao.insert(productBean);
				
		return productBean;
	}
	
	public ProductBean updateProduct(Integer product_id, String product_name, String product_type, String product_intro, Integer product_price, 
			Integer product_available_qty, Integer product_status, Integer product_calorie, Integer degree_of_sweetness, 
			Integer total_star, Integer total_review) {

		ProductBean productBean=  new ProductBean();
		
		productBean.setProduct_id(product_id);
		productBean.setProduct_name(product_name);
		productBean.setProduct_type(product_type);
		productBean.setProduct_intro(product_intro);
		productBean.setProduct_price(product_price);
		productBean.setProduct_available_qty(product_available_qty);
		productBean.setProduct_status(product_status);
		productBean.setProduct_calorie(product_calorie);
		productBean.setDegree_of_sweetness(degree_of_sweetness);
		productBean.setTotal_star(total_star);
		productBean.setTotal_review(total_review);
		dao.update(productBean);
		
		return productBean;
	}
	
	public void deleteProduct(Integer product_id) {
		dao.delete(product_id);
	}
	
	public ProductBean getOneProduct(Integer product_id) {		
		return dao.findByPrimaryKey(product_id);
	}
	
	public List<ProductBean> getAll(){
		return dao.getAll();
	}
}
