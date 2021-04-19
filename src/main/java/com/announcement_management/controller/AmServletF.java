package com.announcement_management.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import com.announcement_management.model.AnnouncementManagementBean;
import com.announcement_management.model.AnnouncementManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.BaseServlet;
import com.util.ResultInfo;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
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

	public void getAllAM01(HttpServletRequest req, HttpServletResponse res) {

		AnnouncementManagementService ams = new AnnouncementManagementService();

		AnnouncementManagementBean amb = (AnnouncementManagementBean) req.getSession().getAttribute("amb");

		List<AnnouncementManagementBean> ambl = ams.getAll01();

		ResultInfo info = new ResultInfo();

		info.setFlag(true);
		info.setMsg("資料取得成功");
		info.setData(ambl);

		writeValueByWriter(res, info);
	}

	public void deleteAM(HttpServletRequest req, HttpServletResponse res) {

		AnnouncementManagementService ams = new AnnouncementManagementService();
		Integer announcement_id = new Integer(req.getParameter("announcement_id"));
		ResultInfo info = new ResultInfo();

//		if (announcement_id == null) {
//			
//			info.setFlag(false);
//            info.setMsg("未傳入參數!");
//            writeValueByWriter(res, info);
//            return;
//			
//		}

		ams.deleteAnn(announcement_id);
		info.setFlag(true);
		info.setMsg("公告刪除刪除!");

		writeValueByWriter(res, info);
	}

	public void addAM(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		AnnouncementManagementService ams = new AnnouncementManagementService();

		// 獲取數據
		Map<String, String[]> map = req.getParameterMap();
		System.out.println("map= " + new ObjectMapper().writeValueAsString(map));
		// 封裝物件
		AnnouncementManagementBean am = new AnnouncementManagementBean();
		try {
			BeanUtils.populate(am, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		System.out.println("難道會是這裡?");
		Part part = req.getPart("announcement_image");
		System.out.println("不會吧 = =");
		InputStream in = part.getInputStream();
		System.out.println("是這裡?");
		byte[] buf = new byte[in.available()];
		in.read(buf);
		in.close();
		am.setAnnouncement_image(buf);
		boolean flag = ams.addAM2(am);

		System.out.println("我覺得是這裡");

		ResultInfo info = new ResultInfo();
		// 創建結果 準備返回前端
		if (flag) {
			// 註冊成功
			info.setFlag(true);
			info.setMsg("新增成功!");
		} else {
			// 註冊失敗
			info.setFlag(false);
			info.setMsg("新增失敗!");
		}
		writeValueByWriter(res, info);

	}
	
	public void updateAM(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		AnnouncementManagementService ams = new AnnouncementManagementService();
		
        //獲取數據
        Map<String, String[]> map = req.getParameterMap();
        System.out.println("map= " + new ObjectMapper().writeValueAsString(map));
        //封裝物件
        AnnouncementManagementBean am = new AnnouncementManagementBean();
        try {
			BeanUtils.populate(am, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
        Part part = req.getPart("announcement_image");
        InputStream in = part.getInputStream();
        byte[] buf = new byte[in.available()];
        in.read(buf);
        in.close();
        if (buf.length != 0) {
        	am.setAnnouncement_image(buf);
        }
        
        boolean flag = ams.updateAM2(am);
        ResultInfo info = new ResultInfo();
        //創建結果 準備返回前端
        if (flag) {
            //註冊成功
            info.setFlag(true);
            info.setMsg("修改成功!");
        } else {
            //註冊失敗
            info.setFlag(false);
            info.setMsg("修改失敗!");
        }
        writeValueByWriter(res, info);
    }

}
