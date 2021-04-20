package com.coupon.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.coupon.model.CouponBean;
import com.coupon.model.CouponService;
import com.coupon_code.model.CouponCodeBean;
import com.coupon_code.model.CouponCodeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.member.model.MemberService;
import com.util.BaseServlet;
import com.util.ResultInfo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.mail.MailUtil;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class CouponServlet extends BaseServlet {
	
	MemberService memberSvc = new MemberService();
	
	public void getCouponData(HttpServletRequest req , HttpServletResponse res) {
		
		CouponService cS = new CouponService();
		
		List<CouponBean> cl = cS.getAll();
		
		ResultInfo info = new ResultInfo();
		
		info.setFlag(true);
		info.setData(cl);
		info.setMsg("撈到資料囉!");
		writeValueByWriter(res, info);
	}
	
	public void deleteCP(HttpServletRequest req, HttpServletResponse res) {

		CouponService cS = new CouponService();
		Integer coupon_id = new Integer(req.getParameter("coupon_id"));
		ResultInfo info = new ResultInfo();


		cS.deleteCoupon(coupon_id);
		info.setFlag(true);
		info.setMsg("優惠券刪除刪除!");

		writeValueByWriter(res, info);
	}
	
	
	public void addCP(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		CouponService cS = new CouponService();
//		MailTest mt = new MailTest();
		
		// 獲取數據
		Map<String, String[]> map = req.getParameterMap();
		
		String[] str = map.get("coupon_sending_time")[0].split("T");
		String coupon_sending_time = str[0]+" "+str[1];
		
		System.out.println("map= " + new ObjectMapper().writeValueAsString(map));
		System.out.println("coupon_sending_time : " + coupon_sending_time);
		
		Date date = DateUtil.parse(coupon_sending_time);
		System.out.println("date : " + date);
		
		System.out.println("不太妙阿阿阿阿阿阿阿???");
		// 封裝物件
		CouponBean cp = new CouponBean();
		try {
			BeanUtils.populate(cp, map);
			System.out.println("有看到我???");
			System.out.println(cp);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		boolean flag = cS.addCP2(cp);
		
		
//		String member_account = map.get("member_account")[0]; //map裡面存的是 字串陣列
//		MemberBean member_bean = memberSvc.getOneMember(member_account);
//		String member_email = member_bean.getMember_email();
		
		String member_email = memberSvc.getOneMember(map.get("member_account")[0]).getMember_email( );
		
//		String[] member_account = map.get("member_account");
//		
//		String member_email = memberSvc.getOneMember(member_account).getMember_email( );
		
		MailUtil.send(member_email, "嗜甜X用戶回饋", "親愛的用戶您好,感謝您一直支持本店,為表達我們誠摯的心意,賞你一張優惠券", false);
		System.out.println("寄信囉~~");
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
	
	
	public void updateCP(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		CouponService cS = new CouponService();
			
	        //獲取數據
	        Map<String, String[]> map = req.getParameterMap();
	        System.out.println("map= " + new ObjectMapper().writeValueAsString(map));
	        System.out.println("資料一直塞不進去阿阿阿阿阿");
	        //封裝物件
	        CouponBean cp = new CouponBean();
	        try {
				BeanUtils.populate(cp, map);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} 
	        boolean flag = cS.updateCP2(cp);
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
