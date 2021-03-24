package com.newsletter.model;


import java.sql.Timestamp;
import java.util.Arrays;

public class NewsLetterBean {

	private Integer newsletter_id;
	private String  newsletter_content;
	private byte[]  newsletter_image;
	private Timestamp    newsletter_releasing_time;
	private Integer	newsletter_status;
	private String  employee_account;
	
	
	@Override
	public String toString() {
		return "newsletterBean ["
				+ "newsletter_id=" + newsletter_id 
				+ ", newsletter_content=" + newsletter_content
				+ ", newsletter_image=" + Arrays.toString(newsletter_image) 
				+ ", newsletter_releasing_time="+ newsletter_releasing_time 
				+ ", newsletter_status=" + newsletter_status 
				+ ", employee_account=" + employee_account + "]";
	}


	
	public Integer getNewsletter_id() {
		return newsletter_id;
	}


	public void setNewsletter_id(Integer newsletter_id) {
		this.newsletter_id = newsletter_id;
	}


	public String getNewsletter_content() {
		return newsletter_content;
	}


	public void setNewsletter_content(String newsletter_content) {
		this.newsletter_content = newsletter_content;
	}


	public byte[] getNewsletter_image() {
		return newsletter_image;
	}


	public void setNewsletter_image(byte[] newsletter_image) {
		this.newsletter_image = newsletter_image;
	}


	public Timestamp getNewsletter_releasing_time() {
		return newsletter_releasing_time;
	}


	public void setNewsletter_releasing_time(Timestamp newsletter_releasing_time) {
		this.newsletter_releasing_time = newsletter_releasing_time;
	}


	public Integer getNewsletter_status() {
		return newsletter_status;
	}


	public void setNewsletter_status(Integer newsletter_status) {
		this.newsletter_status = newsletter_status;
	}


	public String getEmployee_account() {
		return employee_account;
	}


	public void setEmployee_account(String employee_account) {
		this.employee_account = employee_account;
	}
	
	
	
}
