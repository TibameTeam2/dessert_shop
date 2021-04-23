package com.newsLetter.model;

import java.util.List;

public interface NewsLetterDao {
	
	
	/*************************** 新增 **************************/
	public void insert(NewsLetterBean newsletterBean);

	/*************************** 更新 **************************/
	public void update(NewsLetterBean newsletterBean);

	/*************************** 刪除 **************************/
	public void delete(Integer newsletter_id);

	/*************************** 查PK **************************/
	public NewsLetterBean findByPrimaryKey(Integer newsletter_id);

	/*************************** 查全部 **************************/
	public List<NewsLetterBean> getAll();

}
