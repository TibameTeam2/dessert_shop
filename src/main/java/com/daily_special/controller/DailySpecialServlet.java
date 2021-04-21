package com.daily_special.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daily_special.model.DailySpecialBean;
import com.daily_special.model.DailySpecialService;
import com.util.BaseServlet;


public class DailySpecialServlet extends BaseServlet {
	
	//用在收藏, 主要撈discount_price
//	public Integer findDiscountPriceByProductId(HttpServletRequest req, HttpServletResponse res) {
//		
//		DailySpecialService dailySpecialSvc = new DailySpecialService();
//		DailySpecialBean dailySpecialBean = dailySpecialSvc.findDiscountPriceByProductId(null);
//		
//		return null;
//		
//	}

}
