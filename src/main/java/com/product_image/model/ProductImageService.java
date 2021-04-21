package com.product_image.model;

import java.io.IOException;
import java.util.List;

import com.product.model.ProductBean;

public class ProductImageService {
	
	private ProductImageDAO_interface dao;
	
	public ProductImageService(){
		dao = new ProductImageDAO();
	}
	
	public ProductImageBean addProductImage(ProductImageBean piBean) throws IOException {
		dao.insert(piBean);
		
		return piBean;
	}
	
	public ProductImageBean updateProductImage(ProductImageBean piBean) throws IOException {
		dao.update(piBean);
		return piBean;
	}
	
	public void deleteProductImage(Integer image_id) {
		dao.delete(image_id);
	}
	
	public ProductImageBean getOneProductImage(Integer image_id) throws IOException {
		return dao.findByPrimaryKey(image_id);
	}
	
	public List<ProductImageBean> getAll() throws IOException{
		return dao.getAll();
	}
	
	public void addMultiProductImages(List<ProductImageBean> piBeanList) throws IOException{
		for(int i = 0; i < piBeanList.size(); i++) {
			addProductImage(piBeanList.get(i));
		}
	}
	
	
}
