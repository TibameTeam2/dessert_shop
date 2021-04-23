package com.newsLetter.model;

import java.util.Base64;
import java.util.List;

import com.subscriber_list.model.SubscriberListBean;
import com.subscriber_list.model.SubscriberListService;

import cn.hutool.extra.mail.MailUtil;

public class NewsLetterService {

	private NewsLetterDaoImpl dao;

	public NewsLetterService() {
		dao = new NewsLetterDaoImpl();
	}

	/*********************** 刪除 ***********************/
	public void deleteNewsLetter(Integer newsletter_id) {
		dao.delete(newsletter_id);
	}

	/*********************** 查詢全部(後臺) ***********************/
	public List<NewsLetterBean> getAll() {
		return dao.getAll();
	}

	/*********************** 新增電子報(後臺) ***********************/
	public Boolean addNewsLetter(NewsLetterBean newsLetterBean) {
		try {
			dao.insert(newsLetterBean);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*********************** 更新電子報(後臺) ***********************/
	public Boolean updateNewsLetter(NewsLetterBean newsLetterBean) {
		try {
			NewsLetterBean newsLetter = dao.findByPrimaryKey(newsLetterBean.getNewsletter_id());

			newsLetter.setNewsletter_releasing_time(newsLetterBean.getNewsletter_releasing_time());
			newsLetter.setEmployee_account(newsLetterBean.getEmployee_account());
			newsLetter.setNewsletter_content(newsLetterBean.getNewsletter_content());

			if (newsLetterBean.getNewsletter_image() != null) {
				newsLetter.setNewsletter_image(newsLetterBean.getNewsletter_image());
			}
			dao.update(newsLetter);
			return true;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return false;
		}
	}
	
	/*********************** 寄送電子報(後臺) ***********************/
	public Boolean sendNewsLetter(Integer newsletter_id) {
		
		NewsLetterBean newsletterBean =dao.findByPrimaryKey(newsletter_id);
		
		SubscriberListService  subscriberListSvc = new SubscriberListService();		
		List<SubscriberListBean> subList=subscriberListSvc.getAll();
		
//		for(SubscriberListBean subBean:subList) {
//			
//			 String img= Base64.getEncoder().encodeToString(newsletterBean.getNewsletter_image());			
//			 String img1= "<img src='data:image/png;base64,"+img+"'>"; 
//			 
//			MailUtil.send(subBean.getSubscriber_email(), "本月消息",newsletterBean.getNewsletter_content()+img1, true);
//		}
		
		
			
			 String img= Base64.getEncoder().encodeToString(newsletterBean.getNewsletter_image());			
			 String img1= "<img src='data:image/png;base64,"+img+"'>"; 
			 
			 MailUtil.send("sweetness_Chen@yahoo.com", "本月消息",newsletterBean.getNewsletter_content()+img1, true);
		
		
		newsletterBean.setNewsletter_status(1);
		
		dao.update(newsletterBean);
		
		return true;
	}
	
}
