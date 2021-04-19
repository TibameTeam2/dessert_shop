package com.daily_special.model;

import java.util.List;


public class DailySpecialService {
	
	private DailySpecialDAO_interface dao;
	
	public DailySpecialService() {
		dao = new DailySpecialDAO();
	}
	
	// 後台，取得"所有"每日優惠
	public List<DailySpecialBean> getAllDailySpecial(){
		return dao.getAll();
	}
	
	
	
	// 前台，取得"在效期內"且"上架中"的每日優惠
	public List<DailySpecialBean> getValidDailySpecial(){
		return dao.getAllValid();
	}

}
