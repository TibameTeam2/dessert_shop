package com.product_image.model;

import java.io.InputStream;
import java.sql.Blob;

public class ProductImageBean {
	private Integer image_id;
	private Integer product_id;
	private Blob product_image;
	
	@Override
	public String toString() {
		return "product_imageBean [image_id=" + image_id + ", product_id=" + product_id + ", product_image="
				+ product_image + "]";
	}

	public Integer getImage_id() {
		return image_id;
	}

	public void setImage_id(Integer image_id) {
		this.image_id = image_id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	
	public Blob getProduct_image() {
		return product_image;
	}

	public void setProduct_image(Blob product_image) {
		this.product_image = product_image;
	}
	
	
}
