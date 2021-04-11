package com.live_support.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NameServlet extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String uesrId = req.getParameter("uesrId");
		
		req.setAttribute("uesrId", uesrId);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/liveSupport_jsp/chat.jsp");
		dispatcher.forward(req, res);
	}
	
	
	
	
	
	
}
