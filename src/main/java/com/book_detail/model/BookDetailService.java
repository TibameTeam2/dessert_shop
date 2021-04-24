package com.book_detail.model;

import java.util.List;

public class BookDetailService {

	BookDetailDAO dao = new BookDetailDAO();
	
	//insert訂位明細
	public boolean setBookingDetail(BookDetailBean bdBean) {
		
		try {
			dao.insert(bdBean);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	//取得所有訂位明細
	public List<BookDetailBean> getAllBookDetail() {
		
		return dao.getAll();
		
	}
	
	//取得單筆訂位明細
	public BookDetailBean getOneBookDetail(Integer booking_detail_id) {
		
		return dao.findByPrimaryKey(booking_detail_id);
		
	}
	
	//update訂位明細狀態
	public boolean updateBookingStatus(BookDetailBean bdBean) {
		
		try {
			dao.update_status(bdBean);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	//取得訂位明細ByMemberAccount
	public List<BookDetailBean> getAllByMemberAccount(String member_account) {
		
		return dao.getAllByMemberAccount(member_account);
		
	}
	
	
}
