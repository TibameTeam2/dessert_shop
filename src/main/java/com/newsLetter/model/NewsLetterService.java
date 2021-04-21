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
	
	/*********************** 新增電子報(後臺) ***********************/
	public Boolean addNewsLetter(NewsLetterBean newsLetterBean) {
		
		try {		
			
			dao.insert(newsLetterBean);			
			return true;
					 
		}catch(Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		
	}

}
