package com.notice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberBean;
import com.notice.model.NoticeBean;
import com.util.BaseServlet;
import com.util.ResultInfo;

public class NoticeServlet extends BaseServlet{
	
	
	public void test(HttpServletRequest req, HttpServletResponse res) {
		
	
		
		ResultInfo info = new ResultInfo();
		info.setFlag(true);
        info.setMsg("光軒逋逋進站中....");
  
        
        writeValueByWriter(res, info);
		
	}
	
	
	public void getMsg(HttpServletRequest req,HttpServletResponse res) {
		
		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();
		 if (member == null) {
	            info.setFlag(false);
	            info.setMsg("尚未登入!");
	        } else {
	            info.setFlag(true);
//	            req.getSession().setAttribute("member", member);//登入成功
	            info.setMsg("已登入!");
	            List<NoticeBean> noticeBean = new ArrayList<>();
	            
	           
	            
	            
	            
	            info.setData(member); 
	             
	         
	            System.out.println("member = " + member);
	        }
	        writeValueByWriter(res, info);
	    }
		
	}
	

