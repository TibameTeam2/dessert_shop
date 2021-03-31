package com.notice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberBean;
import com.member.model.MemberService;
import com.mysql.cj.protocol.x.Notice;
import com.notice.model.NoticeBean;
import com.notice.model.NoticeService;
import com.util.BaseServlet;
import com.util.ResultInfo;

public class NoticeServlet extends BaseServlet {
	
	
	
	public void test(HttpServletRequest req,HttpServletResponse res) {}
	

	public void Msg(HttpServletRequest req, HttpServletResponse res) {

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		MemberService service = new MemberService();
		NoticeService noticeSvc = new NoticeService();

		member = service.login(member);
		ResultInfo info = new ResultInfo();

		if (member != null) {
			
			info.setFlag(true);
			req.getSession().setAttribute("membean", member);// 登入成功
			List<NoticeBean> notice = noticeSvc.getMember(member.getMember_account());

			info.setMsg("登入成功!");
			info.setData(notice);

			System.out.println("notice = " + notice);
			System.out.println("member = " + member);
		}

		writeValueByWriter(res, info);

	}

}
