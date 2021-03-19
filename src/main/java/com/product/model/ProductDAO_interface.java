package com.product.model;

import java.util.List;

public interface ProductDAO_interface {
	public void insert(ProductBean productBean);
	public void update(ProductBean productBean);
	public void delete(Integer produc_id);
	public List<ProductBean> getAll();
	public ProductBean  findByPrimaryKey(Integer product_id);

}
