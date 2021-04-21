package com.newsLetter.model;

import java.util.List;

public class NewsLetterService {
	
	private NewsLetterDaoImpl dao;

	public NewsLetterService() {
		
		dao =new NewsLetterDaoImpl();
	}
	
	/*********************** 查詢全部(後臺) ***********************/
	public List<NewsLetterBean> getAll(){
		
		return dao.getAll();
	}

}
