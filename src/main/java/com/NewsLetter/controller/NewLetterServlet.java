package com.newsLetter.controller;

import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newsLetter.model.NewsLetterBean;
import com.newsLetter.model.NewsLetterService;
import com.util.BaseServlet;
import com.util.ResultInfo;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class NewLetterServlet extends BaseServlet{

NewsLetterService newsLetterSvc = new NewsLetterService();

public void test(HttpServletRequest req,HttpServletResponse res) {
}


/*********************** 查詢全部(後臺) ***********************/
public void backend_getAllLetter(HttpServletRequest req,HttpServletResponse res) {
	
	List<NewsLetterBean> letterList = newsLetterSvc.getAll();
	
	ResultInfo info = new ResultInfo();
	
	info.setFlag(true);
	info.setMsg("資料取得成功!");
	info.setData(letterList);
	
	writeValueByWriter(res, info);
	
}
	
	
	

}
