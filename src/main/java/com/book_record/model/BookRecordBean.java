package com.book_record.model;

import java.sql.Date;

public class BookRecordBean {
	private Integer book_record_id;
	private Date booking_date;
	private Integer ten_total_count;
	private Integer twelve_total_count;
	private Integer fourteen_total_count;
	private Integer sixteen_total_count;
	private Integer eighteen_total_count;
	private Integer twenty_total_count;
	
	@Override
	public String toString() {
		return "BookRecordDAOTest [book_record_id=" + book_record_id + ", booking_date=" + booking_date
				+ ", ten_total_count=" + ten_total_count + ", twelve_total_count=" + twelve_total_count
				+ ", fourteen_total_count=" + fourteen_total_count + ", sixteen_total_count=" + sixteen_total_count
				+ ", eighteen_total_count=" + eighteen_total_count + ", twenty_total_count=" + twenty_total_count + "]";
	}

	public Integer getBook_record_id() {
		return book_record_id;
	}

	public void setBook_record_id(Integer book_record_id) {
		this.book_record_id = book_record_id;
	}

	public Date getBooking_date() {
		return booking_date;
	}

	public void setBooking_date(Date booking_date) {
		this.booking_date = booking_date;
	}

	public Integer getTen_total_count() {
		return ten_total_count;
	}

	public void setTen_total_count(Integer ten_total_count) {
		this.ten_total_count = ten_total_count;
	}

	public Integer getTwelve_total_count() {
		return twelve_total_count;
	}

	public void setTwelve_total_count(Integer twelve_total_count) {
		this.twelve_total_count = twelve_total_count;
	}

	public Integer getFourteen_total_count() {
		return fourteen_total_count;
	}

	public void setFourteen_total_count(Integer fourteen_total_count) {
		this.fourteen_total_count = fourteen_total_count;
	}

	public Integer getSixteen_total_count() {
		return sixteen_total_count;
	}

	public void setSixteen_total_count(Integer sixteen_total_count) {
		this.sixteen_total_count = sixteen_total_count;
	}

	public Integer getEighteen_total_count() {
		return eighteen_total_count;
	}

	public void setEighteen_total_count(Integer eighteen_total_count) {
		this.eighteen_total_count = eighteen_total_count;
	}

	public Integer getTwenty_total_count() {
		return twenty_total_count;
	}

	public void setTwenty_total_count(Integer twenty_total_count) {
		this.twenty_total_count = twenty_total_count;
	}
	
	

}
