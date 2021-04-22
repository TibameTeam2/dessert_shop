package com.newsLetter.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newsLetter.model.NewsLetterBean;
import com.newsLetter.model.NewsLetterService;
import com.util.BaseServlet;
import com.util.ResultInfo;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class NewLetterServlet extends BaseServlet {

	NewsLetterService newsLetterSvc = new NewsLetterService();

	public void test(HttpServletRequest req, HttpServletResponse res) {
	}

	/*********************** 查詢全部(後臺) ***********************/
	public void backend_getAllLetter(HttpServletRequest req, HttpServletResponse res) {

		List<NewsLetterBean> letterList = newsLetterSvc.getAll();

		ResultInfo info = new ResultInfo();

		info.setFlag(true);
		info.setMsg("資料取得成功!");
		info.setData(letterList);

		writeValueByWriter(res, info);

	}

	/*********************** 新增電子報(後臺) ***********************/
	public void backend_addNewsLetter(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		Map<String, String[]> map1 = req.getParameterMap();
		Map<String, String[]> map = new HashMap<String, String[]>(map1);

		String[] str = map.get("newsletter_releasing_time")[0].split("T");
		String newsletter_releasing_time = str[0] + " " + str[1];

		System.out.println("map= " + new ObjectMapper().writeValueAsString(map));
		System.out.println("newsletter_releasing_time=" + newsletter_releasing_time);

		String[] temp = new String[1];
		temp[0] = newsletter_releasing_time;

		map.replace("newsletter_releasing_time", temp);

		NewsLetterBean newsLetterBean = new NewsLetterBean();
		try {
			BeanUtils.populate(newsLetterBean, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		newsLetterBean.setNewsletter_status(0);

		// 讀取圖片
		Part part = req.getPart("newsletter_image");
		InputStream in = part.getInputStream();
		byte[] buf = new byte[in.available()];
		in.read(buf);
		newsLetterBean.setNewsletter_image(buf);
		in.close();

		boolean flag = newsLetterSvc.addNewsLetter(newsLetterBean);

		System.out.println(newsLetterBean);

		ResultInfo info = new ResultInfo();
		if (flag) {
			info.setFlag(true);
			info.setMsg("新增成功!");
		} else {
			info.setFlag(false);
			info.setMsg("新增失敗!");
		}
		writeValueByWriter(res, info);
	}

}
