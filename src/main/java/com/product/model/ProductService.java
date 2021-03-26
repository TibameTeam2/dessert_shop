package com.product.model;

import java.util.List;

public class ProductService {
	
	private ProductDAO_interface dao;
	
	public ProductService() {
		dao = new ProductDAO();
	}
	
	public ProductBean addProduct(ProductBean productBean) {
		
		dao.insert(productBean);
				
		return productBean;
	}
	
	public ProductBean updateProduct(ProductBean productBean) {

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
