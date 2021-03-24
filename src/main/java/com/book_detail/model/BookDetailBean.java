package com.book_detail.model;

import java.sql.Timestamp;

public class BookDetailBean {
	private Integer booking_detail_id;
	private String member_account;
	private Timestamp booking_establish_time;
	private Timestamp booking_time;
	private Integer people_num;
	private Integer booking_status;
	private String book_postscript;
	private String contact_num;
	private String booking_name;
	
	@Override
	public String toString() {
		return "BookDetailBean [booking_detail_id=" + booking_detail_id + ", member_account=" + member_account
				+ ", booking_establish_time=" + booking_establish_time + ", booking_time=" + booking_time
				+ ", people_num=" + people_num + ", booking_status=" + booking_status + ", book_postscript="
				+ book_postscript + ", contact_num=" + contact_num + ", booking_name=" + booking_name + "]";
	}

	public Integer getBooking_detail_id() {
		return booking_detail_id;
	}

	public void setBooking_detail_id(Integer booking_detail_id) {
		this.booking_detail_id = booking_detail_id;
	}

	public String getMember_account() {
		return member_account;
	}

	public void setMember_account(String member_account) {
		this.member_account = member_account;
	}

	public Timestamp getBooking_establish_time() {
		return booking_establish_time;
	}

	public void setBooking_establish_time(Timestamp booking_establish_time) {
		this.booking_establish_time = booking_establish_time;
	}

	public Timestamp getBooking_time() {
		return booking_time;
	}

	public void setBooking_time(Timestamp booking_time) {
		this.booking_time = booking_time;
	}

	public Integer getPeople_num() {
		return people_num;
	}

	public void setPeople_num(Integer people_num) {
		this.people_num = people_num;
	}

	public Integer getBooking_status() {
		return booking_status;
	}

	public void setBooking_status(Integer booking_status) {
		this.booking_status = booking_status;
	}

	public String getBook_postscript() {
		return book_postscript;
	}

	public void setBook_postscript(String book_postscript) {
		this.book_postscript = book_postscript;
	}

	public String getContact_num() {
		return contact_num;
	}

	public void setContact_num(String contact_num) {
		this.contact_num = contact_num;
	}

	public String getBooking_name() {
		return booking_name;
	}

	public void setBooking_name(String booking_name) {
		this.booking_name = booking_name;
	}
	

}
