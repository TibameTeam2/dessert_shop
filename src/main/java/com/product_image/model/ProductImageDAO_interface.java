package com.product_image.model;

import java.io.IOException;
import java.util.List;

public interface ProductImageDAO_interface {
	public void insert(ProductImageBean piBean) throws IOException;
	public void update(ProductImageBean piBean) throws IOException;
	public void delete(Integer image_id);
	public List<ProductImageBean> getAll() throws IOException;
	public ProductImageBean findByPrimaryKey(Integer image_id) throws IOException;
}
