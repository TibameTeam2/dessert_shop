package com.bucket_list.model;

import java.io.Serializable;
import java.sql.Date;

public class BucketListBean implements Serializable {
	private Integer bucket_list_id;
	private String member_account;
	private Integer product_id;
	private Integer bucket_list_status;
	@Override
	public String toString() {
		return "BucketListBean [bucket_list_id=" + bucket_list_id + ", member_account=" + member_account
				+ ", product_id=" + product_id + ", bucket_list_status=" + bucket_list_status + "]";
	}
	public Integer getBucket_list_id() {
		return bucket_list_id;
	}
	public void setBucket_list_id(Integer bucket_list_id) {
		this.bucket_list_id = bucket_list_id;
	}
	public String getMember_account() {
		return member_account;
	}
	public void setMember_account(String member_account) {
		this.member_account = member_account;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Integer getBucket_list_status() {
		return bucket_list_status;
	}
	public void setBucket_list_status(Integer bucket_list_status) {
		this.bucket_list_status = bucket_list_status;
	}
	

	
}
