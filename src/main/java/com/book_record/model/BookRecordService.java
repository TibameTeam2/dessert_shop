package com.book_record.model;

import java.util.List;

public class BookRecordService {
	
	BookRecordDAO dao = new BookRecordDAO();
	
	//insert可訂位日期
	public boolean openBooking(java.sql.Date open_booking_date) {
		
		BookRecordBean brBean_org = dao.findByBookingDate(open_booking_date);
		if (brBean_org != null) {
			return false;
		}
		
		BookRecordBean brBean = new BookRecordBean();
		brBean.setBooking_date(open_booking_date);
		brBean.setTen_total_count(0);
		brBean.setTwelve_total_count(0);
		brBean.setFourteen_total_count(0);
		brBean.setSixteen_total_count(0);
		brBean.setEighteen_total_count(0);
		brBean.setTwenty_total_count(0);
		
		try {
			dao.insert(brBean);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	//select可訂位日期
	public List<BookRecordBean> getAllOpenBookingDate() {
		
		return dao.getAllOpen();
		
	}
	
	
	//select所有訂位日期
	public List<BookRecordBean> getAllBookingDate() {
			
		return dao.getAll();
			
	}
	
	
	//delete可訂位日期
	public boolean deleteBookRecord(Integer book_record_id) {
		
		try {
			dao.delete(book_record_id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	//取得特定時間的訂位人數
	public Integer getPeopleNum(String booking_date, String booking_time_hour) {
		
		BookRecordBean brBean = dao.findByBookingDate(booking_date);
		
		Integer people_num = null;	
		switch (booking_time_hour) {
			case "10:00:00" :
				people_num = brBean.getTen_total_count();
				break;
			case "12:00:00" :
				people_num = brBean.getTwelve_total_count();
				break;
			case "14:00:00" :
				people_num = brBean.getFourteen_total_count();
				break;
			case "16:00:00" :
				people_num = brBean.getSixteen_total_count();
				break;
			case "18:00:00" :
				people_num = brBean.getEighteen_total_count();
				break;
			case "20:00:00" :
				people_num = brBean.getTwenty_total_count();
				break;
			default :
				break;	
		}
		
		return people_num;
		
	}
	
	
	//根據時間新增人數 update訂位紀錄
	public boolean updateBookingRecord(String booking_date, String booking_time_hour, Integer people_num) {
		
		boolean flag = true;
		BookRecordBean brBean_org = dao.findByBookingDate(booking_date);
		java.sql.Date date_today = new java.sql.Date(System.currentTimeMillis());
		if (brBean_org == null) {
			return false;
		} else if (brBean_org.getBooking_date().getTime() <= date_today.getTime()) {
			return false;
		}
		
		Integer people_num_org = null;	
		switch (booking_time_hour) {
			case "10:00:00" :
				people_num_org = brBean_org.getTen_total_count();
				if ((people_num_org + people_num) <= 20) {
					brBean_org.setTen_total_count(people_num_org + people_num);
				} else {
					flag = false;
				}
				break;
			case "12:00:00" :
				people_num_org = brBean_org.getTwelve_total_count();
				if ((people_num_org + people_num) <= 20) {
					brBean_org.setTwelve_total_count(people_num_org + people_num);
				} else {
					flag = false;
				}
				break;
			case "14:00:00" :
				people_num_org = brBean_org.getFourteen_total_count();
				if ((people_num_org + people_num) <= 20) {
					brBean_org.setFourteen_total_count(people_num_org + people_num);
				} else {
					flag = false;
				}
				break;
			case "16:00:00" :
				people_num_org = brBean_org.getSixteen_total_count();
				if ((people_num_org + people_num) <= 20) {
					brBean_org.setSixteen_total_count(people_num_org + people_num);
				} else {
					flag = false;
				}
				break;
			case "18:00:00" :
				people_num_org = brBean_org.getEighteen_total_count();
				if ((people_num_org + people_num) <= 20) {
					brBean_org.setEighteen_total_count(people_num_org + people_num);
				} else {
					flag = false;
				}
				break;
			case "20:00:00" :
				people_num_org = brBean_org.getTwenty_total_count();
				if ((people_num_org + people_num) <= 20) {
					brBean_org.setTwenty_total_count(people_num_org + people_num);
				} else {
					flag = false;
				}
				break;
			default :
				flag = false;
				break;	
		}
		
		if (flag) {
			try {
				dao.update(brBean_org);
			} catch (Exception e) {
				flag = false;
				e.printStackTrace();
			}		
		}
		
		return flag;
		
	}
	
	
	
	

}
