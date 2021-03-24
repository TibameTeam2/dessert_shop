package com.product_image.model;

import java.io.InputStream;
import java.sql.Blob;
import java.util.Arrays;

public class ProductImageBean {
	private Integer image_id;
	private Integer product_id;
	private byte[] product_image;
	
	@Override
	public String toString() {
		return "ProductImageBean [image_id=" + image_id + ", product_id=" + product_id + ", product_image="
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

	public byte[] getProduct_image() {
		return product_image;
	}

	public void setProduct_image(byte[] product_image) {
		this.product_image = product_image;
	}
	
	
	
}
