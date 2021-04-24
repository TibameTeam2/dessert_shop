package com.book_record.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.book_detail.model.BookDetailBean;
import com.book_detail.model.BookDetailService;
import com.book_record.model.BookRecordBean;
import com.book_record.model.BookRecordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.member.model.MemberBean;
import com.notice.model.NoticeBean;
import com.notice.model.NoticeService;
import com.util.BaseServlet;
import com.util.LineUtil;
import com.util.ResultInfo;

import cn.hutool.core.convert.Convert;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class BookRecordServlet extends BaseServlet {
	
	BookRecordService BRSvc = new BookRecordService();
	BookDetailService BDSvc = new BookDetailService();
	NoticeService NoticeSvc = new NoticeService();
	
	//載入後台所有訂位紀錄
	public void backend_getBookRecordData(HttpServletRequest req, HttpServletResponse res) {
		
		List<BookRecordBean> list = BRSvc.getAllBookingDate();
		ResultInfo info = new ResultInfo();
        info.setFlag(true);
        info.setMsg("取得所有訂位紀錄!");
        info.setData(list);
        writeValueByWriter(res, info);
		
	}
	
	//載入後台所有訂位明細
	public void backend_getBookDetailData(HttpServletRequest req, HttpServletResponse res) {
		
		List<BookDetailBean> list = BDSvc.getAllBookDetail();
		ResultInfo info = new ResultInfo();
        info.setFlag(true);
        info.setMsg("取得所有訂位明細!");
        info.setData(list);
        writeValueByWriter(res, info);
		
	}
	
	//修改訂位明細狀態
	public void backend_updateBookDetail(HttpServletRequest req, HttpServletResponse res) throws IOException {
			
		//獲取數據
		Map<String, String[]> map = req.getParameterMap();
	    System.out.println("map= " + new ObjectMapper().writeValueAsString(map));
	    //查詢原本BookDetail並修改status
	    BookDetailBean bdBean = BDSvc.getOneBookDetail(new Integer(map.get("booking_detail_id")[0])); 
	    bdBean.setBooking_status(new Integer(map.get("booking_status")[0]));
	    System.out.println(bdBean);
	        
	    boolean flag = BDSvc.updateBookingStatus(bdBean);
	        
	    ResultInfo info = new ResultInfo();
	    //創建結果 準備返回前端
	    if (flag) {
	        //修改成功
	        info.setFlag(true);
	        info.setMsg("修改成功!");
	    } else {
	        //修改失敗
	        info.setFlag(false);
	        info.setMsg("修改失敗!");
	    }
	        
	    writeValueByWriter(res, info);
		
	}
	
	
	//新增可訂位日期
	public void backend_openBookingDate(HttpServletRequest req, HttpServletResponse res) {
		
		java.sql.Date open_booking_date = new java.sql.Date(Convert.toDate(req.getParameter("booking_date")).getTime());
		boolean flag = BRSvc.openBooking(open_booking_date);
		
		ResultInfo info = new ResultInfo();
		if (flag) {
			info.setFlag(true);
			info.setMsg("新增成功");
		} else {
			info.setFlag(false);
			info.setMsg("新增失敗");	
		}
		
		writeValueByWriter(res, info);
		
	}
	
	//刪除可訂位日期(訂位紀錄)
	public void backend_deleteBookRecord(HttpServletRequest req, HttpServletResponse res) {
		
		Integer book_record_id = new Integer(req.getParameter("book_record_id"));
		boolean flag = BRSvc.deleteBookRecord(book_record_id);
		
		ResultInfo info = new ResultInfo();
		if (flag) {
			info.setFlag(true);
			info.setMsg("刪除成功");
		} else {
			info.setFlag(false);
			info.setMsg("刪除失敗");	
		}
		
		writeValueByWriter(res, info);
		
	}
	
	
	//載入可訂位日期與已訂位人數
	public void getOpenBookingDate(HttpServletRequest req, HttpServletResponse res) {
		
		ResultInfo info = new ResultInfo();
		
		List<BookRecordBean> list = BRSvc.getAllOpenBookingDate();
		
		if (list == null) {
			info.setFlag(false);
			info.setMsg("尚無開放訂位!");
		} else {
			info.setFlag(true);
			info.setMsg("成功載入可訂位日期與已訂位人數!");
			info.setData(list);
		}
		
		writeValueByWriter(res, info);
		
	}
	
	
	//取特定時間已訂位人數
	public void getPeopleNum(HttpServletRequest req, HttpServletResponse res) {
		
		String booking_date = req.getParameter("booking_date");
		String booking_time_hour = req.getParameter("booking_time_hour");
		Integer people_num = BRSvc.getPeopleNum(booking_date, booking_time_hour);
		
		ResultInfo info = new ResultInfo();
		if (people_num == null) {
			info.setFlag(false);
			info.setMsg("查無資料!");
		} else {
			info.setFlag(true);
			info.setMsg("已取得該時間訂位人數!");
			info.setData(people_num);
		}			
		
		writeValueByWriter(res, info);
		
	}
	
	
	//使用者送出訂位
	public void setBookingDate(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();
		
		//獲取數據
		Map<String, String[]> map1 = req.getParameterMap();
		System.out.println("map1= " + new ObjectMapper().writeValueAsString(map1));
		Map<String,String[]> map = new HashMap<String,String[]>(map1);
		
		//包裝資料並處理資料庫
		//訂位紀錄資料
		Integer people_num = new Integer(map.get("people_num")[0]);
		if (people_num <= 0 || people_num > 20 || people_num == null) {
			info.setFlag(false);
			info.setMsg("訂位失敗!");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/booking.html");
			writeValueByWriter(res, info);
			return ;
		}
		String booking_name = map.get("booking_name")[0].trim();
		if (booking_name.equals("")) {
			info.setFlag(false);
			info.setMsg("請輸入訂位者姓名!");
			writeValueByWriter(res, info);
			return ;
		}
		String contact_num = map.get("contact_num")[0].trim();
		String contact_num_reg = "^[0-9-]{1,15}$";
		if (contact_num.equals("")) {
			info.setFlag(false);
			info.setMsg("請輸入連絡電話!");
			writeValueByWriter(res, info);
			return ;
		} else if (!contact_num.matches(contact_num_reg)) {
			info.setFlag(false);
			info.setMsg("連絡電話格式有誤!");
			writeValueByWriter(res, info);
			return ;
		}	
		
		String booking_date = map.get("booking_date")[0];
		String booking_time_hour = map.get("booking_time_hour")[0];
		//update訂位紀錄
		boolean flag = BRSvc.updateBookingRecord(booking_date, booking_time_hour, people_num);
		if (!flag) {
			info.setFlag(false);
			info.setMsg("訂位失敗!");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/booking.html");
			writeValueByWriter(res, info);
			return ;
		}
	
		//訂位明細資料			
		BookDetailBean bdBean = new BookDetailBean();
		try {
			BeanUtils.populate(bdBean, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String booking_time = booking_date + " " + booking_time_hour;
		bdBean.setBooking_time(java.sql.Timestamp.valueOf(booking_time));
		if (member != null) {
			bdBean.setMember_account(member.getMember_account());
		}
		bdBean.setBooking_status(0);
		//insert訂位明細
		boolean flag2 = BDSvc.setBookingDetail(bdBean);
		if (!flag2) {
			info.setFlag(false);
			info.setMsg("訂位失敗!");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/booking.html");
			writeValueByWriter(res, info);
			return ;
		}	
		
		info.setFlag(true);
		info.setMsg("訂位成功!");
		info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/index.html");
		
		if (member != null) {
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/my-account.html?active=booking");
			//Line通知-訂位成功
			String message = "您已訂位完成!\n訂位時間：\n" + booking_time + "\n訂位人數：" + people_num + "人";
			LineUtil.linePushMessage(member.getMember_account(), message);
			
			//Notice
			NoticeBean noticeBean = new NoticeBean();
			noticeBean.setMember_account(member.getMember_account());
			noticeBean.setNotice_type(3);
			noticeBean.setNotice_dispatcher(req.getContextPath() + "/TEA103G2/front-end/my-account.html?active=booking");
			String notice_content =  "通知!訂位完成!時間：" + booking_time + "人數：" + people_num + "人!";
			noticeBean.setNotice_content(notice_content);	
			NoticeSvc.addWSNotice(noticeBean);
		}	
		
		writeValueByWriter(res, info);
		
	}
	
	
	//載入該會員所有訂位明細
	public void getBookDetailData(HttpServletRequest req, HttpServletResponse res) {
		
		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
        ResultInfo info = new ResultInfo();
        if (member == null) {
            info.setFlag(false);
            info.setMsg("尚未登入!");
            req.getSession().setAttribute("location", req.getContextPath() + "/TEA103G2/front-end/my-account.html");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
			writeValueByWriter(res, info);
			return ;
        }
        
        List<BookDetailBean> list = BDSvc.getAllByMemberAccount(member.getMember_account());
        
        info.setData(list);
		info.setFlag(true);
		info.setMsg("已將訂位明細資料載入!");
        
		writeValueByWriter(res, info);
        
	}
	
	
	//取消訂位
	public void cancelBooking(HttpServletRequest req, HttpServletResponse res) {
		
		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
        ResultInfo info = new ResultInfo();
        if (member == null) {
            info.setFlag(false);
            info.setMsg("尚未登入!");
            req.getSession().setAttribute("location", req.getContextPath() + "/TEA103G2/front-end/my-account.html?active=booking");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
			writeValueByWriter(res, info);
			return ;
        }
        
        //獲取數據
      	Integer booking_detail_id = new Integer(req.getParameter("booking_detail_id"));
        //查詢原本BookDetail並修改status
	    BookDetailBean bdBean = BDSvc.getOneBookDetail(booking_detail_id); 
	    bdBean.setBooking_status(2);
	    System.out.println(bdBean);
	        
	    boolean flag = BDSvc.updateBookingStatus(bdBean);
	    
	    //創建結果 準備返回前端
	    if (flag) {
	        //取消成功
	        info.setFlag(true);
	        info.setMsg("取消成功!");
	        info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/my-account.html?active=booking");
	        
	        //Line通知-取消成功
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        String booking_time = sdf.format(bdBean.getBooking_time());
	        String message = "您已取消訂位!\n訂位時間：\n" + booking_time + "\n訂位人數：" + bdBean.getPeople_num() + "人";
			LineUtil.linePushMessage(member.getMember_account(), message);
			
			//Notice
			NoticeBean noticeBean = new NoticeBean();
			noticeBean.setMember_account(member.getMember_account());
			noticeBean.setNotice_type(3);
			noticeBean.setNotice_dispatcher(req.getContextPath() + "/TEA103G2/front-end/my-account.html?active=booking");
			String notice_content =  "通知!訂位取消!時間：" + booking_time + "人數：" + bdBean.getPeople_num() + "人!";
			noticeBean.setNotice_content(notice_content);	
			NoticeSvc.addWSNotice(noticeBean);	        
	        
	    } else {
	        //取消失敗
	        info.setFlag(false);
	        info.setMsg("取消失敗!");	   
	    }	
	    
		writeValueByWriter(res, info);
	    
	}
	
	
	
}
