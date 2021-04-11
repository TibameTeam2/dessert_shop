package com.announcement_management.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.announcement_management.model.AnnouncementManagementBean;
import com.announcement_management.model.AnnouncementManagementService;
import com.util.BaseServlet;
import com.util.ResultInfo;

public class AmServletF extends BaseServlet {

	public void getAllAM(HttpServletRequest req, HttpServletResponse res) {

		AnnouncementManagementService ams = new AnnouncementManagementService();
		
		AnnouncementManagementBean amb = (AnnouncementManagementBean) req.getSession().getAttribute("amb");
		
		List<AnnouncementManagementBean> ambl = ams.getAll();
		
		ResultInfo info = new ResultInfo();
		
		info.setFlag(true);
		info.setMsg("資料取得成功");
		info.setData(ambl);
		
		writeValueByWriter(res, info);
	}

}
