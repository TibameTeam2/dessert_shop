package com.product.model;

import java.util.List;

public interface ProductDAO_interface {
	public int insert(ProductBean productBean);
	public void update(ProductBean productBean);
	public void delete(Integer produc_id);
	public List<ProductBean> getAll();
	public ProductBean  findByPrimaryKey(Integer product_id);
	public List<ProductBean> getAllWithOneImage();
// 排序
	public List<ProductBean> getAllSortByPurchase();
	public List<ProductBean> getAllSortByPrice();
	public List<ProductBean> getAllSortByCalorie();
	public List<ProductBean> getAllSortBySweetness();
	
	
	
	public ProductBean getOneImageId(Integer product_id);
	public ProductBean getOneProductOneImageId(Integer product_id);

}
