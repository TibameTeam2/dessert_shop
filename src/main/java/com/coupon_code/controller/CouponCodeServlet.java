package com.coupon_code.controller;

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

import com.announcement_management.model.AnnouncementManagementBean;
import com.announcement_management.model.AnnouncementManagementService;
import com.coupon_code.model.CouponCodeBean;
import com.coupon_code.model.CouponCodeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.BaseServlet;
import com.util.ResultInfo;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class CouponCodeServlet extends BaseServlet{
	
	public void getCouponCodeData(HttpServletRequest rep , HttpServletResponse res) {
		
		CouponCodeService ccSvc = new CouponCodeService();
		
		List<CouponCodeBean> ccl = ccSvc.getAll();
		
		ResultInfo info = new ResultInfo();
		
		info.setFlag(true);
		info.setMsg("資料取得成功");
		info.setData(ccl);
		writeValueByWriter(res, info);
		
	}
	
	public void deleteCC(HttpServletRequest req, HttpServletResponse res) {

		CouponCodeService ccs = new CouponCodeService();
		Integer coupon_code_id = new Integer(req.getParameter("coupon_code_id"));
		ResultInfo info = new ResultInfo();


		ccs.deleteCC(coupon_code_id);
		info.setFlag(true);
		info.setMsg("優惠碼刪除刪除!");

		writeValueByWriter(res, info);
	}
	
	public void addCC(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		CouponCodeService ccs = new CouponCodeService();
//		Integer coupon_code_id = new Integer(req.getParameter("coupon_code_id"));
//		System.out.println(coupon_code_id);
		
		// 獲取數據
		Map<String, String[]> map = req.getParameterMap();
		
		System.out.println("map= " + new ObjectMapper().writeValueAsString(map));
		System.out.println("不太妙阿阿阿阿阿阿阿???");
		// 封裝物件
		CouponCodeBean cc = new CouponCodeBean();
		try {
			BeanUtils.populate(cc, map);
			System.out.println("有看到我???");
			System.out.println(cc);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
//		System.out.println("難道會是這裡?");
//		Part part = req.getPart("announcement_image");
//		System.out.println("不會吧 = =");
//		InputStream in = part.getInputStream();
//		System.out.println("是這裡?");
//		byte[] buf = new byte[in.available()];
//		in.read(buf);
//		in.close();
//		ccb.setAnnouncement_image(buf);
		boolean flag = ccs.addCC2(cc);

//		System.out.println("我覺得是這裡");

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
	
public void updateCC(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
	CouponCodeService ccs = new CouponCodeService();
		
        //獲取數據
        Map<String, String[]> map = req.getParameterMap();
        System.out.println("map= " + new ObjectMapper().writeValueAsString(map));
        System.out.println("資料一直塞不進去阿阿阿阿阿");
        //封裝物件
        CouponCodeBean cc = new CouponCodeBean();
        try {
			BeanUtils.populate(cc, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
//        Part part = req.getPart("announcement_image");
//        InputStream in = part.getInputStream();
//        byte[] buf = new byte[in.available()];
//        in.read(buf);
//        in.close();
//        if (buf.length != 0) {
//        	cc.setAnnouncement_image(buf);
//        }
        
        boolean flag = ccs.updateCC2(cc);
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